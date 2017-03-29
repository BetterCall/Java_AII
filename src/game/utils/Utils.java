package game.utils;

import game.map.Map;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Franck on 19/03/2017.
 **/
public class Utils {

    public static String loadFileAsString(String path)  {

        StringBuilder builder = new StringBuilder() ;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line ;

            while( (line = br.readLine()) != null) {
                builder.append(line + "\n") ;
            }

            br.close() ;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }finally {

            return builder.toString() ;
        }
    }


    /**
     *
     * @param number number in string format
     * @return number as an int
     */
    public static int parseInt(String number){

        try {
            return Integer.parseInt(number) ;

        }catch (NumberFormatException e) {
            e.printStackTrace();
            return 0 ;
        }

    }
}
