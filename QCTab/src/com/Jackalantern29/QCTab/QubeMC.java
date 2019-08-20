package com.Jackalantern29.QCTab;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

@SuppressWarnings("deprecation")
public class QubeMC extends JavaPlugin implements PluginMessageListener, Listener {
	int count = 0;
	Player pp;
	public String format(String message) {
		return message.replace("&", "§")
				.replace("{playercount}", Bukkit.getOnlinePlayers().size() + "")
				.replace("{bungeecount}", count + "")
				.replace("{ping}", Packet.pingPlayer(pp) + "");
	}
	public void onEnable() {
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		for(Player p : Bukkit.getOnlinePlayers()) {
			Scoreboard scoreboard = p.getScoreboard();

			
			Team dark_aqua = null;
			Team dark_red = null;
			Team dark_purple = null;
			Team blue = null;
			Team black = null;
			Team dark_blue = null;
			Team green = null;
			Team gold = null;
			Team light_gray = null;
			Team dark_gray = null;
			Team light_green = null;
			Team aqua = null;
			Team red = null;
			Team light_purple = null;
			Team yellow = null;
			Team white = null;
			Team noColor = null;					
			
			for(Team teams : scoreboard.getTeams()) {
				teams.setAllowFriendlyFire(true);
				teams.setCanSeeFriendlyInvisibles(false);
				
			}
			
			p.setScoreboard(scoreboard);
			
			for(Player player : Bukkit.getOnlinePlayers()) {
				int count = 0;
				for(Object list : getConfig().getList("Colors")) {
					count++;
					String letter = "P";
					String string = String.valueOf(list);
					if(count == 1)
						letter = "A";
					if(count == 2)
						letter = "B";
					if(count == 3)
						letter = "C";
					if(count == 4)
						letter = "D";
					if(count == 5)
						letter = "E";
					if(count == 6)
						letter = "F";
					if(count == 7)
						letter = "G";
					if(count == 8)
						letter = "H";
					if(count == 9)
						letter = "I";
					if(count == 10)
						letter = "J";
					if(count == 11)
						letter = "K";
					if(count == 12)
						letter = "L";
					if(count == 13)
						letter = "M";
					if(count == 14)
						letter = "N";
					if(count == 15)
						letter = "O";
					if(count >= 16)
						letter = "P";
					String name = String.valueOf(letter + string + player.getUniqueId()).substring(0, 16);
					if(scoreboard.getTeam(name) == null) {
						if(string.equals("Black")) {
							black = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Dark_Blue")) {
							dark_blue = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Green")) {
							green = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Dark_Red")) {
							dark_red = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Dark_Aqua")) {
							dark_aqua = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Dark_Purple")) {
							dark_purple = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Gold")) {
							gold = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Light_Gray")) {
							light_gray = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Dark_Gray")) {
							dark_gray = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Blue")) {
							blue = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Light_Green")) {
							light_green = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Aqua")) {
							aqua = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Red")) {
							red = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Light_Purple")) {
							light_purple = scoreboard.registerNewTeam(name);
						}
						if(string.equals("Yellow")) {
							yellow = scoreboard.registerNewTeam(name);
						}
						if(string.equals("White")) {
							white = scoreboard.registerNewTeam(name);
						}
					} else {
						if(string.equals("Black")) {
							black = scoreboard.getTeam(name);
						}
						if(string.equals("Dark_Blue")) {
							dark_blue = scoreboard.getTeam(name);
						}
						if(string.equals("Green")) {
							green = scoreboard.getTeam(name);
						}
						if(string.equals("Dark_Red")) {
							dark_red = scoreboard.getTeam(name);
						}
						if(string.equals("Dark_Aqua")) {
							dark_aqua = scoreboard.getTeam(name);
						}
						if(string.equals("Dark_Purple")) {
							dark_purple = scoreboard.getTeam(name);
						}
						if(string.equals("Gold")) {
							gold = scoreboard.getTeam(name);
						}
						if(string.equals("Light_Gray")) {
							light_gray = scoreboard.getTeam(name);
						}
						if(string.equals("Dark_Gray")) {
							dark_gray = scoreboard.getTeam(name);
						}
						if(string.equals("Blue")) {
							blue = scoreboard.getTeam(name);
						}
						if(string.equals("Light_Green")) {
							light_green = scoreboard.getTeam(name);
						}
						if(string.equals("Aqua")) {
							aqua = scoreboard.getTeam(name);
						}
						if(string.equals("Red")) {
							red = scoreboard.getTeam(name);
						}
						if(string.equals("Light_Purple")) {
							light_purple = scoreboard.getTeam(name);
						}
						if(string.equals("Yellow")) {
							yellow = scoreboard.getTeam(name);
						}
						if(string.equals("White")) {
							white = scoreboard.getTeam(name);
						}
					}	
					
					if(scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16)) == null) {
						noColor = scoreboard.registerNewTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
					}
					else {
						noColor = scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
					}
				}
				black.setPrefix("§0");
				dark_blue.setPrefix("§1");
				green.setPrefix("§2");
				dark_aqua.setPrefix("§3");
				dark_red.setPrefix("§4");
				dark_purple.setPrefix("§5");
				gold.setPrefix("§6");
				light_gray.setPrefix("§7");
				dark_gray.setPrefix("§8");
				blue.setPrefix("§9");
				light_green.setPrefix("§a");
				aqua.setPrefix("§b");
				red.setPrefix("§c");
				light_purple.setPrefix("§d");
				yellow.setPrefix("§e");
				white.setPrefix("§f");
				noColor.setPrefix("§f");
				if(player.hasPermission("tab.color.darkaqua")) {
					dark_aqua.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						dark_aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						dark_aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.darkred")) {
					dark_red.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						dark_red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						dark_red.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.darkpurple")) {
					dark_purple.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						dark_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						dark_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.blue")) {
					blue.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.darkblue")) {
					dark_blue.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						dark_blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						dark_blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.green")) {
					green.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						green.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.black")) {
					black.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						black.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						black.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.gold")) {
					gold.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						gold.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						gold.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.lightgray")) {
					light_gray.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						light_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						light_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.darkgray")) {
					dark_gray.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						dark_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						dark_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.lightgreen")) {
					light_green.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						light_green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						light_green.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.aqua")) {
					aqua.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
					aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.red")) {
					red.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						red.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.lightpurple")) {
					light_purple.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						light_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						light_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.yellow")) {
					yellow.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						yellow.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						yellow.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else if(player.hasPermission("tab.color.white")) {
					white.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						white.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						white.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
				else {
					noColor.addEntry(player.getName());
					if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
						noColor.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
					} else {
						noColor.setNameTagVisibility(NameTagVisibility.ALWAYS);
					}
				}
			}
		}
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("PlayerCount");
					out.writeUTF("ALL");
					
