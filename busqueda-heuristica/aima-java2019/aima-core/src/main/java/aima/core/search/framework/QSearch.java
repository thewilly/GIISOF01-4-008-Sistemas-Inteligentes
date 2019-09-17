package aima.core.search.framework;

/*
 * Interfaz para las clases Search que utilizan QueueSearch
 * 
 */


import aima.core.search.framework.qsearch.QueueSearch;

public interface QSearch extends Search {
	
	QueueSearch getImplementation();
	HeuristicFunction getHeuristicFunction();

}
