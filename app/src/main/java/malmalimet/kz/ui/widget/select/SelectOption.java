package malmalimet.kz.ui.widget.select;

import android.support.annotation.StringRes;

public interface SelectOption {
    int getOptionId();
    @StringRes int getTitleRes();
    String getTitle();
}
