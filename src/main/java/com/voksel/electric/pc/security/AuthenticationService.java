package com.voksel.electric.pc.security;


import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.domain.entity.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Collection;
import java.util.List;

public interface AuthenticationService extends UserDetailsService{
      Collection<MenuTreeItem> loadMenuItems();
  	  Collection<MenuTreeItem> loadMenuItemsByRole(String role);

}
