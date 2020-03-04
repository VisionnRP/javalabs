package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        Circle yellowCircle = new Circle(150, 125, 100);
        Circle whiteCircle = new Circle(150, 125, 90);
        Circle time12 = new Circle(150, 50, 10);
        Circle time3 = new Circle(75, 125, 10);
        Circle time6 = new Circle(225, 125, 10);
        Circle time9 = new Circle(150, 200, 10);
        Polygon polygonBig = new Polygon(150, 130, 145, 125, 150, 40, 155, 125);
        Polygon polygonSmallBlack = new Polygon(140, 125, 150, 115, 205, 125, 150, 135);
        Polygon polygonSmallWhite = new Polygon(142, 125, 150, 120, 202, 125, 150, 130);

        root.getChildren().add(yellowCircle);
        root.getChildren().add(whiteCircle);
        root.getChildren().add(time12);
        root.getChildren().add(time3);
        root.getChildren().add(time6);
        root.getChildren().add(time9);
        root.getChildren().add(polygonSmallBlack);
        root.getChildren().add(polygonSmallWhite);
        root.getChildren().add(polygonBig);

        yellowCircle.setFill(Color.YELLOW);
        whiteCircle.setFill(Color.WHITE);
        time12.setFill(Color.YELLOW);
        time3.setFill(Color.YELLOW);
        time6.setFill(Color.YELLOW);
        time9.setFill(Color.YELLOW);
        polygonSmallWhite.setFill(Color.WHITE);

        scene.setFill(Color.rgb(71,92,10));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
