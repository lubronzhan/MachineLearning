package hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Btrain {
  public Btrain(){};
  public static void main(String []args){
    // read file
    
    BufferedReader reader = null;
    
    Btrain bt = new Btrain();
    
    Class cls = bt.getClass();
    URL url = cls.getResource("4Cat-Train.labeled");
    System.out.println("Value = " + url);
    
    File file = new File(url.toString().substring(5, url.toString().length()));
    
    try{
      reader = new BufferedReader(new FileReader(file));


      
      
      String tempStr = "";
      String [] lineResult = new String[5];
           
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      List<Integer> solution = new ArrayList<Integer>();
      int bit = 0;


      // find all hypothesis combination
      
      int [] poss = new int[16];
      for(int i = 0; i < 16; i++){
        poss[i] = -1;
      }
      

      while((tempStr = reader.readLine()) != null ){
        String [] rawLine = tempStr.split("\\t");
        
        for(int i = 0; i < rawLine.length; i++){
          String [] colAndValue = rawLine[i].split("\\s");
          lineResult[i] = colAndValue[1];
        }
        List<Integer> instance = new ArrayList<Integer>();
        if(lineResult[0].equals("Male")){
          instance.add(0);
        }else if(lineResult[0].equals("Female")){
          instance.add(1);
        }
        if(lineResult[1].equals("Young")){
          instance.add(0);
        }else if(lineResult[1].equals("Old")){
          instance.add(1);
        }
        if(lineResult[2].equals("Yes")){
          instance.add(0);
        }else if(lineResult[2].equals("No")){
          instance.add(1);
        }
        if(lineResult[3].equals("Yes")){
          instance.add(0);
        }else if(lineResult[3].equals("No")){
          instance.add(1);
        }
        if(lineResult[4].equals("high")){
          instance.add(0);
        }else if(lineResult[4].equals("low")){
          instance.add(1);
        }
        // line ++
        int index = 0;
        for(int i = 3; i >= 0; i--){
          index += (int)Math.pow(2, 3 - i) * instance.get(i); 
        }
//        System.out.println(index);

        
        poss[index] = instance.get(4);
        
        

        
      }
      
      UtilEnum(result, solution, bit, poss);
      
      System.out.println(result.size());
//      /System.out.println(hpsSet.size());
      reader.close();
      
      
      File testFile = new File(args[1]);
      try{
        reader = new BufferedReader(new FileReader(testFile));
        while((tempStr = reader.readLine()) != null ){
          String [] rawLine = tempStr.split("\\t");
          
          for(int i = 0; i < rawLine.length; i++){
            String [] colAndValue = rawLine[i].split("\\s");
            lineResult[i] = colAndValue[1];
          }
        List<Integer> instance = new ArrayList<Integer>();
        if(lineResult[0].equals("Male")){
          instance.add(0);
        }else if(lineResult[0].equals("Female")){
          instance.add(1);
        }
        if(lineResult[1].equals("Young")){
          instance.add(0);
        }else if(lineResult[1].equals("Old")){
          instance.add(1);
        }
        if(lineResult[2].equals("Yes")){
          instance.add(0);
        }else if(lineResult[2].equals("No")){
          instance.add(1);
        }
        if(lineResult[3].equals("Yes")){
          instance.add(0);
        }else if(lineResult[3].equals("No")){
          instance.add(1);
        }

        
        int low = 0;
        int high = 0;
        
        for(List<Integer> list : result){
          int index = 0;
          for(int i = 3; i >= 0; i--){
            index += (int)Math.pow(2, 3 - i) * list.get(i); 
          }
          
          
          if(list.get(index) == 1){
            low++;
          }else if (list.get(index) == 0){
            high++;
          }
        }
        
        System.out.print(high + " " + low);
        System.out.println();
          
        }
        
      }catch(IOException e){
        e.printStackTrace();
      }

      reader.close();

      
    }catch(IOException e){
      e.printStackTrace();
    }
    
    
    
    
    
  }



  private static void UtilEnum(List<List<Integer>> result, List<Integer> solution, int bit, int [] poss) {
    // TODO Auto-generated method stub
    if(bit == 16){
      List<Integer> list = new ArrayList<Integer>(solution);
      result.add(list);
      return;
    }
    if(poss[bit] != -1){
      solution.add(poss[bit]);
      UtilEnum(result, solution, bit + 1, poss);
      solution.remove(solution.size() - 1);
      return;
    }
    
    for(int i = 0; i < 2; i++){
      if(poss[bit] == -1){
        solution.add(i);
      
        UtilEnum(result, solution, bit + 1, poss);
        solution.remove(solution.size() - 1);
      
      }
    }
  }
  

}
