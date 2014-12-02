package com.danier.solr.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public abstract class AbstractSolrServer implements SolrServers {

    private static Logger log = Logger.getLogger(AbstractSolrServer.class);

    @Override
    public abstract SolrServer getSolrServer();

    @Override
    public QueryResponse getResponse(SolrQuery query) {
        try {
            // 查询时间限制
            // query.setTimeAllowed(2000);
            QueryResponse response = getSolrServer().query(query);
            return response;
        } catch (SolrServerException e) {
            log.error("SOLR QueryResponse is wrong!", e);
            return null;
        }
    }

    @Override
    public SolrDocumentList getQueryResult(SolrQuery query) {
        QueryResponse queryResponse = getResponse(query);
        if (queryResponse == null)
            return null;
        SolrDocumentList doc = queryResponse.getResults();
        return doc;
    }

    @Override
    public long getTotal(SolrQuery query) {
        query.setStart(0).setRows(0);
        SolrDocumentList documents = getQueryResult(query);
        if (documents == null)
            return 0l;
        return documents.getNumFound();
    }

    @Override
    public <T> List<T> getList(Class<T> clazz, QueryResponse res) {
        List<T> messageList = res.getBeans(clazz);
        return messageList;
    }

    @Override
    public <T> List<T> getList(Class<T> clazz, SolrQuery query) {
        // SolrDocumentList对象转化为 List<T>对象
        QueryResponse res = getResponse(query);
        return getList(clazz, res);
    }

    @Override
    public <T> List<T> getListGroup(Class<T> clazz, QueryResponse res) {
        List<T> list = new ArrayList<T>();
        SolrDocumentList results = res.getResults();
        List<GroupCommand> groupCommandList = res.getGroupResponse().getValues();
        for (GroupCommand groupCommand : groupCommandList) {
            List<Group> groups = groupCommand.getValues();
            for (Group group : groups) {
                List<T> groupList = getBeans(clazz, group.getResult());
                list.addAll(groupList);
            }
        }
        return list;
    }

    @Override
    public <T> List<T> getListGroup(Class<T> clazz, SolrQuery query) {
        QueryResponse res = getResponse(query);
        return getListGroup(clazz, res);
    }

    @Override
    public <T> List<T> getListGroup(Class<T> clazz, SolrQuery query, int count) {
        QueryResponse res = getResponse(query);
        List<T> list = new ArrayList<T>();
        List<GroupCommand> groupCommandList = res.getGroupResponse().getValues();
        for (GroupCommand groupCommand : groupCommandList) {
            List<Group> groups = groupCommand.getValues();
            int num = 0;
            int round = 0;
            while (num < count) {
                for (int i = 0; num < count && i < groups.size(); i++) {
                    SolrDocument solrDocument = groups.get(i).getResult().get(round);
                    list.add(getBean(clazz, solrDocument));
                    num++;
                }
                round++;
            }
        }
        return list;
    }

    private <T> List<T> getBeans(Class<T> type, SolrDocumentList _results) {
        return getSolrServer() == null ? new DocumentObjectBinder().getBeans(type, _results) : getSolrServer().getBinder()
                                                                                                              .getBeans(type,
                                                                                                                        _results);
    }

    private <T> T getBean(Class<T> clazz, SolrDocument solrDoc) {
        return getSolrServer() == null ? new DocumentObjectBinder().getBean(clazz, solrDoc) : getSolrServer().getBinder()
                                                                                                             .getBean(clazz,
                                                                                                                      solrDoc);
    }

    @Override
    public void activateHighlight(SolrQuery query, String param) {
        if (!StringUtils.isBlank(param)) {
            // 设置启用高亮
            query.setHighlight(true).setHighlightSnippets(3);
            // 设置合并
            query.setParam("hl", "true");
            // 设置高亮字段
            query.setParam("hl.fl", param); // 高亮title、content部分，以空格隔开
        }
    }

    @Override
    public <E> void buildIndex(E entity) {
        try {
            // 把所有文档添加到solr 实例中，缓存起来。
            getSolrServer().addBean(entity);
            // 把所有文档提交到solr索引库中
            getSolrServer().commit();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SOLR buildIndex is wrong!");
        }
    }

    @Override
    public <E> void buildIndex(List<E> list) {
        try {
            // 把所有文档添加到solr 实例中，缓存起来。
            getSolrServer().addBeans(list);
            // 把所有文档提交到solr索引库中
            getSolrServer().commit();
        } catch (Exception e) {
            log.error("SOLR buildIndex is wrong!");
        }
    }

    @Override
    public void deleteIndexById(String id) {
        try {
            // 缓存
            getSolrServer().deleteById(id);
            // 提交到索引库进行删除
            getSolrServer().commit();
        } catch (Exception e) {
            log.error("SOLR delete " + id + " is wrong!");
        }
    }

    @Override
    public void deleteByQuery(String query) {
        try {
            // 缓存
            getSolrServer().deleteByQuery(query);
            // 提交到索引库进行删除
            getSolrServer().commit();
        } catch (Exception e) {
            log.error("SOLR delete '" + query + "' is wrong!");
        }
    }
}
