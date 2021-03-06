// Generated from BEPL.g4 by ANTLR 4.7.2

/*
 * The Basic English-like Programming Language.
 * Created by Team Rocket
 * CS 143, Section 1415 @ TCC.
 * 
 * Credit to Shalitha Suranga for
 * the usage of Simplerlang in
 * early versions of BEPL.
 * Simplerlang is licensed under the MIT License.
 * https://github.com/shalithasuranga/simpler/blob/master/LICENSE
 */
package org.bepl.interpreter;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BEPLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, STRING=3, VAR=4, INT=5, OPERATOR=6, SINGLELINE_COMMENT=7, 
		MULTILINE_COMMENT=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "STRING", "VAR", "INT", "OPERATOR", "DIGIT", "SINGLELINE_COMMENT", 
			"MULTILINE_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'is'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "STRING", "VAR", "INT", "OPERATOR", "SINGLELINE_COMMENT", 
			"MULTILINE_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public BEPLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "BEPL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13Y\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\7\4#\n\4\f\4\16\4&\13"+
		"\4\3\4\3\4\3\5\6\5+\n\5\r\5\16\5,\3\6\6\6\60\n\6\r\6\16\6\61\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\7\t:\n\t\f\t\16\t=\13\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\7\nH\n\n\f\n\16\nK\13\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\6\13T\n\13\r"+
		"\13\16\13U\3\13\3\13\5$;I\2\f\3\3\5\4\7\5\t\6\13\7\r\b\17\2\21\t\23\n"+
		"\25\13\3\2\7\5\2C\\aac|\5\2,-//\61\61\3\2\62;\4\2\f\f\17\17\5\2\13\f\17"+
		"\17\"\"\2]\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\32"+
		"\3\2\2\2\7 \3\2\2\2\t*\3\2\2\2\13/\3\2\2\2\r\63\3\2\2\2\17\65\3\2\2\2"+
		"\21\67\3\2\2\2\23B\3\2\2\2\25S\3\2\2\2\27\30\7k\2\2\30\31\7u\2\2\31\4"+
		"\3\2\2\2\32\33\7r\2\2\33\34\7t\2\2\34\35\7k\2\2\35\36\7p\2\2\36\37\7v"+
		"\2\2\37\6\3\2\2\2 $\7$\2\2!#\13\2\2\2\"!\3\2\2\2#&\3\2\2\2$%\3\2\2\2$"+
		"\"\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'(\7$\2\2(\b\3\2\2\2)+\t\2\2\2*)\3\2\2"+
		"\2+,\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\n\3\2\2\2.\60\5\17\b\2/.\3\2\2\2\60"+
		"\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\f\3\2\2\2\63\64\t\3\2\2\64\16"+
		"\3\2\2\2\65\66\t\4\2\2\66\20\3\2\2\2\67;\7%\2\28:\13\2\2\298\3\2\2\2:"+
		"=\3\2\2\2;<\3\2\2\2;9\3\2\2\2<>\3\2\2\2=;\3\2\2\2>?\t\5\2\2?@\3\2\2\2"+
		"@A\b\t\2\2A\22\3\2\2\2BC\7%\2\2CD\7%\2\2DE\7%\2\2EI\3\2\2\2FH\13\2\2\2"+
		"GF\3\2\2\2HK\3\2\2\2IJ\3\2\2\2IG\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7%\2\2"+
		"MN\7%\2\2NO\7%\2\2OP\3\2\2\2PQ\b\n\2\2Q\24\3\2\2\2RT\t\6\2\2SR\3\2\2\2"+
		"TU\3\2\2\2US\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\13\2\2X\26\3\2\2\2\t\2$,"+
		"\61;IU\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}