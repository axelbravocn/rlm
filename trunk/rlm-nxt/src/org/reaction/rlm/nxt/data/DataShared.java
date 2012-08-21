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
 * PurPoint: This project was developed to obtens�o the 
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
 * PARTICULAR PURPoint AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 * THE SOFTWARE OR THE USE OR OTHERS IN THE SOFTWARE.
 * 
 *	
 **********************************************************************************
 */
package org.reaction.rlm.nxt.data;

import lejos.robotics.navigation.Pose;

/**
 * @author Flavio Souza
 * 
 */
public class DataShared {

	private Pose pose;
	private int typeData;
	private int data;

	/**
	 * @param position
	 * @param obstacle
	 */
	public DataShared(Pose pose, int type) {
		this.pose = pose;
		this.typeData = type;
	}

	/**
	 * @param pose
	 * @param type
	 * @param data
	 */
	public DataShared(Pose pose, int type, int data) {
		this.pose = pose;
		this.typeData = type;
		this.data = data;
	}

	/**
	 * @return the pose
	 */
	public Pose getPose() {
		return pose;
	}

	/**
	 * @param pose
	 *            the pose to set
	 */
	public void setPose(Pose pose) {
		this.pose = pose;
	}

	/**
	 * @return the typeData
	 */
	public int getTypeData() {
		return typeData;
	}

	/**
	 * @param typeData
	 *            the typeData to set
	 */
	public void setTypeData(int typeData) {
		this.typeData = typeData;
	}
	
	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "x="+this.getPose().getX()+" y="+this.getPose().getY()+" heading="+this.getPose().getHeading()+" data="+this.getData();
	}
	
}
