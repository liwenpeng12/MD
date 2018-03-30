package lwp.md.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lwp on 18-3-30.
 */

public class ToastUtil {
    public static void makeToast(Context context,String text,int length){
        Toast.makeText(context,text,length).show();
    }
    public static void SnackbarToast(){

    }
}
