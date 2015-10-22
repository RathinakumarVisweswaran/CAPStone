package layers;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public enum LayerType {
    INPUT("input"),
    CONVOLUTION("convolution"),
    SUBSAMPLING("subSampling"),
    FULLYCONNECTED("fullyConnected"),
    OUTPUT("output");

    private String type;

    LayerType(String type) {
        this.type = type;
    }
}
