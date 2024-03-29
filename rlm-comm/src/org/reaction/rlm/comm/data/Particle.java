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
package org.reaction.rlm.comm.data;

/**
 * @author Flavio Souza
 * 
 */
public class Particle extends Point {

	private float pont;
	private float heading;

	/**
	 * 
	 */
	protected Particle() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public Particle(float x, float y, float pont, float heading) {
		super(x, y);
		this.pont = pont;
		this.heading = heading;
	}

	/**
	 * @return the pont
	 */
	public float getPont() {
		return pont;
	}

	/**
	 * @param pont
	 *            the pont to set
	 */
	public void setPont(float pont) {
		this.pont = pont;
	}

	/**
	 * @return the heading
	 */
	public float getHeading() {
		return heading;
	}

	/**
	 * @param heading
	 *            the heading to set
	 */
	public void setHeading(float heading) {
		this.heading = heading;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " Head="+this.heading+ " Point="+this.pont;
	}

}
