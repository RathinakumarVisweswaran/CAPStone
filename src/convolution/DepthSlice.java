package convolution;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class DepthSlice {
    public int height, width;
    float[][] data;

    public DepthSlice(int height, int width) {
        this.height = height;
        this.width = width;
        data = new float[height][width];
    }

    public DepthSlice(float[][] data) {
        this.data = data;
        this.height = data.length;
        this.width = data[0].length;
    }
}
