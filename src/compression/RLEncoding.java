package compression;

import java.io.*;

public class RLEncoding implements Coder{
	public byte[] encode(String message){
		int msgLength = message.length();
		BWTEncoding bwte = new BWTEncoding(message);
		String bwtMessage = bwte.encode();
		int msgIndex = 1;
		int charCount = 1;
		char prevChar = message.charAt(0);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream byteCompressed = new DataOutputStream(byteArrayOutputStream);

		try{
			while(msgIndex < msgLength){
				if(bwtMessage.charAt(msgIndex) != prevChar){
					byteCompressed.writeInt(charCount);
					//System.out.print(charCount);
					//System.out.println(prevChar);
					byteCompressed.writeChar(prevChar);
					prevChar = bwtMessage.charAt(msgIndex);
				
					charCount = 0;
				}
			
				msgIndex++;
				charCount++;
			}

			byteCompressed.writeInt(charCount+1);
			//System.out.print(charCount);
			//System.out.println(message.charAt(msgIndex - 1));
			byteCompressed.writeChar(message.charAt(msgIndex - 1));
		}
		catch(IOException ex){
			System.out.println("Exception occurred.");
		}
		
		//System.out.println(bwtMessage);

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
					//System.out.println(result);
				}
			}
		}
		catch(EOFException ex){
		}
		catch(IOException ioEx){
			System.out.println("IO Exception present.");
		}
		
		//System.out.println("\n" + result + "\n");

		bwtd = new BWTDecoding(result);
		
		return bwtd.decode();
	}
}
