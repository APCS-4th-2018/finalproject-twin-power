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
    Label time, distance, display;
    GameEdit game;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Game");
        
        //starting menu
        start();
        
        //main menu
        mainMenu();
        
        window.setScene(start);
        window.show();
    }
    
    private void start()
    {
        GridPane layout1 = new GridPane();
        layout1.setPadding(new Insets(10,10,10,10));
        layout1.setVgap(8);
        layout1.setHgap(10);
        
        Label label1 = new Label("Enter name:");
        GridPane.setConstraints(label1,0,0);
        
        TextField input = new TextField();
        GridPane.setConstraints(input,1,0);
        
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {window.setScene(main); 
                                      game = new GameEdit(input.getText());});
        GridPane.setConstraints(startButton,2,0);
        
        layout1.getChildren().addAll(label1, input, startButton);
        layout1.setAlignment(Pos.CENTER);
        start = new Scene(layout1,960,540);
    }
    
    private void mainMenu()
    {
        time = new Label("Day 1 8:00");
        GridPane.setConstraints(time, 0, 0);
        distance = new Label("Distance left: 0");
        GridPane.setConstraints(distance, 0, 1);
        Label health = new Label("Health: ");
        GridPane.setConstraints(health, 0, 2);
        Label hunger = new Label("Hunger: ");
        GridPane.setConstraints(hunger, 0, 3);
        Label thirst = new Label("Thirst: ");
        GridPane.setConstraints(thirst, 0, 4);
        
        //buttons for choices
        Button travel = new Button("Travel");
        travel.setOnAction(e -> choice(1));
        GridPane.setConstraints(travel,0,6);
        
        Button rest = new Button("Rest");
        rest.setOnAction(e -> choice(2));
        GridPane.setConstraints(rest,0,7);
        
        Button forage = new Button("Forage");
        forage.setOnAction(e -> choice(3));
        GridPane.setConstraints(forage,0,8);
        
        Button viewInv = new Button("View Inventory");
        viewInv.setOnAction(e -> choice(4));
        GridPane.setConstraints(viewInv,0,9);
        
        //prompt
        display = new Label();
        display.setWrapText(true);
        GridPane.setConstraints(display,0,10);
        
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setVgap(10);
        layout.setHgap(10);
        
        layout.getChildren().addAll(time, distance, health, hunger, thirst,
        travel, rest, forage, viewInv, display);
        layout.setAlignment(Pos.TOP_LEFT);
        main = new Scene(layout,960,540);
    }
    
    private void choice(int choice)
    {
        switch(choice)
        {
            case 1: game.choiceTravel(1);
                    break;
            case 2: game.choiceRest(1);
                    break;
            case 3: game.choiceForage(1);
                    break;
            case 4: inventory();
                    break;
        }
        updateStatus();
    }
    
    private void updateStatus()
    {
        distance.setText("Distance left: " + game.getDistance());
        time.setText("Day " + game.getDay() + " " + game.getTime() + ":00");
    }
    
    private void inventory()
    {
        display.setText(game.printInventory());
        //other stuff
    }
    
    public void handle(ActionEvent event)
    {
        
    }
}
