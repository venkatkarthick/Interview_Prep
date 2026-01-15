package Revision.LLD;

public class G_ProxyPattern {

    static class Employee{
        String name;
    }
    interface EmployeeDao {
        public void create(String client, Employee emp) throws Exception;
        public void get(String client, Employee emp) throws Exception;
    }
    static class EmployeeDaoImpl implements EmployeeDao{
        @Override
        public void create(String client,Employee emp) {
            System.out.println("Employee object is created");
        }
        @Override
        public void get(String client, Employee emp) {
            System.out.println("Get API called");
        }
    }
    static class EmployeeDaoProxy implements EmployeeDao{
        EmployeeDao employeeDao;
        EmployeeDaoProxy() {
            employeeDao=new EmployeeDaoImpl();
        }
        @Override
        public void create(String client, Employee emp) throws Exception {
            if(client.equals("ADMIN")) {
                employeeDao.create(client, emp);
            } else {
                throw new Exception("Create Access Denied");
            }
        }
        @Override
        public void get(String client, Employee emp) throws Exception {
            if(client.equals("ADMIN") || client.equals("USER")) {
                employeeDao.get(client, emp);
            } else {
                throw new Exception("Get Access Denied");
            }
        }
    }

    public static void main(String[] args) {
        EmployeeDao employeeDao=new EmployeeDaoProxy();
        try {
            employeeDao.create("ADMIN", new Employee());
            System.out.println("Create Operation successful");
            employeeDao.create("USER", new Employee());
            System.out.println("Create Operation successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
