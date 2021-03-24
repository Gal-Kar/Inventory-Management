package View;

import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageWindow {
    //this window used to pop to send error(good=false) messages or updates(good=true)
    public MessageWindow(Stage stage, String t, Boolean goodMessage) {
        Text er = new Text(t);
        er.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        if (!goodMessage) {
            er.setFill(Color.RED);
            stage.setTitle("Error");
        } else
            er.setFill(Color.GREEN);
        VBox vb = new VBox();
        vb.getChildren().add(er);
        vb.setAlignment(Pos.CENTER);
        stage.initModality(Modality.APPLICATION_MODAL);

        Scene scene = new Scene(vb, 300, 170);
        stage.setResizable(false);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }

}

