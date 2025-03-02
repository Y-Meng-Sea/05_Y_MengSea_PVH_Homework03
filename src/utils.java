import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
public class utils {
    static  List<StaffMember> staffMembers = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static String option;
    static int currentId = 5;

    // static data
    static void addStaticData(){
        staffMembers.add(new SalariedEmployee(1,"sea sea","PP",200,10));
        staffMembers.add(new Volunteer(2,"meng meng","BMC",230));
        staffMembers.add(new HourlySalaryEmployee(3,"KO KO","PPT",22,50));
        staffMembers.add(new HourlySalaryEmployee(4,"Ko KO","PPT",22,50));
        staffMembers.add(new HourlySalaryEmployee(5,"Ko KO","PPT",22,50));
    }

    static void menu(){
        // validation chose option
        while (true){
            System.out.println("==============================================");
            System.out.println("\t\t\tSTAFF MANAGEMENT SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Insert Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Display Employee");
            System.out.println("4. Remove Employee");
            System.out.println("5. Exit Employee");
            System.out.println("\n==============================================");
            System.out.print("-> Choose an option() : ");
            option = scanner.nextLine();
            boolean validOpt = option.matches("^[1-5]$");
            if(validOpt){
                break;
            }
            System.out.println("Invalid Option");
        }
        // case each options
        switch (option){
            case "1" :
                System.out.println("Insert Employee");
                utils.insert();
                utils.menu();
                break;
            case "2":
                System.out.println("Update Employee");
                update();
                break;
            case "3":
                System.out.println("Display Employee");
                utils.display();
                System.out.println("Press Enter to back to menu...");
                System.console().readLine();
                utils.menu();
                break;
            case "4":
                System.out.println("Remove Employee");
                break;
            case "5":
                System.out.println("\n==============================================");
                System.out.println("\t\t\tThanks you 🥹🍻!!");
                System.out.println("==============================================");
                break;
        }
    }

