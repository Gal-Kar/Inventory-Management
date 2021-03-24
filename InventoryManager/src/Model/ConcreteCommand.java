package Model;

import MessageObserver.Message;
import MessageObserver.Sender;

import java.io.IOException;
import java.util.*;

public class ConcreteCommand implements Command, Sender{
    private Model m=Model.get_instance();
    private Memento lastVal;
    private MyFileHandler fileHandler;

    public ConcreteCommand() {
        this.lastVal = null;
        fileHandler=new MyFileHandler();
        LinkedHashMap<String,Product> map=fileHandler.fileToMap();
        m.addToAllMaps(map);
    }

    @Override
    public boolean readProductFromSystem(String[] inputs, boolean promotionsNotify) throws IOException {
        for(int i=0;i< inputs.length;i++)//if one field is empty return false
            if(inputs[i].isEmpty())
                return false;
        if(!GeneralFuncs.checkStringIfOnlyNumbers(inputs[2])||!GeneralFuncs.checkStringIfOnlyNumbers(inputs[3]))
            return false;
        if(Integer.parseInt(inputs[2])<0||Integer.parseInt(inputs[3])<0)
            return false;
        Client c=new Client(inputs[4],inputs[5],promotionsNotify);
        Product p=new Product(GeneralFuncs.removeSpaces(inputs[1]),Integer.parseInt(inputs[2]),Integer.parseInt(inputs[3]),c,inputs[0]);
        fileHandler.addProd(p);
        lastVal= new Memento(m.getRiseAlphabetical(), m.getDecreaseAlphabetical(),m.getByOrder());
        m.readProd(GeneralFuncs.removeSpaces(inputs[0]),p);
        return true;
    }

    @Override
    public String getMapString(String sortMethod) {
        String str=new String();
        if(sortMethod.toLowerCase().equals("rise")){
            for (Map.Entry<String,Product> entry:m.getRiseAlphabetical().entrySet()){
                str+="BarCode: "+entry.getKey()+" "+entry.getValue().toString()+"\n";
            }
            return str;
        }

        if(sortMethod.toLowerCase().equals("decrease")){
            for (Map.Entry<String,Product> entry:m.getDecreaseAlphabetical().entrySet()){
                str+="BarCode:"+entry.getKey()+" "+entry.getValue().toString()+"\n";
            }
            return str;
        }
        for (Map.Entry<String,Product> entry:m.getByOrder().entrySet()){
            str+="BarCode:"+entry.getKey()+" "+entry.getValue().toString()+"\n";
        }
        return str;
    }

    @Override
    public Product searchProductByCode(String code) {
        return m.getByOrder().get(GeneralFuncs.removeSpaces(code));
    }

    @Override
    public boolean removeLast() throws IOException {
        if(lastVal==null)
            return false;
        m.clearMaps();
        m.undoAllMaps(lastVal);
        fileHandler.removeAll();
        fileHandler.addAll(m.getByOrder());
        lastVal=null;
        return true;
    }

    @Override
    public boolean removeProduct(String code) throws IOException {
        if(m.getRiseAlphabetical().get(code)==null)
            return false;
        lastVal= new Memento(m.getRiseAlphabetical(), m.getDecreaseAlphabetical(),m.getByOrder());
        m.removeProduct(code);
        fileHandler.removeProductByCode(code);
        return true;
    }

    @Override
    public void removeAll() throws IOException {
        lastVal= new Memento(m.getRiseAlphabetical(), m.getDecreaseAlphabetical(),m.getByOrder());
        m.clearMaps();
        fileHandler.removeAll();
    }

    @Override
    public List<ProductProfit> getValue() {
        List<ProductProfit> v=new ArrayList<ProductProfit>();
        for (Map.Entry<String, Product> entry : m.getByOrder().entrySet()) {
            v.add(new ProductProfit(entry.getKey(),entry.getValue().getProductName(),entry.getValue().getProfit()));
        }
        return v;
    }

    @Override
    public boolean SendMessage(Client c) {
        return c.getPromotionInterest();
    }


    @Override
    public String SendMessagesToAll(String msgToSend) {
        Message msg=new Message(msgToSend);
        String str=new String();
        HashSet<Client> cSet=m.getUniqueClientsList();
        Iterator<Client> it = cSet.iterator();
        while (it.hasNext()){
            str+=MessageReceivedVerification(it.next(),msg);
        }
        return str;
    }

    @Override
    public List<String> allNotifiedClientsStringList() {
        List<String> strArr=new ArrayList<String>();
        HashSet<Client> cSet=m.getUniqueClientsList();
        Iterator<Client> it = cSet.iterator();
        Client tClient;
        while (it.hasNext()){
            tClient=it.next();
            if(tClient.getPromotionInterest())
                strArr.add(tClient.toString());
        }
        return strArr;
    }

    @Override
    public String MessageReceivedVerification(Client c, Message msg) {
        if(SendMessage(c)){
            return c.getName()+"\t"+c.getPhoneNum()+"\n"+
             c.receiveMSG(this,msg)+"\n\n";
        }
        return "";
    }
}
