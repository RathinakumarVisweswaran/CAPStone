package convolution;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Created by Rathinakumar on 10/20/2015.
 */
public class DataVolume {
    public int height, width, depth;
    public double[][][] data;

    public DataVolume(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
        data = new double[height][width][depth];
    }

    public void setData(double[][][] data)
    {
        this.data = data;
    }

    public double getElement(int height, int width, int depth)
    {
        return data[height][width][depth];
    }

    public void setElement(int height, int width, int depth, double value)
    {
        data[height][width][depth] = value;
    }

    private boolean reLu()
    {
        for(int h=0; h<height; h++)
            for(int w=0; w<width; w++)
                for(int d=0; d<depth; d++)
                    data[h][w][d] = (data[h][w][d] > 0)? data[h][w][d] : 0f;
        return true;
    }

    private boolean sigmoid()
    {
        for(int h=0; h<height; h++)
            for(int w=0; w<width; w++)
                for(int d=0; d<depth; d++)
                    data[h][w][d] = (double) (1/( 1 + Math.pow(Math.E,(-1*(data[h][w][d])))));
        return true;
    }

    private boolean softmax()
    {
        double sum = 0, max = Double.MIN_VALUE;
        for(int h=0; h<height; h++)
            for(int w=0; w<width; w++)
                for(int d=0; d<depth; d++)
                    if(data[h][w][d]>max) max = data[h][w][d];

        for(int h=0; h<height; h++)
            for(int w=0; w<width; w++)
                for(int d=0; d<depth; d++)
                {
                    data[h][w][d] = Math.exp(data[h][w][d] - max);
                    sum+=data[h][w][d];
                }

        for(int h=0; h<height; h++)
            for(int w=0; w<width; w++)
                for(int d=0; d<depth; d++)
                    data[h][w][d] = (data[h][w][d]/sum);
        return true;
    }

    public boolean activate(String name)
    {
        switch (name) {
            case "ReLu":
                return reLu();
            /*case "abs":
                return Math.abs(x);
            case "acos":
                return (float) Math.cos((double) x);
            case "asin":
                return (float) Math.sin((double) x);
            case "atan":
                return (float) Math.tan((double) x);
            case "ceil":
                return (float) Math.ceil(x);*/
/*            case "cos":
                return new Cos(x);
            case "exp":
                return new Exp(x);
            case "floor":
                return new Floor(x);
            case "hardtanh":
                return new HardTanh(x);
            case "identity":
                return new Identity(x);
            case "leakyrelu":
                return new LeakyReLU(x);
            case "log":
                return new Log(x);
            case "maxout":
                return new MaxOut(x);
            case "negative":
                return new Negative(x);
            case "pow":
                return new Pow(x, 2);
            case "round":
                return new Round(x);*/
            case "sigmoid":
                return sigmoid();
/*            case "sign":
                return new Sign(x);
            case "sin":
                return new Sin(x);
            case "softsign":
                return new SoftSign(x);*/
            case "softmax":
                return softmax();
            /*case "stabilize":
                return new Stabilize(x, 1);
            case "tanh":
                return (double) Math.tanh(x);
            case "timesoneminus":
                return new TimesOneMinus(x);
            case "softmax":
                return new SoftMax(x);
            case "softplus":
                return new SoftPlus(x);
            case "step":
                return new XPath.Step(x);*/
            default:
                throw new IllegalArgumentException("Illegal name " + name);
        }
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.000");
        StringBuffer str = new StringBuffer();
        str.append("[");
        for(int d=0; d< depth; d++)
        {
            str.append("[");
            for(int h=0; h<height; h++) {
                str.append("[");
                for (int w = 0; w < width; w++)
                {
                    str.append(data[h][w][d]);
                    str.append((w==width-1)? "":", ");
                }
                str.append("]");
                if(h<height-1)str.append("\n");
            }
            str.append("]");
            if(d<depth-1)str.append("\n");
        }
        str.append("]");
        str.append("\n");
        return str.toString();
    }
}
