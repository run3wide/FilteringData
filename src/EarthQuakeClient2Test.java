import static org.junit.Assert.*;

/**
 * Created by Paul on 9/14/2016.
 */
public class EarthQuakeClient2Test {
    private EarthQuakeClient2 tester;

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void quakesWithFilter() throws Exception {
        EarthQuakeClient2 tester = new EarthQuakeClient2();
        //tester.quakesWithFilter();
        tester.testMatchAllFilter2();

    }

}