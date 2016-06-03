package com.atomikos.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atomikos.jaxrs.Flight.State;

@Component
@Path("/flight")
public class FlightController {
	
	@Autowired
	FlightRepository flightRepository;
	
    @GET
    @Path("/health")
    @Produces("application/json")
    public Health health() {
        return new Health("Jersey: Up and Running!");
    }
    
//    @GET
//    @Produces("application/json")
//    public Flight flight() {
//    	DateTime departure = new DateTime(2016, 8, 02, 12, 0, 0, 0);
//    	DateTime arrival = departure.plus(Period.hours(2));
//    	return new Flight(departure.toDate(), "Paris",arrival.toDate(),"New-York", State.BOOKED);
//    }
  
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response bookFlight(Flight flight) {
    	flight.setState(State.BOOKED);
    	flightRepository.save(flight);
    	return Response.status(Status.CREATED).entity(flight).build();
    }
    
    @PUT
    @Path("/{flightId}")
    @Produces("application/tcc")
    @Consumes("application/tcc" )
    public Response confirmFlight(@PathParam("flightId") Long flightId) {
    	Flight flight = flightRepository.findOne(flightId);
    	flight.setState(State.CONFIRMED);
    	flightRepository.save(flight);
    	return Response.status(Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/{flightId}")
    @Produces("application/json")
    public Response cancelFlight(Long flightId) {
    	Flight flight = flightRepository.findOne(flightId);
    	flight.setState(State.CANCELLED);
    	flightRepository.delete(flight.getId());
    	return Response.status(Status.ACCEPTED).build();
    }
}
