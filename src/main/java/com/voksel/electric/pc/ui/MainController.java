package com.voksel.electric.pc.ui;

import com.voksel.electric.pc.component.AbstractMenuLoader;
import com.voksel.electric.pc.component.MenuLoader;
import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.component.MenuTreeNode;
import com.voksel.electric.pc.domain.entity.UserRole;
import com.voksel.electric.pc.security.SecurityUtils;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.ext.Disable;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by edsarp on 8/14/16.
 */
@org.springframework.stereotype.Component
@Scope("desktop")
public class MainController extends SelectorComposer<Borderlayout> {
    @Inject
    MenuLoader menuLoader;
    @Wire
    Tree treeMenu;
    @Wire
    Div divContent;
    @Wire
    Label lblUser;
    @Wire
    Label lblDate;
    @Wire
    Combobox cmbRole;

    public MainController() {
    }

    public void doAfterCompose(Borderlayout comp) throws Exception {
        super.doAfterCompose(comp);
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
        Clients.evalJavaScript("startClock(\'" + sf.format(new Date(System.currentTimeMillis())) + "\')");
        lblUser.setValue(SecurityUtils.getCurrentUserLogin());
        lblDate.setValue((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
        List<UserRole> userRole =(List<UserRole>)SecurityUtils.getUserInfo().getRoles();
        for (UserRole r : userRole) {
            Comboitem item = new Comboitem();
            item.setLabel(r.getRoleId() + " - " + r.getRole().getRoleName());
            item.setValue(r.getRoleId());
            cmbRole.appendChild(item);
        }
        cmbRole.setSelectedIndex(0);
        if(menuLoader != null) {
            ((AbstractMenuLoader)menuLoader).setContent(this.divContent);
            menuLoader.doLoadByRole(treeMenu, cmbRole.getSelectedItem().getValue());
        }

        treeMenu.addEventListener("onDoubleClick", new EventListener() {
            public void onEvent(Event event) throws Exception {
                Tree tree = (Tree)event.getTarget();
                if(tree.getItemCount() > 0) {
                    if(tree.getSelectedCount() > 0) {
                        Treeitem selectedItem = tree.getSelectedItem();
                        MenuTreeNode node = (MenuTreeNode)selectedItem.getValue();
                        MenuTreeItem item = (MenuTreeItem) node.getData();
                        if(item.isProgram()) {
                            MainController.this.menuLoader.openByCode(item.getId());
                            Component component = MainController.this.divContent.getFirstChild();
                            MainController.this.enterAsTab(component);
                        }

                    }
                }
            }
        });
    }

    private void enterAsTab(final Component component) {
        component.addEventListener("onCreate", new EventListener() {
            public void onEvent(Event event) throws Exception {
                Iterable components = component.queryAll(".input");
                final LinkedList componentList = new LinkedList();
                Iterator i$ = components.iterator();

                while(i$.hasNext()) {
                    final Component componentx = (Component)i$.next();
                    componentList.add(componentx);
                    componentx.addEventListener("onOK", new EventListener() {
                        public void onEvent(Event event) throws Exception {
                            int index = componentList.indexOf(componentx);

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
        });
    }

    @Listen("onChange = #cmbRole")
    public void changeMenuRole(Event event) {
        this.menuLoader.doLoadByRole(treeMenu, (String)cmbRole.getSelectedItem().getValue());
    }
}
