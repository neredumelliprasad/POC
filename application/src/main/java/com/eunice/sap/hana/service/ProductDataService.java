package com.eunice.sap.hana.service;

import com.eunice.sap.hana.model.DateValidator;
import com.eunice.sap.hana.model.ErrorType;
import com.eunice.sap.hana.model.MandatoryField;
import com.eunice.sap.hana.model.NumberValidator;
import com.eunice.sap.hana.model.Precision;
import com.eunice.sap.hana.model.RawProductData;
import com.eunice.sap.hana.model.Validable;
import com.eunice.sap.hana.model.ValidationResult;
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
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.commons.lang.StringUtils;

/**
 * Created by vmullapu on 18/12/19.
 */

public class ProductDataService
{

    private static Set<Field> rawDataFields = new HashSet<>();
    static
    {
        Stream.of(RawProductData.class.getDeclaredFields()).forEach(each->{
            rawDataFields.add(each);
        });
    }

    public List<ValidationResult> validate(List<RawProductData> rawProductData){
        List<ValidationResult> validationResults = new ArrayList<>();
        for (int i=0;i<rawProductData.size();i++){
            Set<ValidationResult> eachValidation = validate(rawProductData.get(i),i);
            validationResults.addAll(eachValidation);
        }
        return validationResults;
    }

    private Set<ValidationResult> validate(RawProductData rawProductData,int pos){
        Set<ValidationResult> validationResults = new HashSet<>();
        rawDataFields.stream().forEach(each->{
            try
            {
                Optional<ValidationResult> mandatoryFieldValidation = validateMandatoryField(each, rawProductData, pos);
                Optional<ValidationResult> numberValidation = validateNumber(each, rawProductData, pos);
                Optional<ValidationResult> dateValidation = validateDate(each, rawProductData, pos);
                if (mandatoryFieldValidation.isPresent())
                    validationResults.add(mandatoryFieldValidation.get());
                if (numberValidation.isPresent())
                {
                    validationResults.add(numberValidation.get());
                }
                if (dateValidation.isPresent())
                {
                    validationResults.add(dateValidation.get());
                }
            }
            catch (IllegalAccessException e){
                throw new RuntimeException("Access not available to RawProductData fields",e);
            }
        });
        return validationResults;
    }

    private Optional<ValidationResult> validateNumber(Field eachField,RawProductData data,int position) throws IllegalAccessException
    {
        boolean isValid = true;
            NumberValidator numberValidator = eachField.getAnnotation(NumberValidator.class);
        Object input = null;
        if (numberValidator!=null){
            input = data.getData(eachField);
            if (input!=null)
            {
                Precision precision = numberValidator.precision();
                if (precision == Precision.INTEGER)
                {
                    try{
                        Integer.parseInt(input.toString());
                    }
                    catch (NumberFormatException e){
                        isValid = false;
                    }
                }
            }
        }
        return isValid?Optional.empty():Optional.of(new ValidationResult(eachField.getName(), ErrorType.INCORRECT_NUMBER_FORMAT,input,position));
    }

    private Optional<ValidationResult> validateMandatoryField(Field eachField, Validable data,int position) throws IllegalAccessException
    {
        boolean isValid = true;
        MandatoryField mandatoryField = eachField.getAnnotation(MandatoryField.class);
        String input = null;
        if (mandatoryField!=null){
            input = (String) data.getData(eachField);
            if (StringUtils.isEmpty(input))
            {
                isValid = false;
            }
        }
        return isValid?Optional.empty():Optional.of(new ValidationResult(eachField.getName(), ErrorType.MANDATORY_FIELD_MISSING,input,position));
    }

    private Optional<ValidationResult> validateDate(Field eachField,RawProductData data,int position) throws IllegalAccessException
    {
        boolean isValid = true;
        DateValidator dateValidator = eachField.getAnnotation(DateValidator.class);
        String input = null;
        if (dateValidator!=null){
            input = (String) data.getData(eachField);
            if (!StringUtils.isEmpty(input))
            {
                String dateFormat = dateValidator.dateFormat();
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
               try{
                   sdf.parse(input);
               }
               catch (ParseException e){
                   isValid = false;
               }

            }
        }
        return isValid?Optional.empty():Optional.of(new ValidationResult(eachField.getName(), ErrorType.DATE_FORMAT_ERROR,input,position));
    }
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
