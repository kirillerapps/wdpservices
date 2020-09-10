package com.planonsoftware.tms.wdp.customerportal.services.get;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.planonsoftware.jaxrs.api.v9.context.IJaxRsResourceContext;

import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service.OrderService;

import javax.ws.rs.core.MediaType;



@Path("/orders")
public class OrderRestService
{

    @Context 
    IJaxRsResourceContext jaxrsContext;

    @GET
    @Consumes("application/json")
    @Path("/getOrder")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOrderDetails(@QueryParam("orderCode") String orderCode) {
      
        System.out.println("Planon getOrders method execution started. orderCode : " + orderCode);            
        
        IDataService dataService = jaxrsContext.getDataService();
        OrderService service = new OrderService();
        OrderDTO order = null;
        Response response = null;

		try {
			order = service.getOrderByCode(dataService, orderCode);
		} catch (Exception e) {		
			order.setErrorMessage(e.getMessage());
		}

        if(order == null) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {            
            response = Response.ok().entity(order).build();
        }          
 
        System.out.println("Planon getOrder method execution ended.");
        return response;
    }
}
