package lwp.md.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.CollapsibleActionView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import lwp.md.R;

/**
 * liwenpeng
 * 2018/3/31 22:59
 */
public class FruitActivity extends AppCompatActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    private String fruitname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        fruitname = intent.getStringExtra(FRUIT_NAME);
        int fruitImageid = intent.getIntExtra(FRUIT_IMAGE_ID,0);
        Toolbar toolbar = findViewById(R.id.fruit_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolBar);
        setSupportActionBar(toolbar);
        TextView fruitContentText = findViewById(R.id.fruit_content_text);
        ImageView fruitImageView = findViewById(R.id.fruit_image_view);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruitname);
        Glide.with(this).load(fruitImageid).into(fruitImageView);
        String fruitContent = generateFruitContent();
        fruitContentText.setText(fruitContent);


    }

    private String generateFruitContent() {
        StringBuffer stringBuffer =new StringBuffer();
        for(int i=0;i<500;i++){
            stringBuffer.append(fruitname);
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
