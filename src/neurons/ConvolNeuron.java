package neurons;

import convolution.DataVolume;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class ConvolNeuron implements Neuron{

    DataVolume weightMatrix;
    int stride;
    float bias;
    int position=-1;
    public ConvolNeuron(DataVolume weightMatrix, float bias, int featureNumber, int stride) {
        this.weightMatrix = weightMatrix;
        this.position = featureNumber;
        this.bias = bias;
        this.stride = stride;
    }


    @Override
    public void processVolume(DataVolume inputVolume, DataVolume outputVolume) {

        for (int h=0; h<outputVolume.height; h++)
            for (int w=0; w<outputVolume.height; w++)
            {
                float sum = 0.0f;
                for(int hh=0; hh<weightMatrix.height; hh++)
                    for(int ww=0; ww<weightMatrix.width; ww++)
                        for(int dd=0; dd<weightMatrix.depth; dd++)
                            sum+=(weightMatrix.getElement(h*stride+hh, w*stride+ww, dd));
                outputVolume.setElement(h,w,position,sum);
            }
        return;
    }
}
