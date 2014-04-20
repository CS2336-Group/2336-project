package compression;

public class TestHuffaman {

	public static void main(String[] args) {
		HuffmanCode h=new HuffmanCode("sinega hulum peace yihonal");
		byte[] s=h.encode();
		h.decode(s);
		
		
	}

}
