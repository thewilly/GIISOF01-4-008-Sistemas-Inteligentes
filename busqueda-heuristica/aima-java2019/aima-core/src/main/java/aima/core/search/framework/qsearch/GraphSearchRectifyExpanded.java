package aima.core.search.framework.qsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import aima.core.agent.Action;
import aima.core.search.framework.HeuristicFunction;
import aima.core.search.framework.Node;
import aima.core.search.framework.Problem;
import aima.core.search.framework.NodeRectifiable;
import aima.core.util.datastructure.Queue;

/**
 * This type of search only works with <code>NodeRectifiable</code>.
 * 
 * @author Paula Diaz Puertas
 * Modificado por Carlos Mencia
 *
 */
public class GraphSearchRectifyExpanded extends QueueSearch {
	
	public static final String METRIC_NODES_RECTIFIED_IN_EXPANDED = "nodesRectifiedInExpanded";
	public static final String METRIC_NODES_RECTIFIED_IN_FRONTIER = "nodesRectifiedInFrontier";
	
	// Saves all the nodes that have been put in the frontier. The key is their state.
	private Map<Object, NodeRectifiable> explored = new HashMap<>();
	
	public GraphSearchRectifyExpanded(HeuristicFunction h){
		this.h = h;
	}
	
	public GraphSearchRectifyExpanded() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Clears the map of explored nodes and calls the search implementation of
	 * <code>QueueSearch</code>
	 */
	@Override
	public List<Action> search(Problem problem, Queue<Node> frontier) {
		// Initialize the explored map to be empty
		explored.clear();
		return super.search(problem, frontier);
	}
	
	/**
	 * Inserts the node at the tail of the frontier if the corresponding state
	 * was not yet explored.
	 */
	protected void insertIntoFrontier(Node node) {
		
		// If the node has already been opened...
		if(explored.containsKey(node.getState())) {
			NodeRectifiable alreadyExploredNode = explored.get(node.getState());
			
			((NodeRectifiable)node.getParent()).addChild(alreadyExploredNode, node.getAction(), node.getStepCost());
			
			// If the new path is better than the path we had before...
			if(node.getPathCost() < alreadyExploredNode.getPathCost()) {
				// We modify the data of the node we have saved with the new path
				alreadyExploredNode.rectify(node);
				
				if(frontier.contains(alreadyExploredNode))
					metrics.incrementInt(METRIC_NODES_RECTIFIED_IN_FRONTIER);
				else
					metrics.incrementInt(METRIC_NODES_RECTIFIED_IN_EXPANDED);

				// The node's children (an their children, etc) must recalculate their pathCost
				// The frontier must be reordered. This is done by removing and reinserting any node that has been modified.
				recalculateChildrensPathCost(alreadyExploredNode);
			}
		}
		
		else {
			
			// If the node hasn't been explored before it's put in the explored map and inserted in the frontier
			NodeRectifiable rNode = NodeRectifiable.cloneNode(node);
			frontier.insert(rNode);
			updateMetrics(frontier.size());
			explored.put(rNode.getState(), rNode);
			
			NodeRectifiable parent = ((NodeRectifiable) rNode.getParent());
			
			if(parent != null)
				parent.addChild(rNode, node.getAction(), node.getStepCost());
		}
	}

	@Override
	protected Node popNodeFromFrontier() {
		Node result = frontier.pop();
		updateMetrics(frontier.size());
		return result;
	}

	@Override
	protected boolean isFrontierEmpty() {
		return frontier.isEmpty();
	}

	/**
	 * Recalculates the node's children path cost recursively.
	 * Forces the frontier to sort itself if the node is in it.
	 * 
	 * @param parent The node whose children's pathcost we want to recalculate
	 */
	private void recalculateChildrensPathCost(NodeRectifiable parent) {
		
		Map<NodeRectifiable,ArrayList<Object>> Mchildren =  parent.getChildren();
		Iterator<NodeRectifiable> children = Mchildren.keySet().iterator();
		
		// For each child of the parent...
		while (children.hasNext()) {
		
			NodeRectifiable child = children.next();
			ArrayList<Object> arr = Mchildren.get(child);
			Action act = (Action)arr.get(0);
			double cost = (double)arr.get(1);
			
			// Recalculate its pathCost [g(n)]
			if(child.recalculatePathCost(parent, cost)){
			// Recalculate the pathCost of its children
				child.setParent(parent);
				child.setAction(act);
				child.setStepCost(cost);

				recalculateChildrensPathCost(child);
			}
		}
		
		if(frontier.contains(parent)) {
			// This way we force the frontier to sort itself
			frontier.remove(parent);
			frontier.insert(parent);
		}
		
	}
	
	
	
	@Override
	public void clearInstrumentation() {
		super.clearInstrumentation();
		metrics.set(METRIC_NODES_RECTIFIED_IN_EXPANDED, 0); 
		metrics.set(METRIC_NODES_RECTIFIED_IN_FRONTIER, 0);
	}
	
}
