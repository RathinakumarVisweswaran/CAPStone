package layers;

import neurons.ConvolNeuron;

import java.io.FileReader;

/**
 * Created by Rathinakumar on 10/18/2015.
 */
public class ConvolutionLayer {
    int numberOfFeatures = 6;
    int stride = 2;
    int padding = 1;
    int filterLength, filterWidth;
    float[][][] inputVolume, outputVolume;
    ConvolNeuron[] neurons;

    public ConvolutionLayer(int numberOfFeatures, int stride, int padding, float[][][] inputVolume) {
        this.numberOfFeatures = numberOfFeatures;
        this.stride = stride;
        this.padding = padding;
        this.inputVolume = inputVolume;
        neurons = new ConvolNeuron[numberOfFeatures];
        outputVolume = new float[filterLength][filterWidth][numberOfFeatures];
    }

    /**
     * given a fileReader, the method recursively reads the configs needed by the
     * layer and creates the convolNeurons required from the weights provided in json
     */
    public void extractNeuronConfig(FileReader fromJson)
    {

    }


    public int getNumberOfFeatures() {
        return numberOfFeatures;    
    }

    public void setNumberOfFeatures(int numberOfFeatures) {
        this.numberOfFeatures = numberOfFeatures;
    }

    public int getStride() {
        return stride;
    }

    public void setStride(int stride) {
        this.stride = stride;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getFilterLength() {
        return filterLength;
    }

    public void setFilterLength(int filterLength) {
        this.filterLength = filterLength;
    }

    public int getFilterWidth() {
        return filterWidth;
    }

    public void setFilterWidth(int filterWidth) {
        this.filterWidth = filterWidth;
    }
}
