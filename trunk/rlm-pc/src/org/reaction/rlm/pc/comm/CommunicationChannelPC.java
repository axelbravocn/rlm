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
package org.reaction.rlm.pc.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;
import lejos.robotics.navigation.Pose;

import org.reaction.rlm.pc.data.DataShared;
import org.reaction.rlm.pc.data.TypeData;
import org.reaction.rlm.pc.listener.DataListener;
import org.reaction.rlm.pc.view.map.MapPanel;

/**
 * @author Flavio Souza
 * 
 */
public class CommunicationChannelPC extends Thread {
	private static CommunicationChannelPC channel;

	private final static String ADDRESS_NXT = "00:16:53:0F:1C:97";
	private final static String NAME_NXT = "NXT";

	private boolean isConnected;

	private DataListener dataListener;
	
	private NXTConnector conn;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;

	private List<DataShared> shareds;
	
	public static CommunicationChannelPC getInstance(MapPanel map) {
		if (channel == null) {
			channel = new CommunicationChannelPC(map);
		}

		return channel;
	}

	/**
	 * 
	 */
	private CommunicationChannelPC(MapPanel map) {
		this.shareds = new ArrayList<DataShared>();
		dataListener = new DataListener(map);
	}

	/**
	 * @throws IOException
	 * 
	 */
	public NXTConnector connectNXT() throws IOException {
		this.conn = new NXTConnector();
		
		this.conn.addLogListener(new NXTCommLogListener(){
				public void logEvent(String message) {
					System.out.println("BTSend Log.listener: "+message);
				}
	
				public void logEvent(Throwable throwable) {
					System.out.println("BTSend Log.listener - stack trace: ");
					 throwable.printStackTrace();
				}
			} 
		);
		// Connect to any NXT over Bluetooth
		boolean connected = this.conn.connectTo(NAME_NXT, ADDRESS_NXT, NXTCommFactory.BLUETOOTH);
	
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		
		this.dataOut = new DataOutputStream(this.conn.getOutputStream());
		this.dataIn = new DataInputStream(this.conn.getInputStream());
		
		this.channel.setConnected(true);

		this.start();

		return this.conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (this.channel.isConnected()) {
			readData();
		}
	}

	
	private void readData(){
		System.out.println("reading ");
		int t = -1;
		float x = 0;
		float y = 0;
		float h = 0;
		float d = 0;
		try {
			writeData(1);
			t = dataIn.readInt();
			x = dataIn.readFloat();
			y = dataIn.readFloat();
			h = dataIn.readFloat();
			
			if(TypeData.OBSTACLE.ordinal() == t){
				d = dataIn.readFloat();
				System.out.println("data  " + t + " " + x + " " + y+ " " + h + 	" " + d);
				this.addDataShared(t, x, y, h, d);
			}else{
				System.out.println("data  " + t + " " + x + " " + y+ " " + h);
				this.addDataShared(t, x, y, h);
			}
			
			Thread.sleep(50);
		} catch (IOException e) {
			writeData(0);
			System.out.println("error");
		} catch (InterruptedException e) {
			writeData(0);
			System.out.println("error");
		}
	}
	
	/**
	 * @param t
	 * @param x
	 * @param y
	 * @param h
	 * @param d
	 */
	private void addDataShared(int t, float x, float y, float h, float d) {
		DataShared dShared = new DataShared();
		dShared.setTypeData(TypeData.values()[t]);
		dShared.setPose(new Pose(x, y, h));
		dShared.setData(d);
		
		this.shareds.add(dShared);
		dataListener.actionPerformed(null);		
	}

	/**
	 * @param t
	 * @param x
	 * @param y
	 * @param h
	 */
	public void addDataShared(int t, float x, float y, float h) {
		DataShared dShared = new DataShared();
		dShared.setTypeData(TypeData.values()[t]);
		dShared.setPose(new Pose(x, y, h));
		
		this.shareds.add(dShared);
		dataListener.actionPerformed(null);
	}

	private void writeData(int code){
		 try {
			dataOut.writeInt(code);
			dataOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public CommunicationChannelPC getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(CommunicationChannelPC channel) {
		this.channel = channel;
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

}
