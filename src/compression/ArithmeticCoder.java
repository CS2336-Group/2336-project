package compression;

import java.math.BigInteger;

class ArithmeticCoder implements Coder
{
    @Override
    public byte [] encode ( String message )
    {
        BigInteger lowValue = new BigInteger ( "1" );
        BigInteger highValue = new BigInteger ( "0" );
        return new byte[1];
    }

    @Override
    public String decode ( byte [] codedMessage )
    {
        // Do nothing
        return "";
    }
}
