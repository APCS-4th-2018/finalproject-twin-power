import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.collections.*;
/**
 * JavaFX version
 * 
 * @author Sophia Du
 */
public class Main extends Application
{
    Stage window;
    Scene start, main, end;
    Label time, distance;
    ListView inventory;
    ObservableList items;
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
        window.setOnCloseRequest(e -> System.exit(0));
        //window.getIcons().add(new Image("file:icon.png"));
        
        //starting menu
        start();
        
        window.setScene(start);
        window.show();
    }
    
    private void start()
    {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        
        Label label1 = new Label("Enter name:");
        GridPane.setConstraints(label1,0,0);
        
        TextField input = new TextField();
        GridPane.setConstraints(input,1,0);
        
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {game = new GameEdit(input.getText());
                                      mainMenu(); window.setScene(main);});
        GridPane.setConstraints(startButton,2,0);
        
        layout.getChildren().addAll(label1, input, startButton);
        start = new Scene(layout,960,540);
    }
    
    private void mainMenu()
    {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_LEFT);
        
        //add time, distance, player stats
        addStatus(layout);
        
        //buttons for choices
        addChoices(layout);
        
        //inventory
        addInventory(layout);
        
        main = new Scene(layout,960,540);
    }
    
    private void addStatus(Pane layout)
    {
        time = new Label("Day 1  0:00");
        GridPane.setConstraints(time, 0, 0);
        distance = new Label("Distance traveled: 0 km");
        GridPane.setConstraints(distance, 0, 1);
        
        //health
        Label stat1 = new Label("Health: ");
        GridPane.setConstraints(stat1, 0, 2);
        
        health = new ProgressBar(1);
        GridPane.setConstraints(health, 1, 2);
        
        //ImageView heart = new ImageView("health.png");
        //heart.setFitHeight(20);
        //heart.setFitWidth(20);
        //GridPane.setConstraints(heart, 2, 2);
        
        //hunger
        Label stat2 = new Label("Hunger: ");
        GridPane.setConstraints(stat2, 0, 3);
        
        hunger = new ProgressBar(1);
        GridPane.setConstraints(hunger, 1, 3);
        
        //ImageView food = new ImageView("hunger.png");
        //food.setFitHeight(20);
        //food.setFitWidth(20);
        //GridPane.setConstraints(food, 2, 3);
        
        //thirst
        Label stat3 = new Label("Thirst: ");
        GridPane.setConstraints(stat3, 0, 4);
        thirst = new ProgressBar(1);
        GridPane.setConstraints(thirst, 1, 4);
        
        layout.getChildren().addAll(time, distance);
        layout.getChildren().addAll(stat1, health, stat2, 
        hunger, stat3, thirst);
    }
    
    private void addChoices(Pane layout)
    {
        Button travel = new Button("Travel");
        travel.setOnAction(e -> choice(1));
        GridPane.setConstraints(travel,0,6);
        
        Button rest = new Button("Rest");
        rest.setOnAction(e -> choice(2));
        GridPane.setConstraints(rest,0,7);
        
        Button forage = new Button("Forage");
        forage.setOnAction(e -> choice(3));
        GridPane.setConstraints(forage,0,8);
        
        layout.getChildren().addAll(travel, rest, forage);
    }
    
    private void addInventory(Pane layout)
    {
        Label inv = new Label("Inventory:");
        GridPane.setConstraints(inv,0,10);
        
        items = FXCollections.observableArrayList(game.inventoryList());
        inventory = new ListView(items);
        inventory.setPrefHeight(200);
        inventory.setPrefWidth(200);
        GridPane.setConstraints(inventory,0,11);
        
        Button use = new Button("Use");
        use.setOnAction(e -> useInventory(inventory.getSelectionModel().getSelectedIndex()));
        GridPane.setConstraints(use,0,12);
        
        layout.getChildren().addAll(inv, inventory, use);
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
                    updateInv();
                    break;
        }
        updateStatus();
        if(game.endGame())
            endGame();
    }
    
    private void updateStatus()
    {
        distance.setText("Distance traveled: " + game.getDistance() + " km");
        time.setText("Day " + game.getDay() + " " + game.getTime() + ":00");
        
        health.setProgress(game.getHealth()/10);
        hunger.setProgress(game.getHunger()/10);
        thirst.setProgress(game.getThirst()/10);
    }
    
    private void updateInv()
    {
        items = FXCollections.observableArrayList(game.inventoryList());
        inventory.setItems(items);
    }
    
    private void useInventory(int i)
    {
        game.useItem(i);
        updateInv();
        updateStatus();
    }
    
    private void endGame()
    {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        
        Label message = new Label(game.endingMessage());
        GridPane.setConstraints(message, 0, 0);
        
        Button restart = new Button("Play Again");
        restart.setOnAction(e -> {window.setScene(start);});
        GridPane.setConstraints(restart, 0, 1);
        
        layout.getChildren().addAll(message, restart);
        end = new Scene(layout,960,540);
        window.setScene(end);
    }
}
