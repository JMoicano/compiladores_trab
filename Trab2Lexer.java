// Generated from Trab2.g4 by ANTLR 4.5
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Trab2Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T_REAL_LIT=59, 
		T_INT_LIT=60, T_KW_VERDADEIRO=61, T_KW_FALSO=62, T_CARAC_LIT=63, T_STRING_LIT=64, 
		SL_COMMENT=65, ML_COMMENT=66, T_IDENTIFICADOR=67, WS=68;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
		"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "T__48", 
		"T__49", "T__50", "T__51", "T__52", "T__53", "T__54", "T__55", "T__56", 
		"T__57", "CR", "LF", "T_OCTAL_LIT", "T_HEX_LIT", "T_BIN_LIT", "T_DEC_LIT", 
		"T_REAL_LIT", "T_INT_LIT", "T_KW_VERDADEIRO", "T_KW_FALSO", "T_CARAC_LIT", 
		"T_STRING_LIT", "SL_COMMENT", "ML_COMMENT", "T_IDENTIFICADOR", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'algoritmo'", "';'", "'variaveis'", "'fim_variaveis'", "','", "':'", 
		"'inteiro'", "'real'", "'caractere'", "'literal'", "'logico'", "'matriz'", 
		"'['", "']'", "'de'", "'inteiros'", "'reais'", "'caracteres'", "'literais'", 
		"'logicos'", "'inicio'", "'fim'", "'retorne'", "':='", "'se'", "'entao'", 
		"'senao'", "'fim_se'", "'enquanto'", "'faca'", "'fim_enquanto'", "'para'", 
		"'ate'", "'fim_para'", "'passo'", "'+'", "'-'", "'ou'", "'||'", "'e'", 
		"'&&'", "'|'", "'^'", "'&'", "'='", "'<>'", "'>'", "'>='", "'<'", "'<='", 
		"'/'", "'*'", "'%'", "'~'", "'nao'", "'('", "')'", "'funcao'", null, null, 
		"'verdadeiro'", "'falso'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "T_REAL_LIT", 
		"T_INT_LIT", "T_KW_VERDADEIRO", "T_KW_FALSO", "T_CARAC_LIT", "T_STRING_LIT", 
		"SL_COMMENT", "ML_COMMENT", "T_IDENTIFICADOR", "WS"
	};
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


	public Trab2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Trab2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2F\u0236\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3"+
		"(\3(\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3\60\3\60\3\61\3"+
		"\61\3\61\3\62\3\62\3\63\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3"+
		"\67\38\38\38\38\39\39\3:\3:\3;\3;\3;\3;\3;\3;\3;\3<\3<\3=\3=\3>\3>\3>"+
		"\6>\u01c0\n>\r>\16>\u01c1\3?\3?\3?\6?\u01c7\n?\r?\16?\u01c8\3@\3@\3@\6"+
		"@\u01ce\n@\r@\16@\u01cf\3A\6A\u01d3\nA\rA\16A\u01d4\3B\6B\u01d8\nB\rB"+
		"\16B\u01d9\3B\3B\6B\u01de\nB\rB\16B\u01df\3C\3C\3C\3C\5C\u01e6\nC\3D\3"+
		"D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\5F\u01fd\n"+
		"F\3F\3F\3G\3G\3G\3G\7G\u0205\nG\fG\16G\u0208\13G\3G\3G\3H\3H\3H\3H\7H"+
		"\u0210\nH\fH\16H\u0213\13H\3H\3H\5H\u0217\nH\3H\3H\3I\3I\3I\3I\7I\u021f"+
		"\nI\fI\16I\u0222\13I\3I\3I\3I\3I\3I\3J\3J\7J\u022b\nJ\fJ\16J\u022e\13"+
		"J\3K\6K\u0231\nK\rK\16K\u0232\3K\3K\4\u0211\u0220\2L\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w\2y\2{\2}\2"+
		"\177\2\u0081\2\u0083=\u0085>\u0087?\u0089@\u008bA\u008dB\u008fC\u0091"+
		"D\u0093E\u0095F\3\2\16\4\2EEee\3\2\62:\4\2ZZzz\b\2))\62;CCHHcchh\4\2D"+
		"Ddd\3\2\62\63\3\2\62;\4\2))^^\6\2\f\f\17\17$$^^\6\2))C\\aac|\7\2))\62"+
		";C\\aac|\6\2\13\f\17\17\"\"))\u0241\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2"+
		"\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2"+
		"\2u\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3"+
		"\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2"+
		"\2\u0093\3\2\2\2\2\u0095\3\2\2\2\3\u0097\3\2\2\2\5\u00a1\3\2\2\2\7\u00a3"+
		"\3\2\2\2\t\u00ad\3\2\2\2\13\u00bb\3\2\2\2\r\u00bd\3\2\2\2\17\u00bf\3\2"+
		"\2\2\21\u00c7\3\2\2\2\23\u00cc\3\2\2\2\25\u00d6\3\2\2\2\27\u00de\3\2\2"+
		"\2\31\u00e5\3\2\2\2\33\u00ec\3\2\2\2\35\u00ee\3\2\2\2\37\u00f0\3\2\2\2"+
		"!\u00f3\3\2\2\2#\u00fc\3\2\2\2%\u0102\3\2\2\2\'\u010d\3\2\2\2)\u0116\3"+
		"\2\2\2+\u011e\3\2\2\2-\u0125\3\2\2\2/\u0129\3\2\2\2\61\u0131\3\2\2\2\63"+
		"\u0134\3\2\2\2\65\u0137\3\2\2\2\67\u013d\3\2\2\29\u0143\3\2\2\2;\u014a"+
		"\3\2\2\2=\u0153\3\2\2\2?\u0158\3\2\2\2A\u0165\3\2\2\2C\u016a\3\2\2\2E"+
		"\u016e\3\2\2\2G\u0177\3\2\2\2I\u017d\3\2\2\2K\u017f\3\2\2\2M\u0181\3\2"+
		"\2\2O\u0184\3\2\2\2Q\u0187\3\2\2\2S\u0189\3\2\2\2U\u018c\3\2\2\2W\u018e"+
		"\3\2\2\2Y\u0190\3\2\2\2[\u0192\3\2\2\2]\u0194\3\2\2\2_\u0197\3\2\2\2a"+
		"\u0199\3\2\2\2c\u019c\3\2\2\2e\u019e\3\2\2\2g\u01a1\3\2\2\2i\u01a3\3\2"+
		"\2\2k\u01a5\3\2\2\2m\u01a7\3\2\2\2o\u01a9\3\2\2\2q\u01ad\3\2\2\2s\u01af"+
		"\3\2\2\2u\u01b1\3\2\2\2w\u01b8\3\2\2\2y\u01ba\3\2\2\2{\u01bc\3\2\2\2}"+
		"\u01c3\3\2\2\2\177\u01ca\3\2\2\2\u0081\u01d2\3\2\2\2\u0083\u01d7\3\2\2"+
		"\2\u0085\u01e5\3\2\2\2\u0087\u01e7\3\2\2\2\u0089\u01f2\3\2\2\2\u008b\u01f8"+
		"\3\2\2\2\u008d\u0200\3\2\2\2\u008f\u020b\3\2\2\2\u0091\u021a\3\2\2\2\u0093"+
		"\u0228\3\2\2\2\u0095\u0230\3\2\2\2\u0097\u0098\7c\2\2\u0098\u0099\7n\2"+
		"\2\u0099\u009a\7i\2\2\u009a\u009b\7q\2\2\u009b\u009c\7t\2\2\u009c\u009d"+
		"\7k\2\2\u009d\u009e\7v\2\2\u009e\u009f\7o\2\2\u009f\u00a0\7q\2\2\u00a0"+
		"\4\3\2\2\2\u00a1\u00a2\7=\2\2\u00a2\6\3\2\2\2\u00a3\u00a4\7x\2\2\u00a4"+
		"\u00a5\7c\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7c\2\2"+
		"\u00a8\u00a9\7x\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac"+
		"\7u\2\2\u00ac\b\3\2\2\2\u00ad\u00ae\7h\2\2\u00ae\u00af\7k\2\2\u00af\u00b0"+
		"\7o\2\2\u00b0\u00b1\7a\2\2\u00b1\u00b2\7x\2\2\u00b2\u00b3\7c\2\2\u00b3"+
		"\u00b4\7t\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7c\2\2\u00b6\u00b7\7x\2\2"+
		"\u00b7\u00b8\7g\2\2\u00b8\u00b9\7k\2\2\u00b9\u00ba\7u\2\2\u00ba\n\3\2"+
		"\2\2\u00bb\u00bc\7.\2\2\u00bc\f\3\2\2\2\u00bd\u00be\7<\2\2\u00be\16\3"+
		"\2\2\2\u00bf\u00c0\7k\2\2\u00c0\u00c1\7p\2\2\u00c1\u00c2\7v\2\2\u00c2"+
		"\u00c3\7g\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7q\2\2"+
		"\u00c6\20\3\2\2\2\u00c7\u00c8\7t\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7"+
		"c\2\2\u00ca\u00cb\7n\2\2\u00cb\22\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce"+
		"\7c\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1\7e\2\2\u00d1"+
		"\u00d2\7v\2\2\u00d2\u00d3\7g\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5\7g\2\2"+
		"\u00d5\24\3\2\2\2\u00d6\u00d7\7n\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7"+
		"v\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7c\2\2\u00dc\u00dd"+
		"\7n\2\2\u00dd\26\3\2\2\2\u00de\u00df\7n\2\2\u00df\u00e0\7q\2\2\u00e0\u00e1"+
		"\7i\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7e\2\2\u00e3\u00e4\7q\2\2\u00e4"+
		"\30\3\2\2\2\u00e5\u00e6\7o\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7v\2\2\u00e8"+
		"\u00e9\7t\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7|\2\2\u00eb\32\3\2\2\2\u00ec"+
		"\u00ed\7]\2\2\u00ed\34\3\2\2\2\u00ee\u00ef\7_\2\2\u00ef\36\3\2\2\2\u00f0"+
		"\u00f1\7f\2\2\u00f1\u00f2\7g\2\2\u00f2 \3\2\2\2\u00f3\u00f4\7k\2\2\u00f4"+
		"\u00f5\7p\2\2\u00f5\u00f6\7v\2\2\u00f6\u00f7\7g\2\2\u00f7\u00f8\7k\2\2"+
		"\u00f8\u00f9\7t\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fb\7u\2\2\u00fb\"\3\2"+
		"\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7c\2\2\u00ff\u0100"+
		"\7k\2\2\u0100\u0101\7u\2\2\u0101$\3\2\2\2\u0102\u0103\7e\2\2\u0103\u0104"+
		"\7c\2\2\u0104\u0105\7t\2\2\u0105\u0106\7c\2\2\u0106\u0107\7e\2\2\u0107"+
		"\u0108\7v\2\2\u0108\u0109\7g\2\2\u0109\u010a\7t\2\2\u010a\u010b\7g\2\2"+
		"\u010b\u010c\7u\2\2\u010c&\3\2\2\2\u010d\u010e\7n\2\2\u010e\u010f\7k\2"+
		"\2\u010f\u0110\7v\2\2\u0110\u0111\7g\2\2\u0111\u0112\7t\2\2\u0112\u0113"+
		"\7c\2\2\u0113\u0114\7k\2\2\u0114\u0115\7u\2\2\u0115(\3\2\2\2\u0116\u0117"+
		"\7n\2\2\u0117\u0118\7q\2\2\u0118\u0119\7i\2\2\u0119\u011a\7k\2\2\u011a"+
		"\u011b\7e\2\2\u011b\u011c\7q\2\2\u011c\u011d\7u\2\2\u011d*\3\2\2\2\u011e"+
		"\u011f\7k\2\2\u011f\u0120\7p\2\2\u0120\u0121\7k\2\2\u0121\u0122\7e\2\2"+
		"\u0122\u0123\7k\2\2\u0123\u0124\7q\2\2\u0124,\3\2\2\2\u0125\u0126\7h\2"+
		"\2\u0126\u0127\7k\2\2\u0127\u0128\7o\2\2\u0128.\3\2\2\2\u0129\u012a\7"+
		"t\2\2\u012a\u012b\7g\2\2\u012b\u012c\7v\2\2\u012c\u012d\7q\2\2\u012d\u012e"+
		"\7t\2\2\u012e\u012f\7p\2\2\u012f\u0130\7g\2\2\u0130\60\3\2\2\2\u0131\u0132"+
		"\7<\2\2\u0132\u0133\7?\2\2\u0133\62\3\2\2\2\u0134\u0135\7u\2\2\u0135\u0136"+
		"\7g\2\2\u0136\64\3\2\2\2\u0137\u0138\7g\2\2\u0138\u0139\7p\2\2\u0139\u013a"+
		"\7v\2\2\u013a\u013b\7c\2\2\u013b\u013c\7q\2\2\u013c\66\3\2\2\2\u013d\u013e"+
		"\7u\2\2\u013e\u013f\7g\2\2\u013f\u0140\7p\2\2\u0140\u0141\7c\2\2\u0141"+
		"\u0142\7q\2\2\u01428\3\2\2\2\u0143\u0144\7h\2\2\u0144\u0145\7k\2\2\u0145"+
		"\u0146\7o\2\2\u0146\u0147\7a\2\2\u0147\u0148\7u\2\2\u0148\u0149\7g\2\2"+
		"\u0149:\3\2\2\2\u014a\u014b\7g\2\2\u014b\u014c\7p\2\2\u014c\u014d\7s\2"+
		"\2\u014d\u014e\7w\2\2\u014e\u014f\7c\2\2\u014f\u0150\7p\2\2\u0150\u0151"+
		"\7v\2\2\u0151\u0152\7q\2\2\u0152<\3\2\2\2\u0153\u0154\7h\2\2\u0154\u0155"+
		"\7c\2\2\u0155\u0156\7e\2\2\u0156\u0157\7c\2\2\u0157>\3\2\2\2\u0158\u0159"+
		"\7h\2\2\u0159\u015a\7k\2\2\u015a\u015b\7o\2\2\u015b\u015c\7a\2\2\u015c"+
		"\u015d\7g\2\2\u015d\u015e\7p\2\2\u015e\u015f\7s\2\2\u015f\u0160\7w\2\2"+
		"\u0160\u0161\7c\2\2\u0161\u0162\7p\2\2\u0162\u0163\7v\2\2\u0163\u0164"+
		"\7q\2\2\u0164@\3\2\2\2\u0165\u0166\7r\2\2\u0166\u0167\7c\2\2\u0167\u0168"+
		"\7t\2\2\u0168\u0169\7c\2\2\u0169B\3\2\2\2\u016a\u016b\7c\2\2\u016b\u016c"+
		"\7v\2\2\u016c\u016d\7g\2\2\u016dD\3\2\2\2\u016e\u016f\7h\2\2\u016f\u0170"+
		"\7k\2\2\u0170\u0171\7o\2\2\u0171\u0172\7a\2\2\u0172\u0173\7r\2\2\u0173"+
		"\u0174\7c\2\2\u0174\u0175\7t\2\2\u0175\u0176\7c\2\2\u0176F\3\2\2\2\u0177"+
		"\u0178\7r\2\2\u0178\u0179\7c\2\2\u0179\u017a\7u\2\2\u017a\u017b\7u\2\2"+
		"\u017b\u017c\7q\2\2\u017cH\3\2\2\2\u017d\u017e\7-\2\2\u017eJ\3\2\2\2\u017f"+
		"\u0180\7/\2\2\u0180L\3\2\2\2\u0181\u0182\7q\2\2\u0182\u0183\7w\2\2\u0183"+
		"N\3\2\2\2\u0184\u0185\7~\2\2\u0185\u0186\7~\2\2\u0186P\3\2\2\2\u0187\u0188"+
		"\7g\2\2\u0188R\3\2\2\2\u0189\u018a\7(\2\2\u018a\u018b\7(\2\2\u018bT\3"+
		"\2\2\2\u018c\u018d\7~\2\2\u018dV\3\2\2\2\u018e\u018f\7`\2\2\u018fX\3\2"+
		"\2\2\u0190\u0191\7(\2\2\u0191Z\3\2\2\2\u0192\u0193\7?\2\2\u0193\\\3\2"+
		"\2\2\u0194\u0195\7>\2\2\u0195\u0196\7@\2\2\u0196^\3\2\2\2\u0197\u0198"+
		"\7@\2\2\u0198`\3\2\2\2\u0199\u019a\7@\2\2\u019a\u019b\7?\2\2\u019bb\3"+
		"\2\2\2\u019c\u019d\7>\2\2\u019dd\3\2\2\2\u019e\u019f\7>\2\2\u019f\u01a0"+
		"\7?\2\2\u01a0f\3\2\2\2\u01a1\u01a2\7\61\2\2\u01a2h\3\2\2\2\u01a3\u01a4"+
		"\7,\2\2\u01a4j\3\2\2\2\u01a5\u01a6\7\'\2\2\u01a6l\3\2\2\2\u01a7\u01a8"+
		"\7\u0080\2\2\u01a8n\3\2\2\2\u01a9\u01aa\7p\2\2\u01aa\u01ab\7c\2\2\u01ab"+
		"\u01ac\7q\2\2\u01acp\3\2\2\2\u01ad\u01ae\7*\2\2\u01aer\3\2\2\2\u01af\u01b0"+
		"\7+\2\2\u01b0t\3\2\2\2\u01b1\u01b2\7h\2\2\u01b2\u01b3\7w\2\2\u01b3\u01b4"+
		"\7p\2\2\u01b4\u01b5\7e\2\2\u01b5\u01b6\7c\2\2\u01b6\u01b7\7q\2\2\u01b7"+
		"v\3\2\2\2\u01b8\u01b9\7\17\2\2\u01b9x\3\2\2\2\u01ba\u01bb\7\f\2\2\u01bb"+
		"z\3\2\2\2\u01bc\u01bd\7\62\2\2\u01bd\u01bf\t\2\2\2\u01be\u01c0\t\3\2\2"+
		"\u01bf\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c1\u01c2"+
		"\3\2\2\2\u01c2|\3\2\2\2\u01c3\u01c4\7\62\2\2\u01c4\u01c6\t\4\2\2\u01c5"+
		"\u01c7\t\5\2\2\u01c6\u01c5\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01c6\3\2"+
		"\2\2\u01c8\u01c9\3\2\2\2\u01c9~\3\2\2\2\u01ca\u01cb\7\62\2\2\u01cb\u01cd"+
		"\t\6\2\2\u01cc\u01ce\t\7\2\2\u01cd\u01cc\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf"+
		"\u01cd\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u0080\3\2\2\2\u01d1\u01d3\t\b"+
		"\2\2\u01d2\u01d1\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4"+
		"\u01d5\3\2\2\2\u01d5\u0082\3\2\2\2\u01d6\u01d8\5\u0081A\2\u01d7\u01d6"+
		"\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da"+
		"\u01db\3\2\2\2\u01db\u01dd\7\60\2\2\u01dc\u01de\5\u0081A\2\u01dd\u01dc"+
		"\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0"+
		"\u0084\3\2\2\2\u01e1\u01e6\5{>\2\u01e2\u01e6\5}?\2\u01e3\u01e6\5\177@"+
		"\2\u01e4\u01e6\5\u0081A\2\u01e5\u01e1\3\2\2\2\u01e5\u01e2\3\2\2\2\u01e5"+
		"\u01e3\3\2\2\2\u01e5\u01e4\3\2\2\2\u01e6\u0086\3\2\2\2\u01e7\u01e8\7x"+
		"\2\2\u01e8\u01e9\7g\2\2\u01e9\u01ea\7t\2\2\u01ea\u01eb\7f\2\2\u01eb\u01ec"+
		"\7c\2\2\u01ec\u01ed\7f\2\2\u01ed\u01ee\7g\2\2\u01ee\u01ef\7k\2\2\u01ef"+
		"\u01f0\7t\2\2\u01f0\u01f1\7q\2\2\u01f1\u0088\3\2\2\2\u01f2\u01f3\7h\2"+
		"\2\u01f3\u01f4\7c\2\2\u01f4\u01f5\7n\2\2\u01f5\u01f6\7u\2\2\u01f6\u01f7"+
		"\7q\2\2\u01f7\u008a\3\2\2\2\u01f8\u01fc\7)\2\2\u01f9\u01fd\n\t\2\2\u01fa"+
		"\u01fb\7^\2\2\u01fb\u01fd\13\2\2\2\u01fc\u01f9\3\2\2\2\u01fc\u01fa\3\2"+
		"\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff\7)\2\2\u01ff"+
		"\u008c\3\2\2\2\u0200\u0206\7$\2\2\u0201\u0205\n\n\2\2\u0202\u0203\7^\2"+
		"\2\u0203\u0205\13\2\2\2\u0204\u0201\3\2\2\2\u0204\u0202\3\2\2\2\u0205"+
		"\u0208\3\2\2\2\u0206\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0209\3\2"+
		"\2\2\u0208\u0206\3\2\2\2\u0209\u020a\7$\2\2\u020a\u008e\3\2\2\2\u020b"+
		"\u020c\7\61\2\2\u020c\u020d\7\61\2\2\u020d\u0211\3\2\2\2\u020e\u0210\13"+
		"\2\2\2\u020f\u020e\3\2\2\2\u0210\u0213\3\2\2\2\u0211\u0212\3\2\2\2\u0211"+
		"\u020f\3\2\2\2\u0212\u0216\3\2\2\2\u0213\u0211\3\2\2\2\u0214\u0217\5y"+
		"=\2\u0215\u0217\7\2\2\3\u0216\u0214\3\2\2\2\u0216\u0215\3\2\2\2\u0217"+
		"\u0218\3\2\2\2\u0218\u0219\bH\2\2\u0219\u0090\3\2\2\2\u021a\u021b\7\61"+
		"\2\2\u021b\u021c\7,\2\2\u021c\u0220\3\2\2\2\u021d\u021f\13\2\2\2\u021e"+
		"\u021d\3\2\2\2\u021f\u0222\3\2\2\2\u0220\u0221\3\2\2\2\u0220\u021e\3\2"+
		"\2\2\u0221\u0223\3\2\2\2\u0222\u0220\3\2\2\2\u0223\u0224\7,\2\2\u0224"+
		"\u0225\7\61\2\2\u0225\u0226\3\2\2\2\u0226\u0227\bI\2\2\u0227\u0092\3\2"+
		"\2\2\u0228\u022c\t\13\2\2\u0229\u022b\t\f\2\2\u022a\u0229\3\2\2\2\u022b"+
		"\u022e\3\2\2\2\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u0094\3\2"+
		"\2\2\u022e\u022c\3\2\2\2\u022f\u0231\t\r\2\2\u0230\u022f\3\2\2\2\u0231"+
		"\u0232\3\2\2\2\u0232\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0234\3\2"+
		"\2\2\u0234\u0235\bK\2\2\u0235\u0096\3\2\2\2\22\2\u01c1\u01c8\u01cf\u01d4"+
		"\u01d9\u01df\u01e5\u01fc\u0204\u0206\u0211\u0216\u0220\u022c\u0232\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}