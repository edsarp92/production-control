package com.voksel.electric.pc.ui.dialog.parameter;

import com.voksel.electric.pc.common.ComponentUtil;
import com.voksel.electric.pc.component.PopupUI;
import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.service.ParameterService;
import com.voksel.electric.pc.ui.parameter.WndRegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.List;

@org.springframework.stereotype.Component
@Scope("execution")
public class WndDialogBrowseForm extends PopupUI<Grid> {

	final Logger log = LoggerFactory.getLogger(WndDialogBrowseForm.class);
	@Wire
	Textbox txtSearch;
	@Wire
	Listbox listBrowseForm;
	@Autowired
	ParameterService parameterService;
	Form result = null;
	
	public void doAfterCompose(Grid comp) throws Exception {
		super.doAfterCompose(comp);
	}

	@Listen("onOk = #txtSearch")
	public void onOkTxtId(Event event){
		try {
			doSearch();
		}catch (Exception e){
			log.error("Textbox txtId on ok", e);
		}
	}

	@Listen("onClick = #btnSearch")
	public void onClickBtnSearch(Event event){
		try {
			doSearch();
		}catch (Exception e){
			log.error("Button Search on click", e);
		}
	}

	@Listen("onClick = #listBrowseForm")
	public void onClickListBrowseForm(Event event){
		try{
			result = (Form) listBrowseForm.getSelectedItem().getAttribute("DATA");
		}catch (Exception e){
			log.error("List listBrowseForm on click", e);
		}
	}

	@Listen("onDoubleClick = #listBrowseForm")
	public void onDoubleCllickListBrowseForm(Event event){
		try{
			result = (Form) listBrowseForm.getSelectedItem().getAttribute("DATA");
			fireEventPopupButton();
		}catch (Exception e){
			log.error("List listBrowseForm on double click", e);
		}
	}

	private void doSearch() throws Exception{
		List<Form> listForm=parameterService.searchForm((String) ComponentUtil.getValue(txtSearch));
		listBrowseForm.getItems().clear();
		if (listForm != null && listForm.size() > 0){
			for(Form form : listForm){
				Listitem item = new Listitem();
				item.setAttribute("DATA", form);
				item.appendChild(new Listcell(form.getFormId()));
				item.appendChild(new Listcell(form.getFormName()));
				listBrowseForm.appendChild(item);
			}
		}
	}
	
	public Object returnValue() {
		return result;
	}
}
