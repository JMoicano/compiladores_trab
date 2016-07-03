
/**
 *
 * @author John
 */
public enum TpPrimitivo {

    INTEIRO, REAL, CARACTERE, LITERAL, LOGICO, INDEFINIDO;

    public static TpPrimitivo translateTipo(String value) {
        return TpPrimitivo.valueOf(value.toUpperCase());
    }

    public static TpPrimitivo translateTipo(int value) {
        switch (value) {
            case 7:
                return INTEIRO;
            case 8:
                return REAL;
            case 9:
                return CARACTERE;
            case 10:
                return LITERAL;
            case 11:
                return LOGICO;
            default:
                return INDEFINIDO;
        }
    }

    public static TpPrimitivo tabAttr(TpPrimitivo t1, TpPrimitivo t2) {
        if ((t1 == LITERAL && t2 != LITERAL) || (t1 != LITERAL && t2 == LITERAL)
                || (t1 == LOGICO && t2 != LOGICO) || (t1 != LOGICO && t2 == LOGICO)
                || (t1 == INTEIRO && t2 == REAL)) {
            return INDEFINIDO;
        }
        return t1;
    }

    public static TpPrimitivo tabComp(TpPrimitivo t1, TpPrimitivo t2) {
        if ((t1 == LITERAL && t2 != LITERAL) || (t1 != LITERAL && t2 == LITERAL)) { //t1 == LITERAL || t2 = LITERAL
            return INDEFINIDO;
        }
        return LOGICO;
    }

    public static TpPrimitivo tabBasicOp(TpPrimitivo t1, TpPrimitivo t2) {
        if (t1 == LITERAL || t2 == LITERAL) {
            return INDEFINIDO;
        } else if (t1 == REAL || t2 == REAL) {
            return REAL;
        } else {
            return INTEIRO;
        }
    }

    public static TpPrimitivo tabTermo(TpPrimitivo t1) {
        if (t1 == LITERAL) {
            return INDEFINIDO;
        }
        return t1;
    }
}
