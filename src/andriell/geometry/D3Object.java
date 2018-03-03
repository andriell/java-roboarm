package andriell.geometry;

/**
 * Created by Андрей on 03.03.2018.
 */
public class D3Object {
    protected double  line[][] = new double[0][];
    private double[] translation = new double[]{0d, 0d, 0d};
    private double[] rotation = new double[]{0d, 0d, 0d};
    private double size = 0d;



    public void setLines(int s) {
        line = new double[s][];
        for (int i = 0; i < s; i++) {
            line[i] = new double[2];
        }
    }

    public int getLines(int s) {
        return line.length;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setTranslation(int i, double v) {
        if (i < 0 || i > translation.length) {
            return;
        }
        translation[i] = v;
    }

    public double getTranslation(int i) {
        if (i < 0 || i > translation.length) {
            return 0d;
        }
        return translation[i];
    }

    public void setRotation(int i, double v) {
        if (i < 0 || i > rotation.length) {
            return;
        }
        rotation[i] = v;
    }

    public double getRotation(int i) {
        if (i < 0 || i > rotation.length) {
            return 0d;
        }
        return rotation[i];
    }
}
