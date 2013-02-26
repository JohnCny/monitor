package cn.sh.ae.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test   { 

public static void main(String ss[]) { 
   SimpleDateFormat d= new SimpleDateFormat("yyyy-MM-dd");//格式化时间 
  
   String nowtime=d.format(new Date());//按以上格式 将当前时间转换成字符串 
  
   System.out.println("当前时间："+nowtime); 
   String testtime="2007-09-05";//测试时间 
   System.out.println("测试时间："+testtime); 
  
   try { 
    long result=(d.parse(nowtime).getTime()-d.parse(testtime).getTime())/1000;//当前时间减去测试时间   这个的除以1000得到秒，相应的60000得到分，3600000得到小时 
    System.out.println("当前时间减去测试时间="+result/86400+"秒"); 
   } catch (ParseException e) { 
    e.printStackTrace(); 
   } 
  
} 

} 

