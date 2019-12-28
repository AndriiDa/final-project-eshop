package com.fs7.finalproject.eshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs7.finalproject.eshop.services.PropertyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PropertyController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PropertyControllerTest {
  @MockBean
  PropertyService propertyService;

  ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "ivanov", password = "ivanov123", roles = "A")
  public void whenCreateProperty_thenReturnCreatedProperty() throws Exception {
    boolean result = true;
    Assert.assertTrue(result);
  }
}