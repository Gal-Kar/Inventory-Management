package Model;

import MessageObserver.Message;
import MessageObserver.Receiver;
import MessageObserver.Sender;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Receiver, Serializable {
    private String name;
    private String phoneNum;
    private Boolean promotionInterest;

    public Client(String name, String phoneNum, Boolean promotionInterest) {
        this.name = name;
        this.phoneNum = GeneralFuncs.removeSpaces(phoneNum);
        this.promotionInterest = promotionInterest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Boolean getPromotionInterest() {
        return promotionInterest;
    }

    public void setPromotionInterest(Boolean promotionInterest) {
        this.promotionInterest = promotionInterest;
    }

    @Override
    public String toString() {
        if(promotionInterest)
        return "Client [" +
                "name: " + name +
                ", Phone number: " + phoneNum +
                ", Interest in promotion]"
                ;

        return "Client [" +
                "name: " + name +
                ", Phone number: " + phoneNum +
                ", Not interest in promotion]"
                ;
    }

    @Override
    public String receiveMSG(Sender s, Message msg) {
        String temp="--------------------------------------------\n";
        temp+=msg.toString();
        temp+="--------------------------------------------\n";
        return temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) &&
                Objects.equals(phoneNum, client.phoneNum) &&
                Objects.equals(promotionInterest, client.promotionInterest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNum, promotionInterest);
    }
}
