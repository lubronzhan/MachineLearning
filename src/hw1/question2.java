package hw1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * Counting words
 * 
 * @author lubron
 *
 */
public class question2 {
  public static void main(String [] args){
    File file = new File(args[0]);
    BufferedReader reader = null;
    try{
      reader = new BufferedReader(new FileReader(file));
      StringBuilder sb = new StringBuilder();
      String tempStr = "";
      while((tempStr = reader.readLine()) != null ){
        sb.append(tempStr);
      }
      reader.close();
      
//      if(sb.toString().equals("")){
//        System.out.println("Output : ");
//        return;
//      }
      
      String [] wordArr = sb.toString().toLowerCase().split("\\s+");
      
      Arrays.sort(wordArr);
      
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      for(String str : wordArr){
        if(!map.containsKey(str))
          map.put(str, 1);
        else{
          map.put(str, map.get(str) + 1);
        }
      }
      
      
      StringBuilder resultStr = new StringBuilder();
      
      for(String str : wordArr){
        if(map.containsKey(str)){
          resultStr.append(str);
          resultStr.append(":");
          resultStr.append(map.get(str));
          resultStr.append(",");
          map.remove(str);
        }
      }
      

      
      String result = resultStr.toString();
      result = result.substring(0, result.length() - 1);
      
      System.out.print(result);
      //System.out.print(result);
      //StdOut.print(result);
      
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}
