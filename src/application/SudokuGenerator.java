package application;

import java.util.Random;

public class SudokuGenerator {
    private static final int SIZE = 9;  // 9x9 board
    private int[][] board = new int[SIZE][SIZE];
    
    // Method to generate a Sudoku puzzle based on difficulty
    public int[][] generateSudoku(String difficulty) {
        fillBoard();  // Start with a full board
        removeNumbers(difficulty);  // Remove numbers based on difficulty
        return board;
    }
    
    // Fill the board with a valid Sudoku solution (this is hard-coded for simplicity)
    private void fillBoard() {
        int[][] solution = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        board = solution;  // Assign this solution to the board
    }
    
    // Remove numbers based on the difficulty level
    private void removeNumbers(String difficulty) {
        Random rand = new Random();
        int cellsToRemove = getCellsToRemove(difficulty);
        
        for (int i = 0; i < cellsToRemove; i++) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            
            while (board[row][col] == 0) {  // Ensure you don't remove an already empty cell
                row = rand.nextInt(SIZE);
                col = rand.nextInt(SIZE);
            }
            board[row][col] = 0;  // Set to 0 to indicate an empty cell
        }
    }
    
    // Determine how many cells to remove based on difficulty
    private int getCellsToRemove(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return 35;  // Fewer cells removed
            case "medium":
                return 45;  // Moderate number of cells removed
            case "hard":
                return 55;  // More cells removed
            default:
                return 35;  // Default to easy
        }
    }
}

