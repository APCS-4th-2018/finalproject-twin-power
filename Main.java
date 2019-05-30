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
    Label time, distance, inventory;
    ProgressBar health, hunger, thirst;
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
        time = new Label("Day 1\n0:00");
        GridPane.setConstraints(time, 0, 0);
        distance = new Label("Distance traveled: 0");
        GridPane.setConstraints(distance, 0, 1);
        
        //player stats
        Label stat1 = new Label("Health: ");
        GridPane.setConstraints(stat1, 0, 2);
        health = new ProgressBar(1);
        GridPane.setConstraints(health, 1, 2);
        
        Label stat2 = new Label("Hunger: ");
        GridPane.setConstraints(stat2, 0, 3);
        hunger = new ProgressBar(1);
        GridPane.setConstraints(hunger, 1, 3);
        
        Label stat3 = new Label("Thirst: ");
        GridPane.setConstraints(stat3, 0, 4);
        thirst = new ProgressBar(1);
        GridPane.setConstraints(thirst, 1, 4);
        
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
        
        //inventory
        inventory = new Label("Inventory:");
        inventory.setWrapText(true);
        GridPane.setConstraints(inventory,0,10);
        TextField input = new TextField("Item #");
        GridPane.setConstraints(input,0,11);
        Button use = new Button("Use");
        use.setOnAction(e -> inventory(input.getText()));
        GridPane.setConstraints(use,1,11);
        
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setVgap(10);
        layout.setHgap(10);
        
        layout.getChildren().addAll(time, distance, stat1, health, stat2, 
        hunger, stat3, thirst, travel, rest, forage, inventory, use, input);
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
                    inventory.setText(game.printInventory()); //update inv
                    break;
        }
        updateStatus();
    }
    
    private void updateStatus()
    {
        distance.setText("Distance left: " + game.getDistance());
        time.setText("Day " + game.getDay() + " " + game.getTime() + ":00");
        
        health.setProgress(game.getHealth()/10);
        hunger.setProgress(game.getHunger()/10);
        thirst.setProgress(game.getThirst()/10);
    }
    
    private void inventory(String i)
    {
        //game.useItem(i);
        inventory.setText(game.printInventory()); //update inv
        updateStatus();
    }
    
    public void handle(ActionEvent event)
    {
        
    }
}
