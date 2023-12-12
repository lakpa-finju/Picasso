/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.model.ImprovedNoise;
import picasso.parser.WrapAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.StringToken;

/**
 * Tests of the evaluation of expression trees
 * 
 * @author Sara Sprenkle
 * @author Liz Kent
 * 
 */
public class EvaluatorTests {

	private final static double ERROR_TOLERANCE = .0001;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testConstantEvaluation() {
		ExpressionTreeNode e = new RGBColor(1, -1, 1);
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(1, -1, 1), e.evaluate(i, i));
		}
	}

	@Test
	public void testXEvaluation() {
		X x = new X();
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), x.evaluate(i, i));
		}
	}

	@Test
	public void testFloorEvaluation() {
		Floor myTree = new Floor(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double floorOfTestVal = Math.floor(testVal);
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(floorOfTestVal, floorOfTestVal, floorOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testCeilEvaluation() {
		Ceil myTree = new Ceil(new X());

		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(.999, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double ceilOfTestVal = Math.ceil(testVal);
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal), myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testClampEvaluation() {
		Clamp myTree = new Clamp(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0.4, 0.4, 0.4), myTree.evaluate(0.4, -1));
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1.1, -1));
		assertEquals(new RGBColor(-1, -1, -1), myTree.evaluate(-1.1, -1));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i, i, i), myTree.evaluate(i, i));
		}

		double[] tests = { -2, -.00001, .000001, 2 };

		for (double testVal : tests) {
			double clampOfTestVal = Math.max(RGBColor.COLOR_MIN, Math.min(RGBColor.COLOR_MAX, testVal));
			assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(clampOfTestVal, clampOfTestVal, clampOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testAbsEvaluation() {
		Abs myTree = new Abs(new X());

		// some straightforward tests
		assertEquals(new RGBColor(.4, .4, .4), myTree.evaluate(.4, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		assertEquals(new RGBColor(.7, .7, .7), myTree.evaluate(-.7, -1));

		// test the ints; remember that y's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double absOfTestVal = Math.abs(testVal);
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, testVal));
		}

	}

	@Test
	public void testAbsEvaluationwithY() {
		Abs myTree = new Abs(new Y());

		// some straightforward tests
		assertEquals(new RGBColor(.4, .4, .4), myTree.evaluate(-1, .4));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(-1, 0));
		assertEquals(new RGBColor(.7, .7, .7), myTree.evaluate(-1, -.7));

		// test the ints; remember that y's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double absOfTestVal = Math.abs(testVal);
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(-1, testVal));
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal), myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testSineEvaluation() {
		Sin myTree = new Sin(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		// common sin value - pi/4
		assertEquals(new RGBColor(0.706825181105366, 0.706825181105366, 0.706825181105366), myTree.evaluate(.785, -1));
		assertEquals(new RGBColor(-0.9999996829318346, -0.9999996829318346, -0.9999996829318346),
				myTree.evaluate(-1.57, -1));

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double sinOfTestVal = Math.sin(testVal);
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal), myTree.evaluate(testVal, testVal));
		}
	}

	@Test
	public void testAdditionEvaluation() {

		Addition myTree = new Addition(new X(), new Y());

		// some straightforward tests
		// some straightforward tests
		assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, 1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, 0));

		// test the ints; remember that y's value doesn't matter
		for (int i = -1; i <= 1; i++) {
			assertEquals(new RGBColor(i - i, i - i, i - i), myTree.evaluate(i, -i));
			assertEquals(new RGBColor(i + i, i + i, i + i), myTree.evaluate(i, i));
		}

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			assertEquals(new RGBColor(testVal - 1, testVal - 1, testVal - 1), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(testVal + 1, testVal + 1, testVal + 1), myTree.evaluate(testVal, 1));
		}
	}

	@Test
	public void testImageWrapEvaluation() {
		ImageWrap myTree = new ImageWrap(new StringToken("foo.jpg"), new X(), new Y());
		Image image = new Image("foo.jpg");
		Color imageColor = image.getColor(0,0);
		RGBColor color = new RGBColor(imageColor);
		assertEquals(color.getRed(), myTree.evaluate(-1,-1).getRed());
		assertEquals(color.getBlue(), myTree.evaluate(-1,-1).getBlue());
		assertEquals(color.getGreen(), myTree.evaluate(-1,-1).getGreen());


	}

	@Test
	public void testAssignmentEvaluationwithAbs() {
//		Assignment assignmentValue = new Assignment(new Variable("a"), new Addition(new X(), new Y())); 
//		Abs myTree = new Abs(assignmentValue); 		
//		
//		// some straightforward tests
//		// some straightforward tests
//				assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, 1));
//				assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(1, -1));
//				assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0,0));
//
////				// test the ints; remember that y's value doesn't matter
////				for (int i = -1; i <= 1; i++) {
////					assertEquals(new RGBColor(i-i, i-i, i-i), myTree.evaluate(i, -i));
////					assertEquals(new RGBColor(i+i, i+i, i+i), myTree.evaluate(i, i));
////				}
////
////				double[] tests = { -.7, -.00001, .000001, .5 };
////
////				for (double testVal : tests) {
////					assertEquals(new RGBColor(testVal-1, testVal-1, testVal-1), myTree.evaluate(testVal, -1));
////					assertEquals(new RGBColor(testVal+1, testVal+1, testVal+1),
////							myTree.evaluate(testVal, 1));
////				}
	}

	@Test
	public void testDivisionEvaluation() {
		Division newTree = new Division(new X(), new Y());
		// some straightforward tests
		// checks divide by 0
		assertEquals(new RGBColor(0, 0, 0), newTree.evaluate(0, 1));
		assertEquals(new RGBColor(-1, -1, -1), newTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), newTree.evaluate(0, 0));

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			assertEquals(new RGBColor(testVal / 2.0, testVal / 2.0, testVal / 2.0), newTree.evaluate(testVal, 2.0));
			assertEquals(new RGBColor(testVal / 1.5, testVal / 1.5, testVal / 1.5), newTree.evaluate(testVal, 1.5));
		}
	}

	@Test
	public void testModEvaluation() {
		Modulo newTree = new Modulo(new X(), new Y());
		// some straightforward tests
		// checks mod by 0
		assertEquals(new RGBColor(0, 0, 0), newTree.evaluate(0, 1));
		assertEquals(new RGBColor(0, 0, 0), newTree.evaluate(1, -1));
		assertEquals(new RGBColor(0, 0, 0), newTree.evaluate(0, 0));

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			assertEquals(new RGBColor(testVal % 2.0, testVal % 2.0, testVal % 2.0), newTree.evaluate(testVal, 2.0));
			assertEquals(new RGBColor(testVal % 1.5, testVal % 1.5, testVal % 1.5), newTree.evaluate(testVal, 1.5));
		}
	}

	@Test
	public void testWrapEvaluation() {
		Wrap newTree = new Wrap(new X());
		assertEquals(new RGBColor(-1, -1, -1), newTree.evaluate(-1, 1));
		assertEquals(new RGBColor(-0.5, -0.5, -0.5), newTree.evaluate(1.5, 0));
		assertEquals(new RGBColor(0.5, 0.5, 0.5), newTree.evaluate(-1.5, 0));

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double res = newTree.wrap(testVal, -1, 1);
			assertEquals(res, newTree.evaluate(testVal, 0).getRed());
			assertEquals(res, newTree.evaluate(testVal, 0).getRed());
			assertEquals(res, newTree.evaluate(testVal, 0).getRed());
		}

	}
	
	@Test
	public void testRgbToYCrCbEvaluationwithX() { 
		rgbToYCrCb myTree = new rgbToYCrCb(new X()); 
		
		// test the ints; remember that y's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testValred = testVal * 0.2989 + testVal* 0.5866 + testVal * 0.1145;
			double testValgreen = testVal * -0.1687 + testVal * -0.3312 + testVal* 0.5;
			double testValblue = testVal * 0.5000 + testVal * -0.4183 + testVal * -0.0816;
			
			
			assertEquals(new RGBColor(testValred, testValgreen, testValblue), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(testValred, testValgreen, testValblue),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testRgbToYCrCbEvaluationwithY() { 
		rgbToYCrCb myTree = new rgbToYCrCb(new Y()); 
		
		// test the ints; remember that x's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testValred = testVal * 0.2989 + testVal* 0.5866 + testVal * 0.1145;
			double testValgreen = testVal * -0.1687 + testVal * -0.3312 + testVal* 0.5;
			double testValblue = testVal * 0.5000 + testVal * -0.4183 + testVal * -0.0816;
			
			
			assertEquals(new RGBColor(testValred, testValgreen, testValblue), myTree.evaluate(0, testVal));
			assertEquals(new RGBColor(testValred, testValgreen, testValblue),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testyCrCbToRGBEvaluationwithX() { 
		yCrCbToRGB myTree = new yCrCbToRGB(new X()); 
		
		// test the ints; remember that x's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testValred = testVal + testVal * 1.4022;
			double testValgreen = testVal + testVal * -0.3456 + testVal * -0.7145;
			double testValblue = testVal + testVal* 1.7710;
			
			assertEquals(new RGBColor(testValred, testValgreen, testValblue), myTree.evaluate(testVal, 0));
			assertEquals(new RGBColor(testValred, testValgreen, testValblue),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testyCrCbToRGBEvaluationwithY() { 
		yCrCbToRGB myTree = new yCrCbToRGB(new Y()); 
		
		// test the ints; remember that x's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testValred = testVal + testVal * 1.4022;
			double testValgreen = testVal + testVal * -0.3456 + testVal * -0.7145;
			double testValblue = testVal + testVal* 1.7710;
			
			assertEquals(new RGBColor(testValred, testValgreen, testValblue), myTree.evaluate(0, testVal));
			assertEquals(new RGBColor(testValred, testValgreen, testValblue),
					myTree.evaluate(testVal, testVal));
		}
	}
	@Test
	public void testPerlinColorEvaluation() { 
		PerlinColor myTree = new PerlinColor(new X(), new Y()); 
		
		// test the ints; remember that x's value doesn't matter

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testValred = ImprovedNoise.noise( testVal+ 0.3, testVal + 0.3, 0);
			double testValgreen = ImprovedNoise.noise(testVal - 0.8, testVal - 0.8, 0);
			double testValblue = ImprovedNoise.noise(testVal + 0.1, testVal + 0.1, 0);
			
			assertEquals(new RGBColor(testValred, testValgreen, testValblue),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	
	@Test
	public void testPerlinBWEvaluation() { 
		PerlinBW myTree = new PerlinBW(new X(), new Y()); 
		

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double testValgrey = ImprovedNoise.noise(testVal + testVal, testVal + testVal,
					testVal + testVal);
		
			assertEquals(new RGBColor(testValgrey, testValgrey, testValgrey),
					myTree.evaluate(testVal, testVal));
		}
	}
	
}
