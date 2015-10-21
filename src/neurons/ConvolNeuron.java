package neurons;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class ConvolNeuron {

    float[][][] inputVolume, outputVolume, weightMatrix;
    int featureNumber;

    public ConvolNeuron(float[][][] weightMatrix, int featureNumber) {
        this.weightMatrix = weightMatrix;
        this.featureNumber = featureNumber;
    }




}
