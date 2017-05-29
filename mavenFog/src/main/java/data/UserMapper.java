package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.CreateSalesRepException;
import exceptions.ConnectionException.GetAllUsersException;
import exceptions.ConnectionException.LoginError;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateUserInfoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.User;

public class UserMapper {

    private static Connection con;

    //Creates a connection to DB
    public static void setConnection() throws ConnectionException {
        con = DB.createConnection(); 
    }

    public static Connection getCon() {
        return con;
    }

    //Validates the user`s input
    //Throws Login error if the email or password is not in the databse or the password doesn`t match
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static void validateLoginDetails(String email, String password) throws LoginError, QueryException {
        String passwordDB = "NotFound";
        String sqlEmail = "SELECT email FROM users WHERE email = '" + email + "'";
        String sqlPass = "SELECT password FROM users WHERE email = '" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sqlEmail);
            rs = stmt.executeQuery();
            //if email does not exist in the DB throw Login Error
            if (!rs.next()) {
                throw new LoginError();
            }
            stmt = con.prepareStatement(sqlPass);
            rs = stmt.executeQuery();
            //gets the password for the current email or throw Login Error
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
        if (!password.equals(passwordDB)) {
            throw new LoginError();
        }
    }//validateLoginDetails
    
    //Takes input from the register form and creates new Customer in the Database. 
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public static void createCustomer(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws CreateCustomerException {
        String sql = "INSERT INTO users (account_id, email, password, first_name, last_name, phone_number, address, zip_code, role, creation_date, user_status)"
                + " VALUES (?,?,?,?,?,?,?,?,0, CURDATE(), 1)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, DB.generateID("users", "account_id", con));
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, fName);
            stmt.setString(5, lName);
            stmt.setInt(6, Integer.parseInt(phone));
            stmt.setString(7, adress);
            stmt.setInt(8, Integer.parseInt(zipCode));
            stmt.executeUpdate();
        } catch (SQLException | QueryException e) {
            throw new CreateCustomerException();
        } finally {
            DB.closeStmt(stmt);
        }

    }//CreateCustomer
    
    //Takes input from the admin form and creates new Salesep in the Database. 
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public static void createSalesRep(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws CreateSalesRepException {
        String sql = "INSERT INTO users (account_id, email, password, first_name, last_name, phone_number, address, zip_code, role, creation_date, user_status)"
                + " VALUES (?,?,?,?,?,?,?,?,1, CURDATE(), 1)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, DB.generateID("users", "account_id", con));
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, fName);
            stmt.setString(5, lName);
            stmt.setInt(6, Integer.parseInt(phone));
            stmt.setString(7, adress);
            stmt.setInt(8, Integer.parseInt(zipCode));
            stmt.executeUpdate();
        } catch (SQLException | QueryException e) {
            throw new CreateSalesRepException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//createSalesRep
    
    //Deletes a user from the Database
    //Used for the testing 
    public static void deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = '" + email + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStmt(stmt);
        }
    }//deleteUser

    //Returns an ArrayList with all the users in the Database
    //Throws GetAllUsers Exception if the method is not executable or the list is empty
    public static ArrayList<User> getAllUsers() throws GetAllUsersException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        String accountID, email, fName, lName, address;
        int zipCode, phone, role, userStatus;
        User user;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                accountID = rs.getString("account_id");
                email = rs.getString("email");
                fName = rs.getString("first_name");
                lName = rs.getString("last_name");
                address = rs.getString("address");
                zipCode = rs.getInt("zip_code");
                phone = rs.getInt("phone_number");
                role = rs.getInt("role");
                userStatus = rs.getInt("user_status");
                user = new User(email, fName, lName, address, zipCode, phone, role, accountID, userStatus);
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
    
    //Creates a user object to be returned
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static User getUser(String email) throws QueryException {
        User user = null;
        String accountID, fName, lName, address;
        int phone, zipCode, role, userStatus;
        Date date;
        String sql = "SELECT * FROM users WHERE email = '" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                accountID = rs.getString("account_id");
                fName = rs.getString("first_name");
                lName = rs.getString("last_name");
                address = rs.getString("address");
                phone = rs.getInt("phone_number");
                zipCode = rs.getInt("zip_code");
                role = rs.getInt("role");
                date = rs.getDate("creation_date");
                userStatus = rs.getInt("user_status");
                user = new User(email, fName, lName, address, zipCode, phone, role, accountID, userStatus);
            }
        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return user;
    }
    
    //Creates a user object to be returned
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public static User getUserByID(String accountID) throws QueryException {
        User user = null;
        String email,fName, lName, address;
        int phone, zipCode, role, userStatus;
        String sql = "SELECT * FROM users WHERE account_id = '" + accountID + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
                fName = rs.getString("first_name");
                lName = rs.getString("last_name");
                address = rs.getString("address");
                phone = rs.getInt("phone_number");
                zipCode = rs.getInt("zip_code");
                role = rs.getInt("role");
                userStatus = rs.getInt("user_status");
                user = new User(email, fName, lName, address, zipCode, phone, role, accountID, userStatus);
            }
        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return user;
    }
    
    //Updates the email from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updateEmail(String email, String accountID) throws UpdateUserInfoException {
        String sql = "UPDATE users SET email = '" + email + "' WHERE account_id = '" + accountID + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateEmail

    //Updates the password from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updatePassword(String password, String accountID) throws UpdateUserInfoException {
        String sql = "UPDATE users SET password = '" + password + "' WHERE account_id = '" + accountID + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updatePassword

    //Updates the address from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updateAdress(String adress, String accountID) throws UpdateUserInfoException {
        String sql = "UPDATE users SET address = '" + adress + "' WHERE account_id = '" + accountID + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }

    //Updates the phone from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updatePhone(String phone, String accountID) throws UpdateUserInfoException {
        String sql = "UPDATE users SET phone_number = '" + Integer.parseInt(phone) + "' WHERE account_id = '" + accountID + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }

    //Updates the zip zode from the update details form
    //Throws UpdateUserInfoException if the update fails
    public static void updateZipcode(String zipCode, String accountID) throws UpdateUserInfoException {
        String sql = "UPDATE users SET zip_code = " + Integer.parseInt(zipCode) + " WHERE account_id = '" + accountID + "';";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//updateZipcode
      
    //Updates an /!\admin/!\ from the Database
    public static void updateUserStatus (int status, String accountID) throws UpdateUserInfoException {
        String sql = "UPDATE users SET user_status = " + status + " WHERE account_id = '" + accountID + "'; ";
        String set = "SET SQL_SAFE_UPDATES = 0;";
        String reset = "SET SQL_SAFE_UPDATES = 1;";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(set);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            stmt = con.prepareStatement(reset);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateUserInfoException();
        } finally {
            DB.closeStmt(stmt);
        }
    }//delete
}//UserMapper
