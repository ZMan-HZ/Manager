package com.ssc.cache;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;


public class CacheKeyGenerator  implements KeyGenerator {
	
	private static Logger logger = Logger.getLogger(CacheKeyGenerator.class);
	@Override
	    public Object generate(Object target, Method method, Object... params) {

		logger.info("CacheKey ==> "+target.getClass().getSimpleName() + "_"+ method.getName() + "_" + StringUtils.arrayToDelimitedString(params, "_"));
	    return target.getClass().getSimpleName() + "_"+ method.getName() + "_" + StringUtils.arrayToDelimitedString(params, "_")+params.hashCode();
	    }
	
	
}
