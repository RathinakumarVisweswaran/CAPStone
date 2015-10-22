package layers;

import static layers.LayerType.*;

/**
 * Created by Rathinakumar on 10/21/2015.
 */
public class LayerFactory {

    public static Layer getLayerOfType(String type)
    {
        LayerType layerType = valueOf(type.toUpperCase());
        Layer layer;
        switch (layerType)
        {
            case INPUT:
                layer = new InputLayer();
                break;
            case CONVOLUTION:
                layer = new ConvolutionLayer();
                break;
            case SUBSAMPLING:
                layer = new SubsamplingLayer();
                break;
            case FULLYCONNECTED:
                layer = new FullyConnectedLayer();
                break;
            case OUTPUT:
                layer = new OutputLayer();
                break;
            default:
                layer = null;
        }
        return layer;
    }

}
