package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

class ProductWindow {

    public ProductWindow(Stage stage, String t) {
        Text er=new Text(t);
        er.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        VBox vb=new VBox();
        vb.getChildren().add(er);
        vb.setAlignment(Pos.CENTER);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Product");
        Scene scene=new Scene(vb,700,200, Color.web("#fccb05"));
        //stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
