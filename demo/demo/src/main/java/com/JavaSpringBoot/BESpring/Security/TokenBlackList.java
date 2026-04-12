package com.JavaSpringBoot.BESpring.Security;

import java.util.HashSet;
import java.util.Set;

//khi User logout → token vẫn còn dùng được
//Nếu token nằm trong blacklist → chặn
//Logout = thêm token vào blacklist
public class TokenBlackList {
    private static final Set<String> blacklist = new HashSet<>();
    public static void add(String token){
        blacklist.add(token);
    }
    public static boolean contains(String token){
        return blacklist.contains(token);
    }
}
