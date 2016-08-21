package com.voksel.electric.pc.ui.dialog;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

/**
 * Created by edsarp on 8/21/16.
 */
public class CustomMessageDialog extends SelectorComposer<Window> {
    @Wire
    Label message;
    @Wire
    Button btnClose;

    public CustomMessageDialog() {
    }

    public Button getBtnClose() {
        return this.btnClose;
    }

    public Label getMessage() {
        return this.message;
    }

    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        this.btnClose.addEventListener(999, "onClick", new EventListener() {
            public void onEvent(Event event) throws Exception {
                ((Window) CustomMessageDialog.this.getSelf()).detach();
            }
        });
    }
}