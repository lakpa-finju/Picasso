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
	private String fileName;
	private ExpressionTreeNode leftETN;
	private ExpressionTreeNode rightETN;
	
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
	

	@Override
	public RGBColor evaluate(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

}
