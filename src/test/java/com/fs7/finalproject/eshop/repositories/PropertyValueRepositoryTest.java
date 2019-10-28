package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.PropertyValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PropertyValueRepositoryTest {
  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private PropertyValueRepository propertyValueRepository;

  @Autowired
  private TestEntityManager entityManager;

  private Property property;

  @Before
  public void setUp() throws Exception {
    property = Property.builder()
            .name("testProperty")
            .build();
    property = entityManager.persistAndFlush(property);
  }

  @Test
  public void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
    assertThat(propertyRepository).isNotNull();
    assertThat(propertyValueRepository).isNotNull();
  }

  @Test
  public void checkSaveProperty() {
    // given
    PropertyValue propertyValue = PropertyValue.builder()
            .name("testPropertyValue1")
            .property(property)
            .build();
    //when
    propertyValue = entityManager.persistAndFlush(propertyValue);
    //then
    assertThat(propertyValueRepository.findById(propertyValue.getId()).get()).isEqualTo(propertyValue);
  }

  @Test
  public void whenFindByName_thenReturnProperty() {
    // given
    PropertyValue propertyValue = PropertyValue.builder()
            .name("testPropertyValue1")
            .description("descr_testPropertyValue1")
            .property(property)
            .build();
    entityManager.persistAndFlush(propertyValue);
    // when
    Optional<PropertyValue> findExOptional = propertyValueRepository.findByName("testPropertyValue1");
    Optional<PropertyValue> findNoneExOptional = propertyValueRepository.findByName("testPropertyValue0");
    PropertyValue findProperty = findExOptional.get();
    // then
    assertThat(findExOptional.isPresent()).isEqualTo(true);
    assertThat(findNoneExOptional.isPresent()).isEqualTo(false);
    assertThat(findProperty.getName()).isEqualTo("testPropertyValue1");
  }
}