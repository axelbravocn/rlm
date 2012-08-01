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

import org.reaction.rlm.pc.data.DataShared;
import org.reaction.rlm.pc.data.TypeData;

/**
 * @author Flavio Souza
 * 
 */
public class CommunicationChannel extends Thread {
	private static CommunicationChannel channel;

	private final static String ADDRESS_NXT = "00:16:53:0F:1C:97";
	private final static String NAME_NXT = "NXT";

	private boolean isConnected;

	private NXTConnector conn;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;

	private List<DataShared> shareds;

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
		this.shareds = new ArrayList<DataShared>();
	}

	/**
	 * @throws IOException
	 * 
	 */
	public NXTConnector connectNXT() throws IOException {
		/*
		this.nxtInfo = new NXTInfo();
		this.nxtInfo.deviceAddress = ADDRESS_NXT;

		this.comm = new NXTCommBluecove();

		try {
			this.comm.open(nxtInfo);
		} catch (NXTCommException e) {
			e.printStackTrace();
		}

		this.dataIn = this.comm.getInputStream();
		this.dataOut = this.comm.getOutputStream();
	*/
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
			try {
				float x = 0;
				float y = 0;
				int t = -1;
				
				t = this.dataIn.readInt();
				System.out.println("t="+t);
				
				TypeData type = TypeData.values()[1];
				
				if(type == TypeData.COLLISION){
					x = this.dataIn.readFloat();
					y = this.dataIn.readFloat();
					System.out.println("COLLISION x="+x+" y="+y);
				}else if (type == TypeData.FREEDOM) {
					x = this.dataIn.readFloat();
					y = this.dataIn.readFloat();
					System.out.println("FREEDOM x="+x+" y="+y);
				}else if (type == TypeData.OBSTACLE) {
					x = this.dataIn.readFloat();
					y = this.dataIn.readFloat();
					System.out.println("OBSTACLE x="+x+" y="+y);
				}
				
				Thread.sleep(50);
				
			} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("ERROR");
			} catch (IOException e) {
				System.out.println(e);
				System.exit(0);
			} catch (InterruptedException e) {
				System.out.println(e);
				System.exit(0);
			}
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
