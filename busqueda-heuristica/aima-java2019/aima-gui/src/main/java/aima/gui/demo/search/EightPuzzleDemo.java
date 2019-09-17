package aima.gui.demo.search;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest;
import aima.core.search.framework.HeuristicFunction;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction;
import aima.core.environment.eightpuzzle.NoConsistentHeuristicFunction;
import aima.core.environment.eightpuzzle.NullHeuristicFunction;
import aima.core.search.framework.Problem;
import aima.core.search.framework.QSearch;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.GraphSearchRectifyExpanded;
import aima.core.search.framework.qsearch.GraphSearchReinsertExpanded;
import aima.core.search.framework.qsearch.QueueSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;

/**
 * @author Ravi Mohan
 * Ultima modificacion: 12/09/2019
 * 
 */

public class EightPuzzleDemo {
	// Este es el estado inicial
		public static EightPuzzleBoard initialState = new EightPuzzleBoard(new int[]
				// Banco de ejemplos para el 8-puzzle

				// Coste 5
//					{1, 0, 3, 8, 2, 5, 7, 4, 6}
//					{1, 4, 2, 0, 8, 3, 7, 6, 5} 
//					{1, 3, 4, 8, 6, 2, 7, 0, 5}
//					{1, 2, 3, 7, 8, 0, 6, 5, 4}
//					{1, 4, 2, 8, 3, 0, 7, 6, 5}
//					{1, 2, 3, 0, 8, 6, 7, 5, 4}
//					{1, 2, 3, 0, 4, 5, 8, 7, 6}
//					{1, 0, 2, 8, 6, 3, 7, 5, 4}
//					{2, 8, 3, 1, 6, 4, 7, 0, 5}

				// Coste 10	
//					{8, 2, 1, 7, 0, 3, 6, 5, 4}
//					{1, 4, 0, 8, 5, 2, 7, 3, 6}
//					{8, 1, 3, 7, 0, 5, 4, 2, 6}
//					{8, 1, 2, 4, 0, 6, 7, 5, 3}
//					{0, 1, 3, 7, 2, 5, 4, 8, 6}
//					{1, 4, 0, 7, 8, 2, 6, 5, 3}
//					{3, 8, 4, 1, 6, 2, 0, 7, 5}
//					{1, 3, 4, 6, 7, 2, 0, 8, 5}
//					{3, 2, 4, 1, 0, 5, 8, 7, 6}
//					{8, 1, 0, 2, 5, 3, 7, 4, 6}

				// Coste 15
					{4, 8, 2, 6, 3, 5, 1, 0, 7}
//					{1, 4, 5, 2, 7, 0, 8, 6, 3}
//					{1, 3, 8, 6, 7, 4, 2, 0, 5}
//					{2, 0, 8, 7, 5, 3, 4, 1, 6}
//					{7, 1, 3, 4, 5, 0, 8, 2, 6}
//					{1, 3, 6, 7, 2, 0, 4, 5, 8}
//					{7, 0, 3, 5, 1, 8, 2, 6, 4}
//					{6, 3, 5, 2, 1, 0, 8, 4, 7}
//					{6, 0, 3, 8, 1, 5, 4, 2, 7}
//					{7, 8, 3, 1, 5, 0, 4, 2, 6}

				// Coste 20
//					{6, 2, 7, 4, 5, 1, 0, 8, 3}
//					{4, 7, 2, 1, 0, 6, 3, 5, 8}
//					{7, 1, 5, 4, 0, 8, 2, 6, 3}
//					{5, 1, 6, 4, 0, 3, 8, 7, 2}
//					{7, 1, 4, 5, 0, 6, 3, 2, 8}
//					{2, 4, 0, 6, 3, 1, 7, 8, 5}
//					{3, 5, 6, 2, 4, 7, 0, 1, 8}
//					{1, 4, 7, 6, 8, 5, 0, 3, 2}
//					{6, 4, 0, 2, 8, 1, 7, 3, 5}
//					{4, 1, 3, 7, 2, 8, 5, 6, 0}

				// coste 25
//					{6, 7, 4, 0, 5, 1, 3, 2, 8}
//					{6, 0, 7, 5, 4, 1, 3, 8, 2}
//					{3, 4, 8, 5, 7, 1, 6, 0, 2}
//					{4, 5, 3, 7, 6, 2, 8, 0, 1}
//					{2, 7, 8, 5, 4, 0, 3, 1, 6}


				// coste 30
//				 	{5, 6, 7, 2, 8, 4, 0, 3, 1}
//					{5, 6, 7, 4, 0, 8, 3, 2, 1}
//					{5, 4, 7, 6, 0, 3, 8, 2, 1} 
//					{3, 8, 7, 4, 0, 6, 5, 2, 1}
//					{5, 6, 3, 4, 0, 2, 7, 8, 1}

				// Instancias que ¿no se resuelven? con h3 y la version de A* que no rectifica
//				 	{ 6, 3, 2, 5, 7, 8, 0, 4, 1 } // 24
//				 	{ 6, 3, 2, 5, 8, 1, 4, 0, 7 } // 23
//				 	{ 3, 5, 6, 4, 2, 7, 0, 8, 1 } // 24
//				 	{ 7, 0, 2, 3, 6, 1, 5, 8, 4 } // 21

		);

