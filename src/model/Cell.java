package model;

import model.pieces.Piece;

public class Cell {
    private Piece piece;
    private final Integer x;
    private final Integer y;

    public Cell(final Integer x, final Integer y, final Piece piece){
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public void setPiece(final Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece(){
        return piece;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
