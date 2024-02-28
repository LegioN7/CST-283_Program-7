package org.example.project7;

/*
    Build a JavaFX user interface that includes 8 direction buttons and a text field for entering the distance you walk (in feet).
    You will use these during the hike to document the track of your hike.
    You need a distribution of the eight buttons in a meaningful and useful configuration.
 */

/*
    The "800" in this graphic is an example of what the user would key in.
    If they then pressed "NE" it implies the most recent segment of their hike was a walk 800 feet to the northeast.
    Provide error checking to be sure there is a meaningful distance in the text field when a direction is pressed.


    Your program should then "stack up" all hiking segments for the entire hike.
    Use a stack for this.
    You can choose a stack of strings, or perhaps a stack of "segment" objects.
    However, you be sure to use a version of the Stack class created as part of the course
    (or a variant of the class with a change of the primary element data type).

 */

/*
    An example of a hike could be:
                                            550 NW
                                            350 E
                                            500 NE
                                            1000 N
                                            800 NE
    Include two additional buttons: A "Reset" button will clear the stack and allow for entry of a new hike.
 */

/*
    A "Done" button will retrieve the complete set of segments of your hiking track and provide a sequence of
    directions and distances to return to your starting point.
        (note that the directions are converted to what is reciprocal to the original direction.)
    Use a JavaFX Alert dialog box or text area to display the tracked hike.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HikeTrack extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();

        Button btnN = new Button("N");
        btnN.setLayoutX(300);
        btnN.setLayoutY(50);

        Button btnNE = new Button("NE");
        btnNE.setLayoutX(400);
        btnNE.setLayoutY(100);

        Button btnE = new Button("E");
        btnE.setLayoutX(450);
        btnE.setLayoutY(200);

        Button btnSE = new Button("SE");
        btnSE.setLayoutX(400);
        btnSE.setLayoutY(300);

        Button btnS = new Button("S");
        btnS.setLayoutX(300);
        btnS.setLayoutY(350);

        Button btnSW = new Button("SW");
        btnSW.setLayoutX(200);
        btnSW.setLayoutY(300);

        Button btnW = new Button("W");
        btnW.setLayoutX(150);
        btnW.setLayoutY(200);

        Button btnNW = new Button("NW");
        btnNW.setLayoutX(200);
        btnNW.setLayoutY(100);

        pane.getChildren().addAll(btnN, btnNE, btnE, btnSE, btnS, btnSW, btnW, btnNW);

        TextField distanceField = new TextField();
        double centerX = (btnW.getLayoutX() + btnE.getLayoutX()) / 2;
        distanceField.setLayoutX(centerX);
        distanceField.setLayoutY(200);

        Button btnReset = new Button("Reset");
        btnReset.setLayoutX(250);
        btnReset.setLayoutY(400);

        Button btnDone = new Button("Done");
        btnDone.setLayoutX(350);
        btnDone.setLayoutY(400);

        pane.getChildren().addAll(distanceField, btnReset, btnDone);

        TextArea hikeDistanceTracker = new TextArea();
        hikeDistanceTracker.setLayoutX(50);
        hikeDistanceTracker.setLayoutY(450);
        pane.getChildren().add(hikeDistanceTracker);

        Scene scene = new Scene(pane, 600, 700);
        stage.setTitle("Direction Application");
        stage.setScene(scene);
        stage.show();

        Platform.runLater(() -> {
            double fieldWidth = distanceField.getWidth();
            distanceField.setLayoutX(centerX - fieldWidth / 2);
        });
    }


    public static void main(String[] args) {
        launch();
    }
}