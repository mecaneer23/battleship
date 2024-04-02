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

    public boolean checkHit() {
        return this.status == Status.HIT;
    }

    public boolean checkMiss() {
        return this.status == Status.MISSED;
    }

    public boolean isUnguessed() {
        return this.status == Status.UNGUESSED;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}
