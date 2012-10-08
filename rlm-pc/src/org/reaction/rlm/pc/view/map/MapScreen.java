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
import org.reaction.rlm.comm.data.Line;
import org.reaction.rlm.comm.data.Particle;
import org.reaction.rlm.comm.data.TypeData;

/**
 * @author Flavio Souza
 * 
 */
public class MapScreen extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = -677687524288703181L;

	private static final int REDUCTION = 30;
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
	boolean isSetPoint;
	float xOrigSimulate;
	float yOrigSimulate;
	float hOrigSimulate;
	
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
		this.isSetPoint = false;
		System.out.println(" Mars map constructor ");
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#setPointSimulate(double, double)
	 */
	@Override
	public void setPointSimulate(float x, float y, float h) {
		this.xOrigSimulate = x;
		this.yOrigSimulate = y;
		this.hOrigSimulate = h;
		this.isSetPoint = true;
	}
	
	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getIsPoint()
	 */
	@Override
	public boolean getIsPoint() {
		return this.isSetPoint;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getDistancesOrigin()
	 */
	@Override
	public double[] getDistancesOrigin() {
		return this.getSimulator().getM().getDistancePointOrig(this.xOrigSimulate, this.yOrigSimulate, this.hOrigSimulate);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(g != null){
			g.drawOval(xpixel((int)this.xOrigSimulate), ypixel((int)this.yOrigSimulate, false), 20, 20);
			
			if(this.getSimulator().getM().getParticles() != null && this.getSimulator().getM().getParticles().size() > 0){
				this.printParticles(g);
			}
		}
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
		g.drawImage(offScreenImage, 0, 0, this); // Writes the Image to
													// thescreen
		/*
		 * float x; float y; double angle; float distance;
		 * 
		 * for (DataShared dsMCL :
		 * this.getCommunicationChannel().getSharedsMCL()) {
		 * 
		 * for (DataShared ds : this.getCommunicationChannel().getShareds()) {
		 * 
		 * distance = ds.getData() - dsMCL.getData();
		 * 
		 * if(TypeData.OBSTACLE.equals(ds.getTypeData())){ angle =
		 * Math.toRadians(ds.getPose().getHeading()); x = (float)
		 * (Math.cos(angle) * distance) + ds.getPose().getX(); y = (float)
		 * (Math.sin(angle) * distance) + ds.getPose().getY();
		 * this.drawMCL(x/REDUCTION, y/REDUCTION); }
		 * 
		 * } }
		 */
	}

	/**
	 * draws the grid with labels
	 */
	public void drawGrid() {

		osGraphics.setColor(Color.green); // Set the line color
		// for y axis
		for (float y = 0; y < 13; y += 0.5f) {
			if (y == 6)
				osGraphics.setColor(Color.blue);
			osGraphics.drawLine(xpixel(-8), ypixel(y, false), xpixel(8),
					ypixel(y, false));
			osGraphics.setColor(Color.green);
		}
		// x axis
		for (float x = -8; x < 8.1; x += 0.5f) {
			if (x == 0)
				osGraphics.setColor(Color.blue);
			osGraphics.drawLine(xpixel(x), ypixel(0, false), xpixel(x),
					ypixel(12.5f, false));
			osGraphics.setColor(Color.green);
		}

		osGraphics.setColor(Color.black); // set number color
		// for y axis
		for (int y = 0; y < 13; y++)
			osGraphics.drawString(y - 6 + "", xpixel(-8.3f),
					ypixel(y - 0.1f, false));
		// x axis
		for (int x = -8; x < 9; x++)
			osGraphics.drawString(x + "", xpixel(x - 0.1f),
					ypixel(-0.4f, false));

		robotPrevX = xpixel(0);
		robotPrevY = ypixel(9, true);

		int xmax = orig + 32 * gridSpacing;// pixels
		System.out.println("x0 " + x0 + " " + xOrig + " " + xmax);

		this.mapSimulator();

		this.repaint();
	}

	
	private void printParticles(){
		osGraphics.setColor(Color.red);
		for (Particle p : simulator.getM().getParticles()) {
			osGraphics.drawOval(xpixel(p.getX()), ypixel(p.getY(), false), 10, 10);
		}
	}
	
	private void printParticles(Graphics g){
		g.setColor(Color.red);
		for (Particle p : simulator.getM().getParticles()) {
			g.drawOval(xpixel(p.getX()), ypixel(p.getY(), false), 5, 5);
		}
	}

	private void mapSimulator() {

		this.setSimulator(new MapSimulator(this));

		osGraphics.setColor(Color.black);
		for (Line p : simulator.getLines()) {
			// osGraphics.fillOval(xpixel(p.getX()), ypixel(p.getY(), false), 5, 5);
			osGraphics.drawLine(xpixel(p.getStartPoint().getX()),ypixel(p.getStartPoint().getY(), false), xpixel(p.getEndPoint().getX()),ypixel(p.getEndPoint().getY(), false));
		}
		
		/*
		 * 
		 * int xp = 3 ; int yp = 10; // y + 6
		 * 
		 * int x = xpixel(xp); int y = ypixel(yp, false); int cx = xpixel(xp +
		 * 2); int cy = ypixel(yp + 2, false);
		 * 
		 * int xPoints[] = {x, x, cx}; int yPoints[] = {y, cy, y};
		 * osGraphics.drawPolygon(xPoints, yPoints, 3); System.out.println("x");
		 * System.out.println(xPoints[0]); System.out.println(xPoints[1]);
		 * System.out.println(xPoints[2]); System.out.println("y");
		 * System.out.println(yPoints[0]); System.out.println(yPoints[1]);
		 * System.out.println(yPoints[2]); int angle = 90; double radians =
		 * Math.toRadians(angle); double radians2 = Math.toRadians(angle+90);
		 * 
		 * x = xpixel(xp); y = ypixel(yp, false);
		 * 
		 * int nxa = (int) (xp + (xp* Math.cos(radians))); int nya = (int) (yp +
		 * (yp* Math.sin(radians2)));
		 * 
		 * int xa = xpixel(nxa); int ya = ypixel(nya, false);
		 * 
		 * int ncx = (int) ((xp +2) + ((xp +2) * Math.cos(radians2))); int ncy =
		 * (int) ((yp +2) + ((yp +2) * Math.sin(radians2))); cx = xpixel(ncx);
		 * cy = ypixel(ncy, false);
		 * 
		 * int xPoints2[] = {x, xa, cx}; int yPoints2[] = {y, cy, ya};
		 * System.out.println("x"); System.out.println(xPoints2[0]);
		 * System.out.println(xPoints2[1]); System.out.println(xPoints2[2]);
		 * System.out.println("y"); System.out.println(yPoints2[0]);
		 * System.out.println(yPoints2[1]); System.out.println(yPoints2[2]);
		 * osGraphics.setColor(Color.red); osGraphics.drawPolygon(xPoints2,
		 * yPoints2, 3);
		 */

	}

	/**
	 * Obstacles shown as magenta dot
	 */
	public void drawObstacle(float xx, float yy) {
		int x = xpixel(xx); // coordinates of intersection
		int y = ypixel(yy, true);
		block = true;
		System.out.print("Point x=" + x + " y=" + y);
		if (x > 0 && y > 0) {
			osGraphics.setColor(Color.magenta);
			osGraphics.fillOval(x - 2, y - 2, 5, 5);// bounding rectangle is 10
													// x 10
		}
		this.repaint();
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
	public int xpixel(double d) {
		return (int) (xOrig + (d * 2 * gridSpacing));
	}

	public int ypixel(double d, boolean coordinates) {
		if (coordinates)
			return (int) ((imageHeight / 2) - (d * 2 * gridSpacing));
		else
			return (int) (y0 - (d * 2 * gridSpacing));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.reaction.rlm.pc.view.map.Map#builder()
	 */
	@Override
	public void builder() {
		float x;
		float y;
		double angle;

		for (DataShared ds : this.getCommunicationChannel().getShareds()) {

			if (TypeData.WALKING.ordinal() == ds.getTypeData()) {
				this.drawRobotPath(ds.getPose().getX() / REDUCTION, ds
						.getPose().getY() / REDUCTION);
			} else if (TypeData.OBSTACLE.ordinal() == ds.getTypeData()) {
				angle = Math.toRadians(ds.getPose().getHeading());
				x = (float) (Math.cos(angle) * ds.getData())
						+ ds.getPose().getX();
				y = (float) (Math.sin(angle) * ds.getData())
						+ ds.getPose().getY();
				this.drawObstacle(x / REDUCTION, y / REDUCTION);
			}

		}
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getHOrig()
	 */
	@Override
	public float getHOrig() {
		return this.hOrigSimulate;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getYOrig()
	 */
	@Override
	public double getYOrig() {
		return this.yOrigSimulate;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getXOrig()
	 */
	@Override
	public double getXOrig() {
		return this.xOrigSimulate;
	}


}
