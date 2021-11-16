package view;

import java.io.File;
import java.util.List;

/**
 * Created by vidojemihajlovikj on 11/5/21.
 */
public interface ViewListener {
  void loadFileEvent(String name);
  void saveFileEvent(String name);
  void selectImageEvent(String name);
  List<String> getImageNames();
}
