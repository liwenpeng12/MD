package lwp.md.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lwp on 18-3-30.
 */

public class SharedPreferenceUtil {

    //设置数据
     public static void setData(Context context,String name,int mode,int type,String key,Object value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        SharedPreferences.Editor edit = sharedPreferences.edit();
         DoSetData(edit,type,key,value);
    }
     private static void DoSetData(SharedPreferences.Editor editor,int type,String key,Object value){
        switch (type){
            case Constant.TYPE_BOOLEAN:
                editor.putBoolean(key,(Boolean) value).apply();
                break;
            case Constant.TYPE_FLOAT:
                editor.putFloat(key,(Float) value).apply();
                break;
            case Constant.TYPE_LONG:
                editor.putLong(key,(Long) value).apply();
                break;
            case Constant.TYPE_INT:
                editor.putInt(key,(Integer) value).apply();
                break;
            case Constant.TYPE_STRING:
                editor.putString(key,(String) value).apply();
                break;
            case 0:
                break;

        }
        editor.commit();
    }

   //读取数据,两种方法实现：1.void不返回，自定义回调，在activcity或fragment中处理;2.
    public static Object getData(Context context,String name,int mode,String key,Object value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        if (value instanceof Boolean){
            return sharedPreferences.getBoolean(key, false);
        }else if (value instanceof Float){
            return  sharedPreferences.getFloat(key,0);
        }else if (value instanceof Integer){
            return sharedPreferences.getInt(key,0);
        }else if (value instanceof Long){
            return sharedPreferences.getLong(key,0);
        }else{
            //That is string
            return sharedPreferences.getString(key,"");
        }
    }
}
