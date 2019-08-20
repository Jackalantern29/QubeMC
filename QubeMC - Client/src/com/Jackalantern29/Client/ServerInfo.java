package com.Jackalantern29.Client;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ServerInfo {

	private JFrame frame;
	JTextPane tp = new JTextPane();
	JScrollPane sp = new JScrollPane(tp);
	
	private static List<String> list = new ArrayList<>();
	boolean status = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerInfo window = new ServerInfo();
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
	public ServerInfo() {
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
				if (!event.getValueIsAdjusting())
					sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
			}
		});

	}

	public void miniRun() {
		URL url;
		URLConnection connection;
		String urlString = "";
		//String matchString = "name";
		String inputLine;
		boolean check = false;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			System.err.println("Exception: " + e);
			return;
		}
		try {
			connection = url.openConnection();
			BufferedReader inStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while (null != (inputLine = inStream.readLine())) {
				if(!list.contains(inputLine)) {
					list.add(inputLine);
					check = true;
					tp.setText(tp.getText() + "\n" + inputLine);
				}
			}
			inStream.close();
		} catch (IOException e) {
			System.err.println("Exception: " + e);
		}
		if(check == true) {
		    for(int i = 0; i < list.size(); i++) {
		    	tp.setText(tp.getText() + "\n" + list.get(i));
	    		status = true;
			}	
	    }
	}

}
