package andriell.robo.arm.gui;

import andriell.robo.arm.Arm;
import andriell.robo.arm.gui.ImagePane;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Андрей on 03.03.2018.
 */
public class MainFrame {
    JFrame frame;
    private JPanel rootPanel;
    private JPanel centerPane;
    private JPanel rightPane;
    private JPanel topPane;
    private JSlider slider01;
    private JSlider slider02;
    private JSlider slider03;
    private JButton saveButton;
    private JCheckBox autoCheckBox;
    private JLabel value01;
    private JLabel value02;
    private JLabel value03;
    private JTextArea textAreaPoints;
    private JButton resetButton;

    Arm arm;

    public MainFrame() {

    }

    public void show() {
        frame = new JFrame("Real Tesseract");
        arm = new Arm();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1252, 875);
        frame.setContentPane(rootPanel);
        centerPane.add(new ImagePane(arm));

        ChangeListener changeListenerRotation = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                update();
            }
        };
        slider01.addChangeListener(changeListenerRotation);
        slider02.addChangeListener(changeListenerRotation);
        slider03.addChangeListener(changeListenerRotation);


        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                slider01.setValue(0);
                slider02.setValue(0);
                slider03.setValue(0);
                update();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    arm.repaint();
                    BufferedImage bi = arm.getImage();
                    String fileName = String.format(
                            "tesseract_%03d_%03d_%03d.png",
                            slider01.getValue(),
                            slider02.getValue(),
                            slider03.getValue()
                    );
                    File outputFile = new File(fileName);
                    ImageIO.write(bi, "png", outputFile);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        update();

        frame.setVisible(true);
    }

    private void update() {
        int value;
        value = slider01.getValue();
        value01.setText(Integer.toString(value));
        arm.setA1(value);

        value = slider02.getValue();
        value02.setText(Integer.toString(value));
        arm.setA2(value);

        value = slider03.getValue();
        value03.setText(Integer.toString(value));
        arm.setA3(value);

        arm.repaint();
        //textAreaPoints.setText(arm.printVertices());

        centerPane.repaint();
    }
}
