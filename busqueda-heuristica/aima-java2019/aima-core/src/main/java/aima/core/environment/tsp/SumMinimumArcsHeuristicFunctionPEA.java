package aima.core.environment.tsp;

import java.util.List;

import aima.core.util.datastructure.PriorityQueue;

/**
 * @author Paula D�az Puertas
 */
public class SumMinimumArcsHeuristicFunctionPEA extends AbstractTSPHeuristicFunction {
	
	private double multiplicationFactor = 1.0; // For security init allways at 1.0, this value does not modify the product at h.
	
	public SumMinimumArcsHeuristicFunctionPEA(double multiplicationFactor) {
		super();
		this.multiplicationFactor = multiplicationFactor;
	}

	@Override
	public double h(Object state) {
		
		//We get all the routes than can be taken in this state
		List<City> toVisit = getCitiesForRoutes(state);
		
		PriorityQueue<Double> arcs = new PriorityQueue<>();
		
		for(City from : toVisit) {
			
			for(City to : toVisit) {
				
				Double cost = from.getCost(to);
				
				if(cost != null)
					arcs.add(cost);
				
			}
		}
		
		double minArcSum = 0;
		
		for(int i = 1; i < toVisit.size(); i++) {
			if(!arcs.isEmpty())
				minArcSum += arcs.poll();
		}
		
		return (minArcSum * multiplicationFactor);
	}

}
