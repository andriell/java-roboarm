package andriell.robo.arm;

import andriell.geometry.D3Object;
import andriell.robo.arm.gui.ImagePane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Arm implements ImagePane.Entity {
    private BufferedImage image;
    private final double armSize = 500;
    private Stroke stroke1 = new BasicStroke(1);
    private Stroke stroke2 = new BasicStroke(2);
    private D3Object d3 = new D3Object(3);

    private int[] WH = {600, 600};

    private double a1 = 0;
    private double a2 = 0;
    private double a3 = 0;

    public Arm() {
        image = new BufferedImage(WH[0], WH[1], BufferedImage.TYPE_4BYTE_ABGR);
        d3 = new D3Object(3);
        d3.setTranslation(0, 300);
        d3.setTranslation(1, 300);
        d3.setTranslation(2, 300);
    }


    public void setA1(double a1) {
        this.a1 = a1;
    }

    public void setA2(double a2) {
        this.a2 = a2;
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

        g.setStroke(stroke2);

        double[][] points = new double[3][];
        points[0] = new double[]{0d, 0d, 0d};
        d3.setPoint(0, points[0]);

        points[1] = new double[3];
        points[1][0] = armSize * Math.cos(Math.PI / 180 * a1);
        points[1][1] = armSize * Math.sin(Math.PI / 180 * a1);
        points[1][2] = armSize * Math.sin(Math.PI / 180 * a2);
        d3.setPoint(1, points[1]);

        points[2] = new double[3];
        points[2][0] = points[1][0] + armSize * Math.cos(Math.PI / 180 * a1);
        points[2][1] = points[1][1] + armSize * Math.sin(Math.PI / 180 * a1);
        points[2][2] = points[1][2] + armSize * Math.sin(Math.PI / 180 * a3);
        d3.setPoint(2, points[2]);

        points = d3.calculatePoints();

        g.setColor(Color.red);
        g.drawLine((int) points[0][0], (int) points[0][1], (int) points[1][0], (int) points[1][1]);
        g.setColor(Color.green);
        g.drawLine((int) points[1][0], (int) points[1][1], (int) points[2][0], (int) points[2][1]);
    }

    public D3Object getD3() {
        return d3;
    }
}
