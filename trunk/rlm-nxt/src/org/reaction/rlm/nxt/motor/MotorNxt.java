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
package org.reaction.rlm.nxt.motor;

import java.io.IOException;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.util.PilotProps;

/**
 * @author Flavio Souza
 *
 */
public class MotorNxt {

	private Navigator navigator;
	private DifferentialPilot differentialPilot;
	private OdometryPoseProvider odometryPoseProvider;
	
	/**
	 * 
	 */
	public MotorNxt() {
		/*this.differentialPilot = new DifferentialPilot(DifferentialPilot.WHEEL_SIZE_NXT2, 15.5, Motor.A, Motor.C);
		this.odometryPoseProvider = new OdometryPoseProvider(differentialPilot);
		this.differentialPilot.addMoveListener(odometryPoseProvider);
		
		this.navigator = new Navigator(this.differentialPilot);
		*/
		PilotProps pp = new PilotProps();
    	try {
			pp.loadPersistentValues();
    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "5.5"));
    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "15.5"));
    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "A"));
    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
    	boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
    	
    	differentialPilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
    	odometryPoseProvider = new OdometryPoseProvider(differentialPilot);
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
	
	
	/**
	 * 
	 */
	public void moveForward(){
		this.differentialPilot.forward();
	}

	/**
	 * 
	 */
	public void moveForward(int distance){
		
	}


	/**
	 * 
	 */
	public void stop() {
		//this.differentialPilot.stop();
		this.navigator.stop();
	}


	/**
	 * 
	 */
	public void backward() {
		this.differentialPilot.backward();
	}

	/**
	 * @param i
	 */
	public void rotate(double angle) {
		this.differentialPilot.rotate(angle);
	}

	/**
	 * @return
	 */
	public Pose getPosition() {
		return this.odometryPoseProvider.getPose();
	}


	
	
}
