package malmalimet.kz.ui.widget;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;

/**
 * A loadingVariable variable tells us whether some operation is still pending.
 */
public class LoadingVariable implements Loadable {

    private int mLoadingCount = 0;
    private ObservableBoolean mValue = new ObservableBoolean(false);

    public ObservableBoolean isLoading() {
        return mValue;
    }

    public boolean getLoading() {
        return mValue.get();
    }

    public boolean isClear() {
        return mLoadingCount == 0;
    }

    /**
     * Set the amount of the loadingVariable variable.
     *
     * @param value - true indicates that the variable should be in loadingVariable state. If false passed, and no
     *              other operation is pending, then the variable moves into the non-loadingVariable state.
     */
    @Override
    public void setLoading(boolean value) {
        mLoadingCount = Math.max(0, mLoadingCount + (value ? 1 : -1));
        mValue.set(mLoadingCount != 0);
    }

    /**
     * Reset the loadingVariable variable to 0, indicating that no pending operation exists.
     */
    public void reset() {
        mLoadingCount = 0;
        mValue.set(false);
    }

    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        mValue.addOnPropertyChangedCallback(callback);
    }

    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        mValue.removeOnPropertyChangedCallback(callback);
    }
}
