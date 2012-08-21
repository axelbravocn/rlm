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
package org.reaction.rlm.nxt.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.navigation.Pose;

import org.reaction.rlm.nxt.data.DataShared;
import org.reaction.rlm.nxt.data.TypeData;

/**
 * @author Flavio Souza
 * 
 */
public class CommunicationChannelRobot extends Thread{

	private static final int LIMIT_HISTORY_DATA = 100;
	
	private static CommunicationChannelRobot channel;

	private boolean isConnected;
	private boolean isSendingPermission = true;
	
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private NXTConnection connection;

	private List<DataShared> shareds;
	private List<DataShared> historyShared;

	public static CommunicationChannelRobot getInstance() {
		if (channel == null) {
			channel = new CommunicationChannelRobot();
		}

		return channel;
	}

	/**
	 * 
	 */
	private CommunicationChannelRobot() {
		this.setConnected(false);
		this.setSendingPermission(false);
		this.shareds = new ArrayList<DataShared>();
		this.historyShared = new ArrayList<DataShared>();
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
		this.start();
		
		return connection;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		 while (true)readData();
	}
	
	
	private void readData(){
		int code = -1;
		try {
			code = dataIn.readInt();
			System.out.println("code " + code);

			boolean haveSend = true;
			while (haveSend) {
				if(this.shareds.size() > 0){
					writeData(this.shareds.get(0));
					this.shareds.remove(this.shareds.get(0));
					haveSend = false;
				}
			}
			
		} catch (IOException e) {
			System.out.println("Read exception " + e);
		}
		
	}
	
	private void writeData(DataShared dShared){
		try {
			dataOut.writeInt(dShared.getTypeData());
			dataOut.writeFloat(Float.valueOf(dShared.getPose().getX()));
			dataOut.writeFloat(Float.valueOf(dShared.getPose().getY()));
			dataOut.writeFloat(Float.valueOf(dShared.getPose().getHeading()));
			
			if(TypeData.OBSTACLE.ordinal() == dShared.getTypeData())
				dataOut.writeFloat(Float.valueOf(dShared.getData()));
			
			dataOut.flush();
		} catch (IOException e) {
		}
	}

	/**
	 * @param ordinal
	 * @param position
	 */
	public void addPoint(int type, Pose pose){
		DataShared ds = new DataShared(pose, type);
		this.shareds.add(ds);
		
		if(this.historyShared.size() >= LIMIT_HISTORY_DATA)
			this.historyShared.remove(0);
		
		this.historyShared.add(ds);
	}
	
	/**
	 * @param type
	 * @param pose
	 * @param data
	 */
	public void addPoint(int type, Pose pose, int data) {
		DataShared ds = new DataShared(pose, type, data);
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
	public CommunicationChannelRobot getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(CommunicationChannelRobot channel) {
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
		
	}

}
