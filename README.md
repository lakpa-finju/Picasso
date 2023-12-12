[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/1EiKHzOV)
# Team member
Lakpa Sherpa, Linh Nguyen, Elizabeth Kent, Trey Custodio

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

## Extension 

### Multiple Image View
This extension allows user to view multiple images at once across tab. The extension utilizes Jtabbed pane and Jpanel. To use this extensino the user should do the following in order.
1. Click on `multi images`, once file chooser has been opened, click on `images` directory. 
2. Select multiple images that you like to open. 
3. Click `open` or press enter
4. Selected images will be opened in different tabs. 

### View history
This extension allows user to see the list of the expression they entered in the program. The extension uses JTextArea, JScrollPane, and JPanel. If the expression is incorrect, it will not be saved to the history. 
1. Type the expression in the text field as usual
2. Hit "evaluate", and the expression will be automatically saved to the history panel on the right side
