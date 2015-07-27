package ro.hasna.ts.math.ml.distance;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.hasna.ts.math.normalization.ZNormalizer;
import ro.hasna.ts.math.representation.PiecewiseLinearAggregateApproximation;
import ro.hasna.ts.math.type.MeanSlopePair;
import ro.hasna.ts.math.util.TimeSeriesPrecision;

import java.util.Random;

/**
 * @since 1.0
 */
public class PlaaEuclideanDistanceTest {

    private PlaaEuclideanDistance distance;

    @Before
    public void setUp() throws Exception {
        distance = new PlaaEuclideanDistance(new PiecewiseLinearAggregateApproximation(8));
    }

    @After
    public void tearDown() throws Exception {
        distance = null;
    }

    @Test
    public void testTriangleInequality() throws Exception {
        int n = 128;
        double a[] = new double[n];
        double b[] = new double[n];
        double c[] = new double[n];

        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = n - i;
            c[i] = i * i;
        }

        double ab = distance.compute(a, b);
        double ba = distance.compute(b, a);
        double bc = distance.compute(b, c);
        double ac = distance.compute(a, c);

        Assert.assertEquals(ab, ba, TimeSeriesPrecision.EPSILON);
        Assert.assertTrue(ab + bc >= ac);
    }

    @Test
    public void testEquality() throws Exception {
        int n = 128;
        double a[] = new double[n];
        double b[] = new double[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = i;
            b[i] = 100 + i + random.nextDouble();
        }

        ZNormalizer normalizer = new ZNormalizer();
        double result = distance.compute(normalizer.normalize(a), normalizer.normalize(b));

        Assert.assertEquals(0, result, 0.1);
    }

    @Test
    public void testOverflow() throws Exception {
        MeanSlopePair[] a = new MeanSlopePair[4];
        a[0] = new MeanSlopePair(0, 8);
        a[1] = new MeanSlopePair(4, 8);
        a[2] = new MeanSlopePair(5, 8);
        a[3] = new MeanSlopePair(6, 8);

        MeanSlopePair[] b = new MeanSlopePair[4];
        b[0] = new MeanSlopePair(7, 8);
        b[1] = new MeanSlopePair(2, 4);
        b[2] = new MeanSlopePair(5, 8);
        b[3] = new MeanSlopePair(1, 2);

        double result = distance.compute(a, b, 128, 3);

        Assert.assertEquals(Double.POSITIVE_INFINITY, result, TimeSeriesPrecision.EPSILON);
    }
}