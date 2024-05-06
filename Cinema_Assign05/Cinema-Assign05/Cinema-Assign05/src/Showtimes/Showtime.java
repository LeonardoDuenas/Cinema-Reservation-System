package Showtimes;

public class Showtime {

    private String startTime;
    private String endTime;
    private int cinemaHall;
    private boolean[][] seats; // 2D array to represent seats in the cinema hall

    public Showtime(String startTime, String endTime, int cinemaHall,  int numRows, int numColumns) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.cinemaHall = cinemaHall;
        this.seats = new boolean[numRows][numColumns]; // Initialize the seats array
        // Initialize all seats as available
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                this.seats[i][j] = true;
            }
        }
    }

    public String getStartTime() {
        return startTime;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(int cinemaHall) {
        this.cinemaHall = cinemaHall;
    }


    // Method to access seats in the cinema hall
    public boolean[][] getSeats() {
        return seats;
    }

    // Method to reserve a seat
    public boolean reserveSeat(int row, int column) {
        if (row >= 0 && row < seats.length && column >= 0 && column < seats[0].length) {
            if (seats[row][column]) {
                seats[row][column] = true; // Mark the seat as reserved
                return true; // Seat reserved successfully
            } else {
                System.out.println("Seat is already reserved.");
                return false; // Seat already reserved
            }
        } else {
            System.out.println("Invalid seat selection.");
            return false; // Invalid seat selection
        }
    }

    // Method to cancel a reserved seat
    public boolean cancelSeat(int row, int column) {
        if (row >= 0 && row < seats.length && column >= 0 && column < seats[0].length) {
            if (seats[row][column]) {
                seats[row][column] = false; // Mark the seat as available
                return true; // Seat canceled successfully
            } else {
                System.out.println("Seat is not reserved.");
                return false; // Seat not reserved
            }
        } else {
            System.out.println("Invalid seat selection.");
            return false; // Invalid seat selection
        }
    }

    public void displaySeats(){
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j]) {
                    System.out.print("[Available]"); // Available seat
                } else {
                    System.out.print("[Reserved]"); // Reserved seat
                }
                System.out.print("(" + (i) + "-" + (j) + ") "); // Seat number
            }
            System.out.println();
        }
    }
    public void displayShowtime() {
        System.out.println("Start time: " + this.startTime);
        System.out.println("End time: " + this.endTime);
        System.out.println("Hall number: " + this.cinemaHall);
        System.out.println("Available Seats");
        //: Row: " + this.seats);
        // Display seat numbers and availability
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j]) {
                    System.out.print("[Available]"); // Available seat
                } else {
                    System.out.print("[Reserved]"); // Reserved seat
                }
                System.out.print("(" + (i + 1) + "-" + (j + 1) + ") "); // Seat number
            }
            System.out.println();
        }
    }
}
