package aima.core.environment.tsp;

import java.util.List;

/**
 * @author Paula D�az Puertas
 */
public class ArcsAverageHeuristicFunction extends AbstractTSPHeuristicFunction {

	@Override
	public double h(Object state) {
		
		List<City> toVisit = this.getCitiesForRoutes(state);
		
		double sumArcs = 0;
		
		int routes = 0;
		
		for(City from : toVisit) {
			
			for(City to : toVisit) {
				
				Double cost = from.getCost(to);
				
				if(cost != null) {
					sumArcs += cost;
					routes++;
				}
			}
		}
		
		return (sumArcs / routes) * (toVisit.size() - 1);
	}

}
