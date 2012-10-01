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
package org.reaction.rlm.nxt.navigator.behavior;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

import org.reaction.rlm.comm.data.TypeData;
import org.reaction.rlm.nxt.comm.CommunicationChannelRobot;
import org.reaction.rlm.nxt.motor.MotorNxt;
import org.reaction.rlm.nxt.motor.observer.ObserverMotor;
import org.reaction.rlm.nxt.motor.observer.TypeGearMotor;
import org.reaction.rlm.nxt.util.SensorUtil;

/**
 * @author Flavio Souza
 *
 */
public class MCLBehavior implements Behavior {
	
	private MotorNxt motorNxt;
	private CommunicationChannelRobot comm;
	private ObserverMotor observerMotor;
	private UltrasonicSensor ultrasonicSensor;
	
	/**
	 * @param observerMotor 
	 * @param motorNxt
	 * @param ultrasonicSensor
	 * @param comm
	 */
	public MCLBehavior(CommunicationChannelRobot comm, MotorNxt motorNxt, ObserverMotor observerMotor, UltrasonicSensor ultrasonicSensor) {
		this.comm = comm;
		this.motorNxt = motorNxt;
		this.observerMotor = observerMotor;
		this.ultrasonicSensor = ultrasonicSensor;
	}
	
	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	@Override
	public boolean takeControl() {
		return this.ultrasonicSensor.getDistance() > SensorUtil.ULTRA_DIST_MIN && this.ultrasonicSensor.getDistance() < SensorUtil.ULTRA_DIST_MAX;
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	@Override
	public void action() {
		this.motorNxt.stop();
		
		this.observerMotor.rotate(TypeGearMotor.ROTATE_LEFT);
		this.comm.addPoint(TypeData.MCL.ordinal(), this.motorNxt.getPosition(), this.ultrasonicSensor.getDistance(), TypeGearMotor.ROTATE_LEFT);
		
		this.observerMotor.rotate(TypeGearMotor.ROTATE_RIGHT);
		this.comm.addPoint(TypeData.MCL.ordinal(), this.motorNxt.getPosition(), this.ultrasonicSensor.getDistance(), TypeGearMotor.ROTATE_RIGHT);
		
		this.observerMotor.rotate(TypeGearMotor.ROTATE_BACK);
		this.comm.addPoint(TypeData.MCL.ordinal(), this.motorNxt.getPosition(), this.ultrasonicSensor.getDistance(), TypeGearMotor.ROTATE_BACK);
		
		this.observerMotor.rotate(TypeGearMotor.ROTATE_FRONT);
		this.comm.addPoint(TypeData.MCL.ordinal(), this.motorNxt.getPosition(), this.ultrasonicSensor.getDistance(), TypeGearMotor.ROTATE_FRONT);
		
		this.motorNxt.moveForward();
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	@Override
	public void suppress() {
		this.motorNxt.stop();		
	}

}
