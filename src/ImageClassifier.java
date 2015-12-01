import convolution.CnnModel;
import convolution.DataVolume;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by Rathinakumar on 10/23/2015.
 */
public class ImageClassifier {

    public static String modelLocation = "modelStore";
    public List<String> availableModels;
    public Map<String, String> modelMap = new HashMap<>();

    public static void main (String[] args) throws Exception {
        testIRIS();
    }


    public static void testMNIST() throws Exception {
        ImageClassifier classifier = new ImageClassifier();
        int inH = 28, inW=28, inD=1;
        double[][][] input  = new double[inH][inW][inD];
        Random r = new Random();
        Scanner testData = new Scanner(new File("modelStore//CNN_MINST//test.txt"));
        Long l1 = System.currentTimeMillis();
        CnnModel model = classifier.loadModel("CNN_MINST");
        System.out.println("model load time : "+ (System.currentTimeMillis() - l1));
        while(testData.hasNext())
        {
            for(int k=0;k<inD; k++)
                for(int i=0;i<inH; i++)
                    for(int j=0;j<inW; j++)
                        input[i][j][k] = testData.nextDouble();
            DataVolume in = new DataVolume(inH,inW,inD);
            in.setData(input);
            Long l = System.currentTimeMillis();
            model.evaluate(in);
            System.out.println("model test time : " + (System.currentTimeMillis()-l));
        }
    }

    public static void testIRIS() throws Exception {
        ImageClassifier classifier = new ImageClassifier();
        int inH = 2, inW=2, inD=1;
        double[][][] input  = new double[inH][inW][inD];
        Random r = new Random();
        Scanner testData = new Scanner(new File("modelStore//CNN_Iris//test.txt"));
        Long l1 = System.currentTimeMillis();
        CnnModel model = classifier.loadModel("CNN_Iris");
        System.out.println("model load time : "+ (System.currentTimeMillis() - l1));
        while(testData.hasNext())
        {
            for(int k=0;k<inD; k++)
                for(int i=0;i<inH; i++)
                    for(int j=0;j<inW; j++)
                        input[i][j][k] = testData.nextDouble();
            DataVolume in = new DataVolume(inH,inW,inD);
            in.setData(input);
            Long l = System.currentTimeMillis();
            model.evaluate(in);
            System.out.println("model test time : " + (System.currentTimeMillis()-l));
        }
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
        FileFilter dirFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        File modelStore = new File("modelStore");
        if(modelStore.isDirectory())
            for(File file : modelStore.listFiles(dirFilter))
            {
                if(file.isDirectory())
                    modelMap.put(file.getName(), file.getPath());
            }
        else
            modelStore.mkdir();
    }

    public CnnModel loadModel(String modelName) throws Exception
    {
        File modelConfig = new File(modelMap.get(modelName)+File.separator+"config.json");
        Scanner weightStream = new Scanner(new File(modelMap.get(modelName)+File.separator+"weights.txt"));
        //String modelJson = "src"+File.separator+"modelStore"+ File.separator+""+modelName+".json";
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject)parser.parse(new FileReader(modelConfig));
        CnnModel model = new CnnModel(config, weightStream);
        return model;
    }

}
