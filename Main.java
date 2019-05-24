import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
/**
 * JavaFX stuff
 */
public class Main extends Application implements EventHandler<ActionEvent>
{
    Stage window;
    Scene start, main;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Game");
        
        //starting menu
        GridPane layout1 = new GridPane();
        layout1.setPadding(new Insets(10,10,10,10));
        layout1.setVgap(8);
        layout1.setHgap(10);
        
        Label label1 = new Label("Enter name:");
        GridPane.setConstraints(label1,0,0);
        
        TextField input = new TextField();
        GridPane.setConstraints(input,1,0);
        
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> window.setScene(main));
        GridPane.setConstraints(startButton,2,0);
        
        layout1.getChildren().addAll(label1, input, startButton);
        layout1.setAlignment(Pos.CENTER);
        start = new Scene(layout1,960,540);
        
        //main menu
        Button travel = new Button("Travel");
        travel.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(travel,1,1);
        
        Button rest = new Button("Rest");
        rest.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(rest,1,2);
        
        Button forage = new Button("Forage");
        forage.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(forage,1,3);
        
        Button viewInv = new Button("View Inventory");
        viewInv.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(viewInv,1,4);
        
        GridPane layout2 = new GridPane();
        layout2.setPadding(new Insets(10,10,10,10));
        layout2.setVgap(8);
        layout2.setHgap(10);
        
        layout2.getChildren().addAll(travel, rest, forage, viewInv);
        layout2.setAlignment(Pos.CENTER_LEFT);
        main = new Scene(layout2,960,540);
        
        
        //start
        window.setScene(start);
        window.show();
    }
    
    public void handle(ActionEvent event)
    {
        if(event.getSource() == null)
            System.out.println("yo");
    }
}
