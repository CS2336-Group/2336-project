package compression.gui;

import java.io.File;
import javax.swing.*;
import java.awt.*;


public class SummaryWindow extends JFrame
{
    private String algorithm;
    private Integer time;
    private File inputFile;
    private File outputFile;

    private JPanel panel;

    public SummaryWindow (
        String algorithm,
        Integer time,
        File inputFile,
        File outputFile )
    {
        this.algorithm = algorithm;
        this.time = time;
        this.inputFile = inputFile;
        this.outputFile = outputFile;

        panel = new JPanel();
        panel.setLayout ( new BoxLayout ( panel, BoxLayout.Y_AXIS ) );

        panel.add ( Box.createRigidArea ( new Dimension ( 0, 12 ) ) );
        panel.add ( new JLabel ( "Compression Algorithm: " + algorithm ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Time in Milliseconds: " + time ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Input File Size: " + inputFile.length() ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Output File Size: " + outputFile.length() ) );
        panel.add ( Box.createRigidArea ( new Dimension ( 0, 24 ) ) );
        panel.add ( new JLabel ( "Compression Rate (btc): " +
            ( ( double ) outputFile.length() / ( double ) inputFile.length() ) * 8
        ) );

        add ( panel );

        setTitle ( "Compression Summary" );
        setSize ( 350, 250 );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        setVisible ( true );
    }
}
