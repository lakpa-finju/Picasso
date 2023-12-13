/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picasso.parser.SemanticAnalyzer;
import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.*;
import picasso.parser.tokens.*;
import picasso.parser.tokens.functions.AbsToken;
import picasso.parser.tokens.functions.CeilToken;

import picasso.parser.tokens.functions.ImageWrapToken;
import picasso.parser.tokens.functions.PerlinBWToken;
import picasso.parser.tokens.functions.PerlinColorToken;
import picasso.parser.tokens.functions.RgbToYCrCbToken;
import picasso.parser.tokens.functions.ClampToken;
import picasso.parser.tokens.functions.ExpToken;
import picasso.parser.tokens.functions.SinToken;
import picasso.parser.tokens.functions.WrapToken;
import picasso.parser.tokens.functions.YCrCbToRGBToken;
import picasso.parser.tokens.operations.*;

/**
 * Test the parsing from the Stack (not as easy as using a String as input, but
 * helps to isolate where the problem is)
 * 
 * @author Sara Sprenkle
 *
 */
class SemanticAnalyzerTest {

	private SemanticAnalyzer semAnalyzer;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		semAnalyzer = SemanticAnalyzer.getInstance();
	}

	@Test
	void testParseAddition() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Addition(new X(), new Y()), actual);
	}
	
	@Test
	void testParseCeil() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new CeilToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Ceil(new X()), actual);
	}
	@Test
	void testParseCeilwithY() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new CeilToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Ceil(new Y()), actual);
	}
	
	@Test
	void testParseClamp() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ClampToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Clamp(new X()), actual);
	}
	
	@Test
	void testParseClampwithY() {
		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ClampToken());
		
		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);
		
		assertEquals(new Clamp(new Y()), actual);
	}

	@Test
	void testParseAbs() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new AbsToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Abs(new X()), actual);
	}
	
	@Test
	void testParseAbswithY() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new AbsToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Abs(new Y()), actual);
	}

	@Test
	void testParseSin() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new SinToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Sin(new X()), actual);
	}
	
	@Test
	void testParseImageWrap() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new StringToken("foo.jpg"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ImageWrapToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new ImageWrap(new StringToken("foo.jpg"), new X(), new Y()), actual);
	}
  @Test
	void testParseSinwithY() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new SinToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Sin(new Y()), actual);
	}
	
	@Test
	void testParseAssignment() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("a"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PlusToken());
		tokens.push(new AssignmentToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Assignment(new Variable("a"), new Addition(new X(), new Y())), actual);
	}

	@Test
	void testParseDivision() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new DivideToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Division(new X(), new Y()), actual);
	}
	
	/**
	 * Checks to make sure division generates 
	 * expression with correct sides.
	 */
	@Test
	void testParseDivisionReverse() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("y"));
		tokens.push(new IdentifierToken("x"));
		tokens.push(new DivideToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertNotEquals(new Division(new X(), new Y()), actual);
	}

	@Test
	void testParseModulo() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new ModToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Modulo(new X(), new Y()), actual);

	}
	
	@Test
	void testParseWrap() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new WrapToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Wrap(new X()), actual);

	}
  @Test
	void testParseYCrCbToRGB() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new YCrCbToRGBToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new yCrCbToRGB(new X()), actual);
	}
	
	@Test
	void testParseRgbToYCrCb() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new RgbToYCrCbToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new rgbToYCrCb(new X()), actual);
	}
	
	@Test
	void testParsePerlinColor() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PerlinColorToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new PerlinColor(new X(), new Y()), actual);
	}
	
	@Test
	void testParsePerlinBW() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new IdentifierToken("y"));
		tokens.push(new PerlinBWToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new PerlinBW(new X(), new Y()), actual);
	}
	@Test
	void testParseExp() {

		Stack<Token> tokens = new Stack<>();
		tokens.push(new IdentifierToken("x"));
		tokens.push(new ExpToken());

		ExpressionTreeNode actual = semAnalyzer.generateExpressionTree(tokens);

		assertEquals(new Exp(new X()), actual);
	}
}
