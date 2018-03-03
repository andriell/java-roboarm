package andriell.robo.arm;

import andriell.robo.arm.gui.ImagePane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Arm implements ImagePane.Entity {
    private BufferedImage image;
    private Stroke stroke1 = new BasicStroke(1);
    private Stroke stroke2 = new BasicStroke(2);
    private Color[] edgesColor = new Color[] {
            new Color(255,0,0),
            new Color(0,255,0),
            new Color(0,0,255),
    };

    private int[] WH = {600, 600};

    private double a1 = 0;
    private double a2 = 0;
    private double a3 = 0;

    public Arm() {
        image = new BufferedImage(WH[0], WH[1], BufferedImage.TYPE_4BYTE_ABGR);
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
        return image;
    }

    public void repaint() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, WH[0], WH[1]);

        g.setColor(Color.black);
        g.setStroke(stroke1);

        g.drawString("A1 " + Math.round(a1), 5, 15);
        g.drawString("A2 " + Math.round(a2), 5, 30);
        g.drawString("A3 " + Math.round(a3), 5, 45);

        g.setColor(Color.black);
        g.drawLine(0, WH[1] / 2, WH[0], WH[1] / 2);
        g.drawLine(WH[0] / 2, 0, WH[0] / 2, WH[1]);

        g.setStroke(stroke2);

        g.setColor(edgesColor[0]);
        g.drawLine(0, 0, 100, 100);


    }
}
