package lwp.md;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lwp.md.JavaBean.Fruit;
import lwp.md.adapter.FruitAdapter;
import lwp.md.util.SharedPreferenceUtil;
import lwp.md.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar supportActionBar;
    private boolean should_open = false;
    private int CLICKCOUNT = 0;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    //    private SharedPreferenceUtil mSharedPreferenceUtil;
    private Fruit[] fruits ={
            new Fruit("Apple",R.mipmap.ic_launcher),
            new Fruit("Orange",R.mipmap.ic_launcher),
            new Fruit("Peer",R.mipmap.ic_launcher),
            new Fruit("Cherry",R.mipmap.ic_launcher)
    };
    private List<Fruit> mFruitList = new ArrayList<>();
    private FruitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruitList();
        drawerLayout = findViewById(R.id.drawae_layout);
        toolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycle_view);
        navigationView = findViewById(R.id.nav_view);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     ToastUtil.makeToast(MainActivity.this,"floatingActionButton clicked", Toast.LENGTH_SHORT);
                ToastUtil.SnackbarToast(v,"button",Toast.LENGTH_SHORT,MainActivity.this);
            }
        });

        GridLayoutManager manager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(manager);
        adapter =new FruitAdapter(mFruitList);
        mRecyclerView.setAdapter(adapter);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
//            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        }
    }

    private void initFruitList() {
        mFruitList.clear();
        for (int i = 0 ; i <50 ; i++ ){
            Random random = new Random();
            int nextInt = random.nextInt(fruits.length);
            mFruitList.add(fruits[nextInt]);
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
