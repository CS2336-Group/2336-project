package compression;

import java.math.BigInteger;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

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
        try
        {
            ObjectOutputStream output = new ObjectOutputStream (
                new ByteArrayOutputStream()
            );

            output.writeObject ( key );
            output.writeInt ( length.intValue() );
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
        // Do nothing
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
