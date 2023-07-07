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
               System.out.println("----Courses----\n");
                System.out.println("ID"+"\t"+"Name"+"\t"+"Days"+"\t"+ "Start Time"+ "\t" +"End Time\n");
            for(ScheduleGenerator g: classesCopy){
                 System.out.println(g.classId+"\t" +g.className+"\t"+g.days+"\t"+ g.startTime+ "\t" +g.endTime);
                 
            }
        
    }
    

    public int numberOfClasses(){
     
          //makes a count of unique classes in list

        Set<String> mainClass = new HashSet<>();

        for(ScheduleGenerator c: classesCopy){
            mainClass.add(c.classId); //adds from classesCopy but no duplicates
        }

        return mainClass.size();
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
                    
                    int count = 1;
                    //Prints out schedule
                    System.out.println("----Schedule----\n");
                    for(ScheduleGenerator item: picked){
                           
                            System.out.println("Class " + count + ": "+ item.classId + " " + item.className + " " + item.days + " " + item.startTime + " " + item.endTime);
                            count++;
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

     public void emptyPicked(){
        picked.clear();
     }

    }
