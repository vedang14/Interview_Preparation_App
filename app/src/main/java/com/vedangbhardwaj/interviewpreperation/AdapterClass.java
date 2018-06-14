package com.vedangbhardwaj.interviewpreperation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<ItemClass> mList;
    private onItemClickListener mClickListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener)
    {
        mClickListener = listener;
    }

    public AdapterClass(Context context, ArrayList<ItemClass> exampleList) {
        mContext = context;
        mList = exampleList;
    }


    @NonNull
    @Override
    public AdapterClass.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ItemClass currentItem = mList.get(position);

        String ques = currentItem.getQuestion();
        String answer = currentItem.getAnswer();

        holder.mques.setText(ques);
        holder.mans.setText("Answer: " + answer);

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mques;
        public TextView mans;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mques = itemView.findViewById(R.id.question);
            mans = itemView.findViewById(R.id.answer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener !=null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            mClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}