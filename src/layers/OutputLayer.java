package layers;

import neurons.DataVolume;
import org.json.simple.JSONObject;

import java.util.Scanner;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class OutputLayer implements Layer{
    @Override
    public DataVolume processVolume(DataVolume inputVolume) {
        return new DataVolume(0,0,0);
    }

    @Override
    public void parseConfig(JSONObject config, Scanner weightStream) {

    }
}
