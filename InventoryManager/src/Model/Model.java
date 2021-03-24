package Model;


import java.io.IOException;
import java.util.*;

public class Model{
    public static Model _instance;
    private TreeMap<String,Product> riseAlphabetical;
    private TreeMap<String,Product> decreaseAlphabetical;
    private LinkedHashMap<String,Product> byOrder;


    private Model(){
        riseAlphabetical=new TreeMap<String, Product>();
        decreaseAlphabetical=
                new TreeMap<String, Product>(new Comparator<String>()
                {
                    public int compare(String o1, String o2)
                    {
                        return o2.compareTo(o1);
                    }
                });
        byOrder=new LinkedHashMap<String, Product>();
    }

    public static Model get_instance() {
        if(_instance==null)
            return _instance= new Model();
        return _instance;
    }

    public TreeMap<String, Product> getRiseAlphabetical() {
        return riseAlphabetical;
    }



    public TreeMap<String, Product> getDecreaseAlphabetical() {
        return decreaseAlphabetical;
    }


    public LinkedHashMap<String, Product> getByOrder() {
        return byOrder;
    }


    public static Model getModel() throws IOException {
        if (_instance != null)
            return _instance;
        return new Model();
    }


    public void readProd(String str,Product p) {
        byOrder.put(str,p);
        riseAlphabetical.put(str,p);
        decreaseAlphabetical.put(str,p);
    }

    public void clearMaps() {
        byOrder.clear();
        riseAlphabetical.clear();
        decreaseAlphabetical.clear();
    }

    public void addToAllMaps(LinkedHashMap<String,Product> m){
        byOrder.putAll(m);
        riseAlphabetical.putAll(m);
        decreaseAlphabetical.putAll(m);
    }

    public void undoAllMaps(Memento lastVal){
        byOrder.putAll(lastVal.getByOrder());
        riseAlphabetical.putAll(lastVal.getRiseAlphabetical());
        decreaseAlphabetical.putAll(lastVal.getDecreaseAlphabetical());
    }

    public void removeProduct(String code) {
        byOrder.remove(code);
        decreaseAlphabetical.remove(code);
        riseAlphabetical.remove(code);
    }

    public HashSet<Client> getUniqueClientsList(){
        HashSet<Client> cSet=new HashSet<Client>();
        for (Map.Entry<String, Product> entry : byOrder.entrySet()){
            cSet.add(entry.getValue().getClient());
        }
        return cSet;
    }
}
