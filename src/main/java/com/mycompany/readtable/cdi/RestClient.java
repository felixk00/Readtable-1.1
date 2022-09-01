package com.mycompany.readtable.cdi;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Felix
 */

@Named
@SessionScoped
public class RestClient implements Serializable {

    private static final Logger logger = LogManager.getLogger(RestClient.class);

    
    
    
   
    public RestClient() {
      
    }

   public String call (){
        Client client = ClientBuilder.newClient();
        
       Date date = client.target("http://localhost:8080/Readtable-1.1/restservice/RestService/sayHello").request().get(Date.class);
        logger.info("hier ist die call Mehtode  ");
        
        String s = date.toString();
        
        return s;
        
    }
    
    
}
