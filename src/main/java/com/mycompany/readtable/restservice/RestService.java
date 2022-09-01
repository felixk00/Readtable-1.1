/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.readtable.restservice;

import java.util.Date;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

/**
 *
 * @author Felix
 */
@Path("/RestService")
@ApplicationPath("restservice")
public class RestService extends Application {
    
    // http://localhost:8080/Readtable-1.1/restservice/RestService/sayHello
    @GET
    @Path("/sayHello")
    public Date getHelloMsg (){
        Date dateValue = new Date();
	
        
        
        return dateValue;
    }
    
    
}
