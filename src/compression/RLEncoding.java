package compression;

import java.io.*;

public class RLEncoding implements Coder{
	public byte[] encode(String message){
		BWTEncoding bwte = new BWTEncoding(message);
		String bwtMessage = bwte.encode();
		int msgLength = bwtMessage.length();
		int msgIndex = 1;
		int charCount = 1;
		char prevChar = bwtMessage.charAt(0);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream byteCompressed = new DataOutputStream(byteArrayOutputStream);

		try{
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

	public String decode(byte[] codedMessage) {
		String result = "";
		BWTDecoding bwtd;
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(codedMessage);
		DataInputStream byteInputStream = new DataInputStream(byteArrayInputStream);

		try{
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
		
		return (bwtd.decode()).substring(0,(bwtd.decode()).length()-1);
	}
}
