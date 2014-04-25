package compression;

/**
 * A generic interface for encoders. Must be able to both encode and decode
 * what they have done. Should be able to do so generically, i.e. with any
 * string they are given.
 * @author Eric Dilmore (geppettodivacin)
 */
public interface Coder
{
    /**
     * Encodes a string using the implemented algorithm.
     * @param message   the unencoded message
     * @return          the encoded message as a byte array
     */
    public byte [] encode ( String message );
    /**
     * Decodes a byte array using the implemented algorithm.
     * @param codedMessage  the coded message as a byte array
     * @return              the clear message as a string
     */
    public String decode ( byte [] codedMessage );
}
