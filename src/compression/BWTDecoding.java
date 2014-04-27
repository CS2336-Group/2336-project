package compression;

import java.util.Arrays;

/**
*	Decodes String input from String encoded by Burrows-Wheeler Transform
*	@author		Ian Brown
*/
public class BWTDecoding{
	private int stringArraySize;
	private String[] compressedFileStringArray;
	
	//Temporary copy of compressedFileStringArray in order to sort compressedFileStringArray without changing it
	private String[] tempSortingArray;

	private int stringIndex;
	private String compressedString;

	/**
	*	Initializes the important data members
	*	@param compressedFileString	String that has been compressed by Burrows-Wheeler Transform
	*/	
	public BWTDecoding(String compressedFileString){
		stringArraySize = compressedFileString.length();
		compressedFileStringArray = new String[stringArraySize];
		tempSortingArray = new String[stringArraySize];
		stringIndex = 0;
		compressedString = compressedFileString;
	}
	
	/**
	*	Sorts copy of compressedFileStringArray, adds it to compressedFileStringArray, and finally returns the final String that contains the end-of-String character(|)
	*	@return		Original String from file
	*/
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
