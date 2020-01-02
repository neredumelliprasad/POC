package com.eunice.sap.hana.model;

import com.eunice.sap.hana.service.Validable;
import java.lang.reflect.Field;

/**
 * Created by vmullapu on 14/12/19.
 */
public class RawProductData extends Validable
{

    @MandatoryField
    @NumberValidator
    String number;
    @MandatoryField
    String material;
    String industrySector;
    String materialType;
    @NumberValidator
    String plant;
    String storeLocation;
    @NumberValidator
    String salesOrg;
    String distributionChannel;
    String productDescription;
    String baseUnitOfMeasure;
    String productGroup;
    @MandatoryField
    String oldMaterialNumber;
    String division;
    String crossPlantMaterialStatus;
    @DateValidator
    String validFromPlantMaterial;
    String weightUnit;
    @MandatoryField
    String pageFormat;
    @MandatoryField
    String classType;
    String classNumber;
    @NumberValidator
    String entry;
    String salesUnit;
    @NumberValidator
    String deliveryPlantSpecStatus;
    @NumberValidator
    String taxClassification;
    String itemCategoryGroup;
    @MandatoryField
    String productHierarchy;
    String materialGroup;
    String availabilityCheck;
    @MandatoryField
    String batchManagement;
    @MandatoryField
    String batchManagementPlant;
    String transportationGroup;
    String loadingGroup;
    @MandatoryField
    String profitCenter;
    String serialNoProfile;
    String countryOfOrigin;
    String plantSpecificMaterialStatus;
    @DateValidator
    String validFromCrossPlantMaterial;
    @NumberValidator
    String goodsReceiptProcessingTime;
    String mrpGroup;
    String mrpType;
    @NumberValidator
    String planningTimeFence;
    String mrpController;
    String lotSizingProcedure;
    String procurementType;
    @NumberValidator
    String specialProcurement;
    String productStoreLocation;
    @NumberValidator
    String inhouseProductionTime;
    String schedulingMarginKey;
    @NumberValidator
    String strategyGroup;
    @NumberValidator
    String consumptionMode;
    @NumberValidator
    String backrwardConsumptionPeriod;
    @NumberValidator
    String forwardConsumptionPeriod;
    @NumberValidator
    String mixedMRP;
    @NumberValidator
    String individualCollectiveRequirements;
    @NumberValidator
    String mrpDependentRequirements;
    String productionSchedulingProfile;
    @NumberValidator
    String minimumRemainingShelfLife;
    @NumberValidator
    String totalShelfLife;
    String expirationDate;
    String valuationClass;
    @NumberValidator
    String priceDeterminationControl;
    @NumberValidator
    String priceUnit;
    String priceControl;
    @MandatoryField
    String withQuantityStructure;
    @MandatoryField
    String materialOrigin;
    String overheadGroup;
    @NumberValidator
    String costingLotSize;
    String language;
    String materialDescription;
    @NumberValidator
    String qunatityDenominator;
    String aun;
    @NumberValidator
    String qunatityNumerator;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(String industrySector) {
        this.industrySector = industrySector;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getSalesOrg() {
        return salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getDistributionChannel() {
        return distributionChannel;
    }

    public void setDistributionChannel(String distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getBaseUnitOfMeasure() {
        return baseUnitOfMeasure;
    }

    public void setBaseUnitOfMeasure(String baseUnitOfMeasure) {
        this.baseUnitOfMeasure = baseUnitOfMeasure;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getOldMaterialNumber() {
        return oldMaterialNumber;
    }

    public void setOldMaterialNumber(String oldMaterialNumber) {
        this.oldMaterialNumber = oldMaterialNumber;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCrossPlantMaterialStatus() {
        return crossPlantMaterialStatus;
    }

    public void setCrossPlantMaterialStatus(String crossPlantMaterialStatus) {
        this.crossPlantMaterialStatus = crossPlantMaterialStatus;
    }

    public String getValidFromPlantMaterial() {
        return validFromPlantMaterial;
    }

    public void setValidFromPlantMaterial(String validFromPlantMaterial) {
        this.validFromPlantMaterial = validFromPlantMaterial;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getPageFormat() {
        return pageFormat;
    }

    public void setPageFormat(String pageFormat) {
        this.pageFormat = pageFormat;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getDeliveryPlantSpecStatus() {
        return deliveryPlantSpecStatus;
    }

    public void setDeliveryPlantSpecStatus(String deliveryPlantSpecStatus) {
        this.deliveryPlantSpecStatus = deliveryPlantSpecStatus;
    }

    public String getTaxClassification() {
        return taxClassification;
    }

    public void setTaxClassification(String taxClassification) {
        this.taxClassification = taxClassification;
    }

    public String getItemCategoryGroup() {
        return itemCategoryGroup;
    }

    public void setItemCategoryGroup(String itemCategoryGroup) {
        this.itemCategoryGroup = itemCategoryGroup;
    }

    public String getProductHierarchy() {
        return productHierarchy;
    }

    public void setProductHierarchy(String productHierarchy) {
        this.productHierarchy = productHierarchy;
    }

    public String getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }

    public String getAvailabilityCheck() {
        return availabilityCheck;
    }

    public void setAvailabilityCheck(String availabilityCheck) {
        this.availabilityCheck = availabilityCheck;
    }

    public String getBatchManagement() {
        return batchManagement;
    }

    public void setBatchManagement(String batchManagement) {
        this.batchManagement = batchManagement;
    }

    public String getBatchManagementPlant() {
        return batchManagementPlant;
    }

    public void setBatchManagementPlant(String batchManagementPlant) {
        this.batchManagementPlant = batchManagementPlant;
    }

    public String getTransportationGroup() {
        return transportationGroup;
    }

    public void setTransportationGroup(String transportationGroup) {
        this.transportationGroup = transportationGroup;
    }

    public String getLoadingGroup() {
        return loadingGroup;
    }

    public void setLoadingGroup(String loadingGroup) {
        this.loadingGroup = loadingGroup;
    }

    public String getProfitCenter() {
        return profitCenter;
    }

    public void setProfitCenter(String profitCenter) {
        this.profitCenter = profitCenter;
    }

    public String getSerialNoProfile() {
        return serialNoProfile;
    }

    public void setSerialNoProfile(String serialNoProfile) {
        this.serialNoProfile = serialNoProfile;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getPlantSpecificMaterialStatus() {
        return plantSpecificMaterialStatus;
    }

    public void setPlantSpecificMaterialStatus(String plantSpecificMaterialStatus) {
        this.plantSpecificMaterialStatus = plantSpecificMaterialStatus;
    }

    public String getValidFromCrossPlantMaterial() {
        return validFromCrossPlantMaterial;
    }

    public void setValidFromCrossPlantMaterial(String validFromCrossPlantMaterial) {
        this.validFromCrossPlantMaterial = validFromCrossPlantMaterial;
    }

    public String getGoodsReceiptProcessingTime() {
        return goodsReceiptProcessingTime;
    }

    public void setGoodsReceiptProcessingTime(String goodsReceiptProcessingTime) {
        this.goodsReceiptProcessingTime = goodsReceiptProcessingTime;
    }

    public String getMrpGroup() {
        return mrpGroup;
    }

    public void setMrpGroup(String mrpGroup) {
        this.mrpGroup = mrpGroup;
    }

    public String getMrpType() {
        return mrpType;
    }

    public void setMrpType(String mrpType) {
        this.mrpType = mrpType;
    }

    public String getPlanningTimeFence() {
        return planningTimeFence;
    }

    public void setPlanningTimeFence(String planningTimeFence) {
        this.planningTimeFence = planningTimeFence;
    }

    public String getMrpController() {
        return mrpController;
    }

    public void setMrpController(String mrpController) {
        this.mrpController = mrpController;
    }

    public String getLotSizingProcedure() {
        return lotSizingProcedure;
    }

    public void setLotSizingProcedure(String lotSizingProcedure) {
        this.lotSizingProcedure = lotSizingProcedure;
    }

    public String getProcurementType() {
        return procurementType;
    }

    public void setProcurementType(String procurementType) {
        this.procurementType = procurementType;
    }

    public String getSpecialProcurement() {
        return specialProcurement;
    }

    public void setSpecialProcurement(String specialProcurement) {
        this.specialProcurement = specialProcurement;
    }

    public String getProductStoreLocation() {
        return productStoreLocation;
    }

    public void setProductStoreLocation(String productStoreLocation) {
        this.productStoreLocation = productStoreLocation;
    }

    public String getInhouseProductionTime() {
        return inhouseProductionTime;
    }

    public void setInhouseProductionTime(String inhouseProductionTime) {
        this.inhouseProductionTime = inhouseProductionTime;
    }

    public String getSchedulingMarginKey() {
        return schedulingMarginKey;
    }

    public void setSchedulingMarginKey(String schedulingMarginKey) {
        this.schedulingMarginKey = schedulingMarginKey;
    }

    public String getStrategyGroup() {
        return strategyGroup;
    }

    public void setStrategyGroup(String strategyGroup) {
        this.strategyGroup = strategyGroup;
    }

    public String getConsumptionMode() {
        return consumptionMode;
    }

    public void setConsumptionMode(String consumptionMode) {
        this.consumptionMode = consumptionMode;
    }

    public String getBackrwardConsumptionPeriod() {
        return backrwardConsumptionPeriod;
    }

    public void setBackrwardConsumptionPeriod(String backrwardConsumptionPeriod) {
        this.backrwardConsumptionPeriod = backrwardConsumptionPeriod;
    }

    public String getForwardConsumptionPeriod() {
        return forwardConsumptionPeriod;
    }

    public void setForwardConsumptionPeriod(String forwardConsumptionPeriod) {
        this.forwardConsumptionPeriod = forwardConsumptionPeriod;
    }

    public String getMixedMRP() {
        return mixedMRP;
    }

    public void setMixedMRP(String mixedMRP) {
        this.mixedMRP = mixedMRP;
    }

    public String getIndividualCollectiveRequirements() {
        return individualCollectiveRequirements;
    }

    public void setIndividualCollectiveRequirements(String individualCollectiveRequirements) {
        this.individualCollectiveRequirements = individualCollectiveRequirements;
    }

    public String getMrpDependentRequirements() {
        return mrpDependentRequirements;
    }

    public void setMrpDependentRequirements(String mrpDependentRequirements) {
        this.mrpDependentRequirements = mrpDependentRequirements;
    }

    public String getProductionSchedulingProfile() {
        return productionSchedulingProfile;
    }

    public void setProductionSchedulingProfile(String productionSchedulingProfile) {
        this.productionSchedulingProfile = productionSchedulingProfile;
    }

    public String getMinimumRemainingShelfLife() {
        return minimumRemainingShelfLife;
    }

    public void setMinimumRemainingShelfLife(String minimumRemainingShelfLife) {
        this.minimumRemainingShelfLife = minimumRemainingShelfLife;
    }

    public String getTotalShelfLife() {
        return totalShelfLife;
    }

    public void setTotalShelfLife(String totalShelfLife) {
        this.totalShelfLife = totalShelfLife;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getValuationClass() {
        return valuationClass;
    }

    public void setValuationClass(String valuationClass) {
        this.valuationClass = valuationClass;
    }

    public String getPriceDeterminationControl() {
        return priceDeterminationControl;
    }

    public void setPriceDeterminationControl(String priceDeterminationControl) {
        this.priceDeterminationControl = priceDeterminationControl;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceControl() {
        return priceControl;
    }

    public void setPriceControl(String priceControl) {
        this.priceControl = priceControl;
    }

    public String getWithQuantityStructure() {
        return withQuantityStructure;
    }

    public void setWithQuantityStructure(String withQuantityStructure) {
        this.withQuantityStructure = withQuantityStructure;
    }

    public String getMaterialOrigin() {
        return materialOrigin;
    }

    public void setMaterialOrigin(String materialOrigin) {
        this.materialOrigin = materialOrigin;
    }

    public String getOverheadGroup() {
        return overheadGroup;
    }

    public void setOverheadGroup(String overheadGroup) {
        this.overheadGroup = overheadGroup;
    }

    public String getCostingLotSize() {
        return costingLotSize;
    }

    public void setCostingLotSize(String costingLotSize) {
        this.costingLotSize = costingLotSize;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getQunatityDenominator() {
        return qunatityDenominator;
    }

    public void setQunatityDenominator(String qunatityDenominator) {
        this.qunatityDenominator = qunatityDenominator;
    }

    public String getAun() {
        return aun;
    }

    public void setAun(String aun) {
        this.aun = aun;
    }

    public String getQunatityNumerator() {
        return qunatityNumerator;
    }

    public void setQunatityNumerator(String qunatityNumerator) {
        this.qunatityNumerator = qunatityNumerator;
    }


    @Override
    public Object getData(Field field)
    {
        try{
            return field.get(this);
        }
        catch (IllegalAccessException e){
            throw new RuntimeException("Error while accessing field",e);
        }
    }
}
