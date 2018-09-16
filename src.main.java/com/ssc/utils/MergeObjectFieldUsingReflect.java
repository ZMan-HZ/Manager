package com.ssc.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;


/**
 * This is to backup the function used reflect, this class is not in use, replaced by BeanUtilsCustom
 * @author e633229
 *
 */


public class MergeObjectFieldUsingReflect {

	private static Logger logger = Logger.getLogger(MergeObjectFieldUsingReflect.class);
    
    
	
	//Copied from FileTransferController
  public static void mergeObject(Object source, Object target) {
  	logger.info("====== Merge DB with Web Information ====");
		Field[] sourceFields = source.getClass().getSuperclass().getDeclaredFields();
		Field[] targetFields = target.getClass().getSuperclass().getDeclaredFields();
		for (Field srcField : sourceFields) {
			srcField.setAccessible(true);
			try {
				Object value = srcField.get(source);
				List<Field> targetFieldsList =  Arrays.asList(targetFields);
				Field targetField = targetFieldsList.get(targetFieldsList.indexOf(srcField));
//				for(Field targetField : targetFields){
					targetField.setAccessible(true);
					if (srcField.getName().equals(targetField.getName())) {
						if (null != value && value.toString().length() != 0) {
							targetField.set(target, value);
//							break;
						}
//					}
				}
				targetField.setAccessible(false);
			} catch (Exception e) {
				 logger.error(srcField, e);
			}
			srcField.setAccessible(false);
			continue;
		}
	}
  
	

	// Using reflect to copy FilterBean each not NULL property from session to filterBean
    // Below three copied from StatusController
	public static void mergeFilterObject(Object target, Object source) {
		Field[] sourceFields = source.getClass().getDeclaredFields();
		for (Field field : sourceFields) {
			try {
				field.setAccessible(true);
				Object value = field.get(source);
				String fieldName = field.getName();
//				if(isFieldContainsHideOrIsFilter(fieldName) && !"isFilter".equalsIgnoreCase(fieldName))
				{
					if (null != value && value.toString().length() != 0) {
						field.set(target, value);
					}
				}
				field.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	
	public static void mergeObjectExcludeHideAndIS(Object target, Object source) {
		Field[] sourceFields = source.getClass().getDeclaredFields();
		for (Field field : sourceFields) {
			try {
				field.setAccessible(true);
				Object value = field.get(source);
				String fieldName = field.getName();
//				if(!isFieldContainsHideOrIsFilter(fieldName))
				{
					if (null != value && value.toString().length() != 0) {
						field.set(target, value);
					}
				}
				field.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	
	public static void mergeEachHideCloumns(Object target,Object source){
		Field[] sourceFields = source.getClass().getDeclaredFields();
		for (Field field : sourceFields) {
			try {
				field.setAccessible(true);
				Object value = field.get(source);
				String fieldName = field.getName();
				if(fieldName.contains("hide")  && !"isFilter".equalsIgnoreCase(fieldName)){
					if (null != value && value.toString().length() != 0) {
						field.set(target, value);
					}
				}
				field.setAccessible(false);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
	}
	
	
	
	
	
	
	
	
	
}