		public static EightPuzzleGoalTest goalState = new EightPuzzleGoalTest();
		
		public static final String METRIC_TIME_TAKEN = "timeTaken(ms)";


	public static void main(String[] args) {
		
		/* ----------------- */
		/* BUSQUEDA A CIEGAS */
		/* ----------------- */
		
		/* ----------------------------------------------------------------------- */
		/* Algoritmos iterativos con busqueda basada en cola (implementan QSearch) */
		/* ----------------------------------------------------------------------- */
		
		//eightPuzzleUninformedSearchDemo(new DepthFirstSearch(new TreeSearch())); 		// Busqueda en profundidad en arboles
		eightPuzzleUninformedSearchDemo(new DepthFirstSearch(new GraphSearch())); 	// Busqueda en profundidad normal en grafos
		
		//eightPuzzleUninformedSearchDemo(new BreadthFirstSearch(new TreeSearch())); 	// Busqueda en anchura en arboles
		//eightPuzzleUninformedSearchDemo(new BreadthFirstSearch(new GraphSearch())); 	// Busqueda en anchura en grafos
		
		//eightPuzzleUninformedSearchDemo(new UniformCostSearch(new TreeSearch())); 	// Busqueda con coste uniforme en arboles 
		//eightPuzzleUninformedSearchDemo(new UniformCostSearch(new GraphSearch())); 	// Busqueda con coste uniforme en grafos 
		
		/* ------------------------------------------ */
		/* Algoritmos recursivos (implementan Search) */
		/* ------------------------------------------ */
		
		//eightPuzzleDepthLimitedSearchDemo(10);	 					// Busqueda en profundidad limitada
		//eightPuzzleIterativeDeepeningSearchDemo(); 					// Busqueda en profundidad iterativa
		
		
		/* -------------------- */
		/* BUSQUEDA INTELIGENTE */
		/* -------------------- */
		
		/* A* con busqueda en arboles */
		//eightPuzzleAStarDemo(new AStarSearch(new TreeSearch(),new NullHeuristicFunction()));
		//eightPuzzleAStarDemo(new AStarSearch(new TreeSearch(),new MisplacedTilleHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new TreeSearch(),new ManhattanHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new TreeSearch(),new NoConsistentHeuristicFunction(goalState)));
		
		/* A* con busqueda en grafos, con reexpansion de nodos ya expandidos */
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchReinsertExpanded(),new NullHeuristicFunction()));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchReinsertExpanded(),new MisplacedTilleHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchReinsertExpanded(),new ManhattanHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchReinsertExpanded(),new NoConsistentHeuristicFunction(goalState)));
		
		/* A* con busqueda en grafos, con rectificacion de nodos ya expandidos */
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(),new NullHeuristicFunction()));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(),new MisplacedTilleHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(),new ManhattanHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(),new NoConsistentHeuristicFunction(goalState)));
		
		/* A* con monitorizacion de los f-valores de los nodos expandidos */
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(new NullHeuristicFunction()),new NullHeuristicFunction()));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(new MisplacedTilleHeuristicFunction(goalState)),new MisplacedTilleHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(new ManhattanHeuristicFunction(goalState)),new ManhattanHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchRectifyExpanded(new NoConsistentHeuristicFunction(goalState)),new NoConsistentHeuristicFunction(goalState)));
		
		/* A* con busqueda en grafos, sin rectificar ni reexpandir nodos ya expandidos */
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearch(),new NullHeuristicFunction()));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearch(),new MisplacedTilleHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearch(),new ManhattanHeuristicFunction(goalState)));
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearch(),new NoConsistentHeuristicFunction(goalState)));
		
		
		/* Algoritmos no admisibles */
		
		/* Algoritmo de Ponderacion Estatica, PEA*: f(n) = g(n)+epsilon*h(n), epsilon >=1 */
		//eightPuzzleAStarDemo(new AStarSearch(new GraphSearchReinsertExpanded(),new ManhattanHeuristicFunction(goalState, 0.2)));
		
		/* Greedy Best First Search; f(n) = h(n) */
		//eightPuzzleGreedyBestFirstDemo(new MisplacedTilleHeuristicFunction(goalState));
		//eightPuzzleGreedyBestFirstDemo(new ManhattanHeuristicFunction(goalState));
		
		/* A* con un heuristico no admisible */
		// . . . . .
		
	}

	
	// BUSQUEDA A CIEGAS
	
	// Busqueda iterativa basada en cola (QSearch)
	private static void eightPuzzleUninformedSearchDemo(QSearch search) {
		String searchAlgorithm = search.getClass().getName().substring(1+search.getClass().getName().lastIndexOf("."));
		String searchSpace = search.getImplementation().getClass().getName().substring(1+search.getImplementation().getClass().getName().lastIndexOf("."));
		System.out.println("\nEightPuzzleDemo: " + "[" + searchAlgorithm + "]" + " [" + searchSpace +"] -->");
		System.out.println("\nInitial State: \n" + initialState);
		try {
			long tIni = System.currentTimeMillis(); 
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), goalState);
			SearchAgent agent = new SearchAgent(problem, search);
			long tFin = System.currentTimeMillis();
			search.getMetrics().set(METRIC_TIME_TAKEN, tFin - tIni);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Busqueda en profundidad limitada
	private static void eightPuzzleDepthLimitedSearchDemo(int limit) {
		System.out.println("\nEightPuzzleDemo: [DepthLimitedSearch (" + limit + ")] -->");
		System.out.println("\nInitial State: \n" + initialState);
		try {
			long tIni = System.currentTimeMillis(); 	
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), goalState);
			Search search = new DepthLimitedSearch(limit);
			SearchAgent agent = new SearchAgent(problem, search);
			long tFin = System.currentTimeMillis();
			search.getMetrics().set(METRIC_TIME_TAKEN, tFin - tIni);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Busqueda en profundidad iterativa
	private static void eightPuzzleIterativeDeepeningSearchDemo() {
		System.out.println("\nEightPuzzleDemo: [IterativeDeepeningSearch] -->");
		System.out.println("\nInitial State: " + initialState);
		try {
			long tIni = System.currentTimeMillis(); 	
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), goalState);
			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);
			long tFin = System.currentTimeMillis();
			search.getMetrics().set(METRIC_TIME_TAKEN, tFin - tIni);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	// BUSQUEDA INTELIGENTE (guiada por heuristicos)
	
	/* ------------ */
	/* ALGORITMO A* */
	/* ------------ */

	private static void eightPuzzleAStarDemo(QSearch search) {
		String searchAlgorithm = search.getClass().getName().substring(1+search.getClass().getName().lastIndexOf("."));
		String searchSpace = search.getImplementation().getClass().getName().substring(1+search.getImplementation().getClass().getName().lastIndexOf("."));
		String heuristicFunction = search.getHeuristicFunction().getClass().getName().substring(1+search.getHeuristicFunction().getClass().getName().lastIndexOf("."));
		System.out.println("\nEightPuzzleDemo: " + "[" + searchAlgorithm + "] [" + searchSpace +"] [" + heuristicFunction +"] -->");
		System.out.println("Initial State: \n" + initialState);
		try { 
			Problem problem = new Problem(initialState, EightPuzzleFunctionFactory
					.getActionsFunction(), EightPuzzleFunctionFactory
					.getResultFunction(), goalState);
			long tIni = System.currentTimeMillis();
			SearchAgent agent = new SearchAgent(problem, search);
			long tFin = System.currentTimeMillis();
			search.getMetrics().set(METRIC_TIME_TAKEN, tFin - tIni);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// Greedy Best First es Best First con f = h
	private static void eightPuzzleGreedyBestFirstDemo(HeuristicFunction h) {
		String heuristicFunction = h.getClass().getName().substring(1+h.getClass().getName().lastIndexOf("."));
		System.out
				.println("\nEightPuzzleDemo: [Greedy Best First Search] [" + heuristicFunction + "] -->");
		try {		
			long tIni = System.currentTimeMillis(); 		
			Problem problem = new Problem(initialState,
					EightPuzzleFunctionFactory.getActionsFunction(),
					EightPuzzleFunctionFactory.getResultFunction(),
					goalState);
			Search search = new GreedyBestFirstSearch(new GraphSearch(),
						h);
			SearchAgent agent = new SearchAgent(problem, search);
			long tFin = System.currentTimeMillis();
			search.getMetrics().set(METRIC_TIME_TAKEN, tFin - tIni);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
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