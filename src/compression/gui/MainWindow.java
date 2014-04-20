package compression.gui;

import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

import compression.*;

public class MainWindow extends JFrame
{
    JPanel panel;
    JPanel filePanel;
    JPanel compressionPanel;
    JPanel buttonPanel;

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
            
            add ( Box.createHorizontalGlue() );
            add ( actionButton );
            add ( Box.createHorizontalGlue() );
            add ( quitButton );
            add ( Box.createHorizontalGlue() );
        }
    }
}
