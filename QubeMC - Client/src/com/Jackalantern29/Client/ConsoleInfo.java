package com.Jackalantern29.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ConsoleInfo implements Runnable {
	JFrame frame;
	JMenuBar menubar;
	JScrollPane scrollpane;
	JTextField cmdField;
	JLabel label;
	Timer timer = new Timer();
	private static String server = "Skyblock";
	private static boolean status = false;
	
	static List<String> list = new ArrayList<>();
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ConsoleInfo());
	}

	private void createWindow() {
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
		frame = new JFrame("QubeMC - Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
	}
	
	private void createMenu() {
		menubar = new JMenuBar();
		JMenu servers = new JMenu("Servers");
		
		/*final JMenuItem wll = new JMenuItem("Enable WhiteList");
		final JMenuItem wlw = new JMenuItem("Disable WhiteList");

		JMenuItem ks = new JMenuItem("Kill server");
		JMenuItem ex = new JMenuItem("Exit");
		JMenuItem exs = new JMenuItem("Safe exit");*/
		
		ButtonGroup bg = new ButtonGroup();
		List<JRadioButtonMenuItem> rbmi = new ArrayList<JRadioButtonMenuItem>();
		List<String> list = new ArrayList<>();
		list.add("Factions");
		list.add("Skyblock");
		list.add("GTA");
		list.add("Lobby");
		list.add("Creative");
		list.add("Development");
		list.add("Towny");
		for (String string : list) {
			JRadioButtonMenuItem jrbm = new JRadioButtonMenuItem(string);
			jrbm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ConsoleInfo.server = string; 
					//label.setText("<html>");
					label.setText(label.getText() + "\n<p> </p>");
					label.setText(label.getText() + "\n<p><b>Switching to " + string + "</b></p>");
					label.setText(label.getText() + "\n<p>  </p>");
					ConsoleInfo.list.clear();
					timer = new Timer();
					timer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							loadConsole(server);
						}
					}, 1000 * 5, 10);
				}
			});
			bg.add(jrbm);
			rbmi.add(jrbm);
		}
		for(JRadioButtonMenuItem jrbmi : rbmi)
			servers.add(jrbmi);
		menubar.add(servers);
		frame.setJMenuBar(menubar);
	}
	
	private void createScrollBar() {
		label = new JLabel();
		scrollpane = new JScrollPane(label);
		label.setText("<html>");
		frame.getContentPane().add(scrollpane);
		scrollpane.getVerticalScrollBar().setUnitIncrement(20);
		scrollpane.getHorizontalScrollBar().setUnitIncrement(20);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
	    scrollpane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent event) {
				if(!event.getValueIsAdjusting())
					if(status) {
						scrollpane.getVerticalScrollBar().setValue(scrollpane.getVerticalScrollBar().getMaximum());
						status = false;
					}
			}
		});
	}
	
	private void createCommandTextBox() {
		cmdField = new JTextField();
		cmdField.setForeground(new Color(127, 127, 127));
		cmdField.setBackground(Color.LIGHT_GRAY);
		cmdField.setText("Enter command...");
		cmdField.setFont(new Font(cmdField.getFont().getFontName(), Font.ITALIC, cmdField.getFont().getSize()));
		frame.getContentPane().add(cmdField, BorderLayout.SOUTH);
		
		cmdField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (cmdField.getForeground().getRGB() == new Color(127, 127, 127)
						.getRGB()) {
					cmdField.setText("");
					cmdField.setForeground(new Color(0, 0, 0));
				}
				cmdField.setFont(cmdField.getFont());
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (cmdField.getText().equalsIgnoreCase("")) {
					cmdField.setForeground(new Color(127, 127, 127));
					cmdField.setText("Enter command...");
					cmdField.setFont(new Font(cmdField.getFont().getFontName(), Font.ITALIC, cmdField.getFont().getSize()));
				}
			}
		});
		cmdField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
			}

			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String text = cmdField.getText();
					list.add(text);
					label.setText(label.getText() + "\n<p>><u>" + text + "</u></p>");
					cmdField.setText("");
					//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), komenda);
				}
			}

			public void keyTyped(KeyEvent arg0) {
			}
		});
	}
	
	private void loadConsole(String server) {
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
				}
			}
			inStream.close();
		} catch (IOException e) {
			System.err.println("Exception: " + e);
		}
		if(check == true) {
			for(int i = 0; i < list.size(); i++) {
		    	if(i > list.size() - 100) {
		    		if(!label.getText().contains("\n<p><b>[" + server + "]</b> " + list.get(i))) {
		    			if(server == ConsoleInfo.server) {
				    		label.setText(label.getText() + "\n<p><b>[" + server  + "]</b> " + list.get(i) + "</p>");
				    		status = true;
		    			}
			    	}
		    	}
			}	
	    }
	}
	@Override
	public void run() {
	    createWindow();
		createMenu();
		createScrollBar();
		createCommandTextBox();
		frame.setTitle("Loading...");
		loadConsole(server);//1000 milliseconds is one second.
		try {
		    Thread.sleep(1000 * 5);  
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		frame.setTitle("QubeMC - Client");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				loadConsole(server);
			}
		}, 0,  10);
	}
	
}
