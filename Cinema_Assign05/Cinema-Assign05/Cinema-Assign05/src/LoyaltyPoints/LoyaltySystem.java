package LoyaltyPoints;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class LoyaltySystem {
    private HashMap<String, Integer> loyaltyPoints;
    private ArrayList<String> rewardedCus;

    public LoyaltySystem() {
        loyaltyPoints = new HashMap<>();
        rewardedCus = new ArrayList<>();
    }

    public HashMap<String, Integer> getLoyaltyPointsMap(){
        return loyaltyPoints;
    }

    public ArrayList<String> getRewardedCusList(){
        return rewardedCus;
    }

    // Method to add loyalty points for a user
//    public void addLoyaltyPoints(String username, int points) {
//        if (loyaltyPoints.containsKey(username)) {
//            int currentPoints = loyaltyPoints.get(username);
//            loyaltyPoints.put(username, currentPoints + points);
//        } else {
//            loyaltyPoints.put(username, points);
//        }
//    }
    // Method to add loyalty points for a user
    public void addLoyaltyPoints(String username, int points) {
        int currentPoints = loyaltyPoints.getOrDefault(username, 0);
        loyaltyPoints.put(username, currentPoints + points);
    }

    // Method to reward a user with points
    public void rewardUser(String username, int points) {
        rewardedCus.add(username);
        addLoyaltyPoints(username, points);
    }
    public void updateLoyaltyPoints(String username, int points)
    {
        loyaltyPoints.put(username, points);
    }

    //public boolean hasBeenRewarded(String username) {

    //return rewardedCus.containsKey(username);
    //}

    public void deductLoyaltyPoints(String username, int points) {
        if (loyaltyPoints.containsKey(username)) {
            int currentPoints = loyaltyPoints.get(username);
            if (currentPoints >= points) {
                loyaltyPoints.put(username, currentPoints - points);
            } else {
                System.out.println("Insufficient loyalty points.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    public int getLoyaltyPoints(String username) {
        return loyaltyPoints.getOrDefault(username,0);
    }
}
