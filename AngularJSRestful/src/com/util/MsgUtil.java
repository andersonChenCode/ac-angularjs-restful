package com.util;

import com.util.lgt.Common;


public class MsgUtil {
    static String msg = "";
    static public void setMsg(String s) { msg = s; }
    static public String getMsg(){return Common.get(msg); }
}
