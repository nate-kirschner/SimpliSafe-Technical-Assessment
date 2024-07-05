package src;

import java.util.List;

/**
 * Class representing a game of TicTacToe.
 * The class contains a grid representing the current state of the game and
 * methods to query information about that state.
 */
public class TicTacToe {

  /**
   * Enum representing how a grid space can be filled.
   * Either by player X, player O, or an unfilled space
   */
  public enum GridSpace {
    X, O, EMPTY
  }

  private final List<List<GridSpace>> grid;

  public TicTacToe(List<List<GridSpace>> grid) {
    // Throw an error if the grid passed in is not 4x4
    if (grid.size() != 4) {
      throw new IllegalArgumentException("Tic Tac Toe grid must have four rows.");
    }
    for (int i = 0; i < grid.size(); i++) {
      if (grid.get(i).size() != 4) {
        throw new IllegalArgumentException("Tic Tac Toe grid must have four columns.");
      }
    }
    this.grid = grid;
  }

  /**
   * Checks if there are any moves left to be made.
   * A move is left to be made if there is still an empty space on the board,
   * regardless of if someone has won the game yet.
   * 
   * @return true if another move can be made, false otherwise.
   */
  public boolean anyMovesLeft() {
    for (int i = 0; i < this.grid.size(); i++) {
      for (int j = 0; j < this.grid.get(0).size(); j++) {
        if (this.grid.get(i).get(j) == GridSpace.EMPTY) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the game is over. The game is considered over if either a player
   * has won the game, or there are no moves left to be made.
   * 
   * @return True if the game is over, false otherwise.
   */
  public boolean isGameOver() {
    return this.checkWinner() != GridSpace.EMPTY || !this.anyMovesLeft();
  }

  /**
   * Checks who won the given game of tic tac toe.
   * A win is determined by if a player fills all spaces in a row, column,
   * diagonal, a two-by-two box, or all four corners.
   * 
   * @return The GridSpace type representing the player that won. If no player has
   *         won, returns the EMPTY GridSpace type.
   */
  public GridSpace checkWinner() {
    boolean playerXWon = checkWinnerForPlayer(GridSpace.X);
    if (playerXWon) {
      return GridSpace.X;
    }

    boolean playerOWon = checkWinnerForPlayer(GridSpace.O);
    if (playerOWon) {
      return GridSpace.O;
    }

    return GridSpace.EMPTY;
  }

  private boolean checkWinnerForPlayer(GridSpace player) {

    boolean hasHorizontalWinner = hasHorizontalWinner(player);

    boolean hasVerticalWinner = hasVerticalWinner(player);

    boolean hasDiagonalWinner = hasDiagonalWinner(player);

    boolean hasFourCornersWinner = hasFourCornersWinner(player);

    boolean hasTwoByTwoBoxWinner = hasTwoByTwoBoxWinner(player);

    return hasHorizontalWinner || hasVerticalWinner || hasDiagonalWinner || hasFourCornersWinner
        || hasTwoByTwoBoxWinner;
  }

  private boolean hasHorizontalWinner(GridSpace player) {
    boolean hasHorizontalWinner = false;
    for (int i = 0; i < this.grid.size(); i++) {
      boolean onlyThisPlayerInRow = true;
      for (int j = 0; j < this.grid.get(0).size(); j++) {
        onlyThisPlayerInRow = onlyThisPlayerInRow && this.grid.get(i).get(j) == player;
      }
      hasHorizontalWinner = hasHorizontalWinner || onlyThisPlayerInRow;
    }
    return hasHorizontalWinner;
  }

  private boolean hasVerticalWinner(GridSpace player) {
    boolean hasVerticalWinner = false;
    for (int i = 0; i < this.grid.size(); i++) {
      boolean onlyThisPlayerInCol = true;
      for (int j = 0; j < this.grid.get(0).size(); j++) {
        onlyThisPlayerInCol = onlyThisPlayerInCol && this.grid.get(j).get(i) == player;
      }
      hasVerticalWinner = hasVerticalWinner || onlyThisPlayerInCol;
    }
    return hasVerticalWinner;
  }

  private boolean hasDiagonalWinner(GridSpace player) {
    boolean onlyThisPlayerInDiagonalLtoR = true;
    boolean onlyThisPlayerInDiagonalRtoL = true;
    int gridLen = this.grid.size() - 1;
    for (int i = 0; i < this.grid.size(); i++) {
      onlyThisPlayerInDiagonalLtoR = onlyThisPlayerInDiagonalLtoR && this.grid.get(i).get(i) == player;
      onlyThisPlayerInDiagonalRtoL = onlyThisPlayerInDiagonalRtoL
          && this.grid.get(gridLen - i).get(gridLen - i) == player;
    }
    return onlyThisPlayerInDiagonalLtoR || onlyThisPlayerInDiagonalRtoL;
  }

  private boolean hasFourCornersWinner(GridSpace player) {
    int gridLen = this.grid.size() - 1;
    return this.grid.get(0).get(0) == player
        && this.grid.get(0).get(gridLen) == player
        && this.grid.get(gridLen).get(0) == player
        && this.grid.get(gridLen).get(gridLen) == player;
  }

  private boolean hasTwoByTwoBoxWinner(GridSpace player) {
    boolean hasTwoByTwoBoxWinner = false;
    for (int i = 0; i < this.grid.size() - 1; i++) {
      for (int j = 0; j < this.grid.get(0).size() - 1; j++) {
        hasTwoByTwoBoxWinner = hasTwoByTwoBoxWinner
            || (this.grid.get(i).get(j) == player
                && this.grid.get(i + 1).get(j) == player
                && this.grid.get(i).get(j + 1) == player
                && this.grid.get(i + 1).get(j + 1) == player);
      }
    }

    return hasTwoByTwoBoxWinner;
  }
}
