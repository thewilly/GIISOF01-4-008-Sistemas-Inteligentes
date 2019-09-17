package aima.core.search.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aima.core.agent.Action;

/**
 * @author Paula D�az Puertas
 */
public class NodeRectifiable extends Node {
	
	private Map<NodeRectifiable,ArrayList<Object>> children = new HashMap<>();

	public NodeRectifiable(Object state) {
		super(state);
	}
	
	public NodeRectifiable(Object state, Node parent, Action action, double stepCost) {
		super(state, parent, action, stepCost);
		this.stepCost = stepCost;
	}
	
	
	/**
	 * Rectifies this node with the data of the node passed, meaning it changes
	 * its parent, action, pathCost and stepCost. It does not recalculate the
	 * path cost of its children.
	 * 
	 * @param node The node with the data we want to use to rectify this one.
	 * 		Its state must be equal to this one.
	 */
	public void rectify(Node node) {
		
		if(!node.getState().equals(getState()))
			throw new IllegalArgumentException("Cannot rectify the node "
					+ "with the data of a node with another state.");
		
		this.parent = node.getParent();
		((NodeRectifiable) parent).addChild(this, node.getAction(), node.getStepCost());
		
		this.action = node.getAction();
		this.pathCost = node.getPathCost();
		stepCost = node.getStepCost();
		
	}
	
	
	public boolean recalculatePathCost(NodeRectifiable parent, double stepCost) {
		double prevPC = pathCost;
		pathCost = parent.getPathCost() + stepCost;
		if(pathCost > prevPC) pathCost = prevPC;

		return prevPC > pathCost;
	}
	
	
	public void addChild(NodeRectifiable child, Action act, double stepCost) {
		ArrayList<Object> array = new ArrayList<Object>();
		array.add((Object)act); 
		array.add((Object)(new Double(stepCost)));
		children.put(child, array);
	}
	
	public void removeChild(NodeRectifiable child) {
		children.remove(child);
	}
	
	
	public Map<NodeRectifiable,ArrayList<Object>> getChildren(){
		return children;
	}
	
	public static NodeRectifiable cloneNode(Node node) {
		double stepCost = 0;
		
		if(node.getParent() != null)
			stepCost = node.getPathCost() - node.getParent().getPathCost();
		
		return new NodeRectifiable(node.getState(), node.getParent(), node.getAction(), stepCost);
	}

}
