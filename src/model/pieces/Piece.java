package model.pieces;

import java.util.List;

import javafx.util.Pair;
import model.Board;
import model.Cell;

public abstract class Piece {
    private final boolean isKing;

    private final boolean isWhite;

    private boolean isMoved = false;

    protected Piece(final boolean white, final boolean king){
        isWhite = white;
        isKing = king;
    }

    public Boolean isPieceWhite() {
        return isWhite;
    }

    public Boolean isPieceKing() {
        return isKing;
    }

    public Boolean isPieceMoved() {
        return isMoved;
    }

    public void movePiece() {
        isMoved = true;
    }

    public boolean canMove(final Board board, final Cell start, final Cell end) {
        // We can't move to a place where a piece of same color exists.
        if (end.getPiece() != null && this.isPieceWhite().equals(end.getPiece().isPieceWhite())) {
            return false;
        }

        List<Pair> nextMoves = this.getNextMoves(board, start);
        if (nextMoves.contains(new Pair(end.getX(), end.getY()))) {
            Board tempBoard = board;
            Piece piece = tempBoard.getBoxes()[start.getX()][start.getY()].getPiece();
            tempBoard.getBoxes()[start.getX()][start.getY()].setPiece(null);
            tempBoard.getBoxes()[end.getX()][end.getY()].setPiece(piece);
            return !canKingBeKilled(tempBoard);
        }
        return false;
    }

    public abstract List<Pair> getNextMoves(Board board, Cell curr);

    protected boolean isValidPoint(int x, int y) {
        if (x < 0 || x >= 8) return false;
        return y >= 0 && y < 8;
    }

    public boolean canKingBeKilled(Board board) {
        Cell[][] boxes = board.getBoxes();
        Pair kingPos = new Pair(0,0);
        for (int i=0; i<8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boxes[i][j].getPiece() != null
                        && boxes[i][j].getPiece().isPieceWhite() == this.isWhite
                        && boxes[i][j].getPiece().isPieceKing()) {
                    kingPos = new Pair<>(i, j);
                    break;
                }
            }
        }
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (boxes[i][j].getPiece() != null
                        && boxes[i][j].getPiece().isPieceWhite() != this.isWhite
                        && boxes[i][j].getPiece().getNextMoves(board, boxes[i][j]).contains(kingPos)) {
                    return true;
                }
            }
        }
        return false;
    }

}
