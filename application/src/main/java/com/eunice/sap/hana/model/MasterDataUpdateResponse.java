package com.eunice.sap.hana.model;
/**
 * Created by roychoud on 18 Dec 2019.
 */

import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.batch.BatchResponse;

/**
 * History:
 * <ul>
 * <li> 18 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public class MasterDataUpdateResponse
{
    private int updateCount;

    public MasterDataUpdateResponse(BatchResponse response){
        //TODO To implement based on batch response
        updateCount = -1;
    }
}
