package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.queries;

import com.planonsoftware.platform.backend.querybuilder.v3.IJoin;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryBuilder;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinition;
import com.planonsoftware.platform.backend.querybuilder.v3.IQueryDefinitionContext;

public class GetCommunicationLogs implements IQueryDefinition {

    @Override
    public void create(IQueryBuilder aBuilder, IQueryDefinitionContext aContext) {     
        IJoin BoRef = aBuilder.addInnerJoin("BORef");
        BoRef.addSelectField("OrderNumber","OrderNumber");
        IJoin commLogJoin = aBuilder.addInnerJoin("CommunicationLogRef");
        commLogJoin.addSearchField("SysChangeDateTime", "comlogSearchMutationDateFrom");
        commLogJoin.addSearchField("SysChangeDateTime", "comlogSearchMutationDateTo");
        // Modified and not modified searchfields
        IJoin modifiedByInnerjoin = commLogJoin.addLeftOuterJoin("SysChangeAccountRef");
        modifiedByInnerjoin.addSearchField("Accountname", "comlogModifiedByAccountName");
        modifiedByInnerjoin.addSearchField("Accountname", "comlogNotModifiedByAccountName");
        modifiedByInnerjoin.addSelectField("Accountname", "comlogModifiedBy");

        commLogJoin.addSelectField("Syscode", "comlogSysCode");
        commLogJoin.addSelectField("Code", "comlogCode");
        commLogJoin.addSelectField("Name", "comlogName");    
        commLogJoin.addSelectField("DocumentReferral", "comlogDocumentReferral");
        commLogJoin.addSelectField("SysChangeDateTime", "comlogSysChangeDateTime");
        commLogJoin.addSelectField("SysInsertDateTime","comlogSysInsertDateTime");

    }

    @Override
    public String getBOName() {
        return "ComLogBaseOrder";
    }

}
