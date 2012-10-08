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
 * Purpose: This project was developed to obtens�o the 
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
package org.reaction.rlm.pc.location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.reaction.rlm.comm.data.Line;
import org.reaction.rlm.comm.data.Particle;
import org.reaction.rlm.comm.data.Point;
import org.reaction.rlm.pc.view.map.Map;

/**
 * @author Flavio Souza
 * 
 */
public class MCLEngine {

	private static final int RADIUS_SONAR = 90;
	private static final float DISTANCE_MAX = 5;
	
	private Map map;
	
	private List<Particle> particles;
	private List<Line> environment;

	private float xMax;
	private float xMin;
	private float yMax;
	private float yMin;
	
	/**
	 * 
	 */
	public MCLEngine(List<Line> lines, Map map) {
		this.map = map;
		this.environment = lines;
		this.generatesParticles(500);
	}
	
	public void startMCL(double move, double distanceOrigin[]){
		Thread t;
		int iterator = 5;
		
		for (int i = 0; i < iterator; i++) {
			t = new Thread() {
				public void run() {
					map.repaint();
				}
			};
			
			this.compareDistanceParticle(distanceOrigin);
			this.moveParticles(move);
			t.start();
			distanceOrigin = this.map.getDistancesOrigin();
		}
		
		int qtdParticleRemove = this.removeTrashParticle();
		this.generatesParticles(qtdParticleRemove);
	}
	
	/*
	 * Remove particles with lowest score
	 */
	private int removeTrashParticle(){
		int qtdParticleRemove = (int) (this.particles.size() * 0.3);
		
		Collections.sort(this.particles, new Comparator<Particle>() {

			@Override
			public int compare(Particle o1, Particle o2) {
				if(o1.getPont() < o2.getPont())
					return -1;
				else if(o1.getPont() > o2.getPont())
					return +1;
				else
					return 0;
			}
		});
		
		for (int i = 0; i < qtdParticleRemove; i++) {
			this.particles.remove(0);
		}
		
		for (Particle p : this.particles) {
			p.setPont(0.0);
		}
		return qtdParticleRemove;
	}
	
	private void compareDistanceParticle(double[] distanceOrigin){
		double degree;
		float valueRef;
		double angleCount = 0;
		Point pointEnd = null;
		double percentageCloseness;
		double angleScanner = 360 /RADIUS_SONAR; //radius of aperture sonar

		for (Particle particle : this.particles) {
			angleCount = 0;
			percentageCloseness = 0;
			degree = particle.getHeading();
		
			for (int i = 0; i < angleScanner; i++) {
			
				if(degree <= 45 || (degree >= 135 && degree <= 225) || degree >= 315){
					valueRef = (float) (Math.cos(Math.toRadians(degree)) * DISTANCE_MAX) + particle.getX();
					pointEnd = MCLMath.equationLineX(particle.getX(), particle.getY(), degree, valueRef);
				}else{
					valueRef = (float) (Math.sin(Math.toRadians(degree)) * DISTANCE_MAX) + particle.getY();
					pointEnd = MCLMath.equationLineY(particle.getX(), particle.getY(), degree, valueRef);
				}
				
				Double distanceParticleAtObstacle = this.getDistanceParticleAtObstacle(particle, pointEnd, degree);
				
				if(distanceParticleAtObstacle != null){
					double qtdRotate = angleCount / RADIUS_SONAR;
					percentageCloseness += this.computesScoresDistance(distanceOrigin[(int) (qtdRotate)], distanceParticleAtObstacle);
				}
				
				degree += RADIUS_SONAR;
				angleCount += RADIUS_SONAR;
				
				if(degree > 360)
					degree -= 360;
				
			}
			
			particle.setPont(percentageCloseness);
		}
	}
	
	
	public double[] getDistancePointOrig(float xOrigSimulate, float yOrigSimulate, float hOrigSimulate){
		float valueRef;
		Point pointEnd;
		double degree = hOrigSimulate;
		double angleScanner = 360 /RADIUS_SONAR;
		
		double distances[] = new double[(int) angleScanner];
		
		Particle p = new Particle(xOrigSimulate, yOrigSimulate, 0.0, hOrigSimulate);
		
		for (int i = 0; i < angleScanner; i++) {
			if(degree <= 45 || (degree >= 135 && degree <= 225) || degree >= 315){
				valueRef = (float) (Math.cos(Math.toRadians(degree)) * DISTANCE_MAX) + p.getX();
				pointEnd = MCLMath.equationLineX(p.getX(), p.getY(), degree, valueRef);
			}else{
				valueRef = (float) (Math.sin(Math.toRadians(degree)) * DISTANCE_MAX) + p.getY();
				pointEnd = MCLMath.equationLineY(p.getX(), p.getY(), degree, valueRef);
			}

			distances[i] = this.getDistanceParticleAtObstacle(p, pointEnd, degree);
			
			degree += RADIUS_SONAR;
			
			if(degree > 360)
				degree -= 360;
		}
		
		return distances;
	}
	
