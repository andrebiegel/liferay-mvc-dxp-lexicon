package demoportlet.portlet;

import com.google.common.collect.Ordering;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import demoportlet.constants.DemoPortletPortletKeys;
import veranstaltungs.management.portlet.entity.Veranstaltung;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=demoportlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/va-portlet-ui/view.jsp",
		"javax.portlet.name=" + DemoPortletPortletKeys.DemoPortlet,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user", "test=test"
	},
	service = Portlet.class
)
public class DemoPortletPortlet extends MVCPortlet {
	
	private List<Veranstaltung> entries = new ArrayList<>();

	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
            renderRequest.setAttribute("vaService", this);
            renderRequest.setAttribute("orderByCol",  "title");
            renderRequest.setAttribute("orderByType",  "asc");
            renderRequest.setAttribute("vatype", "va-type-example");
		super.render(renderRequest, renderResponse);
	}

	public List<Veranstaltung> search(long groupId,String category, int start, int end, OrderByComparator<Veranstaltung> obc){
		
		System.out.println(category);
		System.out.println(start);
		System.out.println(end);
		if ( Objects.nonNull(obc)&& Objects.nonNull(obc.getOrderByConditionFields()) ) {
			for (String field : obc.getOrderByConditionFields()) {
				System.out.println(field);
			}
			
		}
		if (this.entries.isEmpty()) {
			return this.entries;
		}
		Collections.sort(this.entries, obc);

		return //this.entries;
		this.entries.subList(start, (end < this.getVeranstaltungenCount() ) ?  end: getVeranstaltungenCount());
		//return this.entries.subList(start,(end < this.getVeranstaltungenCount() ) ?  end-1 : getVeranstaltungenCount()-1);
	}

	public List<Veranstaltung> getVeranstaltungen(String category, int start, int end) {
		return this.entries;
	}
	
	public Veranstaltung getVeranstaltung(String uuid) throws PortletException {
		 Logger.getLogger(DemoPortletPortlet.class.getName()).log(Level.INFO, "getVeranstaltung: ");
		return this.entries.stream().filter(x-> x.getUuid().equals(uuid)).findFirst().orElseGet(()-> new Veranstaltung("New","New",null));
	}

	
	public int getVeranstaltungenCount() {
		 Logger.getLogger(DemoPortletPortlet.class.getName()).log(Level.INFO, "getVeranstaltungenCount: ");
		return this.entries.size();
	}

	
	
	
	public void updateEntry(long userId, String title, String teaser, String vauuid, ServiceContext serviceContext) {
		this.entries.stream().filter(x-> x.getUuid().equals(vauuid)).findFirst().ifPresent(entry-> updateElement(entry, teaser, title));
		
	}
	private void updateElement(Veranstaltung old,String teaser, String title ) {
		old.setTeaser(teaser);
		old.setTitle(title);
	}



	public void deleteVeranstaltung(String uuid) throws PortalException{
		
			    //this.entries.removeIf(x-> x.getUuid().equals(uuid));
				this.entries.remove(new Veranstaltung(null,null,uuid));
			    System.out.println(this.entries.size());
	}
	

	public void adddsdsdEntrydsd(long userId, String title, String teaser, String vauuid, ServiceContext serviceContext) {
		 Logger.getLogger(DemoPortletPortlet.class.getName()).log(Level.INFO, "UUID: "+ vauuid);
		this.entries.add(new Veranstaltung(title, teaser, vauuid));
	}

}
