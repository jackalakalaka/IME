# Image Manipulation & Enhancement (IME)
## To run IME:
- Please run the ImageManipulationEnhancement.java class file's main method, with no arguments.
## Commands for using IME:
- To quit type: `quit` into the command line.
- To load an image type: `load <image-name> <file-path>` into the command line.
- To save an image type: `save <image-name> <file-name>` into the command line.
- To change the brightness type: `brightness <image-name> <integer-change> <new-name>`.
- To get a heat map of red in the img type `red <img_former> <img_new>` into the command line.
- To get a heat map of intensity in the img type `intensity <img_former> <img_new>` into the command line.
- To flip the img horizontally type `horizontal <img_former> <img_new>` into the command line.
- To get a heat map of green in the img type `green <img_former> <img_new>` into the command line.
- To get a heat map of blue in the img type `blue <img_former> <img_new>` into the command line.
- To flip the img vertically type `vertical <img_former> <img_new>` into the command line.
- To get a heat map of the max value in the img type `value <img_former> <img_new>` into the command line.
- To get a heat map of luminosity in the img type `luma <img_former> <img_new>` into the command line.

Example script:
```bash
load dope_goat dope_goat.ppm

brightness dope_goat 50 dope_goat-brighter
red dope_goat dope_goat-redComponent
vertical dope_goat dope_goat-vertFlip

save dope_goat-brighter dope_goat-brighter.ppm
save dope_goat-redComponent dope_goat-redComponent.ppm
save dope_goat-vertFlip dope_goat-vertFlip.ppm
```

## Code Overview
### Source Code
- **Class ImageManipulationEnhancement**
  - The main class for running the controller.
  - Package _model_:
    - Package _funcobjs_:
      - **Interface ICommands**
        - An interface for command function objects. Can take in a subclass-decided amount of arguments.
        - **Abstract Class ACommandDimension**
          - An abstract class for function objects that work with dimensions.
        - **Abstract Class ACommandsAbsolute**
          - An abstract class for function objects that get a single value from pixels.
        - **Class MockPixel**
          - Simulates a pixel for the more simplistic testing of Image and IMEModel classes. Simplifies pixel construction and assertion.
    - **Interface IMEModel**
      - Interface for models used by IME.
      - **Class IMEModelImpl**
        - A representation of an IME model.
    - **Interface Image**
      - Interface for representing images.
      - **Class ImagePpm**
        - Representation of an image from a PPM file.
    - **Interface IPixel**
      - An interface for pixels of an image.
      - **Class Pixel**
        - This class represents an immutable pixel.
      - **Class MockPixel**
        - Simulates a pixel for the more simplistic testing of Image and IMEModel classes. Simplifies pixel construction and assertion.
### Test Code
## Image License
Title: "Goat Mammal Cute Animal Domestic Farm Funny"

Author: [Max Pixel](https://www.maxpixel.net/)

Source: https://www.maxpixel.net/Goat-Mammal-Cute-Animal-Domestic-Farm-Funny-3471349

License: CC BY 2.0
