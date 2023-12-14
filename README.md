[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/1EiKHzOV)
# Team member
Lakpa Sherpa, Linh Nguyen, Liz Kent, Trey Custodio

# Picasso

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

The given code base is a good start, but it is sparsely documented
(document each method/part after you understand what it's doing) and,
as your application grows, you may need to refactor.

See the specification for Picasso on the course web site.

## Running Picasso

To run Picasso, run `picasso.Main`

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

The `images` directory contains some sample images generated from Picasso.  Some of the expressions for these images can be found in the `expressions` directory.

## Code Base History

This code base originated as a project in a course at Duke University.  The professors realized that the code could be designed better and refactored.  This code base has some code leftover from the original version.

## Extensions

### Multiple Image View
This extension allows user to view multiple images at once across tab. The extension utilizes Jtabbed pane and Jpanel. To use this extension, the user should do the following in order.
1. Click on `multi images`, once file chooser has been opened, click on `images` directory. 
2. Select multiple images that you like to open. 
3. Click `open` or press enter
4. Selected images will be opened in different tabs. 

### View History
This extension allows user to see the list of the expression they entered in the program. The extension uses JTextArea, JScrollPane, and JPanel. If the expression is incorrect, it will not be saved to the history. 
1. Type the expression in the text field as usual
2. Hit "evaluate", and the expression will be automatically saved to the history panel on the right side.

### FRACTALS
## Mandelbrot Fractal
This part of the extension allows the user to generate an image of the Mandelbrot set.
Here is a link to a good explanation of the Mandelbrot fractal: 

https://youtu.be/NGMRB4O922I?si=UDExcgQ0KO0-CVJk

To use this extension, type **mandelbrot** into the text box. This expression accepts no arguments. An image of the Mandelbrot fractal will appear!

![mandelbrotfinal](https://github.com/WLU-CSCI209-F23/picasso-wizards/assets/78395279/bb1f0dda-7ae7-44aa-b27c-81eec369be2f)

## Julia Fractals
This part of the extension allows the user to view custom filled Julia set fractals. 
Here is a link explaining the Julia sets, as well as how they are related to the Mandelbrot set: 

https://youtu.be/oCkQ7WK7vuY?si=JH3NjD_hgTyFSEPh

To use this extension, type **julia("real", "imaginary")** into the text box.
The value that the user can vary is a complex number, so each string represents the real and imaginary portions respectively.
The values passed to julia() **MUST** be strings or the program will be unable to parse the expression.
Watch your fractal appear!
Here are some interesting values of c to try:

("-0.4", "0.6")

("-0.8", "0.156")

("-0.7269", "0.1889")

("0.285", "0.01")

![julia156](https://github.com/WLU-CSCI209-F23/picasso-wizards/assets/78395279/5da1e5a5-9c77-4856-9685-91b5be7112da)
