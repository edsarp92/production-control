package com.voksel.electric.pc.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.Disable;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Window;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by edsarp on 8/21/16.
 */
public abstract class PopupUI<T extends Component> extends SelectorComposer<T> implements EventListener<Event> {
    private Button btnOk;
    private Button btnClose;
    private Window container;
    private EventListener<Event> eventListener;
    private Component parent;

    public PopupUI() {
    }

    public void setEventListener(EventListener<Event> eventListener) {
        this.eventListener = eventListener;
    }

    protected void fireEventPopupButton() throws Exception {
        if(this.eventListener != null) {
            this.eventListener.onEvent(new Event("onClick", this.getBtnOk(), this.returnValue()));
        }

        this.getContainer().detach();
    }

    public void onEvent(Event event) throws Exception {
        if(this.onBtnClicked(((Button)event.getTarget()).getLabel())) {
            if(this.eventListener != null) {
                this.eventListener.onEvent(new Event(event.getName(), event.getTarget(), this.returnValue()));
            }

            this.getContainer().detach();
        }

    }

    protected boolean onBtnClicked(String eventName) {
        return true;
    }

    public void doAfterCompose(T comp) throws Exception {
        super.doAfterCompose(comp);
        this.parent = comp.getParent();
        comp.detach();
        this.getContainer().appendChild(comp);
        comp.setParent(this.getContainer());
        Separator separator = new Separator();
        separator.setSpacing("15px");
        this.getContainer().appendChild(separator);
        this.attachButton();
        this.enableEnterAsTab();
    }

    private void enableEnterAsTab() {
        Iterable components = this.container.queryAll(".input");
        final LinkedList componentList = new LinkedList();
        Iterator i$ = components.iterator();

        while(i$.hasNext()) {
            final Component component = (Component)i$.next();
            componentList.add(component);
            component.addEventListener("onOK", new EventListener() {
                public void onEvent(Event event) throws Exception {
                    int index = componentList.indexOf(component);

                    try {
                        ++index;
                        if(index < componentList.size()) {
                            Component ignored = (Component)componentList.get(index);
                            if(ignored instanceof Disable) {
                                while(((Disable)ignored).isDisabled()) {
                                    ++index;
                                    ignored = (Component)componentList.get(index);
                                }
                            }

                            if(ignored instanceof HtmlBasedComponent) {
                                ((HtmlBasedComponent)ignored).focus();
                            }
                        }
                    } catch (IndexOutOfBoundsException var4) {
                        ;
                    }

                }
            });
        }

    }

    private void attachButton() {
        Hbox hbox = new Hbox();
        hbox.setStyle("margin-left:auto; margin-right:auto");
        hbox.appendChild(this.getBtnOk());
        hbox.appendChild(this.getBtnClose());
        this.getContainer().appendChild(hbox);
        this.getBtnOk().addEventListener("onClick", this);
        this.getBtnClose().addEventListener("onClick", this);
    }

    public Window getContainer() {
        if(this.container == null) {
            this.container = this.buildContainer();
        }

        return this.container;
    }

    public abstract Object returnValue();

    protected Window buildContainer() {
        Window window = new Window();
        this.parent.appendChild(window);
        window.setMode(Window.Mode.MODAL);
        window.setClosable(true);
        return window;
    }

    public Button getBtnOk() {
        if(this.btnOk == null) {
            this.btnOk = new Button("OK");
            this.btnOk.setSclass("input");
        }

        return this.btnOk;
    }

    public Button getBtnClose() {
        if(this.btnClose == null) {
            this.btnClose = new Button("Close");
        }

        return this.btnClose;
    }
}
