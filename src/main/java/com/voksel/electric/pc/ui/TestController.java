package com.voksel.electric.pc.ui;
import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import com.voksel.electric.pc.service.*;

/**
 * Created by Edsarp on 2016-06-26.
 */
@org.springframework.stereotype.Component
@Scope("desktop")
public class TestController extends SelectorComposer<Component> {

    @Wire
    Label labelId;
    @Wire
    Textbox textboxId;
    @Wire
    Button btnSubmit;

    @WireVariable
    TestService testService;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        labelId.setValue("Username :");
        textboxId.setValue("your name");

    }

    @Listen("onClick = button#btnSubmit")
    public void onSubmit() {
        textboxId.setValue(testService.getName());

    }
}
