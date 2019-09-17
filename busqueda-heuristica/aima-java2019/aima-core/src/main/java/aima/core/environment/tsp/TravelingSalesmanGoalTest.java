package aima.core.environment.tsp;

import aima.core.search.framework.GoalTest;

/**
 * @author Paula D�az Puertas
 */
public class TravelingSalesmanGoalTest implements GoalTest {

	@Override
	public boolean isGoalState(Object state) {
		return ((TravelingSalesmanState) state).areAllCitiesVisited();
	}

}
