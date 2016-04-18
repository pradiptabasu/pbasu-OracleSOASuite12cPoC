package com.pradipta.poc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("com/pradipta/poc/rest")
@Produces("application/xml")
public class HelloWorld {
    public HelloWorld() {
        super();
    }

    @GET
    @Path("{name}")
    public String HelloHuman(@PathParam("name") String name) {
        return "Hello "+name;
    }    
}
