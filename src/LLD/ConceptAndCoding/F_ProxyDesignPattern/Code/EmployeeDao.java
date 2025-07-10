package LLD.ConceptAndCoding.F_ProxyDesignPattern.Code;

public interface EmployeeDao {
    public void create(String client, Employee obj) throws Exception;
    public void delete(String client, int employeeId) throws Exception;
    public Employee get(String client, int employeeId) throws Exception;
}
