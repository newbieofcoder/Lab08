package hoanglv.fpoly.thithu.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hoanglv.fpoly.thithu.R;
import hoanglv.fpoly.thithu.dao.NoteDAO;
import hoanglv.fpoly.thithu.models.Note;

public class fragment2 extends Fragment {
    private NoteDAO dao;
    private SharedViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public fragment2() {
    }

    public static fragment2 newInstance() {
        return new fragment2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        EditText edtNote = view.findViewById(R.id.edtNote);
        EditText edtTime = view.findViewById(R.id.edtTime);
        TextView tvSave = view.findViewById(R.id.tvSave);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        tvSave.setOnClickListener(v -> {
            String note = edtNote.getText().toString();
            String time = edtTime.getText().toString();
            if (note.isEmpty() || time.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                dao = new NoteDAO(getContext());
                dao.insert(new Note(time, note));
                List<Note> updatedList = dao.getData();
                viewModel.setItems(updatedList);
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}