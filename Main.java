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
    Game game;
    
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
        Label distance = new Label("Distance left: ");
        GridPane.setConstraints(distance, 0, 1);
        Label health = new Label("Health: ");
        GridPane.setConstraints(health, 0, 2);
        Label hunger = new Label("Hunger: ");
        GridPane.setConstraints(hunger, 0, 3);
        Label thirst = new Label("Thirst: ");
        GridPane.setConstraints(thirst, 0, 4);
        
        Button travel = new Button("Travel");
        travel.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(travel,0,6);
        
        Button rest = new Button("Rest");
        rest.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(rest,0,7);
        
        Button forage = new Button("Forage");
        forage.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(forage,0,8);
        
        Button viewInv = new Button("View Inventory");
        viewInv.setOnAction(e -> window.setScene(start));
        GridPane.setConstraints(viewInv,0,9);
        
        GridPane layout2 = new GridPane();
        layout2.setPadding(new Insets(20,20,20,20));
        layout2.setVgap(10);
        layout2.setHgap(10);
        
        layout2.getChildren().addAll(distance, health, hunger, thirst,
        travel, rest, forage, viewInv);
        layout2.setAlignment(Pos.TOP_LEFT);
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
