package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses;

/**
 * Interface for all the parameters
 * 
 * @param <T> the type
 */
public interface IServiceResponse<T> {

    /**
     * @return the data
     */
    T getData();

    /**
     * @param aData the data to set
     */
    void setData(final T aData);
}
