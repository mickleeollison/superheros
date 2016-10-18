package com.catalyst.collector.PageObjectFramework.Pages;

import static org.junit.Assert.*;

import org.junit.Test;

import superheroTeams.SeleniumFramework.TestPageObject;
import superheroTeams.SeleniumFramework.Pages.HeroPage;

public class HeroPageTest extends TestPageObject{
 
 @Test
 public void testHeroPage(){
	 String expected = "http://localhost:8080/hero";
	 HeroPage h = new HeroPage(driver);
	 String actual = h.getUrl();
	 assertEquals(expected, actual);
 }
 
}
