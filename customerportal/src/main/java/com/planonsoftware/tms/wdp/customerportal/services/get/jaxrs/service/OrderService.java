package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.service;

import java.util.ArrayList;
import java.util.List;
import com.planonsoftware.platform.data.v1.ActionNotFoundException;
import com.planonsoftware.platform.data.v1.BusinessException;
import com.planonsoftware.platform.data.v1.FieldNotFoundException;
import com.planonsoftware.platform.data.v1.IDataService;
import com.planonsoftware.platform.data.v1.IDatabaseQuery;
import com.planonsoftware.platform.data.v1.IResultSet;
import com.planonsoftware.platform.data.v1.Operator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.FilterDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderDetailsDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.ReportedByDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO.InternalCoordinator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO.RentableUnit;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.OrderOverViewDTO.State;

import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO.WoExternalTradesman;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO.WoInternalCoordinator;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO.WoInventoryItem;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO.WoOrderGroup;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto.WorkOrdersDTO.WoState;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.CommonUtils;
import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.Constants;

public class OrderService {

    public OrderDetailsDTO getOrderByCode(IDataService dataService, String orderCode) throws Exception {
        System.out.println("OrderService: getOrderByCode " + orderCode);
        // Get primary keys for IN function
        int foundBoTypeUsrRequest = getboTypeQuery(dataService, "UsrRequest");
        int foundBoTypeUsrComplaint = getboTypeQuery(dataService, "UsrOrder");
        // Finally we can do query
        IDatabaseQuery queryOrders = dataService.getPVDatabaseQuery("GetOrdersByType");
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrRequest);
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrComplaint);
        // Get query by code/requestId
        queryOrders.getSearchExpression("OrderNumberSearch", Operator.EQUAL).setValue(orderCode);
        IResultSet resultset = queryOrders.execute();
        System.out.println("Query Executed successfully...");
        OrderOverViewDTO orderOverViewDTO = new OrderDetailsDTO();

        if (resultset.first()) {
            // Fill the parent class
            orderOverViewDTO = fillOrderBoFields(resultset, orderOverViewDTO);
            // convert class to child class
            OrderDetailsDTO orderDetails = (OrderDetailsDTO) orderOverViewDTO;
            orderDetails = fillOrderDetails(orderDetails, resultset, dataService);
            return orderDetails;
        }
        return null;
    }

    public List<OrderOverViewDTO> getOrders(IDataService dataService, List<OrderOverViewDTO> orderList,
            FilterDTO filterObject) throws Exception {
        // Get primary keys for IN function
        int foundBoTypeUsrRequest = getboTypeQuery(dataService, "UsrRequest");
        int foundBoTypeUsrComplaint = getboTypeQuery(dataService, "UsrOrder");
        // Finally we can do query
        IDatabaseQuery queryOrders = dataService.getPVDatabaseQuery("GetOrdersByType");
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrRequest);
        queryOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrComplaint);

        // Query filter description
        if (filterObject.getNotModifiedBy() != null) {
            queryOrders.getSearchExpression("notModifiedByAccountName", Operator.NOT_EQUAL)
                    .setValue(filterObject.getNotModifiedBy());
        }

        // Query filter state
        if (filterObject.getModifiedBy() != null) {
            queryOrders.getSearchExpression("modifiedByAccountName", Operator.EQUAL)
                    .setValue(filterObject.getModifiedBy());
        }

        // Query filter mutation date from
        if (filterObject.getModifiedDateFrom() != null) {
            queryOrders.getDateTimeSearchExpression("searchMutationDateFrom", Operator.GREATER)
                    .setValue(filterObject.getModifiedDateFrom());
        }
        // Query filter mutation date to
        if (filterObject.getModifiedDateTo() != null) {
            queryOrders.getDateTimeSearchExpression("searchMutationDateTo", Operator.LESS_EQUAL)
                    .setValue(filterObject.getModifiedDateTo());
        }

        IResultSet resultsetNew = queryOrders.execute();
        System.out.println("Query Executed successfully...");

        while (resultsetNew.next()) {
            OrderOverViewDTO orderOverViewDTO = new OrderOverViewDTO();
            orderOverViewDTO = fillOrderBoFields(resultsetNew, orderOverViewDTO);
            orderList.add(orderOverViewDTO);
        }

        return orderList;
    }

    public List<WorkOrdersDTO> getSubOrders(IDataService dataService, FilterDTO filterObject) throws Exception {

        int foundBoTypeUsrRequest = getboTypeQuery(dataService, "UsrRequest");
        int foundBoTypeUsrComplaint = getboTypeQuery(dataService, "UsrOrder");

        IDatabaseQuery querySubOrders = dataService.getPVDatabaseQuery("GetSubOrders");
        // querySubOrders.getSearchExpression("parentOrderNumber", Operator.NOT_NULL);
        // Query filter description

        querySubOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrRequest);
        querySubOrders.getSearchExpression("boType", Operator.IN).addValue(foundBoTypeUsrComplaint);

        if (filterObject.getNotModifiedBy() != null) {
            querySubOrders.getSearchExpression("notModifiedByAccountName", Operator.NOT_EQUAL)
                    .setValue(filterObject.getNotModifiedBy());
        }

        // Query filter state
        if (filterObject.getModifiedBy() != null) {
            querySubOrders.getSearchExpression("modifiedByAccountName", Operator.EQUAL)
                    .setValue(filterObject.getModifiedBy());
        }

        // Query filter mutation date from
        if (filterObject.getModifiedDateFrom() != null) {
            querySubOrders.getDateTimeSearchExpression("searchMutationDateFrom", Operator.GREATER)
                    .setValue(filterObject.getModifiedDateFrom());
        }
        // Query filter mutation date to
        if (filterObject.getModifiedDateTo() != null) {
            querySubOrders.getDateTimeSearchExpression("searchMutationDateTo", Operator.LESS_EQUAL)
                    .setValue(filterObject.getModifiedDateTo());
        }

        IResultSet resultsetNew = querySubOrders.execute();

        // Create container for the suborders
        List<WorkOrdersDTO> foundSubOrders = new ArrayList<WorkOrdersDTO>();
        while (resultsetNew.next()) {
            WorkOrdersDTO subOrder = new WorkOrdersDTO();
            subOrder.setDescription(CommonUtils.blankIfNull(resultsetNew.getString("orderComment")));
            subOrder.setId(CommonUtils.blankIfNull(resultsetNew.getString("orderNumber")));
            subOrder.setPoNumber(CommonUtils.blankIfNull(resultsetNew.getString("poNumberCode")));
            subOrder.setPropertyId(CommonUtils.blankIfNull(resultsetNew.getString("propCode")));
            subOrder.setRequestedCompleteOn(CommonUtils.returnFormatDateTimeFormatToString(
                    resultsetNew.getDateTime("orderRequestedUserEndDateTime"), Constants.dateTimeFormat));
            subOrder.setReportedOn(CommonUtils.returnFormatDateTimeFormatToString(
                    resultsetNew.getDateTime("orderInsertDateTime"), Constants.dateTimeFormat));

            subOrder.setmodifiedOn(CommonUtils.returnFormatDateTimeFormatToString(
                    resultsetNew.getDateTime("sysMutationDateTimeSelect"), Constants.dateTimeFormat));

            subOrder.setRequestId(CommonUtils.blankIfNull(resultsetNew.getString("parentOrderNumberSelect")));

            // Nested items
            // Set state
            WoState state = subOrder.getState();
            state.setId(CommonUtils.blankIfNull(resultsetNew.getString("statusCode")));
            state.setName(CommonUtils.blankIfNull(resultsetNew.getString("statusSystemName")));
            subOrder.setState(state);

            // Set rentableUnit
            WoExternalTradesman externalTradesman = subOrder.getExternalTradesman();
            externalTradesman.setId(CommonUtils.blankIfNull(resultsetNew.getString("externalTradesmanCode")));
            externalTradesman.setName(CommonUtils.blankIfNull(resultsetNew.getString("externalTradesmanName")));
            subOrder.setExternalTradesman(externalTradesman);

            // Set Internal coord
            WoInternalCoordinator iCoordinator = subOrder.getInternalCoordinator();
            iCoordinator.setId(CommonUtils.blankIfNull(resultsetNew.getString("intCoordCode")));
            iCoordinator.setFirstName(CommonUtils.blankIfNull(resultsetNew.getString("intCoordFirstName")));
            iCoordinator.setLastName(CommonUtils.blankIfNull(resultsetNew.getString("intCoordLastName")));
            subOrder.setInternalCoordinator(iCoordinator);

            // Set order group
            WoOrderGroup orderGroup = subOrder.getOrderGroup();
            orderGroup.setId(CommonUtils.blankIfNull(resultsetNew.getString("orderGroupCode")));
            orderGroup.setName(CommonUtils.blankIfNull(resultsetNew.getString("orderGroupName")));
            subOrder.setOrderGroup(orderGroup);

            // Set inv item
            WoInventoryItem invItem = subOrder.getInventoryItem();
            invItem.setId(CommonUtils.blankIfNull(resultsetNew.getString("invItemCode")));
            invItem.setName(CommonUtils.blankIfNull(resultsetNew.getString("invItemName")));
            subOrder.setInventoryItem(invItem);

            // Set reported by
            ReportedByDTO reportedBy = subOrder.getReportedBy();
            reportedBy.setId(CommonUtils.blankIfNull(resultsetNew.getString("reportedByCode")));
            reportedBy.setEmail(CommonUtils.blankIfNull(resultsetNew.getString("reportedByEmail")));
            reportedBy.setFirstName(CommonUtils.blankIfNull(resultsetNew.getString("reportedByFirstName")));
            reportedBy.setLastName(CommonUtils.blankIfNull(resultsetNew.getString("reportedByLastName")));
            reportedBy.setCustomerId(CommonUtils.blankIfNull(resultsetNew.getString("reportedByAdressCode")));
            subOrder.setReportedBy(reportedBy);
            foundSubOrders.add(subOrder);
        }

        return foundSubOrders;
    }

    private OrderOverViewDTO fillOrderBoFields(IResultSet resultsetNew, OrderOverViewDTO orderOverViewDTO)
            throws Exception {
        orderOverViewDTO.setStandardOrderId(CommonUtils.blankIfNull(resultsetNew.getString("StandardOrderRefCode")));
        orderOverViewDTO.setDescription(CommonUtils.blankIfNull(resultsetNew.getString("orderComment")));
        orderOverViewDTO.setcommunicationOwner(CommonUtils.blankIfNull(resultsetNew.getString("communicationOwner")));
         orderOverViewDTO.setcommunicationReader(CommonUtils.blankIfNull(resultsetNew.getString("communicationReader")));


        orderOverViewDTO.setPropertyId(CommonUtils.blankIfNull(resultsetNew.getString("propCode")));
        orderOverViewDTO.setUploadAttachmentYN(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString10")));
        orderOverViewDTO.setContactName(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString19")));
        orderOverViewDTO.setContactPhone(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString3")));
        orderOverViewDTO.setHighPriority(resultsetNew.getBoolean("orderHighPriority"));
        orderOverViewDTO.setExplanationPriority(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString30")));
        orderOverViewDTO.setDescriptionCause(CommonUtils.blankIfNull(resultsetNew.getString("orderComment")));
        orderOverViewDTO.setWhereIsTheProblem(CommonUtils.blankIfNull(resultsetNew.getString("orderFreeString1")));
        orderOverViewDTO.setAppointmentBooking(resultsetNew.getBoolean("orderAppointmentBooking"));
        orderOverViewDTO.setId(CommonUtils.blankIfNull(resultsetNew.getString("OrderNumber")));
        orderOverViewDTO.setReportedOn(CommonUtils.returnFormatDateTimeFormatToString(
                resultsetNew.getDateTime("orderInsertDateTime"), Constants.dateTimeFormat));
        orderOverViewDTO.setRequestedCompleteOn(CommonUtils.returnFormatDateTimeFormatToString(
                resultsetNew.getDateTime("orderRequestedUserEndDateTime"), Constants.dateTimeFormat));

        orderOverViewDTO.setmodifiedOn(CommonUtils.returnFormatDateTimeFormatToString(
                resultsetNew.getDateTime("sysMutationDateTimeSelect"), Constants.dateTimeFormat));

        // Set state
        State state = orderOverViewDTO.getState();
        state.setId(resultsetNew.getString("statusCode"));
        state.setName(resultsetNew.getString("statusSystemName"));
        orderOverViewDTO.setState(state);

        // Set rentableUnit
        RentableUnit rentableUnit = orderOverViewDTO.getRentableUnit();
        rentableUnit.setId(CommonUtils.blankIfNull(resultsetNew.getString("rentCode")));
        rentableUnit.setName(CommonUtils.blankIfNull(resultsetNew.getString("rentName")));
        orderOverViewDTO.setRentableUnit(rentableUnit);

        // Set Internal coord
        InternalCoordinator iCoordinator = orderOverViewDTO.getInternalCoordinator();
        iCoordinator.setId(CommonUtils.blankIfNull(resultsetNew.getString("intCoordCode")));
        iCoordinator.setFirstName(CommonUtils.blankIfNull(resultsetNew.getString("intCoordFirstName")));
        iCoordinator.setLastName(CommonUtils.blankIfNull(resultsetNew.getString("intCoordLastName")));
        orderOverViewDTO.setInternalCoordinator(iCoordinator);
        // Set reported by
        ReportedByDTO reportedBy = orderOverViewDTO.getReportedBy();
        reportedBy.setId(CommonUtils.blankIfNull(resultsetNew.getString("reportedByCode")));
        reportedBy.setEmail(CommonUtils.blankIfNull(resultsetNew.getString("reportedByEmail")));
        reportedBy.setFirstName(CommonUtils.blankIfNull(resultsetNew.getString("reportedByFirstName")));
        reportedBy.setLastName(CommonUtils.blankIfNull(resultsetNew.getString("reportedByLastName")));
        reportedBy.setCustomerId(CommonUtils.blankIfNull(resultsetNew.getString("reportedByAdressCode")));
        orderOverViewDTO.setReportedBy(reportedBy);

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

    private OrderDetailsDTO fillOrderDetails(OrderDetailsDTO orderDetails, IResultSet resultset,
            IDataService dataService) throws BusinessException, ActionNotFoundException, FieldNotFoundException {
        IDatabaseQuery queryOrderAnswers = dataService.getPVDatabaseQuery("GetOrderBOByCode");
        queryOrderAnswers.getSearchExpression("OrderNumber", Operator.EQUAL).setValue(resultset.getPrimaryKey());
        IResultSet resultsetNew = queryOrderAnswers.execute();
        // List<Answer> answerList = orderDetails.getAnswers();
        while (resultsetNew.next()) {
            orderDetails = CommonUtils.setQuestionAnswer(orderDetails, resultsetNew.getString("QuestionnaireCode"),
                    resultsetNew.getString("AnswerOptionCode"));
        }
        return orderDetails;
    }
}
