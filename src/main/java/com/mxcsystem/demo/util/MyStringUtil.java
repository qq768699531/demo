package com.mxcsystem.demo.util;
import com.mxcsystem.demo.entity.User;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;
/**
 * @author zy
 *
 *  match the @xxxx(123456548) string from disscussion and return a Set<User>  eg:@周圆(1302354684687)
 *  PS:Set √  ArrayList ×
 *  PPS:
 */
public class MyStringUtil {
    /**
     * match the @xxxx(123456548) string from disscussion and return a Set<User>  eg:@周圆(1302354684687)
     * @param str 字符串
     * @return Set<User>
     */
    public static  Set<User> matchAt(String str){
//        ArrayList<User> users = new ArrayList<User>();
//        Pattern pattern = Pattern.compile("@周扬(1189051464)|@周在想(124678784)");
//        Matcher matcher = pattern.matcher(str);
//
//        while(matcher.find()){
//            String e=matcher.group(0);
//            e = e.substring(1);
//            String[] arr = e.split("(0-9)");
//            System.out.println(arr[0]+" "+arr[1]);
//            users.add(new User(arr[0],arr[1]));
//        }
        Pattern pattern = Pattern.compile("@[^x00-xff]+\\([0-9]+\\)");
        Matcher matcher = pattern.matcher(str);
        Set<User> userSet = new HashSet();
        while(matcher.find()) {
            String inner = matcher.group();

            String preName = inner.split("\\(")[0].substring(1);
            String nextNum = inner.split("\\(")[1].substring(0,inner.split("\\(")[1].length()-1);

            System.out.println(preName+" "+nextNum);
            userSet.add(
                    new User(preName.substring(1,preName.length()-1),
                    nextNum.substring(0,nextNum.length()-1)));
        }

//        for(String i:resultset) {
//            System.out.println(i);
//        }
        return userSet;
    }
}
