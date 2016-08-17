package com.voksel.electric.pc.common;

import org.zkoss.zul.Messagebox;

public class MessageBox { 

	public static void show(String messages, String header, Integer style, String type){
		Messagebox.show(messages, header, style, type);
	}
	
	public static void showInformation(String messages){
		Messagebox.show(messages, "INFORMASI", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	public static void showError(String messages){
		Messagebox.show(messages, "KESALAHAN", Messagebox.OK, Messagebox.ERROR);
	}
	
	public static boolean showConfirm(String messages){
		if(Messagebox.show(messages, "KONFIRMASI",
				Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION)== Messagebox.OK){
			return true;
		}
		return false;
	}
	
}
