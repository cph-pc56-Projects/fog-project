package exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {
    
    public static class QueryException extends ConnectionException {
    
    }
    
    public static class CreateCustomerException extends ConnectionException {
    
    }
    
    public static class LoginError extends ConnectionException {
    
    }
    
    public static class DataAccessException extends ConnectionException {
    
    }
    
    public static class UpdateUserInfoException extends ConnectionException {
    
    }
}
