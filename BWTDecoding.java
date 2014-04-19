public class BWTDecoding{
	private String[] compressedFileStringArray;
	private String[] tempSortingArray;
	int stringIndex;
	
	public BWTDecoding(String compressedFileString){
		compressedFileStringArray = new String[compressedFileString.length()];
		stringIndex = 0;
	}
	
	public String decode(String compressedFileString){
		for(int i = 0; i < compressedFileString.length(); i++){
			compressedFileStringArray[i] = String.valueOf(compressedFileString.charAt(i));
		}

		for(int j = 0; j < compressedFileString.length(); j++){
			tempSortingArray = compressedFileStringArray;
			Arrays.sort(tempSortingArray);
			for(int k = 0; k < compressedFileString.length(); k++){
				compressedFileStringArray[k] = compressedFileStringArray[k] + tempSortingArray[k].charAt(stringArraySize-1);
			}
		}
		
		while(compressedFileStringArray[stringIndex].charAt(stringArraySize - 1) != '|'){
			stringIndex++;
		}
		
		return compressedFileStringArray[stringIndex];
	}
}
