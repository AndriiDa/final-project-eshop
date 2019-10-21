package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Property;
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
public class PropertyRepositoryTest {

  @Autowired
  private PropertyRepository propertyRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void injectedComponentsAreNotNull() {
    assertThat(entityManager).isNotNull();
    assertThat(propertyRepository).isNotNull();
  }

  @Test
  public void checkSaveProperty() {
    Property property = new Property();
    property.setName("test property");
    property = entityManager.persistAndFlush(property);
    assertThat(propertyRepository.findById(property.getId()).get()).isEqualTo(property);
  }

  @Test
  public void whenFindByName_thenReturnProperty() {
    // given
    Property property = new Property();
    property.setName("property1");
    property = entityManager.persistAndFlush(property);
    // when
    Optional<Property> findExOptional = propertyRepository.findByName("property1");
    Optional<Property> findNoneExOptional = propertyRepository.findByName("property2");
    Property findProperty = findExOptional.get();
    // then
    assertThat(findExOptional.isPresent()).isEqualTo(true);
    assertThat(findNoneExOptional.isPresent()).isEqualTo(false);
    assertThat(findProperty.getName()).isEqualTo("property1");
  }
}
