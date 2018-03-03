package andriell.robo.arm;

import andriell.robo.arm.gui.ImagePane;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Андрей on 03.03.2018.
 */
public class Arm implements ImagePane.Entity {
    private double a1 = 0;
    private double a2 = 0;
    private double a3 = 0;

    public double getX12() {
        return 0;
    }
    
    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = a1;
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = a2;
    }

    public double getA3() {
        return a3;
    }

    public void setA3(double a3) {
        this.a3 = a3;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public BufferedImage getImage() {
        return null;
    }

    public void repaint() {

    }
}
