package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service;


import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.planonsoftware.platform.data.v1.IAction;
import com.planonsoftware.platform.data.v1.IActionListManager;
import com.planonsoftware.platform.data.v1.IBusinessObject;
import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.platform.data.v1.IDatabaseQuery;
import com.planonsoftware.platform.data.v1.IResultSet;
import com.planonsoftware.platform.data.v1.Operator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.ComlogDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.FilterDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.CommonUtils;

public class ComlogService {

    public List<ComlogDTO> getCommunicationLogs(IDataService dataService, FilterDTO filterObject)
            throws Exception {

        List<ComlogDTO> comlogList = new ArrayList<ComlogDTO>();
        // Finally we can do query   
        IDatabaseQuery queryOrders = dataService.getPVDatabaseQuery("GetCommunicationLogs");
             
        if (filterObject.getNotModifiedBy() != null) {
            queryOrders.getSearchExpression("comlogNotModifiedByAccountName", Operator.NOT_EQUAL)
                    .setValue(filterObject.getNotModifiedBy());
        }
      
        if (filterObject.getModifiedBy()!= null) {
            queryOrders.getSearchExpression("comlogModifiedByAccountName", Operator.EQUAL)
                    .setValue(filterObject.getModifiedBy());
        }
        // Query filter mutation date from
        if (filterObject.getModifiedDateFrom() != null) {
            queryOrders.getDateTimeSearchExpression("comlogSearchMutationDateFrom", Operator.GREATER)
                    .setValue(filterObject.getModifiedDateFrom());
        }
        // Query filter mutation date to
        if (filterObject.getModifiedDateTo() != null) {
            queryOrders.getDateTimeSearchExpression("comlogSearchMutationDateTo", Operator.LESS_EQUAL)
                    .setValue(filterObject.getModifiedDateTo());
        }
        IResultSet resultset = queryOrders.execute();
        System.out.println("Query Executed successfully...");

        while (resultset.next()) {
            System.out.println("Comlog found");
            ComlogDTO communicationLog = new ComlogDTO();
            communicationLog.setId(CommonUtils.blankIfNull(resultset.getString("comlogCode")));
            communicationLog.setRequestId(CommonUtils.blankIfNull(resultset.getString("OrderNumber")));
            communicationLog.setReportedOn(CommonUtils.returnFormatDateTimeFormatToString(
                    resultset.getDateTime("comlogSysInsertDateTime"), "yyyy-MM-dd'T'HH:mm:ss"));
            communicationLog.setModifiedOn(CommonUtils.returnFormatDateTimeFormatToString(
                    resultset.getDateTime("comlogSysChangeDateTime"), "yyyy-MM-dd'T'HH:mm:ss"));
            communicationLog
                    .setFileName(CommonUtils.getFileNameFromFilePath(resultset.getString("comlogDocumentReferral")));

            IActionListManager actionListManager = dataService.getActionListManager("CommunicationLog");
            IAction action = actionListManager.getReadAction(resultset.getPrimaryKey());
            IBusinessObject foundComlogBo = action.execute();
            if (!foundComlogBo.getDocumentFileField("DocumentReferral").isEmpty()) {
                byte[] bytes = foundComlogBo.getDocumentFileField("DocumentReferral").downloadContent().readAllBytes();
                byte[] encodedBytes = Base64.getEncoder().encode(bytes);
                communicationLog.setBase64Data(new String(encodedBytes));
            } else {
                communicationLog.setBase64Data("");
            }
            comlogList.add(communicationLog);
        }
        return comlogList;
    }
}
