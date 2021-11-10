# Image Manipulation & Enhancement (IME)
## Changes in pt2
- In order to support a script entry to the app, we added a corresponding function class `CommandScript` that converts a given filepath to a readable for the controller.
  - Additional tests for this were written in the controller test class `IMEControllerImplTest`.
- To integrate the new commmands (blur, sharpening, greyscale, and sepia), we further implemented the `ICommands` interface in abstract-class-extending function classes.
  - Tests for the new commands were appended to the function object testing class.
  - The new commands are by default initialized by the model implementation's constructor.
- Additional file formats were added through extension of the Image interface. Instead of inserting an abstract class into the mix, the format similarities and support from teh ImageIO library allowed the PNG and BMP classes to extend the JPG one with minimal add-ons.
  - Conversion between these was tested via script in the controller test class.
  - The workings of each variant of image class was abstractable, and so an abstract image test class `ImageTest` was created to be extended by the file format image implementations' corresponding testing classes. 
  - ImageFactory was implemented and its construction cases populated for each file format to support.
    - It is also utilized in the ImageTest abstract class and its children.

## Code Overview

### Source Code

- **Class ImageManipulationEnhancement**
    - The main class for initializing and running the controller.
- Package _controller_:
    - **Interface IMEController**
        - Interface for controllers in IME.
        - **Class IMEControllerCompact**
          - This is the controller class for IME.
- Package _model_:
    - Package _funcobjs_:
        - **Interface ICommands**
            - An interface for command function objects. Can take in a subclass-decided amount of
              arguments.
            - **Abstract Class ACommandDimension**
                - An abstract class for function objects that work with dimensions.
                - **Class ConvertByHorizontal**
                    - A function object for getting the pixel on the other side of the image
                      horizontally.
                - **Class ConvertByVertical**
                    - A function object for getting the pixel on the other side of the image
                      vertically.
            - **Abstract Class ACommandImageOp**
              - An abstract class for function objects that require the whole image to work.
              - **Class CommandBlur**
                - This function object creates a blurred version of the pixel.
              - **Class CommandSharpen**
                - Function object for sharpening an image pixel by pixel.
              - **Class CommandSepia**
                - Function object that returns the sepia version of a given pixel.
            - **Abstract Class ACommandsAbsolute**
                - An abstract class for function objects that get a single value from pixels.
                - **Class CommandsRed**
                    - A function object for getting the red value of a pixel.
                - **Class CommandsBlue**
                    - A function object for getting the blue value of a pixel.
                - **Class CommandsGreen**
                    - A function object for getting the green value of a pixel.
                - **Class CommandsIntensity**
                    - A function object for getting the intensity of a pixel.
                - **Class CommandsValue**
                    - A function object for getting the value of a pixel.
                - **Class CommandGreyscale**
                  - A function object that returns the greyscale version of a pixel.
            - **Abstract Class ACommandsMultiple**
                - An abstract class for function objects that get multiple values from pixels.
                - **Class CommandsLuma**
                    - A function object for getting the luma of a pixel.
    - **Interface IMEModel**
        - Interface for models used by IME.
        - **Class IMEModelImpl**
            - A representation of an IME model.
    - **Interface Image**
        - Interface for representing images.
        - **Class ImagePPM**
            - Representation of an image from a PPM file.
        - **Class ImageJPG**
          - Representation of an image from a JPG file.
        - **Class ImagePNG**
            - Representation of an image from a PNG file.
        - **Class ImageBMP**
            - Representation of an image from a BMP file.
    - **Interface IPixel**
        - An interface for pixels of an image.
        - **Class Pixel**
            - This class represents an immutable pixel.
        - **Class MockPixel**
            - Simulates a pixel for the more simplistic testing of Image and IMEModel classes.
              Simplifies pixel construction and assertion.
- Package _factory_:
    - **Class IMEModelFactory**
        - Creates a new IMEModel implementation object.
- Package _view_:
    - **Interface IMEView**
        - Interface for rendering messages and possibly eventually for displaying images.
        - **Class IMEViewImpl**
          - Implementation of an image viewer.

### Test Code

- Package _controller_:
    - **Class IMEControllerImplTest**
      - Test class for the controller implementation.
- Package _model_:
    - **Abstract Class ImageTest**
      - **Class ImagePPMTest**
        - Tests ImagePPM class's utility methods. Also checks that null cannot be input to methods,
          including the constructor.
      - **Class ImageJPGTest**
        - Tests ImageJPG class's utility methods. Also checks that null cannot be input to methods,
          including the constructor.
      - **Class ImagePNGTest**
          - Tests ImagePNG class's utility methods. Also checks that null cannot be input to methods,
            including the constructor.
      - **Class ImageBMPTest**
          - Tests ImageBMP class's utility methods. Also checks that null cannot be input to methods,
            including the constructor.
    - **Class FunctionObjectTests**
        - Test class for commands' function objects.
    - **Class IMEModelTest**
      - Tests IMEModelImpl class using standard assertions as well as multiple method calls in a
      single test method. Uses mock pixels for instantiating an image.
    - **Class PixelTest**
      - Employs a variety of looping and hashmap-factory methods to automate similar calls and
      simplify assertions, respectively. Tests the Pixel class.
    - **Class MockPixel**
      - Simulates a pixel for the more simplistic testing of Image and IMEModel classes. Simplifies
      pixel construction and assertion.
- Package _view_:
    - **Class IMEViewImplTest**
      - Tests the view, which takes in or has a default list of commands that give the menu. The
      view also allows system messaging whose string appends are tested.
    - **Class InvalidMockAppendable**
      - This is a custom-made mock for an appendable passed in during construction of a view. Its
      only use, at the moment, is that it throws an exception when its append method is called.

## Image License

Title: "Goat Mammal Cute Animal Domestic Farm Funny"

Author: [Max Pixel](https://www.maxpixel.net/)

Source: https://www.maxpixel.net/Goat-Mammal-Cute-Animal-Domestic-Farm-Funny-3471349

License: CC BY 2.0
