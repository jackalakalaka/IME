package view;

import java.util.HashMap;

import model.IPixel;
import model.Image;

public abstract class AHistogram implements IHistogram {
  protected HashMap<Integer,Integer> counts;

  public AHistogram(Image img) {
    counts = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      counts.put(i,0);
    }
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int value = this.fillHistogram(img,i,j);
        int oldValue = this.counts.get(value);
        this.counts.put(value,oldValue+1);
      }
    }
  }

  protected abstract int fillHistogram(Image image, int row, int col);

}
