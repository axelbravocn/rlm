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
package org.reaction.rlm.nxt.navigator.behavior;

import java.io.Serializable;

import lejos.robotics.subsumption.Behavior;

import org.reaction.rlm.nxt.comm.CommunicationChannelRobot;
import org.reaction.rlm.nxt.motor.MotorNxt;
import org.reaction.rlm.nxt.navigator.ControlNavigator;
import org.reaction.rlm.nxt.navigator.behavior.realtime.SendDataRealTime;
import org.reaction.rlm.nxt.navigator.behavior.realtime.WalkRealTime;

/**
 * @author Flavio Souza
 *
 */
public class WalkBehavior implements Behavior, Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = -6459445800786937318L;
	
	private MotorNxt motorNxt;
	private CommunicationChannelRobot comm;
	private WalkRealTime walking;
	private SendDataRealTime sendData;
	/**
	 * @param motorNxt
	 * @param touchSensor
	 * @param ultrasonicSensor
	 * @param comm
	 */
	public WalkBehavior(MotorNxt motorNxt, CommunicationChannelRobot comm) {
		this.comm = comm;
		this.motorNxt = motorNxt;
	}
	
	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	@Override
	public boolean takeControl() {
		//return true;
		return ControlNavigator.begaviorIndex == WalkBehavior.serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	@Override
	public void action() {
		//this.walking = new WalkRealTime(this.motorNxt);
		//this.sendData = new SendDataRealTime(this.comm, this.motorNxt);
		
		//this.walking.start();
		//this.sendData.start();
		
		this.motorNxt.moveForward((int) ControlNavigator.TRAVEL_DISTANCE);
		ControlNavigator.begaviorIndex = NearbyObstacleBehavior.serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	@Override
	public void suppress() {
		this.motorNxt.stop();
		this.sendData.setWalking(false);
		this.sendData = null;
		this.walking = null;
	}

}
