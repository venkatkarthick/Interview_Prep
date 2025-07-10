package LLD.ConceptAndCoding.F_ProxyDesignPattern.Code;

public class Client {
    public static void main(String[] args) {
        try{
            EmployeeDao employeeObj = new EmployeeDaoProxy();
            employeeObj.create("ADMIN", new Employee());
            System.out.println("Operation successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
