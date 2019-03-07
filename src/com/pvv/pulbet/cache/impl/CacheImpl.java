package com.pvv.pulbet.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pvv.pulbet.cache.Cache;

public class CacheImpl<K,V> implements Cache<K,V> {

	private String name = null;
	
	private Map<K,V> _cache = null;
	
	public CacheImpl(String name) {
		this.name = name;
		_cache = new HashMap<K,V>();
	}
	
	public String getName() {
		return name;
	}
	
	@Override	
	public void put(K k, V v) {
			_cache.put(k, v);
	}

	@Override
	public V get(K k) {
		return _cache.get(k);
	}

	@Override
	public void remove(K k) {
		_cache.remove(k);
		
	}

	@Override
	public void clear() {
		_cache.clear();
		
	}

}
