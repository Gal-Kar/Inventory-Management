package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
//this window will pop when we want to see all the clients that has agreed to get promotion
//using thread so it can print the clients in a 2 second suspense
//can run simultaneity to the main window
public class NotificationListWindow implements Runnable{

    private List<String> text;
    private Text textArea;

    public NotificationListWindow(Stage stage,List<String> text){
        this.text=text;
        textArea =new Text();
        textArea =new Text("Customers who want promotions:\n\n");

        textArea.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        VBox vb=new VBox();
        vb.getChildren().add(textArea);
        vb.setAlignment(Pos.CENTER);

        Scene scene=new Scene(vb,800,600);

        stage.setResizable(false);
        stage.setTitle("notification verifying");
        stage.setScene(scene);
        stage.show();
    }

    public void updateTextArea(String txt){
        textArea.setText(textArea.getText()+txt);
    }

    @Override
    public void run() {
        for(int i=0;i<text.size();i++){
            try {
                Thread.sleep(2000);
                updateTextArea(text.get(i)+"\n");
            } catch (InterruptedException e) {

            }

        }
    }
}
