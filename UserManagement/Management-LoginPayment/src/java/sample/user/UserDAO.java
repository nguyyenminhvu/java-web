/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, roleID,status FROM tblUsers WHERE userID=? AND password=? AND status=1";
    private static final String SEARCH = "SELECT userID, fullName, roleID,status FROM tblUsers WHERE fullName like ? ";
    private static final String SELECT_ID = "SELECT userID, fullName, roleID, password, status FROM tblUsers WHERE userID = ? ";
    private static final String DELETE = "DELETE tblUsers WHERE userId=?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName=?,roleID=? WHERE userID=? ";
    private static final String CHECK_EXISTED = "SELECT userId, status from tblUsers WHERE userID=?";
    private static final String INSERT = "INSERT INTO tblUsers values(?,?,?,?,1) ";
    private static final String GET_ALL = "SELECT userID, fullName, roleID,status from tblUsers ";
    private static final String UPDATE_STATUS = "UPDATE tblUsers SET status=? WHERE userID=?";
    private static final String TEST = "SELECT TOP 2 userID FROM tblUsers";

    public List<UserDTO> testGetTop2() throws ClassNotFoundException {
        List<UserDTO> listUser = new ArrayList<>();
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(TEST);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String userID = rs.getString("userID");
                String fullName = "******";
                String roleID = "******";
                String password = "******";
                listUser.add(new UserDTO(userID, fullName, roleID, password));
            }
        } catch (SQLException e) {
            System.out.println("Error at testTop2: " + e.toString());
        }
        return listUser;
    }
//
//    public static void main(String[] args) throws ClassNotFoundException {
//        UserDAO dao = new UserDAO();
//        List<UserDTO> userL = dao.testGetTop2();
//        System.out.println(userL.size());
//    }

    public UserDTO checkLogin(String userID, String password) throws SQLException, ClassNotFoundException, NamingException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(LOGIN);
            ptm.setString(1, userID);
            ptm.setString(2, password);
            rs = ptm.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                boolean status = rs.getBoolean("status");
                if (status) {
                    user = new UserDTO(userID, fullName, roleID, "***");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean checkExisted(String userId) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(CHECK_EXISTED);
            ptm.setString(1, userId);
            rs = ptm.executeQuery();
            if (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (status) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH);
            ptm.setString(1, "%" + search + "%");
            rs = ptm.executeQuery();
            while (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (status) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    listUser.add(new UserDTO(userID, fullName, roleID, password));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return listUser;
    }

    public List<UserDTO> getListUserInactive(String search) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH);
            ptm.setString(1, "%" + search + "%");
            rs = ptm.executeQuery();
            while (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (!status) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    listUser.add(new UserDTO(userID, fullName, roleID, password));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return listUser;
    }

    public boolean delete(String userID) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                checkDelete = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkDelete;
    }

    public UserDTO getUserById(String id) throws ClassNotFoundException {
        String sql = SELECT_ID;
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (status) {
                    UserDTO user = new UserDTO(id, rs.getString("fullName"), rs.getString("roleID"), "******");
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error at getUserById: " + e.toString());
        }
        return null;
    }

    public List<UserDTO> getAll() throws ClassNotFoundException {
        String sql = GET_ALL;
        List<UserDTO> UserDTOList = new ArrayList<>();
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (status) {
                    UserDTO user = new UserDTO(rs.getString("userid"), rs.getString("fullName"), rs.getString("roleID"), "******");
                    UserDTOList.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error at getUserById: " + e.toString());
        }
        return UserDTOList;
    }

    public boolean update(UserDTO user) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getUserID());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();;
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkUpdate;
    }

    public boolean insert(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getRoleID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (ptm != null) {
                ptm.close();;
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    @SuppressWarnings("empty-statement")
    public boolean insertv2(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getRoleID());
                check = ptm.executeUpdate() > 0;
            }
        } finally {
            if (ptm != null) {
                ptm.close();;
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateStatus(String userId, boolean status) throws ClassNotFoundException {
        boolean check = false;
        boolean statusV2 = false;
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(UPDATE_STATUS);
            if (status) {
                statusV2 = false;
            } else {
                statusV2 = true;
            }
            st.setBoolean(1, statusV2);
            st.setString(2, userId);
            check = st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error at updateStatus: " + e.toString());
        }
        return check;
    }

    public List<UserDTO> getAllUserUnActive() throws ClassNotFoundException {
        List<UserDTO> userList = new ArrayList<>();
        try {
            PreparedStatement st = DBUtils.getConnection().prepareStatement(GET_ALL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                boolean status = rs.getBoolean("status");
                if (!status) {
                    UserDTO user = new UserDTO(rs.getString("userid"), rs.getString("fullName"), rs.getString("roleID"), "******");
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error at getAllUserUnActive: " + e.toString());
        }
        return userList;
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException, NamingException {
//        UserDAO user = new UserDAO();
//        String userEmail = "vu";
////        UserDTO u = new UserDTO("admin", "Siuuuu", "US", "123");
////        System.out.println(user.getAll().size());
////        boolean checkExist = user.checkExisted(userEmail);
////        while (checkExist) {
////            String code = "123456789";
////            String result = null;
////            for (int i = 0; i < 1; i++) {
////                int index = (int) (code.length() * Math.random());
////                result = code.substring(index, index + 1);
////            }
////            userEmail += result;
////            checkExist = user.checkExisted(userEmail);
////        }
////        System.out.println(userEmail);
//        // System.out.println(user.checkLogin("vu", "vu"));
//        //  System.out.println(user.updateStatus("vu", true));
//        System.out.println(user.getAllUserUnActive().get(0).getFullName());
//    }

}
