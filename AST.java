
import java.util.ArrayList;

/**
 *
 * @author 2012100265
 */
public class AST {

    private int type;
    private TipoAST tipo;

    public void setTipo(TipoAST tipo) {
        this.tipo = tipo;
    }

    public TipoAST getTipo() {
        return tipo;
    }
    private String name;
    private double value;
    private TpPrimitivo tpPrimitivo;
    private final ArrayList<AST> children;

    public ArrayList<AST> getChildren() {
        return children;
    }

    public AST(int type) {
        this.type = type;
        this.children = new ArrayList<>();
    }

    public AST(int type, String name) {
        this(type);
        this.name = name;
        this.tipo = TipoAST.STRING;
    }

    public AST(int type, String name, int escopo) {
        this(type);
        this.name = name;
        this.value = escopo;
    }

    public AST(int type, int value) {
        this(type);
        this.value = value;
        this.tipo = TipoAST.INT;
    }

    public AST(int type, double value) {
        this(type);
        this.value = value;
        this.tipo = TipoAST.DOUBLE;
    }

    public AST(int type, TpPrimitivo tpPrimitivo) {
        this(type);
        this.tpPrimitivo = tpPrimitivo;
    }

    public void add(AST n) {
        children.add(n);
    }

    public AST getChild(int i) {
        return children.get(i);
    }

    public int getChildCount() {
        if (children != null) {
            return children.size();
        } else {
            return 0;
        }
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public TpPrimitivo getTpPrimitivo() {
        return tpPrimitivo;
    }

    @Override
    public String toString() {
        String ret;
        if (tipo == TipoAST.STRING) {
            ret = name;
        } else if (tipo == TipoAST.INT) {
            int saida = (int) value;
            ret = String.valueOf(saida);
        } else {
            ret = String.valueOf(value);
        }
        return ret;
    }
}
