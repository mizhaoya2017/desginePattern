package com.mzy.shejimoshi.PrototypePatternOfSelfBeanUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BeanUtil {
	@SuppressWarnings("rawtypes")
	public static void setPropertyAll(Map<String, Object> valueMap,Object object){
		Iterator iter = valueMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			Object val = entry.getValue();
			try {
				forceSetProperty(object, key, val);
			} catch (NoSuchFieldException e) {
				//Do nothing
			}
		}
	}
	public static Map<String, Object> getPropertyAllByArray(String[] keys,Object object){
		Map<String, Object> valueMap = new HashMap<String, Object>();
		for(String key : keys){
			try {
				valueMap.put(key, forceGetProperty(object, key));
			} catch (NoSuchFieldException e) {
				//do nothong
			}
		}
		return valueMap;
	}
	public static String upperCaseFirstChar(String iString){
	    String newString = iString.substring(0, 1).toUpperCase() + iString.substring(1);
	    return newString;
	}
	public static void forceSetProperty(Object object, String propertyName, Object newValue) throws NoSuchFieldException {
	    Field field = getDeclaredField(object, propertyName);
	    boolean accessible = field.isAccessible();
	    field.setAccessible(true);
	    try {
	    	field.set(object, newValue);
	    } catch (IllegalAccessException e) {
	    	e.printStackTrace();
	    }
	    field.setAccessible(accessible);
	}
	public static Object forceGetProperty(Object object, String propertyName)throws NoSuchFieldException{
	    Field field = getDeclaredField(object, propertyName);
	    boolean accessible = field.isAccessible();
	    field.setAccessible(true);
	    Object result = null;
	    try {
	      result = field.get(object);
	    } catch (IllegalAccessException e) {
	    	e.printStackTrace();
	    }
	    field.setAccessible(accessible);
	    return result;
	  }
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		return getDeclaredField(object.getClass(), propertyName);
	}

	@SuppressWarnings("rawtypes")
	public static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException {
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()){
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				//Do nothing
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}

	public static void simpleCopy(Object source, Object target, Boolean isCopyNull) {
		if ((target == null) || (source == null)) {
			return;
		}
		List targetMethodList = getSetter(target.getClass());
		List sourceMethodList = getGetter(source.getClass());
	}

	public static List<Method> getSetter(Class cl) {
		List list = new ArrayList();
		Method[] methods = cl.getDeclaredMethods();
		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];
			String methodName = method.getName();
			if (!(methodName.startsWith("set"))) {
				continue;
			}
			list.add(method);
		}
		while (true) {
			cl = cl.getSuperclass();
			if (cl == Object.class) {
				break;
			}
			list.addAll(getSetter(cl));
		}
		return list;
	}

	public static List<Method> getGetter(Class cl) {
		List list = new ArrayList();
		Method[] methods = cl.getDeclaredMethods();
		for (int i = 0; i < methods.length; ++i) {
			Method method = methods[i];
			String methodName = method.getName();
			if ((!(methodName.startsWith("get"))) && (!(methodName.startsWith("is")))) {
				continue;
			}
			list.add(method);
		}
		while (true) {
			cl = cl.getSuperclass();
			if (cl == Object.class) {
				break;
			}
			list.addAll(getGetter(cl));
		}
		return list;
	}
}
