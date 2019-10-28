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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PropertyRepositoryTest {

  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private TestEntityManager entityManager;

  Property property;

  @Before
  public void setUp() throws Exception {
    property = Property.builder()
            .name("testProperty")
            .description("test description")
            .build();
    property = entityManager.persistAndFlush(property);
  }

  @Test
  public void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
    assertThat(propertyRepository).isNotNull();
  }

  @Test
  public void checkSaveProperty() {
    // given
    Property property = Property.builder()
            .name("property1")
            .description("description1")
            .build();
    // when
    property = entityManager.persistAndFlush(property);
    //then
    assertThat(propertyRepository.findById(property.getId()).get()).isEqualTo(property);
  }

  @Test
  public void whenFindByName_thenReturnProperty() {
    // given
    Property property = Property.builder()
            .name("property1")
            .build();
    entityManager.persistAndFlush(property);
    // when
    Optional<Property> findExOptional = propertyRepository.findByName("property1");
    Optional<Property> findNoneExOptional = propertyRepository.findByName("property2");
    Property findProperty = findExOptional.get();
    // then
    assertThat(findExOptional.isPresent()).isEqualTo(true);
    assertThat(findNoneExOptional.isPresent()).isEqualTo(false);
    assertThat(findProperty.getName()).isEqualTo("property1");
  }

  @Test
  public void testCascadesPersisting() {
    // given
    Set<PropertyValue> values = new HashSet<>();
    PropertyValue propertyValue = PropertyValue.builder()
            .name("propValue1_name")
            .description("propValue1_description")
            .property(property)
            .build();
//    PropertyValue propertyValueSave = entityManager.persistAndFlush(propertyValue);
    values.add(propertyValue);
    property.setValues(values);
    property = entityManager.persistAndFlush(property);
    // when
    // property = entityManager.persistAndGetId(property)
    Set<PropertyValue> propertyValues = entityManager.find(Property.class, property).getValues();
    //then
    assertThat(propertyValues.contains(propertyValue));
  }
}
