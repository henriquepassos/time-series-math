/**
 * Copyright (C) 2015 Octavian Hasna
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ro.hasna.ts.math.exception;

import org.junit.Assert;
import org.junit.Test;
import ro.hasna.ts.math.representation.PiecewiseLinearAggregateApproximation;
import ro.hasna.ts.math.representation.util.SegmentationStrategy;

/**
 * @since 1.0
 */
public class UnsupportedStrategyExceptionTest {

    @Test
    public void testGetters() throws Exception {
        try {
            new PiecewiseLinearAggregateApproximation(4, SegmentationStrategy.FRACTIONAL_PARTITION);
        } catch (UnsupportedStrategyException e) {
            Assert.assertEquals("FRACTIONAL_PARTITION", e.getStrategy());
            Assert.assertEquals("PLAA", e.getAlgorithm());
            Assert.assertEquals("the strategy FRACTIONAL_PARTITION is not supported in PLAA", e.getMessage());
        }
    }
}