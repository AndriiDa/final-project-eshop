package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.mapper.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.fs7.finalproject.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

//  @Autowired
//  private CategoryMapper mapper;

  @Test
  public void returnsAllCategories() throws Exception {
    // given
    Category category1 = Category.builder()
        .id(25L)
        .code("code1")
        .name("Category1")
        .description("Category1 Description")
        .isActive(true)
        .isGroup(true)
        .imgUrl("Image URL 1")
        .crUser(
            User.builder()
                .id(101L)
                .lastName("Ivanov")
                .loginName("p.ivanov")
                .email("p_ivanov@gmail.com")
                .gender(Gender.MALE)
                .role(Role.ADMIN)
                .address(Address.builder().id(1001L).addressLine("Ivanov address").build())
                .build())
        .build();

    Category category2 = Category.builder()
        .id(26L)
        .code("code2")
        .name("Category2")
        .description("Category2 Description")
        .isActive(true)
        .isGroup(true)
        .imgUrl("Image URL 2")
        .crUser(
            User.builder()
                .id(102L)
                .lastName("Petrov")
                .loginName("i.sidorov")
                .email("p_petrov@gmail.com")
                .gender(Gender.FEMALE)
                .role(Role.ADMIN)
                .address(Address.builder().id(10002L).addressLine("Petrov  address").build())
                .build())
        .build();

//    List<Category> list = Arrays.asList(category1, category2);
//    given(categoryService.findAll(new HashMap<>())
//        .stream()
//        .map(item->(mapper.toEntity(item)))
//        .collect(Collectors.toList())
//    ).willReturn(list);

    //when + then
    this.mockMvc.perform(get("/api/v1/categories"))
        .andExpect(status().isOk());
//        .andExpect(content().json("[" +
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