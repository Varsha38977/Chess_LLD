package model.pieces;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import model.Board;
import model.Cell;

public class Knight extends Piece {

    public Knight(final boolean white){
        super(white, false);
    }

    @Override
    public List<Pair> getNextMoves(Board board, Cell curr) {
        Integer[] xDir = {1, -1, 2, -2, 1, -1, 2, -2};
        Integer[] yDir = {2, -2, 1, -1, -2, 2, -1, 1};
        List<Pair> possibleMoves = new ArrayList<>();
        Cell[][] boxes = board.getBoxes();
        int currX = curr.getX();
        int currY = curr.getY();
        for (int i=0; i<8; i++){
            int newX = currX + xDir[i];
            int newY = currY + yDir[i];
            if (isValidPoint(newX, newY)
                    && (boxes[newX][newY].getPiece() == null
                    || !boxes[newX][newY].getPiece().isPieceWhite().equals(this.isPieceWhite()))) {
                possibleMoves.add(new Pair(newX, newY));
            }
        }
        return possibleMoves;
    }
}
