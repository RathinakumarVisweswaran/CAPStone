package layers;

import convolution.DataVolume;
import neurons.FullyConnectedNeuron;
import neurons.Neuron;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class FullyConnectedLayer implements Layer {
    int numberOfNeurons, weightLength;
    List<Neuron> neurons;

    public FullyConnectedLayer() {
        this.neurons = new ArrayList<>();
    }

    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        DataVolume output = new DataVolume(1,1,numberOfNeurons);
        for(Neuron n : neurons)
        n.processVolume(inputVolume, output);
        return output;
    }

    @Override
    public void parseConfig(JSONObject config) {
        numberOfNeurons = (int)(long) config.get("numberOfNeurons");
        weightLength = (int)(long) config.get("weightLength");
        JSONArray weights = (JSONArray) config.get("weights");
        Iterator<Number> weightIterater = weights.iterator();
        for(int n=0; n<numberOfNeurons; n++)
        {
            DataVolume weightArray = new DataVolume(1,1,weightLength);
            for(int w=0; w<weightLength; w++)
                weightArray.setElement(0,0,w, weightIterater.next().floatValue());
            neurons.add(new FullyConnectedNeuron(weightArray, n));
        }
    }
}
