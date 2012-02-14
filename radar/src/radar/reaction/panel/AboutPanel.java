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

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Font;

/**
 * @author fsouza
 *
 */
public class AboutPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AboutPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{677, 0};
		gridBagLayout.rowHeights = new int[]{95, 206, 126, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lbltitleWork = new JLabel("Implementação de um algoritmo de localização de robôs móveis");
		lbltitleWork.setFont(new Font("Dialog", Font.BOLD, 16));
		GridBagConstraints gbc_lbltitleWork = new GridBagConstraints();
		gbc_lbltitleWork.fill = GridBagConstraints.BOTH;
		gbc_lbltitleWork.insets = new Insets(0, 0, 5, 0);
		gbc_lbltitleWork.gridx = 0;
		gbc_lbltitleWork.gridy = 0;
		add(lbltitleWork, gbc_lbltitleWork);
		lbltitleWork.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{174, 322, 180, 0};
		gbl_panel.rowHeights = new int[]{202, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblUniara = new JLabel("New label");
		lblUniara.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblUniara = new GridBagConstraints();
		gbc_lblUniara.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblUniara.insets = new Insets(0, 0, 0, 5);
		gbc_lblUniara.gridx = 0;
		gbc_lblUniara.gridy = 0;
		panel.add(lblUniara, gbc_lblUniara);
		
		JLabel lblNewLabel = new JLabel("descrição do TCC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblReaction = new JLabel("New label");
		GridBagConstraints gbc_lblReaction = new GridBagConstraints();
		gbc_lblReaction.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblReaction.gridx = 2;
		gbc_lblReaction.gridy = 0;
		panel.add(lblReaction, gbc_lblReaction);
		
		JLabel lblInvolved = new JLabel("involved");
		lblInvolved.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblInvolved = new GridBagConstraints();
		gbc_lblInvolved.fill = GridBagConstraints.BOTH;
		gbc_lblInvolved.gridx = 0;
		gbc_lblInvolved.gridy = 2;
		add(lblInvolved, gbc_lblInvolved);
		setVisible(true);
	}
}
