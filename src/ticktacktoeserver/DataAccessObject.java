package ticktacktoeserver;

import org.apache.derby.jdbc.ClientDriver;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class DataAccessObject {

    public static String insertPlayer(String[] playerInfo) throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String queryString = "INSERT INTO Players (USERNAME, FIRSTNAME, LASTNAME, PASSWORD, MALE, SCORE, PICTURE, AVAILABLE, LOGGEDOFF, INGAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pst = connection.prepareStatement(queryString);
        pst.setString(1, playerInfo[1]); // USERNAME
        pst.setString(2, playerInfo[2]); // FIRSTNAME
        pst.setString(3, playerInfo[3]); // LASTNAME
        pst.setString(4, playerInfo[4]); //PASSWORD
        System.out.println(playerInfo[5]);
        pst.setBoolean(5, (playerInfo[5].equalsIgnoreCase("true")? true : false)); //male
        pst.setInt(6, 0);// isMale 
        Blob imageBlob = connection.createBlob();
        imageBlob.setBytes(1, Base64.getDecoder().decode(playerInfo[7]));
        pst.setBlob(7, imageBlob);
        pst.setBoolean(8, true);
        pst.setBoolean(9, false);
        pst.setBoolean(10, false);
        try {
            int rs = pst.executeUpdate();
            pst.close();
            connection.close();
            return playerInfo[1] + ",signupstatus,true";
        } catch (SQLException sq) {
            pst.close();
            connection.close();
            return playerInfo[1] + ",signupstatus,false";
        }

    }

    public static String getPlayerData(String[] playerInfo) throws SQLException {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");

        String queryString = "SELECT * FROM PLAYERS WHERE USERNAME = ?";
        PreparedStatement pst = connection.prepareStatement(queryString);
        pst.setString(1, playerInfo[1]);

        ResultSet rs = pst.executeQuery();

        String playerData = "";
        if (rs.next()) {
            playerData = playerInfo[1] + ",";
            playerData += "getuserdata,";
            playerData += rs.getString("FIRSTNAME") + ",";

            playerData += rs.getString("LASTNAME") + ",";

            playerData += rs.getString("MALE") + ",";

            playerData += rs.getString("SCORE") + ",";
            Blob pictureBlob = rs.getBlob("PICTURE");
            if (pictureBlob != null) {
                byte[] bytes = pictureBlob.getBytes(1, (int) pictureBlob.length());
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                playerData += base64Image;
            } else {
                playerData += null;
            }
        }
        pst.close();
        return playerData;
    }

    public static String getAvailablePlayers() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String players = "";
        String queryString = "SELECT * FROM PLAYERS WHERE AVAILABLE = true";
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            players += rs.getString("USERNAME") + ",";
        }
        pst.close();
        connection.close();
        return players;
    }

    public static String getLoggedOffPlayers() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String players = "";
        String queryString = "SELECT * FROM PLAYERS WHERE LOGGEDOFF = true";
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            players += rs.getString("USERNAME") + ",";
        }
        pst.close();
        connection.close();
        return players;
    }

    public static String getInGamePlayers() throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String players = "";
        String queryString = "SELECT * FROM PLAYERS WHERE INGAME = true";
        PreparedStatement pst = connection.prepareStatement(queryString);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            players += rs.getString("USERNAME") + ",";
        }
        pst.close();
        connection.close();
        return players;
    }

    public static String login(String[] playerInfo) throws SQLException {
        DriverManager.registerDriver(new ClientDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TTTDB", "root", "root");
        String queryString = "SELECT * FROM PLAYERS WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement pst = connection.prepareStatement(queryString);

        pst.setString(1, playerInfo[1]);
        pst.setString(2, playerInfo[2]);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            pst.close();
            connection.close();
            return playerInfo[1]+",loginstatus,true";
        }
        pst.close();
        connection.close();
        return playerInfo[1]+",loginstatus,false";
    }

}
