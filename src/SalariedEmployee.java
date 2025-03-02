public class SalariedEmployee extends StaffMember{
    private double salary;
    private double bonus;

    public SalariedEmployee(int id, String name, String address, double salary, double bonus){
        super(id,name,address);
        this.bonus = bonus;
        this.salary = salary;
    }

    @Override
    public String toString(){
        return "id: " + id + " name: " + name + " address: " + address + " salary: " + salary + " bonus: " + bonus;
    }

    @Override
    public double pay(){
        return salary + bonus;
    }
    // setter and getter methods
    public void setSalary(double salary){
        this.salary = salary;
    }
    public double getSalary(){
        return this.salary;
    }
    public void setBonus(double bonus){
        this.bonus = bonus;
    }
    public double getBonus(){
        return  this.bonus;
    }
}
