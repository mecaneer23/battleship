public class Location {
    enum Status {
        UNGUESSED,
        HIT,
        MISSED,
    }

    private Status status;
    private boolean ship;

    public Location() {
        this.status = Status.UNGUESSED;
    }

    public void markHit() {
        this.status = Status.HIT;
    }

    public void markMiss() {
        this.status = Status.MISSED;
    }

    public boolean hasShip() {
        return this.ship;
    }

    public void setShip(boolean val) {
        this.ship = val;
    }

    public Status getStatus() {
        return this.status;
    }
}
