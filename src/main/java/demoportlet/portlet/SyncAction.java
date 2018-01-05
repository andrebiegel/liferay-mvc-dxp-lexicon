package demoportlet.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	        "mvc.command.name=syncEntries"
	    },
	    service = MVCActionCommand.class
	)
public class SyncAction extends BaseMVCActionCommand {


	private Portlet portlet;
	
	@Reference(target = "(javax.portlet.name=" + DemoPortletPortletKeys.DemoPortlet + ")")
	public void setPortlet(Portlet portlet) {
		this.portlet = portlet;
	}
	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	    		Veranstaltung.class.getName(), actionRequest);
		
		String vauuid = ParamUtil.getString(actionRequest, "uuid");
		 System.out.println("SYNCED");
	    if (vauuid !=null && !vauuid.trim().isEmpty()) {
	        try {


	        }
	        catch (Exception e) {
	            SessionErrors.add(actionRequest, e.getClass().getName());

	            PortalUtil.copyRequestParameters(actionRequest, actionResponse);

	            actionResponse.setRenderParameter(
	                "mvcPath", "/va-portlet-ui/sync_entries.jsp");
	        }

	    }
	    else {

	        try {

	            SessionMessages.add(actionRequest, "entriesSynced");


	        }
	        catch (Exception e) {
	            SessionErrors.add(actionRequest, e.getClass().getName());

	            PortalUtil.copyRequestParameters(actionRequest, actionResponse);
	            
	            actionResponse.setRenderParameter(
	                "mvcPath", "/va-portlet-ui/sync_entries.jsp");
	        }
	    }		 
	}
}
