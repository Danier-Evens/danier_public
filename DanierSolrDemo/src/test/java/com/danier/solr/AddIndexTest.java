package com.danier.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

import com.danier.solr.solrcloud.SolrCloudServerImpl;
import com.danier.solr.test.bean.Article;

public class AddIndexTest {

    public static void main(String[] args) {
        // SolrTaobaoItemInfo solrInfo = new SolrTaobaoItemInfo();
        // solrInfo.setId("5422928b28b2add61204584a");
        // SolrServerImpl server = new SolrServerImpl();
        // server.buildIndex(solrInfo);
        SolrQuery params = new SolrQuery();
        params.set("q", "*:*");
        params.set("group", "true");
        params.set("group.field", "actName");
        params.set("group.offset", "0");  
        params.set("group.limit", "10"); 
        //单节点solr
//        Article article = new Article();
//        article.setId("5422928b28b2add61204589a");
//        article.setActName("SolrCloud使用教程、原理介绍 | 我心动了");
//        article.setActContent("阿帕琦发展史：阿帕琦创始人郭素贞年轻时就涉足手表商业。开始时作手表批发与零售的生意，长时间的接触手表行业让她对手表行业更加了解，并萌生创业自己手表公司的想法。1993年，她创办了自己的企业，名为“新新精ik阿帕奇ik阿帕奇诚表业有限公司，是一家主要负责销售手表的公司，但她也研发自制手表。逐渐从销售手表公司转为研发手表公司。并于当年在香港注册“IK　colouring 阿帕琦”商标。1995年以来规模逐渐扩大，车间设为两大部门，表部与壳部。壳部主要负责表身设计，表面设计，表壳、表玻璃加工等。表部主要负责装表，出货。同时也设立相应的维修、QC检测、包装车间。随着新新精诚表业有限公司的加速发展，阿帕琦品牌手表也得到了快速的发展。从单一的款色发展成各同各样的的款色，深受各种不同人士的喜空的、有日历的、有潮流时尚的等等，也印证了“IK　colouring”多种色款的品牌商标设计理念。");
//        SolrServerImpl solrServerImpl = new SolrServerImpl();
//        solrServerImpl.getSolrServer();
//        solrServerImpl.buildIndex(article);
//        
//        List<Article> list = solrServerImpl.getListGroup(Article.class, params);
//        for (Article article2 : list) {
//            System.out.println(article2);
//        }
        System.out.println("--------------------------------------");
        //solr云
        SolrCloudServerImpl solrCloudServerImpl = SolrCloudServerImpl.getInstance();
//        for (int i = 0; i < 6; i++) {
//            Article article1 = new Article();
//            article1.setId("5422928b28b2add61204589a"+i);
//            article1.setActName("title"+i);
//            article1.setActContent("我爱中国 中国爱我！"+i);
//            solrCloudServerImpl.buildIndex(article1);
//        }
        
        List<Article> list1 = solrCloudServerImpl.getListGroup(Article.class, params);
        for (Article article2 : list1) {
            System.out.println(article2);
        }
        
//        solrCloudServerImpl.deleteIndexById("5422928b28b2add61204589a");
//        solrCloudServerImpl.deleteIndexById("5422928b28b2add61204584a");
//        solrCloudServerImpl.deleteIndexById("5422928b28b2add6120ssa");
//        SolrQuery params = new SolrQuery();
//        params.set("q", "*:*");
//        params.set("group", "true");
//        params.set("group.field", "category_code");
//        params.set("start", "0");
//        params.set("rows", "10");
//        params.set("group.offset", "0");
//        params.set("group.limit", "2");
//        System.err.println(params.getQuery());
//        List<SolrTaobaoItemInfo> list = solrCloudServerImpl.getListGroup(SolrTaobaoItemInfo.class, params);
//        for (SolrTaobaoItemInfo solrTaobaoItemInfo : list) {
//            System.err.println(solrTaobaoItemInfo.getTitle());
//        }
    }
}
