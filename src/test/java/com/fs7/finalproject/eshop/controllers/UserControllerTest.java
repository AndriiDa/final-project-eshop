package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.services.UserServiceImpl;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  UserServiceImpl userService;

//  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void findAll() throws Exception {
    // given
    User user = User.builder()
            .id(1L)
            .firstName("Petya")
            .lastName("Ivanov")
            .loginName("p.ivanov")
            .email("p_ivanov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(Address.builder().id(1L).addressLine("Ivanov address").build())
            .build();

    List<User> users = Arrays.asList(user);
    given(userService.findAll()).willReturn(users);

    //when + then
    this.mockMvc.perform(get("/api/v1/users"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{'id':1,'firstName':'Petya','lastName':'Ivanov','middleName':null,'email':'p_ivanov@gmail.com','loginName':'p.ivanov','loginPassword':null,'phoneNumber':null,'gender':'MALE','birthDate':null,'address':{'id':1,'addressLine':'Ivanov address'},'emailVerified':false,'verificationCode':null,'role':'ADMIN','active':false}]"));
  }
}