package convolution;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import layers.Layer;
import layers.LayerFactory;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class TrainedModel {

    public List<Layer> layers;
    float[][][] inputVolume, outputVolume;

    public static void main (String[] args) throws Exception {
        String modelJson = "cnn.json";
        TrainedModel model = new TrainedModel();
        model.loadModel(modelJson);

    }

    public void loadModel(String jsonFile) throws Exception {

        this.layers = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject)parser.parse(new FileReader(jsonFile));
        JSONArray layers = (JSONArray) config.get("layers");
        Iterator<JSONObject> iterator = layers.iterator();

        while (iterator.hasNext()) {
            JSONObject jsonLayer = iterator.next();
            String type = (String) jsonLayer.get("type");
            JSONObject layerConfig = (JSONObject) jsonLayer.get("layerConfig");
            Layer layer = LayerFactory.getLayerOfType(type);
            layer.parseConfig(layerConfig);
            this.layers.add(layer);
        }
    }

    public void evaluate(DataVolume volume)
    {
        for(Layer l : layers)
            volume = l.processVolume(volume);

    }
}