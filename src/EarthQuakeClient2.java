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
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");



        Filter magFilter = new MagnitudeFilter(3.5, 4.5);
        Filter depthfilter = new DepthFilter(-20000, -55000);

        /*Location denver = new Location(39.7392, -104.9903);
        Filter phraseFilter = new PhraseFilter("end", "a");
        Filter distanceFilter = new DistanceFilter(denver, 1000000);*/

        ArrayList<QuakeEntry> m7  = filter(list, magFilter);
        m7 = filter(m7, depthfilter);
        System.out.println("There are " + m7.size() + " quakes found with the filters");
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
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakes = new ArrayList<>();
        quakes = parser.read(source);
        System.out.println(quakes.size() + " quakes read in");

       /* for (QuakeEntry quake : quakes) {
            System.out.println(quake.toString());
        }*/


        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-30000.0, -180000.0));
        maf.addFilter(new PhraseFilter("any", "o"));

        quakes = filter(quakes, maf);
        System.out.println("There were " + quakes.size() + " quakes that fit the filters");
       /* for (QuakeEntry quake : quakes) {
            System.out.println(quake);
        }*/
    }

    public void testMatchAllFilter2(){
        String source = "data/nov20quakedata.atom";
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakes = new ArrayList<>();
        quakes = parser.read(source);
        System.out.println(quakes.size() + " quakes read in");
        Location billund = new Location(55.7308, 9.1153);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new DistanceFilter(billund, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));

        quakes = filter(quakes, maf);
        System.out.println("There were " + quakes.size() + " quakes that fit the filters");
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
