package hoanglv.fpoly.thithu.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hoanglv.fpoly.thithu.R;
import hoanglv.fpoly.thithu.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> list;
    private OnItemClickListenter onItemClickListenter;

    public NoteAdapter(List<Note> list) {
        this.list = list;
    }

    public interface OnItemClickListenter {
        void onDeleteClick(int position);
        void onItemClick(Note note);
    }

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_view, null);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        final Note note = list.get(position);
        holder.tvNote.setText(note.getNoidung());
        holder.tvDate.setText(note.getNgay());
        holder.lnItem.setOnClickListener(v -> {
            if (onItemClickListenter != null) {
                onItemClickListenter.onItemClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote, tvDate;
        ImageView imgDelete;
        RelativeLayout lnItem;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            lnItem = itemView.findViewById(R.id.lnItem);
            imgDelete.setOnClickListener(v -> {
                if (onItemClickListenter != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListenter.onDeleteClick(position);
                    }
                }
            });
        }
    }

    public void updateData(List<Note> newList) {
        this.list.clear();
        this.list.addAll(newList);
        notifyDataSetChanged();
    }
}
