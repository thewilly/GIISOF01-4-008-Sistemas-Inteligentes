package aima.core.search.local;

import java.util.Random;
import java.util.Set;

import aima.core.search.local.geneticFunctions.GeneticMutationFunction;
import aima.core.search.local.geneticFunctions.GeneticReproductiveFunction;
import aima.core.search.local.geneticFunctions.mutationFunctions.RandomFlipTwoGenesMF;
import aima.core.search.local.geneticFunctions.reproductiveFunctions.AlternatePointsCrossoverNoRepetitionRF;
import aima.core.search.local.geneticFunctions.reproductiveFunctions.BasicRF;
import aima.core.search.local.geneticFunctions.reproductiveFunctions.TwoPointsCrossoverNoRepetitionRF;

/**
 * 
 * @author Paula D�az Puertas
 *
 * @param <A>
 * 			the type of the alphabet used in the representation of the
 *          individuals in the population (this is to provide flexibility in
 *          terms of how a problem can be encoded).
 */
public class FlexibleGeneticAlgorithm<A> extends GeneticAlgorithm<A> {
	
	protected int numberOfGenerations;

	private GeneticReproductiveFunction<A> reproductiveFunction;
	private GeneticMutationFunction<A> mutationFunction;
	
	/*public FlexibleGeneticAlgorithm(int individualLength, Set<A> finiteAlphabet, double mutationProbability) {
		this(individualLength, finiteAlphabet, mutationProbability, 1, false, 100, new Random());
	}

	public FlexibleGeneticAlgorithm(int individualLength, Set<A> finiteAlphabet, double mutationProbability,
			int numberOfGenerations) {
		this(individualLength, finiteAlphabet, mutationProbability, 1, false, numberOfGenerations, new Random());
	}*/

	public FlexibleGeneticAlgorithm(int individualLength, Set<A> finiteAlphabet, double mutationProbability,
			double crossoverProbability, boolean elitism, int numberOfGenerations, Random random) {
		super(individualLength, finiteAlphabet, mutationProbability, crossoverProbability, elitism, random);

		setNumberOfGenerations(numberOfGenerations);
		
		// Operadores de cruce y mutacion
		reproductiveFunction = new TwoPointsCrossoverNoRepetitionRF<A>();
		//reproductiveFunction = new AlternatePointsCrossoverNoRepetitionRF<A>();
		mutationFunction = new RandomFlipTwoGenesMF<A>();
		//mutationFunction = new RandomSortingSublistMF<A>();
	}

	public void setNumberOfGenerations(int numberOfGenerations) {
		
		assert (numberOfGenerations >= 0);
		
		this.numberOfGenerations = numberOfGenerations;
	}
	
	public void setElitism(boolean elitism) {
		this.elitism = elitism;
	}

	@Override
	protected boolean notFinished() {
		return getIterations() < numberOfGenerations;
	}

	@Override
	protected Individual<A> reproduce(Individual<A> x, Individual<A> y) {
		return reproductiveFunction.reproduce(x, y);
	}

	@Override
	protected Individual<A> mutate(Individual<A> child) {
		return mutationFunction.mutate(child);
	}

	public void setReproductiveFunction(GeneticReproductiveFunction<A> reproductiveFunction) {
		assert (reproductiveFunction != null);
		
		this.reproductiveFunction = reproductiveFunction;
	}

	public void setMutationFunction(GeneticMutationFunction<A> mutationFunction) {
		assert (mutationFunction != null);
		
		this.mutationFunction = mutationFunction;
	}
	
	public GeneticReproductiveFunction<A> getReproductiveFuncion(){
		return reproductiveFunction;
	}
	
	public GeneticMutationFunction<A> getMutationFuncion(){
		return mutationFunction;
	}

}
