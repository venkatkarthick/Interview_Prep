package LLD.ConceptAndCoding.F_ProxyDesignPattern.Code;

public class EmployeeDaoImpl implements EmployeeDao{

    @Override
    public void create(String client, Employee obj) throws Exception {
        //creates a row
        System.out.println("Successfully created a row in Employee table");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception {
        //deletes a row
        System.out.println("Successfully deleted a row in Employee table");
    }

    @Override
    public Employee get(String client, int employeeId) throws Exception {
        //gets a row
        System.out.println("Successfully fetched a row from Employee table");
        return new Employee();
    }
}
