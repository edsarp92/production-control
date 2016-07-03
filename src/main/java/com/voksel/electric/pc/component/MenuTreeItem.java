package com.voksel.electric.pc.component;

/**
 * Created by Edsarp on 7/3/2016.
 */
public interface MenuTreeItem {
    String getId();

    String getUrl();

    String getMenuName();

    boolean isProgram();

    String getParentId();
}
