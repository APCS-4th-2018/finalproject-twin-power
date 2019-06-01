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
    final int WIDTH = 700;
    final int HEIGHT = 375;
    Stage window;
    Scene start, main, event, end;
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
        //window.getIcons().add(new Image("icon.png"));
        
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
        
        Label label = new Label("Enter name:");
        GridPane.setConstraints(label,0,0);
        
        TextField input = new TextField();
        GridPane.setConstraints(input,1,0);
        
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {game = new GameEdit(input.getText());
                                      mainMenu(); window.setScene(main);});
        GridPane.setConstraints(startButton,2,0);
        
        layout.getChildren().addAll(label, input, startButton);
        start = new Scene(layout,WIDTH,HEIGHT);
    }
    
    private void mainMenu()
    {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setGridLinesVisible(false);
        
        //add time, distance, player stats
        addStatus(layout);
        
        //buttons for choices
        addChoices(layout);
        
        //inventory
        addInventory(layout);
        
        main = new Scene(layout,WIDTH,HEIGHT);
    }
    
    private void addStatus(Pane layout)
    {
        //time + day
        time = new Label("Day " + game.getDay() + " " + game.getTime() + ":00");
        GridPane.setConstraints(time,0,0,3,1);
        distance = new Label(game.getDistance() + " km traveled");
        GridPane.setConstraints(distance,0,1,3,1);
        
        //health
        Label stat1 = new Label("Health: ");
        GridPane.setConstraints(stat1,0,2);
        
        health = new ProgressBar(1);
        GridPane.setConstraints(health,1,2);
        
        //ImageView heart = new ImageView("health.png");
        //heart.setFitHeight(40);
        //heart.setFitWidth(40);
        //GridPane.setConstraints(heart,2,2);
        
        //hunger
        Label stat2 = new Label("Hunger: ");
        GridPane.setConstraints(stat2,0,3);
        
        hunger = new ProgressBar(1);
        GridPane.setConstraints(hunger,1,3);
        
        //ImageView food = new ImageView("hunger.png");
        //food.setFitHeight(40);
        //food.setFitWidth(40);
        //GridPane.setConstraints(food,2,3);
        
        //thirst
        Label stat3 = new Label("Thirst: ");
        GridPane.setConstraints(stat3,0,4);
        
        thirst = new ProgressBar(1);
        GridPane.setConstraints(thirst,1,4);
        
        //ImageView thirst = new ImageView("thirst.png");
        //water.setFitHeight(40);
        //water.setFitWidth(40);
        //GridPane.setConstraints(water,2,4);
        
        layout.getChildren().addAll(time, distance);
        layout.getChildren().addAll(stat1, health);
        layout.getChildren().addAll(stat2, hunger);
        layout.getChildren().addAll(stat3, thirst);
    }
    
    private void addChoices(Pane layout)
    {
        Button travel = new Button("Travel");
        travel.setOnAction(e -> choice(1));
        travel.setMaxWidth(80);
        travel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(travel,0,6,3,1);
        
        Button rest = new Button("Rest");
        rest.setOnAction(e -> choice(2));
        rest.setMaxWidth(80);
        rest.setAlignment(Pos.CENTER);
        GridPane.setConstraints(rest,0,7,3,1);
        
        Button forage = new Button("Forage");
        forage.setOnAction(e -> choice(3));
        forage.setMaxWidth(80);
        forage.setAlignment(Pos.CENTER);
        GridPane.setConstraints(forage,0,8,3,1);
        
        layout.getChildren().addAll(travel, rest, forage);
    }
    
    private void addInventory(Pane layout)
    {
        Label inv = new Label("Inventory:");
        GridPane.setConstraints(inv,4,0);
        
        items = FXCollections.observableArrayList(game.inventoryList());
        inventory = new ListView(items);
        inventory.setPrefHeight(200);
        inventory.setPrefWidth(200);
        GridPane.setConstraints(inventory,4,1,1,8);
        
        Button use = new Button("Use");
        use.setOnAction(e -> useInventory(inventory.getSelectionModel().getSelectedIndex()));
        use.setMaxWidth(80);
        use.setAlignment(Pos.CENTER);
        GridPane.setConstraints(use,4,9);
        
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
        distance.setText(game.getDistance() + " km traveled");
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
        message.setAlignment(Pos.CENTER);
        GridPane.setConstraints(message, 0, 0);
        
        Button restart = new Button("Play Again");
        restart.setOnAction(e -> {window.setScene(start);});
        restart.setAlignment(Pos.CENTER);
        GridPane.setConstraints(restart, 0, 1);
        
        layout.getChildren().addAll(message, restart);
        end = new Scene(layout,WIDTH,HEIGHT);
        window.setScene(end);
    }
}
