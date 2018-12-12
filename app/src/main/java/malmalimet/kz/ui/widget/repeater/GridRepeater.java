package malmalimet.kz.ui.widget.repeater;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

import malmalimet.kz.R;

public class GridRepeater extends Repeater {

    private static final int DEFAULT_COLUMN_COUNT = 2;

    public GridRepeater(Context context) {
        this(context, null);
    }

    public GridRepeater(Context context, @Nullable AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public GridRepeater(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GridRepeater, 0, 0);
        int columnCount;
        try {
            columnCount = a.getInt(R.styleable.GridRepeater_gridRepeater_columns, DEFAULT_COLUMN_COUNT);
        } finally {
            a.recycle();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, columnCount);
        setLayoutManager(gridLayoutManager);

        removeDividerDecoration();
    }
}
