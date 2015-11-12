package neurons;

import convolution.DataVolume;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class FullyConnectedNeuron implements Neuron {

    DataVolume weights;
    int position=0;
    float bias;

    public FullyConnectedNeuron(DataVolume weights, float bias, int position)
    {
        this.weights = weights;
        this.position = position;
        this.bias = bias;
    }

    @Override
    public void setBias(float bias)
    {
        this.bias = bias;
    }

    @Override
    public void processVolume(DataVolume inputVolume, DataVolume outputVolume) {
        int wight=0;
        float sum = 0.0f;
        for(int h=0; h<inputVolume.height; h++)
            for(int w=0; w<inputVolume.width; w++)
                for(int d=0; d<inputVolume.depth; d++)
                    sum+=(weights.getElement(0,0,wight++)*inputVolume.getElement(h,w,d));
        outputVolume.setElement(0,0,position, sum+bias);
    }
}
