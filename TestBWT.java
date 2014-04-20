public class TestBWT{
	public static void main(String[] args){
		BWTEncoding str1 = new BWTEncoding("banana");
		
		System.out.println(str1.rotatingString);
		System.out.println(str1.stringArraySize);
		
		String encoded = "";
		encoded = str1.encode();
		
		System.out.println(encoded);
		
		BWTDecoding comStr1 = new BWTDecoding(encoded);
		String decoded = "";
		decoded = comStr1.decode();
		
		System.out.println(decoded);
	}
}
