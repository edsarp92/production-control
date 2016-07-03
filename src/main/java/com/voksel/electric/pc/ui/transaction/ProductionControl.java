package com.voksel.electric.pc.ui.transaction;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@org.springframework.stereotype.Component
@Scope("desktop")
public class ProductionControl extends SelectorComposer<Component>{
	
	@Wire Window wnd;

	
	
	/*@Autowired MasterServices masterService;
	@Autowired AuthenticationService authService;*/
	
	Boolean onLoad = false;
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		/*txtUserId.addEventListener(Events.ON_OK, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
        		//doSearch();
            }
        });

		btnSave.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
        		//doSave();
            }
        });
		
		btnDelete.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
        		//doDelete();
            }
        });
		
		btnReset.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
        		//doReset();
            }
        });*/
		
		//doLoadCombo();
		//doReset();
	}
	
	/*private void doLoadCombo() {
		cmbRole.getItems().clear();
		cmbRole.setSelectedIndex(-1);
		List<DTOMap> listData = (List<DTOMap>) masterService.getDataMaster("SELECT * FROM CFG_ROLE WHERE ROLEID <> 'XX'", null);
		Comboitem item = new Comboitem();
		cmbRole.appendChild(item);
		for (DTOMap map : listData) {
			item = new Comboitem();
			item.setLabel(map.getString("ROLEID") + " - " + map.getString("ROLENM"));
			item.setValue(map.getString("ROLEID"));
			cmbRole.appendChild(item);
		}
	}
	
	private void doSave(){
		if (doValidation()) {
			if(onLoad){
				doUpdate();
			}else{
				doInsert();
			}
		}
	}
	
	private void doEdit(){
		Listitem item = list.getSelectedItem();
		if (item != null){
			DTOMap data = (DTOMap)item.getAttribute("DATA");
			ComponentUtil.setValue(cmbRole, data.get("ROLEID"));
			ComponentUtil.setValue(txtUserId, data.get("USERID"));
			ComponentUtil.setValue(txtUserNmUser, data.get("USERNM"));
			ComponentUtil.setValue(txtJmlFail, data.get("AMTFAIL"));
			ComponentUtil.setValue(txtLmtFail, data.get("LMTFAIL"));
			ComponentUtil.setValue(grpStatus, data.get("STATUS"));
			onLoad = true;
			txtUserId.setDisabled(true);
			btnDelete.setDisabled(false);
		}
	}
	
	private void doSearch(){
		DTOMap datas = new DTOMap();
		datas.put("USERID", ComponentUtil.getValue(txtUserId));
		doRefreshTable(masterService.getDataMaster("MST_USER"));
	}
	
	private void doInsert(){
		try {
			DTOMap datas = new DTOMap();
			datas.put("USERID", ComponentUtil.getValue(txtUserId));
			if (!masterService.isExist(datas, "MST_USER")) {
				datas.put("ROLEID", ComponentUtil.getValue(cmbRole));
				datas.put("USERNM", ComponentUtil.getValue(txtUserNmUser));
				datas.put("PWD", Cryptograph.MD5((String) ComponentUtil.getValue(txtUserId)));
				datas.put("AMTFAIL", ComponentUtil.getValue(txtJmlFail));
				datas.put("LMTFAIL", ComponentUtil.getValue(txtLmtFail));
				datas.put("STATUS", ComponentUtil.getValue(grpStatus));
				datas.put("CRTUSER", authService.getUserDetails().getUserId());
				datas.put("CRTDATE", new Date());
				masterService.insertData(datas, "MST_USER");
				MessageBox.showInformation("Data berhasil di simpan.\nPassword sama dengan user id.");
			}else{
				MessageBox.showError("Data telah ada di database");
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.showError(e.getMessage());
		}
		doReset();
	}
	
	private void doUpdate(){
		try {
			DTOMap datas = new DTOMap();
			datas.put("USERID", ComponentUtil.getValue(txtUserId));
			datas.put("ROLEID", ComponentUtil.getValue(cmbRole));
			datas.put("USERNM", ComponentUtil.getValue(txtUserNmUser));
			datas.put("AMTFAIL", ComponentUtil.getValue(txtJmlFail));
			datas.put("LMTFAIL", ComponentUtil.getValue(txtLmtFail));
			datas.put("STATUS", ComponentUtil.getValue(grpStatus));
			datas.put("UPDUSER", authService.getUserDetails().getUserId());
			datas.put("UPDDATE", new Date());
			datas.put("PK", "USERID");
			masterService.updateData(datas, "MST_USER");
			MessageBox.showInformation("Data berhasil di update");
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.showError(e.getMessage());
		}
		doReset();
	}
	
	private void doDelete(){
		try {
			DTOMap datas = new DTOMap();
			datas.put("USERID", ComponentUtil.getValue(txtUserId));
			datas.put("PK", "USERID");
			masterService.deleteData(datas, "MST_USER");
			MessageBox.showInformation("Data berhasil di hapus");
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.showError(e.getMessage());
		}
		doReset();
	}
	
	private void doReset(){
		ComponentUtil.clear(wnd);
		doRefreshTable(masterService.getDataMaster("SELECT * FROM MST_USER WHERE ROLEID <> 'XX'", new Object[]{}));
		txtUserNmUser.setValue("");
		onLoad = false;
		txtUserId.setDisabled(false);
		btnDelete.setDisabled(true);
		txtUserId.setFocus(true);
		
	}
	
	private void doRefreshTable(List<DTOMap> datas){
		list.getItems().clear();
		if (datas != null && datas.size() > 0){
			for(DTOMap dtoResult : datas){
				Listitem item = new Listitem();
				item.setAttribute("DATA", dtoResult);
				item.appendChild(new Listcell(dtoResult.getString("USERID")));
				item.appendChild(new Listcell(dtoResult.getString("USERNM")));
				list.appendChild(item);
			}
		}
	}
	
	private boolean doValidation(){
		if (ComponentUtil.getValue(txtUserId) == null || ComponentUtil.getValue(txtUserId).equals("")) {
			throw new WrongValueException(txtUserId, "User id harus diisi.");
		}else if (ComponentUtil.getValue(txtUserNmUser) == null || ComponentUtil.getValue(txtUserNmUser).equals("")) {
			throw new WrongValueException(txtUserNmUser, "Username harus diisi.");
		}else if (ComponentUtil.getValue(cmbRole) == null || ComponentUtil.getValue(cmbRole).equals("")) {
			throw new WrongValueException(cmbRole, "Role id harus diisi.");
		}else if (txtUserId.getText().length() < 5 || txtUserId.getText().length() > 5) {
			throw new WrongValueException(txtUserId, "User ID harus 5 digit.");
		}
		return true;
	}*/
}
