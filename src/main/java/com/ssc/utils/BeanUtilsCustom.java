package com.ssc.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class BeanUtilsCustom extends BeanUtils{

	/**
	 *  Copy the property values that not null of the given source bean into the given target bean.
	 * @param source
	 * @param target
	 * @throws BeansException
	 */
	public static void copyPropertiesCustom(Object source, Object target) throws BeansException {
		copyPropertiesCustom(source, target, null,null,(String[]) null);
	}
	
	public static void copyPropertiesCustom(Object source, Object target, Class<?> editable) throws BeansException {
		copyPropertiesCustom(source, target, editable,null, (String[]) null);
	}
	
	public static void copyPropertiesCustom(Object source, Object target, String... ignoreProperties) throws BeansException {
		copyPropertiesCustom(source, target,null,null,ignoreProperties);
	}
	
	public static void copyPropertiesCustom(Object source, Object target, @Nullable Class<?> editable, @Nullable String... ignoreProperties) throws BeansException {
		copyPropertiesCustom(source, target, editable,null,ignoreProperties);
	}
	
	public static void copyPropertiesCustom(Object source, Object target, @Nullable Boolean reverse,@Nullable String... ignoreProperties) throws BeansException {
		copyPropertiesCustom(source, target,null,reverse,ignoreProperties);
	}
	
	private static void copyPropertiesCustom(Object source, Object target, @Nullable Class<?> editable, @Nullable Boolean reverseIgnore, @Nullable String... ignoreProperties) throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");

		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			Boolean ignore = (ignoreList !=null && (reverseIgnore == null|| !reverseIgnore)) ? !ignoreList.contains(targetPd.getName()) : (ignoreList==null?true:ignoreList.contains(targetPd.getName()));
			if (writeMethod != null && (ignoreList == null || ignore)) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null &&
							ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							if(null !=value && value.toString().length() != 0){
								writeMethod.invoke(target, value);
							}
						}
						catch (Throwable ex) {
							throw new FatalBeanException( "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
						}
					}
				}
			}
		}
		
	}
	
	
}
