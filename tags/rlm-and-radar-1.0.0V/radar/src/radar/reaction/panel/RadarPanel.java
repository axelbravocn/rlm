/**
 **********************************************************************************
 *                   ------   RLM IA   ------    
 *
 * @category   IC / TCC
 * @author     Flavio Souza    <flavioluiz.ssouza@gmail.com>
 * @author     Thiago Sydow    <thiagovs05@gmail.com>
 * @guiding    Rodrigo Malara  <rmalara.uniara@gmail.com>
 * @guiding    Rodrigo Bianchi <rodrigo.bianchi@gmail.com>
 * @copyright  Reation Team
 * @license    http://www.reationteam.com.br
 * @version    SVN: 1.0.0
 * @see        www.uniara.com.br
 * 
 * 
 * LICENSE: Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this solution to deal with the publication, use or customization of 
 * the Software without restriction to whom it is provided, subject to the following 
 * conditions:
 * 
 * The notice of Reaction Team and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING WITHOUT LIMITATION WARRANTIES OF MERCHANTABILITY FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 * THE SOFTWARE OR THE USE OR OTHERS IN THE SOFTWARE.
 * 
 *	
 **********************************************************************************
 */

package radar.reaction.panel;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * @author fsouza
 *
 */
public class RadarPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public RadarPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton btnConnet = new JButton("Connect NXT");
		GridBagConstraints gbc_btnConnet = new GridBagConstraints();
		gbc_btnConnet.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnet.gridx = 0;
		gbc_btnConnet.gridy = 0;
		add(btnConnet, gbc_btnConnet);
		
		JPanel mappingPanel = new JPanel();
		mappingPanel.setBorder(new TitledBorder(null, "Mapping", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_mappingPanel = new GridBagConstraints();
		gbc_mappingPanel.fill = GridBagConstraints.BOTH;
		gbc_mappingPanel.gridx = 1;
		gbc_mappingPanel.gridy = 0;
		add(mappingPanel, gbc_mappingPanel);
		mappingPanel.setLayout(new BoxLayout(mappingPanel, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.background"));
		mappingPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{144, 58, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel mapPanel = new JPanel();
		mapPanel.setBackground(UIManager.getColor("Button.highlight"));
		GridBagConstraints gbc_mapPanel = new GridBagConstraints();
		gbc_mapPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mapPanel.fill = GridBagConstraints.BOTH;
		gbc_mapPanel.gridx = 0;
		gbc_mapPanel.gridy = 0;
		panel.add(mapPanel, gbc_mapPanel);
		
		JLabel label = new JLabel("New label");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);

	}

}
