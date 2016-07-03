package com.voksel.electric.pc.component;

import org.zkoss.zul.DefaultTreeNode;

/**
 * Created by Edsarp on 7/3/2016.
 */
public class MenuTreeNode extends DefaultTreeNode<MenuTreeItem> {
    private static final long serialVersionUID = -8085873079938209759L;
    private boolean open = false;

    public MenuTreeNode(MenuTreeItem data, MenuTreeNodeCollection<MenuTreeItem> children, boolean open) {
        super(data, children);
        this.setOpen(open);
    }

    public MenuTreeNode(MenuTreeItem data, MenuTreeNodeCollection<MenuTreeItem> children) {
        super(data, children);
    }

    public MenuTreeNode(MenuTreeItem data) {
        super(data);
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}