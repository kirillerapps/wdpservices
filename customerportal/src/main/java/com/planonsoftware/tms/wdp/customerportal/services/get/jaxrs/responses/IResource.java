package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.responses;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils.CommonUtils;

public interface IResource
{
        default Response constructResponse(String originAllowed, IServiceResponse response) {
        GenericEntity entity = new GenericEntity<IServiceResponse>(response) {
        };
        return Response.ok()
                       .entity(entity)
                       .build();
    }

    default  Response constructResponse(IServiceResponse response) {
        GenericEntity entity = new GenericEntity<IServiceResponse>(response) {
        };
        return Response.ok()
                       .entity(entity)
                       .build();
    }

    default  Response constructErrorResponse(Throwable aMessage) {
        return constructErrorResponse(aMessage, 500);
    }

    default  Response constructErrorResponse(Throwable aMessage, Integer statusCode) {
        final String planonMessage = CommonUtils.getFormattedErrorMessage(aMessage);
        return constructErrorResponse(planonMessage, statusCode);
    }

    default  Response constructErrorResponse(String aMessage, Integer statusCode) {
        GenericEntity entity = new GenericEntity<ServiceErrorResponse>(new ServiceErrorResponse(aMessage)) {
        };
        return Response.serverError()
                       .status(statusCode)
                       .entity(entity)
                       .build();
    }

    default  Response constructErrorResponse(String originAllowed, Exception aexception) {
        if (aexception.getCause() != null) {
            return constructErrorResponse(aexception.getCause(), 500);
        }
        return constructErrorResponse(aexception.getMessage(), 500);
    }
}
