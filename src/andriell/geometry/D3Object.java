package andriell.geometry;

/**
 * Created by Андрей on 03.03.2018.
 */
public class D3Object {
    private final int l = 3;
    protected double points[][];
    private double[] translation = new double[]{0d, 0d, 0d};
    private double[] rotation = new double[]{0d, 0d, 0d};
    private double size = 1d;
    private double[] cos = {1d, 1d, 1d};
    private double[] sin = {1d, 1d, 1d};

    public D3Object(int maxPoints) {
        points = new double[maxPoints][];
        for (int i = 0; i < maxPoints; i++) {
            points[i] = new double[3];
        }
    }

    public double[][] calculatePoints() {
        double[][] r = new double[points.length][];
        for (int i = 0; i < points.length; i++) {
            r[i] = calculatePoint(points[i]);
        }
        return r;
    }

    public double[] calculatePoint(int i) {
        if (i < 0 || i > points.length) {
            return null;
        }
        return calculatePoint(points[i]);
    }

    protected double[] calculatePoint(double[] p) {
        double[] r = new double[l];
        r[0] = size * (translation[0] + p[0] * cos[1] * cos[2] - p[2] * sin[1] - p[1] * cos[1] * sin[2]);
        r[1] = size * (translation[1] - p[2] * cos[1] * sin[0] + p[0] * (cos[0] * sin[2] - cos[2] * sin[0] * sin[1]) + p[1] * (cos[0] * cos[2] + sin[0] * sin[1] * sin[2]));
        r[2] = size * (translation[2] + p[2] * cos[0] * cos[1] + p[0] * (cos[0] * cos[2] * sin[1] + sin[0] * sin[2]) + p[1] * (cos[2] * sin[0] - cos[0] * sin[1] * sin[2]));
        return r;
    }

    public void setPoint(int i, int j, double v) {
        if (i < 0 || i > points.length || j < 0 || j > l) {
            return;
        }
        points[i][j] = v;
    }

    public void setPoint(int i, double[] xyz) {
        if (i < 0 || i > points.length || xyz == null || xyz.length != 3) {
            return;
        }
        points[i] = xyz;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setTranslation(int i, double v) {
        if (i < 0 || i > l) {
            return;
        }
        translation[i] = v;
    }

    public double getTranslation(int i) {
        if (i < 0 || i > l) {
            return 0d;
        }
        return translation[i];
    }

    public void setRotation(int i, double v) {
        if (i < 0 || i > l) {
            return;
        }
        rotation[i] = v;
        cos[i] = Math.cos((Math.PI * v) / 180d);
        sin[i] = Math.sin((Math.PI * v) / 180d);
    }

    public double getRotation(int i) {
        if (i < 0 || i > l) {
            return 0d;
        }
        return rotation[i];
    }
}
