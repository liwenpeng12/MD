package lwp.md;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import lwp.md.util.SharedPreferenceUtil;
import lwp.md.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar supportActionBar;
    private boolean should_open = false;
    private int CLICKCOUNT = 0;
//    private SharedPreferenceUtil mSharedPreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawae_layout);
        toolbar = findViewById(R.id.toolbar);
//        drawerLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.makeToast(MainActivity.this,"drawablelayout",Toast.LENGTH_SHORT);
//            }
//        });
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
//            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                ToastUtil.makeToast(MainActivity.this,"backup", Toast.LENGTH_SHORT);
                break;
            case R.id.delete:
                ToastUtil.makeToast(MainActivity.this,"delete", Toast.LENGTH_SHORT);
                break;
            case R.id.settings:
                ToastUtil.makeToast(MainActivity.this,"settings", Toast.LENGTH_SHORT);
                break;
            case android.R.id.home:
                ToastUtil.makeToast(MainActivity.this,"home", Toast.LENGTH_SHORT);
                CLICKCOUNT++;
                if (CLICKCOUNT % 2 == 0 ){
                    should_open = false;
                }else {
                    should_open = true;
                }
                SharedPreferenceUtil.setData(MainActivity.this,"data",MODE_PRIVATE,1,"should_open",should_open);
                if ((Boolean) SharedPreferenceUtil.getData(MainActivity.this,"data",MODE_PRIVATE,"should_open",should_open)){
drawerLayout.openDrawer(GravityCompat.START);
                }else {
          drawerLayout.closeDrawers();
                }


                break;
                default:
                    ToastUtil.makeToast(MainActivity.this,"null", Toast.LENGTH_SHORT);
                    break;
        }
        return true;
    }
}
