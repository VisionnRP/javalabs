package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Paravozik extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Group root = new Group();
        Scene scene = new Scene(root, 1200, 600);
        int CenterX = 228;
        int CenterY = 167;

        Polygon blackPoligon = new Polygon(
                CenterX - 10, CenterY - 50,
                CenterX - 10, CenterY - 20,
                CenterX - 50, CenterY - 20,
                CenterX - 50, CenterY - 50);
        blackPoligon.setFill(Color.BLACK);
        root.getChildren().add(blackPoligon);

        Polygon redPoligon = new Polygon(
                CenterX - 10, CenterY - 40,
                CenterX - 10, CenterY - 30,
                CenterX - 50, CenterY - 30,
                CenterX - 50, CenterY - 40);
        redPoligon.setFill(Color.RED);
        root.getChildren().add(redPoligon);

        Polygon vozdohod = new Polygon(
                CenterX - 50, CenterY - 50,
                CenterX - 45, CenterY - 55,
                CenterX - 15, CenterY - 55,
                CenterX - 10, CenterY - 50);
        vozdohod.setFill(Color.rgb(39, 56, 207));
        root.getChildren().add(vozdohod);

        Circle whiteCircle2 = new Circle(CenterX - 10, CenterY - 30, 15);
        whiteCircle2.setFill(Color.WHITE);
        root.getChildren().add(whiteCircle2);

        Circle whiteCircle3 = new Circle(CenterX - 50, CenterY - 30, 15);
        whiteCircle3.setFill(Color.WHITE);
        root.getChildren().add(whiteCircle3);

        Circle head = new Circle(CenterX - 50, CenterY, 20);
        head.setFill(Color.rgb(39, 56, 207));
        root.getChildren().add(head);

        Circle whiteCircle = new Circle(CenterX, CenterY - 20, 15);
        whiteCircle.setFill(Color.rgb(39, 56, 207));
        root.getChildren().add(whiteCircle);

        Polygon polygon = new Polygon(
                CenterX - 50, CenterY + 20,
                        CenterX - 50, CenterY - 20,
                        CenterX + 50, CenterY - 20,
                        CenterX + 50, CenterY + 15,
                        CenterX + 55, CenterY + 20,
                        CenterX + 55, CenterY + 10,
                        CenterX + 50, CenterY + 15,
                        CenterX + 50, CenterY + 20);
        polygon.setFill(Color.rgb(39, 56, 207));
        root.getChildren().add(polygon);

        Polygon polygon2 = new Polygon(
                CenterX + 45, CenterY - 40,
                        CenterX + 50, CenterY - 40,
                        CenterX + 50, CenterY - 37,
                        CenterX + 45, CenterY - 37,
                        CenterX + 45, CenterY - 20,
                        CenterX + 25, CenterY - 20,
                        CenterX + 25, CenterY - 40,
                        CenterX + 20, CenterY - 40,
                        CenterX + 20, CenterY - 35,
                        CenterX + 25, CenterY - 35,
                        CenterX + 25, CenterY - 40);
        polygon2.setFill(Color.rgb(39, 56, 207));
        root.getChildren().add( polygon2);

        Polygon polygon3 = new Polygon(
                CenterX + 40, CenterY - 35,
                CenterX + 40, CenterY - 20,
                CenterX + 30, CenterY - 20,
                CenterX + 30, CenterY - 35);
        polygon3.setFill(Color.LIGHTBLUE);
        root.getChildren().add(polygon3);

        Polygon polygon4 = new Polygon(
                CenterX - 40, CenterY - 25,
                CenterX - 40, CenterY - 20,
                CenterX - 20, CenterY - 20,
                CenterX - 20, CenterY - 25);
        polygon4.setFill(Color.rgb(39, 56, 207));
        root.getChildren().add(polygon4);

        Circle big = new Circle(CenterX + 25, CenterY, 25);
        big.setFill(Color.RED);
        root.getChildren().add(big);
        Circle smallBlackInBig = new Circle(CenterX + 25, CenterY, 3);
        smallBlackInBig.setFill(Color.BLACK);
        root.getChildren().add(smallBlackInBig);

        Circle small = new Circle(CenterX - 40, CenterY + 15, 10);
        small.setFill(Color.RED);
        root.getChildren().add(small);

        Circle smallFirst = new Circle(CenterX - 33, CenterY + 15, 10);
        smallFirst.setFill(Color.RED);
        root.getChildren().add(smallFirst);

        Circle smallBlackInSmall = new Circle(CenterX - 33, CenterY + 15, 2);
        smallBlackInSmall.setFill(Color.BLACK);
        root.getChildren().add(smallBlackInSmall);

        final ImageView imv = new ImageView();
        final Image image2 = new Image(new FileInputStream("golova.png"));
        imv.setImage(image2);
        imv.setX(CenterX - 70);
        imv.setY(CenterY - 19);
        imv.setFitHeight(35);
        imv.setFitWidth(20);
        root.getChildren().add(imv);

        int cycleCount = 4;
        int time = 2000;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), root);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), root);
        rotateTransition.setByAngle(360f);
        rotateTransition.setCycleCount(cycleCount);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), root);
        translateTransition.setFromX(150);
        translateTransition.setToX(500);
        translateTransition.setCycleCount(cycleCount + 1);
        translateTransition.setAutoReverse(true);

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time), root);
        translateTransition2.setFromX(50);
        translateTransition2.setToX(500);
        translateTransition2.setCycleCount(cycleCount + 1);
        translateTransition2.setAutoReverse(true);

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(time), root);
        scaleTransition2.setToX(0.1);
        scaleTransition2.setToY(0.1);
        scaleTransition2.setCycleCount(cycleCount);
        scaleTransition2.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                scaleTransition2,
                translateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Lab 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
