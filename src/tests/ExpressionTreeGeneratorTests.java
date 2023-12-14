package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;

import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.Token;
import picasso.parser.tokens.operations.PlusToken;

/**
 * Tests of creating an expression tree from a string expression. Will have
 * compiler errors until some code is created.
 * 
 * @author Sara Sprenkle
 * 
 */
public class ExpressionTreeGeneratorTests {

	private ExpressionTreeGenerator parser;

	@BeforeEach
	public void setUp() throws Exception {
		parser = new ExpressionTreeGenerator();
	}

	@Test
	public void constantExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("[1,-1, 1]");
		assertEquals(new RGBColor(1, -1, 1), e);
	}

	@Test
	public void variableExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x");
		assertEquals(new X(), e);
	}

	@Test
	public void additionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x + y");
		assertEquals(new Addition(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x+y");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] + y");
		assertEquals(new Addition(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x + y + [ -.51, 0, 1]");
		assertEquals(new Addition(new Addition(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void multiplicationExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x * y");
		assertEquals(new Multiplication(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x*y");
		assertEquals(new Multiplication(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] * y");
		assertEquals(new Multiplication(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x * y * [ -.51, 0, 1]");
		assertEquals(new Multiplication(new Multiplication(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void subtractionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x - y");
		assertEquals(new Subtraction(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x-y");
		assertEquals(new Subtraction(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] - y");
		assertEquals(new Subtraction(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x - y - [ -.51, 0, 1]");
		assertEquals(new Subtraction(new Subtraction(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void divisionExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x / y");
		assertEquals(new Division(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x/y");
		assertEquals(new Division(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] / y");
		assertEquals(new Division(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x / y / [ -.51, 0, 1]");
		assertEquals(new Division(new Division(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void moduloExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x % y");
		assertEquals(new Modulo(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x%y");
		assertEquals(new Modulo(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] % y");
		assertEquals(new Modulo(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x % y % [ -.51, 0, 1]");
		assertEquals(new Modulo(new Modulo(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void exponentiationExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("x ^ y");
		assertEquals(new Exponentiation(new X(), new Y()), e);

		// no spaces!
		e = parser.makeExpression("x^y");
		assertEquals(new Exponentiation(new X(), new Y()), e);

		e = parser.makeExpression("[1,.3,-1] ^ y");
		assertEquals(new Exponentiation(new RGBColor(1, .3, -1), new Y()), e);

		e = parser.makeExpression("x ^ y ^ [ -.51, 0, 1]");
		assertEquals(new Exponentiation(new Exponentiation(new X(), new Y()), new RGBColor(-.51, 0, 1)), e);
	}

	@Test
	public void parenthesesExpressionTests() {
		ExpressionTreeNode e = parser.makeExpression("( x + y )");
		assertEquals(new Addition(new X(), new Y()), e);

		e = parser.makeExpression("( x + (y + [ 1, 1, 1] ) )");
		assertEquals(new Addition(new X(), new Addition(new Y(), new RGBColor(1, 1, 1))), e);
	}

	//@Test
	/*public void arithmeticStackTests() {
		Stack<Token> stack = parser.infixToPostfix("x + y * x");

		Stack<Token> expected = new Stack<>();
		expected.push(new IdentifierToken("x"));
		expected.push(new IdentifierToken("y"));
		expected.push(new IdentifierToken("x"));
		//expected.push(new MultiplyToken());
		expected.push(new PlusToken());

		assertEquals(expected, stack);
	}*/

	@Test
	public void floorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("floor( x )");
		assertEquals(new Floor(new X()), e);

		e = parser.makeExpression("floor( x + y )");
		assertEquals(new Floor(new Addition(new X(), new Y())), e);
	}
	
	@Test
	public void ceilFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("ceil( x )");
		assertEquals(new Ceil(new X()), e);

		e = parser.makeExpression("ceil( x + y )");
		assertEquals(new Ceil(new Addition(new X(), new Y())), e);
	}
	
	@Test
	public void clampFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("clamp( x )");
		assertEquals(new Clamp(new X()), e);
		
		e = parser.makeExpression("clamp( x + y )");
		assertEquals(new Clamp(new Addition(new X(), new Y())), e);
	}

	@Test
	public void absFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("abs( x )");
		assertEquals(new Abs(new X()), e);

		e = parser.makeExpression("abs( x + y )");
		assertEquals(new Abs(new Addition(new X(), new Y())), e);

	}
	
	@Test
	public void expFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("exp( x )");
		assertEquals(new Exp(new X()), e);

		e = parser.makeExpression("exp( x + y )");
		assertEquals(new Exp(new Addition(new X(), new Y())), e);

	}

	@Test
	public void sinFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("sin( x )");
		assertEquals(new Sin(new X()), e);

		e = parser.makeExpression("sin( x + y )");
		assertEquals(new Sin(new Addition(new X(), new Y())), e);

	}

	@Test
	public void cosFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("cos( x )");
		assertEquals(new Cosine(new X()), e);

		e = parser.makeExpression("cos( x + y )");
		assertEquals(new Cosine(new Addition(new X(), new Y())), e);

	}

	@Test
	public void tanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("tan( x )");
		assertEquals(new Tan(new X()), e);

		e = parser.makeExpression("tan( x + y )");
		assertEquals(new Tan(new Addition(new X(), new Y())), e);

	}

	@Test
	public void atanFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("atan( x )");
		assertEquals(new Atan(new X()), e);

		e = parser.makeExpression("atan( x + y )");
		assertEquals(new Atan(new Addition(new X(), new Y())), e);

	}

	@Test
	public void logFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("log( x )");
		assertEquals(new Log(new X()), e);

		e = parser.makeExpression("log( x + y )");
		assertEquals(new Log(new Addition(new X(), new Y())), e);

	}

	@Test
	public void randomFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("random");
		assertEquals(new RandomColor(), e);

		e = parser.makeExpression("cos( random() )");
		assertEquals(new Cosine(new RandomColor()), e);

	}

	@Test
	public void mandelbrotFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("mandelbrot");
		assertEquals(new Mandelbrot(), e);

		e = parser.makeExpression("cos( mandelbrot() )");
		assertEquals(new Cosine(new Mandelbrot()), e);

	}

	@Test
	public void negateFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("!x");
		assertEquals(new Negate(new X()), e);

		e = parser.makeExpression("!cos( x )");
		assertEquals(new Negate(new Cosine(new X())), e);

	}
	
	
	@Test
	public void rgbToYCrCbFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("rgbToYCrCb(x)");
		assertEquals(new rgbToYCrCb(new X()), e);

		e = parser.makeExpression("rgbToYCrCb( x + y )");
		assertEquals(new rgbToYCrCb(new Addition(new X(), new Y())), e);

	}
	
	@Test
	public void yCrCbToRGBFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("yCrCbToRGB(x)");
		assertEquals(new yCrCbToRGB(new X()), e);

		e = parser.makeExpression("yCrCbToRGB( x + y )");
		assertEquals(new yCrCbToRGB(new Addition(new X(), new Y())), e);
	}

	
	@Test
	public void imageWrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("imageWrap(\"foo.jpg\", x, y)");
		StringToken stringTok = new StringToken("foo.jpg");
		assertEquals(new ImageWrap(stringTok, new X(), new Y()), e);
		
		e = parser.makeExpression("imageWrap(\"vortex.jpg\", x, y)");
		StringToken stringToken = new StringToken("vortex.jpg");
		assertEquals(new ImageWrap(stringToken, new X(),  new Y()), e);
				

	}

	@Test
	public void juliaFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("julia(\".158\", \".4\")");
		StringToken stringTok1 = new StringToken(".158");
		StringToken stringTok2 = new StringToken(".4");
		assertEquals(new Julia(stringTok1, stringTok2), e);
		
		e = parser.makeExpression("julia(\"-.8\", \"1.0\")");
		StringToken stringTok3 = new StringToken("-.8");
		StringToken stringTok4 = new StringToken("1.0");
		assertEquals(new Julia(stringTok3, stringTok4), e);
				

	}
	
	@Test
	public void wrapFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("wrap(x)");
		assertEquals(new Wrap(new X()), e);
		
		e = parser.makeExpression("wrap(x+x)");
		assertEquals(new Wrap(new Addition(new X(), new X())), e);		

	}
	
	@Test
	public void perlinColorFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("perlinColor(x,y)");
		assertEquals(new PerlinColor(new X(), new Y()), e);

		e = parser.makeExpression("perlinColor( x + y, x + y )");
		assertEquals(new PerlinColor(new Addition(new X(), new Y()),new Addition(new X(), new Y()) ), e);

	}
	
	@Test
	public void perlinBWFunctionTests() {
		ExpressionTreeNode e = parser.makeExpression("perlinBW(x,y)");
		assertEquals(new PerlinBW(new X(), new Y()), e);

		e = parser.makeExpression("perlinBW( x + y, x + y )");
		assertEquals(new PerlinBW(new Addition(new X(), new Y()),new Addition(new X(), new Y()) ), e);
	}

	/**
	 * For now only tests binary operators
	 */
	@Test
	public void orderOfOperationTest() {
		//precedence 1 and 2
		ExpressionTreeNode e = parser.makeExpression("x + y / x");
		assertEquals(new Addition (new X(), new Division(new Y(), new X())), e);

		//precedence 3 and 4
		ExpressionTreeNode f = parser.makeExpression("x / y ^ x");
		assertNotEquals(new Exponentiation (new Y(), new Division(new X(), new Y())), f);
		assertEquals(new Division (new X(), new Exponentiation(new Y(), new X())), f);

		//same precedence
		ExpressionTreeNode g = parser.makeExpression("x + y - x");
		assertEquals(new Subtraction(new Addition(new X(), new Y()), new X()), g);

		//same precedence
		ExpressionTreeNode h = parser.makeExpression("x % y * x");
		assertEquals(new Multiplication(new Modulo(new X(), new Y()), new X()), h);
 	}

}