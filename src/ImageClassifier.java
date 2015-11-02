import convolution.CnnModel;
import convolution.DataVolume;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
        DataVolume in = ic.loadImage("TestImages" + File.separator + "thunderbird-v2-32x32.png");

        ic.loadModel("TestModel");
    }

    public DataVolume loadImage(String img) throws IOException {
        File imageFile = new File(img);

        BufferedImage image = ImageIO.read(imageFile);
        int width = image.getWidth();
        int height = image.getHeight();
        DataVolume input = new DataVolume(height,width,3);

        for(int h=0; h<height; h++){
            for(int w=0; w<width; w++){
                Color c = new Color(image.getRGB(w, h));
                input.setElement(h,w,0,c.getRed());
                input.setElement(h,w,1,c.getGreen());
                input.setElement(h,w,2,c.getBlue());
                //System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
            }
        }
        return input;
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
        String modelJson = "modelStore"+ File.separator+""+modelName+".json";
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject)parser.parse(new FileReader(modelJson));
        CnnModel model = new CnnModel(config);
        return "";
    }

}
