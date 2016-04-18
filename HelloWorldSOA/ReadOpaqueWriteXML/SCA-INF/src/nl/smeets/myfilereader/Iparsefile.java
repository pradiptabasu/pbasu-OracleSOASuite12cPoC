package nl.smeets.myfilereader;

import java.util.ArrayList;

public interface Iparsefile {
    public ArrayList<ArrayList<String>> parse(byte[] bytes_in,
                                              String separator,
                                              Integer ignorefirstlines,
                                              String ignoreStartsWith);
}
