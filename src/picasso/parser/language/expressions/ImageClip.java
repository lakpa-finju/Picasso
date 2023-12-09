/**
 * 
 */
package picasso.parser.language.expressions;
import java.awt.Dimension;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.StringToken;

/**
 * Represents ImageWrap function in the Picasso language
 * @author lakpafinjusherpa
 * @author Liz Kent
 */
public class ImageClip extends ExpressionTreeNode{
	public static final Dimension DEFAULT_SIZE = new Dimension(300, 300);
	//public static final Color DEFAULT_COLOR = Color.BLACK;
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	private Image image;
	ExpressionTreeNode xCoordinateETN;
	ExpressionTreeNode yCoordinateETN;
	
	/**
	 * Creates the ImageWrap Expression that takes as a parameter the imageName, given expression tree node for the 
	 * both left Expression Tree node and right Expression tree node. 
	 * @param fileName the name of the image file
	 * @param leftETN left expression tree node
	 * @param rightETN right expression tree node
	 */
	public ImageClip(StringToken fileName, ExpressionTreeNode leftETN, ExpressionTreeNode rightETN) {
		this.image = new Image((String)fileName.value());
		this.xCoordinateETN = leftETN;
		this.yCoordinateETN = rightETN;
		
	}
	

	@Override
	public RGBColor evaluate(double x, double y) {
		//evaluate x and y
		//just use x and Y 
		RGBColor xCoordinateColor = xCoordinateETN.evaluate(x, y);
		RGBColor yCoordinateColor = yCoordinateETN.evaluate(x, y);
		//need to wrap here
		
		double redX = RGBColor.clamp(xCoordinateColor.getRed());
		double redY = RGBColor.clamp(yCoordinateColor.getRed());

		return image.evaluate(redX, redY);
	}

	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageClip)) {
			return false;
		}

		// Make sure the objects are the same type
		if (o.getClass() != this.getClass()) {
			return false;
		}

		return true;

	}

}

