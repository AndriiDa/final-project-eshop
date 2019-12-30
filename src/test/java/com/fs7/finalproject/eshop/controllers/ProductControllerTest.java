package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.security.CustomUserDetailsService;
import com.fs7.finalproject.eshop.security.JwtAuthenticationEntryPoint;
import com.fs7.finalproject.eshop.security.JwtTokenProvider;
import com.fs7.finalproject.eshop.services.ProductPropertyValueService;
import com.fs7.finalproject.eshop.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
@EnableSpringDataWebSupport
public class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductService productService;

  @MockBean
  ProductPropertyValueService productPropertyValueService;

  @MockBean
  CustomUserDetailsService customUserDetailsService;

  @MockBean
  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @MockBean
  JwtTokenProvider jwtTokenProvider;

  //ObjectMapper mapper = new ObjectMapper();

  @Test
  @WithMockUser(username = "ivanov", password = "ivanov123", roles = "A")
  public void findAll() throws Exception {
    // given
    Product product = Product.builder()
        .id(1L)
        .title("Product Title")
        .description("Product Description")
        .category(
            Category.builder()
                .id(2L)
                .code("Product Code")
                .name("Product Category")
                .build())
        .build();

    List<Product> products = Arrays.asList(product);


//    Page<Product> emptyPage = new PageBuilder<Product>()
//        .elements(products)
//        .pageRequest(pageRequest)
//        .totalElements(products.size())
//        .build();
//
//    Page<Product> page = new PageImpl<>(products);

//    given(this.productService.findAll(PageRequest.of(0, 1))).willReturn(page);
    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
        .andExpect(status().isOk());

    //when + then
//    this.mockMvc.perform(get("/api/v1/products"))
//        .andExpect(status().isOk())
//        .andExpect(content().json("[{'id':1,'category': {'id':2, 'code':'Product Code','name':'Product Category'},'title':'Product Title','description':'Product Description'}]"));
  }

  @Test
  public void findAll1() {
  }

  @Test
  public void findById() {
  }

  @Test
  public void create() {
  }

  @Test
  public void update() {
  }

  @Test
  public void delete() {
  }
}
