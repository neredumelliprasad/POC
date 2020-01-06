package com.eunice.sap.hana;

import com.eunice.sap.hana.model.DataConstructionContext;
import com.eunice.sap.hana.model.MasterDataUpdateResponse;
import com.eunice.sap.hana.model.RawProductData;
import com.eunice.sap.hana.model.ValidationResult;
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
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

@WebServlet("/masterdata/product")
public class ProductServlet extends OdataServlet
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(ProductServlet.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ProductMasterDataService productMasterDataService;

    static {
            ProductMasterService productMasterService = new DefaultProductMasterService();
            ProductMasterServiceBatch productMasterServiceBatch = new DefaultProductMasterServiceBatch(productMasterService);
            productMasterDataService = new ProductMasterDataService(productMasterServiceBatch,productMasterService,destinationName);
    }

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {
        super.doGet(request,response);
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
        super.doPost(req,resp);
        InputStream is = req.getInputStream();
        TypeReference<List<RawProductData>> rawProductTypeRef = new TypeReference<List<RawProductData>>() {};
        try
        {
            List<RawProductData> rawProductDataList = objectMapper.readValue(is,rawProductTypeRef);
            List<ValidationResult> validationResults = validateOdataObject(rawProductDataList);
            if (validationResults.isEmpty())
            {
                DataConstructionContext constructionContext = new DataConstructionContext();
                List<Product> products = rawProductDataList.parallelStream().
                    map(each->productMasterDataService.createEntity(each,constructionContext)).
                    collect(Collectors.toList());
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
}
