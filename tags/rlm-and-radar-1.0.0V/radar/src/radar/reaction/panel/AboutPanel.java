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
		gridBagLayout.columnWidths = new int[]{731, 0};
		gridBagLayout.rowHeights = new int[]{75, 314, 99, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		gbl_panel.columnWidths = new int[]{174, 380, 180, 0};
		gbl_panel.rowHeights = new int[]{304, 0};
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
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{194, 315, 230, 0};
		gbl_panel_1.rowHeights = new int[]{35, 34, 28, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblStudent = new JLabel("Aluno");
		GridBagConstraints gbc_lblStudent = new GridBagConstraints();
		gbc_lblStudent.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudent.gridx = 0;
		gbc_lblStudent.gridy = 0;
		panel_1.add(lblStudent, gbc_lblStudent);
		
		JLabel lblStudentName = new JLabel("Flavio Luiz dos Santos de Souza");
		lblStudentName.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblStudentName = new GridBagConstraints();
		gbc_lblStudentName.insets = new Insets(0, 0, 5, 5);
		gbc_lblStudentName.gridx = 1;
		gbc_lblStudentName.gridy = 0;
		panel_1.add(lblStudentName, gbc_lblStudentName);
		
		JLabel lblStudentMail = new JLabel("flavioluiz.ssouza@gmail.com");
		lblStudentMail.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblStudentMail = new GridBagConstraints();
		gbc_lblStudentMail.insets = new Insets(0, 0, 5, 0);
		gbc_lblStudentMail.gridx = 2;
		gbc_lblStudentMail.gridy = 0;
		panel_1.add(lblStudentMail, gbc_lblStudentMail);
		
		JLabel lblLeader = new JLabel("Orientador");
		GridBagConstraints gbc_lblLeader = new GridBagConstraints();
		gbc_lblLeader.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeader.gridx = 0;
		gbc_lblLeader.gridy = 1;
		panel_1.add(lblLeader, gbc_lblLeader);
		
		JLabel lblLeaderName = new JLabel("Rodrigo Elias Bianchi");
		lblLeaderName.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblLeaderName = new GridBagConstraints();
		gbc_lblLeaderName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLeaderName.gridx = 1;
		gbc_lblLeaderName.gridy = 1;
		panel_1.add(lblLeaderName, gbc_lblLeaderName);
		
		JLabel lblLeaderMail = new JLabel("rodrigo.bianchi@gmail.com");
		lblLeaderMail.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblLeaderMail = new GridBagConstraints();
		gbc_lblLeaderMail.insets = new Insets(0, 0, 5, 0);
		gbc_lblLeaderMail.gridx = 2;
		gbc_lblLeaderMail.gridy = 1;
		panel_1.add(lblLeaderMail, gbc_lblLeaderMail);
		
		JLabel lblCoadvisor = new JLabel("Coorientador");
		GridBagConstraints gbc_lblCoadvisor = new GridBagConstraints();
		gbc_lblCoadvisor.insets = new Insets(0, 0, 0, 5);
		gbc_lblCoadvisor.gridx = 0;
		gbc_lblCoadvisor.gridy = 2;
		panel_1.add(lblCoadvisor, gbc_lblCoadvisor);
		
		JLabel lblCoadvisorName = new JLabel("Rodrigo Daniel Malara");
		lblCoadvisorName.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCoadvisorName = new GridBagConstraints();
		gbc_lblCoadvisorName.insets = new Insets(0, 0, 0, 5);
		gbc_lblCoadvisorName.gridx = 1;
		gbc_lblCoadvisorName.gridy = 2;
		panel_1.add(lblCoadvisorName, gbc_lblCoadvisorName);
		
		JLabel lblCoadvisorMail = new JLabel("rmalara.uniara@gmail.com");
		lblCoadvisorMail.setFont(new Font("Dialog", Font.ITALIC, 12));
		GridBagConstraints gbc_lblCoadvisorMail = new GridBagConstraints();
		gbc_lblCoadvisorMail.gridx = 2;
		gbc_lblCoadvisorMail.gridy = 2;
		panel_1.add(lblCoadvisorMail, gbc_lblCoadvisorMail);
		setVisible(true);
	}
}
