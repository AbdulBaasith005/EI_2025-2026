import java.util.Scanner;
import src.*;

//Client - Uses the builder and director to construct the product.

public class BuilderPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ComputerBuilder computerBuilder = new ComputerBuilder();
        ComputerDirector computerDirector = new ComputerDirector();

        System.out.print("Enter CPU: ");
        String cpu = sc.nextLine();

        System.out.print("Enter RAM: ");
        String ram = sc.nextLine();

        System.out.print("Enter Storage: ");
        String storage = sc.nextLine();

        computerDirector.construct(computerBuilder, cpu, ram, storage);
        Computer gamingComputer = computerBuilder.getResult();
        gamingComputer.displayInfo();
    }
}
