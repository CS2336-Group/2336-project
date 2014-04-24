package compression;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.math.BigInteger;

public class HuffmanCode implements Coder {
    public static PriorityQueue<Node> q;
    public static HashMap<Character, String> toCode;
    public static HashMap<String, Character> toChar;

    HuffmanCode(){

    }

    // This method builds the tree based on the frequency of every characters
    public  Node huffman(int n) {
        Node x, y;
        for(int i = 1; i <= n-1; i++) {
            Node z = new Node();
            z.left = x = q.poll();
            z.right = y = q.poll();
            z.freq = x.freq + y.freq;
            q.add(z);
        }

        return q.poll();
    }

    // build table for the compression and decompression
    public  void buildTable(Node root) {
        toCode = new HashMap<Character, String>();
        toChar = new HashMap<String, Character>();

        postorder(root, new String());
    }

    //  traverse from ROOT-to-LEAVES
    public  void postorder(Node n, String s) {
        if(n == null)
            return;

        postorder(n.left, s+"0");
        postorder(n.right, s+"1");

        // Visit only nodes with keys
        if(n.kar != '\0') {
            System.out.println("\'" + n.kar + "\' -> " + s);
            toCode.put(n.kar, s);
            toChar.put(s, n.kar);
        }
    }

    // This method assumes that the tree and dictionary are already built
    public  String compress(String s) {
        String c = new String();

        for(int i = 0; i < s.length(); i++)
            c = c + toCode.get(s.charAt(i));

        return c;
    }

    //decompression,assuming compression is already done
    public  String decompress(String s) {
        String temp = new String();
        String result = new String();

        for(int i = 0; i < s.length(); i++) {
            temp = temp + s.charAt(i);

            if(toChar.containsKey(temp)) {
                result = result + toChar.get(temp);
                temp = new String();
            }
        }

        return result;
    }
    public  void saveToFile(String s) throws FileNotFoundException {
        PrintWriter oFile = new PrintWriter("file.txt");

        for(String x : toChar.keySet())
            oFile.println(s + "->" + toChar.get(x));
        oFile.println(s);
        oFile.close();
    }
    //encode method
    @Override
    public byte[] encode(String text) {
        HashMap<Character, Integer> d = new HashMap<Character, Integer>();
        for(int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            if(d.containsKey(a))
                d.put(a, d.get(a)+1);
            else
                d.put(a, 1);
        }
        //  adding all the nodes to a priority queue to create group of trees
        q = new PriorityQueue<Node>(100, new FrequencyComparator());
        int n = 0;
        for(Character c : d.keySet()) {
            q.add(new Node(c, d.get(c)));
            n++;
        }

        // Identify the root of the tree
        Node root = huffman(n);

        // Build the table for the variable length codes
        buildTable(root);

        String compressed = compress(text);
        BigInteger compressedBytes = new BigInteger(compressed, 2);

        ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
        DataOutputStream output = new  DataOutputStream ( outputBytes );

        System.out.println("The compressed used a total of " + compressed.length() + " bits");
        System.out.println("code message=\t"+compressed);
        return compressedBytes.toByteArray();
    }
    //decode mothod
    @Override
    public String decode(byte[] codedMessage) {

        String decompressed = "";

        ByteArrayInputStream inputBytes = new ByteArrayInputStream ( codedMessage );
        DataInputStream input = new DataInputStream ( inputBytes );

        BigInteger messageNum = new BigInteger ( codedMessage );

        decompressed = decompress(messageNum.toString(2));

        System.out.println("The original text used a total of " + decompressed.length() + " characters");
        System.out.println(decompressed);
        return decompressed;
    }

}

class Node {

    public char kar;
    public int freq;
    public Node left, right;

    public Node(char a, int f) {
        kar = a;
        freq = f;
    }

    public Node() {

    }

    public String toString() {
        return kar + " " + freq;
    }

}

class FrequencyComparator implements Comparator<Node> {

    public int compare(Node a, Node b) {
        int freqA = a.freq;
        int freqB = b.freq;

        return freqA-freqB;
    }


}
