package com.fs7.finalproject.eshop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EshopApplicationTest {

	@Test
	public void contextLoads() {
		int i = 1;
		Assert.assertEquals("isTrue", 1, i);
	}

}
