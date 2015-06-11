package com.Denver.Airport;


import com.Denver.Airport.Conveyor.BaggageNode;

public class Departures {
	
	private String flightId ;
	
	private BaggageNode flightGate ;
	
	private String destination ;
	
	private String timimg;

	public Departures(String flightId, BaggageNode flightGate,
			String destination, String timimg) {		
		this.flightId = flightId;
		this.flightGate = flightGate;
		this.destination = destination;
		this.timimg = timimg;
	}

	public String getFlightId() {
		return flightId;
	}

	public BaggageNode getFlightGate() {
		return flightGate;
	}

	public String getDestination() {
		return destination;
	}

	public String getTimimg() {
		return timimg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flightId == null) ? 0 : flightId.hashCode());
		return result;
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
		Departures other = (Departures) obj;
		if (flightId == null) {
			if (other.flightId != null)
				return false;
		} else if (!flightId.equals(other.flightId))
			return false;
		return true;
	}
	
	

}
