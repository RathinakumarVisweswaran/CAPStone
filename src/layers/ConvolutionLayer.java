package layers;

import convolution.DataVolume;
import neurons.ConvolNeuron;
import neurons.Neuron;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rathinakumar on 10/18/2015.
 */
public class ConvolutionLayer implements Layer {
    int numberOfFeatures = 6;
    int stride = 2;
    int padding = 1;
    int filterHeight, filterWidth, filterDepth;
    List<Neuron> neurons;

    public ConvolutionLayer() {
        neurons = new ArrayList<>();
    }

    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        inputVolume = padInputVolume(inputVolume);
        DataVolume outputVolume = new DataVolume((inputVolume.height-filterHeight)/stride + 1,
                (inputVolume.width-filterWidth)/stride + 1, numberOfFeatures);

        for(Neuron neuron : neurons)
            neuron.processVolume(inputVolume, outputVolume);
        return outputVolume;
    }

    private DataVolume padInputVolume(DataVolume inputVolume)
    {
        DataVolume volume = new DataVolume(inputVolume.height + 2*padding, inputVolume.width + 2*padding, inputVolume.depth);
        for(int i=0; i<inputVolume.height; i++)
            for(int j=0; j<inputVolume.width; j++)
                for(int k=0; k<inputVolume.depth; k++)
                    volume.data[i+padding][j+padding][k] = inputVolume.data[i][j][k];
        return volume;
    }

    /**
     * given a fileReader, the method recursively reads the configs needed by the
     * layer and creates the convolNeurons required from the weights provided in json
     */
    @Override
    public void parseConfig(JSONObject config) {
        numberOfFeatures = (int)(long) config.get("numberOfFeatures");
        stride = (int)(long) config.get("stride");
        padding = (int)(long) config.get("padding");
        JSONArray dimen = (JSONArray) config.get("filterDimension");
        Iterator<Number> dimIterater = dimen.iterator();
        filterHeight = dimIterater.next().intValue();
        filterWidth = dimIterater.next().intValue();
        filterDepth = dimIterater.next().intValue();
        JSONArray weights = (JSONArray) config.get("weights");
        Iterator<Number> weightIterater = weights.iterator();
        for(int feature=0; feature<numberOfFeatures; feature++)
        {
            DataVolume weightVolume = new DataVolume(filterHeight,filterWidth,filterDepth);
            for(int h=0; h< filterHeight; h++)
                for(int w=0; w<filterWidth; w++)
                    for(int d=0; d<filterDepth; d++)
                        weightVolume.data[h][w][d] = weightIterater.next().floatValue();
            ConvolNeuron convNeuron = new ConvolNeuron(weightVolume, weightIterater.next().floatValue(), feature, stride);
            neurons.add(convNeuron);
        }
    }
}