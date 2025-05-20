import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String makeBike;

        System.out.println("Let's make a motorcycle!");
        System.out.println();
        do {
            Motorcycle motorcycle = new Motorcycle("", new Frame(Material.STEEL, Size.STANDARD), "", new Engine(Size.STANDARD, 1.0), new Wheel(1.0), new Wheel(1.0), Fuel.GASOLINE);

            System.out.println("What color will it be?");
            String color = scanner.nextLine();
            motorcycle.setColor(color);



            System.out.println("What sort of frame will it have?");
            for (int i = 0; i < Size.values().length; i++) {
                System.out.print((i + 1) + ": " + Size.values()[i] + "     ");
            }
            System.out.println();
            int frameIndex = scanner.nextInt();
            scanner.nextLine();
            motorcycle.frame.setFrameType(frameIndex);



            System.out.println("What will the frame be made of?");
            for (int i = 0; i < Material.values().length; i++) {
                System.out.print((i + 1) +": " + Material.values()[i] + "     ");
            }
            System.out.println();
            int materialIndex = scanner.nextInt();
            scanner.nextLine();
            motorcycle.frame.setFrameMaterial(materialIndex);

            System.out.println("How big will its engine be?");
            for (int i = 0; i < Size.values().length; i++) {
                System.out.print((i + 1) +": " + Size.values()[i] + "     ");
            }
            System.out.println();
            int engineIndex = scanner.nextInt();
            scanner.nextLine();
            motorcycle.engine.setSize(engineIndex);

            System.out.println("How much horsepower will it have?");
            double horsePower = scanner.nextDouble();
            scanner.nextLine();
            motorcycle.engine.setHorsePower(horsePower);

            System.out.println("How wide is the front wheel? (in inches)");
            double frontWheelLength = scanner.nextDouble();
            scanner.nextLine();
            motorcycle.frontWheel.setLength(frontWheelLength);

            System.out.println("How wide is the back wheel? (in inches)");
            double backWheelLength = scanner.nextDouble();
            scanner.nextLine();
            motorcycle.backWheel.setLength(backWheelLength);

            System.out.println("What will it be fueled with?");
            for (int i = 0; i < Fuel.values().length; i++) {
                System.out.print((i + 1) +": " + Fuel.values()[i] + "     ");
            }
            System.out.println();
            int fuelIndex = scanner.nextInt();
            scanner.nextLine();
            motorcycle.setFuel(fuelIndex);

            System.out.println("What decal will it have? (leave empty for no decal)");
            String decal = scanner.nextLine();
            motorcycle.setDecal(decal);

            motorcycle.printResults();

            System.out.println("\nBuild another? (y/n)");
            makeBike = scanner.next();
            scanner.nextLine();
        } while (makeBike.equals("y"));
    }
}
