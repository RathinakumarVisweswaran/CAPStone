package neurons;

import convolution.DataVolume;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class ConvolNeuron implements Neuron{

    DataVolume weightMatrix;
    float bias;
    int position=-1;

    public ConvolNeuron() {
    }

    public ConvolNeuron(DataVolume weightMatrix, float bias, int featureNumber) {
        this.weightMatrix = weightMatrix;
        this.position = featureNumber;
        this.bias = bias;
    }


    @Override
    public void processVolume(DataVolume inputVolume, DataVolume outputVolume) {

    }
}