    // insert employee logic
    static void insert(){
        // chose type of employee
        String choseTypeEmployee;
        while (true){
            System.out.println("Choose type:");
            CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
            Table t = new Table(4, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
            t.setColumnWidth(0, 8, 20);
            t.setColumnWidth(0, 8, 20);
            t.setColumnWidth(0, 8, 20);
            t.setColumnWidth(0, 8, 20);
            t.addCell("1. Volunteer",numberStyle);
            t.addCell("2. Salaries Employee",numberStyle);
            t.addCell("3. Hourly Employee",numberStyle);
            t.addCell("4. Back",numberStyle);
            System.out.println(t.render());

            System.out.print("Enter Type Number : ");
            choseTypeEmployee = scanner.nextLine();
            boolean valid = choseTypeEmployee.matches("^[1-4]$");
            if (valid){
                break;
            }
            System.out.println("Invalid Type!! ");
        }
        String name;
        String address;
        double salary;
        switch (choseTypeEmployee.trim()){
            case "1": // volunteer
                System.out.println(currentId + 1);
                System.out.print("Enter name: ");
                name =scanner.nextLine();
                System.out.print("Enter address: ");
                address = scanner.nextLine();
                System.out.print("Enter Salary: ");
                salary = scanner.nextDouble();
                scanner.nextLine();
                staffMembers.add(new Volunteer(currentId+1 , name , address,salary));
                currentId +=1;
                System.out.println("* You added " + name + " of type Volunteer successfully! *");
                break;
            case "2": //salary employee
                System.out.println(currentId + 1);
                System.out.print("Enter name: ");
                name =scanner.nextLine();
                System.out.print("Enter address: ");
                address = scanner.nextLine();
                System.out.print("Enter Salary: ");
                salary = scanner.nextDouble();
                System.out.print("Enter bonus: ");
                double bonus = scanner.nextDouble();
                scanner.nextLine();
                staffMembers.add(new SalariedEmployee(currentId +1, name ,address, salary,bonus ));
                currentId +=1;
                System.out.println("* You added " + name + " of type Salary Employee successfully! *");
                break;
            case "3": //Hourly Employee
                System.out.println(currentId + 1);
                System.out.print("Enter name: ");
                name =scanner.nextLine();
                System.out.print("Enter address: ");
                address = scanner.nextLine();
                System.out.print("Enter Salary: ");
                salary = scanner.nextDouble();
                System.out.println("Enter hourlyWorking: ");
                int hourlyWorking = scanner.nextInt();
                System.out.print("Enter rate: ");
                double rate = scanner.nextDouble();
                scanner.nextLine();
                staffMembers.add(new HourlySalaryEmployee(currentId +1 , name,address,hourlyWorking,rate));
                currentId +=1;
                System.out.println("* You added " + name + " of type Hourly Employee successfully! *");
                break;
            case "4":
                break;
        }

    }

    //display employee logic
    static void display(){
        // table
        CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
        Table t = new Table(9, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
        t.setColumnWidth(0, 8, 20);
        t.setColumnWidth(1, 7, 10);
        t.setColumnWidth(2, 9, 16);
        t.setColumnWidth(3, 9, 16);
        t.setColumnWidth(4, 9, 16);
        t.setColumnWidth(5, 9, 16);
        t.setColumnWidth(6, 9, 16);
        t.setColumnWidth(7, 9, 16);
        t.setColumnWidth(8, 9, 16);

        // total 9 column
        t.addCell("Type",numberStyle);
        t.addCell("ID", numberStyle);
        t.addCell("Name", numberStyle);
        t.addCell("Address", numberStyle);
        t.addCell("Salary", numberStyle);
        t.addCell("Bonus", numberStyle);
        t.addCell("Hour", numberStyle);
        t.addCell("Rate", numberStyle);
        t.addCell("Pay", numberStyle);

        // loop to showing data
        staffMembers.stream().forEach((member)->{

            if (member instanceof SalariedEmployee){
                t.addCell("Salary Employee",numberStyle); // type
                t.addCell(String.valueOf(member.id), numberStyle); // id
                t.addCell(member.name, numberStyle); // name
                t.addCell(member.address, numberStyle); // address
                t.addCell(String.valueOf(((SalariedEmployee) member).getSalary()), numberStyle); // salary
                t.addCell(String.valueOf((((SalariedEmployee) member).getBonus())), numberStyle); // bonus
                t.addCell("---", numberStyle); // hour
                t.addCell("---", numberStyle); // rate
                t.addCell(String.valueOf(member.pay()), numberStyle); // pay

            } else if (member instanceof  HourlySalaryEmployee) {
                t.addCell("Hourly Employee",numberStyle); // type
                t.addCell(String.valueOf(member.id), numberStyle); // id
                t.addCell(member.name, numberStyle); // name
                t.addCell(member.address, numberStyle); // address
                t.addCell("---", numberStyle); // salary
                t.addCell("---", numberStyle); // bonus
                t.addCell(String.valueOf(((HourlySalaryEmployee) member).getHourWorked()), numberStyle); // hour
                t.addCell(String.valueOf(((HourlySalaryEmployee) member).getRate()), numberStyle); // rate
                t.addCell(String.valueOf(member.pay()), numberStyle); // pay

            } else if (member instanceof Volunteer){
                t.addCell("Volunteer Employee",numberStyle); // type
                t.addCell(String.valueOf(member.id), numberStyle); // id
                t.addCell(member.name, numberStyle); // name
                t.addCell(member.address, numberStyle); // address
                t.addCell(String.valueOf(((Volunteer) member).getSalary()), numberStyle); // salary
                t.addCell("---", numberStyle); // bonus
                t.addCell("---", numberStyle); // hour
                t.addCell("---", numberStyle); // rate
                t.addCell(String.valueOf(member.pay()), numberStyle); // pay
            }
        });
        System.out.println(t.render());
    }

    //remove employee logic
    static void remove(){
        System.out.println();
    }

    // update employee logic
    static void update(){
        System.out.println("=============* Update Employee *=============");
        var ref = new Object() {
            String update;
        };
        while (true){
            System.out.print("Enter or Search ID to update : ");
            ref.update = scanner.nextLine();
            boolean valid = ref.update.matches("\\d");
            if (valid){
                break;
            }
            System.out.println("Invalid option please try again!!");
        }
        
        staffMembers.forEach((member -> {
            if (member.id == Integer.parseInt(ref.update)){
                if(member instanceof Volunteer){
                    CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
                    Table t = new Table(6, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

                    t.setColumnWidth(0, 8, 20);
                    t.setColumnWidth(1, 7, 10);
                    t.setColumnWidth(2, 9, 16);
                    t.setColumnWidth(3, 9, 16);
                    t.setColumnWidth(4, 9, 16);
                    t.setColumnWidth(5, 9, 16);

                    // total 6 column
                    t.addCell("Type",numberStyle);
                    t.addCell("ID", numberStyle);
                    t.addCell("Name", numberStyle);
                    t.addCell("Address", numberStyle);
                    t.addCell("Salary", numberStyle);
                    t.addCell("Pay", numberStyle);

                    t.addCell("Volunteer Employee",numberStyle); // type
                    t.addCell(String.valueOf(member.id), numberStyle); // id
                    t.addCell(member.name, numberStyle); // name
                    t.addCell(member.address, numberStyle); // address
                    t.addCell(String.valueOf(((Volunteer) member).getSalary()), numberStyle); // salary
                    t.addCell(String.valueOf(member.pay()), numberStyle); // pay
                    System.out.println(t.render());
                } else if (member instanceof SalariedEmployee) {
                    CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
                    Table t = new Table(7, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);

                    t.setColumnWidth(0, 8, 20);
                    t.setColumnWidth(1, 7, 10);
                    t.setColumnWidth(2, 9, 16);
                    t.setColumnWidth(3, 9, 16);
                    t.setColumnWidth(4, 9, 16);
                    t.setColumnWidth(5, 9, 16);
                    t.setColumnWidth(6, 9, 16);

                    // total 7 column
                    t.addCell("Type",numberStyle);
                    t.addCell("ID", numberStyle);
                    t.addCell("Name", numberStyle);
                    t.addCell("Address", numberStyle);
                    t.addCell("Salary", numberStyle);
                    t.addCell("Bonus", numberStyle);
                    t.addCell("Pay", numberStyle);

                    t.addCell("Salary Employee",numberStyle); // type
                    t.addCell(String.valueOf(member.id), numberStyle); // id
                    t.addCell(member.name, numberStyle); // name
                    t.addCell(member.address, numberStyle); // address
                    t.addCell(String.valueOf(((SalariedEmployee) member).getSalary()), numberStyle); // salary
                    t.addCell(String.valueOf((((SalariedEmployee) member).getBonus())), numberStyle); // bonus
                    t.addCell(String.valueOf(member.pay()), numberStyle); // pay
                    System.out.println(t.render());
                } else if (member instanceof HourlySalaryEmployee) {
                    CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
                    Table t = new Table(7, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.ALL);
                    
                    t.setColumnWidth(0, 8, 20);
                    t.setColumnWidth(1, 7, 10);
                    t.setColumnWidth(2, 9, 16);
                    t.setColumnWidth(3, 9, 16);
                    t.setColumnWidth(4, 9, 16);
                    t.setColumnWidth(5, 9, 16);
                    t.setColumnWidth(6, 9, 16);

                    // total 7 column
                    t.addCell("Type",numberStyle);
                    t.addCell("ID", numberStyle);
                    t.addCell("Name", numberStyle);
                    t.addCell("Address", numberStyle);
                    t.addCell("Hourly", numberStyle);
                    t.addCell("Rate", numberStyle);
                    t.addCell("Pay", numberStyle);

                    t.addCell("Hourly Employee",numberStyle); // type
                    t.addCell(String.valueOf(member.id), numberStyle); // id
                    t.addCell(member.name, numberStyle); // name
                    t.addCell(member.address, numberStyle); // address
                    t.addCell(String.valueOf(((HourlySalaryEmployee) member).getHourWorked()), numberStyle); // hour
                    t.addCell(String.valueOf(((HourlySalaryEmployee) member).getRate()), numberStyle); // rate
                    t.addCell(String.valueOf(member.pay()), numberStyle); // pay
                    System.out.println(t.render());

                }

            }
        }));

    }
}
