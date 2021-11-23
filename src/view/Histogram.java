package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.Arrays;
import javax.swing.JPanel;
import model.image.IPixel;
import model.image.Image;

/**
 * Immutable histogram panel.
 */
public class Histogram extends JPanel {
  private int[] valFreqs;
  private IPixel.Color component; // null if intensity histogram
  private Image img;
  private final int xDim;
  private final int yDim;

  /**
   * Constructs a histogram placeholder.
   */
  public Histogram(int xDim, int yDim) {
    if (xDim < 0 || yDim < 0) {
      throw new IllegalArgumentException("Histogram dimensions cannot be negative.");
    }

    this.valFreqs = new int[0];
    this.xDim = xDim;
    this.yDim = yDim;
    this.setPreferredSize(new Dimension(this.xDim, this.yDim));
  }

  /**
   * Sets given image and the desired color component.
   * @param img image to analyze
   * @param component color component or null for intensity analysis
   */
  public void setImgAndComponent(Image img, IPixel.Color component) {
    this.component = component;
    this.img = img;
    this.valFreqs = new int[this.img.getMaxValue() + 1];

    // Count component value occurrences.
    for (int row = 0; row < img.getHeight(); row += 1) {
      for (int col = 0; col < img.getWidth(); col += 1) {
        this.valFreqs[this.getComponentVal(row, col)] += 1;
      }
    }
  }

  /**
   * Get pixel value depending on component type.
   * @param row pixel row
   * @param col pixel column
   * @return component value of pixel
   */
  private int getComponentVal(int row, int col) {
    if (this.component == null) {
      return this.img.getPixelAt(row, col).getIntensity();
    } else {
      return this.img.getPixelAt(row, col).getColors().get(this.component);
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    // Setup.
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.WHITE);
    g2.draw(new Rectangle(0, 0, this.xDim, this.yDim));

    // Max component value.
    int valRange = this.valFreqs.length == 0 ? 0 : this.valFreqs.length - 1;
    // Component value scaling for rendering.
    double valScale = valRange == 0 ? 0 : (double) this.xDim / (double) valRange;
    // Max freq value. Zero if value frequencies array is currently unpopulated.
    int[] tempSorted = Arrays.copyOf(this.valFreqs, valRange);
    Arrays.sort(tempSorted);
    int freqRange = valRange == 0 ? 0 : tempSorted[valRange - 1];
    // Freq value scaling for rendering.
    double freqScale = (double) this.yDim / (double) freqRange;

    // Drawing config
    int ptLen = 2;
    Stroke ptStroke = g2.getStroke();
    Stroke lnStroke = new BasicStroke(2f);
    Color plotColor;
    if (this.component == null) {
      plotColor = Color.BLACK;
    } else {
      switch (this.component) {
        case Red:
          plotColor = Color.RED;
          break;
        case Green:
          plotColor = Color.GREEN;
          break;
        case Blue:
          plotColor = Color.BLUE;
          break;
        default:
          throw new IllegalStateException("Component should either be a pixel color or null.");
      }
    }
    g2.setColor(Color.BLACK);
    g2.setStroke(lnStroke);
    g2.drawLine(0, this.yDim, this.xDim, this.yDim); // Draw x axis.
    g2.drawString(String.format("Component value: 0-%1$s", valRange),
            (int) ((double) this.xDim * 0.45),
            (int) ((double) this.yDim * 0.975)); // Draw x label.
    g2.drawLine(0, this.yDim, 0, 0); // Draw y axis.
    g2.drawString(String.format("Frequency: 0-%1$s", freqRange),
            (int) ((double) this.xDim * 0.025),
            (int) ((double) this.yDim * 0.1)); // Draw y label.

    // Draw plot.
    for (int val = 0; val < valRange; val += 1) {
      int valFlipped = valRange - val;
      int x = (int) ((double) valFlipped * valScale);
      int freqFlipped = freqRange - this.valFreqs[val];
      int y = (int) ((double) freqFlipped * freqScale);

      // Points.
      g2.setColor(plotColor);
      g2.setStroke(ptStroke);
      g2.fillOval(x, y, ptLen, ptLen);

      // Line segments.
      if (val < valRange - 1) {
        g2.setColor(plotColor);
        g2.setStroke(lnStroke);
        int val2Flipped = valRange - (val + 1);
        int x2 = (int) ((double) val2Flipped * valScale);
        int freq2Flipped = freqRange - this.valFreqs[val + 1];
        int y2 = (int) ((double) freq2Flipped * freqScale);
        g2.drawLine(x, y, x2, y2);
      }
    }
  }

}
