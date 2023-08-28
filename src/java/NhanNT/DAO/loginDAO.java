package NhanNT.DAO;

import NhanNT.DB.DBUtils;
import NhanNT.model.UsersDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class loginDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT * "
                        + "FROM tblUsers "
                        + "WHERE userName = ? AND passWord = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    private List<UsersDTO> listAccounts;

    public List<UsersDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchFullname(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT *\n"
                        + "FROM tblUsers \n"
                        + "WHERE fullName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("userName");
                    String password = rs.getString("passWord");
                    String fullname = rs.getString("fullName");
                    boolean role = rs.getBoolean("isAdmin");

                    UsersDTO dto = new UsersDTO(username, password, fullname, role);

                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<UsersDTO>();
                    }
                    this.listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public static UsersDTO getUsersByName(String name) throws SQLException {
        UsersDTO e = new UsersDTO();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblUsers WHERE userName =? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    e.setUsername(rs.getString(1));
                    e.setPassword(rs.getString(2));
                    e.setFullname(rs.getString(3));
                    e.setRole(rs.getBoolean(4));
                }

            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return e;
    }

    public boolean deleteRecord(String username) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "DELETE FROM tblUsers WHERE userName = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String username, String password, String fullname, boolean role) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                String sql = "UPDATE tblUsers"
                        + " SET passWord=?,fullName=?,isAdmin=?"
                        + " WHERE userName= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, fullname);
                stm.setBoolean(3, role);
                stm.setString(4, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    public boolean insertRecord(String username, String password, String fullname, boolean role )throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBUtils.makeConnection();
            if(con!= null){
                String sql ="INSERT INTO tblUsers(userName, passWord, fullName, isAdmin ) VALUES(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if(row >0){
                    return true;
                }
            }
            
        }finally{
           if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
        }
    }
return false;
}
}
