import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.collections.*;
import apcslib.*;
/**
 * JavaFX version
 * 
 * @author  Sophia Du
 * @version 06/01/19
 */
public class Main extends Application
{
    final int WIDTH = 800;
    final int HEIGHT = 400;
    Stage window;
    Scene start, main, event, end;
    Label time, distance;
    ProgressBar health, hunger, thirst;
    ListView inventory;
    DynamicBackground background;
    Game game;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Game");
        window.setOnCloseRequest(e -> System.exit(0));
        window.getIcons().add(new Image("/Graphics/SYMBOLS/app icon.png"));
        
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
        layout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        Label label = new Label("Enter name:");
        GridPane.setConstraints(label,0,0);
        
        TextField input = new TextField();
        input.setPrefHeight(40);
        GridPane.setConstraints(input,1,0);
        
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {game = new Game(input.getText());
                                      mainMenu(); window.setScene(main);});
        startButton.setPrefHeight(40);
        startButton.setPrefWidth(80);
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
        layout.setAlignment(Pos.CENTER);
        layout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        layout.setGridLinesVisible(false);
        
        //add time, distance, player stats
        addStatus(layout);
        
        //buttons for choices
        addChoices(layout);
        
        //inventory
        addInventory(layout);
        
        //background
        addBackground(layout);
        
        main = new Scene(layout,WIDTH,HEIGHT);
    }
    
    private void addStatus(Pane layout)
    {
        //time + day
        time = new Label("Day " + game.getDay() + " " + game.getTime() + ":00");
        time.setTextFill(Color.WHITE);
        GridPane.setConstraints(time,0,0,3,1);
        
        distance = new Label(Format.left(game.getDistance(),3,1) + " km traveled");
        distance.setTextFill(Color.WHITE);
        GridPane.setConstraints(distance,0,1,3,1);
        
        //health
        Label stat1 = new Label("Health: ");
        stat1.setTextFill(Color.WHITE);
        GridPane.setConstraints(stat1,0,3);
        
        health = new ColoredProgressBar("red-bar",game.getPlayer().getHealth()/10);
        health.setPrefHeight(30);
        health.setPrefWidth(150);
        GridPane.setConstraints(health,1,3);
        
        ImageView heart = new ImageView("/Graphics/SYMBOLS/heart.png");
        heart.setFitHeight(28);
        heart.setFitWidth(28);
        heart.setSmooth(false);
        GridPane.setConstraints(heart,2,3);
        
        //hunger
        Label stat2 = new Label("Hunger: ");
        stat2.setTextFill(Color.WHITE);
        GridPane.setConstraints(stat2,0,4);
        
        hunger = new ColoredProgressBar("chocolate-bar",game.getPlayer().getHunger()/10);
        hunger.setPrefHeight(30);
        hunger.setPrefWidth(150);
        GridPane.setConstraints(hunger,1,4);
        
        ImageView food = new ImageView("/Graphics/SYMBOLS/hunger.png");
        food.setFitHeight(28);
        food.setFitWidth(28);
        food.setSmooth(false);
        GridPane.setConstraints(food,2,4);
        
        //thirst
        Label stat3 = new Label("Thirst: ");
        stat3.setTextFill(Color.WHITE);
        GridPane.setConstraints(stat3,0,5);
        
        thirst = new ColoredProgressBar("aqua-bar",game.getPlayer().getThirst()/10);
        thirst.setPrefHeight(30);
        thirst.setPrefWidth(150);
        GridPane.setConstraints(thirst,1,5);
        
        ImageView water = new ImageView("/Graphics/SYMBOLS/water.png");
        water.setFitHeight(28);
        water.setFitWidth(28);
        water.setSmooth(false);
        GridPane.setConstraints(water,2,5);
        
        layout.getChildren().addAll(time, distance);
        layout.getChildren().addAll(stat1, health, heart);
        layout.getChildren().addAll(stat2, hunger, food);
        layout.getChildren().addAll(stat3, thirst, water);
    }
    
    private void addChoices(Pane layout)
    {
        Button travel = new Button("Travel");
        travel.setOnAction(e -> choice(1));
        travel.setPrefHeight(40);
        travel.setPrefWidth(80);
        travel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(travel,0,7,3,1);
        
        Button rest = new Button("Rest");
        rest.setOnAction(e -> choice(2));
        rest.setPrefHeight(40);
        rest.setPrefWidth(80);
        rest.setAlignment(Pos.CENTER);
        GridPane.setConstraints(rest,0,8,3,1);
        
        Button forage = new Button("Forage");
        forage.setOnAction(e -> choice(3));
        forage.setPrefHeight(40);
        forage.setPrefWidth(80);
        forage.setAlignment(Pos.CENTER);
        GridPane.setConstraints(forage,0,9,3,1);
        
        layout.getChildren().addAll(travel, rest, forage);
    }
    
    private void addInventory(Pane layout)
    {
        Label inv = new Label("Inventory:");
        inv.setTextFill(Color.WHITE);
        GridPane.setConstraints(inv,5,0);
        
        ObservableList items = FXCollections.observableArrayList(game.inventoryList());
        inventory = new ListView(items);
        inventory.setPrefHeight(200);
        inventory.setPrefWidth(210);
        GridPane.setConstraints(inventory,5,1,1,8);
        
        Button use = new Button("Use");
        use.setOnAction(e -> useInventory(inventory.getSelectionModel().getSelectedIndex()));
        use.setPrefHeight(40);
        use.setPrefWidth(80);
        use.setAlignment(Pos.CENTER);
        GridPane.setConstraints(use,5,9);
        
        layout.getChildren().addAll(inv, inventory, use);
    }
    
    private void addBackground(Pane layout)
    {
        //empty middle space
        Label empty = new Label("");
        empty.setPrefWidth(250);
        GridPane.setConstraints(empty,4,0);
        layout.getChildren().add(empty);
        
        //actual background
        background = new DynamicBackground(24,WIDTH,HEIGHT,"/Graphics/DAY NIGHT CYCLE/",".png");
        layout.setBackground(background.getBackground(game.getTime()));
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
        //day and time
        distance.setText(Format.left(game.getDistance(),3,1) + " km traveled");
        time.setText("Day " + game.getDay() + " " + game.getTime() + ":00");
        
        //background
        ((Pane)main.getRoot()).setBackground(background.getBackground(game.getTime()));
        
        //player stats
        health.setProgress(game.getPlayer().getHealth()/10);
        hunger.setProgress(game.getPlayer().getHunger()/10);
        thirst.setProgress(game.getPlayer().getThirst()/10);
    }
    
    private void updateInv()
    {
        inventory.setItems(null);
        inventory.setItems(FXCollections.observableArrayList(game.inventoryList()));
    }
    
    private void useInventory(int i)
    {
        if(i >= 0)
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
        layout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
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
