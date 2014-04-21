public class RLEncoding implements Coder{
	private String originalString = "";
	private String compressedString = "";

	public byte[] encode(String message){
		int msgLength = message.length();
		int msgIndex = 0;
		int charCount = 0;
		char prevChar = '';
		byte[] compressedBytesOfString = new byte[msgLength];

		while(msgIndex < msgLength){
			if(message[msgIndex] != prevChar){
				if(charCount > 1) compressedString += charCount;
				
				compressedString += message[msgIndex];
				prevChar = message[msgIndex];
				
				charCount = 0;
			}
			
			msgIndex++;
			charCount++;
		}

		compressedBytesOfString = compressedString.getBytes();

		return compressedBytesOfString;
	}

	public String decode(byte[] codedMessage){
		String result;

		for(int i = 0; i < codedMessage.length; ++i) {
			int count = 0;
			while(codedMessage[i] >= '0' && codedMessage[i] <= '9') {
				count = count * 10 + codedMessage[i] - '0';
				++i;
			}

			char current = codedMessage[i];

			for(int j = 0; j < count; ++j) {
				result += current;
			}
		}

		return result;
	}
}
