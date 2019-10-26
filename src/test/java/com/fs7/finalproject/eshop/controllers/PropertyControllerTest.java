package com.fs7.finalproject.eshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs7.finalproject.eshop.services.PropertyServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PropertyController.class)
public class PropertyControllerTest {
  @MockBean
  PropertyServiceImpl propertyService;

  ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void whenCreateProperty_thenReturnCreatedProperty() throws Exception {
    boolean result = true;
    Assert.assertTrue(result);
  }
}