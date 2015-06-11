package com.Denver.Airport.UnitTests;

import static org.junit.Assert.*;
import java.util.Arrays;
import com.Denver.Airport.Conveyor.*;
import org.junit.Test;
import com.Denver.Airport.Passenger.*;

public class BaggageTest {

	BaggageNode v1=new BaggageNode("A1");
	BaggageNode v2=new BaggageNode("A2");
	BaggageNode v3=new BaggageNode("A3");
	BaggageNode v4=new BaggageNode("A4");
	BaggageNode v5=new BaggageNode("A5");
	BaggageNode v6=new BaggageNode("A6");
	BaggageNode v7=new BaggageNode("A7");
	BaggageNode v8=new BaggageNode("A8");
	BaggageNode v9=new BaggageNode("A9");
	BaggageNode v10=new BaggageNode("A10");
	BaggageNode Concourse_A_Ticketing=new BaggageNode("Concourse_A_Ticketing");
	BaggageNode baggageClaim= new BaggageNode("BaggageClaim");
	Baggage bag =new Baggage("Test", Concourse_A_Ticketing, "Test_Flght Name");

	@Test
	public void testComputePaths() {
		v1.addAdjencies(Arrays.asList(new ConveyorPath(1, v2),new ConveyorPath(6, v5)));
		v2.addAdjencies(Arrays.asList(new ConveyorPath(1, v1),new ConveyorPath(1, v3)));
		v3.addAdjencies(Arrays.asList(new ConveyorPath(1, v2),new ConveyorPath(1, v4)));
		v4.addAdjencies(Arrays.asList(new ConveyorPath(1, v3)));
		v5.addAdjencies(Arrays.asList(new ConveyorPath(4, v10),new ConveyorPath(6, v1),new ConveyorPath(5, Concourse_A_Ticketing),new ConveyorPath(5, baggageClaim)));
		v6.addAdjencies(Arrays.asList(new ConveyorPath(1, v7)));
		v7.addAdjencies(Arrays.asList(new ConveyorPath(1, v8),new ConveyorPath(1, v6)));
		v8.addAdjencies(Arrays.asList(new ConveyorPath(1, v9),new ConveyorPath(1, v7)));
		v9.addAdjencies(Arrays.asList(new ConveyorPath(1, v10),new ConveyorPath(1, v8)));
		v10.addAdjencies(Arrays.asList(new ConveyorPath(1, v9),new ConveyorPath(4, v5)));
		Concourse_A_Ticketing.addAdjencies(Arrays.asList(new ConveyorPath(5, v5)));
		baggageClaim.addAdjencies(Arrays.asList(new ConveyorPath(5, v5)));		
		bag.setBoardingPoint(v1);		
		bag.computePaths();		
	}

	@Test
	public void testPrintPath() {
		testComputePaths();
		System.out.print(Concourse_A_Ticketing.getName()+" ");
		bag.printPath(Concourse_A_Ticketing, v1);
		System.out.println(":"+v1.getMinDistance());
	}

}
