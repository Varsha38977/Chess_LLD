package model;
public class Move {
    private final Player player;

    private final Cell initialCell;

    private final Cell finalCell;

    public Move(final Player player, final Cell start,final Cell end)
    {
        this.player = player;
        this.initialCell = start;
        this.finalCell = end;
    }

    public Player getPlayer() {
        return player;
    }

    public Cell getInitialCell() {
        return initialCell;
    }

    public Cell getFinalCell() {
        return finalCell;
    }
}
