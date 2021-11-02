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

## Source Code Overview
A text README file explaining your design. Your README file should give the graders an overview of what the purposes are for every class, interface, etc. that you include in your submission, so that they can quickly get a high-level overview of your code. It does not replace the need for proper Javadoc!

## Image License
Title: "Goat Mammal Cute Animal Domestic Farm Funny"

Author: [Max Pixel](https://www.maxpixel.net/)

Source: https://www.maxpixel.net/Goat-Mammal-Cute-Animal-Domestic-Farm-Funny-3471349

License: CC BY 2.0
