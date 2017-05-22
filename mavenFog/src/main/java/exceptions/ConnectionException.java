package exceptions;

import java.sql.SQLException;

//Thrown by the DB class if the connection cannot be established
public class ConnectionException extends SQLException {
    
    //Thrown by the Prepared Statements 
    public static class QueryException extends ConnectionException {
    
    }
    
    //Thrown by the createCustomer method in User Mapper 
    public static class CreateCustomerException extends ConnectionException {
    
    }
    
    //Thrown by the getEmail and getPassword in User Mapper method when logging in
    public static class LoginError extends ConnectionException {
    
    }
    
    //Thrown by all Data Access methods
    public static class DataAccessException extends ConnectionException {
    
    }
    
    //Thrown by the Update Details methods in User Mapper
    public static class UpdateUserInfoException extends ConnectionException {
    
    }
    
    //Thrown by the CreateOrder method in Order Mapper
    public static class CreateOrderException extends ConnectionException {
    
    }
}
