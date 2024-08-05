package hoanglv.fpoly.thithu.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hoanglv.fpoly.thithu.R;
import hoanglv.fpoly.thithu.adapters.NoteAdapter;
import hoanglv.fpoly.thithu.adapters.SpacingItemDecoration;
import hoanglv.fpoly.thithu.dao.NoteDAO;
import hoanglv.fpoly.thithu.models.Note;


public class fragment1 extends Fragment {
    private NoteDAO dao;
    private RecyclerView lvNote;
    private List<Note> list;
    public NoteAdapter adapter;
    private SharedViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public fragment1() {
    }

    public static fragment1 newInstance() {
        return new fragment1();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        lvNote = view.findViewById(R.id.lvNote);
        dao = new NoteDAO(getContext());
        list = dao.getData();
        if (list == null) {
            list = new ArrayList<>();
        }
        adapter = new NoteAdapter(list);
        SpacingItemDecoration spacingItemDecoration = new SpacingItemDecoration(20);
        lvNote.addItemDecoration(spacingItemDecoration);
        lvNote.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvNote.setAdapter(adapter);
        adapter.setOnItemClickListenter(new NoteAdapter.OnItemClickListenter() {
            @Override
            public void onDeleteClick(int position) {
                Note note = list.get(position);
                dao.delete(note);
                list.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onItemClick(Note note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                View view = LayoutInflater.from(requireContext()).inflate(R.layout.custom_dialog, null);
                TextView tvNote = view.findViewById(R.id.tvNote);
                TextView tvDate = view.findViewById(R.id.tvDate);
                tvNote.setText(note.getNoidung());
                tvDate.setText(note.getNgay());
                builder.setView(view);
                builder.setTitle("Chi tiết ghi chú");
                builder.setCancelable(true);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getItems().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.updateData(notes);
            }
        });
        return view;
    }
}