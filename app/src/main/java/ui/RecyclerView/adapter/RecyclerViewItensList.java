package ui.RecyclerView.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

import model.Model;

public class RecyclerViewItensList extends RecyclerView.Adapter<RecyclerViewItensList.ViewHolder> {
    private ClickListener clickListener;
    private MyLongClickListener myLongClickListener;
    List<Model> itensList;

    public RecyclerViewItensList(List<Model> itensList){this.itensList = itensList;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itens_list_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model item = itensList.get(position);
        holder.link(item);
    }

    @Override
    public int getItemCount() {
        return itensList.size();
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

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
        textViewPrice = itemView.findViewById(R.id.textViewPrice);
        textViewQuantify = itemView.findViewById(R.id.textViewQuantify);
        imageitem = itemView.findViewById(R.id.imageView);
    }

        private void link(Model layout){
            textViewDescription.setText(layout.getDescription());
            textViewPrice.setText(layout.getPriceLikeString());
            textViewQuantify.setText(layout.getQuantifyLikeString());
            imageitem.setImageResource(R.drawable.logo);
        }
    }
}
