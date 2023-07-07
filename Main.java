
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {


        //Menu
        
        
         ArrayList<String>classesFile = new ArrayList<>(); //to store lines of class info
         ArrayList<ScheduleGenerator> classList = new ArrayList<>();
          ScheduleGenerator sg = new ScheduleGenerator();

         System.out.println("----Schedule Generator----\n");
             System.out.println("Menu Options");
             System.out.println("1: Read File");
             System.out.println("2: Generate Schedule");
             System.out.println("3: Display All Courses");
             System.out.println("0: Exit");
             System.out.print("Enter option number:");
        Scanner s = new Scanner(System.in);

        String input = s.next();
       

        while(input != "0"){
          
             

            if(input.equals("1")){ // read file and stores in classList
                 Scanner sc;

                   //TODO allow for input of filename
                   
                    try {
                    //reads csv file
                    sc = new Scanner(new File("Sample Schedule - Sheet1.csv")); //soon'll be filename
                
                        while(sc.hasNextLine()){
                            classesFile.add(sc.nextLine());
                        
                        }

                        classesFile.remove(0); //removes header line

                        for(String line: classesFile){
                            String[] cItems = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //splits by comma but keeps commas in Psych courses
                                classList.add(new ScheduleGenerator(cItems[0], cItems[1], cItems[2], cItems[3], cItems[4]));
                            
                        }


           
                    System.out.println("File read.");
                     sc.close();

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                        sg.setList(classList);


            }

            if(input.equals("2")){ //Schedule Generator
                
                if(sg.numberOfClasses() == 0){System.out.println("List empty. Please read in file first (Option 1).\n");}

                
                
                else{

                sg.schedGen();

                System.out.println("\na: Save Schedule");
                System.out.println("b: Generate Schedule");
                System.out.println("c: Return to menu");
                input = s.next();

                while(input.toLowerCase() != "c"){

                    
                    if(input.toLowerCase().equals("a")){
                        
                        sg.saveSchedule();

                    }
                    if(input.toLowerCase().equals("b")){
                       sg.emptyPicked();
                       sg.schedGen();
                    }

                    if(input.toLowerCase().equals("c")){
                        break;
                    }

                      System.out.println("\na: Save Schedule");
                      System.out.println("b: Generate Schedule");
                      System.out.println("c: Return to menu");
                      input= s.next();
                }

                }
                
                

              

            }

            if(input.equals("3")){
                sg.displayAllCourses();

            }

            if(input.equals("0")){
                System.out.println("Goodbye!");
                break;
            }

            System.out.println("----Schedule Generator----\n");
             System.out.println("Menu Options");
             System.out.println("1: Read File");
             System.out.println("2: Generate Schedule");
             System.out.println("3: Display All Courses");
             System.out.println("0: Exit");
             System.out.print("Enter option number:");
             input = s.next();

        

        }




   s.close();



   }

   
    }
