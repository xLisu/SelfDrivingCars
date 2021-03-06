package selfdrivingcars;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

/**
 * Gets the data from the input file as int[][]
 */
public class FileDataTest
{

    //Stack of rides
    public static Stack<Ride> rides = new Stack<>();
    public static Stack<Ride> sp1 = new Stack<>();
    public static Stack<Ride> sp2 = new Stack<>();
    public static Stack<Ride> sp3 = new Stack<>();
    public static Stack<Ride> sp4 = new Stack<>();
    public static Stack<Ride> ssh = new Stack<>();
    public static Stack<Ride> slg = new Stack<>();

    public static ArrayList<Ride> ridesArray = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException
    {
        int[][] fileData = getFileData();

        for (int i = 0; i < fileData.length; i++)
        {
            System.out.println(Arrays.toString(fileData[i]));
        }

        //int[] firstLine = getIntArrayFromString(dataList.get(0));
        int rows = fileData[0][0];
        int columns = fileData[0][1];
        int vehicleNum = fileData[0][2];
        int ridesNum = fileData[0][3];
        int rideBonus = fileData[0][4];
        int stepsNum = fileData[0][5];

        /* Stack needs to be sorted first here*/
        //Assign stack stack of rides
        for (int i = 0; i < ridesNum; i++)
        {
            int sX = fileData[i + 1][0];
            int sY = fileData[i + 1][1];
            int eX = fileData[i + 1][2];
            int eY = fileData[i + 1][3];
            int s = fileData[i + 1][4];
            int f = fileData[i + 1][5];
            int num = i;
            ridesArray.add(new Ride(sX, sY, eX, eY, s, f, num));
        }

        /*
        ArrayList<Ride> p1 = new ArrayList<>();
        ArrayList<Ride> p2 = new ArrayList<>();
        ArrayList<Ride> p3 = new ArrayList<>();
        ArrayList<Ride> p4 = new ArrayList<>();

        ArrayList<Ride> sh = new ArrayList<>();
        ArrayList<Ride> lg = new ArrayList<>();

        for (Ride ride : ridesArray)
        {
            //p1
            if (ride.startPosX < 1000 && ride.startPosY < 1500 && ride.endPosX < 1000 && ride.endPosY < 1500)
            {
                p1.add(ride);
            }
            //p2
            else if (ride.startPosX < 1000 && ride.startPosY > 1500 && ride.endPosX < 1000 && ride.endPosY > 1500)
            {
                p2.add(ride);
            }
            //p3
            else if (ride.startPosX > 1000 && ride.startPosY < 1500 && ride.endPosX > 1000 && ride.endPosY < 1500)
            {
                p3.add(ride);
            }
            //p4
            else if (ride.startPosX < 1000 && ride.startPosY < 1500 && ride.endPosX < 1000 && ride.endPosY < 1500)
            {
                p4.add(ride);
            }
            else if (ride.distane() < 200)
            {
                sh.add(ride);
            }
            else
            {
                lg.add(ride);
            }
        }

        Collections.sort(p1, Collections.reverseOrder());
        Collections.sort(p2, Collections.reverseOrder());
        Collections.sort(p3, Collections.reverseOrder());
        Collections.sort(sh, Collections.reverseOrder());
        Collections.sort(lg, Collections.reverseOrder());
         */
        Collections.sort(ridesArray); //Collections.reverseOrder());

        /*
        Collections.sort(ridesArray, new Comparator<Ride>()
        {
            @Override
            public int compare(Ride r1, Ride r2)
            {
                double dis1 = Math.sqrt(Math.pow(r1.startPosX - 1500, 2) + Math.pow(r1.startPosY - 2250, 2));
                double dis2 = Math.sqrt(Math.pow(r2.startPosX - 1500, 2) + Math.pow(r2.startPosY - 2250, 2));
                if (dis1 > dis2)
                {
                    return 1;
                }
                if (dis2 > dis1)
                {
                    return -1;
                }
                return 0;
            }
        });
         */
        //assigns rides to stack      
        for (Ride ride : ridesArray)
        {
            rides.push(ride);
        }
        /*
        for (Ride ride : lg)
        {
            rides.push(ride);
        }
        
        for (Ride ride : p3)
        {
            rides.push(ride);
        }
        for (Ride ride : p1)
        {
            rides.push(ride);
        }
         */

        Vehicle[] vehicles = new Vehicle[vehicleNum];

        //create all vehicles
        for (int i = 0; i < vehicles.length; i++)
        {
            vehicles[i] = new Vehicle();
        }
        
        //Start the loop
        for (int i = 0; i < stepsNum; i++)
        {
            for (Vehicle veh : vehicles)
            {
                veh.changePos(i);
            }
        }
        /*
        System.out.println("p1: " + p1.size());
        System.out.println("p2: " + p2.size());
        System.out.println("p3: " + p3.size());
        System.out.println("p4: " + p4.size());
        System.out.println("sh: " + sh.size());
        System.out.println("lg: " + lg.size());
         */
        //Starts reading console to create output
        PrintStream out = new PrintStream(new FileOutputStream("4.in"));
        System.setOut(out);

        for (int i = 0; i < vehicleNum; i++)
        {
            System.out.println(vehicles[i].getList());
        }
    }

    private static int[][] getFileData() throws FileNotFoundException
    {
        File file = new File("C:\\Users\\poczt\\OneDrive\\Dokumenty\\NetBeansProjects\\SelfDrivingCars\\src\\selfdrivingcars\\d_metropolis.in");
        Scanner scanner = new Scanner(file);

        // Get data out of file
        ArrayList<int[]> dataList = new ArrayList<>();
        while (scanner.hasNextLine())
        {
            dataList.add(getIntArrayFromString(scanner.nextLine()));
        }

        // Convert ArrayList to 2d array
        int[][] data = new int[dataList.size()][6];
        for (int i = 0; i < data.length; i++)
        {
            data[i] = dataList.get(i);
        }

        return data;
    }

    /*
     * Converts a string of space separated integers to an array of integers
     */
    private static int[] getIntArrayFromString(String inputString)
    {
        String[] tokens = inputString.split(" ");
        int[] numbers = new int[tokens.length];

        for (int i = 0; i < numbers.length; i++)
        {
            numbers[i] = Integer.parseInt(tokens[i]);
        }
        return numbers;
    }
}
