package andriell.robo.arm.gui;

import andriell.robo.arm.Arm;

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
    private JSlider rotationXY;
    private JSlider rotationXZ;
    private JSlider rotationYZ;
    private JButton saveButton;
    private JLabel vrxy;
    private JLabel vrxz;
    private JLabel vryz;
    private JTextArea textAreaPoints;
    private JButton resetButton;
    private JSlider servo1;
    private JLabel vs1;
    private JLabel vs2;
    private JSlider servo2;
    private JLabel vs3;
    private JSlider servo3;
    private JSlider sliderSize;
    private JSlider translationZ;
    private JSlider translationY;
    private JLabel vs;
    private JLabel vtz;
    private JLabel vty;
    private JLabel vtx;
    private JSlider translationX;

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
        rotationXY.addChangeListener(changeListenerRotation);
        rotationXZ.addChangeListener(changeListenerRotation);
        rotationYZ.addChangeListener(changeListenerRotation);
        translationX.addChangeListener(changeListenerRotation);
        translationY.addChangeListener(changeListenerRotation);
        translationZ.addChangeListener(changeListenerRotation);
        sliderSize.addChangeListener(changeListenerRotation);
        servo1.addChangeListener(changeListenerRotation);
        servo2.addChangeListener(changeListenerRotation);
        servo3.addChangeListener(changeListenerRotation);
        

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotationXY.setValue(90);
                rotationXZ.setValue(0);
                rotationYZ.setValue(90);
                translationX.setValue(0);
                translationY.setValue(0);
                translationZ.setValue(0);
                sliderSize.setValue(50);
                servo1.setValue(90);
                servo2.setValue(90);
                servo3.setValue(90);
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
                            servo1.getValue(),
                            servo2.getValue(),
                            servo3.getValue()
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
        value = rotationXY.getValue();
        vrxy.setText(Integer.toString(value));
        arm.getD3().setRotation(2, value);

        value = rotationXZ.getValue();
        vrxz.setText(Integer.toString(value));
        arm.getD3().setRotation(1, value);

        value = rotationYZ.getValue();
        vryz.setText(Integer.toString(value));
        arm.getD3().setRotation(0, value);

        value = translationX.getValue();
        vtx.setText(Integer.toString(value));
        arm.getD3().setTranslation(0, value + 300);

        value = translationY.getValue();
        vty.setText(Integer.toString(value));
        arm.getD3().setTranslation(1, value + 300);

        value = translationZ.getValue();
        vtz.setText(Integer.toString(value));
        arm.getD3().setTranslation(2, value + 300);

        double valueD = ((double) sliderSize.getValue()) / 100d;
        vs.setText(Double.toString(valueD));
        arm.getD3().setSize(valueD);
        
        value = servo1.getValue();
        vs1.setText(Integer.toString(value));
        arm.setA1(value);

        value = servo2.getValue();
        vs2.setText(Integer.toString(value));
        arm.setA2(value);

        value = servo3.getValue();
        vs3.setText(Integer.toString(value));
        arm.setA3(value);

        arm.repaint();
        textAreaPoints.setText("");

        centerPane.repaint();
    }
}
