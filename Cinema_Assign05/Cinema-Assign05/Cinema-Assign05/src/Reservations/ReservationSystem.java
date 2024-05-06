package Reservations;

import java.util.Queue;
import java.util.Stack;
public class ReservationSystem {
    private Stack<Reservation> reservations;
    private Queue<Reservation> reservationRequest;

    public ReservationSystem(){
        this.reservations = new Stack<>();
    }

    public void AddToReservationRequest(Reservation reservation){
        reservations.push(reservation);
        System.out.println("Reservation added for: " + reservation.getCustomerName().getName());
    }
    public Stack<Reservation> getReservations() {
        return reservations;
    }

    public void NextReservation(){
        if (!reservations.isEmpty()) {
            Reservation reservation = reservations.pop();
            System.out.println("The Next Reservation for : " + reservation.getCustomerName().getName());
        } else {
            System.out.println("There is no new reservation request.");
        }
    }

    public void CancelReservation(){
        if (!reservations.isEmpty()) {
            Reservation canceledReservation = reservations.pop();
            System.out.println("Reservation canceled for: " + canceledReservation.getCustomerName().getName());
        } else {
            System.out.println("There is no reservation to cancel.");
        }
    }

    public void displayAllReservations() {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println("Movie: " + reservation.getMovie().getTitle() +
                    ", Showtime: " + reservation.getShowtime().getStartTime() + " - " + reservation.getShowtime().getEndTime() +
                    "\nSeats" +
                    "\nRow: "+reservation.getCustomerName().getSeats().get(0).getRow() + ", Column: " + reservation.getCustomerName().getSeats().get(0).getColumn() +
                    ", Customer: " + reservation.getCustomerName().getName());
        }
    }
}
