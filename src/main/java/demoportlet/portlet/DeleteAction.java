package demoportlet.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import demoportlet.constants.DemoPortletPortletKeys;
import veranstaltungs.management.portlet.entity.Veranstaltung;
@Component(
	    immediate = true,
	    property = {
	    		"javax.portlet.name=" + DemoPortletPortletKeys.DemoPortlet,
	        "mvc.command.name=deleteVeranstaltung"
	    },
	    service = MVCActionCommand.class
	)
public class DeleteAction extends BaseMVCActionCommand {
	
	private Portlet portlet;
	
	@Reference(target = "(javax.portlet.name=" + DemoPortletPortletKeys.DemoPortlet + ")")
	public void setPortlet(Portlet portlet) {
		this.portlet = portlet;
	}
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Veranstaltung.class.getName(), actionRequest);
			System.out.println("delete");
		    String uuid = ParamUtil.getString(actionRequest, "uuid");
		    
		    ((DemoPortletPortlet)this.portlet).deleteVeranstaltung(uuid);

	}

}
