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
package org.reaction.rlm.pc.view.map;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.image.VolatileImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Flavio Souza
 * 
 */
public class MapPanel extends JPanel {

	public final static int MAP_MIN_SCALE = 1;
	public final static int MAP_MAX_SCALE = 150;

	private int scale;
	private Point center;
	private ArrayList<ChangeListener> scaleChanged;
	private ArrayList<ChangeListener> centerChanged;
	
	/**
	 * 
	 */
	public MapPanel() {
		this.scaleChanged = new ArrayList<ChangeListener>();
		this.centerChanged = new ArrayList<ChangeListener>();

	}
	 
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		this.setCenter(new Point((this.getWidth() / 2), (this.getHeight() / 2)));

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		
		VolatileImage volatileImage = createVolatileImage(this.getWidth(), this.getHeight());
		
		Graphics OSIG = volatileImage.getGraphics();
		OSIG.clearRect(0, 0, volatileImage.getWidth(), volatileImage.getHeight());

		g.drawImage(volatileImage, 0, 0, null);
	}
	
	/**
	 * @param value
	 * @param b
	 */
	public void setMapScale(int mapScale, boolean triggerChanged) {
		this.scale = Math.max(Math.min(mapScale, MAP_MAX_SCALE), MAP_MIN_SCALE);
		this.repaint();

		if (triggerChanged && scaleChanged != null) {
			for (ChangeListener listener : scaleChanged) {
				listener.stateChanged(new ChangeEvent(this));
			}
		}
	}
	
	
	/**
	 * @return the scaleChanged
	 */
	public ArrayList<ChangeListener> getScaleChanged() {
		return scaleChanged;
	}

	/**
	 * @param scaleChanged
	 *            the scaleChanged to set
	 */
	public void setScaleChanged(ArrayList<ChangeListener> scaleChanged) {
		this.scaleChanged = scaleChanged;
	}

	/**
	 * @return the centerChanged
	 */
	public ArrayList<ChangeListener> getCenterChanged() {
		return centerChanged;
	}

	/**
	 * @param centerChanged
	 *            the centerChanged to set
	 */
	public void setCenterChanged(ArrayList<ChangeListener> centerChanged) {
		this.centerChanged = centerChanged;
	}

	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * @return the center
	 */
	public Point getCenter() {
		return center;
	}

	/**
	 * @param center the center to set
	 */
	public void setCenter(Point center) {
		this.center = center;
	}
	
	

}
