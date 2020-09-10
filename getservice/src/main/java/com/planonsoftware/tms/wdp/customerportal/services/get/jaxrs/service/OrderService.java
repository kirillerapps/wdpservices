package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service;

import com.planonsoftware.platform.data.v1.ActionNotFoundException;
import com.planonsoftware.platform.data.v1.BusinessException;
import com.planonsoftware.platform.data.v1.FieldNotFoundException;
import com.planonsoftware.platform.data.v1.IAction;
import com.planonsoftware.platform.data.v1.IActionListManager;
import com.planonsoftware.platform.data.v1.IBusinessObject;
import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.platform.data.v1.IDatabaseQuery;
import com.planonsoftware.platform.data.v1.IResultSet;
import com.planonsoftware.platform.data.v1.Operator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDTO;



public class OrderService
{

    public OrderDTO getOrderByCode(final IDataService dataService, final String orderCode) throws BusinessException, FieldNotFoundException, ActionNotFoundException {
        System.out.println("OrderService: getOrderByCode "+ orderCode);        
        IDatabaseQuery query = dataService.getPVDatabaseQuery("GetOrderBOByCode");
        query.getStringSearchExpression("OrderNumber", Operator.EQUAL).addValue(orderCode);

        IResultSet resultset = query.execute();
        System.out.println("Query Executed successfully...");
        OrderDTO orderForResponse = new OrderDTO();

        if(resultset.first())
        {          
        IActionListManager actionListManager = dataService.getActionListManager("BaseOrder"); 
        IAction action = actionListManager.getReadAction(resultset.getPrimaryKey());
        IBusinessObject orderBo = action.execute();
        orderForResponse.setCode(orderBo.getField("OrderNumber").getValue().toString());
        orderForResponse.setSyscode(orderBo.getPrimaryKey());
        orderForResponse.setName(orderBo.getStringField("Comment").getValue());
        }
        return orderForResponse;
    }

}
