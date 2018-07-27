package com.jeff.tianti.tool.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis服务接口
 * 
 * @author Jeff Xu
 * @since 2018-07-18
 */
public interface RedisService<K, V> {

	/**
	 * 设值操作
	 * @param key
	 * @param value
	 * @return
	 */
	boolean set(K key, V value);
	
	/**
	 * 设值操作（含过期时间）
	 * @param key
	 * @param value
	 * @param expire	秒
	 * @return
	 */
	boolean set(K key, V value, long expire);  
    
	/**
	 * 获取值
	 * @param key
	 * @param cls
	 * @return
	 */
    V get(K key, Class<V> cls);  
      
    /**
     * 设置过期时间
     * @param key
     * @param expire
     * @return
     */
    boolean expire(K key,long expire);  
      
    /**
     * 设置list值
     * @param key
     * @param list
     * @return
     */
    boolean setList(K key ,List<V> list);  
      
    /**
     * 获取范围内的值
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<V> getList(K key, int start, int end);  
      
    /**
     * 将值插入到头部
     * @param key
     * @param value
     * @return
     */
    long lpush(K key, V value);  
      
    /**
     * 将值插入到尾部
     * @param key
     * @param value
     * @return
     */
    long rpush(K key, V value);  
      
    /**
     * 移除并获取key对应的值
     * @param key
     * @return
     */
    String lpop(K key);

    /**
     * 删除键值为key的对象
     * @param key
     * @return
     */
    boolean remove(K key);

    /**
     * 批量删除操作
     * @param pattern
     */
    void batchRemove(K pattern);
    
    /**
     * 批量获取List
     * @param keyList
     * @param clazz
     * @return
     */
    List<V> multiGet(List<K> keyList, Class<V> clazz);
    
    /**
     * 批量获取Set
     * @param keySet
     * @param clazz
     * @return
     */
    Map<K, V> multiGet(Set<K> keySet, Class<V> clazz);

}
