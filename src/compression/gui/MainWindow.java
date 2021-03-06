package compression.gui;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import compression.*;
import compression.util.FileReader;
import compression.util.FileWriter;
import java.io.File;

/**
 * The main window of the GUI. It is also responsible for keeping track of the
 * user's actions.
 * @author Eric Dilmore (geppettodivacin)
 */
public class MainWindow extends JFrame
{
    JPanel panel;
    FilePanel filePanel;
    CompressionPanel compressionPanel;
    ButtonPanel buttonPanel;

    public MainWindow()
    {
        panel = new JPanel ( new BorderLayout() );
        filePanel = new FilePanel();
        compressionPanel = new CompressionPanel();
        buttonPanel = new ButtonPanel();

        panel.add ( filePanel, BorderLayout.NORTH );
        panel.add ( compressionPanel, BorderLayout.CENTER );
        panel.add ( buttonPanel, BorderLayout.SOUTH );
        add ( panel );

        setTitle ( "Compression 101" );
        setSize ( 450, 150 );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        setVisible ( true );
    }

    private class FilePanel extends JPanel
    {
        JLabel prompt;
        JTextField filenameField;
        public FilePanel()
        {
            setLayout ( new BoxLayout ( this, BoxLayout.X_AXIS ) );
            prompt = new JLabel ( "What file would you like to compress?" );

            filenameField = new JTextField( 100 );
            setPreferredSize ( new Dimension ( 300, 60 ) );
            filenameField.setMaximumSize ( new Dimension ( 500, 24 ) );

            add ( Box.createHorizontalGlue() );
            add ( prompt );
            add ( Box.createHorizontalGlue() );
            add ( filenameField );
            add ( Box.createHorizontalGlue() );
        }

        public String getFilename()
        {
            return filenameField.getText();
        }
    }
    private class CompressionPanel extends JPanel
    {
        JComboBox<String> compressionBox;
        HashMap<String, Coder> compressionMap;
        JCheckBox filterCheckbox;

        public CompressionPanel()
        {
            setLayout ( new BoxLayout ( this, BoxLayout.X_AXIS ) );
            setBorder ( BorderFactory.createEmptyBorder ( 0, 100, 0, 100 ) );

            compressionMap = new HashMap<String, Coder>();

            compressionMap.put ( "Arithmetic Coding", new ArithmeticCoder() );
            compressionMap.put ( "Huffman Coding", new HuffmanCode() );
            compressionMap.put ( "Run Length Encoding", new RLEncoding() );

            compressionBox = new JComboBox<String>();
            for ( String s : compressionMap.keySet() )
            {
                compressionBox.addItem ( s );
            }

            compressionBox.setMaximumSize ( new Dimension ( 500, 24 ) );
            compressionBox.setPreferredSize ( new Dimension ( 200, 24 ) );

            filterCheckbox = new JCheckBox ( "Filter textfile?", true );

            add ( Box.createHorizontalGlue() );
            add ( compressionBox );
            add ( Box.createHorizontalGlue() );
            add ( filterCheckbox );
            add ( Box.createHorizontalGlue() );
        }

        public Coder getCoder()
        {
            return compressionMap.get ( compressionBox.getSelectedItem() );
        }
        public String getCoderName()
        {
            return compressionBox.getSelectedItem().toString();
        }
    }
    private class ButtonPanel extends JPanel
    {
        JButton actionButton, quitButton;

        public ButtonPanel()
        {
            setLayout ( new BoxLayout ( this, BoxLayout.X_AXIS ) );

            actionButton = new JButton ( "Compress/Decompress" );
            quitButton = new JButton ( "Quit" );
            quitButton.setPreferredSize ( actionButton.getPreferredSize() );

            actionButton.addActionListener ( new CompressionPress() );
            quitButton.addActionListener ( new QuitPress() );
            
            add ( Box.createHorizontalGlue() );
            add ( actionButton );
            add ( Box.createHorizontalGlue() );
            add ( quitButton );
            add ( Box.createHorizontalGlue() );
        }
    }

    // What happens when the user presses the "Compress/Decompress" button.
    private class CompressionPress implements ActionListener
    {
        @Override
        public void actionPerformed ( ActionEvent e )
        {
            Thread processThread = new Thread ( new ProcessTask (
                compressionPanel.getCoder(),
                compressionPanel.getCoderName(),
                filePanel.getFilename(),
                ( Component ) e.getSource()
            ) );
            processThread.start();
        }
    }

    private class ProcessTask implements Runnable
    {
        private Coder coder;
        private String coderName;
        private String filename;
        private Component source;

        public ProcessTask ( Coder coder, String coderName, String filename, Component source )
        {
            this.coder = coder;
            this.coderName = coderName;
            this.filename = filename;
            this.source = source;
        }

