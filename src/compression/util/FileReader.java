package compression.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * A class to store static methods for reading files as both strings and as
 * binary.
 * @author Eric Dilmore (geppettodivacin)
 */
public class FileReader
{
    private FileReader()
    {
        // Do nothing
    }

    /**
     * Read the file in from the designated filepath.
     * @param path  the path of the file
     * @return      a clear message as a string
     */
    public static String readFile ( String path )
    {
        return readFile ( path, StandardCharsets.UTF_8 );
    }
    /**
     * Read the file in from the designated filepath with the designated
     * encoding.
     * @param path      the path of the file
     * @param encoding  the charset with which to encode the String
     * @return          a clear message as a string
     */
    public static String readFile ( String path, Charset encoding )
    {
        byte[] encoded;
        try
        {
            encoded = Files.readAllBytes ( Paths.get ( path ) );
        } catch ( java.io.IOException e )
        {
            return null;
        }
        return new String ( encoded, encoding );
    }

    /**
     * Read the file from the designated filepath as an array of bytes.
     * @param path  the path of the file
     * @return      an encoded message as a byte array
     */
    public static byte[] readBinary ( String path )
    {
        byte[] encoded;
        try
        {
            encoded = Files.readAllBytes ( Paths.get ( path ) );
        } catch ( java.io.IOException e )
        {
            return null;
        }
        return encoded;
    }
}
