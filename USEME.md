## To run IME:

- Please run the ImageManipulationEnhancement.java class file's main method via the ./res/IME.JAR file. Command options are:
  - `java -jar ./res/IME.JAR` to run the GUI-based version
  - `java -jar ./res/IME.JAR -file <script_file_path>` to run the script-based version
  - `java -jar ./res/IME.JAR -text` to run the real time text-based version
- The example script `script.txt` contains a comprehensive example run of the app's text-based functionality.

## Further info:
### GUI-based version
- To load an image, click on the bottom-center load button and choose an image to edit.
- To save the image, click on the bottom-center save button and choose a file path at which to save it.
- To switch between previously-loaded images to edit, select from the bottom-right dropdown menu.
- To manipulate the image, choose between the operation buttons on the left of the window.

### Text and script based version
A corresponding script file uses the same syntax but simply returns after each command.
- To quit type: `quit` into the command line.
- To run the program with a custom script type: `-file <file_path>` into the command line.
- To load an image type: `load <image-name> <file-path>` into the command line.
- To save an image type: `save <image-name> <file-name>` into the command line.
- To blur an image type `blur <img_former> <img_new>` into the command line.
- To get the greyscale color transform type `greyscale <img-former> <img-new>` into the commandline.
- To get the sepia color transformation type: `sepia <img_former> <img_new>` into the command line.
- To sharpen an image type: `sharpen <img_former> <img_new>` into the command line.
- To change the brightness type: `brightness <image-name> <integer-change> <new-name>`.
- To get a heat map of red in the img type `red <img_former> <img_new>` into the command line.
- To get a heat map of intensity in the img type `intensity <img_former> <img_new>` into the command
  line.
- To flip the img horizontally type `horizontal <img_former> <img_new>` into the command line.
- To get a heat map of green in the img type `green <img_former> <img_new>` into the command line.
- To get a heat map of blue in the img type `blue <img_former> <img_new>` into the command line.
- To flip the img vertically type `vertical <img_former> <img_new>` into the command line.
- To get a heat map of the max value in the img type `value <img_former> <img_new>` into the command
  line.
- To get a heat map of luminosity in the img type `luma <img_former> <img_new>` into the command
  line.