        @Override
        public void run()
        {
            String compressedFilename = "";
            String textFilename = "";
            String outputFilename = "";
            byte[] output;

            // Check whether the file exists and is not a directory.
            File inputFile = new File ( filename );
            if ( !inputFile.exists() )
            {
                JOptionPane.showMessageDialog ( source, "No such file found." );
                return;
            } else if ( inputFile.isDirectory() )
            {
                JOptionPane.showMessageDialog ( source, "That file is a " +
                    "directory."
                );
                return;
            }

            long startTime, compressionTime;

            // Display a dialog box to ask the user what he wants to do.
            Object[] options = { "Compress", "Decompress", "Cancel" };
            int option = JOptionPane.showOptionDialog (
                source,
                "What do you want to do?",
                "Compress or Decompress?",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[ 0 ]
            );

            if ( options[ option ] == "Compress" )
            {
                // Name the new file.
                textFilename = filename;
                compressedFilename = textFilename.replaceFirst ( "\\.txt$", "" );
                compressedFilename =
                    compressedFilename.replaceFirst ( "$", ".emi" );
                outputFilename = compressedFilename;

                // Read the file.
                String message = FileReader.readFile ( textFilename );

                // Check for read errors.
                if ( message == null )
                {
                    JOptionPane.showMessageDialog ( source,
                        "There is no such file."
                    );
                    return;
                } else if ( message == "" )
                {
                    JOptionPane.showMessageDialog ( source,
                        "The file is empty."
                    );
                    return;
                }

                // Remove all invalid UTF-8 characters.
                message = message.replace ( Character.toString ( ( char ) 0xFFFD ), "" );

                // Filter the file if requested.
                if ( compressionPanel.filterCheckbox.isSelected() )
                {
                    message = message.replaceAll ( "[ \\t\\n]+", " " ).
                        replaceAll ( "[^0-9A-Za-z ]", "" ).toLowerCase();
                    textFilename = textFilename.replaceFirst ( "\\.txt$", ".filtered.txt" );
                    FileWriter.writeToFile ( textFilename, message.getBytes() );
                }

                // Start the timer.
                startTime = System.currentTimeMillis();

                // Encode the message.
                output = coder.encode ( message );

                // End the timer.
                compressionTime = System.currentTimeMillis() - startTime;
            } else if ( options[ option ] == "Decompress" )
            {
                // Name the new file
                compressedFilename = filename;
                textFilename = compressedFilename.replaceFirst ( "\\.emi$", "" );
                textFilename = textFilename.replaceFirst ( "$", ".txt" );
                outputFilename = textFilename;

                // Check whether the output file already exists.
                if ( new File ( outputFilename ).exists() )
                {
                    String[] choices = { "Modified", "Overwrite", "Cancel" };
                    int choice = JOptionPane.showOptionDialog (
                        source,
                        "The file you would output to already exists. Would you" +
                            "like to overwrite or write to a modified filename?",
                        "File Already Exists",
                        JOptionPane.WARNING_MESSAGE,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        choices,
                        choices[ 0 ]
                    );

                    switch ( choices[ choice ] )
                    {
                        case "Modified":
                            // Find a unique filename.
                            int i;
                            for (
                                i = 1;
                                new File ( outputFilename.replaceFirst (
                                    "\\.txt", "" + i + ".txt"
                                ) ).exists();
                                ++i )
                            {
                                // Do nothing. Wait for it to find a filename that
                                // doesn't exist.
                            }

                            // Set outputFilename to the new filename.
                            outputFilename = outputFilename.replaceFirst (
                                "\\.txt", "" + i + ".txt"
                            );
                            break;
                        case "Overwrite":
                            // Do nothing.
                            break;
                        case "Cancel":
                            return;
                        default:
                            System.err.println ( "I don't know how you got here." );
                            break;
                    }
                }

                // Read the file.
                byte[] codedMessage =
                    FileReader.readBinary ( compressedFilename );

                // If the reader couldn't read the file...
                if ( codedMessage == null )
                {
                    JOptionPane.showMessageDialog ( source,
                        "The file does not exist or is empty."
                    );
                    return;
                }

                // Time the compression.
                startTime = System.currentTimeMillis();

                // Decode the message.
                String outputString = coder.decode ( codedMessage );

                // Complete the timing.
                compressionTime = System.currentTimeMillis() - startTime;

                // Convert the output string to a byteArray for generic output.
                output = outputString.getBytes();
            } else // if ( options[ choice ] == "Cancel" )
            {
                return;
            }

            // Output the result to the output file.
            FileWriter.writeToFile ( outputFilename, output );

            // Make a window to display summary statistics.
            new SummaryWindow ( coderName, compressionTime,
                new File ( textFilename ), new File ( compressedFilename )
            );
        }
    }

    // What happens when the user presses the "Quit" button.
    private class QuitPress implements ActionListener
    {
        @Override
        public void actionPerformed ( ActionEvent e )
        {
            System.exit ( 0 );
        }
    }
}
