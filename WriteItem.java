/**
 * This program takes in input of items from the user and adds them to a file
 * it then creates a backup file and display the output onscreen
 */
import java.io.*;
import java.util.Scanner;
import java.util.Calendar;


/**
 * @author Afsanat Ineza
 *          Farida Eleshin
 */

public class WriteItem {

    //constructor
    public WriteItem(){ }


    /**
     * Writing String to File using PrintWriter and FileOutputStream
     * throws a filenotfound exception incase the user misspells the file name
     */

    public void writingTextToFile() throws IOException {
        PrintWriter printWriter = null;

        try {
            //write to the file only if append is true
            printWriter = new PrintWriter(new FileOutputStream("essentials_stock.txt", true));
        }catch(FileNotFoundException fnfe) {
            fnfe.getMessage();
        }

        //creating ten instances of the item class
        Items item1 = new Items("oil", 3, 5);
        Items item2 = new Items("book", 6, 34);
        Items item3 = new Items("snapple", 9, 56);
        Items item4 = new Items("milo", 23,78 );
        Items item5 = new Items("oil", 7, 6);
        Items item6 = new Items("indomie", 6, 7);
        Items item7 = new Items();
        Items item8 = new Items("kalyppo", 4, 9);
        Items item9= new Items("pen", 3, 10);
        Items item10= new Items("nido", 2, 11);


        //write the ten items to the file
        printWriter.print(item1.printMethod());
        printWriter.print(item2.printMethod());
        printWriter.print(item3.printMethod());
        printWriter.print(item4.printMethod());
        printWriter.print(item5.printMethod());
        printWriter.print(item6.printMethod());
        printWriter.print(item7.printMethod());
        printWriter.print(item8.printMethod());
        printWriter.print(item9.printMethod());
        printWriter.print(item10.printMethod());


        /**
         * prompt the user to enter more items
         *         and write them them to the file(essentials_stock.txt)
         */


        System.out.println("HOW MANY ITEMS DO YOU WANT TO ADD?");
        Scanner n1 = new Scanner(System.in);
        int n=n1.nextInt(); //maximum items to be inputted

        /*
        throw an exception if maximum number to be entered is negative or extra large
         */
        if(n<0  || n>Integer.MAX_VALUE){
            try{
                throw new Exception("Illegal argument");
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }


        /*
        enter the items one by one and write it to the file
         */

        try{
            for (int i=0; i<n;i++) {
                System.out.println("enter the product name");
                Scanner prod = new Scanner(System.in);
                String product = prod.nextLine();

                System.out.println("enter the price");
                Scanner p = new Scanner(System.in);
                int price = prod.nextInt();

                System.out.println("enter quantity");
                Scanner q = new Scanner(System.in);
                int quant = prod.nextInt();

                Items item11 = new Items(product, price, quant);
                printWriter.print(item11.printMethod());
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Index out of bound");
        }


        printWriter.close();  //close the file
        backup();  //copy everything in the file to another file
    }


    /**
     *
     * @throws IOException
     * create a backup of the stocks in another file called backup_essentials.text
     */


    private static void backup() throws IOException {
        InputStream source = new FileInputStream("essentials_stock.txt");    //pass a file to the source inputStream
        OutputStream  backup = new FileOutputStream("backup_essentials_stock.txt");  //pass a file to the backup outputStream

        byte[] StrTobytes = new byte[1024]; //1Kb is 1024Bytes

        //while there is something in the source file, write it to the backup file
        int i;
        while ((i = source.read(StrTobytes)) > 0) {
            backup.write(StrTobytes, 0, i);
        }

        source.close(); //close the source file
        backup.close();  // close the backup file
    }


    /**
     *
     * @throws IOException
     * display items on the scree
     */



    public void DisplayOnScreen() throws IOException {
        //create a buffer class and pass our file as a  parameter
        BufferedReader buff = new BufferedReader(new FileReader("essentials_stock.txt"));

        //loop through the file, line by line and print it if it is not null
        String i;
        while ((i = buff.readLine()) != null) {
            System.out.println(i);
        }
    }



    public static void main(String[] args) {

        //get the start time of the code
        long start = Calendar.getInstance().getTime().getTime();
        WriteItem pwd = new WriteItem();

        try {
            pwd.writingTextToFile();
            System.out.println("**HERE ARE ITEMS CURRENTLY ON THE SHELVES OF ESSENTIALS SHOP**");
            pwd.DisplayOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //get the end time and subtract it from the start time to get the runtime
        long end = Calendar.getInstance().getTime().getTime();
        long duration = end - start;
        System.out.println("\nRuntime: "+duration+"ms");

    }

}