package compression.gui;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import compression.*;
import compression.util.FileReader;
import compression.util.FileWriter;
import java.io.File;

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

        public CompressionPanel()
        {
            setLayout ( new BoxLayout ( this, BoxLayout.Y_AXIS ) );
            setBorder ( BorderFactory.createEmptyBorder ( 0, 100, 0, 100 ) );

            compressionMap = new HashMap<String, Coder>();

            compressionMap.put ( "Arithmetic Coding", new ArithmeticCoder() );

            compressionBox = new JComboBox<String>();
            for ( String s : compressionMap.keySet() )
            {
                compressionBox.addItem ( s );
            }

            compressionBox.setMaximumSize ( new Dimension ( 500, 24 ) );
            compressionBox.setPreferredSize ( new Dimension ( 200, 24 ) );

            add ( Box.createVerticalGlue() );
            add ( compressionBox );
            add ( Box.createVerticalGlue() );
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
            
            add ( Box.createHorizontalGlue() );
            add ( actionButton );
            add ( Box.createHorizontalGlue() );
            add ( quitButton );
            add ( Box.createHorizontalGlue() );
        }
    }

    private class CompressionPress implements ActionListener
    {
        @Override
        public void actionPerformed ( ActionEvent e )
        {
            Coder coder = compressionPanel.getCoder();
            String coderName = compressionPanel.getCoderName();
            String filename = filePanel.getFilename();
            String compressedFilename = "";
            String textFilename = "";
            String outputFilename = "";
            Component frame = ( Component ) e.getSource();
            byte[] output;

            long startTime, compressionTime;

            Object[] options = { "Compress", "Decompress" };
            int choice = JOptionPane.showOptionDialog (
                frame,
                "What do you want to do?",
                "Compress or Decompress?",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                options,
                options[ 0 ]
            );

            if ( options[ choice ] == "Compress" )
            {
                textFilename = filename;
                compressedFilename = textFilename.replaceFirst ( "\\.txt$", "" );
                compressedFilename =
                    compressedFilename.replaceFirst ( "$", ".emi" );
                outputFilename = compressedFilename;

                String message = FileReader.readFile ( textFilename );

                if ( message == null )
                {
                    JOptionPane.showMessageDialog ( frame,
                        "There is no such file."
                    );
                    return;
                } else if ( message == "" )
                {
                    JOptionPane.showMessageDialog ( frame,
                        "The file is empty."
                    );
                    return;
                }

                startTime = System.currentTimeMillis();
                output = coder.encode ( message );
                compressionTime = System.currentTimeMillis() - startTime;
            } else // Decompress
            {
                compressedFilename = filename;
                textFilename = compressedFilename.replaceFirst ( "\\.emi$", "" );
                textFilename = textFilename.replaceFirst ( "$", ".txt" );
                outputFilename = textFilename;

                byte[] codedMessage =
                    FileReader.readBinary ( compressedFilename );

                if ( codedMessage == null )
                {
                    JOptionPane.showMessageDialog ( frame,
                        "The file does not exist or is empty."
                    );
                    return;
                }

                startTime = System.currentTimeMillis();
                String outputString = coder.decode ( codedMessage );
                compressionTime = System.currentTimeMillis() - startTime;

                output = outputString.getBytes();
            }

            FileWriter.writeToFile ( outputFilename, output );

            new SummaryWindow ( coderName, compressionTime,
                new File ( textFilename ), new File ( compressedFilename )
            );
        }
    }
}
