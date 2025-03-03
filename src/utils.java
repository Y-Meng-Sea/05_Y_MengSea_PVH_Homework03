import java.util.ArrayList;
import java.util.InputMismatchException;
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
        staffMembers.add(new SalariedEmployee(1,"Shoko ","PP",250,100));
        staffMembers.add(new Volunteer(2,"gojo","BMC",230));
        staffMembers.add(new HourlySalaryEmployee(3,"sukura","PPT",5,30));
        staffMembers.add(new HourlySalaryEmployee(4,"koko","Korea",12,50));
        staffMembers.add(new Volunteer(5,"geto","japan",500));
        staffMembers.add(new SalariedEmployee(6,"dodo","PPT",420,120));
        staffMembers.add(new Volunteer(7,"yondo","America",410));

    }

    static void menu(){
        // validation chose option
        while (true){
            CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
            Table t = new Table(1, BorderStyle.UNICODE_ROUND_BOX_WIDE, ShownBorders.SURROUND_HEADER_AND_COLUMNS);
            t.setColumnWidth(0, 50, 100);

            t.addCell("STAFF MANAGEMENT SYSTEM",numberStyle);
            t.addCell("1. Insert Employee");
            t.addCell("2. Update Employee");
            t.addCell("3. Display Employee");
            t.addCell("4. Remove Employee");
            t.addCell("5. Exit Employee");
            System.out.println(t.render());

            System.out.println("-------------------------------");
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
                utils.insert();
                utils.menu();
                break;
            case "2":
                update();
                break;
            case "3":
                utils.display();
                System.out.print("Press Enter to back to menu... ");
                System.console().readLine();
                utils.menu();
                break;
            case "4":
                remove();
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
                System.out.println("* Inserting new Staff as Volunteer *");
                System.out.println("New Staff ID: "+(currentId + 1));
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
                System.out.println("* Inserting new Staff as Salary Employee *");
                System.out.println("New Staff ID: "+ (currentId + 1));
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
                System.out.println("* Inserting new Staff as Hourly Employee *");
                System.out.println("New Staff ID: "+ (currentId + 1));
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
                t.addCell(String.valueOf(((SalariedEmployee) member).getSalary())+"$", numberStyle); // salary
                t.addCell(String.valueOf((((SalariedEmployee) member).getBonus()))+"$", numberStyle); // bonus
                t.addCell("---", numberStyle); // hour
                t.addCell("---", numberStyle); // rate
                t.addCell(String.valueOf(member.pay())+"$", numberStyle); // pay

            } else if (member instanceof  HourlySalaryEmployee) {
                t.addCell("Hourly Employee",numberStyle); // type
                t.addCell(String.valueOf(member.id), numberStyle); // id
                t.addCell(member.name, numberStyle); // name
                t.addCell(member.address, numberStyle); // address
                t.addCell("---", numberStyle); // salary
                t.addCell("---", numberStyle); // bonus
                t.addCell(String.valueOf(((HourlySalaryEmployee) member).getHourWorked())+"h", numberStyle); // hour
                t.addCell(String.valueOf(((HourlySalaryEmployee) member).getRate())+"$", numberStyle); // rate
                t.addCell(String.valueOf(member.pay())+"$", numberStyle); // pay

            } else if (member instanceof Volunteer){
                t.addCell("Volunteer Employee",numberStyle); // type
                t.addCell(String.valueOf(member.id), numberStyle); // id
                t.addCell(member.name, numberStyle); // name
                t.addCell(member.address, numberStyle); // address
                t.addCell(String.valueOf(((Volunteer) member).getSalary())+"$", numberStyle); // salary
                t.addCell("---", numberStyle); // bonus
                t.addCell("---", numberStyle); // hour
                t.addCell("---", numberStyle); // rate
                t.addCell(String.valueOf(member.pay())+"$", numberStyle); // pay
            }
        });
        System.out.println(t.render());
    }

    //remove employee logic
    static void remove(){
        System.out.println("=============* Remove Employee *=============");
        System.out.print("\n=> Enter ID to remove : ");
        int deleteId = scanner.nextInt();
        for(int i = 0 ; i <= staffMembers.size(); i++){
            try{
                if(staffMembers.get(i).id == deleteId){
                    staffMembers.remove(i);
                    System.out.println("* ID: "+ deleteId +" has been removed successfully! *");
                    break;
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("Not found");
            }

        }
        utils.menu();
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
                    updateVolunteer(member);
                } else if (member instanceof SalariedEmployee) {
                    updateSalaryEmployee(member);
                } else if (member instanceof HourlySalaryEmployee) {
                    updateHourlyEmployee(member);
                }
            }
        }));

    }

    // update type volunteer
    static void updateVolunteer(StaffMember member){
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
        t.addCell(String.valueOf(((Volunteer) member).getSalary())+"$", numberStyle); // salary
        t.addCell(String.valueOf(member.pay())+"$", numberStyle); // pay
        System.out.println(t.render());
        // update volunteer
        System.out.println("Choose one column to update:");
        String updateColumn;
        while (true){
            System.out.println("1.Name\t2.Address\t3.Salary\t4.Cancel");
            System.out.print("=> Select Column Number: ");
            updateColumn = scanner.nextLine();
            boolean valid = updateColumn.matches("^[1-4]$");
            if(valid){
                break;
            }
            System.out.println("Invalid option");
        }
        switch (updateColumn.trim()){
            case "1":
                String updateName;
                while (true){
                    System.out.print("=> Change Name To : ");
                    updateName = scanner.nextLine();
                    boolean validUpdateName = updateName.matches("^[a-zA-Z][a-zA-Z0-9 ]*$");
                    if (validUpdateName){
                        break;
                    }
                    System.out.println("Name you want to change is not allow !");
                }
                member.name = updateName;
                System.out.println("* Name has update successfully! *");
                updateVolunteer(member);
                break;
            case "2":
                String updateAddress;
                while (true){
                    System.out.print("=> Change Address To : ");
                    updateAddress = scanner.nextLine();
                    boolean validUpdateAddress = updateAddress.matches("^[a-zA-Z][a-zA-Z0-9 ]*$");
                    if (validUpdateAddress){
                        break;
                    }
                    System.out.println("Address you want to change is not allow !");
                }
                member.address = updateAddress;
                System.out.println("* Address has update successfully! *");
                updateVolunteer(member);
                break;
            case "3":
                double updateSalary;
                while (true) {
                    System.out.print("=> Change Salary To: ");
                    try {
                        updateSalary = scanner.nextDouble();
                        if (updateSalary < 0) {
                            System.out.println("Salary cannot be negative! Please enter a valid amount.");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                    }
                }

                ((Volunteer) member).setSalary(updateSalary);
                System.out.println("* Salary has been updated successfully! *");
                scanner.nextLine();
                updateVolunteer(member);
                break;
            case "4":
                menu();
                break;
        }
    }

    //update type salary employee
    static  void updateSalaryEmployee(StaffMember member){
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
        t.addCell(String.valueOf(((SalariedEmployee) member).getSalary())+"$", numberStyle); // salary
        t.addCell(String.valueOf((((SalariedEmployee) member).getBonus()))+"$", numberStyle); // bonus
        t.addCell(String.valueOf(member.pay())+"$", numberStyle); // pay
        System.out.println(t.render());
        // update salary
        System.out.println("Choose one column to update:");
        String updateColumn;
        while (true){
            System.out.println("1.Name\t2.Address\t3.Salary\t4.Bonus\t\t5.Cancel");
            System.out.print("=> Select Column Number: ");
            updateColumn = scanner.nextLine();
            boolean valid = updateColumn.matches("^[1-5]$");
            if(valid){
                break;
            }
            System.out.println("Invalid option");
        }
        switch (updateColumn.trim()){
            case "1":
                String updateName;
                while (true){
                    System.out.print("=> Change Name To : ");
                    updateName = scanner.nextLine();
                    boolean validUpdateName = updateName.matches("^[a-zA-Z][a-zA-Z0-9 ]*$");
                    if (validUpdateName){
                        break;
                    }
                    System.out.println("Name you want to change is not allow !");
                }
                member.name = updateName;
                System.out.println("* Name has update successfully! *");
                updateSalaryEmployee(member);
                break;
            case "2":
                String updateAddress;
                while (true){
                    System.out.print("=> Change Address To : ");
                    updateAddress = scanner.nextLine();
                    boolean validUpdateAddress = updateAddress.matches("^[a-zA-Z][a-zA-Z0-9 ]*$");
                    if (validUpdateAddress){
                        break;
                    }
                    System.out.println("Address you want to change is not allow !");
                }
                member.address = updateAddress;
                System.out.println("* Address has update successfully! *");
                updateSalaryEmployee(member);
                break;
            case "3":
                double updateSalary;
                while (true) {
                    System.out.print("=> Change Salary To: ");
                    try {
                        updateSalary = scanner.nextDouble();
                        if (updateSalary < 0) {
                            System.out.println("Salary cannot be negative! Please enter a valid amount.");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                    }
                }

                ((SalariedEmployee) member).setSalary(updateSalary);
                System.out.println("* Salary has been updated successfully! *");
                scanner.nextLine();
                updateSalaryEmployee(member);
                break;
            case "4":
                double updateBonus;
                while (true) {
                    System.out.print("=> Change Bonus To: ");
                    try {
                        updateBonus = scanner.nextDouble();
                        if (updateBonus < 0) {
                            System.out.println("Bonus cannot be negative! Please enter a valid amount.");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                    }
                }

                ((SalariedEmployee) member).setBonus(updateBonus);
                System.out.println("* Bonus has been updated successfully! *");
                scanner.nextLine();
                updateSalaryEmployee(member);
                break;
            case "5":
                menu();
                break;
        }
    }

    //update type hourly employee
    static  void updateHourlyEmployee(StaffMember member){
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
        t.addCell(String.valueOf(((HourlySalaryEmployee) member).getHourWorked())+"h", numberStyle); // hour
        t.addCell(String.valueOf(((HourlySalaryEmployee) member).getRate())+"$", numberStyle); // rate
        t.addCell(String.valueOf(member.pay())+"$", numberStyle); // pay
        System.out.println(t.render());
        // update salary
        System.out.println("Choose one column to update:");
        String updateColumn;
        while (true){
            System.out.println("1.Name\t2.Address\t3.Hourly\t4.Rate\t5.Cancel");
            System.out.print("=> Select Column Number: ");
            updateColumn = scanner.nextLine();
            boolean valid = updateColumn.matches("^[1-5]$");
            if(valid){
                break;
            }
            System.out.println("Invalid option");
        }
        switch (updateColumn.trim()){
            case "1":
                String updateName;
                while (true){
                    System.out.print("=> Change Name To : ");
                    updateName = scanner.nextLine();
                    boolean validUpdateName = updateName.matches("^[a-zA-Z][a-zA-Z0-9 ]*$");
                    if (validUpdateName){
                        break;
                    }
                    System.out.println("Name you want to change is not allow !");
                }
                member.name = updateName;
                System.out.println("* Name has update successfully! *");
                updateHourlyEmployee(member);
                break;
            case "2":
                String updateAddress;
                while (true){
                    System.out.print("=> Change Address To : ");
                    updateAddress = scanner.nextLine();
                    boolean validUpdateAddress = updateAddress.matches("^[a-zA-Z][a-zA-Z0-9 ]*$");
                    if (validUpdateAddress){
                        break;
                    }
                    System.out.println("Address you want to change is not allow !");
                }
                member.address = updateAddress;
                System.out.println("* Address has update successfully! *");
                updateHourlyEmployee(member);
                break;
            case "3":
                int updateHourly;
                while (true) {
                    System.out.print("=> Change Hourly To: ");
                    try {
                        updateHourly = scanner.nextInt();
                        if (updateHourly < 0) {
                            System.out.println("Hourly cannot be negative! Please enter a valid amount.");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                    }
                }

                ((HourlySalaryEmployee) member).setHourWorked(updateHourly);
                System.out.println("* Hourly has been updated successfully! *");
                scanner.nextLine();
                updateHourlyEmployee(member);
                break;
            case "4":
                double updateRate;
                while (true) {
                    System.out.print("=> Change Rate To: ");
                    try {
                        updateRate = scanner.nextDouble();
                        if (updateRate < 0) {
                            System.out.println("Rate cannot be negative! Please enter a valid amount.");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException exception) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        scanner.nextLine();
                    }
                }

                ((HourlySalaryEmployee) member).setRate(updateRate);
                System.out.println("* Rate has been updated successfully! *");
                scanner.nextLine();
                updateHourlyEmployee(member);
                break;
            case "5":
                menu();
                break;
        }
    }

}
