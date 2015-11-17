package neurons;

import convolution.DataVolume;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public interface Neuron {

    public void processVolume(DataVolume inputVolume, DataVolume outputVolume);
    public void setBias(double bias);
}
