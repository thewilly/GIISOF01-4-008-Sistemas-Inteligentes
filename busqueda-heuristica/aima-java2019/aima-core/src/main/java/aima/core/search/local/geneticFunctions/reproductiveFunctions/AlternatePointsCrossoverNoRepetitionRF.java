package aima.core.search.local.geneticFunctions.reproductiveFunctions;

import java.util.ArrayList;
import java.util.List;

import aima.core.environment.tsp.City;
import aima.core.search.local.Individual;
import aima.core.search.local.geneticFunctions.AbstractGeneticFunction;
import aima.core.search.local.geneticFunctions.GeneticReproductiveFunction;

public class AlternatePointsCrossoverNoRepetitionRF<A> extends AbstractGeneticFunction implements GeneticReproductiveFunction<A> {

	@Override
	public Individual<A> reproduce(Individual<A> firstParent, Individual<A> secondParent) {
		// n <- LENGTH(x);
		// Note: this is = this.individualLength
		// c <- random number from 1 to n
		
		int individualLength = firstParent.length();
		
		int p1 = randomOffset(individualLength);
		int p2 = randomOffset(individualLength);
		List<A> xArray = firstParent.getRepresentation();
		List<A> yArray = secondParent.getRepresentation();
		List<A> offArray = new ArrayList<A>(xArray);

		// even positions from parent 1
		// remainder from parent 2, keeping relative order
		
		for (int i = 1; i < individualLength; i=i+2) offArray.set(i,offArray.get(i-1));
		
		int k=1;
		
		int i=0;
		// Busco el siguiente de yArray que no este en offArray y lo pongo en offArray[k]
		while (i<yArray.size()){
			// compruebo si yArray[i] esta en offArray, si no lo pongo en offArray[k]
			int j=0;
			while (j<offArray.size() && (yArray.get(i)!=offArray.get(j)))
				j++;
			if (j>=offArray.size()) {
				offArray.set(k, yArray.get(i));
				k=k+2;
			}
			i++;
		}	
		
		//for (int l=0; i<offArray.size(); l++) System.out.print(offArray.get(l));
		
		return new Individual<A>(offArray);
	}

}
