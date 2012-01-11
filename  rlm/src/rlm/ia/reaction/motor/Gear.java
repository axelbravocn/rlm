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

package rlm.ia.reaction.motor;

import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * @author flavio
 * 
 */
public class Gear implements Motor {

	protected static final int ANGLE_DEFAULT = 45;

	private static DifferentialPilot motor;
	private static NXTRegulatedMotor flying;
	private static int angleOld = 0;

	/**
	 * @return the motor
	 */
	public DifferentialPilot getMotor() {
		if (motor == null) {
			motor = new DifferentialPilot(WHEEL_DIAMETER, WHEEL_THICK,
					MOTOR_TRACTION_ONE, MOTOR_TRACTION_TWO);
		}

		return motor;
	}

	/**
	 * @return the flying
	 */
	public NXTRegulatedMotor getFlying() {

		if (flying == null) {
			flying = MOTOR_FRONT_ENGINE;
		}

		return flying;
	}

	/*
	 * Configuration Motor
	 */
	
	private void configurationMotor(){
		motor.setTravelSpeed(20);
		motor.setRotateSpeed(180);
	}
	
	/*
	 * movement control
	 */

	public void forward() {
		this.getMotor().backward();
	}

	public void backward() {
		this.getMotor().forward();
	}

	public void stop() {
		this.getMotor().stop();
	}

	/**
	 * 
	 */
	public void left() {
		this.getFlying().rotate(this.ANGLE_DEFAULT);
		this.controlAngle(this.ANGLE_DEFAULT);
	}

	public void rigth() {
		int angleReversed = (-1) * this.ANGLE_DEFAULT;
		this.getFlying().rotate(angleReversed);
		this.controlAngle(angleReversed);
	}

	public void axisStabilized() {
		if (this.angleOld != 0) {
			this.getFlying().rotate((-1) * this.angleOld);
			this.angleOld = 0;
		}
	}

	public void turn(int angle) {
		this.getFlying().rotate(angle);
		this.controlAngle(angle);
	}

	private void controlAngle(int angleNew) {
		this.angleOld = +angleNew;
		System.out.println(angleOld);
	}

}
