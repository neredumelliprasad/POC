package com.eunice.sap.hana.model;
/**
 * Created by roychoud on 30 Dec 2019.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Field;

/**
 * History:
 * <ul>
 * <li> 30 Dec 2019 : roychoud - Created
 * </ul>
 *
 * @authors roychoud : Arunava Roy Choudhury
 * © 2019 HERE
 */
public abstract class Validable
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String toString() {
        try{
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        }
        catch (Exception e){
            return "No String representation could be created!";
        }
    }

    public Object getData(Field field){
        try{
            return field.get(this);
        }
        catch (IllegalAccessException e){
            throw new RuntimeException("Error while accessing field",e);
        }
    }
}
