package layers;

import convolution.DataVolume;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

/**
 * Created by Rathinakumar on 10/18/2015.
 */
public class SubsamplingLayer implements Layer{

    int height, width, stride;

    public SubsamplingLayer()
    {

    }

    public SubsamplingLayer(int height, int width, int stride) {
        this.height = height;
        this.width = width;
        this.stride = stride;
    }

    @Override
    public DataVolume processVolume(DataVolume inputVolume) {

        DataVolume outputVolume = new DataVolume(inputVolume.height/stride, inputVolume.width/stride, inputVolume.depth);

        for(int d=0; d<outputVolume.depth; d++)
            for(int h=0; h<outputVolume.height; h++)
                for(int w=0; w<outputVolume.height; w++)
                {
                    float max = Float.MIN_VALUE;
                    for(int hh=0; hh<height; hh++)
                        for(int ww=0; ww<height; ww++)
                            max = Math.max(max, inputVolume.data[h*stride + hh][w*stride +ww][d]);
                    outputVolume.data[h][w][d] = max;
                }
        return outputVolume;
    }

    @Override
    public void parseConfig(JSONObject config) {
        JSONArray dimensions = (JSONArray) config.get("dimension");
        Iterator<Number> dimIterator = dimensions.iterator();
        height = dimIterator.next().intValue();
        width = dimIterator.next().intValue();
        stride = (int)(long)config.get("stride");
    }
}
