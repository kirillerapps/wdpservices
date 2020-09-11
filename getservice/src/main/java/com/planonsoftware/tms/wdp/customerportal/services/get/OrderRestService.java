package com.planonsoftware.tms.wdp.customerportal.services.get;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.planonsoftware.jaxrs.api.v9.context.IJaxRsResourceContext;

import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDetailsDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrdersResponseDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WSErrorResponse;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service.OrderService;

import javax.ws.rs.core.MediaType;



@Path("/orders")
public class OrderRestService
{    

    @Context 
    IJaxRsResourceContext jaxrsContext;


    @GET
    @Consumes("application/json")
    @Path("")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOrdersOverView() {
        System.out.println("Planon getOrders method execution started"); 
        WSErrorResponse errorResponse = new WSErrorResponse();
        Response response = null;
        try{
        IDataService dataService = jaxrsContext.getDataService();
        OrderService service = new OrderService();
        OrdersResponseDTO orderResponseList = new OrdersResponseDTO();       
       
        orderResponseList = service.getOrders(dataService,orderResponseList);
        response = Response.ok().entity(orderResponseList).build();
        }catch (Exception e) 
        {
            errorResponse.setErrorMessage(e.getMessage());
            errorResponse.setErrorCode(500);
            response = Response.status(500).entity(errorResponse)
                           .build();
        }
 
        System.out.println("Planon orderList method execution ended.");
        return response;
    }



    @GET
    @Consumes("application/json")
    @Path("{orderCode}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOrderDetails(@PathParam("orderCode") String orderCode) {
        System.out.println("Planon getOrders method execution started. orderCode : " + orderCode); 
        IDataService dataService = jaxrsContext.getDataService();
        OrderService service = new OrderService();
        WSErrorResponse errorResponse = new WSErrorResponse();
        OrderDetailsDTO order = null;
        Response response = null;
		try {

            order = service.getOrderByCode(dataService, orderCode);
            response = Response.ok().entity(order).build();
		} catch (Exception e) {		
			errorResponse.setErrorMessage(e.getMessage());
            errorResponse.setErrorCode(500);
            response = Response.status(500).entity(errorResponse)
                           .build();
		}
        System.out.println("Planon getOrder method execution ended.");
        return response;
    }


 
}
