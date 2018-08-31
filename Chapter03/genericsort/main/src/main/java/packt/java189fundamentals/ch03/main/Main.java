// START SNIPPET sort_Main_class
package packt.java189fundamentals.ch03.main;

// SNIPPET SKIP TILL "^//"

import packt.java189fundamentals.ch03.quick.FQuickSort;
import packt.java189fundamentals.ch03.support.ArraySwapper;
import packt.java189fundamentals.ch03.support.ArrayWrapper;

import java.io.*;
import java.util.LinkedList;

// ... imports are deleted from print ...

public class Main {
    public static void main(String[] args) throws IOException {
        final var fileName = args[0];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
            final var lines = new LinkedList<String>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            String[] lineArray = lines.toArray(new String[0]);
            var sort = new FQuickSort<String>();
            sort.setComparator((a, b) -> ((String) a).compareTo((String) b));
            sort.setSwapper(new ArraySwapper<>(lineArray));
            sort.sort(new ArrayWrapper<>(lineArray));
            for (final String outLine : lineArray) {
                System.out.println(outLine);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}
// END SNIPPET