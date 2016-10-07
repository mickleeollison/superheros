package superheroTeams.validation;

import org.springframework.stereotype.Service;

@Service
public class Validation {
	
	public final String NAME_REGEX = "[a-zA-Z0-9_ -]{3,30}";
	public final String NUMBER_REGEX = "[0-9]{1,10}";
	public final String ALLWHITE_REGEX = "^\\s*$"; 
	public final String IMAGE_REGEX = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP)$";

	public boolean validateRegex(String str, String regex){
		boolean matches = str.matches(regex);
		return matches;
	}
	
}
