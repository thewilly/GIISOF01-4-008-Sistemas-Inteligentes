package aima.core.search.local.geneticFunctions.mutationFunctions;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.local.Individual;
import aima.core.search.local.geneticFunctions.AbstractGeneticFunction;
import aima.core.search.local.geneticFunctions.GeneticMutationFunction;

public class RandomFlipTwoGenesMF<A> extends AbstractGeneticFunction implements GeneticMutationFunction<A> {
	
	@Override
	public Individual<A> mutate(Individual<A> cromosome) {
		// Random swapping of two positions
			
		int individualLength = cromosome.length();
			
		int p = randomOffset(individualLength - 1);
		int c = randomOffset(individualLength - 1);
		ArrayList<A> mutatedRepresentation = new ArrayList<A>(cromosome.getRepresentation());
	
		A temp = mutatedRepresentation.get(p);
		mutatedRepresentation.set(p, mutatedRepresentation.get(c));
		mutatedRepresentation.set(c, temp);
		
		return (new Individual<A>(mutatedRepresentation));
	}

}
