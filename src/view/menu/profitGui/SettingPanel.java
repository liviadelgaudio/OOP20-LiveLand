package view.menu.profitGui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import view.model.activity.ActivityType;
import view.model.activity.ViewActivityImpl;

public class SettingPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3798508884700068788L;
	private final ActivityType type;
	final JPanel settingPanel = new JPanel(new BorderLayout());
	final RangePanel rangePanel = new RangePanel();
	final NamePanel namePanel = new NamePanel();
	
	public SettingPanel(ActivityType type) {
		this.type = type;
		this.setLayout(new BorderLayout());
		this.add(namePanel, BorderLayout.NORTH);
		this.add(rangePanel, BorderLayout.CENTER);
		
	}
	
	public ViewActivityImpl buildNewProfitActivity() {
		return new ViewActivityImpl(this.namePanel.getName(), 
				Integer.valueOf(this.rangePanel.textMin.getText()), 
				Integer.valueOf(this.rangePanel.textMax.getText()), this.type);
	}

}
