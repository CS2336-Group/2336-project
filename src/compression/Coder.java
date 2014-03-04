package compression;

interface Coder
{
    byte [] encode ( String message );
    String decode ( byte [] codedMessage );
}
