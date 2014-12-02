package com.danier.solr;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;

import com.danier.solr.solrcloud.SolrCloudServerImpl;
import com.danier.solr.solrcloud.SolrMutilCoreServerImpl;
import com.danier.solr.test.bean.Article;
import com.danier.solr.test.bean.User;

public class MutilCoreAddIndexTest {

    public static void main(String[] args) {
        SolrQuery params = new SolrQuery();
        params.set("q", "*:*");
        params.set("group", "true");
        params.set("group.field", "actName");
        params.set("group.offset", "0");
        params.set("group.limit", "10");
        // solr云
//        SolrMutilCoreServerImpl core0 = SolrMutilCoreServerImpl.getInstance("core0");
//        for (int i = 0; i < 6; i++) {
//            core0.deleteIndexById("5422928b28b2add61204589a" + i);
//        }
//        for (int i = 0; i < 6; i++) {
//            Article article1 = new Article();
//            article1.setId("5422928b28b2add61204589a" + i);
//            article1.setActName("article1Title" + i);
//            article1.setActContent("我爱中国 中国爱我！" + i);
//            core0.buildIndex(article1);
//        }
//        List<Article> list1 = core0.getListGroup(Article.class, params);
//        for (Article article2 : list1) {
//            System.out.println(article2);
//        }
        System.out.println("-----------------");
//        SolrMutilCoreServerImpl core1 = SolrMutilCoreServerImpl.getInstance("core1");
//        for (int i = 0; i < 6; i++) {
//            User u = new User();
//            u.setId("542292832432a" + i);
//            u.setUserName("userTitle" + i);
//            u.setUserAddress("浙江省杭州市！" + i);
//            core1.buildIndex(u);
//        }
//        List<User> list2 = core1.getListGroup(User.class, params);
//        for (User u : list2) {
//            System.out.println(u);
//        }
        // solrCloudServerImpl.deleteIndexById("5422928b28b2add61204589a");
        // solrCloudServerImpl.deleteIndexById("5422928b28b2add61204584a");
        // solrCloudServerImpl.deleteIndexById("5422928b28b2add6120ssa");
        // SolrQuery params = new SolrQuery();
        // params.set("q", "*:*");
        // params.set("group", "true");
        // params.set("group.field", "category_code");
        // params.set("start", "0");
        // params.set("rows", "10");
        // params.set("group.offset", "0");
        // params.set("group.limit", "2");
        // System.err.println(params.getQuery());
        // List<SolrTaobaoItemInfo> list = solrCloudServerImpl.getListGroup(SolrTaobaoItemInfo.class, params);
        // for (SolrTaobaoItemInfo solrTaobaoItemInfo : list) {
        // System.err.println(solrTaobaoItemInfo.getTitle());
        // }
        
        SolrQuery params1 = new SolrQuery();
        params1.set("q", "*:*");
        SolrMutilCoreServerImpl core0 = SolrMutilCoreServerImpl.getInstance("core0");
        List<Article> list3 = core0.getList(Article.class, params1);
        for (Article article : list3) {
            System.out.println(article);
        }
    }
}
