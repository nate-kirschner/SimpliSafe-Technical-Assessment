package test;

import org.junit.Before;
import org.junit.Test;

import src.TicTacToe;
import src.TicTacToe.GridSpace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for TicTacToe win conditions.
 */
public class TicTacToeTests {

  List<List<GridSpace>> grid;
  TicTacToe game;

  @Before
  public void setup() {
    grid = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY))));
    game = new TicTacToe(grid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwErrorWhenGridDoesNotHave4Rows() {
    new TicTacToe(new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void throwErrorWhenGridDoesNotHave4Columns() {
    new TicTacToe(new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)),
        new ArrayList<>(Arrays.asList(GridSpace.EMPTY, GridSpace.EMPTY, GridSpace.EMPTY)))));
  }

  @Test
  public void noWinnerGridReturnsEmptySpace() {
    assertEquals(GridSpace.EMPTY, game.checkWinner());
  }

  @Test
  public void playerXWinsHorizontal() {
    for (int i = 0; i < grid.get(0).size(); i++) {
      grid.get(1).set(i, GridSpace.X);
    }

    assertEquals(GridSpace.X, game.checkWinner());
  }

  @Test
  public void playerXWinsVertical() {
    for (int i = 0; i < grid.get(0).size(); i++) {
      grid.get(i).set(2, GridSpace.X);
    }

    assertEquals(GridSpace.X, game.checkWinner());
  }

  @Test
  public void playerXWinsDiagonal() {
    for (int i = 0; i < grid.get(0).size(); i++) {
      grid.get(i).set(i, GridSpace.X);
    }

    assertEquals(GridSpace.X, game.checkWinner());
  }

  @Test
  public void playerXWins4Corners() {
    grid.get(0).set(0, GridSpace.X);
    grid.get(0).set(this.grid.size() - 1, GridSpace.X);
    grid.get(this.grid.size() - 1).set(0, GridSpace.X);
    grid.get(this.grid.size() - 1).set(this.grid.size() - 1, GridSpace.X);

    assertEquals(GridSpace.X, game.checkWinner());
  }

  @Test
  public void playerXWinsBox() {
    grid.get(2).set(2, GridSpace.X);
    grid.get(2).set(3, GridSpace.X);
    grid.get(3).set(2, GridSpace.X);
    grid.get(3).set(3, GridSpace.X);

    assertEquals(GridSpace.X, game.checkWinner());
  }

  @Test
  public void playerOWinsHorizontal() {
    for (int i = 0; i < grid.get(0).size(); i++) {
      grid.get(1).set(i, GridSpace.O);
    }

    assertEquals(GridSpace.O, game.checkWinner());
  }

  @Test
  public void playerOWinsVertical() {
    for (int i = 0; i < grid.get(0).size(); i++) {
      grid.get(i).set(2, GridSpace.O);
    }

    assertEquals(GridSpace.O, game.checkWinner());
  }

  @Test
  public void playerOWinsDiagonal() {
    for (int i = 0; i < grid.get(0).size(); i++) {
      grid.get(i).set(i, GridSpace.O);
    }

    assertEquals(GridSpace.O, game.checkWinner());
  }

  @Test
  public void playerOWins4Corners() {
    grid.get(0).set(0, GridSpace.O);
    grid.get(0).set(this.grid.size() - 1, GridSpace.O);
    grid.get(this.grid.size() - 1).set(0, GridSpace.O);
    grid.get(this.grid.size() - 1).set(this.grid.size() - 1, GridSpace.O);

    assertEquals(GridSpace.O, game.checkWinner());
  }

  @Test
  public void playerOWinsBox() {
    grid.get(2).set(2, GridSpace.O);
    grid.get(2).set(3, GridSpace.O);
    grid.get(3).set(2, GridSpace.O);
    grid.get(3).set(3, GridSpace.O);

    assertEquals(GridSpace.O, game.checkWinner());
  }

  @Test
  public void anyMovesLeftIsFalseIfNoMoves() {
    for (int i = 0; i < this.grid.size(); i++) {
      for (int j = 0; j < this.grid.get(0).size(); j++) {
        grid.get(i).set(j, GridSpace.X);
      }
    }

    assertFalse(game.anyMovesLeft());
  }

  @Test
  public void anyMovesLeftIsTrueIfMovesLeft() {
    for (int i = 0; i < this.grid.size(); i++) {
      for (int j = 0; j < this.grid.get(0).size() - 1; j++) {
        grid.get(i).set(j, GridSpace.X);
      }
    }
    assertTrue(game.anyMovesLeft());
  }

  @Test
  public void gameIsOverIfPlayerWon() {
    for (int i = 0; i < this.grid.size(); i++) {
      for (int j = 0; j < this.grid.get(0).size(); j++) {
        grid.get(1).set(j, GridSpace.X);
      }
    }
    assertTrue(game.isGameOver());
  }

  @Test
  public void gameIsOverIfNoMovesLeftAndNoWinner() {
    grid = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(GridSpace.X, GridSpace.X, GridSpace.O, GridSpace.X)),
        new ArrayList<>(Arrays.asList(GridSpace.O, GridSpace.O, GridSpace.X, GridSpace.O)),
        new ArrayList<>(Arrays.asList(GridSpace.X, GridSpace.X, GridSpace.O, GridSpace.X)),
        new ArrayList<>(Arrays.asList(GridSpace.O, GridSpace.O, GridSpace.X, GridSpace.O))));
    game = new TicTacToe(grid);

    assertTrue(game.isGameOver());
  }

  @Test
  public void gameIsNotOverIfMovesLeftAndNoWinner() {
    assertFalse(game.isGameOver());
  }
}