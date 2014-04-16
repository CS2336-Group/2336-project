import java.util.Arrays;

public class BWTransformation{
	private String[] rotationsOfText;
	private String rotatingString;
	private String compressedString;
	private int stringArraySize;

	public BWTransform(String originalString){
		rotatingString = originalString+"|";
		stringArraySize = originalString.length();
		rotationsOfText = new String[stringArraySize];
	}

	public String encode(){
		textRotations();
		sortRotations();
		compressString();

		return compressedString;
	}
	
	private void textRotations(){
		for(i = 0; i < (originalString.length); i++){
			rotatingString = rotatingString.charAt(stringArraySize -1) + rotatingString.substring(0, stringArraySize - 2);
			rotationsOfText[i] = rotatingString;
		}
	}
	
	private void sortRotations(){
		Arrays.sort(rotationsOfText);
	}

	private void compressString(){
		for(int j = 0; j < (rotationsOfText.length)-1; j++){
			compressedString += rotationsOfText[j].charAt(stringArraySize);
		}
	}

	public String decode(String compressedFileString){
		String[] compressedFileStringArray;
		String[] tempSortingArray;
		int stringIndex = 0;

		for(int i = 0; i < compressedFileString.length(); i++){
			compressedFileStringArray[i] = compressedFileString.charAt(i);
		}

		for(int j = 0; j < compressedFileString.length(); j++){
			tempSortingArray = compressedFileStringArray;
			Arrays.sort(tempSortingArray);
			for(int k = 0; k < compressedFileString.length(); k++){
				compressedFileStringArray[k] = compressedFileStringArray[k] + tempSortingArray.charAt(stringArraySize-1);
			}
		}
		
		while(compressedFileStringArray[stringIndex].charAt(stringArraySize - 1) != '|'){
			stringIndex++;
		}
		
		return compressedFileStringArray[stringIndex];
	}
}
