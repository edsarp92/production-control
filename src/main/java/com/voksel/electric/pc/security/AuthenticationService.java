package com.voksel.electric.pc.security;


import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.domain.Roles;

import java.util.Collection;
import java.util.List;

public interface AuthenticationService{
      Collection<MenuTreeItem> getMenuItems();
  	  Collection<MenuTreeItem> getMenuItemsByRole(String role);
      List<Roles> getRoles(String roleId);
}
