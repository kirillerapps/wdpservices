package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service;

import java.util.List;

import com.planonsoftware.platform.data.v1.IAction;
import com.planonsoftware.platform.data.v1.IActionListManager;
import com.planonsoftware.platform.data.v1.IBusinessObject;
import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.platform.data.v1.IDatabaseQuery;
import com.planonsoftware.platform.data.v1.IResultSet;
import com.planonsoftware.platform.data.v1.Operator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDetailsDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrdersResponseDTO;


public class OrderService
{

    public OrderDetailsDTO getOrderByCode(final IDataService dataService, final String orderCode) throws Exception {
        System.out.println("OrderService: getOrderByCode "+ orderCode);        
        IDatabaseQuery query = dataService.getPVDatabaseQuery("GetOrderBOByCode");
        query.getStringSearchExpression("OrderNumber", Operator.EQUAL).addValue(orderCode);

        IResultSet resultset = query.execute();
        System.out.println("Query Executed successfully...");
        OrderDetailsDTO orderForResponse = new OrderDetailsDTO();

        if(resultset.first())
        {          
        IActionListManager actionListManager = dataService.getActionListManager("BaseOrder"); 
        IAction action = actionListManager.getReadAction(resultset.getPrimaryKey());
        IBusinessObject orderBo = action.execute();   

        orderForResponse.setOrderNumber(orderBo.getField("OrderNumber").getValue().toString());
        orderForResponse.setDescription(orderBo.getField("Description").getValue().toString());
        orderForResponse.setProperty(orderBo.getReferenceField("PropertyRef").getDisplayValue());
        orderForResponse.setRequestedBy(orderBo.getReferenceField("InternalRequestorPersonRef").getDisplayValue());
        orderForResponse.setRequestedFor(orderBo.getReferenceField("InternalAssignedByPersonRef").getDisplayValue());


        }
        return orderForResponse;
    }

    
    public OrdersResponseDTO getOrders(final IDataService dataService, OrdersResponseDTO orderResponseList) throws Exception {
        //Ugly function because queries ain't working with innerjoins:  
        int foundBoTypeUsrRequest = getboTypeQuery(dataService,"UsrRequest");
        int foundBoTypeUsrComplaint = getboTypeQuery(dataService,"UsrOrder");

        //Finally we can do query
        IDatabaseQuery queryOrders = dataService.getPVDatabaseQuery("GetOrdersByType");
        queryOrders.getSearchExpression("BoType", Operator.IN).addValue(foundBoTypeUsrRequest);
        queryOrders.getSearchExpression("BoType", Operator.IN).addValue(foundBoTypeUsrComplaint);
        IResultSet resultsetNew = queryOrders.execute();
        System.out.println("Query Executed successfully...");     
        List<OrderOverViewDTO> orderList = orderResponseList.getOrders();         
        while(resultsetNew.next())
        {
            OrderOverViewDTO OrderOverViewDTO = new OrderOverViewDTO();
            IActionListManager actionListManager = dataService.getActionListManager("BaseOrder"); 
            IAction action = actionListManager.getReadAction(resultsetNew.getPrimaryKey());
            IBusinessObject orderBo = action.execute();

            OrderOverViewDTO.setCode(orderBo.getField("OrderNumber").getValue().toString());            
            OrderOverViewDTO.setDescription(orderBo.getStringField("Comment").getValue());
            OrderOverViewDTO.setReportedOn(orderBo.getDateTimeField("InsertDateTime").getValue().toString());
            OrderOverViewDTO.setStatus(orderBo.getReferenceField("RefBOStateUserDefined").getDisplayValue());
            orderList.add(OrderOverViewDTO);          

        }
        orderResponseList.setRealtimeDataResponse(orderList);         
        return orderResponseList;
    }

    private int getboTypeQuery(IDataService dataService, String boTypeName) throws Exception
    {        
        IDatabaseQuery boQueryTypePrimaryKey = dataService.getPVDatabaseQuery("GetUserDefinedTypePrimaryKey");
        boQueryTypePrimaryKey.getStringSearchExpression("BOType", Operator.EQUAL).addValue(boTypeName);
        IResultSet foundBoType = boQueryTypePrimaryKey.execute();
        if(foundBoType.first())
        {
            return foundBoType.getPrimaryKey();
           
         }
         return 0;

    }

}
