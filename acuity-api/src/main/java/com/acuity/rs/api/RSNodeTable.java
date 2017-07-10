package com.acuity.rs.api;


//Generated by the injector on run.

//Extends java/lang/Object
public interface RSNodeTable {

	com.acuity.rs.api.RSNodeQueue getQueue();

	com.acuity.rs.api.RSHashTable getTable();

	com.acuity.rs.api.RSCacheableNode invokeGet(long var0);

	void invokePut(com.acuity.rs.api.RSCacheableNode var0, long var1);

	void invokeRemove(long var0);

	void invokeReset();

	void setQueue(com.acuity.rs.api.RSNodeQueue var0);

	void setTable(com.acuity.rs.api.RSHashTable var0);
}
