package malmalimet.kz.ui.widget;

import android.databinding.BindingAdapter;
import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

public class Form {

    private SparseArray<FormField> mFields = new SparseArray<>();

    @BindingAdapter("formField_form")
    public static void setForm(View formField, Form form) {
        if (formField instanceof FormField) {
            if (formField.getId() != View.NO_ID) {
                form.addField(formField.getId(), (FormField) formField);
            } else {
                throw new RuntimeException("formField_form binding's view must have an ID");
            }
        } else {
            throw new RuntimeException("formField_form binding's view must implement the FormField interface");
        }
    }

    public Form() {
    }

    public void addField(@IdRes int id, FormField field) {
        mFields.put(id, field);
    }

    public FormField getField(@IdRes int id) {
        return mFields.get(id);
    }

    public SparseArray<FormField> getFields() {
        return mFields;
    }

    public void removeField(@IdRes int id) {
        mFields.remove(id);
    }

    public boolean validate() {
        boolean isValid = true;
        for (int i = 0; i < mFields.size(); ++i) {
            int key = mFields.keyAt(i);
            if (!mFields.get(key).validate()) {
                isValid = false;
            }
        }
        return isValid;
    }

    public void clear() {
        mFields.clear();
    }

    public void reset() {
        for (int i = 0; i < mFields.size(); ++i) {
            int key = mFields.keyAt(i);
            mFields.get(key).resetValidators();
        }
    }
}
