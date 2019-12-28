package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.mapper.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fs7.finalproject.eshop.services.CategoryService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@MockBean(JpaMetamodelMappingContext.class)
@EnableSpringDataWebSupport
public class CategoryControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  CategoryService categoryService;

  @MockBean
  private CategoryMapper mapper;

  @Test
  @WithMockUser(username = "ivanov", password = "ivanov123", roles = "A")
  public void returnsAllCategories() throws Exception {
    // given
    CategoryDto category1 = CategoryDto.builder()
        .id(25L)
        .code("code1")
        .name("Category1")
        .description("Category1 Description")
        .isActive(true)
        .isGroup(true)
        .imgUrl("Image URL 1")
        .crUserId(1L)
        .build();

    CategoryDto category2 = CategoryDto.builder()
        .id(26L)
        .code("code2")
        .name("Category2")
        .description("Category2 Description")
        .isActive(true)
        .isGroup(true)
        .imgUrl("Image URL 2")
        .crUserId(1L)
        .build();

    List<CategoryDto> list = Arrays.asList(category1, category2);
    PageRequest pageable = PageRequest.of(0, 2);

    Page<CategoryDto> page = new PageImpl<>(list, pageable, list.size());
    given(categoryService.findAll(Mockito.any(PageRequest.class))).willReturn(page);

    //when + then
    this.mockMvc.perform(get("/api/v1/categories"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)));
//        .andExpect(content()..json("[" +
//            "{'id':25," +
//            "'code':'code1'," +
//            "'name':'Category1'," +
//            "'imgUrl':'Image URL 1'," +
//            "'description':'Category1 Description'," +
//            "'crUser':" +
//            "{'id':101," +
//            "'firstName':null," +
//            "'lastName':'Ivanov'," +
//            "'middleName':null," +
//            "'email':'p_ivanov@gmail.com'," +
//            "'loginName':'p.ivanov'," +
//            "'loginPassword':null," +
//            "'phoneNumber':null," +
//            "'gender':'MALE'," +
//            "'birthDate':null," +
//            "'address':" +
//            "{'id':1001,'country':null,'state':null,'city':null,'street':null,'zipCode':null,'addressLine':'Ivanov address'}," +
//            "'emailVerified':false," +
//            "'verificationCode':null," +
//            "'role':'ADMIN'," +
//            "'crTime':null," +
//            "'active':false}," +
//            "'lmTime':null," +
//            "'lmUser':null," +
//            "'isActive':true," +
//            "'isGroup':true}," +
//            "{'id':26," +
//            "'code':'code2'," +
//            "'name':'Category2'," +
//            "'imgUrl':'Image URL 2'," +
//            "'description':'Category2 Description'," +
//            "'crTime':null," +
//            "'crUser':" +
//            "{'id':102," +
//            "'firstName':null," +
//            "'lastName':'Petrov'," +
//            "'middleName':null," +
//            "'email':'p_petrov@gmail.com'," +
//            "'loginName':'i.sidorov'," +
//            "'loginPassword':null," +
//            "'phoneNumber':null," +
//            "'gender':'FEMALE'," +
//            "'birthDate':null," +
//            "'address':" +
//            "{'id':10002,'country':null,'state':null,'city':null,'street':null,'zipCode':null,'addressLine':'Petrov  address'}," +
//            "'emailVerified':false," +
//            "'verificationCode':null," +
//            "'role':'ADMIN'," +
//            "'crTime':null," +
//            "'active':false}," +
//            "'lmTime':null," +
//            "'lmUser':null," +
//            "'isActive':true," +
//            "'isGroup':true}]"
//        ));
  }

  @Test
  public void findAll() {
  }

  @Test
  public void findCategoryById() {
  }

  @Test
  public void createCategory() {
  }

  @Test
  public void updateCategory() {
  }

  @Test
  public void deleteCategory() {
  }
}