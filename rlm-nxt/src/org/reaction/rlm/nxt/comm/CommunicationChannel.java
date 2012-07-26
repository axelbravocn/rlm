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
package org.reaction.rlm.nxt.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.navigation.Pose;

import org.reaction.rlm.nxt.data.DataShared;

/**
 * @author Flavio Souza
 * 
 */
public class CommunicationChannel extends Thread{

	private static final int LIMIT_HISTORY_DATA = 100;
	
	private static CommunicationChannel channel;

	private boolean isConnected;
	private boolean isSendingPermission = true;
	
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private NXTConnection connection;

	private List<DataShared> shareds;
	private List<DataShared> historyShared;

	public static CommunicationChannel getInstance() {
		if (channel == null) {
			channel = new CommunicationChannel();
		}

		return channel;
	}

	/**
	 * 
	 */
	private CommunicationChannel() {
		this.setConnected(false);
		this.setSendingPermission(false);
		this.shareds = new ArrayList<DataShared>();
		this.historyShared = new ArrayList<DataShared>();
		this.start();
	}
	
	/**
	 * @throws IOException
	 * 
	 */
	public NXTConnection connectServer() throws IOException {
		System.out.println("Found on server");
		this.connection = Bluetooth.waitForConnection();
		System.out.println("Connected on server");
		this.dataOut = (DataOutputStream) connection.openDataOutputStream();
		this.dataIn = (DataInputStream) connection.openDataInputStream();

		this.channel.setConnected(true);
		
		return connection;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		DataShared dShared = null;
		while (true){
			if(this.shareds != null && this.shareds.size() > 0) {
		
				try {
					dShared = this.shareds.get(0);
					this.dataOut.writeInt(dShared.getTypeData());
					this.dataOut.writeFloat(dShared.getPose().getX());
					this.dataOut.writeFloat(dShared.getPose().getY());
					//this.shareds.remove(dShared);
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
	}
	
	
	public void addPoint(int type, Pose pose){
		DataShared ds = new DataShared(pose, type);
		this.shareds.add(ds);
		
		if(this.historyShared.size() >= LIMIT_HISTORY_DATA)
			this.historyShared.remove(0);
		
		this.historyShared.add(ds);
	}
	
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
	 * @return the channel
	 */
	public CommunicationChannel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(CommunicationChannel channel) {
		this.channel = channel;
	}

	/**
	 * @return the dataIn
	 */
	public DataInputStream getDataIn() {
		return dataIn;
	}

	/**
	 * @param dataIn the dataIn to set
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
	 * @param dataOut the dataOut to set
	 */
	public void setDataOut(DataOutputStream dataOut) {
		this.dataOut = dataOut;
	}

	/**
	 * @return the isSendingPermission
	 */
	public boolean isSendingPermission() {
		return isSendingPermission;
	}

	/**
	 * @param isSendingPermission the isSendingPermission to set
	 */
	public void setSendingPermission(boolean isSendingPermission) {
		this.isSendingPermission = isSendingPermission;
		
		if(isSendingPermission == true)
			this.start();
	}

}
