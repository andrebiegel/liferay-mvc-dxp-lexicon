package veranstaltungs.management.portlet.control;

import com.liferay.portal.kernel.util.OrderByComparator;

import veranstaltungs.management.portlet.entity.Veranstaltung;

public class TitleComparator extends OrderByComparator<Veranstaltung> {

	public TitleComparator() {
		this(Boolean.FALSE);
	}

	public TitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

	public static final String ORDER_BY_ASC = "Veranstaltung.title ASC";

	public static final String ORDER_BY_DESC = "Veranstaltung.title DESC";

	public static final String[] ORDER_BY_FIELDS = { "title" };
	/**
	 * 
	 */
	private static final long serialVersionUID = -1980529455853706504L;

	@Override
	public int compare(Veranstaltung entry1, Veranstaltung entry2) {
		String title1 = entry1.getTitle();
		String title2 = entry2.getTitle();

		int value = title1.compareTo(title2);

		if (_ascending) {
			return value;
		} else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		} else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}
}
