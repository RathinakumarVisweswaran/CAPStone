import java.util.Random;

/**
 * Created by rathinakumar on 11/2/15.
 */
public class RoughDraft {
    public static void main(String[] args) {
        Random r = new Random();
        for(int i=1; i<=32; i++)
            System.out.print(r.nextInt(15)*r.nextFloat() + ",");
    }
}
