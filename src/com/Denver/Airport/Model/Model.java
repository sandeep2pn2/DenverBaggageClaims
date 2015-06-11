package com.Denver.Airport.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Denver.Airport.Constants;
import com.Denver.Airport.Departures;
import com.Denver.Airport.Conveyor.BaggageNode;
import com.Denver.Airport.Passenger.Baggage;

public class Model {
	
	//private List<BaggageNode> nodelist;	
	private List<Baggage> baggageList;
	private List<Departures> departureList ;
	private Map<String, BaggageNode> nodeMap;
	private static volatile Model instance; 
	
	public static Model getInstance()
	{
		if (instance==null)
		{
			synchronized (Model.class) {
				if (instance==null) {
					instance=new Model();	
					//instance.nodelist=new ArrayList<>();
					instance.nodeMap=new HashMap<String,BaggageNode>();
					instance.baggageList=new ArrayList<Baggage>();
					instance.departureList=new ArrayList<Departures>();
				}
			}
		}		
		return instance;
	}
	
	public void updateBoardingPoints()
	{
		for (int i = 0; i < baggageList.size(); i++) {
			String thisBagDest=baggageList.get(i).getFlightID() ;
			
			//Check for the arrival flight
			if(thisBagDest.equalsIgnoreCase(Constants.ARRIVAL))
			{
				baggageList.get(i).setBoardingPoint(nodeMap.get("BaggageClaim"));
			}
			
			for(Departures departure:departureList)
			{
				if(thisBagDest.equals(departure.getFlightId()))
				{
					baggageList.get(i).setBoardingPoint(departure.getFlightGate());
					break;
				}
			}
			
		}
		
	}	
	
	/*
	 * 
	 * 
	 * Add , Delete and Get operation on DepartureList
	 */
	
	public void addDeparture(Departures newDeparture)
	{
		this.departureList.add(newDeparture);
	}
	
	public boolean removeDeparture(Departures cancelledFlight)
	{
		return this.departureList.remove(cancelledFlight);
	}
	
	public List<Departures> getdepartureList()
	{
		return departureList ;
	}
	
	/*
	 * 
	 * 
	 * Add , Delete and Get operation on Baggage List
	 */
	
	public void checkinBaggage(Baggage newBaggage)
	{
		this.baggageList.add(newBaggage);
	}
	
	public boolean checkoutBaggage(Baggage checkoutBaggage)
	{
		return this.baggageList.remove(checkoutBaggage);
	}
	
	public List<Baggage> getBaggagelist()
	{
		return baggageList ;
	}
	
	/*
	 * 
	 * 
	 * Add , Delete and Get operation on BaggageNode List
	 */
	
	public void addNode(BaggageNode node)
	{
		this.nodeMap.put(node.getName(),node);
		//this.nodelist.add(node);
	}
	
	public BaggageNode removeNode(BaggageNode deleteNode)
	{
		return	this.nodeMap.remove(deleteNode.getName());
		//return nodelist.remove(deleteNode);
	}

	public Map<String, BaggageNode> getNodeMap() {
		return nodeMap;
	}
	
	public void resetMinimumDistance()
	{
		for (String k : nodeMap.keySet())
		{
			BaggageNode node =nodeMap.get(k);
			node.setMinDistance(Integer.MAX_VALUE);
			node.setPrevNode(null);
			nodeMap.put(k, node);
		}		
		
	}
		

}
