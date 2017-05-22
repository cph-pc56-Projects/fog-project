package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.LoginError;
import exceptions.ConnectionException.QueryException;
import exceptions.ConnectionException.UpdateUserInfoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UserMapper {
    
    private final Connection con;
    private final DB db;

    public UserMapper() throws ConnectionException {
        db = new DB();
        con = db.createConnection();
    }

    public DB getDb() {
        return db;
    }

    public Connection getCon() {
        return con;
    }
    
    //Takes input from the register form and creates new Customer in the Database. 
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public void createCustomer(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws CreateCustomerException  {
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

    //Checks the database for the email the user tries to login with
    //Throws Login Error exception if the email is not found
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public String getEmail(String email) throws LoginError, QueryException {
        String userEmail = "NotFound";
        String sql = "SELECT email FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                userEmail = email;
            } else {
                throw new LoginError();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return userEmail;
    }//getEmail
    
    //Checks the password for the desired email
    //Throws Login Error exception if the password is not found
    //Throws Query Exception if the input is not the right data type or the querry is wrong
    public String getPassword(String email) throws LoginError, QueryException {
        String password = "NotFound";
        String sql = "SELECT password FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            } else {
                throw new LoginError();
            }

        } catch (SQLException e) {
            throw new QueryException();
        } finally {
            DB.closeRs(rs);
            DB.closeStmt(stmt);
        }
        return password;
    }//getPassword
    
    //Searches for the role of the user by email
    //Throws QueryException if the input is not the right data type or the querry is wrong
    public int getRole(String email) throws QueryException {
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
    public int getAccountID(String email) throws QueryException {
        int id = 0;
        String sql = "SELCT account_id FROM users WHERE email = " + "'" + email + "'";
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
    public int getZipCode(String email) throws QueryException {
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
    public String getFirstName(String email) throws QueryException {
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
    public String getLastName(String email) throws QueryException {
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
    public String getPhone(String email) throws QueryException {
        String phone = null;
        String sql = "SELECT phone_number FROM users WHERE email = " + "'" + email + "'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                phone = rs.getString("phone_number");
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
    public String getAdress(String email) throws QueryException {
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
    public void updateEmail(String email, int acc_id) throws UpdateUserInfoException {
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
    public void updatePassword(String password, int acc_id) throws UpdateUserInfoException {
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
    public void updateAdress(String adress, int acc_id) throws UpdateUserInfoException {
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
    public void updatePhone(String phone, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET phone_number = '" + phone + "' WHERE account_id = " + acc_id + "";
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
    public void updateZipcode(int zipCode, int acc_id) throws UpdateUserInfoException {
        String sql = "UPDATE users SET zip_code = " + zipCode + " WHERE account_id = " + acc_id + "";
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
}//UserMapper
