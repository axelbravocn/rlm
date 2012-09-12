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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextField;

import org.reaction.rlm.comm.data.DataShared;

/**
 * @author Flavio Souza
 * 
 */
public class MapScreen extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = -677687524288703181L;

	/**
	 * The robot path is drawn and updated on this object. <br>
	 * created by makeImage which is called by paint(); guarantees image always
	 * exists before used;
	 */
	Image offScreenImage;
	/**
	 * width of the dawing area;set by makeImage,used by clearImage
	 */
	int imageWidth;
	/**
	 * height of the dawing are; set by makeImage,used by clearImage
	 */
	int imageHeight;
	/**
	 * set by paint, clear; used by paint; indicates first plot of robot
	 * position
	 */
	boolean first = true;
	/**
	 * the graphics context of the image; set by makeImage, used by all methods
	 * that draw on the image
	 */
	private Graphics2D osGraphics;
	/**
	 * robot position ; used by checkContinuity, drawRobotPath
	 */
	private int robotPrevX = 50;
	/**
	 * robot position; used by checkContinuity, drawRobotPath
	 */
	private int robotPrevY = 400;
	int gridSpacing = 25;
	int orig = 75;
	int xOrig;
	/**
	 * y origin in pixels
	 */
	private int y0;
	private int x0;
	/**
	 * node status - true if blocked; set by drawObstacle, used by drawRobotPath
	 */

	public TextField textX;
	public TextField textY;
	boolean block = false;

	/**
	 * simple constructor
	 */
	public MapScreen() {
		setBackground(Color.white);
		System.out.println(" Mars map constructor ");
	}

	/**
	 * Initialize the off screen canvas<br>
	 * Create the offScreenImage, or make a new one if applet size has changed.
	 */
	public void makeImage() {
		if (offScreenImage == null || first) {

			imageWidth = getSize().width;
			imageHeight = getSize().height;
			y0 = 20 + imageHeight - orig;
			// x0 = imageWidth/2;
			xOrig = imageWidth / 2;
			System.out.println(imageWidth + " " + imageHeight);
			offScreenImage = createImage(imageWidth, imageHeight);
			osGraphics = (Graphics2D) offScreenImage.getGraphics();
			osGraphics.setColor(getBackground());
			osGraphics.fillRect(0, 0, imageWidth, imageHeight);// erase
																// everything
			drawGrid();
		}
	}

	/**
	 * Copy off screen canvas to the screen.
	 **/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (offScreenImage == null)
			makeImage();
		g.drawImage(offScreenImage, 0, 0, this); // Writes the Image to the
													// screen
	}

	/**
	 * draws the grid with labels
	 */
	public void drawGrid() {
		
		osGraphics.setColor(Color.green); // Set the line color
		// for y axis
		for (float y = 0; y < 13; y += 0.5f){
			if(y == 6)
				osGraphics.setColor(Color.blue);
			osGraphics.drawLine(xpixel(-8), ypixel(y, false), xpixel(8), ypixel(y, false));
			osGraphics.setColor(Color.green);
		}
		// x axis
		for (float x = -8; x < 8.1; x += 0.5f){
			if(x == 0)
				osGraphics.setColor(Color.blue);
			osGraphics.drawLine(xpixel(x), ypixel(0, false), xpixel(x), ypixel(12.5f, false));
			osGraphics.setColor(Color.green);
		}
		
		osGraphics.setColor(Color.black); // set number color
		// for y axis
		for (int y = 0; y < 13; y++)
			osGraphics.drawString(y-6 + "", xpixel(-8.3f), ypixel(y - 0.1f, false));
		// x axis
		for (int x = -8; x < 9; x++)
			osGraphics.drawString(x + "", xpixel(x - 0.1f), ypixel(-0.4f, false));

		robotPrevX = xpixel(0);
		robotPrevY = ypixel(9, true);

		int xmax = orig + 32 * gridSpacing;// pixels
		System.out.println("x0 " + x0 + " " + xOrig + " " + xmax);
		
		repaint();
	}

	/**
	 * Obstacles shown as magenta dot
	 */
	public void drawObstacle(float xx, float yy) {
		int x = xpixel(xx); // coordinates of intersection
		int y = ypixel(yy, true);
		block = true;
		if (x > 0 && y > 0) {
			osGraphics.setColor(Color.magenta);
			osGraphics.fillOval(x - 2, y - 2, 5, 5);// bounding rectangle is 10
													// x 10
		}
		repaint();
	}

	/**
	 * blue line connects current robot position to last position if adjacent to
	 * current position
	 */
	public void drawRobotPath(float xx, float yy) {
		int x = xpixel(xx);
		int y = ypixel(yy, true);
		if (x > 0 && y > 0)
			osGraphics.setColor(Color.blue);
		osGraphics.fillOval(x - 2, y - 2, 4, 4); // show robot position
		repaint();
		osGraphics.drawLine(robotPrevX, robotPrevY, x, y);
		robotPrevX = x;
		robotPrevY = y;
	}

	/**
	 * convert grid coordinates to pixels
	 */
	public int xpixel(float x) {
		return xOrig + (int) (x * 2 * gridSpacing);
	}

	public int ypixel(float y, boolean coordinates) {
		if(coordinates)
			return (imageHeight/2) - (int) (y * 2 * gridSpacing);
		else
			return y0 - (int) (y * 2 * gridSpacing);
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#builder()
	 */
	@Override
	public void builder() {
		float x;
		float y;
		double angle;
		
		for (DataShared ds : this.getCommunicationChannel().getShareds()) {
			angle = Math.toRadians(ds.getPose().getHeading());
			x = (float) (Math.cos(angle) * ds.getData()) + ds.getPose().getX();
			y = (float) (Math.sin(angle) * ds.getData()) + ds.getPose().getY();
			System.out.println("x="+x+" y="+y);
			System.out.println("x/30="+x/30+" y/30="+y/30);
			this.drawObstacle(x/30, y/30);
		}
	}

}
