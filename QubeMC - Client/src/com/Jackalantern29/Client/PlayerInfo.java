package com.Jackalantern29.Client;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PlayerInfo {

	private JFrame frame;
	JTextPane tp = new JTextPane();
    JScrollPane sp = new JScrollPane(tp);
	
	private static List<String> list = new ArrayList<>();
	private static boolean status = false;
	private static String player;
	private static String ip;
	private static String port;
	boolean connectingStatus = false;
	boolean isConnected = false;
	
	static boolean isLoaded = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerInfo window = new PlayerInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlayerInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		
	    frame.getContentPane().add(sp);
	    tp.setEditable(false);
	    tp.setFont(tp.getFont().deriveFont(16F));
	    frame.setTitle("QubeMC - Client");
	    frame.setVisible(true);
	    miniRun();
	    isLoaded = true;
	    Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				miniRun();
			}
		}, 0, 1000 * 1);
	    sp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent event) {
				if(!event.getValueIsAdjusting())
					if(status) {
						sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
						status = false;
					}
			}
		});
	    try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public void miniRun() {
		boolean check = false;
	    try {
			FileInputStream fstream = new FileInputStream("C:" + File.separator + "Users" + File.separator + "Michael" + File.separator + "OneDrive" + File.separator + "Minecraft" + File.separator + "Minecraft" + File.separator + "logs" + File.separator + "latest.log");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			boolean b = true;
			while ((strLine = br.readLine()) != null) {
				b = true;
				if(strLine.contains("] [Client thread/FATAL]: Error executing task"))
					b = false;
				if(strLine.equals("Caused by: java.lang.NullPointerException"))
					b = false;
				if(strLine.equals("java.util.concurrent.ExecutionException: java.lang.NullPointerException"))
					b = false;
				if(strLine.contains("at java.util.concurrent.FutureTask.report(FutureTask.java:122) ~[?:1.8.0_25]"))
					b = false;
				if(strLine.contains("at java.util.concurrent.FutureTask.get(FutureTask.java:192) ~[?:1.8.0_25]"))
					b = false;
				if(strLine.contains("at h.a(SourceFile:46) [1.9.4.jar:?]"))
					b = false;
				if(strLine.contains("at bcd.av(SourceFile:961) [1.9.4.jar:?]"))
					b = false;
				if(strLine.contains("at bcd.a(SourceFile:399) [1.9.4.jar:?]"))
					b = false;
				if(strLine.contains("at net.minecraft.client.main.Main.main(SourceFile:124) [1.9.4.jar:?]"))
					b = false;
				if(strLine.contains("[Client thread/INFO]: Setting user: ")) {
					b = false;
					player = strLine.substring(47);
				}
				if(strLine.contains(" [Client thread/INFO]: Connecting to ")) {
					String server = strLine.substring(47);
					ip = server.split(", ")[0];
					port = server.split(", ")[1];
					connectingStatus = true;
				}
				if(strLine.contains(" [Server Connector #2/ERROR]: Couldn't connect to server")) {
					if(connectingStatus) {
						connectingStatus = false;
						ip = "";
						port = "";
					}
				}
				if(b) {
					if(!list.contains(strLine)) {
						//String time = strLine.substring(10);
						if(strLine.contains("[CHAT]")) {
							try {
								if(strLine.split(":")[4].contains(" ##GOTO WEB ")) {
									if(isLoaded) {
										if(Desktop.isDesktopSupported()) {
											String url = strLine.split(":")[4].replace(" ##GOTO WEB ", "");
											list.add(url);
											Desktop.getDesktop().browse(new URI("http://www." + url));
											Toolkit.getDefaultToolkit().beep();
										}
									}
								} else if(strLine.split(":")[4].contains(" ##OPEN ")) {
									String application = strLine.split(":")[4].replace(" ##OPEN ", "");
									if(strLine.toLowerCase().contains(" remote_desktop")) {
										application = "MSTSC";
									}
									if(isLoaded) {
										try {
											Runtime runTime = Runtime.getRuntime();
											Process process = runTime.exec(application.replace("_", ""));
											Toolkit.getDefaultToolkit().beep();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
								} else if(strLine.split(":")[4].contains(" ##PLAYSOUND BEEP")) {
									if(isLoaded) {
										Toolkit.getDefaultToolkit().beep();
									}
								}
							} catch(ArrayIndexOutOfBoundsException e) {
								
							}
							if(strLine.contains(("hey " + player)) || strLine.contains(player + "?") || strLine.contains(player + " you there") || strLine.contains("ping " + player)) {
									if(isLoaded) {
										Toolkit.getDefaultToolkit().beep();
										frame.setFocusableWindowState(true);
									}
								}
						}
						
						list.add(strLine);
						check = true;
					}
				}
			}			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    if(check == true) {
		    for(int i = 0; i < list.size(); i++) {
		    	if(i > list.size() - 100) {
		    		if(!tp.getText().contains("\n" + list.get(i))) {
			    		tp.setText(tp.getText() + "\n" + list.get(i));
			    		status = true;
			    	}
		    	}
			}	
	    }
	}

}
