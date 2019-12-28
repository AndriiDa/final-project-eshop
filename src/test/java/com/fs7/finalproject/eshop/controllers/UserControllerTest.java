package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.UserDto;
import com.fs7.finalproject.eshop.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  UserService userService;

  //ObjectMapper mapper = new ObjectMapper();

  @Test
  @WithMockUser(username = "ivanov", password = "ivanov123", roles = "A")
  public void findAll() throws Exception {
    // given
    UserDto user = UserDto.builder()
            .id(1L)
            .firstName("Petya")
            .lastName("Ivanov")
            .loginName("p.ivanov")
            .email("p_ivanov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(Address.builder().id(1L).addressLine("Ivanov address").build())
            .build();

    List<UserDto> list = Arrays.asList(user);
    PageRequest pageable = PageRequest.of(0, 1);

    Page<UserDto> page = new PageImpl<>(list, pageable, list.size());
    given(userService.findAll(Mockito.any(PageRequest.class))).willReturn(page);

    //when + then
    this.mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(1)));

    this.mockMvc.perform(get("/api/v1/users"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"content\":[{\"id\":1,\"firstName\":\"Petya\",\"lastName\":\"Ivanov\",\"middleName\":null,\"email\":\"p_ivanov@gmail.com\",\"loginName\":\"p.ivanov\",\"loginPassword\":null,\"phoneNumber\":null,\"gender\":\"MALE\",\"birthDate\":null,\"address\":{\"id\":1,\"country\":null,\"state\":null,\"city\":null,\"street\":null,\"zipCode\":null,\"addressLine\":\"Ivanov address\"},\"emailVerified\":null,\"verificationCode\":null,\"role\":\"ADMIN\",\"isActive\":null,\"crTime\":null}],\"pageable\":{\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"pageSize\":1,\"pageNumber\":0,\"offset\":0,\"unpaged\":false,\"paged\":true},\"totalPages\":1,\"totalElements\":1,\"last\":true,\"first\":true,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"numberOfElements\":1,\"size\":1,\"number\":0,\"empty\":false}"));
  }
}