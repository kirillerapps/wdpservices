package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

import java.util.ArrayList;
import java.util.List;

public class OrdersResponseDTO
{
    
    private List<OrderOverViewDTO> realTimeDataResponse = new ArrayList<>();

    public List<OrderOverViewDTO> getOrders() {
        return realTimeDataResponse;
    }

    public void setRealtimeDataResponse(List<OrderOverViewDTO> orders) {
        this.realTimeDataResponse = orders;
    }
}
