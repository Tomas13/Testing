package malmalimet.kz.ui.widget.base;

import malmalimet.kz.ui.widget.viewcontract.ViewContract;
import malmalimet.kz.ui.widget.viewcontract.ViewContractActivity;

public interface ViewDelegate {
    void onCreate(ViewContractActivity context, ViewContract viewContract);

    void onDestroy();
}
