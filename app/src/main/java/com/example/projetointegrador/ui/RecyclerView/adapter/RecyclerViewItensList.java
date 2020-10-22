package com.example.projetointegrador.ui.RecyclerView.adapter;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetointegrador.R;

import java.util.List;

import com.example.projetointegrador.model.Model;
import com.example.projetointegrador.ui.ItensList;

import static com.example.projetointegrador.ui.Interface.KEY_EDIT_ITENS;
import static java.security.AccessController.getContext;

public class RecyclerViewItensList extends RecyclerView.Adapter<RecyclerViewItensList.ViewHolder> {
    private Context mContext;
    List<Model> mNotes;

    public RecyclerViewItensList(Context context, List<Model> notes) {
        mNotes = notes;
        mContext = context;
    }
        private ClickListener clickListener;
        private MyLongClickListener myLongClickListener;

    public RecyclerViewItensList(List<Model> itensList){this.mNotes = itensList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View notesView = inflater.inflate(R.layout.itens_list_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(notesView);
        return viewHolder;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public void onBindViewHolder(RecyclerViewItensList.ViewHolder holder, int position) {
        // Get the data model based on position
        Model notes = mNotes.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.textViewDescription;
        textView.setText(notes.getDescription());
        TextView textView1 = holder.textViewPrice;
        textView1.setText(notes.getPriceLikeString());
        TextView textView2 = holder.textViewQuantify;
        textView2.setText(notes.getQuantifyLikeString());
        ImageView img = holder.imageitem;
        img.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model notes = mNotes.get(position);
                Intent intent = new Intent(view.getContext(), ItensList.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_EDIT_ITENS,notes);
                intent.putExtras(bundle);
                getContext().startActivity(intent);

                Toast.makeText(getContext(), "Recycle Click" + position+"  ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnItemLongClickListener(MyLongClickListener clickListener) {
        this.myLongClickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public interface MyLongClickListener {
        boolean onItemLongClick(int position, View v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewQuantify;
    private TextView textViewPrice;
    private TextView textViewDescription;
    private ImageView imageitem;

    public ViewHolder(View itemView) {
        super(itemView);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        textViewQuantify = itemView.findViewById(R.id.textViewQuantify);
        imageitem = itemView.findViewById(R.id.imageView);
    }
    }
}
