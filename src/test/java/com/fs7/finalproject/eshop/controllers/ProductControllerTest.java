package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ProductService productService;

//  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void findAll() throws Exception {
    // given
    Product product = new Product();
    product.setId(1L);
    product.setCategoryId(2L);
    product.setTitle("Product Title");
    product.setDescription("Product Description");

    List<Product> products = Arrays.asList(product);
    given(productService.findAll()).willReturn(products);

    //when + then
    this.mockMvc.perform(get("/api/v1/products"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{'id':1,'categoryId':2,'title':'Product Title','description':'Product Description'}]"));
  }
}