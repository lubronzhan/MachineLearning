package hw2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class partA {
  public partA(){};
  public static void main(String [] args){
    // 1. Prints (to stdout), by itself on the first line, 
    // the size of the input space (number of possible unique inputs).
    System.out.println((int)Math.pow(2, 9));
    
    // 2. Prints, by itself on the next line, the number of decimal digits in |C|.
    String str= "13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096";
    
    System.out.println(str.length());
    
    // 3. the size of the hypothesis space H
    System.out.println((int)Math.pow(3, 9) + 1);
    
    // read file
    
    BufferedReader reader = null;
    
    partA bt = new partA();
    
    Class cls = bt.getClass();
    URL url = cls.getResource("9Cat-Train.labeled");
    File file = new File(url.toString().substring(5, url.toString().length()));
    // write file
    File outputFile = new File("partA4.txt");
    FileWriter fw; 
    BufferedWriter bw;
    if(!outputFile.exists()){
      // if file not exist
      try {
        outputFile.createNewFile();
        
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    String [] currentResult = new String[10];
    for(int i = 0; i < 9; i++){
      currentResult[i] = "NULL";
    }
    currentResult[9] = "high";
    
    try{
      reader = new BufferedReader(new FileReader(file));
      fw = new FileWriter(outputFile.getAbsolutePath());
      bw = new BufferedWriter(fw);
      
      
      String tempStr = "";
      int lineCurrentTotal = 0;
      

      String [] lineResult = new String[10];
      
      while((tempStr = reader.readLine()) != null ){
        String [] rawLine = tempStr.split("\\t");
        
        for(int i = 0; i < rawLine.length; i++){
          String [] colAndValue = rawLine[i].split("\\s");
          lineResult[i] = colAndValue[1];
        }
        
        // line ++
        lineCurrentTotal++;
        
        // check negative instance
        if(lineResult[9].equals("low")){
          if(lineCurrentTotal % 30 == 0){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < currentResult.length - 2; i++){
              sb.append(currentResult[i]);
              sb.append("\t");
            }
            sb.append(currentResult[8]);
            bw.write(sb.toString() + "\n");
          }
          continue;
        }
        
        // get current result
        currentResult = findS(currentResult, lineResult); 
        // if 30, output current result
        if(lineCurrentTotal % 30 == 0){
          StringBuilder sb = new StringBuilder();
          for(int i = 0; i < currentResult.length - 2; i++){
            sb.append(currentResult[i]);
            sb.append("\t");
          }
          sb.append(currentResult[8]);
          bw.write(sb.toString() + "\n");
          //System.out.println(sb.toString());
        }
        
      }
      
      
      
      reader.close();
      bw.close();
      
    }catch(IOException e){
      e.printStackTrace();
    }
    
    
    // 5. printsalinewiththemisclassi- fication rate
    File practiceFile = new File(args[0]);
    
    try{
      reader = new BufferedReader(new FileReader(practiceFile));
      String tempStr;
      double right = 0.0;
      double wrong = 0.0;
      // prediction is false or true
      boolean fact = true;
      // predict it is true or false
      boolean expected = true;
      while((tempStr = reader.readLine()) != null ){
        String [] lineResult = new String[10];
        String [] rawLine = tempStr.split("\\t");
        
        for(int i = 0; i < rawLine.length; i++){
          String [] colAndValue = rawLine[i].split("\\s");
          lineResult[i] = colAndValue[1];
        }    

        
        for(int i = 0; i < 9; i++){
          if(!currentResult[i].equals("?")){
            if(!currentResult[i].equals(lineResult[i])){
              if(currentResult[9].equals(lineResult[9])){
                expected = true;
                fact = false;
                break;
              }else{
                expected = false;
              }
            }
          }
        }
        if(fact == true){
          if(lineResult[9].equals("low") && expected == true){
            wrong++;
          }else
            right++;
          expected = true;
        }else{
          wrong++;
          fact = true;
        }
      }
      
      double misRate = wrong / (right + wrong);
      System.out.println(misRate);
      

      reader.close();
      
    }catch(IOException e){
      e.printStackTrace();
    }
    
    // 6 . predict
    try{
      reader = new BufferedReader(new FileReader(practiceFile));
      String tempStr;

      // prediction is false or true
      boolean fact = true;
      // predict it is true or false
      
      while((tempStr = reader.readLine()) != null ){
        boolean expected = true;
        String [] lineResult = new String[10];
        String [] rawLine = tempStr.split("\\t");
        
        for(int i = 0; i < rawLine.length; i++){
          String [] colAndValue = rawLine[i].split("\\s");
          lineResult[i] = colAndValue[1];
        }   
        
        for(int i = 0; i < 9; i++){
          if(!currentResult[i].equals("?")){
            if(!currentResult[i].equals(lineResult[i])){
              expected = false;
            }
          }
        }
        if(expected){
          System.out.println("high");
        }else{
          System.out.println("low");
        }

      }
      
      reader.close();
      
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  
  public static String[] findS(String [] trainResult, String [] currentLine){
    for(int i = 0; i < trainResult.length - 1; i++){
      if(!trainResult[i].equals("?")){
        if(trainResult[i].equals("NULL")){
          trainResult[i] = currentLine[i];
        }else if(!trainResult[i].equals(currentLine[i])){
          trainResult[i] = "?";
        }
      }
    }
    return trainResult;
    
  }
  
}
