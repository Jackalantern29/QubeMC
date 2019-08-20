package com.Jackalantern29.QCRewards;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;

public class Stats {
	public static boolean sqlplayerExists(String uuid) {
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean sqlplayerExists(int id) {
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE ID= '" + id + "'");
			if (rs.next()) {
				return rs.getString("ID") != null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void sqlcreatePlayer(String uuid) {
		if (!(sqlplayerExists(uuid))) {
			QubeMC.plugin.getMySQL().update(
					"INSERT INTO Coins(UUID, COINS, LASTUSER) VALUES ('" + uuid
							+ "', '0', '"
							+ Bukkit.getPlayer(UUID.fromString(uuid)).getName()
							+ "');");
		}
	}

	public static void createStatFile(UUID uuid) {
		sqlcreatePlayer(uuid.toString());
	}

	public static void setStatus(UUID uuid, String value) {
		if (sqlplayerExists(uuid.toString())) {
			QubeMC.plugin.getMySQL().update(
					"UPDATE Coins SET STATUS= '" + value + "' WHERE UUID= '"
							+ uuid.toString() + "';");
		} else {
			sqlcreatePlayer(uuid.toString());
			setStatus(uuid, value);
		}
	}

	public static void setCoins(UUID uuid, int value) {
		if (sqlplayerExists(uuid.toString())) {
			QubeMC.plugin.getMySQL().update(
					"UPDATE Coins SET COINS= '" + value + "' WHERE UUID= '"
							+ uuid + "';");
		} else {
			sqlcreatePlayer(uuid.toString());
			setCoins(uuid, value);
		}
	}

	public static void setLastUsername(UUID uuid) {
		if (sqlplayerExists(uuid.toString())) {
			QubeMC.plugin.getMySQL().update(
					"UPDATE Coins SET LASTUSER= '"
							+ Bukkit.getPlayer(uuid).getName()
							+ "' WHERE UUID= '" + uuid + "';");
		} else {
			sqlcreatePlayer(uuid.toString());
			setLastUsername(uuid);
		}
	}

	public static String getStatus(UUID uuid) {
		String i = "";
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
			if ((!rs.next()) || (rs.getString("STATUS") == null))
				;
			i = rs.getString("STATUS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int getCoins(UUID uuid) {
		Integer i = 0;
		if (sqlplayerExists(uuid.toString())) {
			try {
				ResultSet rs = QubeMC.plugin.getMySQL().query(
						"SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
				if ((!rs.next())
						|| (Integer.valueOf(rs.getInt("COINS")) == null))
					;
				i = rs.getInt("COINS");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			sqlcreatePlayer(uuid.toString());
			getCoins(uuid);
		}
		return i;
	}

	public static int getOnlineTime(UUID uuid) {
		Integer i = 0;
		if (sqlplayerExists(uuid.toString())) {
			try {
				ResultSet rs = QubeMC.plugin.getMySQL().query(
						"SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
				if ((!rs.next())
						|| (Integer.valueOf(rs.getInt("ONLINETIME")) == null))
					;
				i = rs.getInt("ONLINETIME");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			sqlcreatePlayer(uuid.toString());
			getOnlineTime(uuid);
		}
		return i;
	}

	public static void setOnlineTime(UUID uuid, int value) {
		if (sqlplayerExists(uuid.toString())) {
			QubeMC.plugin.getMySQL().update(
					"UPDATE Coins SET ONLINETIME= '" + value
							+ "' WHERE UUID= '" + uuid + "';");
		} else {
			sqlcreatePlayer(uuid.toString());
			setOnlineTime(uuid, value);
		}
	}

	public static String getLastUsername(int id) {
		String i = "";
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE ID= '" + id + "'");
			if ((!rs.next()) || (rs.getString("LASTUSER") == null))
				;
			i = rs.getString("LASTUSER");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int getCoins(int id) {
		Integer i = 0;
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE ID= '" + id + "'");
			if ((!rs.next()) || (Integer.valueOf(rs.getInt("COINS")) == null))
				;
			i = rs.getInt("COINS");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static int getID(int id) {
		Integer i = 1;
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE ID= '" + id + "'");
			if ((!rs.next()) || (Integer.valueOf(rs.getInt("ID")) == null))
				;
			i = rs.getInt("ID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static UUID getUUID(int id) {
		UUID uuid = null;
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins WHERE ID= '" + id + "'");
			if ((!rs.next()) || (rs.getString("UUID") == null))
				;
			uuid = UUID.fromString(rs.getString("UUID"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uuid;
	}

	public static int getMAXID() {
		Integer i = 1;
		try {
			ResultSet rs = QubeMC.plugin.getMySQL().query(
					"SELECT * FROM Coins ORDER BY ID DESC LIMIT 1");
			if ((!rs.next()) || (Integer.valueOf(rs.getInt("ID")) == null))
				;
			i = rs.getInt("ID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static String getLastUsername(UUID uuid) {
		String i = "";
		if (sqlplayerExists(uuid.toString())) {
			try {
				ResultSet rs = QubeMC.plugin.getMySQL().query(
						"SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
				if ((!rs.next()) || (rs.getString("LASTUSER")) == null)
					;
				i = rs.getString("LASTUSER");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			sqlcreatePlayer(uuid.toString());
			getLastUsername(uuid);
		}
		return i;
	}

	public static int getSQLOnlineTime(UUID uuid) {
		Integer i = 0;
		if (sqlplayerExists(uuid.toString())) {
			try {
				ResultSet rs = QubeMC.plugin.getMySQL().query(
						"SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
				if ((!rs.next())
						|| (Integer.valueOf(rs.getInt("ONLINETIME")) == null))
					;
				i = rs.getInt("ONLINETIME");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			sqlcreatePlayer(uuid.toString());
			getOnlineTime(uuid);
		}
		return i;
	}

}