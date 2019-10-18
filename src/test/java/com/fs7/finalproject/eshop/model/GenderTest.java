package com.fs7.finalproject.eshop.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GenderTest {
  public Gender gender;

  @Before
  public void setUp() throws Exception {
    // given
    gender = Gender.MALE;
  }

  @Test
  public void returnsShortNameMWhenGenderIsMale() {
    gender = Gender.MALE;
    Assert.assertEquals("M", gender.getShortName());
  }

  @Test
  public void returnsShortNameFWhenGenderIsFemale() {
    gender = Gender.FEMALE;
    Assert.assertEquals("F", gender.getShortName());
  }

  @Test
  public void checkExecGetShortName() {
    //when
    //then
    assertEquals(Gender.valueOf("MALE"), gender);
    assertNotEquals(Gender.valueOf("FEMALE"), gender);
  }

  @Test
  public void checkExecFromShortName() {
    //when
    //then
    assertEquals(Gender.fromShortName("M"), gender);
    assertNotEquals(Gender.fromShortName("F"), gender);
  }
}