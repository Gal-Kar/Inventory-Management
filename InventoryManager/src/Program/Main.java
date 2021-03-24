package Program;

import javafx.application.Application;
import javafx.stage.Stage;
import  Model.*;
import  Controller.*;
import View.*;

import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ConcreteCommand cc=new ConcreteCommand();
        MainWindow theView=new MainWindow(primaryStage);
        Controller theController=new Controller(cc,theView);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
