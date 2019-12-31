package com.eunice.sap.hana;

import com.eunice.sap.hana.model.MasterDataUpdateResponse;
import com.eunice.sap.hana.model.RawProductData;
import com.eunice.sap.hana.model.ValidationResult;
import com.eunice.sap.hana.service.ProductDataService;
import com.eunice.sap.hana.service.ProductMasterDataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.DefaultProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultProductMasterService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.ProductMasterService;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;

@WebServlet("/masterdata/product")
public class ProductServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(ProductServlet.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ProductMasterDataService productMasterDataService;
    private static final ProductDataService productBuilderService;

    static {
            String destinationName = "DFJ300_HTTPS_CLONING";
            ProductMasterService productMasterService = new DefaultProductMasterService();
            ProductMasterServiceBatch productMasterServiceBatch = new DefaultProductMasterServiceBatch(productMasterService);
            productMasterDataService = new ProductMasterDataService(productMasterServiceBatch,productMasterService,destinationName);
            productBuilderService = new ProductDataService();
    }

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {
        response.setHeader("Content-Type","application/json;charset=utf-8");
        try{
            List<Product> partnerList = productMasterDataService.getProductTop10();
            objectMapper.writeValue(response.getWriter(),partnerList);
        }
        catch (Exception ex){
            objectMapper.writeValue(response.getWriter(),getErrorResponse(ex));
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        InputStream is = req.getInputStream();
        TypeReference<List<RawProductData>> rawProductTypeRef = new TypeReference<List<RawProductData>>() {};
        resp.setHeader("Content-Type","application/json;charset=utf-8");
        try
        {
            List<RawProductData> rawProductDataList = objectMapper.readValue(is,rawProductTypeRef);
            List<ValidationResult> validationResults = productBuilderService.validate(rawProductDataList);
            if (validationResults.isEmpty())
            {
                List<Product> products = productBuilderService.buildProductMasterData(rawProductDataList);
                MasterDataUpdateResponse response = productMasterDataService.persistProducts(products);
                objectMapper.writeValue(resp.getWriter(), response);
            }
            else {
                objectMapper.writeValue(resp.getWriter(), validationResults);
            }
        }
        catch (Exception e)
        {
            objectMapper.writeValue(resp.getWriter(),getErrorResponse(e));
        }

    }

    private Object getErrorResponse(Exception e){
        Map<String,Object> errorData = new HashMap<>();
        errorData.put("stackTrace",ExceptionUtils.getStackTrace(e));
        errorData.put("errorMessage",ExceptionUtils.getMessage(e));
        return errorData;
    }
}
