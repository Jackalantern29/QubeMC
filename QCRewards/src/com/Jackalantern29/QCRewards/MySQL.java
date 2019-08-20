package com.Jackalantern29.QCRewards;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQL {

    private String HOST = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";

    private Connection con;

    public MySQL(String host, String database, String user, String password) {
        this.HOST = host;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;

        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/"
                    + DATABASE, USER, PASSWORD);
            System.out.println("[MySQL] The connection to MySQL is made!");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " [] Error Code: " + e.getErrorCode() + " [] SQLState: " + e.getSQLState());
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out
                        .println("[MySQL] The connection to MySQL is ended successfully!");
            }
        } catch (SQLException e) {
            System.out.println("[MySQL] The connection couldn't be closed! reason: ");
            System.out.println(e.getMessage() + " [] Error Code: " + e.getErrorCode() + " [] SQLState: " + e.getSQLState());
        }
    }

    public void update(String qry) {
        try {
            PreparedStatement st = con.prepareStatement(qry);
            st.execute();
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public boolean hasConnection() {
        return con != null;
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            PreparedStatement st = con.prepareStatement(qry);
            rs = st.executeQuery();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }
    
    
    
	public ArrayList<HashMap<String,String>> raw_query(String Full_Command) {
	  try {
	    Statement Stm = null;
	    Stm = con.createStatement();

	    ResultSet Result = null;
	    boolean Returning_Rows = Stm.execute(Full_Command);
	    if (Returning_Rows)
	      Result = Stm.getResultSet();
	    else
	      return new ArrayList<HashMap<String,String>>();

	    ResultSetMetaData Meta = null;
	    Meta = Result.getMetaData();
	    int Col_Count = Meta.getColumnCount();
	    ArrayList<String> Cols = new ArrayList<String>();
	    for (int Index=1; Index<=Col_Count; Index++)
	      Cols.add(Meta.getColumnName(Index));

	    ArrayList<HashMap<String,String>> Rows = 
	    new ArrayList<HashMap<String,String>>();

	    while (Result.next()) {
	      HashMap<String,String> Row = new HashMap<String,String>();
	      for (String Col_Name:Cols) {
	        String Val = Result.getString(Col_Name);
	        Row.put(Col_Name,Val);
	      }
	      Rows.add(Row);
	    }

	    Stm.close();

	    return Rows;
	  }
	  catch (Exception Ex) {
	    System.out.print(Ex.getMessage());
	    return new ArrayList<HashMap<String,String>>();
	  }
	}
}
