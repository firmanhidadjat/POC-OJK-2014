package ojk.app.form;

import javax.faces.bean.ManagedBean;


import org.primefaces.context.RequestContext;

@ManagedBean(name = "ShowDialog")
public class ShowDialog {
	public void viewDialog() {
		RequestContext.getCurrentInstance().openDialog("App2Dialog");
	}
}
