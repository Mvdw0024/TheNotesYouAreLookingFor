package vandewouwer.michael.thenotesyouarelookingfor.Utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import vandewouwer.michael.thenotesyouarelookingfor.Models.Note;
import vandewouwer.michael.thenotesyouarelookingfor.Models.NoteViewModel;
import vandewouwer.michael.thenotesyouarelookingfor.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> implements Filterable {


    private FragmentActivity activity;

    class NoteViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle;
        final TextView tvCreateDate;
        final Button btnDetails;
        final ImageButton btnDelete;


        final View.OnClickListener detailListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Bundle data = new Bundle();
                data.putSerializable("passedNote", notes.get(position));
                Navigation.findNavController(v).navigate(R.id.action_noteFragment_to_detailFragment, data);

            }
        };

        final View.OnClickListener deleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positiont = getAdapterPosition();
                Note toDelNote = notes.get(positiont);
                NoteViewModel model = new ViewModelProvider(activity).get(NoteViewModel.class);
                notes.remove(toDelNote);
                searchNotes.remove(toDelNote);
                model.removeNote(toDelNote);
                notifyDataSetChanged();
            }
        };


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCreateDate = itemView.findViewById(R.id.tvCreateDate);
            //DELETE
            btnDelete = itemView.findViewById(R.id.delBtn);
            btnDelete.setOnClickListener(deleteListener);
            //DETAILS
            btnDetails = itemView.findViewById(R.id.btn_details);
            btnDetails.setOnClickListener(detailListener);
        }
    }

    private List<Note> notes;
    private List<Note> searchNotes;

    public NoteAdapter(FragmentActivity activity) {
        this.activity = activity;
        this.notes = new ArrayList<>();
        this.searchNotes = new ArrayList<>();
    }


    public void addNotes(List<Note> notes) {
        this.notes.clear();
        this.notes.addAll(notes);
        searchNotes = notes;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = notes.get(position);

        holder.tvTitle.setText(currentNote.getTitle());
        holder.tvCreateDate.setText(currentNote.getCreationTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String input = charSequence.toString();
                notes = searchNotes;
                if (input.isEmpty()) {
                    notes = searchNotes;
                } else {
                    ArrayList<Note> filteredList = new ArrayList<>();
                    for (Note element : notes) {
                        if (element.getTitle().contains(input.toLowerCase())) {
                            filteredList.add(element);
                        }
                        notes = filteredList;
                    }
                }
                return null;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notifyDataSetChanged();
            }
        };
    }
}



