package recover_docs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * This is the main class. The user inputs various parameters to generate an appropriate genetic algorithm...
 *
 * @author Katie Lee #6351696
 * @date 2020/11/03
 */

public class main_recover {

    static int popSize;
    static int chromosomeLength;
    static int maxGenSpan;
    static int tournamentK;
    static double mutationRate;
    static double crossoverRate;
    static char[][] shreddedDocument;
    static int crossoverMethod;
    static boolean bonusSelection = false;

    static public population pop;


    public static void main(String[] args) {

        //setting up the configurations
        setUp();

        //initialize genetic algorithm
        genetic_algorithm ga = new genetic_algorithm(popSize, chromosomeLength, maxGenSpan, tournamentK, mutationRate, crossoverRate, shreddedDocument, crossoverMethod);

        //initialize the population
        population pop = ga.initPopulation();
        int generation = 1;

        //looping through each generation
        while (generation <= maxGenSpan) {
            System.out.println("Generation " + generation + ":");
            if (bonusSelection) {
                pop = ga.runBonus(pop);
            } else {
                pop = ga.runPopulation(pop);
            }

            System.out.println("> Best individual fitness = " + pop.getFittest().getFitness()
                     + "\n> Average population fitness = " + pop.getPopulationFitness()
                     + "\n__________________________________________________");
            generation++;
        }

        //fittest individual from last generation
        individual finalInd = pop.getFittest();

        //printing the un-shredded document
        assert shreddedDocument != null;
        char[][] unshredded = fitness_calculator.unshred(shreddedDocument, finalInd.getChromosome());
        fitness_calculator.prettyPrint(unshredded);

        //printing the parameters used
        System.out.println("GA parameters used to get the above solution:\n>> Population size: "+popSize+
                "\n>> Chromosome length: "+chromosomeLength+"\n>> Max generations: "+maxGenSpan+"\n>> Tournament K: "
                +tournamentK+"\n>> Mutation rate: "+mutationRate+"\n>> Crossover rate: "+crossoverRate+
                "\n>> Crossover method: "+crossoverMethod+" (1: OX, 2: UOX)\n>> Bonus: "+bonusSelection+" (1: Y, 2: N)");

        //printing the fittest individual's chromosome
        System.out.print("\nBest individual fitness = "+finalInd.getFitness()+"\nBest individual chromosome: ");
        finalInd.printChromosome();

    }

    private static void setUp() {
        double[] input = new double[8];
        try {
            //reading in the configuration file
            File filename = new File("config\\main_config.txt");
            Scanner scan = new Scanner(filename);
            for (int i=0; i<8; i++) {
                double data = scan.nextDouble();
                input[i] = data;
                scan.next();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("__Error: File not found.");
            e.printStackTrace();
        }
        //setting up configuration variables
        popSize = (int)input[0];
        chromosomeLength = (int)input[1];
        maxGenSpan = (int)input[2];
        tournamentK = (int)input[3];
        mutationRate = input[4];
        crossoverRate = input[5];
        crossoverMethod = (int)input[6];
        if ((int)input[7] == 2) bonusSelection = true;
        shreddedDocument = fitness_calculator.getShreddedDocument("config\\FILE_TO_READ.txt");
    }

}