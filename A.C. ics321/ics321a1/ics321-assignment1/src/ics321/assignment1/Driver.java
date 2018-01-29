package ics321.assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
	
	//include test for binary load filename, binary etc in Driver class

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Assignment1 a1 = new Assignment1();
            Timing stopwatch = new Timing();
            FileReader file = new FileReader("commands.txt");
            BufferedReader reader = new BufferedReader(file);
            String line = null;

            stopwatch.start();
            if((line = reader.readLine())!=null) {
                String[] arr = line.split(" ");
                if (arr[0].equalsIgnoreCase("naiveLoad") && arr.length==2) {
                    a1.naiveLoad(arr[1]);
                } else if (arr[0].equalsIgnoreCase("binaryLoad") && arr.length==2) {
                    a1.binaryLoad(arr[1]); // load the fileName (i.e. the value stored in arr[1])
                } else {
                    System.out.println("Error: First line of commands must be a load and not "+line);
                    reader.close();
                    return;
                }
            }
       
            stopwatch.stop();
            System.out.println( stopwatch.print("Total load time") );
            stopwatch.start();
            while ((line = reader.readLine())!=null) {
                String[] arr = line.split(" ");
                if (arr[0].equalsIgnoreCase("naiveSearchEq") && arr.length==3) {
                    try {
                        a1.naiveSearchEq(Integer.parseInt(arr[1]), arr[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipped line: "+line);
                    }catch (NullPointerException npe) {
                        System.out.println("Skipped line: "+line);
            		}
                } else if (arr[0].equalsIgnoreCase("naiveBufSearchEq") && arr.length==3) {
                    try {
                        a1.naiveBufSearchEq(Integer.parseInt(arr[1]), arr[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipped line: "+line);
                    }catch (NullPointerException npe) {
                        System.out.println("Skipped line: "+line);
            		}
                }  else if (arr[0].equalsIgnoreCase("naiveSearchGtr") && arr.length==3) {
                    try {
                        a1.naiveSearchGtr(Integer.parseInt(arr[1]), Float.parseFloat(arr[2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipped line: "+line);
                    }catch (NullPointerException npe) {
                        System.out.println("Skipped line: "+line);
            		}
               }else if (arr[0].equalsIgnoreCase("naiveBufSearchGtr") && arr.length==3) {
                        try {
                            a1.naiveBufSearchGtr(Integer.parseInt(arr[1]), Float.parseFloat(arr[2]));
                        } catch (NumberFormatException e) {
                            System.out.println("Skipped line: "+line);
                        }catch (NullPointerException npe) {
                            System.out.println("Skipped line: "+line);
                		}
                } else if (arr[0].equalsIgnoreCase("binarySearchEq") && arr.length==3) {
                    try {
                        a1.binarySearchEq(Integer.parseInt(arr[1]), arr[2]);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipped line: "+line);
                    } catch (NullPointerException npe) {
                        System.out.println("Skipped line: "+line);
            		}
                }else if (arr[0].equalsIgnoreCase("binarySearchGtr") && arr.length==3) {
                    try {
                        a1.binarySearchGtr(Integer.parseInt(arr[1]), Float.parseFloat(arr[2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Skipped line: "+line);
                    }
                    catch(NullPointerException npe) {
                        System.out.println("Skipped line: "+line);
            		}
                }else {
                    System.out.println("Skipped line: "+line);
                }
            }
            stopwatch.stop();
            System.out.println( stopwatch.print("Total query time") );
            reader.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Path to command file required");
        } catch (FileNotFoundException e) {
            System.out.println("Command file "+ args[0] +" not found");
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

}
