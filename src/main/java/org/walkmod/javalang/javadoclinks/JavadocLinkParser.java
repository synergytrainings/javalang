/* Generated By:JavaCC: Do not edit this line. JavadocLinkParser.java */
package org.walkmod.javalang.javadoclinks;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class JavadocLinkParser implements JavadocLinkParserConstants {
	public static MethodLink parse(InputStream aInSt) throws ParseException {
		// create a parser (this object)
		JavadocLinkParser parser = new JavadocLinkParser(aInSt);
		// parse!
		return parser.methodlink();
	}

	public MethodLink main(String[] args) throws ParseException, FileNotFoundException {
		return parse(new FileInputStream(args[0]));
	}

	public static MethodLink parse(String expr) throws ParseException {
		MethodLink result = null;
		ByteArrayInputStream aux = new ByteArrayInputStream(expr.getBytes());
		try {
			result = parse(aux);
		} finally {
			try {
				aux.close();
			} catch (Exception e) {
			}
		}
		return result;
	}

	final public MethodLink methodlink() throws ParseException {
		String className = "";
		String methodName = "";
		String aux = "";
		List<String> params = new LinkedList<String>();
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IDENTIFIER:
			className = type();
			break;
		default:
			jj_la1[0] = jj_gen;
			;
		}
		jj_consume_token(SEPARATOR);
		jj_consume_token(IDENTIFIER);
		methodName = token.image;
		jj_consume_token(OPENPAR);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case IDENTIFIER:
			aux = type();
			params.add(aux);
			label_1: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case COMMA:
					;
					break;
				default:
					jj_la1[1] = jj_gen;
					break label_1;
				}
				jj_consume_token(COMMA);
				aux = type();
				params.add(aux);
			}
			break;
		default:
			jj_la1[2] = jj_gen;
			;
		}
		jj_consume_token(CLOSEPAR);
		jj_consume_token(0);
		{
			if (true)
				return new MethodLink(className, methodName, params);
		}
		throw new Error("Missing return statement in function");
	}

	final public String type() throws ParseException {
		String typeName = "";
		jj_consume_token(IDENTIFIER);
		typeName = token.image;
		label_2: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case DOT:
				;
				break;
			default:
				jj_la1[3] = jj_gen;
				break label_2;
			}
			jj_consume_token(DOT);
			jj_consume_token(IDENTIFIER);
			typeName += "." + token.image;
		}
		{
			if (true)
				return typeName;
		}
		throw new Error("Missing return statement in function");
	}

	/** Generated Token Manager. */
	public JavadocLinkParserTokenManager token_source;
	JavaCharStream jj_input_stream;
	/** Current token. */
	public Token token;
	/** Next token. */
	public Token jj_nt;
	private int jj_ntk;
	private int jj_gen;
	final private int[] jj_la1 = new int[4];
	static private int[] jj_la1_0;

	static {
		jj_la1_init_0();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0x40, 0x10, 0x40, 0x20, };
	}

	/** Constructor with InputStream. */
	public JavadocLinkParser(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public JavadocLinkParser(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream = new JavaCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new JavadocLinkParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Constructor. */
	public JavadocLinkParser(java.io.Reader stream) {
		jj_input_stream = new JavaCharStream(stream, 1, 1);
		token_source = new JavadocLinkParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Constructor with generated Token Manager. */
	public JavadocLinkParser(JavadocLinkParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(JavadocLinkParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	/** Get the next Token. */
	final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	final public Token getToken(int index) {
		Token t = token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	private int[] jj_expentry;
	private int jj_kind = -1;

	/** Generate ParseException. */
	public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[10];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 4; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	final public void enable_tracing() {
	}

	/** Disable tracing. */
	final public void disable_tracing() {
	}

}
