package com.planonsoftware.tms.wdp.customerportal.services.get;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.ComlogDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.FilterDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDetailsDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses.IResource;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses.IServiceResponse;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses.ServiceResponse;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service.ComlogService;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service.OrderService;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.CommonUtils;

import javax.ws.rs.core.MediaType;

@Path("/v1.0.0")
public class OrderRestService implements IResource {
    @Context
    IJaxRsResourceContext jaxrsContext;

    @GET
    @Consumes("application/json")
    @Path("orderservice/requests")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOrdersOverView(@QueryParam("modifiedBy") String modifiedBy,
            @QueryParam("notModifiedBy") String notModifiedBy, @QueryParam("modifiedDateFrom") String modifiedDateFrom,
            @QueryParam("modifiedDateTo") String modifiedDateTo) {
        System.out.println("Planon getOrders method execution started");
        FilterDTO filterObject;
        try {
            filterObject = fillFilterObject(notModifiedBy, modifiedBy, modifiedDateFrom, modifiedDateTo);
        } catch (Exception e) {
            return constructErrorResponse(e.getMessage(), 400);
        }

        try {
            filterObject = fillFilterObject(notModifiedBy, modifiedBy, modifiedDateFrom, modifiedDateTo);
            IDataService dataService = jaxrsContext.getDataService();
            OrderService service = new OrderService();
            List<OrderOverViewDTO> orderList = new ArrayList<>();
            orderList = service.getOrders(dataService, orderList, filterObject);
            IServiceResponse<List<OrderOverViewDTO>> response = new ServiceResponse<>(orderList);
            return constructResponse(response);
        } catch (Exception e) {
            return constructErrorResponse(e);
        }
    }

    @GET
    @Consumes("application/json")
    @Path("orderservice/requests/{requestId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOrderDetails(@PathParam("requestId") String requestId) {
        System.out.println("Planon getOrders method execution started. requestId : " + requestId);
        try {
            IDataService dataService = jaxrsContext.getDataService();
            OrderService service = new OrderService();
            OrderDetailsDTO orderResponse = new OrderDetailsDTO();
            orderResponse = service.getOrderByCode(dataService, requestId);
            IServiceResponse<OrderDetailsDTO> response = new ServiceResponse<>(orderResponse);
            return constructResponse(response);
        } catch (Exception e) {
            return constructErrorResponse(e);
        }
    }

    // @GET
    // @Consumes("application/json")
    // @Path("orderservice/workorders/{requestId}")
    // @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    // public Response getSubOrdersFromOrder(@PathParam("requestId") String requestId) {
    //     System.out.println("Planon get workorders (suborders) method execution started. requestId : " + requestId);
    //     try {
    //         IDataService dataService = jaxrsContext.getDataService();
    //         OrderService service = new OrderService();
    //         List<WorkOrdersDTO> subOrderResponseList = service.getWorkOrdersByRequestId(dataService, requestId);
    //         IServiceResponse<List<WorkOrdersDTO>> response = new ServiceResponse<>(subOrderResponseList);
    //         return constructResponse(response);
    //     } catch (Exception e) {
    //         return constructErrorResponse(e);
    //     }
    // }

    
    @GET
    @Consumes("application/json")
    @Path("orderservice/workorders")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getSubOrdersFromOrderWithoutId(@QueryParam("modifiedBy") String modifiedBy,
            @QueryParam("notModifiedBy") String notModifiedBy, @QueryParam("modifiedDateFrom") String modifiedDateFrom,
            @QueryParam("modifiedDateTo") String modifiedDateTo) {
        System.out.println("Planon get workorders (suborders) method execution started.");
         FilterDTO filterObject;
        try {
            filterObject = fillFilterObject(notModifiedBy, modifiedBy, modifiedDateFrom, modifiedDateTo);
        } catch (Exception e) {
            return constructErrorResponse(e.getMessage(), 400);
        }

        try {
            IDataService dataService = jaxrsContext.getDataService();
            OrderService service = new OrderService();
            List<WorkOrdersDTO> subOrderResponseList = service.getSubOrders(dataService, filterObject);
            IServiceResponse<List<WorkOrdersDTO>> response = new ServiceResponse<>(subOrderResponseList);
            return constructResponse(response);
        } catch (Exception e) {
            return constructErrorResponse(e);
        }
    }

    @GET
    @Consumes("application/json")
    @Path("comlogservice/comlogs")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getCommunicationLogs(@QueryParam("modifiedBy") String modifiedBy, @QueryParam("notModifiedBy") String notModifiedBy,
            @QueryParam("modifiedDateFrom") String modifiedDateFrom,
            @QueryParam("modifiedDateTo") String modifiedDateTo) {
        System.out.println("Planon get communicationlogs method execution started.");
        FilterDTO filterObject;
        try {
            filterObject = fillFilterObject(notModifiedBy, modifiedBy, modifiedDateFrom, modifiedDateTo);
        } catch (Exception e) {
            return constructErrorResponse(e.getMessage(), 400);
        }
        try {
            IDataService dataService = jaxrsContext.getDataService();
            ComlogService service = new ComlogService();
            List<ComlogDTO> communicationLogList = service.getCommunicationLogs(dataService, filterObject);
            IServiceResponse<List<ComlogDTO>> response = new ServiceResponse<>(communicationLogList);
            return constructResponse(response);
        } catch (Exception e) {
            return constructErrorResponse(e);
        }
    }

    private FilterDTO fillFilterObject(String notModifiedBy, String modifiedBy, String modifiedDateFrom,
            String modifiedDateTo) throws Exception {
        FilterDTO filterObject = new FilterDTO();
        if (modifiedDateFrom != null && !modifiedDateFrom.isBlank()) {
            filterObject.setModifiedDateFrom(validateDateTimeParam(modifiedDateFrom, "yyyy-MM-dd'T'HH:mm:ss"));
        }
        if (modifiedDateTo != null && !modifiedDateTo.isBlank()) {
            filterObject.setModifiedDateTo(validateDateTimeParam(modifiedDateTo, "yyyy-MM-dd'T'HH:mm:ss"));
        }

           if (notModifiedBy != null && !notModifiedBy.isBlank()) {
            filterObject.setNotModifiedBy(notModifiedBy);
        }
        if (modifiedBy != null && !modifiedBy.isBlank()) {
           filterObject.setModifiedBy(modifiedBy);
        }
        return filterObject;
    }

    private Date validateDateTimeParam(String modifiedDate, String dateFormat) throws Exception {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            Date formattedDate = formatter.parse(modifiedDate);
            return formattedDate;
        } catch (ParseException e) {
            throw new Exception("Not a valid datetime format expected yyyy-MM-dd'T'HH:mm:ss received: " + modifiedDate);
        }

    }

}
