package aima.gui.demo.search;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.NullHeuristicFunction;
import aima.core.environment.tsp.*;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.qsearch.*;
import aima.core.search.informed.AStarSearch;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.FlexibleGeneticAlgorithm;
import aima.core.search.local.Individual;
import aima.core.search.local.geneticFunctions.GeneticMutationFunction;
import aima.core.search.local.geneticFunctions.GeneticReproductiveFunction;
import aima.gui.demo.search.util.AbstractTSPGeneticAlgorithmInstantiator;
import aima.gui.demo.search.util.TSPBasicGeneticAlgorithmInstantiator;
import aima.gui.demo.search.util.TSPFlexibleGeneticAlgorithmInstantiator;
import aima.gui.demo.search.util.TitledPart;



public class TSPDemo {
	
	public static final String METRIC_TIME_TAKEN = "timeTaken(ms)";

	public static void main(String[] args) {

		System.out.println("- TSP IMPLEMENTATION TESTING -\n\n");

		List<TitledPart<TravelingSalesmanState>> problems = new ArrayList<>();
		problems.add(new TitledPart<TravelingSalesmanState>("Problem 1", instantiateProblem1()));
		//problems.add(new TitledPart<TravelingSalesmanState>("Problem 2", instantiateProblem2()));
		//problems.add(new TitledPart<TravelingSalesmanState>("Problem 3", instantiateProblem3()));
		//problems.add(new TitledPart<TravelingSalesmanState>("Problem 4 - gr24.tsp", instantiateProblem("gr24.tsp")));
		
		// ALGORITMO A*
		
		List<TitledPart<HeuristicFunction>> heuristics = new ArrayList<>();
		heuristics.add(new TitledPart<HeuristicFunction>("Null heuristic", new NullHeuristicFunction()));
		//heuristics.add(new TitledPart<HeuristicFunction>("Minimum cost arcs heuristic", new MinimumCostArcHeuristicFunction()));
		//heuristics.add(new TitledPart<HeuristicFunction>("Sum minimum arcs heuristic", new SumMinimumArcsHeuristicFunction()));
		//heuristics.add(new TitledPart<HeuristicFunction>("Arcs average heuristic", new ArcsAverageHeuristicFunction()));
		//heuristics.add(new TitledPart<HeuristicFunction>("Spanning tree heuristic", new SpanningTreeHeuristicFunction()));
		

		List<TitledPart<QueueSearch>> searchs = new ArrayList<>();
		//searchs.add(new TitledPart<QueueSearch>("[CONSISTENT]", new GraphSearch()));
		//searchs.add(new TitledPart<QueueSearch>("[RECTIFY EXPANDED]", new GraphSearchRectifyExpanded()));
		searchs.add(new TitledPart<QueueSearch>("[REINSERT EXPANDED]", new GraphSearchReinsertExpanded()));

		for (TitledPart<TravelingSalesmanState> problem : problems) {

			for (TitledPart<HeuristicFunction> heuristic : heuristics) {

				for (TitledPart<QueueSearch> search : searchs) {

					AStarSearch(search.getTitle() + " " + problem.getTitle() + " " + heuristic.getTitle(),
							problem.getPart(), search.getPart(), heuristic.getPart());
				}
			}
			
		// ALGORITMO GENETICO
			
		// Parametros
		int popSize = 50;
		int numGen = 50;
		double Pc = 1.0;
		double Pm = 0.2;
		boolean elitism = true;
		// Operadores de cruce y mutacion en FlexibleGeneticAlgorithm
		// Monitorizacion de best y average en GeneticAlgorithm
			
		List<TitledPart<AbstractTSPGeneticAlgorithmInstantiator>> instantiators = new ArrayList<>();
		instantiators.add(new TitledPart<AbstractTSPGeneticAlgorithmInstantiator>("[FLEXIBLE GENETIC ALGORITHM]", 
				new TSPFlexibleGeneticAlgorithmInstantiator()));

		for (TitledPart<AbstractTSPGeneticAlgorithmInstantiator> instantiator : instantiators)
				geneticAlgorithmSearch(instantiator.getTitle() + " " + problem.getTitle(), problem.getPart(), instantiator.getPart(), 
						popSize, numGen, Pc, Pm, elitism);
		}

	}
	
