package lwp.md.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lwp.md.JavaBean.Fruit;
import lwp.md.R;
import lwp.md.view.activity.FruitActivity;

/**
 * Created by liwenpeng on 18-3-30.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private static final String TAG = "FruitAdapter";

    private Context mContext;
    private List<Fruit> mFruitList;

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitimage;
        TextView fruitname;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitimage = cardView.findViewById(R.id.fruit_image);
            fruitname = cardView.findViewById(R.id.fruit_name);
        }
    }


    public FruitAdapter(List<Fruit> mFruitList) {
        this.mFruitList = mFruitList;
    }

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(adapterPosition);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageid());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitname.setText(fruit.getName());
        Log.d(TAG," name :"+fruit.getName());
        Glide.with(mContext).load(fruit.getImageid()).into(holder.fruitimage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
