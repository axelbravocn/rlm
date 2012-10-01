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
package org.reaction.rlm.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.reaction.rlm.comm.data.DataShared;

/**
 * @author Flavio Souza
 * 
 */
public abstract class CommunicationChannelGeneric extends Thread implements
		CommunicationChannel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380123518631103307L;

	private boolean isConnected;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;

	private List<DataShared> shareds;
	private List<DataShared> sharedsMCL;

	/**
	 * 
	 */
	public CommunicationChannelGeneric() {
		this.shareds = new ArrayList<DataShared>();
		this.sharedsMCL = new ArrayList<DataShared>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public abstract void run();

	/**
	 * @return the isConnected
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * @param isConnected
	 *            the isConnected to set
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	/**
	 * @return the dataIn
	 */
	public DataInputStream getDataIn() {
		return dataIn;
	}

	/**
	 * @param dataIn
	 *            the dataIn to set
	 */
	public void setDataIn(DataInputStream dataIn) {
		this.dataIn = dataIn;
	}

	/**
	 * @return the dataOut
	 */
	public DataOutputStream getDataOut() {
		return dataOut;
	}

	/**
	 * @param dataOut
	 *            the dataOut to set
	 */
	public void setDataOut(DataOutputStream dataOut) {
		this.dataOut = dataOut;
	}

	/**
	 * @return the shareds
	 */
	public List<DataShared> getShareds() {
		return shareds;
	}

	/**
	 * @param shareds
	 *            the shareds to set
	 */
	public void setShareds(List<DataShared> shareds) {
		this.shareds = shareds;
	}

	/**
	 * @return the sharedsMCL
	 */
	public List<DataShared> getSharedsMCL() {
		return sharedsMCL;
	}

	/**
	 * @param sharedsMCL
	 *            the sharedsMCL to set
	 */
	public void setSharedsMCL(List<DataShared> sharedsMCL) {
		this.sharedsMCL = sharedsMCL;
	}

}
