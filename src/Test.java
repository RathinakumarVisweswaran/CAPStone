import convolution.DataVolume;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by rathinakumar on 11/22/15.
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        DataVolume d = new DataVolume(1,1,3);
        Scanner testData = new Scanner(new File("modelStore//softmaxTest.txt"));
            for(int i=0; i<3; i++)
                d.setElement(0,0,i, testData.nextDouble());
            d.activate("softmax");
            System.out.println(d.toString());
    }
}
