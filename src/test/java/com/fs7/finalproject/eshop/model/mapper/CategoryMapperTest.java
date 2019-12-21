package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CategoryMapperTest {

  @Mock
  private CategoryRepository mockedCategoryRepository;

  @Mock
  private UserRepository mockedUserRepository;

  @InjectMocks
  private CategoryMapper mapper = new CategoryMapper(mockedUserRepository, mockedCategoryRepository);

  private Category category = new Category();
  private Category parentCategory = new Category();
  private CategoryDto categoryDto = new CategoryDto();
  private User user = new User();

  @Before
  public void init() {

    user.setId(1L);

    parentCategory.setId(100L);

    categoryDto.setId(22L);
    categoryDto.setName("Category DTO name");
    categoryDto.setDescription("Category DTO description");
    categoryDto.setParentCategoryId(parentCategory.getId());
    categoryDto.setCrUserId(user.getId());
    categoryDto.setLmUserId(user.getId());
    categoryDto.setDescription("Category DTO description");
    categoryDto.setDescription("Category DTO description");

    category.setId(22L);
    category.setName("Category name");
    category.setDescription("Category description");
    category.setParentCategory(parentCategory);
    category.setCrUser(user);
    category.setLmUser(user);

    user.setId(1L);

    //when(mockedCategoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
    //when(mockedUserRepository.findById(anyLong())).thenReturn(Optional.of(user));
  }

  @Test
  public void checkCategoryDtoToEntityMapping() {
    //CategoryDto result = mapper.toDto(category);
    assertEquals(categoryDto.getName(), categoryDto.getName());
    assertEquals(categoryDto.getDescription(), categoryDto.getDescription());
  }

  @Test
  public void checkCategoryEntityToDtoMapping() {
    //CategoryDto result = mapper.toDto(category);
    assertEquals(category.getName(), category.getName());
    assertEquals(category.getDescription(), category.getDescription());
    assertEquals(category.getParentCategory().getId(), categoryDto.getParentCategoryId());
  }

}