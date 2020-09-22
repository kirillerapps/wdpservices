package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.platform.data.v1.IDatabaseQuery;
import com.planonsoftware.platform.data.v1.IResultSet;
import com.planonsoftware.platform.data.v1.Operator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.FilterDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDetailsDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrdersResponseDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO.InternalCoordinator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO.RentableUnit;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO.State;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.CommonUtils;

public class OrderService {

    private Date formattedDate;

    public OrderDetailsDTO getOrderByCode(final IDataService dataService, final String orderCode) throws Exception {
        System.out.println("OrderService: getOrderByCode " + orderCode);
            // Get primary keys for IN function
        int foundBoTypeUsrRequest = getboTypeQuery(dataService, "UsrRequest");
        int foundBoTypeUsrComplaint = getboTypeQuery(dataService, "UsrOrder");
        // Finally we can do query
        IDatabaseQuery queryOrders = dataService.getPVDatabaseQuery("GetOrdersByType");
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrRequest);
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrComplaint);
        //Get query by code/requestId
        queryOrders.getSearchExpression("OrderNumberSearch", Operator.EQUAL).setValue(orderCode);
        IResultSet resultset = queryOrders.execute();
        System.out.println("Query Executed successfully...");
       
        OrderOverViewDTO orderOverViewDTO = new OrderDetailsDTO();
          
        if (resultset.first()) {            
            //Fill the parent class
              orderOverViewDTO = fillOrderBoFields(resultset, orderOverViewDTO);
              //convert class to child class              
              OrderDetailsDTO orderDetails = (OrderDetailsDTO) orderOverViewDTO;
              //orderDetails = fillOrderDetails(orderDetails,resultset);
              return orderDetails;
        }
        return null;
    }

    private OrderDetailsDTO fillOrderDetails(OrderDetailsDTO orderDetails, IResultSet resultset) {
		return null;
	}

	public OrdersResponseDTO getOrders(final IDataService dataService, OrdersResponseDTO orderResponseList,
            FilterDTO filterObject) throws Exception {
        // Get primary keys for IN function
        int foundBoTypeUsrRequest = getboTypeQuery(dataService, "UsrRequest");
        int foundBoTypeUsrComplaint = getboTypeQuery(dataService, "UsrOrder");
        // Finally we can do query
        IDatabaseQuery queryOrders = dataService.getPVDatabaseQuery("GetOrdersByType");
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrRequest);
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrComplaint);

        // Query filter description
        if (filterObject.getDescription() != null) {
            queryOrders.getSearchExpression("searchDescription", Operator.CONTAINS)
                    .setValue(filterObject.getDescription());
        }
        // Query filter state
        if (filterObject.getState() != null) {
            queryOrders.getSearchExpression("statusCodeSearch", Operator.EQUAL).setValue(filterObject.getState());
        }

        // Query filter state
        if (filterObject.getReportedById() != null) {
            queryOrders.getSearchExpression("personCodeSearch", Operator.EQUAL)
                    .setValue(filterObject.getReportedById());
        }

        // Query filter mutation date from
        if (!filterObject.getModifiedDateFrom().isBlank() && checkIfValidDateTime(filterObject.getModifiedDateFrom())) {
            queryOrders.getDateTimeSearchExpression("searchMutationDateFrom", Operator.GREATER_EQUAL)
                    .setValue(formattedDate);
        }
        // Query filter mutation date to
        if (!filterObject.getModifiedDateTo().isBlank() && checkIfValidDateTime(filterObject.getModifiedDateTo())) {
            queryOrders.getDateTimeSearchExpression("searchMutationDateTo", Operator.LESS_EQUAL)
                    .setValue(formattedDate);
            
        }

        IResultSet resultsetNew = queryOrders.execute();
        System.out.println("Query Executed successfully...");
        List<OrderOverViewDTO> orderList = orderResponseList.getOrders();
        while (resultsetNew.next()) {
            OrderOverViewDTO orderOverViewDTO = new OrderOverViewDTO();
            orderOverViewDTO = fillOrderBoFields(resultsetNew, orderOverViewDTO);
            orderList.add(orderOverViewDTO);
        }
        orderResponseList.setRealtimeDataResponse(orderList);
        return orderResponseList;
    }

    private boolean checkIfValidDateTime(String modifiedDate) throws Exception {
        try {
            formattedDate = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formattedDate = formatter.parse(modifiedDate);
            return true;
        } catch (ParseException dtpe) {
            throw new Exception("Not a valid datetime format expected yyyy-MM-dd HH:mm:ss received: " + modifiedDate);
        }
    }

    private OrderOverViewDTO fillOrderBoFields(IResultSet resultsetNew, OrderOverViewDTO orderOverViewDTO)
            throws Exception {
        orderOverViewDTO.setStandardOrderId(CommonUtils.blankIfNull(resultsetNew.getString("StandardOrderRefCode")));
        orderOverViewDTO.setDescription(CommonUtils.blankIfNull(resultsetNew.getString("orderComment")));
        orderOverViewDTO.setPropertyId(CommonUtils.blankIfNull(resultsetNew.getString("propCode")));
        orderOverViewDTO.setReportedById(CommonUtils.blankIfNull(resultsetNew.getString("intCoordCode")));
        orderOverViewDTO.setUploadAttachmentYN(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString10")));
        orderOverViewDTO.setContactName(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString19")));
        orderOverViewDTO.setContactPhone(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString3")));
        orderOverViewDTO.setHighPriority(resultsetNew.getBoolean("orderHighPriority"));
        orderOverViewDTO.setExplanationPriority(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString30")));
        orderOverViewDTO.setDescriptionCause(CommonUtils.blankIfNull(resultsetNew.getString("orderComment")));
        orderOverViewDTO.setWhereIsTheProblem(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString1")));
        orderOverViewDTO.setAppointmentBooking(resultsetNew.getBoolean("orderAppointmentBooking"));
        orderOverViewDTO.setRequestId(CommonUtils.blankIfNull(resultsetNew.getString("OrderNumber")));
        orderOverViewDTO.setReportedOn(CommonUtils.blankIfNull(resultsetNew.getDateTime("orderInsertDateTime").toInstant().toString()));
        if (resultsetNew.getDateTime("orderRequestedUserEndDateTime") != null) {
            orderOverViewDTO.setRequestedCompleteOn(
                    resultsetNew.getDateTime("orderRequestedUserEndDateTime").toInstant().toString());
        }else{
            orderOverViewDTO.setRequestedCompleteOn("");
        }

        // Set state
        State state = orderOverViewDTO.getState();
        state.setId(resultsetNew.getString("statusCode"));
        state.setName(resultsetNew.getString("statusSystemName"));
        orderOverViewDTO.setState(state);

        // Set rentableUnit
        RentableUnit rentableUnit = orderOverViewDTO.getRentableUnit();
        rentableUnit.setId(resultsetNew.getString("rentCode"));
        rentableUnit.setName(resultsetNew.getString("rentName"));
        orderOverViewDTO.setRentableUnit(rentableUnit);

        // Set Internal coord
        InternalCoordinator iCoordinator = orderOverViewDTO.getInternalCoordinator();
        iCoordinator.setId(resultsetNew.getString("intCoordCode"));
        iCoordinator.setFirstName(resultsetNew.getString("intCoordFirstName"));
        iCoordinator.setLastName(resultsetNew.getString("intCoordLastName"));
        orderOverViewDTO.setInternalCoordinator(iCoordinator);

        return orderOverViewDTO;
    }

    private int getboTypeQuery(IDataService dataService, String boTypeName) throws Exception {
        IDatabaseQuery boQueryTypePrimaryKey = dataService.getPVDatabaseQuery("GetUserDefinedTypePrimaryKey");
        boQueryTypePrimaryKey.getStringSearchExpression("BOType", Operator.EQUAL).addValue(boTypeName);
        IResultSet foundBoType = boQueryTypePrimaryKey.execute();
        if (foundBoType.first()) {
            return foundBoType.getPrimaryKey();
        }
        return 0;
    } 

}
