# Cinema Reservation System

A Java-based cinema reservation system designed to provide a seamless booking experience. This project includes essential functionalities such as user authentication, movie reservations, a loyalty points system, and personalized movie recommendations.

## Features
- **User Authentication**: Secure login system for various user types (e.g., admin, customer).
- **Movie Reservation**: Easily reserve movies and manage bookings.
- **Loyalty Points System**: Accumulate points with every reservation and redeem them for rewards.
- **Custom Recommendations**: Personalized recommendations based on user preferences and past activity.

## Design Patterns Used
This project leverages several design patterns to ensure scalability, maintainability, and clean code architecture:

### 1. **Factory Pattern**
   - Used to create different types of users (e.g., Admin, Regular User, Premium User) based on the input or context.
   - Simplifies object creation and promotes loose coupling between client classes and the classes they instantiate.

   ```java
   public class Login {
       public static Login loginFactory(LoginType type){ //factory design
        Login login = null;
        switch (type){
            case UserLogin:
                login = new UserLogin();
                break;
            case EmpLogin:
                login = new EmpLogin();
                break;
        }
        return login;
       }
   }
   ```

### 2. **Singleton Pattern**
   - Ensures that the mock database instance is created only once and shared across the application.
   - Prevents multiple instances of the database and ensures global access to the shared resource.

   ```java
   public class Database {
       public static DataBase Instance = new DataBase();
       private DataBase(){
           InitializeData();
       }
   }
   ```

## Getting Started

### Prerequisites
- **Java JDK 8+**
- IDE (e.g., IntelliJ, Eclipse)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/cinema-reservation-system.git
   ```
2. Open the project in your IDE.
3. Compile and run the `Main.java` file.

### Usage
- Log in using a pre-defined user account or create a new one.
- Reserve movies, earn loyalty points, and receive custom recommendations!

