package com.planonsoftware.tms.wdp.customerportal.services.get;

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
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.FilterDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDetailsDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrdersResponseDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses.IResource;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses.IServiceResponse;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses.ServiceResponse;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service.OrderService;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.CommonUtils;

import javax.ws.rs.core.MediaType;

@Path("/orderservice")
public class OrderRestService implements IResource {
    @Context
    IJaxRsResourceContext jaxrsContext;

    @GET
    @Consumes("application/json")
    @Path("requests")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOrdersOverView(@QueryParam("description") String description, @QueryParam("state") String state,
            @QueryParam("reportedById") String reportedById, @QueryParam("modifiedDateFrom") String modifiedDateFrom,
            @QueryParam("modifiedDateTo") String modifiedDateTo) {
        System.out.println("Planon getOrders method execution started");
        try {
            FilterDTO filterObject = fillFilterObject(description, state, reportedById, modifiedDateFrom,
                    modifiedDateTo);
            IDataService dataService = jaxrsContext.getDataService();
            OrderService service = new OrderService();
            OrdersResponseDTO orderResponse = new OrdersResponseDTO();
            orderResponse = service.getOrders(dataService, orderResponse, filterObject);

            OrdersResponseDTO orderResponseList = new OrdersResponseDTO();
            orderResponseList = service.getOrders(dataService, orderResponseList, filterObject);
            IServiceResponse<List<OrderOverViewDTO>> response = new ServiceResponse<>(orderResponseList.getOrders());
            return constructResponse(response);

        } catch (Exception e) {
            return constructErrorResponse(e);
        }
    }

    @GET
    @Consumes("application/json")
    @Path("requests/{requestId}")
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

    private FilterDTO fillFilterObject(String description, String state, String reportedById, String modifiedDateFrom,
            String modifiedDateTo) {
        FilterDTO filterObject = new FilterDTO();
        filterObject.setDescription(CommonUtils.blankIfNull(description));
        filterObject.setModifiedDateFrom(CommonUtils.blankIfNull(modifiedDateFrom));
        filterObject.setModifiedDateTo(CommonUtils.blankIfNull(modifiedDateTo));
        filterObject.setReportedById(CommonUtils.blankIfNull(reportedById));
        filterObject.setState(CommonUtils.blankIfNull(state));
        return filterObject;
    }
}
