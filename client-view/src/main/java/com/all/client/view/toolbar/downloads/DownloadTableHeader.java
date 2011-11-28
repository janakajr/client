/**
 * 
 */
package com.all.client.view.toolbar.downloads;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SortOrder;
import javax.swing.RowSorter.SortKey;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.all.core.common.view.SynthFonts;
import com.all.core.common.view.SynthIcons;

public class DownloadTableHeader extends JTableHeader {
	private static final long serialVersionUID = 1L;

	public DownloadTableHeader(TableColumnModel columnModel) {
		super(columnModel);
		this.setReorderingAllowed(false);
		this.setDefaultRenderer(new DownloadTableHeaderCellRenderer());
	}
}

class DownloadTableHeaderCellRenderer implements TableCellRenderer {
	private JLabel title;
	private JLabel arrow;
	private JPanel panel;

	public DownloadTableHeaderCellRenderer() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		title = new JLabel();
		arrow = new JLabel();

		title.setName(SynthFonts.BOLD_FONT11_BLACK);
		panel.add(title, BorderLayout.CENTER);
		panel.add(arrow, BorderLayout.EAST);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		DownloadTableColumns correspondingColumn = (DownloadTableColumns) table.getColumnModel().getColumn(column)
				.getIdentifier();

		title.setHorizontalAlignment(correspondingColumn.alignment());
		String text = value.toString();
		if (JLabel.RIGHT == title.getHorizontalAlignment()) {
			text = text + "  ";
		} else if (JLabel.LEFT == title.getHorizontalAlignment()) {
			text = "  " + text;
		}
		title.setText(text);
		SortOrder sortOrder = SortOrder.UNSORTED;
		if (table.getRowSorter() != null && table.getRowSorter().getSortKeys() != null) {
			List<? extends SortKey> sortKeys = table.getRowSorter().getSortKeys();
			for (SortKey sortKey : sortKeys) {
				if (sortKey.getColumn() == column) {
					sortOrder = sortKey.getSortOrder();
					break;
				}
			}
		}
		switch (sortOrder) {
		case ASCENDING:
			panel.setName("tableDownloadSelectedHeader");
			arrow.setIcon(SynthIcons.SORT_ASCENDING_ICON);
			title.setName(SynthFonts.BOLD_FONT11_WHITE);
			break;
		case DESCENDING:
			panel.setName("tableDownloadSelectedHeader");
			arrow.setIcon(SynthIcons.SORT_DESCENDING_ICON);
			title.setName(SynthFonts.BOLD_FONT11_WHITE);
			break;
		default:
			panel.setName("tableHeader");
			arrow.setIcon(SynthIcons.SORT_NATURAL_ICON);
			title.setName(SynthFonts.BOLD_FONT11_GRAY77_77_77);
			break;
		}
		return panel;
	}

}