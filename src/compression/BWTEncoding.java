package compression;

import java.util.Arrays;

public class BWTEncoding{
	private String[] rotationsOfText;
	public String rotatingString;
	private String compressedString = "";
	public int stringArraySize;

	public BWTEncoding(String originalString){
		rotatingString = originalString+"|";
		stringArraySize = rotatingString.length();
		rotationsOfText = new String[stringArraySize];
	}

	public String encode(){
		textRotations();
		sortRotations();
		compressString();

		return compressedString;
	}
	
	private void textRotations(){
		for(int i = 0; i < stringArraySize; i++){
			rotatingString = rotatingString.charAt(stringArraySize - 1) + rotatingString.substring(0, stringArraySize - 1);
			rotationsOfText[i] = rotatingString;
		}
	}
	
	private void sortRotations(){
		Arrays.sort(rotationsOfText);
	}

	private void compressString(){
		for(int j = 0; j < rotationsOfText.length; j++){
            System.out.println ( "Starting iteration " + j );
			compressedString += rotationsOfText[j].charAt(stringArraySize - 1);
            System.out.println ( "Ending iteration " + j );
		}
	}
}
