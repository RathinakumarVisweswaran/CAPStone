package convolution;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import layers.Layer;
import layers.LayerFactory;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class CnnModel {

    public List<Layer> layers;
    float[][][] inputVolume, outputVolume;

    public CnnModel(JSONObject config, Scanner wieghtStream){
        this.layers = new ArrayList<>();
        JSONArray layers = (JSONArray) config.get("layers");
        Iterator<JSONObject> iterator = layers.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonLayer = iterator.next();
            String type = (String) jsonLayer.get("type");
            JSONObject layerConfig = (JSONObject) jsonLayer.get("layerConfig");
            Layer layer = LayerFactory.getLayerOfType(type);
            layer.parseConfig(layerConfig, wieghtStream);
            this.layers.add(layer);
        }
    }

    public void evaluate(DataVolume volume)
    {
        for(Layer l : layers)
            volume = l.processVolume(volume);

    }
}