package aima.gui.demo.search.util;

import java.util.Random;
import java.util.Set;

import aima.core.environment.tsp.City;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.geneticFunctions.GeneticMutationFunction;
import aima.core.search.local.geneticFunctions.GeneticReproductiveFunction;
import aima.core.search.local.geneticFunctions.reproductiveFunctions.TwoPointsCrossoverNoRepetitionRF;
import aima.core.search.local.FlexibleGeneticAlgorithm;

public class TSPFlexibleGeneticAlgorithmInstantiator extends AbstractTSPGeneticAlgorithmInstantiator {

	@Override
	protected GeneticAlgorithm<City> instantiate(int individualLength, Set<City> finiteAlphabet,
			int popSize, int numberOfGenerations, double crossoverProbability, double mutationProbability, boolean elitism) 
	{
		FlexibleGeneticAlgorithm<City> sga = new FlexibleGeneticAlgorithm<City>(individualLength, finiteAlphabet, mutationProbability,
				crossoverProbability, elitism, numberOfGenerations, new Random());
		sga.setPopulationSize(popSize);
		return sga;
	}

}


