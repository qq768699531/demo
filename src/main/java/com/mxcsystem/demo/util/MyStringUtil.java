package com.mxcsystem.demo.util;
import com.mxcsystem.demo.entity.User;


import java.util.HashSet;
import java.util.Set;
import java.util.regex.*;

public class MyStringUtil {
    /**
     * match the @xxxx(123456548) string from disscussion and return a Set<User>
     * @param str 字符串
     * @return Set<User>
     */
    public static Set<User> matchAt(String str){
        Pattern pattern = Pattern.compile("@([^x00-xf]|[a-zA-Z])+\\([0-9]+\\)");
        Matcher matcher = pattern.matcher(str);
        Set<User> userSet = new HashSet<>();
        while(matcher.find()) {
            String inner = matcher.group();
            String preName = inner.split("\\(")[0].substring(1);
            String nextNum = inner.split("\\(")[1].substring(0,inner.split("\\(")[1].length()-1);
            userSet.add(new User(preName,nextNum));
        }
        return userSet;
    }
}
