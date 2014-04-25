package compression.util;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class to store static methods for writing to files.
 * @author Eric Dilmore (geppettodivacin)
 */
public class FileWriter
{
    private FileWriter()
    {
        // Do nothing
    }

    /**
     * Write the output from a byte array to a file.
     * @param filename      the filename of a file
     * @param output        the byte array to output to the file
     */
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
