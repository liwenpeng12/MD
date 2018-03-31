package lwp.md.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import lwp.md.JavaBean.Fruit;
import lwp.md.R;
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
            new Fruit("Apple", R.mipmap.ic_launcher),
            new Fruit("Orange",R.mipmap.ic_launcher),
            new Fruit("Peer",R.mipmap.ic_launcher),
            new Fruit("Cherry",R.mipmap.ic_launcher)
    };
    private List<Fruit> mFruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private View headerView;
    private TextView tv_header_mail;
    private TextView tv_header_username;
    private CircleImageView circleImageView;
    private SwipeRefreshLayout swipeRefreshLayout;

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
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));//abandon
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

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
        headerView = navigationView.getHeaderView(0);
        circleImageView = headerView.findViewById(R.id.icon_image);
        circleImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToastUtil.makeToast(MainActivity.this,"circleImageView",Toast.LENGTH_SHORT);
                //TODO:调起相机或者相册选择图片进行替换，最好加个缓存，保存之前做过的头像
            }
        });
        tv_header_mail = headerView.findViewById(R.id.mail);
        tv_header_username = headerView.findViewById(R.id.username);
        tv_header_mail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToastUtil.makeToast(MainActivity.this,"点击了tv_header_mail",Toast.LENGTH_SHORT);
            }
        });
        tv_header_username.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToastUtil.makeToast(MainActivity.this,"tv_header_username",Toast.LENGTH_SHORT);
            }
        });

        setSupportActionBar(toolbar);
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
         //   supportActionBar.setHomeAsUpIndicator(R.drawable.category);//注意:R.mipmap.category报错
        }
    }

    //更新数据源
    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }
        }).start();
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
