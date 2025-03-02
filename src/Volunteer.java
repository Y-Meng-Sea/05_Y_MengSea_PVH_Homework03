public class Volunteer extends StaffMember {

    private double salary;

    public Volunteer(int id, String name, String address, double salary){
        super(id,name,address);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " address: " + address;
    }

    @Override
    public double pay() {
        return this.salary;
    }
    // setter and getter method
    public void setSalary(double salary){
        this.salary = salary;
    }
    public double getSalary(){
        return this.salary;
    }
}
