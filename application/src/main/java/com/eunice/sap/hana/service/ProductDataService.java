package com.eunice.sap.hana.service;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vmullapu on 18/12/19.
 */

public class ProductDataService
{

    public List<Product> buildProductMasterData(List<RawProductData> rawProductData) {
        List<Product> productList = new ArrayList<>();

        rawProductData.stream().forEach( e -> {
            Product product = new Product();
            product.setProduct(e.getNumber());
            product.setProductType(e.getMaterialType());
            product.addPlant(buildProductPlant(e));

            ProductDescription productDescription = new ProductDescription();
            productDescription.setProductDescription(e.getMaterialDescription());
            product.addDescription(productDescription);

            product.setProductOldID(e.getOldMaterialNumber());
            product.setDivision(e.getDivision());
            product.setCrossPlantStatus(e.getCrossPlantMaterialStatus());
            //product.setCrossPlantStatusValidityDate(e.getValidFromCrossPlantMaterial()); //Convert to local date time
            product.setWeightUnit(e.getWeightUnit());
            ProductSalesDelivery productSalesDelivery = new ProductSalesDelivery();
            productSalesDelivery.setSalesMeasureUnit(e.getSalesUnit());
            productSalesDelivery.setSupplyingPlant(e.getDeliveryPlantSpecStatus());
            product.setItemCategoryGroup(e.getItemCategoryGroup());
            product.setProductHierarchy(e.getProductHierarchy());
            product.setIsBatchManagementRequired(Boolean.valueOf(e.getBatchManagement())); //validation null check and boolean check

            ProductSales productSales = new ProductSales();
            productSales.setTransportationGroup(e.getTransportationGroup());

            ProductPlantSales productPlantSales = new ProductPlantSales();
            productPlantSales.setLoadingGroup(e.getLoadingGroup());

            product.setSerialNumberProfile(e.getSerialNoProfile());
            product.setCountryOfOrigin(e.getCountryOfOrigin());

            ProductStorage productStorage = new ProductStorage();
            //productStorage.setMinRemainingShelfLife(e.getMinimumRemainingShelfLife()); //BigDecimal
            productStorage.setExpirationDate(e.getExpirationDate());
            product.setProductStorage(productStorage);

            ProductValuation productValuation = new ProductValuation();
            productValuation.setValuationClass(e.getValuationClass());
            productValuation.setPriceDeterminationControl(e.getPriceDeterminationControl());
            productValuation.setProductOriginType(e.getMaterialOrigin());
            product.addValuation(productValuation);

            ProductUnitsOfMeasure productUnitsOfMeasure = new ProductUnitsOfMeasure();
            productUnitsOfMeasure.setQuantityDenominator(new BigDecimal(e.getQunatityDenominator())); //BigDecimal
            productUnitsOfMeasure.setQuantityNumerator(new BigDecimal(e.getQunatityNumerator())); //BigDecimal
            product.addProductUnitsOfMeasure(productUnitsOfMeasure);

            //setProductPlantSales in product or productPlant
            //Set product Sales delivery in product
            product.setProductSales(productSales);
            productList.add(product);
        });
        return productList;
    }

    private ProductPlant buildProductPlant(RawProductData rawProductData) {
        ProductPlant productPlant = new ProductPlant();
        productPlant.addStorageLocation(buildProductStorageLocation(rawProductData));
        productPlant.setIsBatchManagementRequired(Boolean.valueOf(rawProductData.getBatchManagementPlant()));
        productPlant.setProfitCenter(rawProductData.getProfitCenter());
        productPlant.setProfileCode(rawProductData.getPlantSpecificMaterialStatus());
        //productPlant.setProfileValidityStartDate(rawProductData.getValidFromPlantMaterial()); //date conversion

        ProductSupplyPlanning productSupplyPlanning = new ProductSupplyPlanning();
        //productSupplyPlanning.setTotalReplenishmentLeadTime(rawProductData.getInhouseProductionTime()); //BigDecimal
        productSupplyPlanning.setPlanningStrategyGroup(rawProductData.getStrategyGroup());

        ProductPlantMRPArea productPlantMRPArea = new ProductPlantMRPArea();
        productPlantMRPArea.setIsMRPDependentRqmt(rawProductData.getMrpDependentRequirements());

        productPlant.addPlantMRPArea(productPlantMRPArea);
        productPlant.setProductSupplyPlanning(productSupplyPlanning);
        return productPlant;
    }

    private ProductStorageLocation buildProductStorageLocation(RawProductData rawProductData) {
        ProductStorageLocation productStorageLocation = new ProductStorageLocation();
        productStorageLocation.setStorageLocation(rawProductData.getStoreLocation());
        productStorageLocation.setProduct(rawProductData.getNumber());
        return productStorageLocation;
    }

}
