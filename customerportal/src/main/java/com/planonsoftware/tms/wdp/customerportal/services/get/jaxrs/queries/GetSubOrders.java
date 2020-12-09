package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;

import com.planonsoftware.platform.backend.querybuilder.v3.IJoin;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetSubOrders implements IQueryDefinition
{

@Override
    public void create(IQueryBuilder aBuilder, IQueryDefinitionContext aContext) {   


        IJoin parentOrderJoin = aBuilder.addInnerJoin("ParentOrderRef");
        parentOrderJoin.addSearchField("RefBODefinitionUserDefined","boType");
        parentOrderJoin.addSelectField("OrderNumber", "parentOrderNumberSelect");          
        

        aBuilder.addSearchField("SysMutationDateTime","searchMutationDateFrom");
        aBuilder.addSearchField("SysMutationDateTime","searchMutationDateTo");
        aBuilder.addSelectField("SysMutationDateTime","sysMutationDateTimeSelect");

        //Modified and not modified searchfields
        IJoin modifiedByInnerjoin = aBuilder.addInnerJoin("SysChangeAccountRef");
        modifiedByInnerjoin.addSearchField("Accountname","modifiedByAccountName");

        IJoin notModifiedByInnerjoin = aBuilder.addInnerJoin("SysChangeAccountRef");
        notModifiedByInnerjoin.addSearchField("Accountname","notModifiedByAccountName");

        //IJoin select fields
        IJoin boInternalCoord = aBuilder.addLeftOuterJoin("InternalCoordinatorPersonRef");
		boInternalCoord.addSelectField("Code", "intCoordCode");
        boInternalCoord.addSelectField("FirstName", "intCoordFirstName");
        boInternalCoord.addSelectField("LastName", "intCoordLastName");

        IJoin boInternalRequestorPerson = aBuilder.addLeftOuterJoin("InternalRequestorPersonRef");
        boInternalRequestorPerson.addSelectField("Code","reportedByCode");
        boInternalRequestorPerson.addSelectField("FirstName","reportedByFirstName");
        boInternalRequestorPerson.addSelectField("LastName","reportedByLastName");
        boInternalRequestorPerson.addSelectField("Email","reportedByEmail");
        boInternalRequestorPerson.addLeftOuterJoin("AddressRef").addSelectField("Code","reportedByAdressCode");

        IJoin boProperty = aBuilder.addLeftOuterJoin("PropertyRef");
		boProperty.addSelectField("Code", "propCode");
        boProperty.addSelectField("Name", "propName");

        IJoin boExternalTradesman = aBuilder.addLeftOuterJoin("ExternalTradesmanAddressRef");
		boExternalTradesman.addSelectField("Code", "externalTradesmanCode");
        boExternalTradesman.addSelectField("Name", "externalTradesmanName");

        IJoin boOrderGroup = aBuilder.addLeftOuterJoin("OrderGroupRef");
		boOrderGroup.addSelectField("Code", "orderGroupCode");
        boOrderGroup.addSelectField("Name", "orderGroupName");

        IJoin boStatus = aBuilder.addLeftOuterJoin("RefBOStateUserDefined");        
		boStatus.addSelectField("Name", "statusSystemName");
        boStatus.addSelectField("Code", "statusCode");

           IJoin boRentableUnit= aBuilder.addLeftOuterJoin("RentableUnitRef");
        boRentableUnit.addSelectField("Code", "poNumberCode");   

        IJoin inventoryItem = aBuilder.addLeftOuterJoin("InventoryItemRef");
        inventoryItem.addSelectField("Code", "invItemCode");  
        inventoryItem.addSelectField("Name", "invItemName");
     
        //Select fields
        aBuilder.addSelectField("Description", "orderComment");
        aBuilder.addSelectField("Comment", "communicationReader");
        aBuilder.addSelectField("CommentString", "communicationOwner");
        aBuilder.addSelectField("RequestedUserEndDateTime", "orderRequestedUserEndDateTime");
        aBuilder.addSelectField("OrderNumber", "orderNumber");
        aBuilder.addSelectField("InsertDateTime", "orderInsertDateTime");       
        
    }
   
    @Override
    public String getBOName() {
        return "WorkOrder";
    }

    
}
