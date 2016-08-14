package com.voksel.electric.pc.ui;

import com.voksel.electric.pc.component.AbstractMenuLoader;
import com.voksel.electric.pc.component.MenuLoader;
import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.component.MenuTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class Main1Controller extends SelectorComposer<Borderlayout> {
    @Autowired
    MenuLoader menuLoader;
   /* @Autowired
    HasListBranch branchService;*/
    @Autowired
    Environment environment;
    @Wire
    Tree treeMenu;
    @Wire
    Div divContent;
    @Wire
    Grid westInfoGrid;
    @Wire
    Html htmlRunText;
    @Wire
    Label lblForm;
    @Wire
    Label lblUser;
    @Wire
    Label lblDate;
    @Wire
    Label lblTime;
    @Wire
    Combobox cmbRole;
    @Wire
    Combobox cmbCabang;
   // private ListModelList<Branch> branchesModel;
    private ListModelList<GrantedAuthority> roleModels;

    public Main1Controller() {
    }

    public void doAfterCompose(Borderlayout comp) throws Exception {
        super.doAfterCompose(comp);
        this.westInfoGrid.setVisible(Boolean.parseBoolean(this.environment.getProperty("application.show-left-info-panel", "true")));
        this.htmlRunText.setVisible(Boolean.parseBoolean(this.environment.getProperty("application.show-running-text", "true")));
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
        Clients.evalJavaScript("startClock(\'" + sf.format(new Date(System.currentTimeMillis())) + "\')");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.lblUser.setValue(userDetails.getUsername());
        this.lblDate.setValue((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
        if(this.menuLoader != null) {
            ((AbstractMenuLoader)this.menuLoader).setContent(this.divContent);
            this.menuLoader.doLoadByRole(this.treeMenu, userDetails.getAuthorities().stream().findFirst().toString());
        }

        this.roleModels = new ListModelList(new ArrayList(SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
        Iterator i$ = this.roleModels.iterator();

        while(i$.hasNext()) {
            GrantedAuthority branch = (GrantedAuthority)i$.next();
            //if(branch.getAuthority().equals(userDetails.getAuthorities().stream().findFirst().toString())) {
            this.roleModels.setSelection(Collections.singleton(branch));
               // break;
            //}
        }
        this.cmbRole.setModel(this.roleModels);
      /*  if(userDetails.isAdmin()) {
            this.cmbCabang.setDisabled(false);
        }*/

        /*if(this.branchService != null) {
            this.branchesModel = new ListModelList(this.branchService.getListBranch());
            this.cmbCabang.setModel(this.branchesModel);
            i$ = this.branchesModel.iterator();

            while(i$.hasNext()) {
                Branch branch1 = (Branch)i$.next();
                if(userDetails.getBranchId().equals(branch1.getCode())) {
                    this.branchesModel.setSelection(Collections.singleton(branch1));
                    break;
                }
            }
        }*/

        this.treeMenu.addEventListener("onDoubleClick", new EventListener() {
            public void onEvent(Event event) throws Exception {
                Tree tree = (Tree)event.getTarget();
                if(tree.getItemCount() > 0) {
                    if(tree.getSelectedCount() > 0) {
                        Treeitem selectedItem = tree.getSelectedItem();
                        MenuTreeNode node = (MenuTreeNode)selectedItem.getValue();
                        MenuTreeItem item = (MenuTreeItem) node.getData();
                        if(item.isProgram()) {
                            Main1Controller.this.lblForm.setValue(item.getUrl());
                            Main1Controller.this.menuLoader.openByCode(item.getId());
                            Component component = Main1Controller.this.divContent.getFirstChild();
                            Main1Controller.this.enterAsTab(component);
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
       // ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setActiveRole((String)this.cmbRole.getSelectedItem().getValue());
        this.menuLoader.doLoadByRole(this.treeMenu, (String)this.cmbRole.getSelectedItem().getValue());
    }

    @Listen("onChange = #cmbCabang")
    public void onChangeCmbBranch(Event event) {
        Comboitem comboitem = this.cmbCabang.getSelectedItem();
        if(comboitem != null) {
           // ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setBranchId((String)comboitem.getValue());
        }

    }
}
