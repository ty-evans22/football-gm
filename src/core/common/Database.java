package core.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that handles all interactions with SQL Database.
 * @author Tyler Evans
 */
public class Database {
	
	private String dbName;
	
	public Database(String dbName) {
		this.dbName = dbName;
		
		Connection conn = connect();
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		createPlayerTable();
		createCollegeTable();
		createConferenceTable();
		createOffersTable();
	}
	
	private Connection connect() {
		
		String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/" + dbName;
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	private void createOffersTable() {
		String[] columnNames = {"id", "player_id", "college_id"};
		String[] columnTypes = {"integer", "integer", "integer"};
		String[] columnModifiers = {"PRIMARY KEY", "REFERENCES players(id)", "REFERENCES colleges(id)"};
		
		createNewTable("offers", columnNames, columnTypes, columnModifiers);
	}
	
	private void createConferenceTable() {
		String[] columnNames = {"id", "conference_name"};
		String[] columnTypes = {"integer", "text"};
		String[] columnModifiers = {"PRIMARY KEY", "NOT NULL"};
		
		createNewTable("conferences", columnNames, columnTypes, columnModifiers);
	}
	
	private void createCollegeTable() {
		String[] columnNames = {"id", "college_name", "conference_id"};
		String[] columnTypes = {"integer", "text", "integer"};
		String[] columnModifiers = {"PRIMARY KEY", "NOT NULL", "REFERENCES conferences(id)"};
		
		createNewTable("colleges", columnNames, columnTypes, columnModifiers);
		
		String sql = "INSERT INTO colleges(id,college_name) VALUES(?,?)";
		Connection conn = connect();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, 0);
			pstmt.setString(2, "No College");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void createPlayerTable() {
		String[] columnNames = {"id", "first_name", "last_name", "birth_date", "college_id", "position", "eligibility"};
		String[] columnTypes = {"integer", "text", "text", "text", "integer", "text", "text"};
		String[] columnModifiers = {"PRIMARY KEY", "NOT NULL", "NOT NULL", "NOT NULL", "REFERENCES colleges(id)", "NOT NULL", ""};
		
		createNewTable("players", columnNames, columnTypes, columnModifiers);
	}
	
	public void createNewTable(String tableName, String[] columnNames, String[] columnTypes, String[] columnModifiers) {
		
		String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n";
        
        for (int i = 0; i < columnNames.length; i++) {
        	sql += " " + columnNames[i] + " ";
        	sql += columnTypes[i] + " ";
        	sql += columnModifiers[i];
        	
        	if (i != columnNames.length - 1) {
        		sql += ",";
        	}
        	
        	sql += "\n";
        }
        
        sql += ");";
        
        try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public int addPlayer(Player p, int collegeID) {
		String firstName = p.getFirstName();
		String lastName = p.getLastName();
		String birthday = p.getBirthday().toDatabaseFormat();
		String position = p.getPosition().toStringShorthand();
		String eligibility;
		if (p.getEligibility() != null) {
			eligibility = p.getEligibility().toString();
		} else {
			eligibility = "";
		}
		
		String sql = "INSERT INTO players(first_name,last_name,birth_date,college_id,position,eligibility) VALUES(?,?,?,?,?,?)";
		int result = -1;
		
		Connection conn = connect();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, birthday);
			pstmt.setInt(4, collegeID);
			pstmt.setString(5, position);
			pstmt.setString(6, eligibility);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		sql = "SELECT last_insert_rowid()";
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result = rs.getInt("last_insert_rowid()");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public int addCollege(College c, int conferenceID) {
		String collegeName = c.toString();
		
		String sql = "INSERT INTO colleges(college_name,conference_id) VALUES(?,?)";
		int result = -1;
		
		Connection conn = connect();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, collegeName);
			pstmt.setInt(2, conferenceID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		sql = "SELECT last_insert_rowid()";
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result = rs.getInt("last_insert_rowid()");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public int addConference(Conference c) {
		String conferenceName = c.name;
		
		String sql = "INSERT INTO conferences(conference_name) VALUES(?)";
		int result = -1;
		
		Connection conn = connect();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, conferenceName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		sql = "SELECT last_insert_rowid()";
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result = rs.getInt("last_insert_rowid()");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public void removePlayer(Player p) {
		int playerID = p.playerID;
		
		String sql = "DELETE FROM players WHERE id = ?";
		
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, playerID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updatePlayerEligibility(Player p) {
		int playerID = p.playerID;
		String eligibility = p.getEligibility().toString();
		
		String sql = "UPDATE players SET eligibility = ? WHERE id = ?";
		
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, eligibility);
			pstmt.setInt(2, playerID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updatePlayerCollege(Player p, College c) {
		int playerID = p.playerID;
		int collegeID = c.collegeID;
		
		String sql = "UPDATE players SET college_id = ? WHERE id = ?";
		
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, collegeID);
			pstmt.setInt(2, playerID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int addOffer(Player p, College c) {
		int playerID = p.playerID;
		int collegeID = c.collegeID;
		
		String sql = "INSERT INTO offers(player_id,college_id) VALUES(?,?)";
		int result = -1;
		
		Connection conn = connect();
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, playerID);
			pstmt.setInt(2, collegeID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		sql = "SELECT last_insert_rowid()";
		
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				result = rs.getInt("last_insert_rowid()");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
