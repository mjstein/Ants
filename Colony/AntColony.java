package Colony;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AntColony {
    private static Random randomGenerator = new Random();
    static int getRandom(){
        return randomGenerator.nextInt(100) - 49;
    }

    private int foodFactor ; //abundance of food
    private Integer evolutions; //number of life cycles;
    private ArrayList<Ant> ants = new ArrayList<>();
    private interface iterateOverAnts {

         void forAllAnts(int i);

    }
    public AntColony(){
        this(100,1);  //default constructor
    }

    public AntColony(int foodFactor, int evolutions){
        this.foodFactor = foodFactor;
        this.evolutions = evolutions;
    }

    public void setupColony(Integer numberOfAnts) {
        iterateOverAnts ioa = (i) -> {
            Ant ant = new Ant();
            ants.add(ant);
        };
        loopOverAnts(numberOfAnts, ioa);
    }

    public ArrayList<ArrayList<Integer>> tickAnts(){
        ArrayList<ArrayList<Integer>> evolutionReportAnts = new ArrayList<>();
        for(int i=0; i<(evolutions); i++) {
            pruneAnts();
            evolutionReportAnts.add(reportAnts());
        }
        return evolutionReportAnts;
    }

    public int getColonySize() {
        return ants.size();
    }

    private void pruneAnts()
    {
        Iterator<Ant> iter = ants.iterator();
        int antsToAdd=0;
        while (iter.hasNext()) {
            Ant ant = iter.next();

            if (ant.killAnt()) {
                iter.remove();
            }
            else if (ant.breedAnt()) {
                ant.setHealth();
                antsToAdd++;
            }
          }
          for(int i=0;i<antsToAdd;i++) {
            ants.add(new Ant());
          }

        }


    private ArrayList<Integer> reportAnts() {
        ArrayList<Integer> antReport = new ArrayList<>();
        iterateOverAnts ioa = (i) -> {
            Ant ant = ants.get(i);
                ant.eat(foodFactor);
                ant.expendAnt();
                antReport.add(ant.reportAnt());
        };
        loopOverAnts(ants.size(),ioa);
        return antReport;
    }

    private void loopOverAnts(Integer numberOfAnts, iterateOverAnts iterationMethod){
        IntStream.range(0, numberOfAnts).forEach(iterationMethod::forAllAnts);
    }
}