					p.sendPluginMessage(QubeMC.this, "BungeeCord", out.toByteArray());
					pp = p;
					Packet.sendTabHeaderFooter(p, format(getConfig().getString("Header")), format(getConfig().getString("Footer")));
				}
			}
		}, 0, 20);
	}
	
	public void onDisable() {
		
	}
	@Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		if (!channel.equals("BungeeCord")) {
			return;
	    }
		ByteArrayDataInput in = ByteStreams.newDataInput(message);
		String subchannel = in.readUTF();
		if (subchannel.equals("PlayerCount")) {
			String server = in.readUTF(); // Name of server, as given in the arguments
	    	int playercount = in.readInt();
	    	if(server.equals("ALL"))
	    		count = Integer.valueOf(String.valueOf(server + ";" + playercount).split(";")[1]);
	    }
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Scoreboard scoreboard = p.getScoreboard();

					
					Team dark_aqua = null;
					Team dark_red = null;
					Team dark_purple = null;
					Team blue = null;
					Team black = null;
					Team dark_blue = null;
					Team green = null;
					Team gold = null;
					Team light_gray = null;
					Team dark_gray = null;
					Team light_green = null;
					Team aqua = null;
					Team red = null;
					Team light_purple = null;
					Team yellow = null;
					Team white = null;
					Team noColor = null;					
					
					for(Team teams : scoreboard.getTeams()) {
						teams.setAllowFriendlyFire(true);
						teams.setCanSeeFriendlyInvisibles(false);
						
					}
					
					p.setScoreboard(scoreboard);
					
					for(Player player : Bukkit.getOnlinePlayers()) {
						int count = 0;
						for(Object list : getConfig().getList("Colors")) {
							count++;
							String letter = "P";
							String string = String.valueOf(list);
							if(count == 1)
								letter = "A";
							if(count == 2)
								letter = "B";
							if(count == 3)
								letter = "C";
							if(count == 4)
								letter = "D";
							if(count == 5)
								letter = "E";
							if(count == 6)
								letter = "F";
							if(count == 7)
								letter = "G";
							if(count == 8)
								letter = "H";
							if(count == 9)
								letter = "I";
							if(count == 10)
								letter = "J";
							if(count == 11)
								letter = "K";
							if(count == 12)
								letter = "L";
							if(count == 13)
								letter = "M";
							if(count == 14)
								letter = "N";
							if(count == 15)
								letter = "O";
							if(count >= 16)
								letter = "P";
							String name = String.valueOf(letter + string + player.getUniqueId()).substring(0, 16);
							if(scoreboard.getTeam(name) == null) {
								if(string.equals("Black")) {
									black = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Blue")) {
									dark_blue = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Green")) {
									green = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Red")) {
									dark_red = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Aqua")) {
									dark_aqua = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Purple")) {
									dark_purple = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Gold")) {
									gold = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Gray")) {
									light_gray = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Gray")) {
									dark_gray = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Blue")) {
									blue = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Green")) {
									light_green = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Aqua")) {
									aqua = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Red")) {
									red = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Purple")) {
									light_purple = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Yellow")) {
									yellow = scoreboard.registerNewTeam(name);
								}
								if(string.equals("White")) {
									white = scoreboard.registerNewTeam(name);
								}
							} else {
								if(string.equals("Black")) {
									black = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Blue")) {
									dark_blue = scoreboard.getTeam(name);
								}
								if(string.equals("Green")) {
									green = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Red")) {
									dark_red = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Aqua")) {
									dark_aqua = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Purple")) {
									dark_purple = scoreboard.getTeam(name);
								}
								if(string.equals("Gold")) {
									gold = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Gray")) {
									light_gray = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Gray")) {
									dark_gray = scoreboard.getTeam(name);
								}
								if(string.equals("Blue")) {
									blue = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Green")) {
									light_green = scoreboard.getTeam(name);
								}
								if(string.equals("Aqua")) {
									aqua = scoreboard.getTeam(name);
								}
								if(string.equals("Red")) {
									red = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Purple")) {
									light_purple = scoreboard.getTeam(name);
								}
								if(string.equals("Yellow")) {
									yellow = scoreboard.getTeam(name);
								}
								if(string.equals("White")) {
									white = scoreboard.getTeam(name);
								}
							}	
							
							if(scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16)) == null) {
								noColor = scoreboard.registerNewTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
							}
							else {
								noColor = scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
							}
						}
						black.setPrefix("§0");
						dark_blue.setPrefix("§1");
						green.setPrefix("§2");
						dark_aqua.setPrefix("§3");
						dark_red.setPrefix("§4");
						dark_purple.setPrefix("§5");
						gold.setPrefix("§6");
						light_gray.setPrefix("§7");
						dark_gray.setPrefix("§8");
						blue.setPrefix("§9");
						light_green.setPrefix("§a");
						aqua.setPrefix("§b");
						red.setPrefix("§c");
						light_purple.setPrefix("§d");
						yellow.setPrefix("§e");
						white.setPrefix("§f");
						noColor.setPrefix("§f");
						if(player.hasPermission("tab.color.darkaqua")) {
							dark_aqua.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkred")) {
							dark_red.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_red.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkpurple")) {
							dark_purple.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.blue")) {
							blue.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkblue")) {
							dark_blue.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.green")) {
							green.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								green.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.black")) {
							black.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								black.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								black.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.gold")) {
							gold.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								gold.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								gold.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightgray")) {
							light_gray.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkgray")) {
							dark_gray.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightgreen")) {
							light_green.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_green.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.aqua")) {
							aqua.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
							aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.red")) {
							red.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								red.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightpurple")) {
							light_purple.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.yellow")) {
							yellow.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								yellow.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								yellow.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.white")) {
							white.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								white.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								white.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else {
							noColor.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								noColor.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								noColor.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
					}
				}
			}
		}, 20);
	}
	public void onLeave(PlayerQuitEvent event) {
Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Scoreboard scoreboard = p.getScoreboard();

					
					Team dark_aqua = null;
					Team dark_red = null;
					Team dark_purple = null;
					Team blue = null;
					Team black = null;
					Team dark_blue = null;
					Team green = null;
					Team gold = null;
					Team light_gray = null;
					Team dark_gray = null;
					Team light_green = null;
					Team aqua = null;
					Team red = null;
					Team light_purple = null;
					Team yellow = null;
					Team white = null;
					Team noColor = null;					
					
					for(Team teams : scoreboard.getTeams()) {
						teams.setAllowFriendlyFire(true);
						teams.setCanSeeFriendlyInvisibles(false);
						
					}
					
					p.setScoreboard(scoreboard);
					
					for(Player player : Bukkit.getOnlinePlayers()) {
						int count = 0;
						for(Object list : getConfig().getList("Colors")) {
							count++;
							String letter = "P";
							String string = String.valueOf(list);
							if(count == 1)
								letter = "A";
							if(count == 2)
								letter = "B";
							if(count == 3)
								letter = "C";
							if(count == 4)
								letter = "D";
							if(count == 5)
								letter = "E";
							if(count == 6)
								letter = "F";
							if(count == 7)
								letter = "G";
							if(count == 8)
								letter = "H";
							if(count == 9)
								letter = "I";
							if(count == 10)
								letter = "J";
							if(count == 11)
								letter = "K";
							if(count == 12)
								letter = "L";
							if(count == 13)
								letter = "M";
							if(count == 14)
								letter = "N";
							if(count == 15)
								letter = "O";
							if(count >= 16)
								letter = "P";
							String name = String.valueOf(letter + string + player.getUniqueId()).substring(0, 16);
							if(scoreboard.getTeam(name) == null) {
								if(string.equals("Black")) {
									black = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Blue")) {
									dark_blue = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Green")) {
									green = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Red")) {
									dark_red = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Aqua")) {
									dark_aqua = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Purple")) {
									dark_purple = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Gold")) {
									gold = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Gray")) {
									light_gray = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Gray")) {
									dark_gray = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Blue")) {
									blue = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Green")) {
									light_green = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Aqua")) {
									aqua = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Red")) {
									red = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Purple")) {
									light_purple = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Yellow")) {
									yellow = scoreboard.registerNewTeam(name);
								}
								if(string.equals("White")) {
									white = scoreboard.registerNewTeam(name);
								}
							} else {
								if(string.equals("Black")) {
									black = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Blue")) {
									dark_blue = scoreboard.getTeam(name);
								}
								if(string.equals("Green")) {
									green = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Red")) {
									dark_red = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Aqua")) {
									dark_aqua = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Purple")) {
									dark_purple = scoreboard.getTeam(name);
								}
								if(string.equals("Gold")) {
									gold = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Gray")) {
									light_gray = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Gray")) {
									dark_gray = scoreboard.getTeam(name);
								}
								if(string.equals("Blue")) {
									blue = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Green")) {
									light_green = scoreboard.getTeam(name);
								}
								if(string.equals("Aqua")) {
									aqua = scoreboard.getTeam(name);
								}
								if(string.equals("Red")) {
									red = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Purple")) {
									light_purple = scoreboard.getTeam(name);
								}
								if(string.equals("Yellow")) {
									yellow = scoreboard.getTeam(name);
								}
								if(string.equals("White")) {
									white = scoreboard.getTeam(name);
								}
							}	
							
							if(scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16)) == null) {
								noColor = scoreboard.registerNewTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
							}
							else {
								noColor = scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
							}
						}
						black.setPrefix("§0");
						dark_blue.setPrefix("§1");
						green.setPrefix("§2");
						dark_aqua.setPrefix("§3");
						dark_red.setPrefix("§4");
						dark_purple.setPrefix("§5");
						gold.setPrefix("§6");
						light_gray.setPrefix("§7");
						dark_gray.setPrefix("§8");
						blue.setPrefix("§9");
						light_green.setPrefix("§a");
						aqua.setPrefix("§b");
						red.setPrefix("§c");
						light_purple.setPrefix("§d");
						yellow.setPrefix("§e");
						white.setPrefix("§f");
						noColor.setPrefix("§f");
						if(player.hasPermission("tab.color.darkaqua")) {
							dark_aqua.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkred")) {
							dark_red.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_red.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkpurple")) {
							dark_purple.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.blue")) {
							blue.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkblue")) {
							dark_blue.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.green")) {
							green.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								green.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.black")) {
							black.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								black.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								black.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.gold")) {
							gold.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								gold.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								gold.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightgray")) {
							light_gray.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkgray")) {
							dark_gray.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightgreen")) {
							light_green.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_green.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.aqua")) {
							aqua.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
							aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.red")) {
							red.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								red.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightpurple")) {
							light_purple.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.yellow")) {
							yellow.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								yellow.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								yellow.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.white")) {
							white.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								white.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								white.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else {
							noColor.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								noColor.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								noColor.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
					}
				}
			}
		}, 20);
	}
	public void onKick(PlayerKickEvent event) {
Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Scoreboard scoreboard = p.getScoreboard();

					
					Team dark_aqua = null;
					Team dark_red = null;
					Team dark_purple = null;
					Team blue = null;
					Team black = null;
					Team dark_blue = null;
					Team green = null;
					Team gold = null;
					Team light_gray = null;
					Team dark_gray = null;
					Team light_green = null;
					Team aqua = null;
					Team red = null;
					Team light_purple = null;
					Team yellow = null;
					Team white = null;
					Team noColor = null;					
					
					for(Team teams : scoreboard.getTeams()) {
						teams.setAllowFriendlyFire(true);
						teams.setCanSeeFriendlyInvisibles(false);
						
					}
					
					p.setScoreboard(scoreboard);
					
					for(Player player : Bukkit.getOnlinePlayers()) {
						int count = 0;
						for(Object list : getConfig().getList("Colors")) {
							count++;
							String letter = "P";
							String string = String.valueOf(list);
							if(count == 1)
								letter = "A";
							if(count == 2)
								letter = "B";
							if(count == 3)
								letter = "C";
							if(count == 4)
								letter = "D";
							if(count == 5)
								letter = "E";
							if(count == 6)
								letter = "F";
							if(count == 7)
								letter = "G";
							if(count == 8)
								letter = "H";
							if(count == 9)
								letter = "I";
							if(count == 10)
								letter = "J";
							if(count == 11)
								letter = "K";
							if(count == 12)
								letter = "L";
							if(count == 13)
								letter = "M";
							if(count == 14)
								letter = "N";
							if(count == 15)
								letter = "O";
							if(count >= 16)
								letter = "P";
							String name = String.valueOf(letter + string + player.getUniqueId()).substring(0, 16);
							if(scoreboard.getTeam(name) == null) {
								if(string.equals("Black")) {
									black = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Blue")) {
									dark_blue = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Green")) {
									green = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Red")) {
									dark_red = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Aqua")) {
									dark_aqua = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Purple")) {
									dark_purple = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Gold")) {
									gold = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Gray")) {
									light_gray = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Dark_Gray")) {
									dark_gray = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Blue")) {
									blue = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Green")) {
									light_green = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Aqua")) {
									aqua = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Red")) {
									red = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Light_Purple")) {
									light_purple = scoreboard.registerNewTeam(name);
								}
								if(string.equals("Yellow")) {
									yellow = scoreboard.registerNewTeam(name);
								}
								if(string.equals("White")) {
									white = scoreboard.registerNewTeam(name);
								}
							} else {
								if(string.equals("Black")) {
									black = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Blue")) {
									dark_blue = scoreboard.getTeam(name);
								}
								if(string.equals("Green")) {
									green = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Red")) {
									dark_red = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Aqua")) {
									dark_aqua = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Purple")) {
									dark_purple = scoreboard.getTeam(name);
								}
								if(string.equals("Gold")) {
									gold = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Gray")) {
									light_gray = scoreboard.getTeam(name);
								}
								if(string.equals("Dark_Gray")) {
									dark_gray = scoreboard.getTeam(name);
								}
								if(string.equals("Blue")) {
									blue = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Green")) {
									light_green = scoreboard.getTeam(name);
								}
								if(string.equals("Aqua")) {
									aqua = scoreboard.getTeam(name);
								}
								if(string.equals("Red")) {
									red = scoreboard.getTeam(name);
								}
								if(string.equals("Light_Purple")) {
									light_purple = scoreboard.getTeam(name);
								}
								if(string.equals("Yellow")) {
									yellow = scoreboard.getTeam(name);
								}
								if(string.equals("White")) {
									white = scoreboard.getTeam(name);
								}
							}	
							
							if(scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16)) == null) {
								noColor = scoreboard.registerNewTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
							}
							else {
								noColor = scoreboard.getTeam(String.valueOf("QNoColor" + player.getUniqueId().toString()).substring(0, 16));
							}
						}
						black.setPrefix("§0");
						dark_blue.setPrefix("§1");
						green.setPrefix("§2");
						dark_aqua.setPrefix("§3");
						dark_red.setPrefix("§4");
						dark_purple.setPrefix("§5");
						gold.setPrefix("§6");
						light_gray.setPrefix("§7");
						dark_gray.setPrefix("§8");
						blue.setPrefix("§9");
						light_green.setPrefix("§a");
						aqua.setPrefix("§b");
						red.setPrefix("§c");
						light_purple.setPrefix("§d");
						yellow.setPrefix("§e");
						white.setPrefix("§f");
						noColor.setPrefix("§f");
						if(player.hasPermission("tab.color.darkaqua")) {
							dark_aqua.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkred")) {
							dark_red.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_red.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkpurple")) {
							dark_purple.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.blue")) {
							blue.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkblue")) {
							dark_blue.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_blue.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_blue.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.green")) {
							green.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								green.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.black")) {
							black.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								black.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								black.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.gold")) {
							gold.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								gold.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								gold.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightgray")) {
							light_gray.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.darkgray")) {
							dark_gray.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								dark_gray.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								dark_gray.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightgreen")) {
							light_green.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_green.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_green.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.aqua")) {
							aqua.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								aqua.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
							aqua.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.red")) {
							red.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								red.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								red.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.lightpurple")) {
							light_purple.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								light_purple.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								light_purple.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.yellow")) {
							yellow.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								yellow.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								yellow.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else if(player.hasPermission("tab.color.white")) {
							white.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								white.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								white.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
						else {
							noColor.addEntry(player.getName());
							if(player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
								noColor.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
							} else {
								noColor.setNameTagVisibility(NameTagVisibility.ALWAYS);
							}
						}
					}
				}
			}
		}, 20);
	}
}
