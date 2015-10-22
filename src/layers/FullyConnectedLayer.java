package layers;

import convolution.DataVolume;
import org.json.simple.JSONObject;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class FullyConnectedLayer implements Layer {
    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        return new DataVolume(0,0,0);
    }

    @Override
    public void parseConfig(JSONObject config) {

    }
}
