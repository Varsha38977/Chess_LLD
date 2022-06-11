package service;

import java.util.Arrays;
import java.util.List;

import model.Board;
import model.Cell;
import model.GameStatus;
import model.Move;
import model.pieces.Piece;
import model.Player;

public class GameManager {
    private final Player playerOne;

    private final Player playerTwo;

    private Board board;

    private Player currentTurn;

    private GameStatus gameStatus;

    public GameManager(final Player playerOne, final Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = new Board();
        currentTurn = playerOne;
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public boolean playerMove (final Player player, final Integer startX, final Integer endX,
            final Integer startY, final Integer endY){
        if (!areValidPoints(Arrays.asList(startX, endX, startY, endY))) {
            return false;
        }
        final Cell initialCell = board.getBoxes()[startX][endX];
        final Cell finalCell = board.getBoxes()[startY][endY];
        Move move = new Move(player, initialCell, finalCell);
        return makeMove(move);
    }

    private boolean areValidPoints(List<Integer> points) {
        for (Integer point: points) {
            if (point<0 || point>=8) {
                return false;
            }
        }
        return true;
    }

    private boolean makeMove(Move move){
        // It's not current user's turn
        if (!currentTurn.equals(move.getPlayer())) {
            return false;
        }

        // empty cell.
        Piece piece = move.getInitialCell().getPiece();
        if (piece == null) return false;

        //if the start piece color is not same as player's color.
        if (!piece.isPieceWhite().equals(currentTurn.isWhiteSide())) {
            return false;
        }

        // if the piece can't be moved.
        if (!move.getInitialCell().getPiece().canMove(board, move.getInitialCell(),
                    move.getFinalCell())) {
                return false;
        }

        //move piece
        piece.movePiece();
        board.getBoxes()[move.getFinalCell().getX()][move.getFinalCell().getY()].setPiece(piece);
        board.getBoxes()[move.getInitialCell().getX()][move.getInitialCell().getY()].setPiece(null);
        if (currentTurn.equals(playerOne)) {
            currentTurn = playerTwo;
        } else {
            currentTurn = playerOne;
        }

        return true;
    }

}
