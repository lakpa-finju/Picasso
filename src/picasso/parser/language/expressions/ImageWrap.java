/**
 * 
 */
package picasso.parser.language.expressions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import picasso.model.Pixmap;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents ImageWrap function in the Picasso language
 * @author lakpafinjusherpa
 */
public class ImageWrap extends ExpressionTreeNode{
	public static final Dimension DEFAULT_SIZE = new Dimension(300, 300);
	public static final Color DEFAULT_COLOR = Color.BLACK;
	private String fileName;
	ExpressionTreeNode leftETN;
	ExpressionTreeNode rightETN;
	
	private BufferedImage myImage;
	private Dimension mySize;
	

	/**
	 * Creates the ImageWrap Expression that takes as a parameter the imageName, given expression tree node for the 
	 * both left Expression Tree node and right Expression tree node. 
	 * @param fileName the name of the image file
	 * @param leftETN left expression tree node
	 * @param rightETN right expression tree node
	 */
	public ImageWrap(String fileName, ExpressionTreeNode leftETN, ExpressionTreeNode rightETN) {
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		this.leftETN = leftETN;
		this.rightETN = rightETN;
		read(fileName);
		
	}
	/**
	 * Read the image named by fileName
	 * 
	 * @param fileName the name of the image file to be read in
	 */
	public void read(String fileName) {
		try {
			this.fileName = fileName;
			myImage = ImageIO.read(new File(this.fileName));
			mySize = new Dimension(myImage.getWidth(), myImage.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Dimension getSize() {
		return new Dimension(mySize);
	}
	
	/**
	 * Returns the color of the pixel at the given (x,y) coordinate if the
	 * coordinate is within the bounds of the image; otherwise returns the default
	 * color
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the color of the pixel at the given (x,y) coordinate if the
	 *         coordinate is within the bounds of the image; otherwise returns the
	 *         default color
	 */
	public Color getColor(int x, int y) {
		if (isInBounds(x, y))
			return new Color(myImage.getRGB(x, y));
		else
			return DEFAULT_COLOR;
	}

	public void setColor(int x, int y, Color value) {
		if (isInBounds(x, y)) {
			myImage.setRGB(x, y, value.getRGB());
		}
	}
	
	/**
	 * Determine if the given (x,y) coordinate is within the bounds of this image.
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if the given (x,y) coordinate is within the bounds of this
	 *         image.
	 */
	public boolean isInBounds(int x, int y) {
		return (0 <= x && x < mySize.width) && (0 <= y && y < mySize.height);
	}
	

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		RGBColor leftETN = this.leftETN.evaluate(x, y);
		RGBColor rightETN = this.rightETN.evaluate(x, y);
		
		return null;
	}

}
