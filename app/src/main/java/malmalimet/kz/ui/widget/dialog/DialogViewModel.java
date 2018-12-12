package malmalimet.kz.ui.widget.dialog;

import kz.senim.ui.dialog.type.Dialog;

public interface DialogViewModel<D extends Dialog, V> extends ViewModel<V> {
    void setDialog(D dialog);
}
