package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestEntityManager entityManager;

  private User userOne, userTwo, userThree;

  @Before
  public void setUp() throws Exception {
    // given
    userOne = User.builder()
            .firstName("Petya")
            .lastName("Ivanov")
            .loginName("p.ivanov")
            .email("p_ivanov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(Address.builder().addressLine("Ivanov address").build())
            .build();
    userTwo = User.builder()
            .firstName("Vasya")
            .lastName("Petrov")
            .loginName("v.ipetrov")
            .email("v_petrov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(Address.builder().addressLine("Petrov address").build())
            .build();
    userThree = User.builder()
            .firstName("Dasha")
            .lastName("Smirnova")
            .loginName("d.smirnova")
            .email("d_smirnova@gmail.com")
            .gender(Gender.FEMALE)
            .role(Role.ADMIN)
            .address(Address.builder().addressLine("Smirnova address").build())
            .build();
  }

  @Test
  public void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
    assertThat(userRepository).isNotNull();
  }

  @Test
  public void checkSaveProperty() {
    // when
    User user = entityManager.persistAndFlush(userOne);
    // then
    assertThat(userRepository.findById(user.getId()).get()).isEqualTo(user);
  }

  @Test
  public void whenFindByEMail_thenReturnUser() {
    entityManager.persist(userOne);
    // when
    User user = userRepository.findByEmail("p_ivanov@gmail.com");
    // then
    assertThat(user.getLastName()).isEqualTo("Ivanov");
  }

  @Test
  public void whenFindAll_thenReturnUserList() {
    entityManager.persistAndFlush(userOne);
    entityManager.persistAndFlush(userTwo);
    entityManager.persistAndFlush(userThree);
    // when
    List<User> users = userRepository.findAll();
    // then
    assertThat(users).hasSize(3);
  }
}