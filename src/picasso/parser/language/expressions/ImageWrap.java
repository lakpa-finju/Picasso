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
 */
public class ImageWrap extends ExpressionTreeNode{
	public static final Dimension DEFAULT_SIZE = new Dimension(300, 300);
	public static final Color DEFAULT_COLOR = Color.BLACK;
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
		this.image = new Image(fileName.value());
		this.xCoordinateETN = leftETN;
		this.yCoordinateETN = rightETN;
		
	}
	

	@Override
	public RGBColor evaluate(double x, double y) {
		//evaluaete x and y
		//just use x and Y 
		RGBColor xCoordinateColor = xCoordinateETN.evaluate(x, y);
		RGBColor yCoordinateColor = xCoordinateETN.evaluate(x, y);
		//need to wrap here
		double redX = xCoordinateColor.getRed();
		double redY = yCoordinateColor.getRed();
		int imageX = image.domainScaleToImage(redX);

		int imageY = image.domainScaleToImage(redY);
		Color imageColor = image.getColor(imageX, imageY);
		
		
		return new RGBColor(imageColor);
	}

}
