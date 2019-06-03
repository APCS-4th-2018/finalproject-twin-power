import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
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
    //sizes for window, buttons, progress bars, images, etc.
    final int WIDTH = 1200;
    final int HEIGHT = 600;
    final int BUTTON_WIDTH = 100;
    final int BUTTON_HEIGHT = 40;
    final int BAR_WIDTH = 200;
    final int BAR_HEIGHT = 30;
    final int ICON_SIZE = 28;
    final int SPACING = 20;
    
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
        
        window.show();
    }
    
    //creates starting screen
    private void start()
    {
        GridPane layout = new GridPane();
        layout.setHgap(SPACING);
        layout.setAlignment(Pos.CENTER);
        layout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(184,206,234),null,null)));
        
        Label label = new Label("Enter name:");
        GridPane.setConstraints(label,0,0);
        
        //input of name
        TextField input = new TextField();
        input.setPrefHeight(BUTTON_HEIGHT);
        GridPane.setConstraints(input,1,0);
        
        //start button
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {game = new Game(input.getText());
                                      mainMenu();});
        startButton.setPrefHeight(BUTTON_HEIGHT);
        startButton.setPrefWidth(BUTTON_WIDTH);
        GridPane.setConstraints(startButton,2,0);
        
        layout.getChildren().addAll(label, input, startButton);
        start = new Scene(layout,WIDTH,HEIGHT);
        window.setScene(start);
    }
    
    //creates game interface
    private void mainMenu()
    {
        GridPane layout = new GridPane();
        layout.setVgap(SPACING);
        layout.setHgap(SPACING);
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
        window.setScene(main);
    }
    
    private void addStatus(Pane layout)
    {
        //time + day
        time = new Label("Day " + (game.getDay() + 1) + " " + game.getTime() + ":00");
        time.setTextFill(Color.WHITE);
        GridPane.setConstraints(time,0,0,3,1);
        
        //distance
        distance = new Label(Format.left(game.getDistance(),3,1) + " km traveled");
        distance.setTextFill(Color.WHITE);
        GridPane.setConstraints(distance,0,1,3,1);
        
        //health
        Label stat1 = new Label("Health: ");
        stat1.setTextFill(Color.WHITE);
        GridPane.setConstraints(stat1,0,3);
        
        health = new ColoredProgressBar("red-bar",game.getPlayer().getHealth()/10);
        health.setPrefHeight(BAR_HEIGHT);
        health.setPrefWidth(BAR_WIDTH);
        GridPane.setConstraints(health,1,3);
        
        ImageView heart = new ImageView("/Graphics/SYMBOLS/heart.png");
        heart.setFitHeight(ICON_SIZE);
        heart.setFitWidth(ICON_SIZE);
        heart.setSmooth(false);
        GridPane.setConstraints(heart,2,3);
        
        //hunger
        Label stat2 = new Label("Hunger: ");
        stat2.setTextFill(Color.WHITE);
        GridPane.setConstraints(stat2,0,4);
        
        hunger = new ColoredProgressBar("chocolate-bar",game.getPlayer().getHunger()/10);
        hunger.setPrefHeight(BAR_HEIGHT);
        hunger.setPrefWidth(BAR_WIDTH);
        GridPane.setConstraints(hunger,1,4);
        
        ImageView food = new ImageView("/Graphics/SYMBOLS/hunger.png");
        food.setFitHeight(ICON_SIZE);
        food.setFitWidth(ICON_SIZE);
        food.setSmooth(false);
        GridPane.setConstraints(food,2,4);
        
        //thirst
        Label stat3 = new Label("Thirst: ");
        stat3.setTextFill(Color.WHITE);
        GridPane.setConstraints(stat3,0,5);
        
        thirst = new ColoredProgressBar("aqua-bar",game.getPlayer().getThirst()/10);
        thirst.setPrefHeight(BAR_HEIGHT);
        thirst.setPrefWidth(BAR_WIDTH);
        GridPane.setConstraints(thirst,1,5);
        
        ImageView water = new ImageView("/Graphics/SYMBOLS/water.png");
        water.setFitHeight(ICON_SIZE);
        water.setFitWidth(ICON_SIZE);
        water.setSmooth(false);
        GridPane.setConstraints(water,2,5);
        
        layout.getChildren().addAll(time, distance);
        layout.getChildren().addAll(stat1, health, heart);
        layout.getChildren().addAll(stat2, hunger, food);
        layout.getChildren().addAll(stat3, thirst, water);
    }
    
    private void addChoices(Pane layout)
    {
        //travel
        Button travel = new Button("Travel");
        travel.setOnAction(e -> choice(1));
        travel.setPrefHeight(BUTTON_HEIGHT);
        travel.setPrefWidth(BUTTON_WIDTH);
        travel.setAlignment(Pos.CENTER);
        GridPane.setConstraints(travel,0,7,3,1);
        
        //rest
        Button rest = new Button("Rest");
        rest.setOnAction(e -> choice(2));
        rest.setPrefHeight(BUTTON_HEIGHT);
        rest.setPrefWidth(BUTTON_WIDTH);
        rest.setAlignment(Pos.CENTER);
        GridPane.setConstraints(rest,0,8,3,1);
        
        //forage
        Button forage = new Button("Forage");
        forage.setOnAction(e -> choice(3));
        forage.setPrefHeight(BUTTON_HEIGHT);
        forage.setPrefWidth(BUTTON_WIDTH);
        forage.setAlignment(Pos.CENTER);
        GridPane.setConstraints(forage,0,9,3,1);
        
        layout.getChildren().addAll(travel, rest, forage);
    }
    
    private void addInventory(Pane layout)
    {
        Label inv = new Label("Inventory:");
        inv.setTextFill(Color.WHITE);
        GridPane.setConstraints(inv,5,0);
        
        //list of items in inventory
        ObservableList items = FXCollections.observableArrayList(game.inventoryList());
        inventory = new ListView(items);
        inventory.setPrefHeight(200);
        inventory.setPrefWidth(300);
        GridPane.setConstraints(inventory,5,1,1,8);
        
        //button to use selected item
        Button use = new Button("Use");
        use.setOnAction(e -> choice(4));
        use.setPrefHeight(BUTTON_HEIGHT);
        use.setPrefWidth(BUTTON_WIDTH);
        use.setAlignment(Pos.CENTER);
        GridPane.setConstraints(use,5,9);
        
        layout.getChildren().addAll(inv, inventory, use);
    }
    
    private void addBackground(Pane layout)
    {
        //empty middle space
        Label empty = new Label("");
        empty.setPrefWidth(350);
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
                    event(game.event());
                    break;
            case 2: game.choiceRest(1);
                    break;
            case 3: game.choiceForage(1);
                    break;
            case 4: game.useItem(inventory.getSelectionModel().getSelectedIndex());
                    break;
        }
        //delay(0.5);
        updateStatus();
        updateInv();
        if(game.endGame()) //game has ended
            endGame();
    }
    
    private void updateStatus()
    {
        //day and time
        distance.setText(Format.left(game.getDistance(),3,1) + " km traveled");
        time.setText("Day " + (game.getDay() + 1) + " " + game.getTime() + ":00");
        
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
    
    private void event(Animal animal)
    {
        if(animal != null)
        {
            GridPane layout = new GridPane();
            layout.setVgap(SPACING);
            layout.setHgap(SPACING);
            layout.setAlignment(Pos.CENTER);
            layout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            layout.setBackground(background.getBackground(game.getTime()));
            
            //event description
            Label description = new Label(animal.getDescript());
            description.setWrapText(true);
            description.setTextFill(Color.WHITE);
            description.setPrefWidth(600);
            GridPane.setConstraints(description,0,0,2,1);
            
            //choices
            Label select = new Label("Select Action:");
            select.setTextFill(Color.WHITE);
            GridPane.setConstraints(select,0,2);
            
            ChoiceBox choices = new ChoiceBox(FXCollections.observableArrayList(game.getAnimals()));
            choices.setPrefWidth(400);
            GridPane.setConstraints(choices,1,2);
            
            //button to choose
            Button choose = new Button("Choose");
            choose.setOnAction(e -> {finishEvent(animal,choices.getSelectionModel().getSelectedIndex());});
            choose.setPrefHeight(BUTTON_HEIGHT);
            choose.setPrefWidth(BUTTON_WIDTH);
            choose.setAlignment(Pos.CENTER);
            GridPane.setConstraints(choose,0,3,2,1);
            
            layout.getChildren().addAll(description,select,choices,choose);
            event = new Scene(layout,WIDTH,HEIGHT);
            window.setScene(event);
        }
    }
    
    private void finishEvent(Animal animal, int choice)
    {
        if(choice >= 0)
        {
            if(game.correctChoice(choice, animal))
                window.setScene(main);
            else
            {
                game.killPlayer();
                if(game.endGame()) //game has ended
                    endGame();
            }
        }
    }
    
    //creates new scene for ending screen
    private void endGame()
    {
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(SPACING);
        layout.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        layout.setBackground(new Background(new BackgroundFill(Color.rgb(184,206,234),null,null)));
        
        //end game message (win/lose)
        String temp;
        if(game.getPlayer().isAlive()) //player is alive = win
            temp = "YOU SURVIVED\n" + "You traveled " + game.getWinDistance() + " km in " +
                      game.getDay() + " days and " + game.getTime() + " hours";
        else //player is dead = lose
            temp = "YOU DIED\n" + "You traveled " + Format.left(game.getDistance(),3,1) + " km";
        Label message = new Label(temp);
        message.setTextAlignment(TextAlignment.CENTER);
        GridPane.setConstraints(message, 0, 0);
        
        //button to restart game
        Button restart = new Button("Play Again");
        restart.setOnAction(e -> {window.setScene(start);});
        restart.setAlignment(Pos.CENTER);
        GridPane.setConstraints(restart, 0, 1);
        
        layout.getChildren().addAll(message, restart);
        end = new Scene(layout,WIDTH,HEIGHT);
        window.setScene(end);
    }
    
    private void delay(double seconds)
    {
        try { Thread.sleep((int)(seconds * 1000));}
        catch(Exception e) {}
    }
}
