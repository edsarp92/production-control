package com.voksel.electric.pc.component;

import com.voksel.electric.pc.ui.dialog.CustomMessageDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.Map;

/**
 * Created by edsarp on 8/21/16.
 */
public class DialogUtil {
    private static final Logger logger = LoggerFactory.getLogger(DialogUtil.class);

    public DialogUtil() {
    }

    public static DialogUtil.ConfirmationDialogBuilder createConfirmationDialog(String question) {
        return new DialogUtil.ConfirmationDialogBuilder(question);
    }

    public static void showConfirmationDialog(String question, EventListener<Event> onOk, EventListener<Event> onCancel) {
        DialogUtil.ConfirmationDialogBuilder dialogBuilder = new DialogUtil.ConfirmationDialogBuilder(question);
        dialogBuilder.onCancel = onCancel;
        dialogBuilder.onOk = onOk;
        dialogBuilder.show();
    }

    public static Component createAndShowCustomDialog(String message, String title, Component parent, EventListener<Event> onCloseEvent) {
        Window window = (Window) Executions.createComponents("~./page/dialog/customDialog.zul", parent, (Map)null);
        CustomMessageDialog customMessageDialog = (CustomMessageDialog) Components.getComposer(window);
        customMessageDialog.getBtnClose().setLabel("Cancel");
        customMessageDialog.getMessage().setValue(message);
        window.setTitle(title);
        if(onCloseEvent != null) {
            customMessageDialog.getBtnClose().addEventListener(1, "onClick", onCloseEvent);
        }

        return window;
    }

    public static void showPopupDialogCloseOnly(String zulUrl, String title, Component parent, EventListener<Event> onClose, Map<String, Object> args) {
        showPopupDialog(zulUrl, title, parent, DialogUtil.PopupMode.CLOSE_ONLY, (EventListener)null, onClose, args);
    }

    public static void showPopupDialog(String zulUrl, String title, final Component parent, DialogUtil.PopupMode popupMode, final EventListener<Event> onOk, final EventListener<Event> onClose, Map<String, Object> args) {
        try {
            final Component e = Executions.createComponents(zulUrl, parent, args);
            final PopupUI popupUI = (PopupUI)Components.getComposer(e);
            if(popupUI == null) {
                throw new NullPointerException("Controller for popup " + zulUrl + " not found, apply composer to use popup");
            } else {
                if(title != null) {
                    popupUI.getContainer().setTitle(title);
                } else {
                    popupUI.getContainer().setTitle("Dialog");
                }

                if(DialogUtil.PopupMode.CLOSE_ONLY == popupMode) {
                    popupUI.getBtnOk().setVisible(false);
                } else if(DialogUtil.PopupMode.OK_CLOSE == popupMode) {
                    popupUI.getBtnClose().setLabel("Cancel");
                }

                popupUI.setEventListener(new EventListener() {
                    public void onEvent(Event event) throws Exception {
                        if(event.getTarget().equals(popupUI.getBtnOk())) {
                            if(onOk != null) {
                                onOk.onEvent(event);
                            }
                        } else if(event.getTarget().equals(popupUI.getBtnClose()) && onClose != null) {
                            onClose.onEvent(event);
                        }

                        parent.removeChild(e);
                    }
                });
            }
        } catch (ClassCastException var9) {
            logger.error("You should use PopupController composer for pop Dialog", var9);
            throw var9;
        }
    }

    public static DialogUtil.CommonDialogBuilder createDialog(String message, String title) {
        return new DialogUtil.CommonDialogBuilder(message, title);
    }

    public static void showDialog(String message, String title) {
        DialogUtil.CommonDialogBuilder dialogBuilder = new DialogUtil.CommonDialogBuilder(message, title);
        dialogBuilder.show();
    }

    public static void showDialog(String message, String title, EventListener<Event> onOk) {
        DialogUtil.CommonDialogBuilder dialogBuilder = new DialogUtil.CommonDialogBuilder(message, title);
        dialogBuilder.onClose = onOk;
        dialogBuilder.show();
    }

    public static void showLoading() {
        Clients.showBusy("Loading...");
    }

    public static void clearLoading() {
        Clients.clearBusy();
    }

    public static enum PopupMode {
        CLOSE_ONLY,
        OK_CLOSE;

        private PopupMode() {
        }
    }

    static class CommonDialogBuilder implements DialogUtil.Dialog {
        private String message;
        private String title;
        private String icon;
        private EventListener<Event> onClose;

        private void onClose(EventListener<Event> eventListener) {
            this.onClose = eventListener;
        }

        CommonDialogBuilder(String message, String title) {
            this.message = message;
            this.title = title;
        }

        public void show() {
            Messagebox.show(this.message, this.title, 1, this.icon != null && !"".equals(this.icon)?this.icon:"z-messagebox-icon z-messagebox-information", this.onClose);
        }
    }

    static class ConfirmationDialogBuilder implements DialogUtil.Dialog {
        private String question;
        private EventListener<Event> onOk;
        private EventListener<Event> onCancel;

        ConfirmationDialogBuilder(String question) {
            this.question = question;
        }

        public void onOk(EventListener<Event> eventListener) {
            this.onOk = eventListener;
        }

        public void onCancel(EventListener<Event> eventListener) {
            this.onCancel = eventListener;
        }

        public void show() {
            Messagebox.show(this.question, "Confirmation dialog", 3, "z-messagebox-icon z-messagebox-question", new EventListener() {
                public void onEvent(Event event) throws Exception {
                    if(event.getName().equals("onOK")) {
                        if(ConfirmationDialogBuilder.this.onOk != null) {
                            ConfirmationDialogBuilder.this.onOk.onEvent(event);
                        }
                    } else if(event.getName().equals("onCancel") && ConfirmationDialogBuilder.this.onCancel != null) {
                        ConfirmationDialogBuilder.this.onCancel.onEvent(event);
                    }

                }
            });
        }
    }

    interface Dialog {
        void show();
    }
}
