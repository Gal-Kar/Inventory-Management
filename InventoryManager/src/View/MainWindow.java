package View;

import Model.GeneralFuncs;
import Model.ProductProfit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import javafx.scene.text.*;
import javafx.stage.Stage;


import java.util.List;


public class MainWindow {
    private final Scene scene;

    private HBox mainHB;
    private VBox mainVB;
    private VBox optionVB;

    private Button addProductOptionB;
    private Button searchProductAndMsgOptionB;
    private Button showAllProductsOptionB;
    private Button deleteOptionB;
    private Button profitOptionB;
    private  Button sendUpdateOptionB;



    private VBox addProductVB;
    private TextField[] addProductTF;
    private Button addAddProductB;
    private ComboBox saleNotify;

    private VBox searchProductVB;
    private TextField searchProductTF;
    private Button searchProductB;
    private Button showNotifiedClientsB;



    private VBox showAllProductsVB;
    private Button sortOpButton1,sortOpButton2,sortOpButton3;
    private TextArea  sortedProducts;

    private VBox deleteVB;
    private Button undoB;
    private Button deleteAllB;
    private Button deleteWithCodeB;
    private TextField deleteTF;

    private VBox profitVB;
    private TableView table;
    TableColumn pCode,pName, pProfit;
    private Text totalProfitT;

    private VBox sendUpdateVB;
    private TextArea msgText;
    private Button sendMsgB;



