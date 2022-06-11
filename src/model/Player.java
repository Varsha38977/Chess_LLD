package model;

import java.util.Objects;
import java.util.UUID;

public class Player {
    private final Boolean isWhite;

    private final String id;

    private final UUID uniqueId;

    public Player(final String id, final Boolean isWhite){
        this.isWhite = isWhite;
        this.id = id;
        uniqueId = UUID.randomUUID();
    }

    public boolean isWhiteSide()
    {
        return this.isWhite;
    }

    public UUID getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass())
        return false;
        Player that = (Player) o;
        return this.uniqueId.equals(that.uniqueId) && this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueId);
    }
}
