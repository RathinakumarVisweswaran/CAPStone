package convolution;

/**
 * Created by Rathinakumar on 10/20/2015.
 */
public class DataVolume {
    public int height, width, depth;
    public float[][][] data;

    public DataVolume(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        data = new float[height][width][depth];
    }

    public void setData(float[][][] data)
    {
        this.data = data;
    }

    public float getElement(int height, int width, int depth)
    {
        return data[height][width][depth];
    }

    public void setElement(int height, int width, int depth, float value)
    {
        data[height][width][depth] = value;
    }

}
