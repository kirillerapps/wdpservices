package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses;

public class ServiceErrorResponse<T> {
    private T error;

    public ServiceErrorResponse() {
        // Default constructor.
    }

    /**
     * Creates an instance of the Response class.
     * 
     * @param aData the data to set
     */
    public ServiceErrorResponse(final T aData) {
        this.error = aData;
    }

    /**
     * @return the error
     */
    public T getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(T error) {
        this.error = error;
    }
}
