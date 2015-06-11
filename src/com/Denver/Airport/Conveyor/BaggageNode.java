package com.Denver.Airport.Conveyor;

import java.util.ArrayList;
import java.util.List;

public class BaggageNode implements Comparable<BaggageNode>{
	private String name;	
	private int minDistance =Integer.MAX_VALUE;
	private List<ConveyorPath> adjencies ; 
	private BaggageNode prevNode;
	//public boolean isVisited=false;
	public BaggageNode(String name)
	{
		this.name=name;
		adjencies=new ArrayList<ConveyorPath>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	public List<ConveyorPath> getAdjencies() {
		return adjencies;
	}

	public void addAdjencies(List<ConveyorPath> adjencie) {
		this.adjencies.addAll(adjencie) ;
	}

	public BaggageNode getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(BaggageNode prevNode) {
		this.prevNode = prevNode;
	}

	public String toString()
	{
		return name;   
	}

	@Override
	public int compareTo(BaggageNode other) {

		return Integer.compare(minDistance,other.minDistance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaggageNode other = (BaggageNode) obj;

		if (!name.equals(other.name))
			return false;

		return true;
	}

}