	//Builds an instance from a file with format as gr17.txt
	private static TravelingSalesmanState instantiateProblem(String file) {
	
			Set<City> cities = new HashSet<>();
			City inicial = null;
			int dimension = 0;
			
			Formatter dos = new Formatter(System.out);
			Scanner dis = null;
			  try {
			    dis = new Scanner(new FileInputStream(file));
			    String s="a";
			    while (dis.hasNext() && !s.contains("DIMENSION:")) {
			       s = dis.next();
			       //dos.format("%s\n", s);
			    }
			    if (s.contains("DIMENSION:")) dimension = dis.nextInt();
			    else throw new Exception("Mal los datos ... 1");
			    
			    dis.nextLine();
			    while (dis.hasNext() && !s.equals("0")) {
				       s = dis.next();
				       dos.format("%s\n", s);
			    }
			    if(!s.equals("0")) throw new Exception("Mal los datos ... 2");
			    
			    City [] vCity = new City [dimension];		    
			    for (int i = 0; i<dimension; i++){
			    	vCity[i] = new City("city"+i);
			    	cities.add(vCity[i]);
			    }			
			    // Departure city
			    inicial = vCity[0];
			    // Read distances
			    for (int i=1; i<dimension; i++){
				   for (int j=0; j<i; j++){
					   int x = dis.nextInt();
					   vCity[i].addSymmetricCost(vCity[j], (double)x);
				   }
				   int y = dis.nextInt();
				   if(y!=0) throw new Exception("Mal los datos ... 3");
			    }
			   } catch (Exception e) {
				   System.out.println(e);
			   }
			  //System.out.println("\n Cities: " + cities);
			  return new TravelingSalesmanState(cities, inicial);
	}

	private static TravelingSalesmanState instantiateProblem1() {
		City a = new City("A");
		City b = new City("B");
		City c = new City("C");
		City d = new City("D");
		City e = new City("E");
		City f = new City("F");

		a.addSymmetricCost(f, 92D);
		a.addSymmetricCost(e, 113D);
		a.addSymmetricCost(d, 15D);
		a.addSymmetricCost(c, 12D);

		b.addSymmetricCost(f, 9D);
		b.addSymmetricCost(e, 25D);
		b.addSymmetricCost(d, 32D);
		b.addSymmetricCost(c, 7D);

		d.addSymmetricCost(f, 39D);
		d.addSymmetricCost(e, 180D);

		e.addSymmetricCost(f, 17D);

		Set<City> cities = new HashSet<>();
		cities.add(a);
		cities.add(b);
		cities.add(c);
		cities.add(d);
		cities.add(e);
		cities.add(f);

		return new TravelingSalesmanState(cities, c);
	}

	private static TravelingSalesmanState instantiateProblem2() {
		City a = new City("A");
		City b = new City("B");
		City c = new City("C");
		City d = new City("D");
		City e = new City("E");
		City f = new City("F");

		a.addSymmetricCost(b, 1D);
		a.addSymmetricCost(c, 3D);
		a.addSymmetricCost(d, 7D);
		a.addSymmetricCost(e, 8D);
		a.addSymmetricCost(f, 6D);

		b.addSymmetricCost(c, 3D);
		b.addSymmetricCost(d, 7D);
		b.addSymmetricCost(e, 8D);
		b.addSymmetricCost(f, 6D);

		c.addSymmetricCost(d, 5D);
		c.addSymmetricCost(e, 6D);
		c.addSymmetricCost(f, 8D);

		d.addSymmetricCost(e, 2D);
		d.addSymmetricCost(f, 5D);

		e.addSymmetricCost(f, 4D);

		Set<City> cities = new HashSet<>();
		cities.add(a);
		cities.add(b);
		cities.add(c);
		cities.add(d);
		cities.add(e);
		cities.add(f);

		return new TravelingSalesmanState(cities, c);
	}

	private static TravelingSalesmanState instantiateProblem3() {
		City a = new City("A");
		City b = new City("B");
		City c = new City("C");
		City d = new City("D");
		City e = new City("E");
		City f = new City("F");

		a.addSymmetricCost(b, 21D);
		a.addSymmetricCost(c, 12D);
		a.addSymmetricCost(d, 15D);
		a.addSymmetricCost(e, 113D);
		a.addSymmetricCost(f, 92D);

		b.addSymmetricCost(c, 7D);
		b.addSymmetricCost(d, 32D);
		b.addSymmetricCost(e, 25D);
		b.addSymmetricCost(f, 9D);

		c.addSymmetricCost(d, 5D);
		c.addSymmetricCost(e, 18D);
		c.addSymmetricCost(f, 20D);

		d.addSymmetricCost(e, 180D);
		d.addSymmetricCost(f, 39D);

		e.addSymmetricCost(f, 17D);

		Set<City> cities = new HashSet<>();
		cities.add(a);
		cities.add(b);
		cities.add(c);
		cities.add(d);
		cities.add(e);
		cities.add(f);

		return new TravelingSalesmanState(cities, c);
	}

