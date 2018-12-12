package malmalimet.kz.ui.widget;

import android.content.Context;

import rx.functions.Action1;

public interface FormField {

    boolean validate();
    boolean checkIfValid();
    boolean wasValidated();
    void resetValidators();

    void setError(String error);
    void clearError();

    Object getValue();
    void addOnValueChangedListener(Action1<Object> listener);
    void removeOnValueChangedListener(Action1<Object> listener);

    void addOnFocusChangedListener(Action1<Boolean> listener);
    void removeOnFocusChangedListener(Action1<Boolean> listener);

    Context getContext();
}
