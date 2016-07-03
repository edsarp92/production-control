package com.voksel.electric.pc.component;

import com.voksel.electric.pc.security.AuthenticationService;
import com.voksel.electric.pc.service.MenuLoaderService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by Edsarp on 7/3/2016.
 */
public abstract class AbstractMenuLoader implements InitializingBean, MenuLoaderService {
    private Component content;
    protected Collection<MenuTreeItem> menuTreeItems;

    @Autowired
    private AuthenticationService authService;
    public AbstractMenuLoader() {
    }

    public void setContent(Component content) {
        this.content = content;
    }

    public Component getContent() {
        return this.content;
    }

    @Deprecated
    protected  Collection<MenuTreeItem> initializeMenuItems(){
        return authService.getMenuItems();
    }

    public void setMenuTreeItems(Collection<MenuTreeItem> menuTreeItems) {
        this.menuTreeItems = menuTreeItems;
    }

    protected MenuTreeNode buildRootMenu() {
        MenuTreeNode root = new MenuTreeNode((MenuTreeItem)null, new MenuTreeNodeCollection());
        LinkedList treeItems = new LinkedList(this.menuTreeItems);
        Iterator i$ = treeItems.iterator();

        while(i$.hasNext()) {
            MenuTreeItem treeItem = (MenuTreeItem)i$.next();
            if(StringUtils.isBlank(treeItem.getParentId())) {
                root.add(this.buildMenu(treeItem, treeItems));
            }
        }

        return root;
    }

    public void afterPropertiesSet() throws Exception {
        this.menuTreeItems = this.initializeMenuItems();
        Validate.noNullElements(this.menuTreeItems, "Failed to initialize bean, getMenuItems() is null", new Object[0]);
    }

    public void loadMenu(Tree tree) {
        tree.setModel(new DefaultTreeModel(this.buildRootMenu()));
    }

    public void openContent(String url, Map<String, Object> parameters) {
        if(this.getContent() != null) {
            Component component = this.getContent();
            Label lblForm = (Label)component.getRoot().getFellowIfAny("lblForm");
            if(lblForm != null) {
                lblForm.setValue(url);
            }

            Components.removeAllChildren(component);
            Executions.createComponents(url, component, parameters);
        }

    }

    public void openByCode(String code) {
        Components.removeAllChildren(this.getContent());
        Iterator i$ = this.menuTreeItems.iterator();

        while(i$.hasNext()) {
            MenuTreeItem menuTreeItem = (MenuTreeItem)i$.next();
            if(menuTreeItem.getId().equals(code) && menuTreeItem.isProgram()) {
                Label lblForm = (Label)this.getContent().getRoot().getFellowIfAny("lblForm");
                if(lblForm != null) {
                    lblForm.setValue(menuTreeItem.getUrl());
                }

                this.openContent(menuTreeItem.getUrl(), Collections.emptyMap());
                break;
            }
        }

    }

    public abstract Collection<MenuTreeItem> loadByRole(String var1);

    public void doLoadByRole(Tree tree, String roleCode) {
        this.menuTreeItems = this.loadByRole(roleCode);
        this.loadMenu(tree);
    }

    protected MenuTreeNode buildMenu(MenuTreeItem firstLevelItem, List<MenuTreeItem> childItems) {
        if(firstLevelItem.isProgram()) {
            return new MenuTreeNode(firstLevelItem);
        } else {
            MenuTreeNode subMenu = new MenuTreeNode(firstLevelItem, new MenuTreeNodeCollection());
            Iterator i$ = childItems.iterator();

            while(i$.hasNext()) {
                MenuTreeItem childItem = (MenuTreeItem)i$.next();
                if(firstLevelItem.getId().equals(childItem.getParentId())) {
                    subMenu.add(this.buildMenu(childItem, childItems));
                }
            }

            return subMenu;
        }
    }
}
