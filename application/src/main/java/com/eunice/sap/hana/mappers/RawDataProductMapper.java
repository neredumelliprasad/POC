package com.eunice.sap.hana.mappers;
/**
 * Created by roychoud on 18 Dec 2019.
 */

import com.eunice.sap.hana.model.DataConstructionContext;
import com.eunice.sap.hana.model.RawProductData;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductDescription;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductPlant;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductPlantMRPArea;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductPlantSales;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductSales;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductSalesDelivery;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductStorage;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductStorageLocation;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductSupplyPlanning;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductUnitsOfMeasure;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductValuation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        ProductUnitsOfMeasure productUnitsOfMeasure = new ProductUnitsOfMeasure();
        productUnitsOfMeasure.setQuantityDenominator(new BigDecimal(rawProductData.getQunatityDenominator()));
        productUnitsOfMeasure.setQuantityNumerator(new BigDecimal(rawProductData.getQunatityNumerator()));
        return productUnitsOfMeasure;
    }

    private ProductStorageLocation buildProductStorageLocation(RawProductData rawProductData,DataConstructionContext constructionContext) {
        ProductStorageLocation productStorageLocation = new ProductStorageLocation();
        productStorageLocation.setStorageLocation(rawProductData.getStoreLocation());
        productStorageLocation.setProduct(rawProductData.getMaterial());
        return productStorageLocation;
    }

    ProductPlant createProductPlant(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductPlant productPlant = new ProductPlant();
        ProductSupplyPlanning productSupplyPlanning = new ProductSupplyPlanning();
        productSupplyPlanning.setPlanningStrategyGroup(rawProductData.getStrategyGroup());
        ProductPlantMRPArea productPlantMRPArea = new ProductPlantMRPArea();
        productPlantMRPArea.setIsMRPDependentRqmt(rawProductData.getMrpDependentRequirements());
        productPlant.addPlantMRPArea(productPlantMRPArea);
        productPlant.setIsBatchManagementRequired(Boolean.valueOf(rawProductData.getBatchManagementPlant()));
        productPlant.setProfitCenter(rawProductData.getProfitCenter());
        productPlant.setProfileCode(rawProductData.getPlantSpecificMaterialStatus());
        productPlant.setProfileValidityStartDate(LocalDateTime.parse(rawProductData.getValidFromPlantMaterial()));
        productPlant.setProductSupplyPlanning(productSupplyPlanning);
        productPlant.setPlant(rawProductData.getPlant());
        productPlant.addStorageLocation(buildProductStorageLocation(rawProductData,constructionContext));
        productPlant.setPlantSales(createProductPlantSales(rawProductData,constructionContext));
        return productPlant;
    }

    ProductStorage createProductStorage(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductStorage productStorage = new ProductStorage();
        productStorage.setMinRemainingShelfLife(new BigDecimal(rawProductData.getMinimumRemainingShelfLife()));
        productStorage.setExpirationDate(rawProductData.getExpirationDate());
        return productStorage;
    }
    ProductSalesDelivery createProductSalesDelivery(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductSalesDelivery productSalesDelivery = new ProductSalesDelivery();
        productSalesDelivery.setSalesMeasureUnit(rawProductData.getSalesUnit());
        productSalesDelivery.setSupplyingPlant(rawProductData.getDeliveryPlantSpecStatus());
        return productSalesDelivery;
    }

    ProductDescription createProductDescription(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductDescription productDescription = new ProductDescription();
        productDescription.setProductDescription(rawProductData.getMaterialDescription());
        return productDescription;
    }
    ProductValuation createProductValuation(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductValuation productValuation = new ProductValuation();
        productValuation.setValuationClass(rawProductData.getValuationClass());
        productValuation.setPriceDeterminationControl(rawProductData.getPriceDeterminationControl());
        productValuation.setProductOriginType(rawProductData.getMaterialOrigin());
        return productValuation;
    }

    ProductSales createProductSales(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductSales productSales = new ProductSales();
        productSales.setTransportationGroup(rawProductData.getTransportationGroup());
        return productSales;
    }

    ProductPlantSales createProductPlantSales(RawProductData rawProductData,DataConstructionContext constructionContext){
        ProductPlantSales productPlantSales = new ProductPlantSales();
        productPlantSales.setLoadingGroup(rawProductData.getLoadingGroup());
        return productPlantSales;
    }
    public Product createEntity(RawProductData rawProductData,DataConstructionContext constructionContext){
        String key = rawProductData.getMaterial();
        Product product = constructionContext.getProductMap().containsKey(key)?constructionContext.getProductMap().get(key):new Product();
        product.setProduct(rawProductData.getMaterial());
        product.setProductType(rawProductData.getMaterialType());
        product.setProductOldID(rawProductData.getOldMaterialNumber());
        product.setDivision(rawProductData.getDivision());
        product.setCrossPlantStatus(rawProductData.getCrossPlantMaterialStatus());
        product.setCrossPlantStatusValidityDate(LocalDateTime.parse(rawProductData.getValidFromCrossPlantMaterial())); //Convert to local date time
        product.setWeightUnit(rawProductData.getWeightUnit());
        product.setItemCategoryGroup(rawProductData.getItemCategoryGroup());
        product.setProductHierarchy(rawProductData.getProductHierarchy());
        product.setIsBatchManagementRequired(Boolean.valueOf(rawProductData.getBatchManagement())); //validation null check and boolean check
        product.setSerialNumberProfile(rawProductData.getSerialNoProfile());
        product.setCountryOfOrigin(rawProductData.getCountryOfOrigin());
        product.addPlant(createProductPlant(rawProductData,constructionContext));
        product.addDescription(createProductDescription(rawProductData,constructionContext));
        product.addSalesDelivery(createProductSalesDelivery(rawProductData,constructionContext));
        product.addValuation(createProductValuation(rawProductData,constructionContext));
        product.addProductUnitsOfMeasure(createUnitOfMeasure(rawProductData,constructionContext));
        product.setProductSales(createProductSales(rawProductData,constructionContext));
        product.setProductStorage(createProductStorage(rawProductData,constructionContext));

        return product;
    }
}
