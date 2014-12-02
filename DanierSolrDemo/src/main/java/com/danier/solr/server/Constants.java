package com.danier.solr.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 通用配置  及方法 
 * @author Clark
 * 2013-10-14
 */
public class Constants{
	
    private static Logger log = Logger.getLogger(Constants.class);

    private static Properties props;
    
    /** 首页显示的条数 */
    public static final int HOME_ITEMS_SIZE = 12;

    /** 每页显示的条数 */
    public static final int PAGE_SIZE;
    
    /** 线程池大小 */
    public static final int POOL_SIZE;

    /** SOLR地址标记 */
    public static final String SOLR_URL;
    
    /** SOLRCLOUD地址标记 */
    public static final String SOLR_CLOUD_URL;

    /** 左菜单高亮标记 */
    public static final String MENU_MARK; 

    static{
    	init();
    	
    	PAGE_SIZE = Integer.parseInt(props.getProperty("PAGE_SIZE"));
        POOL_SIZE = Integer.parseInt(props.getProperty("POOL_SIZE"));
        MENU_MARK = props.getProperty("MENU_MARK");
        SOLR_URL = props.getProperty("SOLR_URL");
        SOLR_CLOUD_URL = props.getProperty("SOLR_CLOUD_URL");
    }
    
    /**
     * 读取配置文件
     */
    public static void init(){
    	try{
    		props = new Properties();
    		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("constants.properties");
            
    		if(in==null){
    			in = new FileInputStream(new File(System.getProperty("user.dir")+File.separator+"constants.properties"));
    		}
    		
    		props.load(in);
        }catch(IOException e){
            log.error("获取配置文件失败：" + e.getMessage(), e);
        }
    }
    
    /**
     * 判断该字符串是否URL形式
     * @param url
     * @return
     */
    public static boolean isURL(String url){
    	String regex = "^(https?|ftp|mailto|ldap|file|news|gopher|telnet)://"  // 协议
                + "((\\w{1,26})(\\.\\w{1,26}){1,3}|((\\w+|(\\d|[1-9]\\d|1\\d{2}|2([0-4]\\d|5[0-5]))(\\.(\\d|[1-9]\\d|1\\d{2}|2([0-4]\\d|5[0-5]))){3})"  // 域名|主机
                + ":(\\d|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6([0-4]\\d{3}|5([0-4]\\d{2}|5([0-2]\\d|3[0-5]))))))"  // 端口
                + "(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)" + "*$"; // 路径
    	
    	if(url.matches(regex)) return true;
    	return false;
    }
    
    /**
     * 获取指定日期（负数为前N天，正数为后N天）
     * @param days
     * @return
     */
    public static Date getBeginDate(int days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
    
    

    public static Date[] getLastSevenDays(Date date){
        Date[] d = new Date[2];
        d[1] = date;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0,
                        0, 0);
        d[0] = calendar.getTime();
        return d;
    }

    public static Date getNDays(Date date, int num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        if(num < 0)
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        else
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime();
    }

    public static Date[] initSearchTime(Date begin, Date end){
        if(begin == null){
            if(end == null){
                Date[] dates = getLastSevenDays(new Date());
                begin = dates[0];
                end = dates[1];
            }else{
                begin = getNDays(end, -6);
            }
        }else{
            if(end == null){
                end = getNDays(begin, 6);
            }else{
                begin = getNDays(end, -6);
            }
        }
        return new Date[] {begin, end};
    }

    public static Date[] dateDeal(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Date begin = Constants.getNDays(date, -6);
        System.out.println("---begin:" + sdf.format(begin));
        Date end = Constants.getNDays(date, 6);
        System.out.println("\n-----end:" + sdf.format(end));
        Date[] dates = Constants.initSearchTime(begin, end);
        return dates;
    }

