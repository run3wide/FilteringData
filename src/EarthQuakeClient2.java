import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");



        /*Filter magFilter = new MagnitudeFilter(4.0, 5.0);
        Filter depthfilter = new DepthFilter(-12000, -35000);*/
        Location japan = new Location(35.42, 139.43);
        Filter phraseFilter = new PhraseFilter("end", "Japan");
        Filter distanceFilter = new DistanceFilter(japan, 10000000);

        ArrayList<QuakeEntry> m7  = filter(list, phraseFilter);
        m7 = filter(m7, distanceFilter);
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void testMatchAllFilter(){
        String source = "data/nov20quakedatasmall.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakes = new ArrayList<>();
        quakes = parser.read(source);
        System.out.println(quakes.size() + " quakes read in");

       /* for (QuakeEntry quake : quakes) {
            System.out.println(quake.toString());
        }*/


        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 2.0));
        maf.addFilter(new DepthFilter(-10000.0, -100000.0));
        maf.addFilter(new PhraseFilter("any", "a"));

        quakes = filter(quakes, maf);
        for (QuakeEntry quake : quakes) {
            System.out.println(quake);
        }






    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
