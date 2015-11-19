package neurons;

import convolution.DataVolume;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class ConvolNeuron implements Neuron{

    DataVolume weightMatrix;
    int stride;
    double bias;
    int position=-1;
    public ConvolNeuron(DataVolume weightMatrix, double bias, int featureNumber, int stride) {
        this.weightMatrix = weightMatrix;
        this.position = featureNumber;
        this.bias = bias;
        this.stride = stride;
    }

    public void setBias(double bias)
    {
        this.bias = bias;
    }

    @Override
    public void processVolume(DataVolume inputVolume, DataVolume outputVolume) {

        for (int h=0; h<outputVolume.height; h++)
            for (int w=0; w<outputVolume.width; w++)
            {
                double sum = 0;
                for(int hh=0; hh<weightMatrix.height; hh++)
                    for(int ww=0; ww<weightMatrix.width; ww++)
                        for(int dd=0; dd<weightMatrix.depth; dd++)
                            sum+=(inputVolume.getElement(h*stride+hh, w*stride+ww, dd) * weightMatrix.getElement(hh, ww, dd));
                outputVolume.setElement(h,w,position,sum+bias);
            }
        return;
    }
}
