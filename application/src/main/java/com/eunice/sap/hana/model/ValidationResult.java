package com.eunice.sap.hana.model;
/**
 * Created by roychoud on 18 Dec 2019.
 */

/**
 * History:
 * <ul>
 * <li> 18 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public class ValidationResult
{
    private String fieldName;
    private ErrorType errorType;
    private Object data;
    private int recordPosition;


    public ValidationResult(String fieldName, ErrorType errorType, Object data, int recordPosition)
    {
        this.fieldName = fieldName;
        this.errorType = errorType;
        this.data = data;
        this.recordPosition = recordPosition;
    }


    public String getFieldName()
    {
        return fieldName;
    }


    public ErrorType getErrorType()
    {
        return errorType;
    }


    public Object getData()
    {
        return data;
    }


    public int getRecordPosition()
    {
        return recordPosition;
    }
}
