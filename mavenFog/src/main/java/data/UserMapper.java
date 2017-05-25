package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.GetAllUsersException;
import exceptions.ConnectionException.LoginError;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateUserInfoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.User;

public class UserMapper {

    private static Connection con;

    public static void setConnection() throws ConnectionException {
        con = DB.createConnection(); 
    }

    public static Connection getCon() {
        return con;
    }

    //Validates the user`s input
    //Throws Login error if the email or password is not in the databse or the password doesn`t match
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static void validateLoginDetails(String email, String Password) throws LoginError, QueryException {
        String passwordDB = "NotFound";
        String sqlEmail = "SELECT email FROM users WHERE email = '" + email + "'";
        String sqlPass = "SELECT password FROM users WHERE email = '" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sqlEmail);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new LoginError();
            }
            stmt = con.prepareStatement(sqlPass);
            rs = stmt.executeQuery();
            if (rs.next()) {
                passwordDB = rs.getString("password");
            } else {
                throw new LoginError();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        if (!passwordDB.equals(passwordDB)) {
            throw new LoginError();
        }
    }//validateLoginDetails
    
    //Takes input from the register form and creates new Customer in the Database. 
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public static void createCustomer(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws CreateCustomerException {
        String sql = "INSERT INTO users (email, password, first_name, last_name, phone_number, address, zip_code, role, creation_date)"
                + " VALUES (?,?,?,?,?,?,?,0, CURDATE())";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, fName);
            stmt.setString(4, lName);
            stmt.setInt(5, Integer.parseInt(phone));
            stmt.setString(6, adress);
            stmt.setInt(7, Integer.parseInt(zipCode));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CreateCustomerException();
        } finally {
            DB.closeStmt(stmt);
        }

    }//CreateCustomer
    
    //Takes input from the admin form and creates new Salesep in the Database. 
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public static void createSalesRep(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws CreateCustomerException {
        String sql = "INSERT INTO users (email, password, first_name, last_name, phone_number, address, zip_code, role, creation_date)"
                + " VALUES (?,?,?,?,?,?,?,1, CURDATE())";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, fName);
            stmt.setString(4, lName);
            stmt.setInt(5, Integer.parseInt(phone));
            stmt.setString(6, adress);
            stmt.setInt(7, Integer.parseInt(zipCode));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CreateCustomerException();
        } finally {
            DB.closeStmt(stmt);
        }

    }//createSalesRep

    //Returns an ArrayList with all the users in the Database
    //Throws GetAllUsers Exception if the method is not executable or the list is empty

    public static ArrayList<User> getAllUsers() throws GetAllUsersException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        String email, fName, lName, address;
        int zipCode, phone, role, accountID;
        User user;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                fName = rs.getString("first_name");
                lName = rs.getString("last_name");
                address = rs.getString("address");
                zipCode = rs.getInt("zip_code");
                phone = rs.getInt("phone_bumber");
                role = rs.getInt("role");
                accountID = rs.getInt("account_id");
                user = new User(email, fName, lName, address, zipCode, phone, role, accountID);
                users.add(user);
            }
        } catch (SQLException x) {
            x.printStackTrace();
            throw new GetAllUsersException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        if (users.isEmpty()) {
            throw new GetAllUsersException();
        }
        return users;
    }//getAllUsers

    //Searches for the email of the user by account_id
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static String getEmail(int accountID) throws QueryException {
        String email = null;
        String sql = "SELECT email FROM users WHERE account_id = " + accountID + "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return email;
    }//getEmail
    
    //Searches for the password of the user by account_id
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static String getPassword(int accountID) throws QueryException {
        String pass = null;
        String sql = "SELECT password FROM users WHERE account_id = " + accountID + "";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                pass = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return pass;
    }//getPassword

    //Searches for the role of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static int getRole(String email) throws QueryException {
        int role = 0;
        String sql = "SELECT role FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getInt("role");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return role;
    }//getRole

    //Searches for the account id of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static int getAccountID(String email) throws QueryException {
        int id = 0;
        String sql = "SELECT account_id FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("account_id");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return id;
    }//getAccountID

    //Searches for the zip code of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static int getZipCode(String email) throws QueryException {
        int zipCode = 0;
        String sql = "SELECT zip_code FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                zipCode = rs.getInt("zip_code");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return zipCode;
    }//getZipCode

    //Searches for the first name of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static String getFirstName(String email) throws QueryException {
        String fName = null;
        String sql = "SELECT first_name FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                fName = rs.getString("first_name");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return fName;
    }//getFirstName

    //Searches for the last name of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static String getLastName(String email) throws QueryException {
        String lName = null;
        String sql = "SELECT last_name FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                lName = rs.getString("last_name");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return lName;
    }//getLastName

    //Searches for the phone of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static int getPhone(String email) throws QueryException {
        int phone = 0;
        String sql = "SELECT phone_number FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                phone = rs.getInt("phone_number");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return phone;
    }//getPhone

    //Searches for the address of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static String getAdress(String email) throws QueryException {
        String adress = null;
        String sql = "SELECT address FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                adress = rs.getString("address");
            } else {
                throw new QueryException();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return adress;
    }//getAddress

    //Updates the email from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updateEmail(String email, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET email = '" + email + "' WHERE account_id = " + acc_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateEmail

    //Updates the password from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updatePassword(String password, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET password = '" + password + "' WHERE account_id = " + acc_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updatePassword

    //Updates the address from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updateAdress(String adress, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET address = '" + adress + "' WHERE account_id = " + acc_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }

    //Updates the phone from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updatePhone(String phone, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET phone_number = '" + Integer.parseInt(phone) + "' WHERE account_id = " + acc_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }

    //Updates the zip zode from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updateZipcode(String zipCode, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET zip_code = " + Integer.parseInt(zipCode) + " WHERE account_id = " + acc_id + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateZipcode
    
    //Deletes an /!\admin/!\ from the Database
    public static void deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = '" + email + "'";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStmt(stmt);
        }
    }//delete
    
    //Deletes an /!\admin/!\ from the Database
    public static boolean deleteSalesRep (int accountID) {
        boolean deleted = true;
        String sqlDelete = "DELETE FROM users WHERE account_id = " + accountID + "";
        String sqlUpdate = "UPDATE FROM order_details WHERE sales_rep_id = " + accountID + "";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sqlUpdate);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sqlDelete);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStmt(stmt);
        }
        return deleted;
    }//delete
}//UserMapper
