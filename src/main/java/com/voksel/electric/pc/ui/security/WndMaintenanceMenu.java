package com.voksel.electric.pc.ui.security;

import com.voksel.electric.pc.common.ComponentUtil;
import com.voksel.electric.pc.common.MessageBox;
import com.voksel.electric.pc.component.DialogUtil;
import com.voksel.electric.pc.domain.Privilege;
import com.voksel.electric.pc.domain.entity.*;
import com.voksel.electric.pc.domain.entity.Menu;
import com.voksel.electric.pc.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by edsarp on 8/21/16.
 */
@org.springframework.stereotype.Component
@Scope("desktop")
public class WndMaintenanceMenu extends SelectorComposer<Component>    {
    final Logger log = LoggerFactory.getLogger(WndMaintenanceMenu.class);
    @Wire Window wnd;
    @Wire Textbox txtMenuId;
    @Wire Textbox txtMenuNm;
    @Wire Textbox txtFormId;
    @Wire Textbox txtFormNm;
    @Wire Textbox txtParentId;
    @Wire Textbox txtParentNm;
    @Wire Intbox txtSeq;
    @Wire Textbox txtParameters;
    @Wire Tree tree;
    @Wire Button btnSearch;
    @Wire Button btnSave;
    @Wire Button btnDelete;
    @Wire Button btnReset;

