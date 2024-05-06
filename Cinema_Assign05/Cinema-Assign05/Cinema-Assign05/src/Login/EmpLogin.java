package Login;

import Data.*;
import Utility.*;
import Reservations.*;
public class EmpLogin extends Login{
    ReservationSystem reservationSystem = new ReservationSystem();

    @Override
    public Data generateLogin() {
        Employee currentEmployee = null;

        System.out.println("Employee login");
        System.out.println("The employee must be an existing employee in the database");
        //username: Emp2
        //passwaord: 12345
        System.out.println("=================");
        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            try {
                System.out.println("Enter username: ");
                String empName = this.input.nextLine();

                System.out.println("\nEnter password: ");
                String empPass = this.input.nextLine();

                currentEmployee = DataBase.Instance.getEmployeeByCredentials(empName, empPass);

                if (currentEmployee != null) {
                    loginSuccessful = true;
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            } catch (DataNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

        assert currentEmployee != null;

        return currentEmployee;
    }
}
