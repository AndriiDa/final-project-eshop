package com.fs7.finalproject.eshop.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RoleTest {
  public Role role;

  @Before
  public void setUp() throws Exception {
    log.info("Start test: enum RoleTest...");
    // given
    role = Role.ADMIN;
  }

  @Test
  public void checkExecGetShortName() {
    log.info("Test: check exec GetShortName...");
    //when
    //then
    assertEquals(Role.valueOf("ADMIN"), role);
    assertNotEquals(Role.valueOf("CUSTOMER"), role);
  }

  @Test
  public void checkExecFromShortName() {
    log.info("Test: check exec FromShortName...");
    //when
    //then
    assertEquals(Role.fromShortName("A"), role);
    assertNotEquals(Role.fromShortName("C"), role);
  }
}