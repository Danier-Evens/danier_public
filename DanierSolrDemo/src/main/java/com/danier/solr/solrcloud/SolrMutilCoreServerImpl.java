package com.danier.solr.solrcloud;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.CloudSolrServer;

import com.danier.solr.server.AbstractSolrServer;
import com.danier.solr.server.Constants;

public class SolrMutilCoreServerImpl extends AbstractSolrServer {

    @SuppressWarnings("unused")
    private Logger log = Logger.getLogger(getClass());

    private CloudSolrServer solrServer;

    private String defaultCollection = "collection1";

    private static SolrMutilCoreServerImpl instance;

    private SolrMutilCoreServerImpl(String defaultCollection) {
        this.defaultCollection = defaultCollection;
        getSolrServer();
    }

    public static SolrMutilCoreServerImpl getInstance(String defaultCollection) {
        if (instance == null) {
            instance = new SolrMutilCoreServerImpl(defaultCollection);
        }
        return instance;
    }

    @Override
    public SolrServer getSolrServer() {
        if (solrServer == null) {
            solrServer = new CloudSolrServer(Constants.SOLR_CLOUD_URL);
            solrServer.setDefaultCollection(defaultCollection);
            solrServer.connect();
        }
        return solrServer;
    }

    public String getDefaultCollection() {
        return defaultCollection;
    }

    public void setDefaultCollection(String defaultCollection) {
        this.defaultCollection = defaultCollection;
    }
}
