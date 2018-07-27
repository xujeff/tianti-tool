package com.jeff.tianti.tool.redis.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json操作工具类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
public class JsonUtils {

	public static ObjectMapper mapper;

	public static class Test {
		public Date now = new Date();
	}

	static {
		mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static String toJson(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T fromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, clazz);
	}

	public static <T> List<T> toList(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
	}

	public static <T> List<T> toListHasCatch(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <K, V> Map<K, V> toMap(String json, Class<K> keyClazz, Class<V> ValueClazz)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, mapper.getTypeFactory().constructMapType(HashMap.class, keyClazz, ValueClazz));
	}

	public static Object fromJson(String json, TypeReference<?> typeReference)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, typeReference);
	}

	public static Object fromJson(String json, JavaType javaType) {
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			throw new RuntimeException("JSON反序列化出错");
		}
	}

	public static String jsonArrayToString(String json) {
		return json.replaceAll("\\[", "").replaceAll("]", "").replaceAll("\"", "").replaceAll("\\{", "").replaceAll("}",
				"");
	}

	public static String jsonArrayToStringTwo(String json) {
		return json.replaceAll("\"", "");
	}

}
