package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonMsgWindow {

    public PersonMsgWindow(Stage stage,String t) {
        TextArea textArea=new TextArea("Customers who received the message:\n\n"+t);
        textArea.setEditable(false);
        textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        textArea.setPrefSize(800,600);
        VBox vb=new VBox();
        vb.getChildren().add(textArea);
        vb.setAlignment(Pos.CENTER);
        stage.initModality(Modality.APPLICATION_MODAL);

        Scene scene=new Scene(vb,800,600);
        stage.setResizable(false);
        stage.setTitle("notification verifying");
        stage.setScene(scene);
        stage.show();
    }
}
