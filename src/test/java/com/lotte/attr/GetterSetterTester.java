package com.lotte.attr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.function.Supplier;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.objectpartners.GetterSetterPair;

public class GetterSetterTester<T> {
	T instance;
	
	private ImmutableMap<Class<?>, Supplier<?>> mappers;
	
	private Set<String> ignoredGetFields;

	public static class Builder<T> {
		T instance;
		
		private ImmutableMap<Class<?>, Supplier<?>> mappers;
		
		private Set<String> ignoredGetFields = new HashSet<String>();;
		
		private ImmutableMap<Class<?>, Supplier<?>> DEFAULT_MAPPERS;
		
		public Builder (T instance) {
			this.instance =  instance;
			
			final com.google.common.collect.ImmutableMap.Builder<Class<?>, Supplier<?>> mapperBuilder = ImmutableMap.builder();

			/* Primitives */
			mapperBuilder.put(int.class, () -> 0);
			mapperBuilder.put(double.class, () -> 0.0d);
			mapperBuilder.put(float.class, () -> 0.0f);
			mapperBuilder.put(long.class, () -> 0l);
			mapperBuilder.put(boolean.class, () -> true);
			mapperBuilder.put(short.class, () -> (short) 0);
			mapperBuilder.put(byte.class, () -> (byte) 0);
			mapperBuilder.put(char.class, () -> (char) 0);

			mapperBuilder.put(Integer.class, () -> Integer.valueOf(0));
			mapperBuilder.put(Double.class, () -> Double.valueOf(0.0));
			mapperBuilder.put(Float.class, () -> Float.valueOf(0.0f));
			mapperBuilder.put(Long.class, () -> Long.valueOf(0));
			mapperBuilder.put(Boolean.class, () -> Boolean.TRUE);
			mapperBuilder.put(Short.class, () -> Short.valueOf((short) 0));
			mapperBuilder.put(Byte.class, () -> Byte.valueOf((byte) 0));
			mapperBuilder.put(Character.class, () -> Character.valueOf((char) 0));

			mapperBuilder.put(BigDecimal.class, () -> BigDecimal.ONE);
			mapperBuilder.put(Date.class, () -> new Date());
			mapperBuilder.put(java.sql.Date.class, () -> new java.sql.Date(System.currentTimeMillis()));
			mapperBuilder.put(Timestamp.class, () -> new Timestamp(System.currentTimeMillis()));

			/* Collection Types. */
			mapperBuilder.put(Set.class, () -> Collections.emptySet());
			mapperBuilder.put(SortedSet.class, () -> Collections.emptySortedSet());
			mapperBuilder.put(List.class, () -> Collections.emptyList());
			mapperBuilder.put(Map.class, () -> Collections.emptyMap());
			mapperBuilder.put(SortedMap.class, () -> Collections.emptySortedMap());

			mappers = mapperBuilder.build();
		}
		
		public Builder<T> instance (T instance) {
			this.instance = instance;
			
			return this;
		}

		public Builder<T> mappers (Map<Class<?>, Supplier<?>> customMappers) {
			if (customMappers == null) {
				this.mappers = DEFAULT_MAPPERS;
			} else {
				final com.google.common.collect.ImmutableMap.Builder<Class<?>, Supplier<?>> builder = ImmutableMap.builder();
				builder.putAll(customMappers);
				builder.putAll(DEFAULT_MAPPERS);
				this.mappers = builder.build();
			}
			
			return this;
		}
		
		public Builder<T> ignoredGetFields (Set<String> ignoredGetFields) {
			if(ignoredGetFields != null) {
				this.ignoredGetFields = ignoredGetFields;
			}
			
			return this;
		}
		
		public GetterSetterTester<T> build() {
			return new GetterSetterTester<T>(this);
		}
	}
	
	private GetterSetterTester (Builder<T> builder) {
		this.instance = builder.instance;
		this.mappers = builder.mappers;
		this.ignoredGetFields = builder.ignoredGetFields;
	}
	

