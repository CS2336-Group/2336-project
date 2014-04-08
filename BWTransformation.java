import java.util.Arrays;

public class BWTransformation{
	private String[] rotationsOfText;
	private String rotatingString;
	private String compressedString;
	private int stringArraySize;

	public BWTransform(String originalString){
		rotatingString = originalString;
		stringArraySize = originalString.length();
		rotationsOfString = new String[stringArraySize];
	}

	public String encode(){
		textRotations();
		sortRotations();
		compressString();

		return compressedString;
	}
	
	private void textRotations(){
		for(i = 0; i < originalString.length(); i++){
			rotatingString = rotatingString.charAt(stringArraySize -1) + rotatingString.substring(0, stringArraySize - 2);
			rotationsOfString[i] = rotatingString;
		}
	}
	
	private void sortRotations(){
		rotationsOfText.sort();
	}

	private void compressString(){
		for(int j = 0; j < rotationsOfStrings.length()-1; j++){
			compressedString += rotationsOfStrings[j].charAt(stringArraySize);
		}
	}

	public String decode(){
		
}
