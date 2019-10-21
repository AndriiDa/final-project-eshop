package com.fs7.finalproject.eshop.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RoleTest {
  public Role role;

  @Before
  public void setUp() throws Exception {
    // given
    role = Role.ADMIN;
  }

  @Test
  public void checkExecGetShortName() {
    //when
    //then
    assertEquals(Role.valueOf("ADMIN"), role);
    assertNotEquals(Role.valueOf("CUSTOMER"), role);
  }

  @Test
  public void checkExecFromShortName() {
    //when
    //then
    assertEquals(Role.fromShortName("A"), role);
    assertNotEquals(Role.fromShortName("C"), role);
  }
}