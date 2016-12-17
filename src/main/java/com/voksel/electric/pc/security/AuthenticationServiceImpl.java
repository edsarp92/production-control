
package com.voksel.electric.pc.security;

import com.voksel.electric.pc.common.GlobalVariable;
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
import javax.inject.Inject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	@Inject
    UserRepository userRepository;
    @Inject
    RoleMenuRepository roleMenuRepository;
    @Inject
    private  RoleRepository userRoleRepository;
    private GlobalVariable globel = GlobalVariable.getInstance();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userRepository.findOneByUserName(userName);
        if(null == user){
            throw new UsernameNotFoundException("No user present with username: "+userName);
        }else{
            List<String> role=new ArrayList<>();
            for(UserRole userRole :user.getRoles()){
                role.add(userRole.getRoleId());
            }
            globel.put(user.getUserName(),user);
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

