package veranstaltungs.management.portlet.control;

import com.liferay.portal.kernel.util.OrderByComparator;

import veranstaltungs.management.portlet.entity.Veranstaltung;

public class TeaserComparator extends OrderByComparator<Veranstaltung> {

	public TeaserComparator() {
		this(Boolean.FALSE);
	}

	public TeaserComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

	public static final String ORDER_BY_ASC = "Veranstaltung.teaser ASC";

	public static final String ORDER_BY_DESC = "Veranstaltung.teaser DESC";

	public static final String[] ORDER_BY_FIELDS = { "teaser" };
	/**
	 * 
	 */
	private static final long serialVersionUID = -1980529455853706504L;

	@Override
	public int compare(Veranstaltung entry1, Veranstaltung entry2) {
		String teaser1 = entry1.getTeaser();
		String teaser2 = entry2.getTeaser();

		int value = teaser1.compareTo(teaser2);

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
