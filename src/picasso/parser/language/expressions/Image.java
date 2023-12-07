/**
 * 
 */
package picasso.parser.language.expressions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;

/**
 * This class represents String class in Picasso
 */
public class Image extends ExpressionTreeNode {

	private String value;
	public static final Dimension DEFAULT_SIZE = new Dimension(300, 300);
	public static final Color DEFAULT_COLOR = Color.BLACK;
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	public static final int BOUNDS = 2;

	ExpressionTreeNode xCoordinateETN;
	ExpressionTreeNode yCoordinateETN;
	
	private BufferedImage myImage;
	private Dimension mySize;

	/**
	 * Creates a new String class in a picasso
	 */
	public Image(String val) {
		if (value == null) {
			throw new IllegalArgumentException("String is null, make sure to enter something");
		}
		value = val;
		read(value);
	}

	
	/**
	 * Read the image named by fileName
	 * 
	 * @param fileName the name of the image file to be read in
	 */
	public void read(String fileName) {
		try {
			myImage = ImageIO.read(new File(fileName));
			mySize = new Dimension(myImage.getWidth(), myImage.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public Dimension getSize() {
		return new Dimension(mySize);
	}
	/**
	 * Convert from image space to domain space.
	 */
	protected int domainScaleToImage(double value) {
		double range = 255-0;
		return (int) ((value - (-1)) * range) / BOUNDS;
	}

	/**
	 * Returns the value of the String
	 * 
	 * @return the value of the String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns the String as a string formatted as "String: <value>"
	 */
	@Override
	public String toString() {
		return "String: " + value;
	}

	@Override
	public RGBColor evaluate(double x, double y) {
		// return new RGBColor(x, x);
		// ask professor about this

		int imageX = domainScaleToImage(x);
		int imageY = domainScaleToImage(y);
		Color imageColor = getColor(imageX, imageY);

		return new RGBColor(imageColor);
	}//

}
