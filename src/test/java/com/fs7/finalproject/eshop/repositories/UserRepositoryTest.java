package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestEntityManager entityManager;

  User user1, user2, user3;
  Long id;
  Role role;

  @Before
  public void setUp() throws Exception {
    log.info("Start test: enum UserRepositoryTest...");
    // given
    user1 = User.builder()
            .firstName("Petya")
            .lastName("Ivanov")
            .loginName("p.ivanov")
            .email("p_ivanov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(Address.builder().addressLine("Ivanov address").build())
            .build();
    user2 = User.builder()
            .firstName("Vasya")
            .lastName("Petrov")
            .loginName("v.ipetrov")
            .email("v_petrov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(Address.builder().addressLine("Petrov address").build())
            .build();
//    Thread.sleep(10);
    user3 = User.builder()
            .firstName("Dasha")
            .lastName("Smirnova")
            .loginName("d.smirnova")
            .email("d_smirnova@gmail.com")
            .gender(Gender.FEMALE)
            .role(Role.ADMIN)
            .address(Address.builder().addressLine("Smirnova address").build())
            .build();

    entityManager.persist(user1);
    entityManager.persist(user2);
    entityManager.persist(user3);

    Thread.sleep(1000);
  }

  @Test
  public void injectedComponentsAreNotNull() {
    log.info("Test: check exec injectedComponentsAreNotNull...");
//    assertThat(dataSource).isNotNull();
//    assertThat(jdbcTemplate).isNotNull();
    assertThat(entityManager).isNotNull();
    assertThat(userRepository).isNotNull();
  }

  @Test
  public void whenFindByEMail_thenReturnUser() {
    log.info("Test: check exec whenFindByEMail_thenReturnUser...");
    // when
    User user = userRepository.findByEmail("p_ivanov@gmail.com");
    log.info(user.toString());
    // then
    assertThat(user.getLastName()).isEqualTo("Ivanov");
  }

  @Test
  public void whenFindAll_thenReturnUserList() {
    log.info("Test: check exec whenFindAll_thenReturnUserList...");
    // when
    List<User> users = userRepository.findAll();
    log.info(users.toString());
    // then
    assertThat(users).hasSize(3);
  }

}