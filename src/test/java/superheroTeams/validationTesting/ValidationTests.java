package superheroTeams.validationTesting;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import superheroTeams.validation.Validation;

public class ValidationTests {
	Validation valid;
	
	@Before
	public void setUp(){
		valid = new Validation();
	}
	@Test
	public void testValidateRegexHappyNumber(){
		assertTrue(valid.validateRegex("345", valid.NUMBER_REGEX));
	}
	@Test
	public void testValidateRegexSadNumber(){
		assertFalse(valid.validateRegex("12345678901", valid.NUMBER_REGEX));
	}
	@Test
	public void testValidateRegexHappyName(){
		assertTrue(valid.validateRegex("Name ", valid.NAME_REGEX));
	}
	@Test
	public void testValidateRegexSadName(){
		assertFalse(valid.validateRegex("Name %", valid.NUMBER_REGEX));
	}
	@Test
	public void testValidateRegexHappyWhite(){
		assertTrue(valid.validateRegex(" 	"
				+ "", valid.ALLWHITE_REGEX));
	}
	@Test
	public void testValidateRegexSadWhite(){
		assertFalse(valid.validateRegex("1", valid.ALLWHITE_REGEX));
	}
	@Test
	public void testValidateRegexHappyImage(){
		assertTrue(valid.validateRegex("KDFSKL.jpeg", valid.IMAGE_REGEX));
	}
	@Test
	public void testValidateRegexSadImage(){
		assertFalse(valid.validateRegex("ldflk.com", valid.IMAGE_REGEX));
	}

}
