package aima.gui.demo.search.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import aima.core.environment.tsp.City;
import aima.core.environment.tsp.TSPGeneticAlgorithmUtil;
import aima.core.environment.tsp.TravelingSalesmanState;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public abstract class AbstractTSPGeneticAlgorithmInstantiator {

	public GeneticAlgorithm<City> instantianteGeneticAlgorithm(TravelingSalesmanState problem,
			int popSize, int numGen, double Pc, double Pm, boolean elitism) {

		List<City> cities = getAllCities(problem);

		Set<City> finiteAlphabet = new HashSet<>();

		finiteAlphabet.addAll(cities);

		return instantiate(cities.size(), finiteAlphabet, popSize, numGen, Pc, Pm, elitism);
	}
	
	public List<City> getAllCities(TravelingSalesmanState problem) {
		List<City> cities = new ArrayList<>();

		if (problem.getStarterCity() != null)
			cities.add(problem.getStarterCity());

		cities.addAll(problem.getNotVisited());
		
		return cities;
	}
	
	//protected abstract GeneticAlgorithm<City> instantiate(int individualLength, Set<City> finiteAlphabet, double mutationProbability);
	
	protected abstract GeneticAlgorithm<City> instantiate(int individualLength, Set<City> finiteAlphabet, int popSize, int numGen, double Pc, double Pm, boolean elitism);

}
