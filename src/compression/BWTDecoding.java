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
		compressedFileStringArray = new String[stringArraySize];
		tempSortingArray = new String[stringArraySize];
		stringIndex = 0;
		compressedString = compressedFileString;
	}
	
	public String decode(){
		for(int i = 0; i < stringArraySize; i++){
			compressedFileStringArray[i] = String.valueOf(compressedString.charAt(i));	
		}

		for(int j = 0; j < stringArraySize-1; j++){
			System.arraycopy(compressedFileStringArray, 0, tempSortingArray, 0, stringArraySize);
			Arrays.sort(tempSortingArray);
			for(int k = 0; k < stringArraySize; k++){
				compressedFileStringArray[k] += tempSortingArray[k].charAt(tempSortingArray[k].length() - 1);
			}
		}
		
		while(compressedFileStringArray[stringIndex].charAt(compressedFileStringArray[stringIndex].length() - 1) != '|'){
			stringIndex++;
		}
		
		//System.out.println("\n" + compressedFileStringArray[stringIndex] + "\n");
		return compressedFileStringArray[stringIndex];
	}
}
