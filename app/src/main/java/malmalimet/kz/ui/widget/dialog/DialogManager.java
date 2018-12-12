package malmalimet.kz.ui.widget.dialog;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import kz.senim.ui.dialog.type.Dialog;

public class DialogManager {

    public static final String TYPE_BILL = "TYPE_BILL";

    private Map<String, Dialog> mSingletonDialogs = new HashMap<>();

    @Inject
    public DialogManager() {
    }

    public void onSingletonDialogShown(String typeId, Dialog dialog) {
        if (typeId != null) {
            if (mSingletonDialogs.containsKey(typeId)) {
                mSingletonDialogs.get(typeId).dismiss();
            }
            mSingletonDialogs.put(typeId, dialog);
        }
    }

    public void removeDialog(String typeId) {
        if (mSingletonDialogs.containsKey(typeId)) {
            mSingletonDialogs.remove(typeId);
        }
    }
}
