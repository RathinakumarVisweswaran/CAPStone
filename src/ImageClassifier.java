import convolution.CnnModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rathinakumar on 10/23/2015.
 */
public class ImageClassifier {

    public static String modelLocation = "modelStore";
    public List<String> availableModels;
    public Map<String, String> modelMap = new HashMap<>();

    public static void main (String[] args) throws Exception {
        ImageClassifier ic = new ImageClassifier();

        //ic.loadModel(modelJson)
    }

    public ImageClassifier()
    {
        scanForModels();
    }

    private void scanForModels()
    {
        FilenameFilter jsonFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return (name.endsWith(".json"))? true: false;
            }
        };
        File modelStore = new File("modelStore");
        if(modelStore.isDirectory())
            for(File file : modelStore.listFiles(jsonFilter))
                modelMap.put(file.getName(), file.getPath());
        else
            modelStore.mkdir();
    }

    public String loadModel(String modelName) throws Exception
    {
        String modelJson = "modelStore"+ File.separator+"TestModel.json";
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject)parser.parse(new FileReader(modelJson));
        CnnModel model = new CnnModel(config);
        return "";
    }

}
