package com.eunice.sap.hana.mappers;
/**
 * Created by roychoud on 18 Dec 2019.
 */

import com.eunice.sap.hana.model.DataConstructionContext;
import com.eunice.sap.hana.model.RawProductData;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductDescription;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductPlant;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductSalesDelivery;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductStorageLocation;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductUnitsOfMeasure;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductValuation;

/**
 * History:
 * <ul>
 * <li> 18 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public class RawDataProductMapper implements RawDataMapper<Product>
{
    ProductUnitsOfMeasure createUnitOfMeasure(RawProductData rawProductData, DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    ProductPlant createProductPlant(RawProductData productData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    ProductStorageLocation createProductStorageLocation(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }
    ProductSalesDelivery createProductSalesDelivery(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }

    ProductDescription createProductDescription(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }
    ProductValuation createProductValuation(RawProductData rawProductData,DataConstructionContext constructionContext){
        //TODO to implement
        return null;
    }
    public Product createEntity(RawProductData rawProductData,DataConstructionContext constructionContext){
        String key = rawProductData.getMaterial();
        Product product = constructionContext.getProductMap().containsKey(key)?constructionContext.getProductMap().get(key):new Product();
        product.setProduct(rawProductData.getMaterial());
        product.setProductType(rawProductData.getMaterialType());
        product.addPlant(createProductPlant(rawProductData,constructionContext));
        constructionContext.getProductMap().put(product.getProduct(),product);
        return product;
    }
}
