package compression.gui;

import javax.swing.*;
import java.awt.*;

import compression.*;

public class CompressWindow extends JFrame
{
    Coder coder;

    JPanel panel;

    public CompressWindow ( Coder coder )
    {
        this.coder = coder;

        panel = new ButtonPanel();

        add ( panel );

        setTitle ( "Compress or Decompress?" );
        setSize ( 300, 100 );
        setMinimumSize ( new Dimension ( 300, 100 ) );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        setVisible ( true );
    }

    private class ButtonPanel extends JPanel
    {
        JButton compressButton, decompressButton;

        public ButtonPanel()
        {
            setLayout ( new BoxLayout ( this, BoxLayout.LINE_AXIS ) );

            compressButton = new JButton ( "Compress" );
            decompressButton = new JButton ( "Decompress" );

            compressButton.setPreferredSize ( decompressButton.getPreferredSize() );

            compressButton.setMaximumSize ( new Dimension ( 500, 30 ) );
            decompressButton.setMaximumSize ( new Dimension ( 500, 30 ) );

            add ( Box.createHorizontalGlue() );
            add ( compressButton );
            add ( Box.createHorizontalGlue() );
            add ( decompressButton );
            add ( Box.createHorizontalGlue() );
        }
    }
}
