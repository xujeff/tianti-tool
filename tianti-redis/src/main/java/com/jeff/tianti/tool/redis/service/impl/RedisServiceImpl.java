package com.jeff.tianti.tool.redis.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jeff.tianti.tool.redis.service.RedisService;
import com.jeff.tianti.tool.redis.util.JsonUtils;

/**
 * redis服务类
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
@Service
public class RedisServiceImpl<K, V> implements RedisService<K, V> {

	@Autowired
	private RedisTemplate<K, V> redisTemplate;

	/**
	 * 设值操作
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean set(K key, V value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
		        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        String valueJson = JsonUtils.toJson(value);
				connection.set(serializer.serialize(keyJson), serializer.serialize(valueJson));
				return true;
			}
		});
		return result;
	}

	/**
	 * 设值操作（含过期时间）
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public boolean set(K key, V value, long expire) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
		        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        String valueJson = JsonUtils.toJson(value);
				connection.set(serializer.serialize(keyJson), serializer.serialize(valueJson));
				connection.expire(serializer.serialize(keyJson), expire);
				return true;
			}
		});
		return result;
	}
	
	/**
	 * 获取值
	 * @param key
	 * @param cls
	 * @return
	 */
	@Override
	public V get(K key, Class<V> cls) {
		V v = redisTemplate.execute(new RedisCallback<V>() {
			@Override
			public V doInRedis(RedisConnection connection) throws DataAccessException {
				
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        byte[] bytes =  connection.get(serializer.serialize(keyJson));
		        String valueJson = serializer.deserialize(bytes);
		        if(StringUtils.isNotBlank(valueJson)){
		        	try {
						return JsonUtils.fromJson(valueJson, cls);
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }
		        return null;
			}
		});
		return v;
	}

	/**
     * 设置过期时间
     * @param key
     * @param expire 单位：秒
     * @return
     */
	@Override
	public boolean expire(K key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	/**
     * 设置list值
     * @param key
     * @param list
     * @return
     */
	@Override
	public boolean setList(K key, List<V> list) {
		Long result = redisTemplate.opsForList().leftPushAll(key, list);
		return (result != null && result > 0) ? true : false;
	}

	/**
     * 获取范围内的值
     * @param key
     * @param start
     * @param end
     * @return
     */
	@Override
	public List<V> getList(K key, int start, int end) {
		return redisTemplate.opsForList().range(key, start, end);
	}

	 /**
     * 将值插入到头部
     * @param key
     * @param value
     * @return
     */
	@Override
	public long lpush(K key, V value) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        String valueJson = JsonUtils.toJson(value);
				return connection.lPush(serializer.serialize(keyJson), 
						serializer.serialize(valueJson));
			}
		});
		return result;
	}

	/**
     * 将值插入到尾部
     * @param key
     * @param value
     * @return
     */
	@Override
	public long rpush(K key, V value) {
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        String valueJson = JsonUtils.toJson(value);
				return connection.rPush(serializer.serialize(keyJson), 
						serializer.serialize(valueJson));
			}
		});
		
		return result;
	}

	/**
     * 移除并获取key对应的值
     * @param key
     * @return
     */
	@Override
	public String lpop(K key) {
		String v = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        byte[] bytes =  connection.lPop(serializer.serialize(keyJson));
		        String valueJson = serializer.deserialize(bytes);
		        return valueJson;
			}
		});
		return v;
	}

	 /**
     * 删除键值为key的对象
     * @param key
     * @return
     */
	@Override
	public boolean remove(K key) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		        String keyJson = JsonUtils.toJson(key);
		        byte[] bytes =  connection.get(serializer.serialize(keyJson));
				connection.del(bytes);
				return true;
			}
		});
		return result;
	}
	
	 /**
     * 批量删除操作
     * @param pattern
     */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void batchRemove(K pattern){
		Set<K> keys = redisTemplate.keys(pattern);
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			K key = (K) iterator.next();
			remove(key);
		}
	}

	 /**
     * 批量获取List
     * @param keyList
     * @param clazz
     * @return
     */
	@Override
	public List<V> multiGet(List<K> keyList, Class<V> clazz) {
		if(keyList == null || keyList.isEmpty()){
			return null;
		}
		List<V> vs = redisTemplate.execute(new RedisCallback<List<V>>() {
			@Override
			public List<V> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				int i = 0;
				byte[][] byteArray = new byte[keyList.size()][];
				for(K key : keyList){
					String keyJson = JsonUtils.toJson(key);
					byteArray[i++] = serializer.serialize(keyJson);
				}
		        List<byte[]> bytesList = connection.mGet(byteArray);
		        List<V> resultList = new ArrayList<V>();
		        for(byte[] bytes : bytesList){
		        	String valueJson = serializer.deserialize(bytes);			        
			        if(StringUtils.isNotBlank(valueJson)){
			        	try {
							V v = JsonUtils.fromJson(valueJson, clazz);
							resultList.add(v);
						} catch (JsonParseException e) {
							e.printStackTrace();
						} catch (JsonMappingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
		        }
		        return resultList;
			}
		});
		return vs;
	}

	/**
     * 批量获取Set
     * @param keySet
     * @param clazz
     * @return
     */
	@Override
	public Map<K, V> multiGet(Set<K> keySet, Class<V> clazz) {
		if(keySet == null || keySet.isEmpty()){
			return null;
		}
		Map<K, V> vMap = redisTemplate.execute(new RedisCallback<Map<K, V>>() {
			@Override
			public Map<K, V> doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				int i = 0;
				byte[][] byteArray = new byte[keySet.size()][];
				for(K key : keySet){
					String keyJson = JsonUtils.toJson(key);
					byteArray[i++] = serializer.serialize(keyJson);
				}
		        List<byte[]> bytesList = connection.mGet(byteArray);
		        Iterator<K> itor = keySet.iterator();
		        Map<K, V> map = new HashMap<K, V>();
		        for(byte[] bytes : bytesList){
		        	K key = itor.next();
		        	V value = null;
		        	String valueJson = serializer.deserialize(bytes);			        
			        if(StringUtils.isNotBlank(valueJson)){
			        	try {
			        		value = JsonUtils.fromJson(valueJson, clazz);
						} catch (JsonParseException e) {
							e.printStackTrace();
						} catch (JsonMappingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
			        }
			        map.put(key, value);
		        }
		        return map;
			}
		});
		return vMap;
	}

}