    @Autowired
    ParameterService parameterService;
    Boolean onLoad = false;


    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initWndRegistrationForm();
    }

    @Listen("onOk = #txtMenuId")
    public void onSearch(Event event){
        try{
            Menu menu=parameterService.findOneMenu((String)ComponentUtil.getValue(txtMenuId));
            if(menu !=null) {
                doFillDetailForm(menu);
            }
        }catch (Exception e)
        {
            log.error("Textbox Menu Id on ok ", e);
        }
    }

    @Listen("onOk = #txtFormId")
    public void onSearchForm(Event event){
        try{
            doSearchForm();
        }catch (Exception e)
        {
            log.error("Textbox Form Id on ok ", e);
        }
    }

    @Listen("onClick = #btnSearch")
    public void onButtonSearchForm(Event event){
        try{
            doSearchForm();
        }catch (Exception e)
        {
            log.error("Button search form on click ", e);
        }
    }

    @Listen("onOk = #txtParentId")
    public void onSearchParent(Event event){
        try{
            doSearchParent();
        }catch (Exception e)
        {
            log.error("Textbox Parent Id on ok ", e);
        }
    }


    @Listen("onDoubleClick = #tree")
    public void onEdit(Event event){
        try{
            Tree trees = tree;
            Treeitem item = trees.getSelectedItem();
            if (item != null){
                String menuId = ((String)item.getLabel()).split("-")[0];
                Menu menu=parameterService.findOneMenu(menuId);
                if(menu !=null) {
                    doFillDetailForm(menu);
                    onLoad = true;
                    txtMenuId.setDisabled(true);
                    btnDelete.setDisabled(false);
                }

            }
        }catch (Exception e){
            log.error("Tree on double click ", e);
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
            if(ComponentUtil.getValue(txtMenuId)!=null || !ComponentUtil.getValue(txtMenuId).equals("")) {
                parameterService.deleteMenu((String) ComponentUtil.getValue(txtMenuId));
                MessageBox.showInformation("Data berhasil dihapus");
                doReset();
            }else{
                MessageBox.showInformation("Data tidak ada yang dipilih!");
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

    private void initWndRegistrationForm(){
        try{
            doReset();
        }catch (Exception e){
            log.error("Initialization on WndRegistrationForm: ",e);
        }
    }

    @SuppressWarnings("unchecked")
    private void doOpenDialogForm(){
        DialogUtil.showPopupDialog("/page/dialog/WndDialogBrowseForm.zul", "Browse Form", getSelf(), DialogUtil.PopupMode.OK_CLOSE, new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                Form form = (Form) event.getData();
                if (form != null) {
                    ComponentUtil.setValue(txtFormId, form.getFormId());
                    doSearchForm();
                }
            }
        }, null, Collections.EMPTY_MAP);
    }

    private void doSearchForm()throws Exception{
        if (ComponentUtil.getValue(txtFormId) != null && !ComponentUtil.getValue(txtFormId).equals("")) {
            Form form = parameterService.findOneForm((String) ComponentUtil.getValue(txtFormId));
            if (form != null) {
                ComponentUtil.setValue(txtFormNm, form.getFormName());
            }
        }
    }

    private void doSearchParent()throws Exception{
        if (ComponentUtil.getValue(txtParentId) != null && !ComponentUtil.getValue(txtParentId).equals("")) {
            Menu parent = parameterService.findOneMenu((String) ComponentUtil.getValue(txtParentId));
            if (parent != null) {
                ComponentUtil.setValue(txtParentNm, parent.getMenuName());
            }
        }
    }
    private void doInsert() throws  Exception{
        if (!isExists()) {
            Menu menu=populationData();
            parameterService.saveMenu(menu);
            MessageBox.showInformation("Data berhasil disimpan");
        }else{
            MessageBox.showError("Data sudah tersimpan sebelumnya!");
        }
        doReset();
    }

    private void doUpdate() throws  Exception{
        Menu menu=populationData();
        parameterService.saveMenu(menu);
        MessageBox.showInformation("Data berhasil diupdate");
        doReset();
    }

    private void doReset() throws Exception{
        ComponentUtil.clear(wnd);
        doLoadData();
        onLoad = false;
        btnDelete.setDisabled(true);
        txtMenuId.setFocus(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void doLoadData() throws Exception{
        List<Menu> listMenu=parameterService.findAllMenu();
        tree.getChildren().clear();
        Treechildren root = new Treechildren();
        HashMap mapTree = new HashMap();
        for (Menu menu : listMenu) {
            Treeitem item = new Treeitem();
            Treerow rowTree = new Treerow();
            mapTree.put(menu.getMenuId(), item);
            item.appendChild(rowTree);
            item.setAttribute("MENU_ID", menu.getMenuId());
            item.setAttribute("DATA", menu);
            rowTree.appendChild(new Treecell(menu.getMenuId() + " - " + menu.getMenuName() + " - " + menu.getSequence()));
            if (menu.getParentId() != null) {
                Treeitem itemParent = (Treeitem) mapTree.get(menu.getParentId());
                Treechildren parent = (Treechildren) itemParent.getAttribute("PARENT");
                if (parent == null) {
                    parent = new Treechildren();
                    itemParent.appendChild(parent);
                    itemParent.setOpen(false);
                    itemParent.setAttribute("PARENT", parent);
                }
                parent.appendChild(item);
            } else {
                root.appendChild(item);
            }
        }
        tree.appendChild(root);
    }



    private boolean isExists() throws Exception{
        return parameterService.existsMenu((String)ComponentUtil.getValue(txtMenuId));
    }

    private void doFillDetailForm(Menu menu){
        ComponentUtil.setValue(txtMenuId,menu.getMenuId());
        ComponentUtil.setValue(txtMenuNm,menu.getMenuName());
        ComponentUtil.setValue(txtFormId,menu.getForm().getFormId());
        ComponentUtil.setValue(txtFormNm,menu.getForm().getFormName());
        ComponentUtil.setValue(txtParentId,menu.getParentId());
        ComponentUtil.setValue(txtParentNm,menu.getParentId());
        ComponentUtil.setValue(txtSeq,menu.getSequence());
        ComponentUtil.setValue(txtParameters,menu.getParameter());
    }

    private Menu populationData(){
        Menu menu=new Menu();
        Menu parent=new Menu();
        Form form=new Form();
        menu.setMenuId((String)ComponentUtil.getValue(txtMenuId));
        menu.setMenuName((String)ComponentUtil.getValue(txtMenuNm));
        form.setFormId((String)ComponentUtil.getValue(txtFormId));
        menu.setForm(form);

        menu.setParentId((String)ComponentUtil.getValue(txtParentId));
        menu.setSequence((Integer)ComponentUtil.getValue(txtSeq));
        menu.setParameter((String)ComponentUtil.getValue(txtParameters));
        return menu;
    }

    private boolean doValidation(){
        if (ComponentUtil.getValue(txtMenuId) == null || ComponentUtil.getValue(txtMenuId).equals("")) {
            throw new WrongValueException(txtMenuId, "Menu id harus diisi");
        }else if (ComponentUtil.getValue(txtMenuNm) == null || ComponentUtil.getValue(txtMenuNm).equals("")) {
            throw new WrongValueException(txtMenuNm, "Nama menu harus diisi");
        }
        return true;
    }

}
