package data;

import exceptions.ConnectionException;
import exceptions.ConnectionException.CreateCustomerException;
import exceptions.ConnectionException.DataAccessException;
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
    //Throws Query Exception if the query is not executable. 
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public void createCustomer(String email, String password, String fName, String lName, String phone, String adress, String zipCode) throws CreateCustomerException, QueryException {
        String sql = "INSERT INTO users (email, password, fName, lName, phone, adress, zipCode, role, creationDate)"
                + " VALUES (?,?,?,?,?,?,?,0, CURDATE())";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
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
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }//CreateCustomer

    //Checks the database for the email the user tries to login with
    //Throws Login Error exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public String getEmail(String email) throws LoginError, QueryException {
        String userEmail = "NotFound";
        String sql = "SELECT email FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        userEmail = email;
                    } else {
                        throw new LoginError();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return userEmail;
    }//getEmail
    
    //Checks the password for the desired email
    //Throws Login Error exception if the password is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public String getPassword(String email) throws LoginError, QueryException {
        String password = "NotFound";
        String sql = "SELECT password FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        password = rs.getString("password");
                    } else {
                        throw new LoginError();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
            
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return password;
    }//getPassword
    
    //Searches for the role of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public int getRole(String email) throws QueryException, DataAccessException {
        int role = 0;
        String sql = "SELECT role FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        role = rs.getInt("role");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return role;
    }//getRole
    
    //Searches for the account id of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public int getAccountID(String email) throws QueryException, DataAccessException {
        int id = 0;
        String sql = "SELCT account_id FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        id = rs.getInt("account_id");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return id;
    }//getAccountID

    //Searches for the zip code of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public int getZipCode(String email) throws QueryException, DataAccessException {
        int zipCode = 0;
        String sql = "SELECT zipCode FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        zipCode = rs.getInt("zipCode");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return zipCode;
    }//getZipCode

    //Searches for the first name of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public String getFirstName(String email) throws QueryException, DataAccessException {
        String fName = null;
        String sql = "SELECT fName FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        fName = rs.getString("fName");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return fName;
    }//getFirstName

    //Searches for the last name of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public String getLastName(String email) throws QueryException, DataAccessException {
        String lName = null;
        String sql = "SELECT lName FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        lName = rs.getString("fName");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return lName;
    }//getLastName

    //Searches for the phone of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public String getPhone(String email) throws QueryException, DataAccessException  {
        String phone = null;
        String sql = "SELECT phone FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        phone = rs.getString("phone");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return phone;
    }//getPhone

    //Searches for the address of the user by email
    //Throws Data Access exception if the email is not found
    //Throws Create Customer Exception if the input is not the right data type or the querry is wrong
    public String getAdress(String email) throws QueryException, DataAccessException {
        String adress = null;
        String sql = "SELECT adress FROM users WHERE email = " + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                ResultSet rs = stmt.executeQuery();
                try {
                    if (rs.next()) {
                        adress = rs.getString("adress");
                    } else {
                        throw new DataAccessException();
                    }
                } finally {
                    //Close the resultset
                    if (rs!=null) {rs.close();}
                }
            }  finally {
                //Close the statement
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e)  {
            throw new QueryException();
        }
        return adress;
    }//getAddress

    //Updates the email from the update details form
    //Throws UpdateUserInfoException if the update fails
    //Throws Query Exception if there is something wrong with the query
    public void updateEmail(String email, int acc_id) throws UpdateUserInfoException, QueryException {
        String sql = "UPDATE users SET email = '" + email + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new UpdateUserInfoException();
            } finally {
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }//updateEmail

    //Updates the password from the update details form
    //Throws UpdateUserInfoException if the update fails
    //Throws Query Exception if there is something wrong with the query
    public void updatePassword(String password, int acc_id) throws UpdateUserInfoException, QueryException {
        int i = 0;
        String sql = "UPDATE users SET password = '" + password + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new UpdateUserInfoException();
            } finally {
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }//updatePassword

    //Updates the address from the update details form
    //Throws UpdateUserInfoException if the update fails
    //Throws Query Exception if there is something wrong with the query
    public void updateAdress(String adress, int acc_id) throws UpdateUserInfoException, QueryException {
        String sql = "UPDATE users SET adress = '" + adress + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new UpdateUserInfoException();
            } finally {
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }

    //Updates the phone from the update details form
    //Throws UpdateUserInfoException if the update fails
    //Throws Query Exception if there is something wrong with the query
    public void updatePhone(String phone, int acc_id) throws UpdateUserInfoException, QueryException {
        String sql = "UPDATE users SET phone = '" + phone + "' WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new UpdateUserInfoException();
            } finally {
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }

    //Updates the zip zode from the update details form
    //Throws UpdateUserInfoException if the update fails
    //Throws Query Exception if there is something wrong with the query
    public void updateZipcode(int zipCode, int acc_id) throws UpdateUserInfoException, QueryException {
        String sql = "UPDATE users SET zipCode = " + zipCode + " WHERE account_id = " + acc_id + "";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            try {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new UpdateUserInfoException();
            } finally {
                if (stmt!=null) {stmt.close();}
            }
        } catch (SQLException e) {
            throw new QueryException();
        }
    }//updateZipcode
}
