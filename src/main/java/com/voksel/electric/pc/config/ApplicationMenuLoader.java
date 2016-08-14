package com.voksel.electric.pc.config;

import com.voksel.electric.pc.component.AbstractMenuLoader;
import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class ApplicationMenuLoader extends AbstractMenuLoader{

    @Autowired
    AuthenticationService authService;
	
    @Override
    protected Collection<MenuTreeItem> initializeMenuItems() {

        return authService.loadMenuItems();
    }

    @Override
    public Collection<MenuTreeItem> loadByRole(String s)
    {
        return authService.loadMenuItemsByRole(s);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
