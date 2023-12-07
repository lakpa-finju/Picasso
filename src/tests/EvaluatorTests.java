/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;

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
			assertEquals(new RGBColor(ceilOfTestVal, ceilOfTestVal, ceilOfTestVal),
					myTree.evaluate(testVal, testVal));
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
			assertEquals(new RGBColor(absOfTestVal, absOfTestVal, absOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
		
	}

	@Test
	public void testSineEvaluation() {
		Sin myTree = new Sin(new X());

		// some straightforward tests
		assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0, -1));
		//common sin value - pi/4
		assertEquals(new RGBColor(0.706825181105366, 0.706825181105366, 0.706825181105366), myTree.evaluate(.785, -1));
		assertEquals(new RGBColor(-0.9999996829318346, -0.9999996829318346, -0.9999996829318346), myTree.evaluate(-1.57, -1));

		double[] tests = { -.7, -.00001, .000001, .5 };

		for (double testVal : tests) {
			double sinOfTestVal = Math.sin(testVal);
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal), myTree.evaluate(testVal, -1));
			assertEquals(new RGBColor(sinOfTestVal, sinOfTestVal, sinOfTestVal),
					myTree.evaluate(testVal, testVal));
		}
	}
	
	@Test
	public void testAdditionEvaluation() {
		Addition myTree = new Addition(new X(),new Y());

		// some straightforward tests
		// some straightforward tests
				assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, 1));
				assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
				assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0,0));

				// test the ints; remember that y's value doesn't matter
				for (int i = -1; i <= 1; i++) {
					assertEquals(new RGBColor(i-i, i-i, i-i), myTree.evaluate(i, -i));
					assertEquals(new RGBColor(i+i, i+i, i+i), myTree.evaluate(i, i));
				}

				double[] tests = { -.7, -.00001, .000001, .5 };

				for (double testVal : tests) {
					assertEquals(new RGBColor(testVal-1, testVal-1, testVal-1), myTree.evaluate(testVal, -1));
					assertEquals(new RGBColor(testVal+1, testVal+1, testVal+1),
							myTree.evaluate(testVal, 1));
				}
	}
	
	@Test
	public void testImageWrapEvaluation() {
		ImageWrap myTree = new ImageWrap("foo.jpg", new X(),new Y());

		// some straightforward tests
		// some straightforward tests
				assertEquals(new RGBColor(1, 1, 1), myTree.evaluate(0, 1));
				assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(1, -1));
				assertEquals(new RGBColor(0, 0, 0), myTree.evaluate(0,0));

				// test the ints; remember that y's value doesn't matter
				for (int i = -1; i <= 1; i++) {
					assertEquals(new RGBColor(i-i, i-i, i-i), myTree.evaluate(i, -i));
					assertEquals(new RGBColor(i+i, i+i, i+i), myTree.evaluate(i, i));
				}

				double[] tests = { -.7, -.00001, .000001, .5 };

				for (double testVal : tests) {
					assertEquals(new RGBColor(testVal-1, testVal-1, testVal-1), myTree.evaluate(testVal, -1));
					assertEquals(new RGBColor(testVal+1, testVal+1, testVal+1),
							myTree.evaluate(testVal, 1));
				}
	}

}
