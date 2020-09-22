package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses;

/**
 * Abstract class for all the response classes.
 * 
 * @author sapydi
 *
 * @param <T>
 */
public class ServiceResponse<T> implements IServiceResponse<T> {
    
    private T data;
    
    /**
     * Creates an instance of the Response class.
     * 
     * @param aData
     */
    public ServiceResponse() {
        // Default constructor.
    }
    
    /**
     * Creates an instance of the Response class.
     * 
     * @param aData the data to set
     */
    public ServiceResponse(final T aData) {
        this.data = aData;
    }
    
    /**
     * @return the data
     */
    public T getData() {
        return data;
    }
    
    /**
     * @param aData the data to set
     */
    public void setData(final T aData) {
        this.data = aData;
    }
}
