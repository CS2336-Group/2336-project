package compression.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileReader
{
    private FileReader()
    {
        // Do nothing
    }

    public static String readFile ( String path )
    {
        return readFile ( path, StandardCharsets.UTF_8 );
    }
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
