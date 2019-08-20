package com.Jackalantern29.QCLogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class QubeMC extends JavaPlugin {
	public static List<String> list = new ArrayList<>();
	
	public void onLoad() {
		QCFileLoader.createFiles();
	}
	public void onEnable() {
		miniSBRun();
		miniFactionRun();
		miniGTARun();
		miniTownyRun();
		miniCreativeRun();
		miniLobbyRun();
		miniDevelopmentRun();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				miniSBRun();
				miniFactionRun();
				miniGTARun();
				miniTownyRun();
				miniCreativeRun();
				miniLobbyRun();
				miniDevelopmentRun();
			}
		}, 0, 20 * 1);
	}
	public void miniDevelopmentRun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - Development/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - Development/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}	

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/Development", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void miniSBRun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - Skyblock/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - Skyblock/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}	

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/Skyblock", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void miniFactionRun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - Factions/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - Factions/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}	

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/Factions", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void miniGTARun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - GTA/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - GTA/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}	

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/GTA", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void miniTownyRun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - Towny/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - Towny/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}	

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/Towny", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void miniCreativeRun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - Creative/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - Creative/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/Creative", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void miniLobbyRun() {
		int lines = 0;
		try {
			LineNumberReader  lnr = new LineNumberReader(new FileReader(new File("../QubeMC - Lobby/logs", "latest.log")));
			lnr.skip(Long.MAX_VALUE);
			lines = lnr.getLineNumber();
			lnr.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String text = "";
		try {
			FileInputStream fstream = new FileInputStream(new File("../QubeMC - Lobby/logs", "latest.log"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int i = 0;
			String time = "";
			while (((strLine = br.readLine()) != null)) {
				i++;
				try {
					if(strLine.substring(0, 1).equals("["))
						time = strLine.substring(0, 10);
				} catch(StringIndexOutOfBoundsException e) {
					
				}
				if(lines < 100) {
		    		text = text + "\n" + time + " " + strLine;
				} else if(i >= lines - 100) {
					text = text + "\n" + time + " " + strLine;
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream foutput = new FileOutputStream(new File("../QubeMC - Data/QCLogger/Servers/Lobby", "log.txt"));
    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(foutput));
    		writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}



