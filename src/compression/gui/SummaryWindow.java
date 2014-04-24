package compression.gui;

import java.io.File;
import javax.swing.*;
import java.awt.*;


public class SummaryWindow extends JFrame
{
    private String algorithm;
    private Long time;
    private File textFile;
    private File compressedFile;

    private JPanel panel;

    public SummaryWindow (
        String algorithm,
        Long time,
        File textFile,
        File compressedFile )
    {
        this.algorithm = algorithm;
        this.time = time;
        this.textFile = textFile;
        this.compressedFile = compressedFile;

        panel = new JPanel();
        panel.setLayout ( new BoxLayout ( panel, BoxLayout.Y_AXIS ) );

        panel.add ( Box.createRigidArea ( new Dimension ( 0, 12 ) ) );
        panel.add ( new JLabel ( "Compression Algorithm: " + algorithm ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Time in Milliseconds: " + time ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Text File Size: " + textFile.length() ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Compressed File Size: " + compressedFile.length() ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Compression Rate (btc): " +
            ( ( double ) compressedFile.length() / ( double ) textFile.length() ) * 8
        ) );

        add ( panel );

        setTitle ( "Compression Summary" );
        setSize ( 350, 250 );
        setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );
        setVisible ( true );
    }
}
