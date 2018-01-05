<%@include file="../init.jsp"%>

<%
    String mvcPath = ParamUtil.getString(request, "mvcPath");

    ResultRow row = (ResultRow) request
                    .getAttribute("SEARCH_CONTAINER_RESULT_ROW");

    Veranstaltung guestbook = (Veranstaltung) row.getObject();
%>

<liferay-ui:icon-menu markupView="lexicon">

    <portlet:renderURL var="editURL">
        <portlet:param name="uuid"
            value="<%=String.valueOf(guestbook.getUuid()) %>" />
        <portlet:param name="mvcPath"
            value="/va-portlet-ui/edit_entry.jsp" />
    </portlet:renderURL>

    <liferay-ui:icon image="edit" message="Edit"
            url="<%=editURL.toString() %>" />

    <portlet:actionURL name="deleteVeranstaltung" var="deleteURL">
            <portlet:param name="uuid"
                value="<%= String.valueOf(guestbook.getUuid()) %>" />
    </portlet:actionURL>

    <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

</liferay-ui:icon-menu>