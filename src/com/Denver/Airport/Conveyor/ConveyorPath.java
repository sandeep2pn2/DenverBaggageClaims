package com.Denver.Airport.Conveyor;



public class ConveyorPath  {
	private BaggageNode target ;
	public BaggageNode getTarget() {
		return target;
	}

	public void setTarget(BaggageNode target) {
		this.target = target;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	private  int weight;
	
	public ConveyorPath(int wt ,BaggageNode v)
	{
		this.target=v;
		this.weight=wt;		
	}
	

}
