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
package org.reaction.rlm.pc.data;

import java.awt.Point;

/**
 * @author Flavio Souza
 * 
 */
public class DataShared {

	private Point point;
	private TypeData typeData;

	/**
	 * 
	 */
	public DataShared() {
		this.point = new Point();
	}
	
	/**
	 * @param position
	 * @param obstacle
	 */
	public DataShared(Point point, TypeData type) {
		this.point = point;
		this.typeData = type;
	}

	/**
	 * @return the Point
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * @param Point
	 *            the Point to set
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

	/**
	 * @return the typeData
	 */
	public TypeData getTypeData() {
		return typeData;
	}

	/**
	 * @param typeData
	 *            the typeData to set
	 */
	public void setTypeData(TypeData typeData) {
		this.typeData = typeData;
	}

}