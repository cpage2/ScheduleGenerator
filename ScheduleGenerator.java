import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ScheduleGenerator {



    private ArrayList<ScheduleGenerator>classesCopy = new ArrayList<>(); //holds copy of classes inputted into ClassList
    private ArrayList<ScheduleGenerator> picked = new ArrayList<>();//holds randomly picked classes

    private String classId;
    private String className;
    private String days;
    private String startTime;
    private String endTime;
    private int fileFoundcount = 1; //used for renaming file if copy is found
    
 
    ScheduleGenerator(){}

    ScheduleGenerator(String classId, String className, String days, String startTime, String endTime){
        this.classId = classId;
        this.className = className;
        this.days =  days;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public void setList(ArrayList<ScheduleGenerator> classList){

           classesCopy = classList; 
        
    }

    public void displayAllCourses(){
               System.out.println(String.format("%50s", "----Courses----\n"));
               //TODO fix formatting
               
                
                System.out.println(String.format("%-15s" +"%-28s"+ "%-15s"+"%-15s" + "%-15s", "ID","Name","Days", "Start", "End"));
            for(ScheduleGenerator g: classesCopy){
                 System.out.println("----------------------------------------------------------------------------------------------");
                 System.out.println(String.format("%-15s" +"%-28s"+ "%-12s"+"%10s" + "%15s", g.classId, g.className,g.days, g.startTime,g.endTime) );
                 
            }
            System.out.println("");
        
    }
    

    public int numberOfClasses(){
     
          //makes a count of unique classes in list

        Set<String> uniqueClasses = new HashSet<>();

        for(ScheduleGenerator c: classesCopy){
            uniqueClasses.add(c.classId); //adds from classesCopy but no duplicates
        }

        if(uniqueClasses.size() > 8){ return 8;} //set limit to 8 courses for students

        return uniqueClasses.size();
     }


    //use size to pick size amount of unique classes from entire list
    /*
     *  1. picks from class one use code
     *  2. picks days/times based of course code
     *  3. repeats for next three classes
     *       - check days & times for matches
     *       - if match pull next
     */


    public  void  schedGen(){

                
            int size = numberOfClasses();
                
            int randNum = (int)(Math.random()*(classesCopy.size()));

            int count1 = 0;

           while(count1 < size){

                        if(!classIdFound(classesCopy.get(randNum).classId) && !startTimeFound(classesCopy.get(randNum).startTime)){
                              picked.add(classesCopy.get(randNum));
                              
                              count1++;


                        }
                        randNum = (int)(Math.random()*(classesCopy.size()));
                      
           }

                  picked.sort((d1,d2) -> d1.days.compareTo(d2.days)) ;
                    
                   
                    //Prints out schedule
                    System.out.println(String.format("%50s", "----Schedule----\n"));
                    System.out.println(String.format("%-15s" +"%-28s"+ "%-15s"+"%-15s" + "%-15s", "ID","Name","Days", "Start", "End"));
                 
                    for(ScheduleGenerator g: picked){
                      System.out.println("----------------------------------------------------------------------------------------------");
                      System.out.println(String.format("%-15s" +"%-28s"+ "%-12s"+"%10s" + "%15s", g.classId, g.className,g.days, g.startTime,g.endTime) );
                      
                  }
                  System.out.println("");     

      }

    public void saveSchedule(){
      
         
       
        try {
          File newFile = new File("MyClassSchedule.txt"); // creates file for generated schedule
    
            
                  if(newFile.createNewFile()){

                          writeFile(newFile);
                    
                  }

                  else{

                      //if file is already created
                      String directory = System.getProperty("user.dir"); //pulls current directory
                      File directoryFile = new File(directory);

                      String []fileList = directoryFile.list(); //makes list of directory items

                      String filename = "MyClassSchedule";

                      for(String file: fileList){
                          if(file.equalsIgnoreCase(filename+".txt")){ //if file is found add rename with count
                                
                             newFile = new File(filename+fileFoundcount+".txt");
                              fileFoundcount++;
                          }

                          if(file.equalsIgnoreCase(filename+fileFoundcount+".txt")){

                              fileFoundcount++;
                              newFile = new File(filename+fileFoundcount+".txt");



                          }
                          
                             
                           

                         
                      }

                      if(newFile.createNewFile()){
                          writeFile(newFile);
                      }
                       
                        
                  }
           
      
               } catch (Exception e) {
                   
                   System.out.println("An error occurred.");
                   e.printStackTrace();
               }

     }

     public void writeFile(File filename){ //writes a new file for generated schedule

                try {
                  FileWriter writer = new FileWriter(filename);

                  writer.write(String.format("%50s", "----Schedule----\n"));
                  writer.write(String.format("%-15s" +"%-28s"+ "%-15s"+"%-15s" + "%-15s", "ID","Name","Days", "Start", "End"));
                  writer.write("\n");

                  for(ScheduleGenerator s: picked){
                    writer.write("----------------------------------------------------------------------------------------------\n");
                    writer.write(String.format("%-15s" +"%-28s"+ "%-12s"+"%10s" + "%15s", s.classId, s.className,s.days, s.startTime,s.endTime) );//writes picked schedule into new file
                    writer.write("\n");
                      
                  }

                  writer.close();
                  System.out.print("Schedule saved.");
                  
                } catch (Exception e) {
                  
                  System.out.println("An error occurred.");
                  e.printStackTrace();
                }


     }

    
    public boolean classIdFound(String item){
                //checks for duplicate class id
                for(ScheduleGenerator c: picked){
                        if(c.classId.equals(item)){
                          return true;
                        }
                }

              return false;
    }  
       
       
    public boolean startTimeFound(String item){
              //checks for duplicate start time
              for(ScheduleGenerator c: picked){
                      if(c.startTime.equals(item)){
                        return true;
                      }
              }

            return false;
    }  


     public void emptyPicked(){ //empties picked for regeneration
        picked.clear();
     }

 }
