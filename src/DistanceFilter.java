/**
 * Created by Paul on 9/14/2016.
 */
public class DistanceFilter implements Filter {

    private Location location;
    private double maxDistance;

    public DistanceFilter(Location loc, double max){
        location = loc;
        maxDistance = max;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }
}
