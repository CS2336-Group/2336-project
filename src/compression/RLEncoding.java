package compression;

import java.io.*;

/**
*	Compresses the String from file by condensing it to the combination of the count of consecutive alike characters and the character
*	@author		Ian Brown
*/
public class RLEncoding implements Coder{
	/**
	*	Encodes the String, message, using Burrows-Wheeler Transform and run-length encoding
	*	@param message		Original String from file
	*	@return			returns byte array of the compressed String
	*/
	public byte[] encode(String message){
		BWTEncoding bwte = new BWTEncoding(message);
		String bwtMessage = bwte.encode();
		int msgLength = bwtMessage.length();
		int msgIndex = 1;
		int charCount = 1;
		char prevChar = bwtMessage.charAt(0);
		
		//Allows writing of String into byte array format
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream byteCompressed = new DataOutputStream(byteArrayOutputStream);

		try{
			//Puts character counts and characters into bytes for putting them into a byte array
			while(msgIndex < msgLength){
				if(bwtMessage.charAt(msgIndex) != prevChar){
					byteCompressed.writeInt(charCount);

					byteCompressed.writeChar(prevChar);
					prevChar = bwtMessage.charAt(msgIndex);
				
					charCount = 0;
				}
			
				msgIndex++;
				charCount++;
			}

			byteCompressed.writeInt(charCount);
			byteCompressed.writeChar(bwtMessage.charAt(msgIndex - 1));
		}
		catch(IOException ex){
			System.out.println("Exception occurred.");
		}

		return byteArrayOutputStream.toByteArray();
	}

	/**
	*	Decodes byte array to original String using Burrows-Wheeler Transform and run-length encoding
	*	@param codedMessage		Compressed String in the form of a byte array
	*	@return				Returns the original String that was read from the file
	*/
	public String decode(byte[] codedMessage) {
		String result = "";
		BWTDecoding bwtd;
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(codedMessage);
		DataInputStream byteInputStream = new DataInputStream(byteArrayInputStream);

		try{
			//Loops through byte array and prints out the characters equal to the count of that character in the Burrows-Wheeler transformed String
			while(true) {
				int count = byteInputStream.readInt();
				char current = byteInputStream.readChar(); 

				for(int i = 0; i < count; i++) {
					result += current;
				}
			}
		}
		catch(EOFException ex){
		}
		catch(IOException ioEx){
			System.out.println("IO Exception present.");
		}
		
		bwtd = new BWTDecoding(result);
		
		//returns the decoded String using Burrows-Wheeler Transform
		return (bwtd.decode()).substring(0,(bwtd.decode()).length()-1);
	}
}
