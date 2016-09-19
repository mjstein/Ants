import Colony.AntColony;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Ants"); // Display the string.
        AntColony ac = new AntColony(100,20);
        ac.setupColony(100);
        for(ArrayList<Integer> i: ac.tickAnts()) {
            System.out.println("Evolution " + i.size() + ":" + i);
        }
    }
}
