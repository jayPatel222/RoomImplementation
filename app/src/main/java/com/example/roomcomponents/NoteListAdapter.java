package com.example.roomcomponents;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
  public interface onDeleteClickListener{
      void onDeleteClickListener(Note myNote);

  }


    private final LayoutInflater layoutInflater;
    private Context mcontext;
    private List<Note> mnotes;
    private onDeleteClickListener onDeleteClickListener;



    public NoteListAdapter(Context context,onDeleteClickListener listener){
    layoutInflater = LayoutInflater.from(context);
    mcontext = context;
    this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item,parent,false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
       if (mnotes != null){
           Note note = mnotes.get(position);
           holder.setData(note.getTexter(),position);
           holder.setListeners();
       }
       else
       {
           holder.noteItemView.setText("No notes");
       }
    }

    @Override
    public int getItemCount() {
        if (mnotes != null)
            return mnotes.size();
        else return 0;
    }

    public void setNotes(List<Note> notes){
        mnotes = notes;
        notifyDataSetChanged();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView noteItemView;
        private int mPosition;
        private ImageView del;

        public NoteViewHolder(View itemView) {
            super(itemView);
            noteItemView = itemView.findViewById(R.id.txvNote);
            del = itemView.findViewById(R.id.ivRowDelete);


        }

        public void setData(String note, int position) {
            noteItemView.setText(note);
            mPosition = position;
        }


        public void setListeners() {
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.onDeleteClickListener(mnotes.get(mPosition));
                    }
                }
            });
        }
    }
}
