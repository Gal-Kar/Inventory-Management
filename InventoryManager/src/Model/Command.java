package Model;

import java.io.IOException;
import java.util.List;

public interface Command {
    boolean readProductFromSystem(String[] inputs,boolean promotionsNotify) throws IOException;
    String getMapString(String sortOp1);
    Product searchProductByCode(String code);
    boolean removeLast() throws IOException;
    boolean removeProduct(String code) throws IOException;
    void removeAll() throws IOException;
    List<ProductProfit> getValue();
    String SendMessagesToAll(String msg);
    List<String> allNotifiedClientsStringList();
}
