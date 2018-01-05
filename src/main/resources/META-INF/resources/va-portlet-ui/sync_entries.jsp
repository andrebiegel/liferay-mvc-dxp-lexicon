
<%@ include file="../init.jsp"%>


<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/va-portlet-ui/view.jsp"></portlet:param>
</portlet:renderURL>


<portlet:actionURL name="syncEntries" var="syncEntriesURL"></portlet:actionURL>

<aui:form action="<%=syncEntriesURL%>" name="<portlet:namespace />fm">

	<aui:fieldset>
		<%
			Calendar startingDateCalendar = CalendarFactoryUtil.getCalendar();
					startingDateCalendar.setTime(DateUtil.newDate());
		%>
		<aui:field-wrapper label="veranstaltungs-management.sync.date">
			<liferay-ui:input-date dayParam="startDateDay"
				dayValue="<%=startingDateCalendar.get(Calendar.DATE)%>"
				disabled="<%=false%>"
				firstDayOfWeek="<%=startingDateCalendar.getFirstDayOfWeek() - 1%>"
				monthParam="startDateMonth"
				monthValue="<%=startingDateCalendar.get(Calendar.MONTH)%>"
				name="startDate" yearParam="startDateYear"
				yearValue="<%=startingDateCalendar.get(Calendar.YEAR)%>" />
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="veranstaltungs-management.sync" />
		<aui:button type="cancel" onClick="<%= viewURL.toString() %>"
			value="veranstaltungs-management.cancel" />
	</aui:button-row>
</aui:form>