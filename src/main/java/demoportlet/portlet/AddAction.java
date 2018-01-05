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
	        "mvc.command.name=addEntry"
	    },
	    service = MVCActionCommand.class
	)
public class AddAction extends BaseMVCActionCommand {


	private Portlet portlet;
	
	@Reference(target = "(javax.portlet.name=" + DemoPortletPortletKeys.DemoPortlet + ")")
	public void setPortlet(Portlet portlet) {
		this.portlet = portlet;
	}
	
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	    		Veranstaltung.class.getName(), actionRequest);
		String title = ParamUtil.getString(actionRequest, "title");
		String teaser = ParamUtil.getString(actionRequest, "teaser");
	
		
		String vauuid = ParamUtil.getString(actionRequest, "uuid");
		Logger.getLogger(DemoPortletPortlet.class.getName()).log(Level.SEVERE, "UUID: "+ vauuid);
		 System.out.println("UUID: :"+ vauuid+ ":");
	    if (vauuid !=null && !vauuid.trim().isEmpty()) {
	    	 Logger.getLogger(DemoPortletPortlet.class.getName()).log(Level.SEVERE, "UUID: "+ vauuid);
	    	 System.out.println("UUID: "+ vauuid);
	        try {

	            ((DemoPortletPortlet)this.portlet).updateEntry(
	                serviceContext.getUserId(), title, teaser, vauuid,
	                serviceContext);

//	            SessionMessages.add(request, "entryAdded");
//

	        }
	        catch (Exception e) {
	            System.out.println(e);

	            SessionErrors.add(actionRequest, e.getClass().getName());

	            PortalUtil.copyRequestParameters(actionRequest, actionResponse);

	            actionResponse.setRenderParameter(
	                "mvcPath", "/va-portlet-ui/edit_entry.jsp");
	        }

	    }
	    else {

	        try {
	        	((DemoPortletPortlet)this.portlet).adddsdsdEntrydsd(
	                serviceContext.getUserId(),title, teaser, UUID.randomUUID().toString(),
	                serviceContext);

	            SessionMessages.add(actionRequest, "entryAdded");


	        }
	        catch (Exception e) {
	            SessionErrors.add(actionRequest, e.getClass().getName());

	            PortalUtil.copyRequestParameters(actionRequest, actionResponse);
	            
	            actionResponse.setRenderParameter(
	                "mvcPath", "/va-portlet-ui/edit_entry.jsp");
	        }
	    }		 
	}
}
