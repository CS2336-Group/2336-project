package compression;

import java.util.Arrays;

public class BWTDecoding{
	private int stringArraySize;
	private String[] compressedFileStringArray;
	private String[] tempSortingArray;
	private int stringIndex;
	private String compressedString;
	
	public BWTDecoding(String compressedFileString){
		stringArraySize = compressedFileString.length();
		System.out.println(stringArraySize);
		compressedFileStringArray = new String[stringArraySize];
		stringIndex = 0;
		compressedString = compressedFileString;
	}
	
	public String decode(){
		for(int i = 0; i < stringArraySize; i++){
			compressedFileStringArray[i] = String.valueOf(compressedString.charAt(i));
		}

		for(int j = 0; j < stringArraySize; j++){
			tempSortingArray = compressedFileStringArray;
			Arrays.sort(tempSortingArray);
			for(int k = 0; k < stringArraySize-1; k++){
				compressedFileStringArray[k] += compressedFileStringArray[k] + tempSortingArray[k].charAt(tempSortingArray.length - 1);
			}
		}
		
		while(compressedFileStringArray[stringIndex].charAt(stringArraySize - 1) != '|'){
			stringIndex++;
		}
		
		return compressedFileStringArray[stringIndex];
	}
}
