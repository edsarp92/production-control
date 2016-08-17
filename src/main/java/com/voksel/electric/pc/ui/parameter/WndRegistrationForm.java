package com.voksel.electric.pc.ui.parameter;

import com.voksel.electric.pc.common.CompareUtil;
import com.voksel.electric.pc.common.ComponentUtil;
import com.voksel.electric.pc.common.MessageBox;
import com.voksel.electric.pc.domain.CompareData;
import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.service.SetupSystemService;
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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Paginal;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
@Scope("desktop")
public class WndRegistrationForm extends SelectorComposer<Component> {
	final Logger log = LoggerFactory.getLogger(WndRegistrationForm.class);
	@Wire
	Window wnd;
	@Wire
	Textbox txtFormId;
	@Wire
	Textbox txtFormNm;
	@Wire
	Textbox txtZulFile;
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
	SetupSystemService setupSystemService;
	Boolean onLoad = false;

	Listitem item;
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		txtFormId.addEventListener(Events.ON_OK, new EventListener<Event>() {
			public void onEvent(Event event){
				try{
					doSearch();
				}catch (Exception e)
				{
					log.error("Event txtFormId on ok ", e);
				}
			}
		});

		list.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener<Event>() {
			public void onEvent(Event event){
				try{
					doEdit();
				}catch (Exception e)
				{
					log.error("Event list on double click ", e);
				}
			}
		});

		btnSave.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event){
				try{
					doSave();
				}catch (Exception e)
				{
					log.error("Event btnSave on click ", e);
				}
			}
		});

		btnDelete.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event) {
				try{
					doDelete();
				}catch (Exception e)
				{
					log.error("Event btnDelete on click ", e);
				}
			}
		});

		btnReset.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			public void onEvent(Event event)  {
				try{
					doReset();
				}catch (Exception e)
				{
					log.error("Event btnDelete on click ", e);
				}
			}
		});
		paging.addEventListener("onPaging", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) {
				try {
					doLoadData();
				} catch (Exception e) {
					log.error("Event list on Paging  ", e);
				}
			}
		});
		initWndRegistrationForm();
	}

	private void initWndRegistrationForm(){
		try{
			doReset();
		}catch (Exception e){
			log.error("Initialization on WndRegistrationForm: ",e);
		}
	}
	private void doSave() throws Exception{
		if (doValidation()) {
			if(onLoad==true){
				doUpdate();
			}else{
				doInsert();
			}
		}
	}

	private void doEdit(){
		item = list.getSelectedItem();
		if (item != null){
			Form form = (Form)item.getAttribute("DATA");
			doFillDetailForm(form);
			onLoad = true;
			txtFormId.setDisabled(true);
			btnDelete.setDisabled(false);
		}
	}

	private void doSearch() throws Exception{
		Form form= setupSystemService.findOneForm((String)ComponentUtil.getValue(txtFormId));
		if(form !=null) {
			List<Form> forms=new ArrayList<>();
			forms.add(form);
			paging.setTotalSize(1);
			doFillTable(forms);
		}
	}

	private void doInsert() throws  Exception{
		if (!isExists()) {
			setupSystemService.saveForm(populationData());
			MessageBox.showInformation("Data berhasil disimpan");
		}else{
			MessageBox.showError("Data telah ada di database");
		}
		doReset();
	}

	private void doUpdate() throws  Exception{
		List<CompareData> compareDatas=CompareUtil.doCompare(Form.class,(Form)item.getAttribute("DATA"),populationData());
		//setupSystemService.saveForm(populationData());
		MessageBox.showInformation("Data berhasil diupdate");
		doReset();
	}

	private void doDelete() throws Exception
	{
		setupSystemService.deleteForm((String)ComponentUtil.getValue(txtFormId));
		MessageBox.showInformation("Data berhasil dihapus");
		doReset();
	}

	private void doReset() throws Exception{
		ComponentUtil.clear(wnd);
		doLoadData();
		onLoad = false;
		txtFormId.setDisabled(false);
		btnDelete.setDisabled(true);
		txtFormId.setFocus(true);
	}
	private void doLoadData() throws Exception{
		Page<Form> page=setupSystemService.findAllForm(createPageRequest());
		paging.setTotalSize((int)page.getTotalElements());
		doFillTable(page.getContent());
	}

	private void doFillTable(List<Form> page) throws  Exception{
		list.getItems().clear();
		if (page != null){
			for(Form form : page){
				Listitem item = new Listitem();
				item.setAttribute("DATA", form);
				item.appendChild(new Listcell(form.getFormId()));
				item.appendChild(new Listcell(form.getFormName()));
				item.appendChild(new Listcell(form.getUrl()));
				list.appendChild(item);
			}
		}
	}

	private boolean isExists() throws Exception{
		return setupSystemService.existsForm((String)ComponentUtil.getValue(txtFormId));
	}
	private void doFillDetailForm(Form form){
		ComponentUtil.setValue(txtFormId, form.getFormId());
		ComponentUtil.setValue(txtFormNm, form.getFormName());
		ComponentUtil.setValue(txtZulFile, form.getUrl());
	}
	private Form populationData(){
		Form form =new Form();
		form.setFormId((String) ComponentUtil.getValue(txtFormId));
		form.setFormName((String) ComponentUtil.getValue(txtFormNm));
		form.setUrl((String) ComponentUtil.getValue(txtZulFile));
		return form;
	}
	private Pageable createPageRequest(){
		int pageNo = paging.getActivePage();
		int totalSize = paging.getTotalSize();
		int pageCount = paging.getPageCount();
		int pageSize = paging.getPageSize();
		log.debug("pageNo: {}, totalSize: {}, pageCount: {}, pageSize: {}", pageNo, totalSize, pageCount, pageSize);
		return new PageRequest(pageNo, pageSize, Sort.Direction.ASC, "formId");
	}
	private boolean doValidation(){
		if (ComponentUtil.getValue(txtFormId) == null || ComponentUtil.getValue(txtFormId).equals("")) {
			throw new WrongValueException(txtFormId, "Form id harus diisi.");
		}else if (txtFormId.getText().length() < 4  || txtFormId.getText().length() > 4) {
			throw new WrongValueException(txtFormId, "Form id harus 4 digit.");
		}
		return true;
	}
}
