package Miscellenous;

public class TowerOfHanoi {

    // Move a disc from A to B using C
    // Move a disc from A to C
    // Move a disc from B to C using A
    // For understanding, check for 1 , 2 and 3 cases
    // Link : https://www.youtube.com/watch?v=q6RicK1FCUs

    public static void main(String[] args) {
        towerOfHanoi(3, "A", "B", "C");
    }

    private static void towerOfHanoi(int noOfRods, String firstTower, String secondTower, String thirdTower){
        if(noOfRods > 0) {
            towerOfHanoi(noOfRods-1, firstTower, thirdTower, secondTower);
            System.out.println("Move a disc from " + firstTower + " to " + thirdTower);
            towerOfHanoi(noOfRods-1, secondTower, firstTower, thirdTower);
        }
    }
}
