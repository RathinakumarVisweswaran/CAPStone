package layers;

import convolution.DataVolume;
import org.json.simple.JSONObject;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class InputLayer implements Layer {

    int height, width,depth;

    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        if(validate(inputVolume))
            return inputVolume;
        else
            return null;
    }

    private boolean validate(DataVolume inputVolume)
    {
        return (inputVolume.height == height && inputVolume.width == width && inputVolume.depth == depth)? true : false;
    }

    @Override
    public void parseConfig(JSONObject config, Scanner weightStream) {
        height = ((Number)config.get("height")).intValue();
        width = ((Number) config.get("width")).intValue();
        depth = ((Number)config.get("depth")).intValue();
    }
}
