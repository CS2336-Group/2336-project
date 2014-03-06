package compression;

public interface Coder
{
    public byte [] encode ( String message );
    public String decode ( byte [] codedMessage );
}
