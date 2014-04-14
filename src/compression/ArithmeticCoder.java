package compression;

import java.math.BigInteger;

import compression.util.arithmetic.CharMap;

class ArithmeticCoder implements Coder
{
    @Override
    public byte [] encode ( String message )
    {
        BigInteger lowValue = new BigInteger ( "1" );
        BigInteger highValue = new BigInteger ( "0" );
        CharMap key = makeKey ( message );

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