	private Double getDistanceParticleAtObstacle(Particle particle, Point pointEnd, double degree){
		boolean valid = false;
		
		for (Line l : this.environment) {
			valid = false;
			Point intersectionPoint = MCLMath.intersectionPointOfTwoLine(l.getStartPoint(), l.getEndPoint(), particle, pointEnd);
			
			if(intersectionPoint != null){
				
				if((degree <= 45 || degree >= 315) && intersectionPoint.getX() >= particle.getX()){
					valid = true;
				}else if((degree >= 135 && degree <= 225) && intersectionPoint.getX() <= particle.getX()){
					valid = true;
				}else if((degree >= 45 && degree <= 135) && intersectionPoint.getY() > particle.getY()){
					valid = true;
				}else if((degree >= 225 && degree <= 315) && intersectionPoint.getY() < particle.getY()){
					valid = true;
				}	
				
				if(valid){
					return MCLMath.distanceBetweenTwoPoints(particle, intersectionPoint);
				}
			}
		}
		
		return null;
	}
	
	private void moveParticles(double distance){
		float x, y;
		
		this.moveOrig(distance);
		
		for (Particle particle : this.particles) {
			x = (float) ((Math.cos(Math.toRadians(particle.getHeading())) * distance) + particle.getX());
			y = (float) ((Math.sin(Math.toRadians(particle.getHeading())) * distance) + particle.getY());
			
			particle.setX(x);
			particle.setY(y);
		}
	}
	
	private void moveOrig(double distance){
		float x = (float) ((Math.cos(Math.toRadians(this.map.getHOrig())) * distance) + this.map.getXOrig());
		float y = (float) ((Math.sin(Math.toRadians(this.map.getHOrig())) * distance) + this.map.getYOrig());
		
		this.map.setPointSimulate(x, y, this.map.getHOrig());
	}
	
	/*
	private double computesDistanceParticle(Particle particle, double angle){
		return 0.0;
	}
	*/
	private Point computesDistanceParticle(Particle particle, double angle){
		return MCLMath.equationLineX(particle, angle, particle.getX()+1);
	}
	
	private Point computesDistanceParticle(Particle particle, double angle, float x){
		return MCLMath.equationLineX(particle, angle, x);
	}
	
	private Point computesDistanceParticle(Particle particle, double angle, float x, boolean useX){
		if(useX)
			return MCLMath.equationLineX(particle, angle, x);
		else 
			return MCLMath.equationLineY(particle, angle, x);
	}
	
	private double computesScoreProbabilistic(double percentageCloseness){
		return (100 * percentageCloseness / 12);
	}
	
	private double computesScoresDistance(double expectedDistance, double realDistance){
		double point = 0 ;
		double percent = 1 / (360/RADIUS_SONAR);
		
		if(expectedDistance >= realDistance)
			point = realDistance / expectedDistance;
		else {
			int division = (int) (realDistance / expectedDistance);
			point = ((realDistance / expectedDistance) - division) * (2/division);
		}
		
		return point;
	}
	
	
	private void generatesParticles(int quantity) {
		float x, y, angle;
		Particle p;
		
		if(this.particles == null)
			this.particles = new ArrayList<Particle>();
		
		this.findMaxAndMin();

		for (int i = 0; i < quantity; i++) {
			x = randInRangeInc(xMin, xMax);
			y = randInRangeInc(yMin, yMax);
			angle = randInRangeInc(0, 360);
			
			p = new Particle(x, y, 0, angle);
			
			//if (!this.particles.contains(p))
				this.particles.add(p);
			//else
				//i--;
		}
	}

	private void findMaxAndMin() {
		this.xMax = this.environment.get(0).getStartPoint().getX();
		this.xMin = this.environment.get(0).getStartPoint().getX();
		this.yMax = this.environment.get(0).getStartPoint().getY();
		this.yMin = this.environment.get(0).getStartPoint().getY();

		int qtyPoints = this.environment.size()*2;
		Point[] points = new Point[qtyPoints];
		
		int q =0;
		for (int i = 0; i < this.environment.size(); i++) {
			points[q++] = this.environment.get(i).toArray()[0];
			points[q++] = this.environment.get(i).toArray()[1];
		}
		
		for (Point p : points) {
			if (this.xMax < p.getX())
				this.xMax = p.getX();

			if (this.xMin > p.getX())
				this.xMin = p.getX();

			if (this.yMax < p.getY())
				this.yMax = p.getY();

			if (this.yMin > p.getY())
				this.yMin = p.getY();

		}
	}

	private int randInRangeInc(float min, float max) {
		return (int) (min + (Math.random() * (max - min)));
	}

	/**
	 * @return the particles
	 */
	public List<Particle> getParticles() {
		return particles;
	}

	/**
	 * @param particles
	 *            the particles to set
	 */
	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}

}