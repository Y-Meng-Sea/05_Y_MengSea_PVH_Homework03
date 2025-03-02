public class HourlySalaryEmployee extends StaffMember{
    private int hourWorked;
    private double rate;

    public HourlySalaryEmployee(int id, String name, String address, int hourWorked, double rate){
        super(id, name, address);
        this.hourWorked = hourWorked;
        this.rate = rate;
    }
    @Override
    public String toString(){
        return "id: "+ id + " name: " + name + " address: " + address + " hourWorked: " + hourWorked + " rate: " + rate;
    }

    @Override
    public double pay(){
        return hourWorked * rate;
    }

    //setter and getter method
    public void setHourWorked(int hourWorked){
        this.hourWorked = hourWorked;
    }
    public int getHourWorked(){
        return this.hourWorked;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getRate(){
        return this.rate;
    }
}
