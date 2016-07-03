package com.voksel.electric.pc.service;

import com.voksel.electric.pc.component.MenuTreeItem;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Tree;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Edsarp on 7/3/2016.
 */
@Service
public interface MenuLoaderService {

    void loadMenu(Tree var1);

    Collection<MenuTreeItem> loadByRole(String var1);

    void doLoadByRole(Tree var1, String var2);

    void setMenuTreeItems(Collection<MenuTreeItem> var1);

    void openContent(String var1, Map<String, Object> var2);

    void openByCode(String var1);
}