    /**
     * 判断url合法性
     * 
     * @param url
     * @return
     */
    public static String getUrl(String url){
//        String regex0 = "^((https?|ftp|file)://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
//        String regex1 = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
//        String regex2 = "<\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>"; // matches
//                                                                                                        // <http://google.com>
//        String regex3 = "<^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]>"; // does not match
//                                                                                                      // <http://google.com>
//        String regex4 = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
//
//        String regex5 = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
//                        + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
//                        + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
//                        + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
//                        + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
//                        + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
//                        + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
//                        + "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
//
//        String s = "<URL的访问方式>://<主机>:<端口>/<路径>";

        String regex = "^(https?|ftp|mailto|ldap|file|news|gopher|telnet)://"  // 协议
                        + "((\\w{1,26})(\\.\\w{1,26}){1,3}|((\\w+|(\\d|[1-9]\\d|1\\d{2}|2([0-4]\\d|5[0-5]))(\\.(\\d|[1-9]\\d|1\\d{2}|2([0-4]\\d|5[0-5]))){3})"  // 域名|主机
                        + ":(\\d|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6([0-4]\\d{3}|5([0-4]\\d{2}|5([0-2]\\d|3[0-5]))))))"  // 端口
                        + "(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)" + "*$"; // 路径

//        String ss = "(/[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)";
        if(url.matches(regex))
            return url;
        return "";

    }
    
    /* 两类别匹配度：originCategory当前页面类别，targetCategory目标类别 */
    public static double matchDegree(String originCategory, String targetCategory){
        double match = 1;
        int end = originCategory.lastIndexOf("-");
        String second = "null";
        String first = "null";

        if(end > -1){// 当前类别不为顶级类别时，得到当前类别的上级类别类别
            second = originCategory.substring(0, end);
        }
        end = second.lastIndexOf("-");
        if(end > -1){// 当前类别的上级类别类别不为顶级类别时，得到当前类别的上级类别类别的上级类别
            first = second.substring(0, end);
        }

        if(targetCategory.startsWith(originCategory)){
            match = 1;// 若目标类别为当前类别或其子类别时，页面类别匹配度为1
        }else if(targetCategory.startsWith(second)){
            match *= 0.8;// 若目标类别与当前类别属于同一父级类别时，匹配度为80%
        }else if(targetCategory.startsWith(first)){
            match *= 0.5;// 若目标类别的父级类别与当前类别的父级类别属于同一父级类别时，匹配度为50%
        }else{
            match = 0;
        }
        return match;
    }
    
    /**
     * 专用于Solr查询特殊字符过滤
     */
    public static String searchStrFilter(String searchStr){
        StringBuffer sb = new StringBuffer();
        String regex = "[+\\-&|!(){}\\[\\]^\"~*?:(\\)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(searchStr);
        while(matcher.find()){
            matcher.appendReplacement(sb, "\\\\" + matcher.group());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    public static void main(String[] args){

        // String url =
        // "http://www.google.com.hk/#newwindow=1&safe=strict&q=java+url+regex&oq=java+url+regex&gs_l=serp.3...28302.28601.0.29162.2.2.0.0.0.0.0.0..0.0....0...1c.1.22.serp..2.0.0.KGJGjncdJpE&bav=on.2,or.&bvm=bv.49784469%2Cd.dGI%2Cpv.xjs.s.en_US.MpiVkF51mpA.O&fp=278de7208fdd5e83&biw=1309&bih=713";

        // String url =
        // "http://www.baidu.com/s?tn=bajiduhome_pg&ie=utf-8&bs=%40requestparam&f=3&rsv_bp=1&rsv_spt=1&wd=%E7%BB%9D%E5%9C%B0%E5%8F%8D%E5%87%BB&rsv_sug3=9&rsv_sug1=7&rsv_sug4=512&oq=jdfj&rsp=0&rsv_sug5=0&rsv_sug=0&rsv_sug2=0&inputT=7399";

//        String url = "http://192.168.2.205:8080";
//        url = "/j-a_sj.sd?j=ks&k=1&s=12%&s=~df=@,dkj=ds";
//        System.out.println(url + "\n---------");
//        System.out.println(getUrl(url));
        
        
        System.out.println(new Date().getTime());

    }
}
