package model;

import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;

public class Board {
    Cell[][] boxes;

    public Board() {
        this.resetBoard();
    }

    public Cell[][] getBoxes() {
        return boxes;
    }
    public Cell getCell(final Integer x, Integer y) {
        return boxes[x][y];
    }

    private void resetBoard() {
        //set all the 64 boxes.
        boxes = new Cell[8][8];
        boxes[0][0] = new Cell(0, 0, new Rook(true));
        boxes[0][1] = new Cell(0, 1, new Knight(true));
        boxes[0][2] = new Cell(0, 2, new Bishop(true));
        boxes[0][3] = new Cell(0, 3, new King(true));
        boxes[0][4] = new Cell(0, 4, new Queen(true));
        boxes[0][5] = new Cell(0, 5, new Bishop(true));
        boxes[0][6] = new Cell(0, 6, new Knight(true));
        boxes[0][7] = new Cell(0, 7, new Rook(true));

        boxes[7][0] = new Cell(7, 0, new Rook(false));
        boxes[7][1] = new Cell(7, 1, new Knight(false));
        boxes[7][2] = new Cell(7, 2, new Bishop(false));
        boxes[7][3] = new Cell(7, 3, new King(false));
        boxes[7][4] = new Cell(7, 4, new Queen(false));
        boxes[7][5] = new Cell(7, 5, new Bishop(false));
        boxes[7][6] = new Cell(7, 6, new Knight(false));
        boxes[7][7] = new Cell(7, 7, new Rook(false));

        for(int j=0; j<8; j++) {
            boxes[1][j] = new Cell(1, j, new Pawn(true));
            boxes[6][j] = new Cell(6, j, new Pawn(false));
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Cell(i, j, null);
            }
        }
    }
}
