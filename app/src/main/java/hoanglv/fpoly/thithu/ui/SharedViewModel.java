package hoanglv.fpoly.thithu.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hoanglv.fpoly.thithu.models.Note;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<Note>> items = new MutableLiveData<>();

    public LiveData<List<Note>> getItems() {
        return items;
    }

    public void setItems(List<Note> newItems) {
        items.setValue(newItems);
    }

    public void addItem(Note newItem) {
        List<Note> currentItems = items.getValue();
        if (currentItems != null) {
            currentItems.add(newItem);
            items.setValue(currentItems);
        }
    }
}

