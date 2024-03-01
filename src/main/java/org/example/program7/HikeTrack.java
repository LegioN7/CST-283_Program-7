// CST-283
// Aaron Pelto
// Winter 2024

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

package org.example.program7;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The HikeTrack class is a JavaFX application that allows a user to track their hike.
 * It includes the eight cardinal direction buttons and a text field for entering the distance you walk (in feet).
 * The application stacks up all hiking segments for the entire hike.
 * The application includes the following:
 * A reset button to clear the stack and allow for entry of a new hike,
 * A done button to retrieve the complete set of segments of your hiking track and provide a sequence of
 * directions and distances to return to your starting point.
 * An exit button to exit the application
 * The tracked hike is displayed in a JavaFX Alert with a text area.
 */
public class HikeTrack extends Application {

    /**
     * The HikeDistanceStack instance that holds the hiking segments.
     */
    private final HikeDistanceStack hikeStack = new HikeDistanceStack(100);

    /**
     * The TextField for entering the distance you walk in feet.
     */
    private TextField hikeDistanceInput;

    /**
     * The TextArea for displaying the tracked hike.
     */
    private TextArea hikeDistanceTracker;

    /**
     * The done button.
     */
    private Button doneButton;

    /**
     * The reset button.
     */
    private Button resetButton;

    /**
     *
     */
    private Button undoButton;

    /**
     * The exit button
     */
    private Button exitButton;


    /**
     * The main method that launches the application.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Creates a direction button.
     */
    private Button createDirectionButton(HikeDirection direction) {
        // Create direction button
        Button directionButton = new Button(direction.name());
        directionButton.setMinSize(35, 35);
        directionButton.setOnAction(e -> performDirectionAction(direction));
        return directionButton;
    }

    /**
     * Creates a HikeDistance instance.
     */
    private HikeDistance createHikeDistance(String distance, HikeDirection direction) {
        return new HikeDistance(distance, direction);
    }

    /**
     * Updates the hike distance tracker.
     */
    private void updateHikeDistanceTracker() {
        // Peek into the stack
        HikeDistance newEntry = hikeStack.peek();
        String currentText = hikeDistanceTracker.getText();
        String header = "Distance (Feet) | Direction\n";
        String textWithoutHeader = currentText.substring(header.length());
        hikeDistanceTracker.setText(header + newEntry + "\n" + textWithoutHeader);
    }

    /**
     * Performs the direction action.
     */
    private void performDirectionAction(HikeDirection direction) {
        String distance = hikeDistanceInput.getText().trim();
        if (!distance.isEmpty()) {
            HikeDistance segment = createHikeDistance(distance, direction);
            hikeStack.push(segment);
            updateHikeDistanceTracker();
            // Enable the done button
            doneButton.setDisable(false);
            // Enable the undo button
            undoButton.setDisable(false);
            // Enable the reset button
            resetButton.setDisable(false);
        }
        hikeDistanceInput.clear();
    }

