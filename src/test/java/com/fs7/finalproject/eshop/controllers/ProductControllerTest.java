package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductService productService;

  private ModelMapper modelMapper = new ModelMapper();

  //ObjectMapper mapper = new ObjectMapper();

  @Test
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
    given(productService.findAll()
        .stream()
        .map(item -> (modelMapper.map(item, Product.class))).collect(Collectors.toList())
    ).willReturn(products);

    //when + then
    this.mockMvc.perform(get("/api/v1/products"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{'id':1,'category': {'id':2, 'code':'Product Code','name':'Product Category'},'title':'Product Title','description':'Product Description'}]"));
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
