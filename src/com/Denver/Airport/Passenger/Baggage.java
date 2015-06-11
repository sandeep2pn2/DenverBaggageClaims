package com.Denver.Airport.Passenger;

import java.util.PriorityQueue;

import com.Denver.Airport.Conveyor.BaggageNode;
import com.Denver.Airport.Conveyor.ConveyorPath;

public class Baggage {
	
	private String BagId ;
	private BaggageNode loadingPoint ; //Source
	private String flightID ;
	
	private BaggageNode boardingPoint ; //Destination
	
	public Baggage(String Id,BaggageNode ldingPoint,String flightID)
	{
		this.BagId=Id ;
		this.loadingPoint=ldingPoint ;
		this.flightID=flightID;
	}
	
	public String getBagId() {
		return BagId;
	}
	public BaggageNode getBoardingPoint() {
		return boardingPoint;
	}

	public void setBoardingPoint(BaggageNode boardingPoint) {
		this.boardingPoint = boardingPoint;
	}

	public BaggageNode getLoadingPoint() {
		return loadingPoint;
	}
	public String getFlightID() {
		return flightID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Baggage other = (Baggage) obj;
		if (BagId == null) {
			if (other.BagId != null)
				return false;
		} else if (!BagId.equals(other.BagId))
			return false;
		return true;
	}
	
	public void computePaths()
	{
		BaggageNode source=this.loadingPoint ;
		BaggageNode destination=this.boardingPoint ;
		source.setMinDistance(0);
		PriorityQueue<BaggageNode> pq=new PriorityQueue<BaggageNode>();
		pq.add(source);
		while(!pq.isEmpty())
		{

			BaggageNode u= pq.poll();			
			for (ConveyorPath edge : u.getAdjencies()) {						
				BaggageNode v=edge.getTarget() ;				
				int weight=edge.getWeight();
				int distanceThroughU=u.getMinDistance()+weight ;
				if(distanceThroughU<v.getMinDistance())
				{
					pq.remove(v);
					v.setMinDistance(distanceThroughU);
					v.setPrevNode(u);
					pq.add(v);
				}
				/*if(v.getName().equalsIgnoreCase(destination.getName()))
				{					
					return;
				}	*/	
				
				if(v.equals(destination))
				{					
					return;
				}
			}

		}
	}	

	public void printPath(BaggageNode source,BaggageNode dest)
	{
		if(source.equals(dest))
			return;
		printPath(source, dest.getPrevNode());
		System.out.print(dest.getName()+" ");
	}
	

}
