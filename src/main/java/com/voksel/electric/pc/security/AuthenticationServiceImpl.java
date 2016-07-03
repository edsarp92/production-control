/*
package com.security;

import com.component.MenuTreeItem;
import com.domain.MenuItem;
import com.domain.Menus;
import com.domain.UserRole;
import com.repository.MenusRepository;
import com.repository.UserRepository;
import com.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRolesRepository roleRepository;
	@Autowired
	MenusRepository menuRepository;
	

	@Override
	public Collection<MenuTreeItem> getMenuItems() {
		List<MenuTreeItem> menuItems = new ArrayList<MenuTreeItem>();
		List<Menus> menus = menuRepository.findItemsMenu();
		for (Menus menu : menus) {
			menuItems.add(new MenuItem(menu.getMenuId(), menu.getMenuName(), menu.getUrl(),
					(menu.getUrl() == null || menu.getUrl().equals("")) ? false : true , menu.getMenuParent(), menu.getFormId()));
		}
		return menuItems;
	}

	@Override
	public Collection<MenuTreeItem> getMenuItemsByRole(String role) {
		List<MenuTreeItem> menuItems = new ArrayList<MenuTreeItem>();
		List<Menus> menus = menuRepository.findItemsMenuByRoleId(role);
		for (Menus menu : menus) {
			menuItems.add(new MenuItem(menu.getMenuId(), menu.getMenuName(), menu.getUrl(), 
					(menu.getUrl() == null || menu.getUrl().equals("")) ? false : true , menu.getMenuParent(), menu.getFormId()));
		}
		return menuItems;
	}
	

	public UserDetails getUserDetails() {
		return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@Override
	public List<UserRole> getRoles(String roleId){
		return roleRepository.findAllByRoleId(roleId);
	}
}

*/
