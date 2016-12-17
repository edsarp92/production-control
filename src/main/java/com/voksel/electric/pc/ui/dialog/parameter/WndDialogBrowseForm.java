package com.voksel.electric.pc.ui.dialog.parameter;

import com.voksel.electric.pc.common.ComponentUtil;
import com.voksel.electric.pc.component.PopupUI;
import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;


@org.springframework.stereotype.Component
@Scope("execution")
public class WndDialogBrowseForm extends PopupUI<Grid> {

	final Logger log = LoggerFactory.getLogger(WndDialogBrowseForm.class);
	@Wire
	Textbox txtBrowseSearch;
	@Wire
	Button btnBrowseSearch;
	@Wire
	Listbox listBrowseForm;
	@Wire
	Paging paging;

	@Inject
	ParameterService parameterService;
	Form result = null;

	public void doAfterCompose(Grid comp) throws Exception {
		super.doAfterCompose(comp);
	}

	@Listen("onOK = #txtSearch")
	public void onOkTxtId(Event event){
		try {
			doSearch();
		}catch (Exception e){
			log.error("Textbox search on ok", e);
		}
	}

	@Listen("onClick = #btnBrowseSearch")
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
		doValidation();
		Page<Form> page=parameterService.searchForm((String) ComponentUtil.getValue(txtBrowseSearch),createPageRequest());
		paging.setTotalSize((int)page.getTotalElements());
		listBrowseForm.getItems().clear();
		if (page.getContent() !=null){
			for(Form form : page.getContent()){
				Listitem item = new Listitem();
				item.setAttribute("DATA", form);
				item.appendChild(new Listcell(Long.valueOf(form.getFormId()).toString()));
				item.appendChild(new Listcell(form.getFormName()));
				listBrowseForm.appendChild(item);
			}
		}
	}

	private Pageable createPageRequest(){
		int pageNo = paging.getActivePage();
		int pageSize = paging.getPageSize();
		return new PageRequest(pageNo, pageSize);
	}
	private void doValidation(){
		if (ComponentUtil.getValue(txtBrowseSearch) == null || ComponentUtil.getValue(txtBrowseSearch).equals("")) {
			throw new WrongValueException(txtBrowseSearch, "Tolong isi kriteria pencarian");
		}
	}
	public Object returnValue() {
		return result;
	}
}
