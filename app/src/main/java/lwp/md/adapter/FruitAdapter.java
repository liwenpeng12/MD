package lwp.md.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lwp.md.JavaBean.Fruit;
import lwp.md.R;

/**
 * Created by liwenpeng on 18-3-30.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitname.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageid()).into(holder.fruitimage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