    public MainWindow(Stage stage) {
        mainVB=new VBox();
        mainHB=new HBox();
        optionVB=new VBox();
        //text style

        Text optionTitle =new Text("Menu   ");
        optionTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 65));

        optionTitle.setFill(Color.web("#fccb05"));
        optionTitle.setStroke(Color.GOLD);
        optionTitle.setStrokeWidth(2);
        //optionTitle.

        //buttons style
        addProductOptionB =new Button("Add Product");
        addProductOptionB.setMinSize(150,45);
        addProductOptionB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));


        searchProductAndMsgOptionB =new Button("Search");
        searchProductAndMsgOptionB.setMinSize(150,45);
        searchProductAndMsgOptionB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        showAllProductsOptionB=new Button("Show");
        showAllProductsOptionB.setMinSize(150,45);
        showAllProductsOptionB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));



        deleteOptionB =new Button("Delete");
        deleteOptionB.setMinSize(150,45);
        deleteOptionB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        profitOptionB=new Button("Profits");
        profitOptionB.setMinSize(150,45);
        profitOptionB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        sendUpdateOptionB=new Button("Notify");
        sendUpdateOptionB.setMinSize(150,45);
        sendUpdateOptionB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        optionVB.setAlignment(Pos.CENTER);
        optionVB.getChildren().addAll(optionTitle, addProductOptionB, searchProductAndMsgOptionB,
                showAllProductsOptionB, deleteOptionB,profitOptionB,sendUpdateOptionB);
        optionVB.setMargin(optionTitle,new Insets(80,0,10,0));
        optionVB.setMargin(searchProductAndMsgOptionB,new Insets(10,0,10,0));
        optionVB.setMargin(deleteOptionB,new Insets(10,0,10,0));
        optionVB.setMargin(sendUpdateOptionB,new Insets(10,0,10,0));


        createAddProductVB();
        createSearchProductVB();
        createShowAllProductsVB();
        createDeleteProductsVB();
        createProfitsVB();
        createSendMsgVB();

        mainHB.getChildren().addAll(optionVB);
        mainHB.setMargin(optionVB,new Insets(0,100,20,0));
        mainVB.getChildren().addAll(mainHB);
        optionVB.setAlignment(Pos.CENTER);
        mainVB.setAlignment(Pos.TOP_CENTER);

        //Background
        BackgroundSize backgroundSize = new BackgroundSize(1200,
                600,
                true,
                true,
                true,
                false);
        BackgroundImage background = new BackgroundImage(new Image("Background.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);



        mainVB.setBackground(new Background(background));
        scene = new Scene(mainVB,1200,594);
        stage.setTitle("Shop Inventory");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    public void createAddProductVB(){
        addProductVB=new VBox();
        addProductTF=new TextField[6];

        addProductTF[0]=new TextField();
        addProductTF[0].setPromptText("Product BarCode");

        addProductTF[1]=new TextField();
        addProductTF[1].setPromptText("Product Name");


        addProductTF[2]=new TextField();
        addProductTF[2].setPromptText("Store Purchase price");


        addProductTF[3]=new TextField();
        addProductTF[3].setPromptText("Costumer Purchase price");


        addProductTF[4]=new TextField();
        addProductTF[4].setPromptText("Costumer Name");


        addProductTF[5]=new TextField();
        addProductTF[5].setPromptText("Costumer Phone Number");


        saleNotify=new ComboBox();
        saleNotify.getItems().addAll("I would like to receive notifications about promotions",
                "I would not like to receive notifications about promotions");
        saleNotify.getSelectionModel().select(0);

        addAddProductB=new Button("send");
        addProductVB.getChildren().addAll(addProductTF);
        addProductVB.getChildren().addAll(saleNotify,addAddProductB);
        addProductVB.setAlignment(Pos.CENTER);

        addProductVB.setMargin(addProductTF[0],new Insets(150,50,0,100));
        for(int i=1;i<addProductTF.length;i++){
            addProductVB.setMargin(addProductTF[i],new Insets(10,50,0,100));
        }
        addProductVB.setMargin(saleNotify,new Insets(10,50,0,100));
        addProductVB.setMargin(addAddProductB,new Insets(10,50,0,100));
    }
    public void createSearchProductVB(){
        HBox searchProductHB =new HBox();
        searchProductVB =new VBox();
        searchProductTF=new TextField();
        searchProductTF.setPromptText("Product Barcode");
        searchProductB =new Button("Search");
        searchProductHB.getChildren().addAll(searchProductTF, searchProductB);
        searchProductHB.setAlignment(Pos.CENTER);
        searchProductVB.setAlignment(Pos.BASELINE_CENTER);
        searchProductHB.setMargin(searchProductTF,new Insets(200,10,120,130));
        searchProductHB.setMargin(searchProductB,new Insets(200,50,120,0));
        showNotifiedClientsB =new Button("show notified\nclients");

        showNotifiedClientsB.setTextAlignment(TextAlignment.CENTER);
        showNotifiedClientsB.setFont(Font.font("Verdana",FontWeight.BOLD ,13));

        searchProductVB.getChildren().addAll(searchProductHB, showNotifiedClientsB);
        searchProductVB.setMargin(showNotifiedClientsB,new Insets(0,0,0,50));
    }
    public void createShowAllProductsVB(){//need to add products list
        showAllProductsVB =new VBox();
        Text sortText=new Text("Order By");
        sortText.setFont(Font.font("Verdana", 20));
        HBox sortHB=new HBox();
        sortOpButton1=new Button("A-Z");
        sortOpButton2=new Button("Z-A");
        sortOpButton3=new Button("By Order");
        sortHB.getChildren().addAll(sortOpButton1,sortOpButton2,sortOpButton3);
        sortedProducts=new TextArea();
        sortedProducts.setEditable(false);
        sortedProducts.setPrefWidth(715);
        sortedProducts.setPrefHeight(350);
        sortedProducts.setFont(Font.font("Verdana",FontWeight.BOLD, 15));
        showAllProductsVB.getChildren().addAll(sortText,sortHB,sortedProducts);
        sortHB.setMargin(sortOpButton2,new Insets(0,10,0,10));
        showAllProductsVB.setMargin(sortText,new Insets(0,0,10,0));
        showAllProductsVB.setMargin(sortHB,new Insets(0,0,5,0));
        sortHB.setAlignment(Pos.CENTER);
        showAllProductsVB.setAlignment(Pos.BOTTOM_CENTER);
    }
    public void createDeleteProductsVB(){
        deleteVB=new VBox();
        undoB =new Button("Undo Last Action");
        deleteAllB=new Button("Delete All");
        deleteVB.getChildren().addAll(undoB,deleteAllB);
        HBox deleteHB=new HBox();
        deleteWithCodeB=new Button("Search And Delete");
        deleteTF=new TextField();
        deleteHB.getChildren().addAll(deleteTF,deleteWithCodeB);
        deleteVB.getChildren().addAll(deleteHB);
        deleteVB.setAlignment(Pos.CENTER);
        deleteVB.setMargin(deleteAllB,new Insets(10,0,10,0));
    }
    public void createProfitsVB(){
        profitVB=new VBox();
        Label label = new Label("Address Book");
        label.setFont(new Font("Verdana", 20));
        table=new TableView();
        table.setEditable(false);
        pCode = new TableColumn("Barcode");
        pName = new TableColumn("Product");
        pProfit = new TableColumn("Profit");
        table.getColumns().addAll(pCode,pName, pProfit);
        pCode.setMinWidth(200);
        pName.setMinWidth(200);
        pProfit.setMinWidth(200);
        totalProfitT=new Text("Total profits:");
        totalProfitT.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        profitVB.getChildren().addAll(table,totalProfitT);
        profitVB.setAlignment(Pos.BOTTOM_CENTER);
    }
    public void createSendMsgVB(){
        sendUpdateVB=new VBox();
        sendMsgB =new Button("Send Update");
        msgText=new TextArea();
        msgText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        msgText.setPrefSize(400,200);
        sendUpdateVB.getChildren().addAll(msgText,sendMsgB);
        sendUpdateVB.setAlignment(Pos.CENTER);
        sendUpdateVB.setMargin(msgText,new Insets(0,0,10,0));
    }

    public void updateSceneToAddProd(){
        mainHB.getChildren().clear();
        mainHB.getChildren().addAll(optionVB,addProductVB);
        mainHB.setMargin(optionVB,new Insets(0,200,80,0));
    }
    public void updateSceneToSearchProd(){
        mainHB.getChildren().clear();
        mainHB.getChildren().addAll(optionVB,searchProductVB);
        searchProductVB.setAlignment(Pos.TOP_CENTER);
        mainHB.setMargin(optionVB,new Insets(0,200,80,0));

    }
    public void updateSceneToShow(){
        mainHB.getChildren().clear();
        sortedProducts.clear();
        mainHB.getChildren().addAll(optionVB, showAllProductsVB);
        showAllProductsVB.setAlignment(Pos.BOTTOM_CENTER);
        mainHB.setMargin(optionVB,new Insets(0,200,80,0));
        mainHB.setMargin(showAllProductsVB,new Insets(0,0,0,0));
    }
    public void updateSceneToDelete(){
        mainHB.getChildren().clear();
        mainHB.getChildren().addAll(optionVB, deleteVB);
        mainHB.setMargin(optionVB,new Insets(0,300,80,0));
        mainHB.setMargin(deleteVB,new Insets(100,0,80,0));
    }
    public void updateSceneToProfits(List<ProductProfit> v){
        mainHB.getChildren().clear();
        mainHB.getChildren().addAll(optionVB,profitVB);
        pCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        pName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        pProfit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        table.getItems().clear();
        table.getItems().addAll(v);
        int sum=0;
        for(int i=0;i<v.size();i++)
            sum+=v.get(i).getProfit();
        totalProfitT.setText("Total profits: "+sum);
        mainHB.setMargin(optionVB,new Insets(0,200,80,0));
        mainHB.setMargin(profitVB,new Insets(0,0,20,0));
    }
    public void updateSceneToSendMsg(){
        mainHB.getChildren().clear();
        mainHB.getChildren().addAll(optionVB,sendUpdateVB);
        mainHB.setMargin(optionVB,new Insets(0,300,80,0));
    }


    public void addTextToShow(String text){
        sortedProducts.clear();
        sortedProducts.setText(sortedProducts.getText()+text);
    }

    public String[] addProdTextField(){
        String[] str=new String[addProductTF.length];
        for(int i=0;i<addProductTF.length;i++)
            str[i]=addProductTF[i].getText();
        for(int i=0;i<addProductTF.length;i++)
            addProductTF[i].clear();
        return str;
    }
    public Boolean wantPromotions(){
        boolean b=saleNotify.getValue().toString().equals("I would like to receive notifications about promotions");
        return b;
    }
    public String getSearchTextField(){
        String temp= searchProductTF.getText();
        searchProductTF.clear();
        return temp;
    }
    public String getDeleteTextField(){
        String temp= deleteTF.getText();
        deleteTF.clear();
        return GeneralFuncs.removeSpaces(temp);
    }

    public boolean isEmptyMsg(){
        return msgText.getText().isEmpty();
    }

    public String getMsgText(){
        String temp= "\n"+msgText.getText()+"\n";
        msgText.clear();
        return temp;
    }

    public void addEventToAddProductOptionB(EventHandler<ActionEvent> event){
        addProductOptionB.setOnAction(event);
    }
    public void addEventToSearchProductOptionB(EventHandler<ActionEvent> event){
        searchProductAndMsgOptionB.setOnAction(event);
    }
    public void addEventToShowProductOptionB(EventHandler<ActionEvent> event){
        showAllProductsOptionB.setOnAction(event);
    }
    public void addEventToDeleteOptionB(EventHandler<ActionEvent> event){
        deleteOptionB.setOnAction(event);
    }
    public void addEventToProfitOptionB(EventHandler<ActionEvent> event){
        profitOptionB.setOnAction(event);
    }
    public void addEventToSendUpdateOptionB(EventHandler<ActionEvent> event){
        sendUpdateOptionB.setOnAction(event);
    }


    public void addEventToAddProductB(EventHandler<ActionEvent> event){
        addAddProductB.setOnAction(event);
    }

    public void addEventToSearchProductB(EventHandler<ActionEvent> event){
        searchProductB.setOnAction(event);
    }
    public void addEventToShowNotifiedClientsB(EventHandler<ActionEvent> event){
        showNotifiedClientsB.setOnAction(event);
    }

    public void addEventToSortOpButton1(EventHandler<ActionEvent> event){
        sortOpButton1.setOnAction(event);
    }
    public void addEventToSortOpButton2(EventHandler<ActionEvent> event){
        sortOpButton2.setOnAction(event);
    }
    public void addEventToSortOpButton3(EventHandler<ActionEvent> event){
        sortOpButton3.setOnAction(event);
    }
    public void addEventToSendMsgB(EventHandler<ActionEvent> event){
        sendMsgB.setOnAction(event);
    }

    public void addEventToDeleteLastB(EventHandler<ActionEvent> event){
        undoB.setOnAction(event);
    }
    public void addEventToDeleteAllB(EventHandler<ActionEvent> event){
        deleteAllB.setOnAction(event);
    }
    public void addEventToDeleteWithCodeB(EventHandler<ActionEvent> event){
        deleteWithCodeB.setOnAction(event);
    }


    public void messageWindow(String text,Boolean good) {
        MessageWindow m=new MessageWindow(new Stage(), text,good);
    }

    public void productWindow(String text) {
        ProductWindow p=new ProductWindow(new Stage(), text);
    }
    public void openPersonMsgWindow(String text) {
        PersonMsgWindow p=new PersonMsgWindow(new Stage(), text);
    }

}
