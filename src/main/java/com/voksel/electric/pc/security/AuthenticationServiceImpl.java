
package com.voksel.electric.pc.security;

import com.voksel.electric.pc.component.MenuTreeItem;
import com.voksel.electric.pc.domain.MenuItem;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.domain.entity.RoleMenu;
import com.voksel.electric.pc.domain.entity.User;
import com.voksel.electric.pc.domain.entity.UserRole;
import com.voksel.electric.pc.repository.RoleMenuRepository;
import com.voksel.electric.pc.repository.RoleRepository;
import com.voksel.electric.pc.repository.UserRepository;
import com.voksel.electric.pc.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	@Autowired
    UserRepository userRepository;
    @Autowired
    RoleMenuRepository roleMenuRepository;
    @Autowired
    private  RoleRepository userRoleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);
        if(null == user){
            throw new UsernameNotFoundException("No user present with username: "+username);
        }else{
            List<String> role=new ArrayList<>();
            for(UserRole userRole :user.getRoles()){
                role.add(userRole.getRoleId());
            }
            return new CustomUserDetails(user,role);
        }
    }

	@Override
	public Collection<MenuTreeItem> loadMenuItems() {
		List<MenuTreeItem> menuItems = new ArrayList<>();
		List<RoleMenu> roleMenus = roleMenuRepository.findAllJoinMenu();
		for (RoleMenu roleMenu : roleMenus) {
            String menuId=roleMenu.getMenuId();
            String menuName=roleMenu.getMenu().getMenuName();
            String url=(roleMenu.getMenu().getForm()!=null?roleMenu.getMenu().getForm().getUrl():"");
            String parentId=roleMenu.getMenu().getParentId();
            String formId=(roleMenu.getMenu().getForm() !=null?roleMenu.getMenu().getForm().getFormId():"");
			menuItems.add(new MenuItem(menuId,menuName,url, (url == null || url.equals("")) ? false : true,
                    parentId, formId));
		}
		return menuItems;
	}

	@Override
	public Collection<MenuTreeItem> loadMenuItemsByRole(String roleId) {
        List<MenuTreeItem> menuItems = new ArrayList<>();
        List<RoleMenu> roleMenus = roleMenuRepository.findAllJoinMenuRoleId(roleId);
        for (RoleMenu roleMenu : roleMenus) {
            String menuId=roleMenu.getMenuId();
            String menuName=roleMenu.getMenu().getMenuName();
            String url=(roleMenu.getMenu().getForm()!=null?roleMenu.getMenu().getForm().getUrl():"");
            String parentId=roleMenu.getMenu().getParentId();
            String formId=(roleMenu.getMenu().getForm() !=null?roleMenu.getMenu().getForm().getFormId():"");
            menuItems.add(new MenuItem(menuId,menuName,url, (url == null || url.equals("")) ? false : true,
                    parentId, formId));
        }
        return menuItems;

	}

}

