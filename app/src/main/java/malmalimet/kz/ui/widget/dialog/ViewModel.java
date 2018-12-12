package malmalimet.kz.ui.widget.dialog;

public interface ViewModel<V> {
    void onCreate(V view);
    void onDestroy();

    boolean isCreated();
    boolean isStarted();
    boolean isStopped();

    void onStart();
    void onStop();

    void onStartAnimationStarted();
    void onStartAnimationFinished();

    void startWithoutAnimation();
}
