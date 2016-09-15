/**
 * Created by Paul on 9/14/2016.
 */
public class DepthFilter implements Filter {

    private double minDepth;
    private double maxDepth;

    public DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getDepth() <= minDepth && qe.getDepth() >= maxDepth);
    }
}
