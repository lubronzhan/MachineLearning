package hw1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * Spliting words
 * 
 * @author lubron
 *
 */
public class question1 {
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
//        System.out.print("Empty input file");
//        return;
//      }
      
      String [] wordArr = sb.toString().toLowerCase().split("\\s+");
      
      Arrays.sort(wordArr);
      
      Set<String> dict = new HashSet<String>();
      for(String str : wordArr){
        if(!dict.contains(str))
          dict.add(str);
      }
      
      
      StringBuilder resultStr = new StringBuilder();
      
      
      for(String distinctStr : wordArr){
        if(dict.contains(distinctStr)){
          resultStr.append(distinctStr);
          resultStr.append(",");
          dict.remove(distinctStr);
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
