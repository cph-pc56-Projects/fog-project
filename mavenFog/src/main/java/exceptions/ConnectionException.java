package exceptions;

//Thrown when the connection with DB can`t be established
public class ConnectionException extends Exception {
    
    //Thrown by the Prepared Statements 
    public static class QueryException extends Exception {
        //We can`t process your request at the moment
    }
    
    //Thrown by the createCustomer method in User Mapper 
    public static class CreateCustomerException extends Exception {
        //We can`t create your username at the moment
    }
    
    //Thrown by the createSalesRep method in User Mapper 
    public static class CreateSalesRepException extends Exception {
        //We can`t create a salesRep account at the moment
    }
    
    //Thrown by the getEmail and getPassword in User Mapper method when logging in
    public static class LoginError extends Exception {
        //Wrong login details
    }
    
    //Thrown by the Update Details methods in User Mapper
    public static class UpdateUserInfoException extends Exception {
        //We can`t update your information at the moment
    }
    
    //Thrown by the CreateOrder method in Order Mapper
    public static class CreateOrderException extends Exception {
        //We can`t create your order at the moment
    }
    
    //Thrown by UpdateSalesRep, UpdateDelivery and UpdateInvoice methods in the Order Mapper
    public static class UpdateOrderDetailsException extends Exception {
        //We can`t update the order details at the moment
    }
    
    //Thrown by createDelivery method in Delivery Mappper if insertion fails
    public static class CreateDeliveryException extends Exception {
        //We can`t create this delivery at the moment
    }
    
    //Thrown by createProduct method in Product Mapper if inerstion fails
    public static class CreateProductException extends Exception {
        //We can`t create you product at this moment
    }
    
    //THrown by getAllProducts method in Product Mapper if extration fails
    public static class GetAllProductsException extends Exception {
        //We can`t show you the products at the moment
    }
    //Thrown by getAllOrders method in Orders Mapper if extration fails
    public static class GetAllOrdersException extends Exception {
        //We can`t show you the orders at the moment
    }
    
    //Thrown by getAllDelivery method in Delivery Mapper if extration fails
    public static class GetAllDeliveryException extends Exception {
        //We can`t show you the deliveries at the moment
    }
    
    //Thrown by getAllUsers method in User Mapper if extration fails
    public static class GetAllUsersException extends Exception {
        //We can`t show you the users at the moment
    }
        
    //Thrown by createInvoice method in Invoice Mappper if insertion fails
    public static class CreateInvoiceException extends Exception {
        //We can`t create this invoice at the moment
    }
    
    //Thrown by getAllInvoices method in Invoice Mapper if extration fails
    public static class GetAllInvoicesException extends Exception {
        //We can`t show you the invoices at the moment
    }
}
