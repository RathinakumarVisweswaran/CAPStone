package layers;

import convolution.DataVolume;
import neurons.FullyConnectedNeuron;
import neurons.Neuron;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class FullyConnectedLayer implements Layer {
    int numberOfNeurons, weightLength;
    List<Neuron> neurons;
    String activationFunction;
    public FullyConnectedLayer() {
        this.neurons = new ArrayList<>();
    }

    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        DataVolume output = new DataVolume(1,1,numberOfNeurons);
        for(Neuron n : neurons)
            n.processVolume(inputVolume, output);
        output.activate(activationFunction);
        return output;
    }

    @Override
    public void parseConfig(JSONObject config, Scanner weightStream) {
        numberOfNeurons = (int)(long) config.get("numberOfNeurons");
        weightLength = (int)(long) config.get("weightLength");
        activationFunction = (String) config.get("activation");

        List<Double> biasList = new ArrayList<>();
        for(int f=0; f<numberOfNeurons; f++)
            biasList.add(weightStream.nextDouble());

        for(int n=0; n<numberOfNeurons; n++)
        {
            DataVolume weightArray = new DataVolume(1,1,weightLength);
            for(int w=0; w<weightLength; w++)
                weightArray.setElement(0,0,w, weightStream.nextFloat());
            FullyConnectedNeuron fc = new FullyConnectedNeuron(weightArray, 0.0f, n);
            fc.setBias(biasList.get(n));
            neurons.add(fc);
        }
    }
}
