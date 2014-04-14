package compression;

import java.math.BigInteger;

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

        return new byte[1];
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
