package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetOrdersByType implements IQueryDefinition
{
    @Override
    public void create(IQueryBuilder aBuilder, IQueryDefinitionContext aContext) {
     aBuilder.addSearchField("RefBODefinitionUserDefined","BoType");
     aBuilder.addSelectField("Syscode");   
    }
   
    @Override
    public String getBOName() {
        return "BaseOrder";
    }


}
