package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.TicTacToe.GridSpace;

public class Main {
  public static void main(String[] args) {
    List<List<GridSpace>> grid = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY))));

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        int position = i * 4 + j;
        if (position >= args.length) {
          continue;
        }
        if (args[position].equals("X")) {
          grid.get(i).set(j, GridSpace.X);

        } else if (args[position].equals("O")) {
          grid.get(i).set(j, GridSpace.O);
        }
      }
    }
    System.out.println("Grid: " + grid);

    TicTacToe game = new TicTacToe(grid);

    System.out.println("Any Moves Left: " + game.anyMovesLeft());
    System.out.println("Is Game Over: " + game.isGameOver());
    System.out.println("Check Winner: " + game.checkWinner());
  }
}
