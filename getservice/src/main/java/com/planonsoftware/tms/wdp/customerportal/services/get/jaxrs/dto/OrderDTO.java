package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.dto;

public class OrderDTO
{
    private int syscode;
    private String code;
    private String name;
    private String errorMessage;
 



    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param anErrorMessage the anErrorMessage to set
     */
    public void setErrorMessage(final String anErrorMessage) {
        errorMessage = anErrorMessage;
    }


    /**
     * @return the syscode
     */
    public int getSyscode() {
        return syscode;
    }

    /**
     * @param orderSyscode the syscode to set
     */
    public void setSyscode(final int aSyscode) {
        syscode = aSyscode;
    }


    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param aCode the code to set
     */
    public void setCode(final String aCode) {
        code = aCode;
    }


    
    /**
     * @return the code
     */
    public String getName() {
        return name;
    }

    /**
     * @param aCode the code to set
     */
    public void setName(final String aName) {
        name = aName;
    }
   
}
