package lwp.md.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lwp on 18-3-30.
 */

public class ToastUtil {
    public static void makeToast(Context context,String text,int length){
        Toast.makeText(context,text,length).show();
    }
    public static void SnackbarToast(View view, String text, int length, final Context context){
        Snackbar.make(view,text,length).setAction("DO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(context,"点击了按钮",Toast.LENGTH_SHORT);
            }
        }).show();

    }

}
