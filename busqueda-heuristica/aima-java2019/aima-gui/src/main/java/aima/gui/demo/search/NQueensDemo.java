package aima.gui.demo.search;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.environment.nqueens.AttackingPairsHeuristic;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensBoard.Config;
import aima.core.environment.nqueens.NQueensFunctionFactory;
import aima.core.environment.nqueens.NQueensGenAlgoUtil;
import aima.core.environment.nqueens.NQueensGoalTest;
import aima.core.search.framework.GoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.BasicGeneticAlgorithm;
import aima.core.search.local.HillClimbingSearch;
import aima.core.search.local.Individual;
import aima.core.search.local.Scheduler;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

/**
 * @author Ravi Mohan
 * 
 */

public class NQueensDemo {

	private static final int boardSize = 16;

	public static void main(String[] args) {

		newNQueensDemo();
	}

	private static void newNQueensDemo() {

		//nQueensWithDepthFirstSearch();
		//nQueensWithBreadthFirstSearch();
		//nQueensWithRecursiveDLS();
		//nQueensWithIterativeDeepeningSearch();
		//nQueensSimulatedAnnealingSearch();
		//nQueensHillClimbingSearch();
		nQueensGeneticAlgorithmSearch();
	}

	private static void nQueensWithRecursiveDLS() {
		System.out.println("\nNQueensDemo recursive DLS -->");
		try {
			Problem problem = new Problem(new NQueensBoard(boardSize), NQueensFunctionFactory.getIActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
			Search search = new DepthLimitedSearch(boardSize);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void nQueensWithBreadthFirstSearch() {
		try {
			System.out.println("\nNQueensDemo BFS -->");
			Problem problem = new Problem(new NQueensBoard(boardSize), NQueensFunctionFactory.getIActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
			Search search = new BreadthFirstSearch(new TreeSearch());
			SearchAgent agent2 = new SearchAgent(problem, search);
			printActions(agent2.getActions());
			printInstrumentation(agent2.getInstrumentation());
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	private static void nQueensWithDepthFirstSearch() {
		System.out.println("\nNQueensDemo DFS -->");
		try {
			Problem problem = new Problem(new NQueensBoard(boardSize), NQueensFunctionFactory.getIActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
			Search search = new DepthFirstSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void nQueensWithIterativeDeepeningSearch() {
		System.out.println("\nNQueensDemo Iterative DS  -->");
		try {
			Problem problem = new Problem(new NQueensBoard(boardSize), NQueensFunctionFactory.getIActionsFunction(),
					NQueensFunctionFactory.getResultFunction(), new NQueensGoalTest());
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);

			System.out.println();
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void nQueensSimulatedAnnealingSearch() {
		System.out.println("\nNQueensDemo Simulated Annealing  -->");
		try {
			Problem problem = new Problem(new NQueensBoard(boardSize, Config.QUEENS_IN_FIRST_ROW),
					NQueensFunctionFactory.getCActionsFunction(), NQueensFunctionFactory.getResultFunction(),
					new NQueensGoalTest());
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(new AttackingPairsHeuristic(),
					new Scheduler(20, 0.045, 100));
			SearchAgent agent = new SearchAgent(problem, search);

			System.out.println();
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void nQueensHillClimbingSearch() {
		System.out.println("\nNQueensDemo HillClimbing  -->");
		try {
			Problem problem = new Problem(new NQueensBoard(boardSize, Config.QUEENS_IN_FIRST_ROW),
					NQueensFunctionFactory.getCActionsFunction(), NQueensFunctionFactory.getResultFunction(),
					new NQueensGoalTest());
			HillClimbingSearch search = new HillClimbingSearch(new AttackingPairsHeuristic());
			SearchAgent agent = new SearchAgent(problem, search);

			System.out.println();
			printActions(agent.getActions());
			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void nQueensGeneticAlgorithmSearch() {
		System.out.println("\nNQueensDemo GeneticAlgorithm  -->");
		try {
			FitnessFunction<Integer> fitnessFunction = NQueensGenAlgoUtil.getFitnessFunction();
			GoalTest goalTest = NQueensGenAlgoUtil.getGoalTest();
			// Generate an initial population
			Set<Individual<Integer>> population = new HashSet<Individual<Integer>>();
			for (int i = 0; i < 100; i++) {
				population.add(NQueensGenAlgoUtil.generateRandomIndividual(boardSize));
			}

			//BasicGeneticAlgorithm<Integer> ga = new BasicGeneticAlgorithm<Integer>(boardSize,
					//NQueensGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize), 0.15);
			
			BasicGeneticAlgorithm<Integer> ga = new BasicGeneticAlgorithm<Integer>(boardSize,
					NQueensGenAlgoUtil.getFiniteAlphabetForBoardOfSize(boardSize), 0.15);

			// Run for a set amount of time
			long maxTime = 1000L;
			ga.setMaxTimeMilliseconds(maxTime);
			Individual<Integer> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest);

			System.out.println("Max Time (" + maxTime + " miliseconds) Best Individual=\n"
					+ NQueensGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
			System.out.println("Fitness         = " + fitnessFunction.getValue(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Run till goal is achieved
			/*
			ga.setMaxTimeMilliseconds(0L);
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest);

			System.out.println("");
			System.out
					.println("Goal Test Best Individual=\n" + NQueensGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
			System.out.println("Fitness         = " + fitnessFunction.getValue(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			*/
			
			// Run for a nuumber of generations
			ga.setMaxTimeMilliseconds(0L);
			long maxIterations = 100;
			ga.setMaxNumberOfIterations(maxIterations);
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest);

			System.out.println("");
			System.out
					.println(maxIterations + " iterations Best Individual=\n" + NQueensGenAlgoUtil.getBoardForIndividual(bestIndividual));
			System.out.println("Board Size      = " + boardSize);
			System.out.println("# Board Layouts = " + (new BigDecimal(boardSize)).pow(boardSize));
			System.out.println("Fitness         = " + fitnessFunction.getValue(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Iterations       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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

}