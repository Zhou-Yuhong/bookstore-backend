package zn.zyh.back_code.utils.analysisutils;
import zn.zyh.back_code.dto.Order_product_wrap;
import zn.zyh.back_code.dto.User_Comsumption;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AnaUtil {
    //获得字符串中的数字
    public static List<Long> getDigit(String text) {

        List<Long> digitList =new ArrayList<Long>();

        Pattern p= Pattern.compile("(\\d+)");

        Matcher m= p.matcher(text);

        while (m.find()) {

            String find= m.group(1).toString();

            digitList.add(Long.valueOf(find));

        }
        return digitList;
    }
    //比较两个时间的大小,time1>=time2返回true,time1<time2返回false
    public static boolean compareTime(String time1,String time2){
        List<Long> long1=getDigit(time1);
        List<Long> long2=getDigit(time2);
        //年月日的比较
        for(int i=0;i<3;i++){
            if(long1.get(i)>long2.get(i)) return true;
            if(long1.get(i)<long2.get(i)) return false;
            continue;
        }
        return true;
    }
    //判断一个时间是否在一个时间范围内
    public static boolean InRange(String target,String begin,String end){
        if(compareTime(target,begin)&&compareTime(end,target)) return true;
        else return false;
    }
    //把两个List<Product_Wrap>合并成一个,结果放a中
    public static void compact(List<Order_product_wrap> a,List<Order_product_wrap> b){
       for(int i=0;i<b.size();i++){
           Order_product_wrap tmp=b.get(i);
           for(int j=0;j<a.size();j++){
               if(a.get(j).getProduct_id()==tmp.getProduct_id()){
                   a.get(j).setNum(a.get(j).getNum()+tmp.getNum());
                   continue;
               }
           }
           a.add(tmp);
       }
    }
    //先判断user_com是否在user_coms中，若不在则加入，否则合并
    public static void addUserComsumption(List<User_Comsumption> user_comsumptions,User_Comsumption user_comsumption){
        if(user_comsumptions.size()==0) {
            user_comsumptions.add(user_comsumption);
            return;
        }
        else {
           for(int i=0;i<user_comsumptions.size();i++){
               if(user_comsumption.getUserid()==user_comsumptions.get(i).getUserid()){
                   user_comsumptions.get(i).setNum(user_comsumptions.get(i).getNum()+user_comsumption.getNum());
                   user_comsumptions.get(i).setValue(user_comsumptions.get(i).getValue().add(user_comsumption.getValue()));
                   return;
               }
           }
           user_comsumptions.add(user_comsumption);
        }
    }
}