	private static void AStarSearch(String title, TravelingSalesmanState initialState, QueueSearch qSearch,
			HeuristicFunction heuristic) {

		System.out.println(title + "\n");
		System.out.println("Initial State:\n" + initialState.toString() + "\n");

		try {
			Problem problem = new Problem(initialState, TravelingSalesmanFunctionFactory.getActionsFunction(),
					TravelingSalesmanFunctionFactory.getResultFunction(), new TravelingSalesmanGoalTest(),
					TravelingSalesmanFunctionFactory.getStepCostFunction());
			Search search = new AStarSearch(qSearch, heuristic);
			
			long tIni = System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long tFin = System.currentTimeMillis();
			
			search.getMetrics().set(METRIC_TIME_TAKEN, tFin - tIni);
			
			printActions(agent.getActions());
			System.out.println();
			printInstrumentation(agent.getInstrumentation());

			System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void geneticAlgorithmSearch(String title, TravelingSalesmanState problem,
			AbstractTSPGeneticAlgorithmInstantiator instantiator, 
			int popSize, int numGen, double Pc, double Pm, boolean elitism) {
		
		System.out.println(title + "\n");
		try {
			FitnessFunction<City> fitnessFunction = TSPGeneticAlgorithmUtil.getFitnessFunction();
			GoalTest goalTest = TSPGeneticAlgorithmUtil.getGoalTest();

			List<City> cities = instantiator.getAllCities(problem);

			// Generate an initial population
			Set<Individual<City>> population = new HashSet<>();
			for (int i = 0; i < popSize; i++) {
				population.add(TSPGeneticAlgorithmUtil.generateRandomIndividual(cities));
			}
			
			GeneticAlgorithm<City> ga = instantiator.instantianteGeneticAlgorithm(problem, popSize, numGen, Pc, Pm, elitism);

			Individual<City> firstBest = ga.retrieveBestIndividual(population, fitnessFunction);
			
			Individual<City> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest);
			
			String originalStringRepresentation = getStringRepresentation(firstBest);
			String stringRepresentation = getStringRepresentation(bestIndividual);

			double originalCost = 1 / fitnessFunction.getValue(firstBest);
			double fitness = fitnessFunction.getValue(bestIndividual);

			System.out.println("\nBest Individual from initial population (cost = " + originalCost + ")\n"
					+ originalStringRepresentation + "\n");

			System.out.println("Best Individual\n" + stringRepresentation);
			System.out.println("Fitness:          " + fitness);
			System.out.println("Cost:        	  " + 1 / fitness);
			System.out.println("Is Goal:          " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size:  " + ga.getPopulationSize());
			System.out.println("Iterations(#Gen): " + ga.getIterations());
			System.out.println("Crossover(Prob):  " + ((FlexibleGeneticAlgorithm<City>)ga).getReproductiveFuncion().getClass().getSimpleName() + "(" + ga.getCrossoverProbability() + ")");
			System.out.println("Mutation(Prob):   " + ((FlexibleGeneticAlgorithm<City>)ga).getMutationFuncion().getClass().getSimpleName() + "(" + ga.getMutationProbability() + ")");
			System.out.println("Time taken:       " + ga.getTimeInMilliseconds() + "ms");

			System.out.println("\n");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -- Métodos auxiliares para mostrar resultados --

	private static void printInstrumentation(Properties properties) {

		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property);
		}
	}

	private static void printActions(List<Action> actions) {

		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			System.out.println(action);
		}
	}

	private static String getStringRepresentation(Individual<City> cromosome) {
		List<City> representation = cromosome.getRepresentation();

		String stringRepresentation = representation.get(0).toString();

		for (int i = 1; i < representation.size(); i++)
			stringRepresentation += " -> " + representation.get(i);

		return stringRepresentation;
	}
}