	private void callGetter(String fieldName, Method getter, T instance, Object expected)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		final Object getResult = getter.invoke(instance);

		if (getter.getReturnType().isPrimitive()) {
			assertEquals(fieldName + " is different", expected, getResult);
		} else {
			assertSame(fieldName + " is different", expected, getResult);
		}
	}

	private Object createObject(String fieldName, Class<?> clazz)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		try {
			final Supplier<?> supplier = this.mappers.get(clazz);
			if (supplier != null) {
				return supplier.get();
			}

			if (clazz.isEnum()) {
				return clazz.getEnumConstants()[0];
			}

//			return clazz.newInstance();
			return clazz.getDeclaredConstructor().newInstance();
		} catch (IllegalAccessException | InstantiationException e) {
			throw new RuntimeException("Unable to create objects for field '" + fieldName + "'.", e);
		}
	}

	@Test
	public void testGettersAndSetters() throws Exception {
		/* Sort items for consistent test runs. */
		final SortedMap<String, GetterSetterPair> getterSetterMapping = new TreeMap<>();

		for (final Method method : instance.getClass().getMethods()) {
			final String methodName = method.getName();

			if (this.ignoredGetFields.contains(methodName)) {
				continue;
			}

			String objectName;
			if (methodName.startsWith("get") && method.getParameters().length == 0) {
				/* Found the get method. */
				objectName = methodName.substring("get".length());

				GetterSetterPair getterSettingPair = getterSetterMapping.get(objectName);
				if (getterSettingPair == null) {
					getterSettingPair = new GetterSetterPair();
					getterSetterMapping.put(objectName, getterSettingPair);
				}
				getterSettingPair.setGetter(method);
			} else if (methodName.startsWith("set") && method.getParameters().length == 1) {
				/* Found the set method. */
				objectName = methodName.substring("set".length());

				GetterSetterPair getterSettingPair = getterSetterMapping.get(objectName);
				if (getterSettingPair == null) {
					getterSettingPair = new GetterSetterPair();
					getterSetterMapping.put(objectName, getterSettingPair);
				}
				getterSettingPair.setSetter(method);
			} else if (methodName.startsWith("is") && method.getParameters().length == 0) {
				/* Found the is method, which really is a get method. */
				objectName = methodName.substring("is".length());

				GetterSetterPair getterSettingPair = getterSetterMapping.get(objectName);
				if (getterSettingPair == null) {
					getterSettingPair = new GetterSetterPair();
					getterSetterMapping.put(objectName, getterSettingPair);
				}
				getterSettingPair.setGetter(method);
			}
		}

		/*
		 * Found all our mappings. Now call the getter and setter or set the field via
		 * reflection and call the getting it doesn't have a setter.
		 */
		for (final Entry<String, GetterSetterPair> entry : getterSetterMapping.entrySet()) {
			final GetterSetterPair pair = entry.getValue();

			final String objectName = entry.getKey();
			final String fieldName = objectName.substring(0, 1).toLowerCase() + objectName.substring(1);

			if (pair.hasGetterAndSetter()) {
				/* Create an object. */
				final Class<?> parameterType = pair.getSetter().getParameterTypes()[0];
				final Object newObject = createObject(fieldName, parameterType);

				pair.getSetter().invoke(instance, newObject);

				callGetter(fieldName, pair.getGetter(), instance, newObject);
			} else if (pair.getGetter() != null) {
				/*
				 * Object is immutable (no setter but Hibernate or something else sets it via
				 * reflection). Use reflection to set object and verify that same object is
				 * returned when calling the getter.
				 */
				final Object newObject = createObject(fieldName, pair.getGetter().getReturnType());
				final Field field = instance.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(instance, newObject);

				callGetter(fieldName, pair.getGetter(), instance, newObject);
			}
		}
	}
}