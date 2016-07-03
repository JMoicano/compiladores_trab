
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 2012100265
 */
public class AST {
    private int type;
    private String name;
    private int line;
    private double value;
    private TpPrimitivo tpPrimitivo;
    private final ArrayList <AST> children;
    
    public AST(int type){
        this.type = type;
        this.children = new ArrayList<>();
    }
    
    public AST(int type, String name){
        this(type);
        this.name = name;
    }
    
    public AST(int type, String name, int line){
        this(type);
        this.name = name;
        this.line = line;
    }
    
    public AST(int type, int value){
        this(type);
        this.value = value;
    }
    
    public AST(int type, double value){
        this(type);
        this.value = value;
    }
    
    public AST(int type, TpPrimitivo tpPrimitivo){
        this(type);
        this.tpPrimitivo = tpPrimitivo;
    }
    
    public void add(AST n){
        children.add(n);
    }
    
    public AST getChild(int i){
        return children.get(i);
    }
    
    public int getChildCount(){
        return children.size();
    }
    
    public double getValue(){
        return value;
    }
    
    public String getName(){
        return name;
    }
    
    public int getLine(){
        return line;
    }
    
    public int getType(){
        return type;
    }
    
    public TpPrimitivo getTpPrimitivo(){
        return tpPrimitivo;
    }
}