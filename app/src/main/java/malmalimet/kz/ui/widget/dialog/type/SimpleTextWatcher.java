package malmalimet.kz.ui.widget.dialog.type;

import android.text.TextWatcher;

public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Nothing
    }
}