    /**
     * Creates the direction buttons.
     */
    private HBox createDirectionButtons() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        for (HikeDirection direction : HikeDirection.values()) {
            Button btn = createDirectionButton(direction);
            hbox.getChildren().add(btn);
        }
        return hbox;
    }

    /**
     * Creates the hbox for the reset button, done button and exit button
     */
    private HBox createControlButtons() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(resetButton(), undoButton(), doneButton(), exitButton());
        return hbox;
    }

    /**
     * Creates the Reset button.
     */
    private Button resetButton() {
        // Create the reset button title
        resetButton = new Button("Reset");
        // Set the minimum size
        resetButton.setMinSize(100, 35);
        // Add an action event to the reset button to clear the stack and allow for entry of a new hike
        resetButton.setOnAction(e -> performResetAction());
        // Return the reset button
        return resetButton;
    }

    /**
     * Performs the reset action.
     */
    private void performResetAction() {
        hikeDistanceTracker.clear();
        // Reset the header
        hikeDistanceTracker.setText("Distance (Feet) | Direction\n");
        // Clear the stack
        hikeStack.clear();
        // Disable the done button
        doneButton.setDisable(true);
        // Disable the undo button
        undoButton.setDisable(true);
        // Disable the reset button
        resetButton.setDisable(true);
    }

    /**
     * Creates the Done button.
     */
    private Button doneButton() {
        // Create the done button title
        doneButton = new Button("Done");
        // Set the minimum size
        doneButton.setMinSize(100, 35);
        // Add an action event to the done button to show the alert
        doneButton.setOnAction(e -> performDoneAction());
        // Return the done button
        return doneButton;
    }

    /**
     * Builds the hike track.
     */
    private String reverseHike() {
        // Create a temporary stack
        HikeDistanceStack tempStack = new HikeDistanceStack(hikeStack.getTop());
        // Use a StringBuilder to build the hike track
        StringBuilder hikeTrack = new StringBuilder();
        hikeTrack.append("Distance (Feet) | Direction\n");
        while (!hikeStack.isEmpty()) {
            // pop the top segment from the stack
            HikeDistance segment = hikeStack.pop();
            // push the segment onto the temporary stack
            tempStack.push(segment);
            // append the opposite direction to the hike track
            String distance = segment.getDistance();
            HikeDirection direction = segment.getDirection();
            String oppositeDirection = direction.getOpposite();
            // format the output
            String formattedOutput = String.format("%-15s | %s", distance, oppositeDirection);
            hikeTrack.append(formattedOutput).append("\n");
        }
        // restore the segments back to the original stack
        for (int i = tempStack.getTop() - 1; i >= 0; i--) {
            hikeStack.push(tempStack.stackArray[i]);
        }
        // return the hike track to the toString method
        return hikeTrack.toString();
    }

    /**
     * Creates an alert when clicking Done
     * Creates a text area with the information so the user can backtrack their hike
     */
    private Alert createAlert(String content) {
        // Create an alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Set the title
        alert.setTitle("Hike Tracker");
        // Set the header text to "Your Hike" which allows the user to backtrack their hike
        alert.setHeaderText("Your Hike");
        // Create a text area with the content
        TextArea textArea = new TextArea(content);
        // Set the font
        textArea.setFont(Font.font("Courier New", 12));
        // Set the text area to be non-editable
        textArea.setEditable(false);
        // Set the text area to wrap text
        alert.getDialogPane().setContent(textArea);
        // Return the alert
        return alert;
    }

    /**
     * Performs the done action.
     * Shows the alert
     */
    private void performDoneAction() {
        // Reverse the hike track (pop the segments from the stack and push them onto a temporary stack)
        // Reverse the direction of each segment
        String reversedHikeTrack = reverseHike();
        // Create an alert
        Alert alert = createAlert(reversedHikeTrack);
        // Show the alert and wait for a response from the user
        alert.showAndWait();
    }

    /**
     * Create the undo button
     */
    private Button undoButton() {
        // Create the undo button title
        undoButton = new Button("Undo");
        // Set the minimum size
        undoButton.setMinSize(100, 35);
        // Add an action event to the undo button to undo the user's last hike entry
        undoButton.setOnAction(e-> performUndoAction());
        // Return the undo button
        return undoButton;
    }

    /**
     * Undo the user's last hike entry and update the text area.
     */
    private void performUndoAction() {
        if (!hikeStack.isEmpty()) {
            // Pop the top segment from the stack if the user clicks the undo button
            hikeStack.pop();
            // Peek into the stack
            HikeDistance lastEntry = hikeStack.peek();
            if (lastEntry != null) {
                // Update the tracker
                updateHikeDistanceTracker();
                // Update the text area
                updateTextArea();
            } else {
                // Reset the header
                hikeDistanceTracker.setText("Distance (Feet) | Direction\n");
                // Disable the done button
                doneButton.setDisable(true);
                // Disable the undo button
                undoButton.setDisable(true);
            }
            // If the stack is empty
            if (hikeStack.isEmpty()) {
                // Disable the undo button
                undoButton.setDisable(true);
                // Disable the reset button
                resetButton.setDisable(true);
            }
        }
    }

    /**
     * Update the text area with the current state of the stack.
     */
    private void updateTextArea() {
        StringBuilder sb = new StringBuilder("Distance (Feet) | Direction\n");
        for (int i = 0; i < hikeStack.getTop(); i++) {
            sb.append(hikeStack.stackArray[i]).append("\n");
        }
        hikeDistanceTracker.setText(sb.toString());
    }
    /**
     * Creates the exit button.
     */
    private Button exitButton() {
        // Create the exit button title
        exitButton = new Button("Exit");
        // Set the minimum size
        exitButton.setMinSize(100, 35);
        // Add an action event to the exit button to exit the program
        exitButton.setOnAction(e -> exitProgram());
        // Return the exit button
        return exitButton;
    }

    /**
     * Exits the program.
     */
    public void exitProgram() {
        // Create an alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // Set the title
        alert.setTitle("Exit Hike Tracker");
        // Set the header text
        alert.setHeaderText("Are you sure you want to exit?");
        // Show the alert and wait for a response
        alert.showAndWait().ifPresent(response -> {
            // If the response is OK, exit the program
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    /**
     * Creates the hike distance input field.
     */
    private TextField createHikeDistanceInput() {
        // Create hike distance input text field
        hikeDistanceInput = new TextField();
        // Set the prompt text
        hikeDistanceInput.setPromptText("Enter distance in feet");
        // Add a listener to the text property
        hikeDistanceInput.textProperty().addListener((observable, oldValue, newValue) -> validateInput(oldValue, newValue));
        // Return the hike distance input
        return hikeDistanceInput;
    }

    /**
     * Creates the hike distance tracker.
     */
    private TextArea createHikeDistanceTracker() {
        // Create hike distance tracker text area
        hikeDistanceTracker = new TextArea();
        // Set the header
        hikeDistanceTracker.setText("Distance (Feet) | Direction\n");
        // Set the font
        hikeDistanceTracker.setFont(Font.font("Courier New", 12));
        // Return the hike distance tracker
        return hikeDistanceTracker;
    }

    /**
     * Validates the input from the distance input.
     */
    private void validateInput(String oldValue, String newValue) {
        // Validate the input
        // If the length of the input is greater than 6
        if (newValue.length() > 6) {
            hikeDistanceInput.setText(oldValue);
        } else {
            try {
                // Try to parse the input to an integer
                // I was having issues with the spaces so that is why the listener is here
                // I wanted to make sure the user could not enter a space
                Integer.parseInt(newValue.replaceAll("\\s", ""));
            } catch (NumberFormatException e) {
                hikeDistanceInput.setText("");
            }
        }
    }

    /**
     * Starts the application.
     */
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(createDirectionButtons(), createHikeDistanceInput(), createHikeDistanceTracker(), createControlButtons());
        // Disable the done button
        doneButton.setDisable(true);
        // Disable the undo button
        undoButton.setDisable(true);
        // Disable the reset button
        resetButton.setDisable(true);


        Scene scene = new Scene(vbox, 500, 300);
        stage.setTitle("Hike Tracker");
        stage.setScene(scene);
        stage.show();
    }
}