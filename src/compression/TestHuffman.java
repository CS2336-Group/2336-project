package compression;

public class TestHuffman {

	public static void main(String[] args) {
		HuffmanCode h=new HuffmanCode();
		byte[] s=h.encode("This is a test");
		h.decode(s);
	}
}
