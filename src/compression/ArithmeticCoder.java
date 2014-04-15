package compression;

import java.math.BigInteger;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Map;

import compression.util.arithmetic.CharMap;

class ArithmeticCoder implements Coder
{
    @Override
    public byte [] encode ( String message )
    {
        BigInteger lowValue = BigInteger.valueOf ( 0 );
        BigInteger highValue = BigInteger.valueOf ( 0 );
        BigInteger length = BigInteger.valueOf ( message.length() );
        BigInteger totalProduct = BigInteger.valueOf ( 1 );
        CharMap key = makeKey ( message );

        for ( Character c : message.toCharArray() )
        {
            lowValue = lowValue.add (
                totalProduct.multiply (
                    BigInteger.valueOf ( key.get ( c ) )
                )
            );
            lowValue = lowValue.multiply ( length );
            totalProduct = totalProduct.multiply (
                BigInteger.valueOf ( key.getProbability ( c ) )
            );
        }
        lowValue = lowValue.divide ( length );

        highValue = lowValue.add ( totalProduct );

        ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream ( outputBytes );
        try
        {
            output.writeInt ( key.size() );
            for ( Map.Entry<Character, Integer> e : key.entrySet() )
            {
                output.writeChar ( e.getKey() );
                output.writeInt ( e.getValue() );
            }
            output.write ( lowValue.toByteArray() );
        } catch ( java.io.IOException e )
        {
            System.err.println ( "There was an IOException." );
        }

        return outputBytes.toByteArray();
    }

    @Override
    public String decode ( byte [] codedMessage )
    {
        ByteArrayInputStream inputBytes = new ByteArrayInputStream (
            codedMessage
        );
        DataInputStream input = new DataInputStream ( inputBytes );

        BigInteger code;
        BigInteger power;
        CharMap key = new CharMap();

        try
        {
            int keyLength = input.readInt();
            for ( int i = 0; i < keyLength; ++i )
            {
                key.put ( input.readChar(), input.readInt() );
            }

            byte[] valueStorage = new byte[ inputBytes.available() ];
            input.readFully ( valueStorage );
            code = new BigInteger ( valueStorage );
        } catch ( java.io.IOException e )
        {
            System.err.println ( "There was an IOException" );
        }

        int messageLength = 0;
        for ( Integer j : key.values() )
        {
            messageLength += j;
        }

        power = BigInteger.valueOf ( messageLength ).pow ( messageLength - 1 );

        return "";
    }

    CharMap makeKey ( String message )
    {
        CharMap key = new CharMap();
        for ( Character c : message.toCharArray() )
        {
            key.addTo ( c );
        }
        return key;
    }
}
