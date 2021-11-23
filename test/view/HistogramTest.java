package view;

import org.junit.Test;

/**
 * Tests histogram construction inputs and provides several example instantiations.
 */
public class HistogramTest {

  /**
   * Initialize examples.
   */
  public HistogramTest() {
    Histogram hist1 = new Histogram(0, 0);
    Histogram hist2 = new Histogram(100, 100);
    Histogram hist3 = new Histogram(300, 200);
  }

  /**
   * Test invalid xDim to constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorInvalidXDim() {
    Histogram hist = new Histogram(-1, 0);
  }

  /**
   * Test invalid yDim to constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorInvalidYDim() {
    Histogram hist = new Histogram(0, -1);
  }
}
