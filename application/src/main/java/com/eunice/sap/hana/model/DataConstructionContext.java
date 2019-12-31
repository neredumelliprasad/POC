package com.eunice.sap.hana.model;
/**
 * Created by roychoud on 18 Dec 2019.
 */

import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * History:
 * <ul>
 * <li> 18 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public class DataConstructionContext
{
    private Map<String, Product> productMap = new ConcurrentHashMap<>();


    public Map<String, Product> getProductMap()
    {
        return productMap;
    }
}
