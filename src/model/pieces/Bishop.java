package model.pieces;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import model.Board;
import model.Cell;

public class Bishop extends Piece {

    public Bishop(final boolean white){
        super(white, false);
    }

    @Override
    public List<Pair> getNextMoves(Board board, Cell curr) {
        Integer[] xDir = {1, -1, 1, -1};
        Integer[] yDir = {1, 1, -1, -1};
        List<Pair> possibleMoves = new ArrayList<>();
        Cell[][] boxes = board.getBoxes();
        int currX = curr.getX();
        int currY = curr.getY();
        for (int i=0; i<4; i++) {
            int tempX = currX, tempY= currY;
            for (int j = 0; j < 8; j++) {
                tempX = tempX + xDir[i];
                tempY = tempY + yDir[i];
                if (isValidPoint(tempX, tempY) && (boxes[tempX][tempY].getPiece() == null)) {
                    possibleMoves.add(new Pair(tempX, tempY));
                } else {
                    if (isValidPoint(tempX, tempY)
                        && !boxes[tempX][tempY].getPiece().isPieceWhite().equals(this.isPieceWhite())) {
                        possibleMoves.add(new Pair(tempX, tempY));
                    }
                    break;
                }
            }
        }
        return possibleMoves;
    }
}
