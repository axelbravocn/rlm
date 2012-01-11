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
package rlm.ia.reaction.trajectory.navigator;

import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.ArcRotateMoveController;
import lejos.robotics.navigation.Pose;
import rlm.ia.reaction.motor.Gear;
import rlm.ia.reaction.view.Directions;
import rlm.ia.reaction.view.Signal;

/**
 * @author flavio
 * 
 */
public class Navigator {

	private Gear fly;
	private Signal view;
	private ArcRotateMoveController pilot;
	private OdometryPoseProvider poseProvider;
	private Pose pose = new Pose();
	
	/**
	 * 
	 */
	public Navigator() {
		this.fly = new Gear();
		this.view = new Directions();

		pilot = this.fly.getMotor();
		poseProvider = new OdometryPoseProvider(pilot);
		
	}

	// Method getting and setting

	/**
	 * @return the fly
	 */
	public Gear getFly() {
		return fly;
	}

	/**
	 * @param fly
	 *            the fly to set
	 */
	public void setFly(Gear fly) {
		this.fly = fly;
	}

	/**
	 * @return the view
	 */
	public Signal getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(Signal view) {
		this.view = view;
	}

	/**
	 * @return the poseProvider
	 */
	public OdometryPoseProvider getPoseProvider() {
		return poseProvider;
	}

	/**
	 * @return the pose
	 */
	public Pose getPose() {
		return pose;
	}
	
}
