import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;


// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.chart.BarChart;
// import javafx.scene.chart.CategoryAxis;
// import javafx.scene.chart.NumberAxis;
// import javafx.scene.chart.XYChart;
// import javafx.stage.Stage;

public class Main2 {

  public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
  {
      // Create a list from elements of HashMap
      List<Map.Entry<String, Integer> > list =
             new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

      // Sort the list
      Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
          public int compare(Map.Entry<String, Integer> o1, 
                             Map.Entry<String, Integer> o2)
          {
              return (o2.getValue()).compareTo(o1.getValue());
          }
      });

      // put data from sorted list to hashmap 
      HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
      for (Map.Entry<String, Integer> artist : list) {
          temp.put(artist.getKey(), artist.getValue());
      }
      return temp;
  }


  public static void main(String[] args) {

    DataSource ds = DataSource.connectAs("CSV", "./artists.csv");
    ds.load();
    ds.printUsageString(); 
    
    String[] h1 = ds.fetchStringArray("artist(s)_name");
    // for (String s : h1) {
    //   System.out.println(s);
    // }

    DataSource ds1 = DataSource.connectAs("CSV", "./spotify-2023.csv");
    ds1.load();
    ds1.printUsageString(); 
    String[] h2 = ds1.fetchStringArray("streams");

    for (int i = 0; i < h2.length; i++) {
      System.out.println(h2[i]);
    }

    System.out.println("len2: " + h2.length); 
    System.out.println("len1: " + h1.length); 
    
    String fileName = "spotify-2023.csv"; 
    File file= new File(fileName);

    // this gives you a 2-dimensional array of strings
    ArrayList<List<String>> lines = new ArrayList<>();
    HashMap<String, Integer> uniqueArtists = new HashMap<String, Integer>();
    Scanner inputStream;

    try(BufferedReader fileReader
            = new BufferedReader(new FileReader(fileName)))
    {
      String line = "";

      //Read the file line by line
      while ((line = fileReader.readLine()) != null)
      {
        //Get all tokens available in line
        String[] tokens = line.split(",");
        ArrayList<String> tokensArrList = new ArrayList<String>(Arrays.asList(tokens)); 

        //Verify tokens
        // System.out.println(Arrays.toString(tokens));
        lines.add(tokensArrList); 
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }


    // read all the artists into a hashmap 
    try(BufferedReader fileReader
            = new BufferedReader(new FileReader("artists.csv")))
    {
      String line = "";

      //Read the file line by line
      while ((line = fileReader.readLine()) != null)
      {
        //Get all tokens available in line
        String[] tokens = line.split(",");
        ArrayList<String> tokensArrList = new ArrayList<String>(Arrays.asList(tokens)); 

        //Verify tokens
        // System.out.println(Arrays.toString(tokens));
        //   for (String artist : tokensArrList) {
        //   if (uniqueArtists.containsKey(artist)) {
        //     System.out.println("(Before) Key = " + artist + 
        //               ", Value = " + uniqueArtists.get(artist) );
        //     uniqueArtists.put(artist, uniqueArtists.get(artist) + 1); 
        //     System.out.println("(After) Key = " + artist + 
        //               ", Value = " + uniqueArtists.get(artist) );
        //   } else {
        //     uniqueArtists.put(artist, 1); 
        //   }
        // }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    // print out artist-count pairs 
    // Map<String, Integer> uniqueArtists1 = sortByValue(uniqueArtists);
    // for (Map.Entry<String, Integer> arts : uniqueArtists1.entrySet()) {
    //     System.out.println("Key = " + arts.getKey() + 
    //                   ", Value = " + arts.getValue());
    // }



  }
}

