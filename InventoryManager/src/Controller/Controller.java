package Controller;

import Model.*;
import View.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Controller {

    private ConcreteCommand systemCommand;
    private MainWindow theView;

    public Controller(ConcreteCommand c, MainWindow v) throws IOException {


        systemCommand =c;
        theView=v;
        //choose option window
        //add product window
        EventHandler<ActionEvent> EventToAddProductOptionB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.updateSceneToAddProd();
                addProdWindow();
            }
        };
        theView.addEventToAddProductOptionB(EventToAddProductOptionB);
        //search product and clients to notify window
        EventHandler<ActionEvent> EventToSearchProductOptionB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.updateSceneToSearchProd();
                searchProdWindow();
            }
        };
        theView.addEventToSearchProductOptionB(EventToSearchProductOptionB);
        //show window
        EventHandler<ActionEvent> EventToShowProductOptionB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.updateSceneToShow();
                showProdWindow();
            }
        };
        theView.addEventToShowProductOptionB(EventToShowProductOptionB);
        //delete window
        EventHandler<ActionEvent> EventToDeleteOptionB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.updateSceneToDelete();
                deleteWindow();
            }
        };
        theView.addEventToDeleteOptionB(EventToDeleteOptionB);
        //profits window
        EventHandler<ActionEvent> EventToProfitOptionB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.updateSceneToProfits(systemCommand.getValue());

            }
        };
        theView.addEventToProfitOptionB(EventToProfitOptionB);

        EventHandler<ActionEvent> EventToSendUpdateOptionB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.updateSceneToSendMsg();
                sendMsgWindow();
            }
        };
        theView.addEventToSendUpdateOptionB(EventToSendUpdateOptionB);


    }

    public void addProdWindow(){
        EventHandler<ActionEvent> EventToAddProductB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    if(systemCommand.readProductFromSystem(theView.addProdTextField(),theView.wantPromotions()))
                        theView.messageWindow("Added",true);
                    else
                        theView.messageWindow("Error!\nall fields must be filled and\nall number fields must be greater\n" +
                                "than zero",false);
                } catch (IOException e) {
                }
            }
        };
        theView.addEventToAddProductB(EventToAddProductB);
    }

    public void searchProdWindow(){
        EventHandler<ActionEvent> EventToSearchProductB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                Product p= systemCommand.searchProductByCode(GeneralFuncs.removeSpaces(theView.getSearchTextField()));
                if(p==null)
                    theView.messageWindow("Not Found",false);
                else
                    theView.productWindow(p.toString());
            }
        };
        theView.addEventToSearchProductB(EventToSearchProductB);

        EventHandler<ActionEvent> EventToShowNotifiedClientsB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                List<String> str=systemCommand.allNotifiedClientsStringList();
                NotificationListWindow nw=new NotificationListWindow(new Stage(),str);
                Thread t=new Thread(nw);
                t.start();
            }
        };
        theView.addEventToShowNotifiedClientsB(EventToShowNotifiedClientsB);
    }

    public void showProdWindow(){
        EventHandler<ActionEvent> EventToSortOpButton1=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.addTextToShow(systemCommand.getMapString("rise"));
            }
        };
        theView.addEventToSortOpButton1(EventToSortOpButton1);

        EventHandler<ActionEvent> EventToSortOpButton2=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.addTextToShow(systemCommand.getMapString("decrease"));
            }
        };
        theView.addEventToSortOpButton2(EventToSortOpButton2);

        EventHandler<ActionEvent> EventToSortOpButton3=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                theView.addTextToShow(systemCommand.getMapString("order"));
            }
        };
        theView.addEventToSortOpButton3(EventToSortOpButton3);


    }

    public void deleteWindow(){
        EventHandler<ActionEvent> EventToDeleteLastB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    if(systemCommand.removeLast())
                        theView.messageWindow("Undo Successfully",true);
                    else
                        theView.messageWindow("Error",false);
                } catch (IOException e) {
                    theView.messageWindow("Error",false);
                }
            }
        };
        theView.addEventToDeleteLastB(EventToDeleteLastB);

        EventHandler<ActionEvent> EventToDeleteAllB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    systemCommand.removeAll();
                } catch (IOException e) {
                }
                theView.messageWindow("Removed",true);
            }
        };
        theView.addEventToDeleteAllB(EventToDeleteAllB);

        EventHandler<ActionEvent> EventToDeleteWithCodeB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    if(systemCommand.removeProduct(theView.getDeleteTextField()))
                        theView.messageWindow("Deleted",true);
                    else
                        theView.messageWindow("Didn't Found",false);
                } catch (IOException e) {
                }
            }
        };
        theView.addEventToDeleteWithCodeB(EventToDeleteWithCodeB);
    }

    public void sendMsgWindow(){
        EventHandler<ActionEvent> EventToSendMsgB=new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(theView.isEmptyMsg())
                    theView.messageWindow("Can't send empty message",false);
                else{
                    String str=systemCommand.SendMessagesToAll(theView.getMsgText());
                    theView.openPersonMsgWindow(str);
                }
            }
        };
        theView.addEventToSendMsgB(EventToSendMsgB);
    }
}
