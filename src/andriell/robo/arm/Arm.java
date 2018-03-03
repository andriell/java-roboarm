package andriell.robo.arm;

import andriell.geometry.D3Object;
import andriell.robo.arm.gui.ImagePane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Arm extends D3Object implements ImagePane.Entity {
    private BufferedImage image;
    private final double armSize = 500;
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
        setMaxPoints(3);
        setRotationAngle(0, 45);
        setRotationAngle(1, 45);
        setRotationAngle(2, 45);
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

        points[0][0] = 0d;
        points[0][1] = 0d;
        points[0][2] = 0d;

        points[1][0] = armSize * Math.cos(Math.PI / 180 * a1);
        points[1][1] = armSize * Math.sin(Math.PI / 180 * a1);
        points[1][2] = armSize * Math.sin(Math.PI / 180 * a2);

        double newPoints[][] = getPoints();

        g.setColor(Color.red);
        g.drawLine((int) newPoints[0][0], (int) newPoints[0][1], (int) newPoints[1][0], (int) newPoints[1][1]);


    }
}
