package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;

import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetOrderBOByCode implements IQueryDefinition
{

@Override
    public void create(IQueryBuilder order, IQueryDefinitionContext aContext) {         
       order.addSearchField("OrderNumber","OrderNumber");
       order.addSelectField("OrderNumber");   
    }
   
    @Override
    public String getBOName() {
        return "BaseOrder";
    }
    
}
