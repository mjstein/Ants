package Colony;

import static Colony.AntColony.*;

class Ant {
    private int health = 100; //0-100 health of ant

    int reportAnt(){
        return health;
    }

    void eat(int foodFactor){
        health += (getRandom() * (foodFactor))/100;
    }

    void expendAnt(){
        health -= 20;
    }

}

