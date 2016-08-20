package com.voksel.electric.pc.ui.parameter;

import com.voksel.electric.pc.common.ComponentUtil;
import com.voksel.electric.pc.common.MessageBox;
import com.voksel.electric.pc.domain.entity.Role;
import com.voksel.electric.pc.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Paginal;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
@Scope("desktop")
public class WndRegistrationRole extends SelectorComposer<Component> {
	final Logger log = LoggerFactory.getLogger(WndRegistrationRole.class);
	@Wire
	Window wnd;
	@Wire
	Textbox txtRoleId;
	@Wire
	Textbox txtRoleNm;
	@Wire
	Listbox list;
	@Wire
	Paginal paging;
	@Wire
	Button btnSave;
	@Wire
	Button btnDelete;
	@Wire
	Button btnReset;

	@Autowired
	ParameterService parameterService;
	Boolean onLoad = false;

	Listitem item;
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		initWndRegistrationForm();
	}

	@Listen("onOk = #txtRoleId")
	public void onSearch(Event event){
		try{
			Role role= parameterService.findOneRole((String)ComponentUtil.getValue(txtRoleId));
			if(role !=null) {
				List<Role> roles=new ArrayList<>();
				roles.add(role);
				paging.setTotalSize(1);
				doFillTable(roles);
			}
		}catch (Exception e)
		{
			log.error("Textbox Role Id on ok ", e);
		}
	}

	@Listen("onDoubleClick = #list")
	public void onEdit(Event event){
		try{
			item = list.getSelectedItem();
			if (item != null) {
				Role role = (Role) item.getAttribute("DATA");
				doFillDetailForm(role);
				onLoad = true;
				txtRoleId.setDisabled(true);
				btnDelete.setDisabled(false);
			}
		}catch (Exception e){
			log.error("Listbox on double click ", e);
		}
	}

	@Listen("onClick =#btnSave")
	public void onSave(Event event){
		try{
			if (doValidation()) {
				if(onLoad==true){
					doUpdate();
				}else{
					doInsert();
				}
			}
		}catch (Exception e){
			log.error("Button save on click ", e);
		}
	}

	@Listen("onClick =#btnDelete")
	public void onDelete(Event event){
		try{
			if(ComponentUtil.getValue(txtRoleId)!=null || !ComponentUtil.getValue(txtRoleId).equals("")) {
				parameterService.deleteRole((String) ComponentUtil.getValue(txtRoleId));
				MessageBox.showInformation("Data berhasil dihapus");
				doReset();
			}else{
				MessageBox.showInformation("Data tidak ada yang terpilih!");
			}
		}catch (Exception e){
			log.error("Button delete on clik ", e);
		}
	}

	@Listen("onClick =#btnReset")
	public void onReset(Event event){
		try{
			doReset();
		}catch (Exception e)
		{
			log.error("Event btnDelete on click ", e);
		}
	}

	@Listen("onPaging =#paging")
	public void onPaging(Event event){
		try {
			doLoadData();
		} catch (Exception e) {
			log.error("Event list on Paging  ", e);
		}
	}

	private void initWndRegistrationForm(){
		try{
			doReset();
		}catch (Exception e){
			log.error("Initialization on WndRegistrationForm: ",e);
		}
	}

	private void doInsert() throws  Exception{
		if (!isExists()) {
			parameterService.saveRole(populationData());
			MessageBox.showInformation("Data berhasil disimpan");
		}else{
			MessageBox.showError("Data sudah tersimpan sebelumnya!");
		}
		doReset();
	}

	private void doUpdate() throws  Exception{
		parameterService.saveRole(populationData());
		MessageBox.showInformation("Data berhasil diupdate");
		doReset();
	}

	private void doReset() throws Exception{
		ComponentUtil.clear(wnd);
		doLoadData();
		onLoad = false;
		txtRoleId.setDisabled(false);
		btnDelete.setDisabled(true);
		txtRoleId.setFocus(true);
	}

	private void doLoadData() throws Exception{
		Page<Role> page= parameterService.findAllRole(createPageRequest());
		paging.setTotalSize((int)page.getTotalElements());
		doFillTable(page.getContent());
	}

	private void doFillTable(List<Role> page) throws  Exception{
		list.getItems().clear();
		if (page != null){
			for(Role role : page){
				Listitem item = new Listitem();
				item.setAttribute("DATA", role);
				item.appendChild(new Listcell(role.getRoleId()));
				item.appendChild(new Listcell(role.getRoleName()));
				list.appendChild(item);
			}
		}
	}

	private boolean isExists() throws Exception{
		return parameterService.existsForm((String)ComponentUtil.getValue(txtRoleId));
	}

	private void doFillDetailForm(Role role){
		ComponentUtil.setValue(txtRoleId,role.getRoleId());
		ComponentUtil.setValue(txtRoleNm, role.getRoleName());
	}

	private Role populationData(){
		Role role=new Role();
		role.setRoleId((String) ComponentUtil.getValue(txtRoleId));
		role.setRoleName((String) ComponentUtil.getValue(txtRoleNm));
		return role;
	}

	private Pageable createPageRequest(){
		int pageNo = paging.getActivePage();
		int totalSize = paging.getTotalSize();
		int pageCount = paging.getPageCount();
		int pageSize = paging.getPageSize();
		log.debug("pageNo: {}, totalSize: {}, pageCount: {}, pageSize: {}", pageNo, totalSize, pageCount, pageSize);
		return new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "roleId");
	}

	private boolean doValidation(){
		if (ComponentUtil.getValue(txtRoleId) == null || ComponentUtil.getValue(txtRoleId).equals("")) {
			throw new WrongValueException(txtRoleId, "Role ID harus diisi");
		}else if (txtRoleId.getText().length() < 2  || txtRoleId.getText().length() > 2) {
			throw new WrongValueException(txtRoleId, "Role ID harus 4 digit");
		}
		return true;
	}
}
