package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.model.dto.BrandDto;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class BrandMapper extends AbstractMapper<Brand, BrandDto> {

  private UserRepository userRepository;

  private ModelMapper mapper;

  @Autowired
  public BrandMapper(ModelMapper mapper, UserRepository userRepository) {
    super(Brand.class, BrandDto.class);
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  private Long getCrUserId(Brand source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCrUser().getId();
  }

  private Long getLmUserId(Brand source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLmUser().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Brand.class, BrandDto.class)
        .addMappings(m -> m.skip(BrandDto::setCrUserId))
        .addMappings(m -> m.skip(BrandDto::setLmUserId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(BrandDto.class, Brand.class)
        .addMappings(m -> m.skip(Brand::setCrUser))
        .addMappings(m -> m.skip(Brand::setLmUser))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Brand source, BrandDto destination) {
    destination.setCrUserId(getCrUserId(source));
    destination.setLmUserId(getLmUserId(source));
  }

  @Override
  void mapSpecificFields(BrandDto source, Brand destination) {
    destination.setCrUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setLmUser(userRepository.findById(source.getLmUserId()).orElse(null));
  }
}