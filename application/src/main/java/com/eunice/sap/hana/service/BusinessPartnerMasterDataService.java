package com.eunice.sap.hana.service;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.ExpressionFluentHelper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartnerFluentHelper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.BusinessPartnerService;
import java.util.List;
import javax.transaction.Transactional;

public class BusinessPartnerMasterDataService
{
    private BusinessPartnerService businessPartnerService;

    private String destinationName;


    public BusinessPartnerMasterDataService(
        BusinessPartnerService businessPartnerService,
        String connection)
    {
        this.businessPartnerService = businessPartnerService;
        this.destinationName = connection;
    }


    @Transactional
    public List<BusinessPartner> getBusinessPartnerTop10() throws ODataException
    {
        BusinessPartnerFluentHelper fluentHelper =  businessPartnerService.getAllBusinessPartner().top(10);
        return fluentHelper.execute(getErpContext());
    }

    @Transactional
    public List<BusinessPartner> getBusinessPartnerByKeys(List<String> businessPartnerKeys) throws ODataException
    {
        return businessPartnerService.
            getAllBusinessPartner().
            filter(getFilterQuery(businessPartnerKeys)).
            execute(getErpContext());
    }


    private ExpressionFluentHelper getFilterQuery(List<String> businessPartnerKeys){
        ExpressionFluentHelper<BusinessPartner> filter = null;
        if (!businessPartnerKeys.isEmpty()) {
            for (String eachKey:businessPartnerKeys){
                filter = filter == null? BusinessPartner.BUSINESS_PARTNER.eq(eachKey):filter.or(BusinessPartner.BUSINESS_PARTNER.eq(eachKey));
            }
        }
        return filter;
    }

    private final ErpConfigContext getErpContext(){
        return new ErpConfigContext(destinationName);
    }
}
