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
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.StringToken;

/**
 * Represents ImageWrap function in the Picasso language
 * @author lakpafinjusherpa
 * @author Liz Kent
 */
public class ImageWrap extends ExpressionTreeNode{
	public static final Dimension DEFAULT_SIZE = new Dimension(300, 300);
	//public static final Color DEFAULT_COLOR = Color.BLACK;
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	private Image image;
	ExpressionTreeNode xCoordinateETN;
	ExpressionTreeNode yCoordinateETN;
	
	private BufferedImage myImage;
	private Dimension mySize;
	

	/**
	 * Creates the ImageWrap Expression that takes as a parameter the imageName, given expression tree node for the 
	 * both left Expression Tree node and right Expression tree node. 
	 * @param fileName the name of the image file
	 * @param leftETN left expression tree node
	 * @param rightETN right expression tree node
	 */
	public ImageWrap(StringToken fileName, ExpressionTreeNode leftETN, ExpressionTreeNode rightETN) {
		this.image = new Image((String)fileName.value());
		this.xCoordinateETN = leftETN;
		this.yCoordinateETN = rightETN;
		
	}
	

	@Override
	public RGBColor evaluate(double x, double y) {
		//evaluaete x and y
		//just use x and Y 
		RGBColor xCoordinateColor = xCoordinateETN.evaluate(x, y);
		RGBColor yCoordinateColor = yCoordinateETN.evaluate(x, y);
		//need to wrap here
		double redX = wrap(xCoordinateColor.getRed(), -1,1);
		double redY = wrap(yCoordinateColor.getRed()
				,-1,1);
		int imageX = image.domainScaleToImageX(redX);
		int imageY = image.domainScaleToImageY(redY);
		Color imageColor = image.getColor(imageX, imageY);
		
		
		return new RGBColor(imageColor);
	}

	/**
	 * A wrapper function for doubles. 
	 * Specs: takes any number not within upperBound and
	 * lowerBound, removes the amount over the 
	 * limit in either direction and returns a number within range.
	 * Ex. For range -1.0 to 1.0, input of -1.25 should return .75. 1.5 should 
	 * return -.5.
	 * 
	 * @param n number to be wrapped
	 * @param lowerBound lower bound of range, inclusive
	 * @param upperBound upper bound of range, inclusive
	 */
	public double wrap(double n, double lowerBound, double upperBound){
		return (n - lowerBound) % (upperBound - lowerBound) + lowerBound;
	}

	/**
	 * A wrapper function for RGBColors. 
	 * See wrap function for doubles for how numbers should wrap
	 * 
	 * @param input RGBColor to be wrapped
	 * @param lowerBound lower bound of range, inclusive
	 * @param upperBound upper bound of range, inclusive
	 */
	public RGBColor wrap(RGBColor input, double lowerBound, double upperBound){
		double red = input.getRed();
		double green = input.getGreen();
		double blue = input.getBlue();

		red = wrap(red, -1.0, 1.0);
		green = wrap(green, -1.0, 1.0);
		blue = wrap(blue, -1.0, 1.0);

		return new RGBColor(red, green, blue);

	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageWrap)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		return true;

	}

}
