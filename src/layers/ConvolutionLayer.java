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
import java.util.Scanner;

/**
 * Created by Rathinakumar on 10/18/2015.
 */
public class ConvolutionLayer implements Layer {
    int numberOfFeatures = 6;
    int stride = 2;
    int padding = 1;
    String activationFunction;
    int filterHeight, filterWidth, filterDepth;
    List<Neuron> neurons;

    public ConvolutionLayer() {
        neurons = new ArrayList<>();
    }

    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        inputVolume = padInputVolume(inputVolume);
        DataVolume outputVolume = new DataVolume((inputVolume.height - filterHeight)/stride + 1,
                (inputVolume.width - filterWidth)/stride + 1, numberOfFeatures);

        for(Neuron neuron : neurons)
            neuron.processVolume(inputVolume, outputVolume);
        outputVolume.activate(activationFunction);
        return outputVolume;
    }

    private DataVolume padInputVolume(DataVolume inputVolume)
    {
        DataVolume volume = new DataVolume(inputVolume.height + 2*padding, inputVolume.width + 2*padding, inputVolume.depth);
        for(int k=0; k<inputVolume.depth; k++)
            for(int i=0; i<inputVolume.height; i++)
                for(int j=0; j<inputVolume.width; j++)
                    volume.data[i+padding][j+padding][k] = inputVolume.data[i][j][k];
        return volume;
    }

    /**
     * given a fileReader, the method recursively reads the configs needed by the
     * layer and creates the convolNeurons required from the weights provided in json
     */
    @Override
    public void parseConfig(JSONObject config, Scanner weightStream) {
        numberOfFeatures = (int)(long) config.get("numberOfFeatures");
        stride = (int)(long) config.get("stride");
        padding = (int)(long) config.get("padding");
        JSONArray dimen = (JSONArray) config.get("filterDimension");
        Iterator<Number> dimIterater = dimen.iterator();
        filterHeight = dimIterater.next().intValue();
        filterWidth = dimIterater.next().intValue();
        filterDepth = dimIterater.next().intValue();
        activationFunction = (String) config.get("activation");

        List<Double> biasList = new ArrayList<>();
        for(int f=0; f<numberOfFeatures; f++)
            biasList.add(weightStream.nextDouble());

        DataVolume[] dataVolumes = new DataVolume[numberOfFeatures];
        for(int f=0; f<numberOfFeatures; f++)
            dataVolumes[f] = new DataVolume(filterHeight,filterWidth,filterDepth);

        for(int f=0; f<numberOfFeatures; f++)
            for(int d=0; d<filterDepth; d++)
                for(int h=0; h< filterHeight; h++)
                    for(int w=0; w<filterWidth; w++)
                        dataVolumes[f].data[h][w][d] = weightStream.nextDouble();

        for(int f=0; f<numberOfFeatures; f++)
        {
            ConvolNeuron convNeuron = new ConvolNeuron(dataVolumes[f], 0.0f, f, stride);
            convNeuron.setBias(biasList.get(f));
            neurons.add(convNeuron);
        }
    }
}