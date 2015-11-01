package com.example.qiang.d_scrollview.myTouch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiang on 2015/10/20.
 */
public class TouchUtils {

    public final static Map<Integer,String> touchMap = new HashMap<>();



    static {
       int ACTION_DOWN             = 0;

        int ACTION_UP               = 1;


        int ACTION_MOVE             = 2;

        int ACTION_CANCEL           = 3;


         int ACTION_OUTSIDE          = 4;

        int ACTION_POINTER_DOWN     = 5;


         int ACTION_POINTER_UP       = 6;


       int ACTION_HOVER_MOVE       = 7;


         int ACTION_SCROLL           = 8;


        int ACTION_HOVER_ENTER      = 9;


        int ACTION_HOVER_EXIT       = 10;

        putMap(ACTION_DOWN,"ACTION_DOWN");
        putMap(ACTION_UP,"ACTION_UP");
        putMap(ACTION_MOVE,"ACTION_MOVE");
        putMap(ACTION_CANCEL,"ACTION_CANCEL");
        putMap(ACTION_OUTSIDE,"ACTION_OUTSIDE");
        putMap(ACTION_POINTER_DOWN,"ACTION_POINTER_DOWN");
        putMap(ACTION_POINTER_UP,"ACTION_POINTER_UP");
        putMap(ACTION_HOVER_MOVE,"ACTION_HOVER_MOVE");
        putMap(ACTION_SCROLL,"ACTION_SCROLL");
        putMap(ACTION_HOVER_ENTER,"ACTION_HOVER_ENTER");
        putMap(ACTION_HOVER_EXIT,"ACTION_HOVER_EXIT");

    }

   public static void putMap(int aciont , String actionName){

       touchMap.put(aciont,actionName);

   }


    public static String getActionName (int action){


        String actionName  =touchMap.get(action);

        if(actionName==null)
            return "不知道的动作 id为"+action;
        else return actionName;


    }



}
