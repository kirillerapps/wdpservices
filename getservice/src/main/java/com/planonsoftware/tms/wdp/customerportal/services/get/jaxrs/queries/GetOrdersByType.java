package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;
import com.planonsoftware.platform.backend.querybuilder.v3.IJoin;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetOrdersByType implements IQueryDefinition
{
    @Override
    public void create(IQueryBuilder aBuilder, IQueryDefinitionContext aContext) {
        //Search fields
        aBuilder.addSearchField("RefBODefinitionUserDefined","boType");
        aBuilder.addSearchField("Comment","searchDescription");
        aBuilder.addSearchField("SysMutationDateTime","searchMutationDateFrom");
        aBuilder.addSearchField("SysMutationDateTime","searchMutationDateTo");

        //Search field get request by code
        aBuilder.addSearchField("OrderNumber", "OrderNumberSearch");
        
        IJoin boStatusSearch = aBuilder.addInnerJoin("RefBOStateUserDefined");
        boStatusSearch.addSearchField("Code","statusCodeSearch");

        IJoin boPersonIdSearch = aBuilder.addInnerJoin("InternalRequestorPersonRef");
        boPersonIdSearch.addSearchField("Code","personCodeSearch");

       
        //Select fields
        IJoin boStatus = aBuilder.addLeftOuterJoin("RefBOStateUserDefined");        
		boStatus.addSelectField("Name", "statusSystemName");
        boStatus.addSelectField("Code", "statusCode");        

        IJoin boRentableUnit = aBuilder.addLeftOuterJoin("RentableUnitRef");
		boRentableUnit.addSelectField("Code", "rentCode");
        boRentableUnit.addSelectField("Name", "rentName");

        IJoin boInternalCoord = aBuilder.addLeftOuterJoin("InternalCoordinatorPersonRef");
		boInternalCoord.addSelectField("Code", "intCoordCode");
        boInternalCoord.addSelectField("FirstName", "intCoordFirstName");
        boInternalCoord.addSelectField("LastName", "intCoordLastName");

        IJoin boProperty = aBuilder.addLeftOuterJoin("PropertyRef");
		boProperty.addSelectField("Code", "propCode");
        boProperty.addSelectField("Name", "propName");

        IJoin boStandardOrder = aBuilder.addLeftOuterJoin("StandardOrderRef");
        boStandardOrder.addSelectField("Code","StandardOrderRefCode");

        aBuilder.addSelectField("Syscode"); 
        aBuilder.addSelectField("Comment", "orderComment");
        aBuilder.addSelectField("FreeString10", "orderFreeString10");
        aBuilder.addSelectField("FreeString19", "orderFreeString19");
        aBuilder.addSelectField("FreeString3", "orderFreeString3");
        aBuilder.addSelectField("FreeString1", "orderFreeString1");
        aBuilder.addSelectField("HighPriority", "orderHighPriority");
        aBuilder.addSelectField("FreeString30", "orderFreeString30");
        aBuilder.addSelectField("AppointmentBooking", "orderAppointmentBooking");
        aBuilder.addSelectField("InsertDateTime", "orderInsertDateTime");
        aBuilder.addSelectField("RequestedUserEndDateTime", "orderRequestedUserEndDateTime");
        aBuilder.addSelectField("OrderNumber", "OrderNumber");
    }
   
    @Override
    public String getBOName() {
        return "BaseOrder";
    }


}
