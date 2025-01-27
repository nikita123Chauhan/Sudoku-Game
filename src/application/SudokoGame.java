package application;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SudokoGame extends Application {
    private SudokuGenerator generator = new SudokuGenerator();
    private GridPane gridPane = new GridPane(); // To display the Sudoku board
    private VBox mainLayout = new VBox(); // Main layout to hold both panels
    private Text messageText = new Text(); // To display messages

    @Override
    public void start(Stage primaryStage) {
        // Create a panel for selecting difficulty and starting the game
        VBox selectionPanel = new VBox(10);
        selectionPanel.setAlignment(Pos.CENTER);
        Label difficultyLabel = new Label("Select Difficulty Level:");
        ComboBox<String> difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll("Easy", "Medium", "Hard");

        Button startButton = new Button("Start Sudoku Game");
        Button submitButton = new Button("Submit"); // New submit button
        Button endButton = new Button("End Game"); // End game button

        // Handle button click to generate and display a Sudoku board
        startButton.setOnAction(e -> {
            String selectedDifficulty = difficultyComboBox.getValue();
            if (selectedDifficulty != null) {
                int[][] sudokuBoard = generator.generateSudoku(selectedDifficulty);
                displayBoard(sudokuBoard); // Show the board on the UI
                messageText.setText(""); // Clear previous messages
            }
        });

        // Handle end game button click
        endButton.setOnAction(e -> endGame());

        // Handle submit button click to check the Sudoku solution
        submitButton.setOnAction(e -> checkCompletion());

        // Add elements to the selection panel
        selectionPanel.getChildren().addAll(difficultyLabel, difficultyComboBox, startButton, submitButton, endButton);
        
        // Create a layout for the Sudoku board
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        gridPane.setStyle("-fx-background-color: blue"); // Set a background color for the Sudoku grid

        // Add the selection panel and Sudoku board to the main layout
        mainLayout.getChildren().addAll(selectionPanel, gridPane, messageText);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setSpacing(20); // Set space between the two panels

        // Set the scene
        Scene scene = new Scene(mainLayout, 400, 400);
        primaryStage.setTitle("Sudoku Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display the Sudoku board in the UI
    private void displayBoard(int[][] board) {
        gridPane.getChildren().clear(); // Clear previous grid

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField cell = new TextField();
                int value = board[row][col];

                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false); // Don't allow changing filled cells
                    cell.setStyle("-fx-background-color: lightgray;"); // Set background color for filled cells
                } else {
                    cell.setStyle("-fx-background-color: white;"); // Set background color for empty cells
                }

                // Style for the cell
                cell.setPrefWidth(30); // Set the preferred width
                cell.setPrefHeight(30); // Set the preferred height
                cell.setStyle("-fx-font-size: 14; -fx-alignment: CENTER; -fx-background-color: white; -fx-border-color: gray; -fx-border-width: 1;");

                gridPane.add(cell, col, row); // Add the cell to the GridPane
            }
        }

        // Center the Sudoku grid
        gridPane.setAlignment(Pos.CENTER); // Center the gridPane within the main layout
    }

    // Method to check if the Sudoku is completed correctly
    private void checkCompletion() {
        int[][] userBoard = new int[9][9];

        // Read the values from the text fields and check for completion
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField cell = (TextField) gridPane.getChildren().get(row * 9 + col);
                String text = cell.getText();
                userBoard[row][col] = text.isEmpty() ? 0 : Integer.parseInt(text);
            }
        }

        // Validate the user board (You can implement your own validation logic)
        if (isSudokuValid(userBoard)) {
            messageText.setText("You passed!!"); // Set success message
            messageText.setFill(Color.GREEN); // Set message color
            messageText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;"); // Set message style
        } else {
            messageText.setText("Try again!"); // Set failure message
            messageText.setFill(Color.RED); // Set message color
            messageText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;"); // Set message style
        }
    }

    // Method to handle the end game action
    private void endGame() {
        messageText.setText("Game Ended!"); // End game message
        messageText.setFill(Color.ORANGE); // Set message color
        messageText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    }

    // Method to validate the Sudoku solution (this is a simplified version)
    private boolean isSudokuValid(int[][] board) {
        // Implement validation logic (this is just a placeholder)
        // Check if all cells are filled correctly (this is not a complete check)
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] < 1 || board[row][col] > 9) {
                    return false; // Invalid value
                }
            }
        }
        return true; // If passed simple check
    }

    public static void main(String[] args) {
        launch(args);
    }
}
