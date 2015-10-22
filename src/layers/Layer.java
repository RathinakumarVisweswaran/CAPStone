package layers;

import convolution.DataVolume;
import org.json.simple.JSONObject;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public interface Layer {

    public DataVolume processVolume(DataVolume inputVolume);
    public void parseConfig(JSONObject config);
}
