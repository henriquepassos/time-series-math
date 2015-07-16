package ro.hasna.ts.math.representation.distance;

import org.apache.commons.math3.util.Precision;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.hasna.ts.math.representation.SymbolicAggregateApproximation;

import java.util.Random;

/**
 * @since 1.0
 */
public class SAXEuclideanDistanceTest {
    private SAXEuclideanDistance distance;

    @Before
    public void setUp() throws Exception {
        distance = new SAXEuclideanDistance(new SymbolicAggregateApproximation(8, 3));
    }

    @After
    public void tearDown() throws Exception {
        distance = null;
    }

    @Test
    public void testCompute1() throws Exception {
        int n = 128;
        double a[] = new double[n];
        double b[] = new double[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = n - i;
        }

        double result = distance.compute(a, b);

        Assert.assertEquals(8.440496812487385, result, Precision.EPSILON);
    }

    @Test
    public void testCompute2() throws Exception {
        int n = 128;
        double a[] = new double[n];
        double b[] = new double[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = 100 + i + random.nextDouble();
        }

        double result = distance.compute(a, b);

        Assert.assertEquals(0, result, Precision.EPSILON);
    }
}