package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PropertyRepositoryTest {

  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private TestEntityManager entityManager;

  private Property property1, property2, property3;

  @Before
  public void setUp() throws Exception {
    // given
    property1 = Property.builder()
        .name("property1")
        .description("Property1 Description")
        .propertyValues(new ArrayList<>())
        .build();
    property2 = Property.builder()
        .name("property2")
        .description("Property2 Description")
        .propertyValues(new ArrayList<>())
        .build();
    property3 = Property.builder()
        .name("property3")
        .description("Property3 Description")
        .propertyValues(new ArrayList<>())
        .build();
  }

  @Test
  public void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
    assertThat(propertyRepository).isNotNull();
  }

  @Test
  public void checkSaveProperty() {
//    // given
//
//    // when
//    Property property2 = entityManager.persistAndFlush(property1);
//    //then
//    assertThat(propertyRepository.findById(property2.getId()).get()).isEqualTo(property1);
  }

  @Test
  public void whenFindByName_thenReturnProperty() {
//    // given
//    entityManager.persistAndFlush(property2);
//    entityManager.persistAndFlush(property3);
//    // when
//    Optional<Property> findExOptional = propertyRepository.findPropertyByName("property2");
//    Optional<Property> findNoneExOptional = propertyRepository.findPropertyByName("property3");
//    Property findProperty = findExOptional.get();
//    // then
//    assertThat(findExOptional.isPresent()).isEqualTo(true);
//    assertThat(findNoneExOptional.isPresent()).isEqualTo(false);
//    assertThat(findProperty.getName()).isEqualTo("property2");
  }
}
