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
package rlm.ia.reaction.trajectory.behavior;

import rlm.ia.reaction.trajectory.navigator.Navigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * @author flavio
 * 
 */
public class DivertBehavior extends Navigator implements Behavior {

	/*
	 * (non-Javadoc)
	 * 
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	@Override
	public void action() {
		boolean exit = true, left = false;

		int count = 0;
		try {

			while (exit) {
				this.getFly().stop();

				if (count < 3 && !left) {

					this.getFly().left();

					this.getFly().backward();

					Thread.sleep(100);

					this.getFly().stop();

					this.getFly().axisStabilized();

					count++;
					if (count > 2)
						left = true;

				} else {
					this.getFly().rigth();

					this.getFly().backward();

					Thread.sleep(100);

					this.getFly().stop();

					this.getFly().axisStabilized();

					count--;
					if (count == 0)
						left = false;
				}

				if (this.getView().distance() > 15) {
					exit = false;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Arbitrator go = new Arbitrator(new Behavior[] { new ForwardBehavior() });
		go.start();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	@Override
	public void suppress() {
		this.getFly().stop();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

}
