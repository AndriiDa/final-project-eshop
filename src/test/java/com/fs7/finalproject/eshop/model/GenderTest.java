package com.fs7.finalproject.eshop.model;

import org.junit.Assert;
import org.junit.Test;

public class GenderTest {

    @Test
    public void getShortNameEqualsMWhenGenderIsMale() {
        Gender gender = Gender.MALE;
        Assert.assertEquals("M", gender.getShortName());
    }

    @Test
    public void getShortNameEqualsFWhenGenderIsFemale() {
        Gender gender = Gender.FEMALE;
        Assert.assertEquals("F", gender.getShortName());
    }

}