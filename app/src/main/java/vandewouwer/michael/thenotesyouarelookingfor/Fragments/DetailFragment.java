package vandewouwer.michael.thenotesyouarelookingfor.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vandewouwer.michael.thenotesyouarelookingfor.Models.Note;
import vandewouwer.michael.thenotesyouarelookingfor.Models.NoteViewModel;
import vandewouwer.michael.thenotesyouarelookingfor.R;
import vandewouwer.michael.thenotesyouarelookingfor.Utils.NoteAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private View.OnClickListener saveNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Note n = new Note(titleTV.getText().toString(), inhoudTV.getText().toString());
            Log.d(titleTV.getText().toString(), "Title is Found");
            Log.d(inhoudTV.getText().toString(), "Inhoud is Found");
            NoteViewModel model = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
            model.addNote(n);
            model.updateNotes(n);
            Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_noteFragment);
            Toast.makeText(getActivity(), titleTV.getText() + " - note saved", Toast.LENGTH_SHORT).show();


        }
    };
    private EditText titleTV;
    private EditText inhoudTV;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_detail, container, false);

        titleTV = rootview.findViewById(R.id.tv_details_title);
        inhoudTV = rootview.findViewById(R.id.ti_details_inhoud);
        Button saveBtn = rootview.findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(saveNoteClickListener);
        Bundle data = getArguments();
        if (data != null) {
            if (data.containsKey("passedNote")) {
                Note note = (Note) data.getSerializable("passedNote");
                titleTV.setText(note.getTitle());
                inhoudTV.setText(note.getInhoud());

            }
        }


        return rootview;
    }

}
