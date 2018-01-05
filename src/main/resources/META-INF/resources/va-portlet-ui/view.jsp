<%@ include file="../init.jsp"%>
<liferay-ui:success key="entryAdded" message="entry-added" />
<liferay-ui:success key="entriesSynced"
	message="veranstaltungs-management.entries-synced" />
<p>
	<b><liferay-ui:message key="veranstaltungs-management.caption" /></b>
</p>

<%
	String orderByCol = ParamUtil.getString(request, "orderByCol", "title");

	boolean orderByAsc = false;

	String orderByType = ParamUtil.getString(request, "orderByType", "asc");

	if (orderByType.equals("asc")) {
		orderByAsc = true;
	}

	OrderByComparator orderByComparator = null;

	if (orderByCol.equals("title")) {
		orderByComparator = new TitleComparator(orderByAsc);
	} else {
		orderByComparator = new TeaserComparator(orderByAsc);
	}
%>

<liferay-portlet:renderURL varImpl="viewPageURL">
	<portlet:param name="mvcPath" value="/va-portlet-ui/view.jsp" />
	<portlet:param name="orderByCol" value="<%=orderByCol%>" />
	<portlet:param name="orderByType" value="<%=orderByType%>" />
</liferay-portlet:renderURL>


<jsp:useBean id="vatype" class="java.lang.String" scope="request" />

<aui:form method="post" name="show_fm">
	<aui:input name="vaIds" type="hidden" />
	<liferay-frontend:management-bar includeCheckBox="<%=true%>"
		searchContainerId="trash">
		 <liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort orderByCol="<%=orderByCol%>"
			orderByType="<%=orderByType%>"
			orderColumns='<%=new String[]{"title","teaser"}%>'
			portletURL="<%=viewPageURL%>" />
</liferay-frontend:management-bar-filters>
	</liferay-frontend:management-bar>
	<liferay-ui:search-container id="trash" headerNames="title,teaser"
		total="<%=vaService.getVeranstaltungenCount()%>"
		emptyResultsMessage="veranstaltungs-management.empty.results"
		delta="5" deltaConfigurable="true"
		rowChecker="<%=new RowChecker(renderResponse)%>">

		<liferay-ui:search-container-results
			results="<%= vaService.search(scopeGroupId.longValue(),
		 vatype, searchContainer.getStart(),
         searchContainer.getEnd(), orderByComparator) %>" />

		<liferay-ui:search-container-row
			className="veranstaltungs.management.portlet.entity.Veranstaltung"
			modelVar="entry" keyProperty="uuid">
			<liferay-ui:search-container-column-text  property="title" name="title"
				 orderable="<%=true%>" />
			<liferay-ui:search-container-column-text property="teaser" name="teaser" 
				 orderable="<%=true%>" />
			<liferay-ui:search-container-column-text property="uuid"
				 />
			<liferay-ui:search-container-column-jsp align="right"
				path="/va-portlet-ui/va_actions.jsp" />
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />

	</liferay-ui:search-container>

</aui:form>
<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcPath" value="/va-portlet-ui/edit_entry.jsp"></portlet:param>
</portlet:renderURL>
<aui:button-row>

	<portlet:renderURL var="syncURL">
		<portlet:param name="mvcPath" value="/va-portlet-ui/sync_entries.jsp"></portlet:param>
	</portlet:renderURL>
	<aui:button onClick="<%= addEntryURL.toString() %>"
		value="veranstaltungs-management.add-entry"></aui:button>
	<aui:button onClick="<%= syncURL.toString() %>"
		value="veranstaltungs-management.sync"></aui:button>
	<aui:button
		onClick='<%= renderResponse.getNamespace() + "showSelectedFoos();" %>'
		value="veranstaltungs-management.delete-entries" />
	<aui:script>
Liferay.provide(
        window,
        '<portlet:namespace />showSelectedFoos',
         function() {
                    var cmd = 
                           Liferay.Util.listCheckedExcept(
                               document.<portlet:namespace />show_fm,   "<portlet:namespace />allRowIds"); 
                    if(cmd==""||cmd==null){
                         alert("Please select atleast one foo to delete");
                        return false;
                    }

                    if (confirm("Are you sure you want to delete Foos? ")) {

                        var actionURL = Liferay.PortletURL.createActionURL();
                        actionURL.setWindowState("<%=LiferayWindowState.NORMAL.toString() %>");
                        actionURL.setPortletMode("<%=LiferayPortletMode.VIEW %>");
                        actionURL.setParameter("selectedRows",cmd);
                        actionURL.setParameter("javax.portlet.action","deleteMultipleVeranstaltungen");
                        actionURL.setPortletId("<%=themeDisplay.getPortletDisplay().getId() %>");

                        submitForm(document.<portlet:namespace />show_fm, actionURL);

                }

          },
          ['liferay-util-list-fields', 'liferay-portlet-url']
);</aui:script>	
</aui:button-row>

<liferay-frontend:add-menu>
    <liferay-frontend:add-menu-item   title='<%= LanguageUtil.get(request,
    "veranstaltungs-management.add-entry") %>' url="<%= addEntryURL.toString() %>"  />
</liferay-frontend:add-menu>
