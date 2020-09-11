package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;


import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetUserDefinedTypePrimaryKey implements IQueryDefinition
{
    @Override
    public void create(IQueryBuilder aBuilder, IQueryDefinitionContext aContext) {
    
    aBuilder.addSearchField("PnName", "BOType");

    }
   
    @Override
    public String getBOName() {
        return "UserBODefinition";
    }


}
