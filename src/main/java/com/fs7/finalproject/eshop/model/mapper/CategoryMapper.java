package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDto> {

  private UserRepository userRepository;
  private CategoryRepository categoryRepository;

  private ModelMapper mapper;

  @Autowired
  public CategoryMapper(ModelMapper mapper,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository) {
    super(Category.class, CategoryDto.class);
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.mapper = mapper;
  }

  private Long getCrUserId(Category source) {
    return Objects.isNull(source) || Objects.isNull(source.getCrUser()) ? null : source.getCrUser().getId();
  }

  private Long getLmUserId(Category source) {
    return Objects.isNull(source) || Objects.isNull(source.getLmUser()) ? null : source.getLmUser().getId();
  }

  private Long getCategoryId(Category source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getParentCategory()) ? null : source.getParentCategory().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Category.class, CategoryDto.class)
        .addMappings(m -> m.skip(CategoryDto::setCrUserId))
        .addMappings(m -> m.skip(CategoryDto::setLmUserId))
        .addMappings(m -> m.skip(CategoryDto::setParentCategoryId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(CategoryDto.class, Category.class)
        .addMappings(m -> m.skip(Category::setCrUser))
        .addMappings(m -> m.skip(Category::setLmUser))
        .addMappings(m -> m.skip(Category::setParentCategory))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Category source, CategoryDto destination) {
    destination.setCrUserId(getCrUserId(source));
    destination.setLmUserId(getLmUserId(source));
    destination.setParentCategoryId(getCategoryId(source));
  }

  @Override
  void mapSpecificFields(CategoryDto source, Category destination) {
    destination.setCrUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setLmUser(userRepository.findById(source.getLmUserId()).orElse(null));
    destination.setParentCategory(
        Objects.isNull(source.getParentCategoryId())
            ? null
            : categoryRepository.findById(source.getParentCategoryId()).orElse(null)
    );
  }
}