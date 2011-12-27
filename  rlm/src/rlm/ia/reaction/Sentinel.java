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

package rlm.ia.reaction;

import java.util.ArrayList;
import java.util.List;

import rlm.ia.reaction.trajectory.Trajectory;
import rlm.ia.reaction.view.Token;

/**
 * @author flavio
 * 
 */
public class Sentinel {

	List<Trajectory> list;

	public void addTrajectory(Trajectory trajectory) {
		this.getList().add(trajectory);
	}

	public void notify(Token token) {
		for (Trajectory trajectory : this.getList()) {
			trajectory.notification(token);
		}
	}

	/**
	 * @return the list
	 */
	public List<Trajectory> getList() {
		if (list == null)
			list = new ArrayList<Trajectory>();

		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Trajectory> list) {
		this.list = list;
	}

}
