//Clemens Osbahr // Matrikelnummer: 23453430


import java.util.Scanner;


public class Circuit {
    public static void main(String[] args) {
        float protectiveResistor;
        int Voltage = 5;
        float d1 = 0.003f;
        float d2 = 0.004f;
        float a1 =  0.0097f;
        float a2 =  0.0051f;

        float resistor1 = 1000;
        float resistor2 = 472;

        final double EPSILON_0 = 8.854e-12;


        Scanner scRes = new Scanner(System.in);
        System.out.println("Please enter a positive Resistance");
        protectiveResistor = scRes.nextFloat();

        while (protectiveResistor < 0) {
            System.err.println("Resistance must be greater than 0.");
            System.out.println("Please enter a positive Resistance");
            protectiveResistor = scRes.nextFloat();


        }
        float current = (float) Voltage/protectiveResistor;
        float power = current*current*protectiveResistor;


        System.out.println("Current: "+current+ " Ampere; "+"Power: " +power+ " Watt");

        Scanner scMod = new Scanner(System.in);
        System.out.println("Please enter how the Circuit should be configured: cap, res, short");

        String module = scMod.nextLine();

        switch (module) {
            case "cap":
                double capacity1 = EPSILON_0*0.0097f/d1;
                double capacity2 = EPSILON_0*0.0097f/d2;
                double totalCapacity = capacity1+capacity2;

                System.out.println("Capacity1: "+capacity1+ " Farad");
                System.out.println("Capacity2: "+capacity2+ " Farad");
                System.out.println("The total capacity is: "+ totalCapacity + " Farad.");
                break;
            case "res":
                float totalResistance = 1/resistor1 + 1/resistor2;
                System.out.println("The total resistance is: "+ totalResistance + " Ohm");
                break;

            case "short":
                System.out.println("Board shorted - No modules installed!");
                break;

            default:
                System.err.println("Only Inputs cap, res and short are accepted");

        }

//once finished
        scRes.close();
        scMod.close();

    }
}