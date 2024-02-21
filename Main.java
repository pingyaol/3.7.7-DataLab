import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;
import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import core.data.*;


// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.chart.BarChart;
// import javafx.scene.chart.CategoryAxis;
// import javafx.scene.chart.NumberAxis;
// import javafx.scene.chart.XYChart;
// import javafx.stage.Stage;

public class Main {

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

    DataSource ds1 = DataSource.connectAs("CSV", "./spotify-2023.csv");
    ds1.load();
    ds1.printUsageString(); 
    String[] streams = ds1.fetchStringArray("streams");
    String[] titles = ds1.fetchStringArray("track_name"); 

    // for (int i = 0; i < titles.length; i++) {
    //   System.out.println(titles[i]);
    // }

    System.out.println("len2: " + streams.length); 
    System.out.println("len3: " + titles.length); 

    // GETTING ARTISTS 
    HashMap<String, Integer> uniqueArtists = new HashMap<String, Integer>(); 
    ArrayList<ArrayList<String>> artists = new ArrayList<ArrayList<String>>(); 

    try(BufferedReader fileReader
            = new BufferedReader(new FileReader("arts.csv")))
    {
      String line = "";

      //Read the file line by line
      while ((line = fileReader.readLine()) != null)
      {
        //Get all tokens available in line
        String[] tokens = line.split(",");
        ArrayList<String> tokensArrList = new ArrayList<String>(Arrays.asList(tokens)); 
        artists.add(tokensArrList); 

        //Verify tokens
        System.out.println(Arrays.toString(tokens));
          for (String artist : tokensArrList) {
          if (uniqueArtists.containsKey(artist)) {
            System.out.println("(Before) Key = " + artist + 
                      ", Value = " + uniqueArtists.get(artist) );
            uniqueArtists.put(artist, uniqueArtists.get(artist) + 1); 
            System.out.println("(After) Key = " + artist + 
                      ", Value = " + uniqueArtists.get(artist) );
          } else {
            uniqueArtists.put(artist, 1); 
          }
        }
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    // CHECKS IF ALL THE ARTISTS ARE MATCHED UP WITH THE RIGHT INFO 
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < artists.size() - 1; j++) {
        System.out.print(artists.get(j).toString()); 
        System.out.print(" TITLE: "); 
        System.out.print(titles[j]); 
        System.out.print(" STREAMS: "); 
        System.out.println(streams[j]); 
      }
    }


  }
}

