package compression;

import java.io.*;

public class RLEncoding implements Coder{
	public byte[] encode(String message){
		int msgLength = message.length();
		int msgIndex = 1;
		int charCount = 1;
		char prevChar = message.charAt(0);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream byteCompressed = new DataOutputStream(byteArrayOutputStream);

		try{
			while(msgIndex < msgLength){
				if(message.charAt(msgIndex) != prevChar){
					byteCompressed.writeInt(charCount);
				
					byteCompressed.writeChar(prevChar);
					prevChar = message.charAt(msgIndex);
				
					charCount = 0;
				}
			
				msgIndex++;
				charCount++;
			}

			byteCompressed.writeInt(charCount);
			byteCompressed.writeChar(message.charAt(msgIndex - 1));
		}
		catch(IOException ex){
			System.out.println("Exception occurred.");
		}

		return byteArrayOutputStream.toByteArray();
	}

	public String decode(byte[] codedMessage) {
		String result = "";
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
			System.out.println("EOF Exception occurred.");
		}
		catch(IOException ioEx){
			System.out.println("IO Exception present.");
		}

		return result;
	}
}
