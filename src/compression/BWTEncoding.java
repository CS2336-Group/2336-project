package compression;

import java.util.Arrays;

/**
*	Encodes String input from a file for compression with run-length encoding
*	@author Ian Brown
*/

public class BWTEncoding{
	private String[] rotationsOfText;
	private String rotatingString;
	private String compressedString = "";
	private int stringArraySize;
	
	/**
	*	Initiates the important data members
	*	@param originalString	String from file
	*/
	public BWTEncoding(String originalString){
		//Adds an end-of-String character for easier decoding
		rotatingString = originalString+"|";
		stringArraySize = rotatingString.length();

		//Stores the rotations of the String
		rotationsOfText = new String[stringArraySize];
	}

	/**
	*	Runs the necessary functions to encode the String input
	*	@return		String input encoded using Burrows-Wheeler Transform
	*/
	public String encode(){
		textRotations();
		sortRotations();
		compressString();

		return compressedString;
	}
	
	/**
	*	Rotates and stores the rotations of the String input
	*	@return		void
	*/
	private void textRotations(){
		for(int i = 0; i < stringArraySize; i++){
			rotatingString = rotatingString.charAt(stringArraySize - 1) + rotatingString.substring(0, stringArraySize - 1);
			rotationsOfText[i] = rotatingString;
		}
	}
	
	/**
	*	Sorts the rotations of the String input
	*	@return		void
	*/
	private void sortRotations(){
		Arrays.sort(rotationsOfText);
	}

	private void compressString(){
		for(int j = 0; j < rotationsOfText.length; j++){ 
			compressedString += rotationsOfText[j].charAt(stringArraySize - 1);
		}
	}
}
