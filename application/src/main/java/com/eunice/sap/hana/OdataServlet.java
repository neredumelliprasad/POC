package com.eunice.sap.hana;
/**
 * Created by roychoud on 31 Dec 2019.
 */

import com.eunice.sap.hana.model.ValidationResult;
import com.eunice.sap.hana.service.Validable;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * History:
 * <ul>
 * <li> 31 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public class OdataServlet extends HttpServlet
{
    protected static final String destinationName = "DFJ300_HTTPS_CLONING";
    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {
        response.setHeader("Content-Type","application/json;charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setHeader("Content-Type","application/json;charset=utf-8");
    }

    protected Object getErrorResponse(Exception e){
        Map<String,Object> errorData = new HashMap<>();
        errorData.put("stackTrace", ExceptionUtils.getStackTrace(e));
        errorData.put("errorMessage",ExceptionUtils.getMessage(e));
        return errorData;
    }

    protected final List<ValidationResult> validateOdataObject(List<? extends Validable> validables){
        List<ValidationResult> validationResults =new ArrayList<>();
        for (int i=0;i<validables.size();i++){
            validationResults.addAll(validables.get(i).validate(i));
        }
        return validationResults;
    }
}
