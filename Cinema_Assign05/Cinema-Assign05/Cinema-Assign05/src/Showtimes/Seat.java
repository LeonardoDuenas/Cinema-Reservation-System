package Showtimes;

public class Seat {
    private final int row;
    private final int column;
    private boolean available;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }




    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
