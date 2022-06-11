package service;

import java.util.Arrays;
import java.util.List;

import javafx.util.Pair;
import model.Board;
import model.Cell;
import model.GameStatus;
import model.Move;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.Player;
import model.pieces.Queen;

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
        this.gameStatus = GameStatus.ACTIVE;
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public void setGameStatus(final GameStatus status) {
        this.gameStatus = status;
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

        //pawn converting to queen
        boolean isWhiteAndAtLastRow = currentTurn.isWhiteSide() && move.getFinalCell().getX()==7;
        boolean isBlackAndAtLastRow = !currentTurn.isWhiteSide() && move.getFinalCell().getX()==0;
        if (board.getBoxes()[move.getFinalCell().getX()][move.getFinalCell().getY()]
                .getPiece().getClass().equals(Pawn.class)
                && (isBlackAndAtLastRow || isWhiteAndAtLastRow)) {
            Piece newQueenPiece = new Queen(currentTurn.isWhiteSide());
            board.getBoxes()[move.getFinalCell().getX()][move.getFinalCell().getY()].setPiece(newQueenPiece);
        }

        if (isOtherPlayerOnCheque() && !canOtherPlayerMakeMoves()) {
            System.out.printf("%s wins the game", currentTurn.getId());
            if (currentTurn.isWhiteSide()) {
                setGameStatus(GameStatus.WHITE_WIN);
            }
            else {
                setGameStatus(GameStatus.BLACK_WIN);
            }
        }
        if (!isOtherPlayerOnCheque() && !canOtherPlayerMakeMoves()) {
            System.out.printf("%s Stalemate", currentTurn.getId());
            setGameStatus(GameStatus.STALEMATE);
        }

        if (currentTurn.equals(playerOne)) {
            currentTurn = playerTwo;
        } else {
            currentTurn = playerOne;
        }
        return true;
    }

    private boolean isOtherPlayerOnCheque() {
        Cell[][] boxes = board.getBoxes();
        Pair kingPos = new Pair(0,0);
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boxes[i][j].getPiece() != null
                        && boxes[i][j].getPiece().isPieceWhite() != currentTurn.isWhiteSide()
                        && boxes[i][j].getPiece().isPieceKing()) {
                    kingPos = new Pair<>(i, j);
                    break;
                }
            }
        }

        for (int i=0; i<8 ;i++) {
            for (int j=0; j<8; j++) {
                if (board.getBoxes()[i][j].getPiece() != null
                    && board.getBoxes()[i][j].getPiece().isPieceWhite().equals(currentTurn.isWhiteSide())) {
                    List<Pair> nextMoves = board.getBoxes()[i][j].getPiece().getNextMoves(board,
                            board.getBoxes()[i][j]);
                    if (!nextMoves.isEmpty() && nextMoves.contains(kingPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canOtherPlayerMakeMoves() {
        for (int i=0; i<8 ;i++) {
            for (int j=0; j<8; j++) {
                if(board.getBoxes()[i][j].getPiece() != null
                        && !board.getBoxes()[i][j].getPiece().isPieceWhite().equals(currentTurn.isWhiteSide())) {
                    if (!board.getBoxes()[i][j].getPiece().getNextMoves(board,
                            board.getBoxes()[i][j]).isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
