package model.pieces;


import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import model.Board;
import model.Cell;

public class Pawn extends Piece {

    public Pawn(final boolean white){
        super(white, false);
    }

    @Override
    public List<Pair> getNextMoves(Board board, Cell curr) {
        List<Pair> possibleMoves = new ArrayList<>();
        Cell[][] boxes = board.getBoxes();
        int currX = curr.getX();
        int currY = curr.getY();
        if (this.isPieceWhite()) {
            if (isValidPoint(currX+1, currY+1)) {
                if (boxes[currX+1][currY+1].getPiece() != null
                        && !boxes[currX+1][currY+1].getPiece().isPieceWhite().equals(this.isPieceWhite())){
                    possibleMoves.add(new Pair(currX+1, currY+1));
                }
            }
            if (isValidPoint(currX+1, currY-1)) {
                if (boxes[currX+1][currY-1].getPiece() != null
                        && !boxes[currX+1][currY-1].getPiece().isPieceWhite().equals(this.isPieceWhite())){
                    possibleMoves.add(new Pair(currX+1, currY-1));
                }
            }
            if (isValidPoint(currX+1, currY)) {
                if (boxes[currX+1][currY].getPiece() == null){
                    possibleMoves.add(new Pair(currX+1, currY));
                }
            }

            if (isValidPoint(currX+2, currY)) {
                if (!this.isPieceMoved() && boxes[currX+2][currY].getPiece() == null
                        && boxes[currX+2][currY].getPiece() == null){
                    possibleMoves.add(new Pair(currX+2, currY));
                }
            }
        } else {
            if (isValidPoint(currX-1, currY+1)) {
                if (boxes[currX-1][currY+1].getPiece() != null
                        && !boxes[currX-1][currY+1].getPiece().isPieceWhite().equals(this.isPieceWhite())){
                    possibleMoves.add(new Pair(currX-1, currY+1));
                }
            }
            if (isValidPoint(currX-1, currY-1)) {
                if (boxes[currX-1][currY-1].getPiece() != null
                        && !boxes[currX-1][currY-1].getPiece().isPieceWhite().equals(this.isPieceWhite())){
                    possibleMoves.add(new Pair(currX-1, currY-1));
                }
            }
            if (isValidPoint(currX-1, currY)) {
                if (boxes[currX-1][currY].getPiece() == null ){
                    possibleMoves.add(new Pair(currX-1, currY));
                }
            }
            if (isValidPoint(currX-2, currY)) {
                if (!this.isPieceMoved() && boxes[currX-2][currY].getPiece() == null
                        && boxes[currX-2][currY].getPiece() == null){
                    possibleMoves.add(new Pair(currX-2, currY));
                }
            }
        }
        return possibleMoves;
    }
}
