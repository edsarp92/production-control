/*
package com.voksel.electric.pc.component;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TreeMainMenu extends Tree {

	private static final long serialVersionUID = -8051428557169911904L;
	
	Treechildren root = new Treechildren();

	public HashMap<String, Treeitem> mapTree;
	
	public TreeMainMenu() {
		this.setZclass("z-vfiletree");
		this.appendChild(root);
	}
	
	public void generateMenuTree(List<DTOMap> menus){
		try{
			EventListener selectItem=new EventListener() {
				public void onEvent(Event e) throws Exception {
					onSelectItem((Treeitem)e.getTarget());
				}
			};
			root.getChildren().clear();
			mapTree = new HashMap<String, Treeitem>();
			for (DTOMap menuMap : menus) {
				Treeitem item=new Treeitem();
				item.setAttribute("MENU_NM", menuMap.getString("NAME"));
				item.setAttribute("PARENT_ID", menuMap.getString("MENU_PARENT"));
				item.setAttribute("FORM_ID", menuMap.getString("FORM_ID"));
				item.setAttribute("PARAM", menuMap.get("PARAM"));
				item.setAttribute("DATA", menuMap);
				item.setValue(menuMap.getString("ZUL_FILE"));
				if(item.getValue()!=null){
					item.addEventListener(Events.ON_DOUBLE_CLICK, selectItem);
					item.addEventListener(Events.ON_OK, selectItem);
				}else{
					item.setId("tree"+menuMap.getString("MENU_ID"));
					item.setWidgetListener("onClick", "this.$f('"+item.getId()+"').setOpen(true)");
				}
				
				Treerow rowTree=new Treerow();
				rowTree.appendChild(new Treecell(menuMap.getString("MENU_ID")+" - "+menuMap.getString("NAME")));
				item.appendChild(rowTree);
				
				mapTree.put(menuMap.getString("MENU_ID"), item);
			}
			for (DTOMap menuMap : menus) {
				Treeitem item=(Treeitem)mapTree.get(menuMap.getString("MENU_ID"));
				if (menuMap.get("MENU_PARENT")!=null){
					Treeitem itemParent=(Treeitem)mapTree.get(menuMap.getString("MENU_PARENT"));
					if (itemParent==null) continue;
					Treechildren parent=(Treechildren)itemParent.getAttribute("PARENT");
					if (parent==null){
						parent=new Treechildren();
						itemParent.setOpen(false);
						itemParent.appendChild(parent);
						itemParent.setAttribute("PARENT", parent);
					}
					parent.appendChild(item);
				} else {
					root.appendChild(item);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void onFilterMenu(String name){
		Collection<Treeitem> values = mapTree.values();
		if(name != null && !name.isEmpty()){
			root.getChildren().clear();
			for (Treeitem item : values) {
				if(((String)item.getAttribute("MENU_NM")).toUpperCase().contains(name)){
					if(item.getValue() != null) root.appendChild(item);
				}
			}
		}
	}
	
	public void onSelectItem(Treeitem item) {
		item.setOpen(true);
		if (item!=null && item.getValue()!=null) {
			Include include = (Include) getSpaceOwner().getFellow("xcontents");
			include.setSrc((String) item.getValue());
			include.invalidate();
			Panel panel = (Panel) getSpaceOwner().getFellow("panelCenter");
			panel.setTitle(item.getLabel());
		}
	}
}
*/
