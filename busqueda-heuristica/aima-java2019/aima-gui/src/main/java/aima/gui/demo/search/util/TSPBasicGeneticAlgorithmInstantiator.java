package aima.gui.demo.search.util;

import java.util.Set;

import aima.core.environment.tsp.City;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.BasicGeneticAlgorithm;

public class TSPBasicGeneticAlgorithmInstantiator extends AbstractTSPGeneticAlgorithmInstantiator {

	@Override
	protected GeneticAlgorithm<City> instantiate(int individualLength, Set<City> finiteAlphabet,
			int popSize, int numberOfGenerations, double crossoverProbability, double mutationProbability, boolean elitism) {
		return new BasicGeneticAlgorithm<City>(individualLength, finiteAlphabet, mutationProbability);
	}

}
