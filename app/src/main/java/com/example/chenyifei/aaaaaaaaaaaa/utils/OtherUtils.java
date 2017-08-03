package com.example.chenyifei.aaaaaaaaaaaa.utils;

/**
 * Created by chenyifei on 2017/5/15.
 */
public class OtherUtils {


public static String msTominute(int a ){
if(a/1000%60<10){
    return a/1000/60+":0"+a/1000%60;


}
    return a/1000/60+":"+a/1000%60;


}


}
