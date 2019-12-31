package com.eunice.sap.hana.service;

import com.eunice.sap.hana.mappers.RawDataProductMapper;
import com.eunice.sap.hana.model.DataConstructionContext;
import com.eunice.sap.hana.model.MasterDataUpdateResponse;
import com.eunice.sap.hana.model.RawProductData;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.ExpressionFluentHelper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.batch.BatchResponse;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductFluentHelper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatch;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.batch.ProductMasterServiceBatchChangeSet;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.ProductMasterService;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;

public class ProductMasterDataService extends RawDataProductMapper
{
    private ProductMasterServiceBatch productMasterServiceBatch;

    private ProductMasterService productMasterService;

    private String destinationName;


    public ProductMasterDataService(
        ProductMasterServiceBatch productMasterServiceBatch,
        ProductMasterService productMasterService,
        String connection)
    {
        this.productMasterServiceBatch = productMasterServiceBatch;
        this.productMasterService = productMasterService;
        this.destinationName = connection;
    }


    @Transactional
    public MasterDataUpdateResponse persistProducts(Collection<Product> products) throws ODataException
    {
        ProductMasterServiceBatchChangeSet changeSet = productMasterServiceBatch.beginChangeSet();
        for (Product product:products){
            changeSet = changeSet.updateProduct(product);
        }
        BatchResponse response = changeSet.endChangeSet().execute(getErpContext());
        return new MasterDataUpdateResponse(response);
    }

    @Transactional
    public List<Product> getProductTop10() throws ODataException
    {
        ProductFluentHelper productFluentHelper =  productMasterService.getAllProduct().top(10);
        return productFluentHelper.execute(getErpContext());
    }

    @Transactional
    public List<Product> getProductsByKey(List<String> productKeys) throws ODataException
    {
        return productMasterService.
            getAllProduct().
            filter(getFilterQuery(productKeys)).
            execute(getErpContext());
    }

    public Set<Product> constructProductData(List<RawProductData> rawProductData)
    {
        DataConstructionContext constructionContext = new DataConstructionContext();
        Set<Product> products = new HashSet<>();
        rawProductData.stream().forEach(each->{
            Product eachProduct = createEntity(each,constructionContext);
            products.add(eachProduct);
        });
    return products;
    }

    private ExpressionFluentHelper getFilterQuery(List<String> productKeys){
        ExpressionFluentHelper<Product> filter = null;
        if (!productKeys.isEmpty()) {
            for (String eachKey:productKeys){
                filter = filter == null? Product.PRODUCT.eq(eachKey):filter.or(Product.PRODUCT.eq(eachKey));
            }
        }
        return filter;
    }

    private final ErpConfigContext getErpContext(){
        return new ErpConfigContext(destinationName);
    }
}
