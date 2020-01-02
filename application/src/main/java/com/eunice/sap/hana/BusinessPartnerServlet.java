package com.eunice.sap.hana;

import com.eunice.sap.hana.service.BusinessPartnerMasterDataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.BusinessPartnerService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultBusinessPartnerService;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

@WebServlet("/masterdata/businessPartner")
public class BusinessPartnerServlet extends OdataServlet
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = CloudLoggerFactory.getLogger(BusinessPartnerServlet.class);
    private static final BusinessPartnerMasterDataService businessPartnerMDService;

    static {
            BusinessPartnerService businessPartnerService = new DefaultBusinessPartnerService();
            businessPartnerMDService = new BusinessPartnerMasterDataService(businessPartnerService,destinationName);
    }

    @Override
    protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
        throws IOException
    {
        super.doGet(request,response);
        try{
            List<BusinessPartner> partnerList = businessPartnerMDService.getBusinessPartnerTop10();
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
        TypeReference<List<String>> rawProductTypeRef = new TypeReference<List<String>>() {};
        try
        {
            List<String> bpKeys = objectMapper.readValue(is,rawProductTypeRef);
            List<BusinessPartner> businessPartners = businessPartnerMDService.getBusinessPartnerByKeys(bpKeys);
            objectMapper.writeValue(resp.getWriter(),businessPartners);
        }
        catch (Exception e)
        {
            objectMapper.writeValue(resp.getWriter(),getErrorResponse(e));
        }

    }
}
