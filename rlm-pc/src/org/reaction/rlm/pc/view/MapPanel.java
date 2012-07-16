/**
 **********************************************************************************
 *                   ------   RLM IA   ------    
 *
 * @category   IC / TCC
 * @author     Flavio Souza    <flavioluiz.ssouza@gmail.com>
 * @guiding    Rodrigo Malara  <rmalara.uniara@gmail.com>
 * @guiding    Rodrigo Bianchi <rodrigo.bianchi@gmail.com>
 * @copyright  Reation Team
 * @license    http://www.reationteam.com.br
 * @version    SVN: 2.0.0
 * @see        www.uniara.com.br
 * 
 * 
 * Purpose: This project was developed to obtensão the 
 * title of a Computer Engineer Flavio Luiz dos Santos de Souza
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
package org.reaction.rlm.pc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * @author Flavio Souza
 * 
 */
public class MapPanel extends Panel{
	private JPanel pnlMargin;
	private JPanel pnlBorder;
	private Panel pnlContainer;

	Component spacerComponent;

	public MapPanel(int borderWidth, Color borderColor, Color backgroundColor) {
		this(0, null, borderWidth, borderColor, backgroundColor);
	}

	public MapPanel(int margin, int borderWidth, Color borderColor, Color backgroundColor) {
		this(margin, null, borderWidth, borderColor, backgroundColor);
	}

	
	public MapPanel(int margin, Color marginBackground, int borderWidth, Color borderColor, Color backgroundColor) {
		super(new BorderLayout());

		this.spacerComponent = spacerComponent;
		createGUI();

		this.setComponentMargin(margin);
		this.setComponentBorder(borderWidth, borderColor);
		this.setComponentBackground(backgroundColor);
		if(marginBackground!=null){
			this.setMarginBackground(marginBackground);	
		}
	}

	private void createGUI() {
		pnlMargin = new JPanel(new BorderLayout());
		pnlBorder = new JPanel(new BorderLayout());
		pnlContainer = new Panel(new BorderLayout());

		pnlBorder.add(pnlContainer, BorderLayout.CENTER);
		pnlMargin.add(pnlBorder, BorderLayout.CENTER);
		this.add(pnlMargin, BorderLayout.CENTER);
	}

	public void setMarginBackground(Color c) {
		this.setBackground(c);
		pnlMargin.setBackground(c);
	}
	
	public void setComponentBackground(Color c) {
		pnlContainer.setBackground(c);
	}

	public void setComponentMargin(int top, int left, int bottom, int right) {
		pnlMargin.setBorder(BorderFactory.createEmptyBorder(top, left, bottom,
				right));
	}

	public void setComponentMargin(int margin) {
		this.setComponentMargin(margin, margin, margin, margin);
	}

	public void setComponentBorder(int width, Color c) {
		pnlBorder.setBorder(BorderFactory.createLineBorder(c, width));
	}
}
