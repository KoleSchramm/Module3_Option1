import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Random r = new Random();

        primaryStage.setTitle("Module 3 CT Option#1");

        //Create Menu
        Menu menu = new Menu("Menu");
        MenuItem menuItem1 = new MenuItem("Date and Time");
        MenuItem menuItem2 = new MenuItem("Print to File");
        MenuItem menuItem3 = new MenuItem("Hue: 0");
        MenuItem menuItem4 = new MenuItem("Exit");

        //Create Text Box
        TextField textField = new TextField("This is some text.");

        //Add Items to Menu
        menu.getItems().add(menuItem1);
        menu.getItems().add(menuItem2);
        menu.getItems().add(menuItem3);
        menu.getItems().add(menuItem4);

        //Create MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);

        //Create VBox
        VBox vBox = new VBox(menuBar, textField);
        vBox.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));

        //Create Scene
        Scene scene = new Scene(vBox, 400, 200);

        //Menu Actions
        //Set textField text to local date and time
        menuItem1.setOnAction(e -> {
            textField.setText("Date: " + LocalDate.now().toString() + ", Time: " + LocalTime.now().toString());
        });

        //Print textField text to file
        menuItem2.setOnAction(e -> {
            try {
                FileWriter writer = new FileWriter("log.txt");
                writer.write(textField.getText());
                writer.close();
                System.out.println("File Written");
            } catch (IOException ex) {
                System.out.println("Failed to write file");
            }
        });

        //Set background to random hue of green
        menuItem3.setOnAction(e -> {
            double h = r.nextInt(80) + 80;
            double s = 1;
            double b = 1;

            menuItem3.setText("Hue: " + h);

            vBox.setBackground(new Background(new BackgroundFill(Color.hsb(h,s,b),null,null)));
        });

        //Exit Application
        menuItem4.setOnAction(e -> {
            primaryStage.close();
        });

        //Add scene to primaryStage and make scene visible
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}