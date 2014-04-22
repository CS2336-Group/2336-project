package compression.util;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileWriter
{
    private FileWriter()
    {
        // Do nothing
    }

    public static void writeToFile ( String filename, byte[] output )
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream ( filename );
            outputStream.write ( output );
        } catch ( FileNotFoundException e )
        {
            System.err.println ( "Can't open file " + filename + " for output." );
            return;
        } catch ( IOException e )
        {
            System.err.println ( "Failed to write output to file." );
        }
    }
}
