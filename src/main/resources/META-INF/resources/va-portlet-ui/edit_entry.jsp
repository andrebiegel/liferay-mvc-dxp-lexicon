
<%@ include file="../init.jsp" %>


<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/va-portlet-ui/view.jsp"></portlet:param>
</portlet:renderURL>

<%
        String uuid = ParamUtil.getString(request, "uuid");

        Veranstaltung veranstaltung = vaService.getVeranstaltung(uuid);
        

%>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm" >

        <aui:fieldset >
            <aui:input name="title" label="veranstaltungs-management.va.title" value='<%= veranstaltung == null ? "" : veranstaltung.getTitle() %>' ></aui:input>
            <aui:input name="teaser" label="veranstaltungs-management.va.teaser" value='<%= veranstaltung == null ? "" : veranstaltung.getTeaser() %>'></aui:input>
            <aui:input name="uuid" type="hidden"  value='<%= veranstaltung == null ? "" : veranstaltung.getUuid() %>' />
        </aui:fieldset>

        <aui:button-row>
            <aui:button type="submit"></aui:button>
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>" value="veranstaltungs-management.cancel"></aui:button>
        </aui:button-row>
</aui:form>