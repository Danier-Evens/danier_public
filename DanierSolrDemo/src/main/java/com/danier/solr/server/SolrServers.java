package com.danier.solr.server;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public interface SolrServers {
	
	/**
	 * 获取SolrServer实例
	 * @return
	 */
	SolrServer getSolrServer();
	
	/**
	 * 获取QueryResponse实例
	 * @param query
	 * @return
	 * @throws SolrServerException
	 */
	QueryResponse getResponse(SolrQuery query);
	
	/**
	 * 获取SolrDocumentList实例
	 * @param query
	 * @return
	 * @throws SolrServerException
	 */
	SolrDocumentList getQueryResult(SolrQuery query);

	/**
	 * 获取查询数量
	 */
	long getTotal(SolrQuery query);
	
	/**
	 * 获取数据列表, 并转成对应数据结构
	 */
	<T> List<T> getList(Class<T> clazz,QueryResponse res);
	
	/**
	 * 获取数据列表, 并转成对应数据结构
	 */
	<T> List<T> getList(Class<T> clazz,SolrQuery query);
	
	/**
	 * 获取数据列表, 并转成对应数据结构(按组查询)
	 */
	<T> List<T> getListGroup(Class<T> clazz,QueryResponse res);
	
	/**
	 * 获取数据列表, 并转成对应数据结构(按组查询)
	 */
	<T> List<T> getListGroup(Class<T> clazz,SolrQuery query);
	
	<T> List<T> getListGroup(Class<T> clazz,SolrQuery query, int count);
	
	/**
	 * 建立索引
	 * @param doc
	 */
	<E> void buildIndex(E entity);
	
	/**
	 * 批量建立索引
	 * @param doc
	 */
	<E> void buildIndex(List<E> list);
	
	/**
	 * 根据id进行删除索引，solr 的deleteById（）函数，参数可以是单个id，也可以是一个集合。
	 * @param id
	 */
	void deleteIndexById(String id);
	
	/**
	 * 根据query语句进行删除索引
	 * @param id
	 */
	void deleteByQuery(String query);
	
	/**
	 * 是否激活高亮
	 * @param query
	 * @param index
	 * @param pagesize
	 * @param param	
	 */
	void activateHighlight(SolrQuery query, String param);
	
}
