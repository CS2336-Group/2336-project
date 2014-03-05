package compression;

class ArithmeticCoder implements Coder
{
    @Override
    public byte [] encode ( String message )
    {
        // Do nothing
        return new byte[1];
    }

    @Override
    public String decode ( byte [] codedMessage )
    {
        // Do nothing
        return "";
    }
}
