package nl.smeets.myfilereader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

public class parsefile implements Iparsefile {
    public parsefile() {
        super();
    }

    public ArrayList<ArrayList<String>> parse(byte[] bytes_in,
                                              String separator,
                                              Integer ignorefirstlines,
                                              String ignoreStartsWith) {
        ArrayList<ArrayList<String>> l_retval =
            new ArrayList<ArrayList<String>>();
        ByteArrayInputStream ba_in = new ByteArrayInputStream(bytes_in);
        BufferedReader br_in =
            new BufferedReader(new InputStreamReader(ba_in));
        Integer linecounter = 0;
        String line = null;
        try {
            while ((line = br_in.readLine()) != null) {
                //debug("verwerken regel: "+line);
                if (!line.startsWith(ignoreStartsWith) &&
                    linecounter >= ignorefirstlines) {
                    //debug("parsen regel: "+line);
                    l_retval.add(parseLine(line, separator));
                }
                linecounter++;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedReader
            try {
                if (br_in != null)
                    br_in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return l_retval;
    }

    ArrayList<String> parseLine(String input, String separator) {
        ArrayList<String> retval = new ArrayList<String>();
        String[] splitVals = input.split(separator);
        for (String addVal : splitVals) {
            retval.add(addVal);
        }
        return retval;
    }

    public static void main(String[] args) {
        //test; skips first line and all lines starting with blaaa. uses ; as separator
        //parsefile parsefile = new parsefile();
        //System.out.println(parsefile.parse("h1;h2;h3;h4\ne1;f1;g1;h1\ne2;f2;g2;h2\nTotal: 1".getBytes(), ";", 1, "Total"));
    }
}