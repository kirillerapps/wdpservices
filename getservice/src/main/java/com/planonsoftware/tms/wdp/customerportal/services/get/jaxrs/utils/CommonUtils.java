package com.planonsoftware.tms.wdp.customerportal.services.get.jaxrs.utils;

import java.lang.reflect.UndeclaredThrowableException;

import com.planonsoftware.platform.data.v1.ActionNotFoundException;
import com.planonsoftware.platform.data.v1.BusinessException;
import com.planonsoftware.platform.data.v1.FieldNotFoundException;
import com.planonsoftware.platform.data.v1.IConfirmation;
import com.planonsoftware.platform.data.v1.IError;
import com.planonsoftware.platform.data.v1.IMessageHandler;
import com.planonsoftware.platform.data.v1.ISystemError;
import com.planonsoftware.platform.data.v1.IWarning;

public class CommonUtils
{
        public static String getFormattedStackTrace(Throwable e) {
        String formattedStackTrace = null;
        if (e != null) {
            formattedStackTrace = "Exception: " + e;
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    String methodName = stackTraceElement.getMethodName();
                    formattedStackTrace += "\n   " + (methodName != null ? "in thread " + methodName : "") + " at "
                            + stackTraceElement;
                }
            }
        }
        return formattedStackTrace;
    }

    public static String getFormattedErrorMessage(final Throwable e) {

        if (e instanceof BusinessException) {
            return getDisplayText((BusinessException) e);
        } else if (e instanceof FieldNotFoundException) {
            return getDisplayText((FieldNotFoundException) e);
        } else if (e instanceof ActionNotFoundException) {
            return getDisplayText((ActionNotFoundException) e);
        } else if (e instanceof UndeclaredThrowableException) {
            return e.getMessage();
        }
        return getFormattedStackTrace(e);
    }

    public static String getDisplayText(final BusinessException e) {
        String displayText = null;
        if (e != null) {
            IMessageHandler messageHandler = e.getMessageHandler();
            int numberOfSystemErrors = messageHandler.getNumberOfSystemErrors();
            for (int i = 0; i < numberOfSystemErrors; i++) {
                ISystemError errorMessage = messageHandler.getSystemError(i);
                String systemErrorDisplayText = errorMessage.getLabel();
                displayText = (displayText == null ? "" : displayText + "\n");
                displayText += systemErrorDisplayText;
            }
            int numberOfErrors = messageHandler.getNumberOfErrors();
            for (int i = 0; i < numberOfErrors; i++) {
                IError errorMessage = messageHandler.getError(i);
                String errorDisplayText = errorMessage.getLabel();
                displayText = (displayText == null ? "" : displayText + "\n");
                displayText += errorDisplayText;
            }
            int numberOfWarnings = messageHandler.getNumberOfWarnings();
            for (int i = 0; i < numberOfWarnings; i++) {
                IWarning warningMessage = messageHandler.getWarning(i);
                String warningDisplayText = warningMessage.getLabel();
                displayText = (displayText == null ? "" : displayText + "\n");
                displayText += warningDisplayText;
            }
            int numberOfConfirmations = messageHandler.getNumberOfConfirmations();
            for (int i = 0; i < numberOfConfirmations; i++) {
                IConfirmation confirmationMessage = messageHandler.getConfirmation(i);
                String confirmationDisplayText = confirmationMessage.getLabel();
                displayText = (displayText == null ? "" : displayText + "\n");
                displayText += confirmationDisplayText;
            }
        }
        return displayText;
    }

    public static String getDisplayText(final FieldNotFoundException e) {
        String displayText = null;
        if (e != null) {
            displayText = e.getFieldName() + " not found for " + e.getBOType().getPnName();
        }
        return displayText;
    }

    public static String getDisplayText(final ActionNotFoundException e) {
        String displayText = null;
        if (e != null) {
            displayText = e.getBOMName() + " not found for " + e.getBOType().getPnName();
        }
        return displayText;
    }

    public static String getDisplayText(final Exception e) {
        return e.getMessage();
    }

       public static String blankIfNull(String string) {
        return string == null ? "" : string;
    }

}
