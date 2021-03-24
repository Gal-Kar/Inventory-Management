package Model;


import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Memento{
    private TreeMap<String,Product> riseAlphabetical;
    private TreeMap<String,Product> decreaseAlphabetical;
    private LinkedHashMap<String,Product> byOrder;

    public Memento(TreeMap<String,Product> riseAlphabetical, TreeMap<String, Product> decreaseAlphabetical,
                   LinkedHashMap<String, Product> byOrder) {
        this.riseAlphabetical=new TreeMap<String, Product>();
        this.decreaseAlphabetical=new TreeMap<String, Product>();
        this.byOrder=new LinkedHashMap<String, Product>();
        this.riseAlphabetical.putAll(riseAlphabetical);
        this.decreaseAlphabetical.putAll(decreaseAlphabetical);
        this.byOrder.putAll(byOrder);
    }

    public TreeMap<String,Product> getRiseAlphabetical() {
        return riseAlphabetical;
    }

    public TreeMap<String, Product> getDecreaseAlphabetical() {
        return decreaseAlphabetical;
    }

    public LinkedHashMap<String,Product> getByOrder() {
        return byOrder;
    }
}
