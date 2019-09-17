package aima.core.search.local;

import java.util.Random;
import java.util.Set;

import aima.core.search.framework.GoalTest;
import aima.core.search.local.geneticFunctions.GeneticMutationFunction;
import aima.core.search.local.geneticFunctions.GeneticReproductiveFunction;
//import aima.core.search.local.geneticFunctions.mutationFunctions.BasicMF;
import aima.core.search.local.geneticFunctions.mutationFunctions.RandomSortingSublistMF;
//import aima.core.search.local.geneticFunctions.reproductiveFunctions.BasicRF;
import aima.core.search.local.geneticFunctions.reproductiveFunctions.TwoPointsCrossoverNoRepetitionRF;

/**
 * Artificial Intelligence A Modern Approach (3rd Edition): Figure 4.8, page
 * 129.<br>
 * <br>
 * 
 * <pre>
 * function GENETIC-ALGORITHM(population, FITNESS-FN) returns an individual
 *   inputs: population, a set of individuals
 *           FITNESS-FN, a function that measures the fitness of an individual
 *           
 *   repeat
 *     new_population &lt;- empty set
 *     for i = 1 to SIZE(population) do
 *       x &lt;- RANDOM-SELECTION(population, FITNESS-FN)
 *       y &lt;- RANDOM-SELECTION(population, FITNESS-FN)
 *       child &lt;- REPRODUCE(x, y)
 *       if (small random probability) then child &lt;- MUTATE(child)
 *       add child to new_population
 *     population &lt;- new_population
 *   until some individual is fit enough, or enough time has elapsed
 *   return the best individual in population, according to FITNESS-FN
 * --------------------------------------------------------------------------------
 * function REPRODUCE(x, y) returns an individual
 *   inputs: x, y, parent individuals
 *   
 *   n &lt;- LENGTH(x); c &lt;- random number from 1 to n
 *   return APPEND(SUBSTRING(x, 1, c), SUBSTRING(y, c+1, n))
 * </pre>
 * 
 * Figure 4.8 A genetic algorithm. The algorithm is the same as the one
 * diagrammed in Figure 4.6, with one variation: in this more popular version,
 * each mating of two parents produces only one offspring, not two.
 * 
 * @author Ciaran O'Reilly
 * @author Mike Stampone
 * 
 * @param <A>
 *            the type of the alphabet used in the representation of the
 *            individuals in the population (this is to provide flexibility in
 *            terms of how a problem can be encoded).
 */
public class BasicGeneticAlgorithm<A> extends GeneticAlgorithm<A> {
	
	//private BasicRF<A> rf;
	//private BasicMF<A> mf;
	private GeneticReproductiveFunction<A> rf;
	private GeneticMutationFunction<A> mf;
	
	
	private long maxTimeMilliseconds;
	private long maxNumberOfIterations;
	private GoalTest goalTest;

	public BasicGeneticAlgorithm(int individualLength, Set<A> finiteAlphabet,
			double mutationProbability) {
		this(individualLength, finiteAlphabet, mutationProbability, 0, 0,
				new Random());
	}
	
	public BasicGeneticAlgorithm(int individualLength, Set<A> finiteAlphabet,
			double mutationProbability, long maxTimeMilliseconds, long maxNumberOfIterations) {
		this(individualLength, finiteAlphabet, mutationProbability, maxTimeMilliseconds, maxNumberOfIterations,
				new Random());
	}

	public BasicGeneticAlgorithm(int individualLength, Set<A> finiteAlphabet,
			double mutationProbability, long maxTimeMilliseconds, long maxNumberOfIterations, Random random) {
		
		super(individualLength, finiteAlphabet, mutationProbability, 1, false, random);
		
		setMaxTimeMilliseconds(maxTimeMilliseconds);
		setMaxNumberOfIterations(maxNumberOfIterations);
		
		//rf = new BasicRF<>();
		rf = new TwoPointsCrossoverNoRepetitionRF<>();
		//mf = new BasicMF<>(this.finiteAlphabet);
		mf = new RandomSortingSublistMF<>();
	}
	
	public void setMaxTimeMilliseconds(long maxTimeMilliseconds) {
		
		assert (maxTimeMilliseconds >= 0);
		
		this.maxTimeMilliseconds = maxTimeMilliseconds;
	}

	public void setMaxNumberOfIterations(long maxNumberOfIterations) {
		
		assert (maxNumberOfIterations >= 0);
		
		this.maxNumberOfIterations = maxNumberOfIterations;
	}
	@Override
	public Individual<A> geneticAlgorithm(Set<Individual<A>> population,
			FitnessFunction<A> fitnessFn, GoalTest goalTest) {
		
		this.goalTest = goalTest;
		
		return super.geneticAlgorithm(population, fitnessFn, goalTest);
	}

	@Override
	protected boolean notFinished() {
		//It's not finished if the time isn't out and we haven't found a solution
		return !isTimeOut() && !goalTest.isGoalState(bestIndividual) && !isMaxIter();
	}

	private boolean isTimeOut() {
		return maxTimeMilliseconds > 0L && getTimeInMilliseconds() > maxTimeMilliseconds;
	}
	
	private boolean isMaxIter() {
		return maxNumberOfIterations > 0 && getIterations() >= maxNumberOfIterations;
	}

	@Override
	protected Individual<A> reproduce(Individual<A> x, Individual<A> y) {
		return rf.reproduce(x, y);
	}

	@Override
	protected Individual<A> mutate(Individual<A> child) {
		return mf.mutate(child);
	}
}