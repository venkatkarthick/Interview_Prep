package LLD.ConceptAndCoding.F_ProxyDesignPattern.Code;

public class EmployeeDaoProxy implements EmployeeDao {

    EmployeeDao employeeDaoObj;

    EmployeeDaoProxy() {
        employeeDaoObj=new EmployeeDaoImpl();
    }

    @Override
    public void create(String client, Employee obj) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDaoObj.create(client, obj);
        }
        throw new Exception("Create Access Denied");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDaoObj.delete(client, employeeId);
        }
        throw new Exception("Delete Access Denied");
    }

    @Override
    public Employee get(String client, int employeeId) throws Exception {
        if (client.equals("ADMIN") || client.equals("USER")) {
            return employeeDaoObj.get(client, employeeId);
        }
        throw new Exception("Get Access Denied");
    }
}
