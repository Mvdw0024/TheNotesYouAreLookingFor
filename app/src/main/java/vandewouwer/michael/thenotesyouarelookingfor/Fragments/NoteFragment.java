package vandewouwer.michael.thenotesyouarelookingfor.Fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vandewouwer.michael.thenotesyouarelookingfor.Models.Note;
import vandewouwer.michael.thenotesyouarelookingfor.Models.NoteViewModel;
import vandewouwer.michael.thenotesyouarelookingfor.R;
import vandewouwer.michael.thenotesyouarelookingfor.Utils.NoteAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteFragment extends Fragment {

    private NoteAdapter adapter;


////NoteViewModel model = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
//           model.addNote(n);

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            adapter.getFilter().filter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            adapter.getFilter().filter(query);
            return false;
        }
    };

    private View.OnClickListener newNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(view).navigate(R.id.action_noteFragment_to_detailFragment);

        }
    };
    private FragmentActivity mContext;

    public NoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_note_list, container, false);
        FloatingActionButton NewNoteBtn = rootView.findViewById(R.id.floatingActionButton);
        NewNoteBtn.setOnClickListener(newNoteClickListener);

        setHasOptionsMenu(true);
        RecyclerView rvNotes = rootView.findViewById(R.id.rv_notes);
        RecyclerView.LayoutManager manager;
        manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        //  manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        // manager = new GridLayoutManager(getContext(), 3);

        rvNotes.setLayoutManager(manager);
        adapter = new NoteAdapter(getActivity());
        rvNotes.setAdapter(adapter);
        NoteViewModel model = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
        model.getNotes().observe(mContext, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.addNotes(notes);
                adapter.notifyDataSetChanged();
            }

        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView;
        searchView = (SearchView) menu.findItem(R.id.mi_search).getActionView();
        searchView.setOnQueryTextListener(searchListener);


        super.onCreateOptionsMenu(menu, inflater);
    }
}

