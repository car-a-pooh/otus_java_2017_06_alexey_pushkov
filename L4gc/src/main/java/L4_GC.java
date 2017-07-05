import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by carapooh on 26.06.2017.
 */
public class L4_GC {
    public static void main(String[] args) throws IOException {
        List<Double[]> list = new ArrayList<>(100);
        int tail = 0;
        int arrayLength = 10;
        int add = 100, cutFromHead = 50, cutFromTail = 54;
        long startTimeNanos = System.nanoTime();
        long currentTimeNanos;
        long cutOldPeriodNanos = 100_000_000L;
        long cutOldPeriodsDone = 0L;
        long statisticsPeriodNanos = 10_000_000_000L;
        long statisticsPeriodsDone = 0L;
        File inputFile = null;
        File outputFile = null;
        while(true){
            for (int i = 0; i != add; i++) {
                Double[] arr = new Double[arrayLength];
                for (int j = 0; j != arrayLength; j++) {
                    arr[j] = new Double(tail + arrayLength * i + j);
                }
                list.add(arr);
            }
            tail += arrayLength * arrayLength * add;
            for (int i = 0; i != cutFromTail; i++) {
                list.remove(list.size() - 1);
            }
            currentTimeNanos = System.nanoTime();
            if (currentTimeNanos - startTimeNanos > (cutOldPeriodsDone + 1L) * cutOldPeriodNanos){
                cutOldPeriodsDone++;
                for (int i = 0; i != cutFromHead; i++){
                    list.remove(i);
                }
            }
            currentTimeNanos = System.nanoTime();
            if (currentTimeNanos - startTimeNanos > (statisticsPeriodsDone + 1L) * statisticsPeriodNanos){
                statisticsPeriodsDone++;
                if (inputFile == null){
                    inputFile = getGCLogFile();
                    outputFile = new File(inputFile.getName() + ".statistics");
                }
                getGCStatistics(inputFile, outputFile);
            }
        }
    }

    public static File getGCLogFile() throws FileNotFoundException, NumberFormatException {
        File currentDirectory = new File("./");
        File[] files = currentDirectory.listFiles();
        File gcLogFile = null;
        int gcLogFileNumber = -1;
        int previousGCLogFileNumber = -1;
        for (File file : files){
            String filename = file.getName();
            if (filename.startsWith("gc_pid")){
                gcLogFileNumber = Integer.parseInt(String.valueOf(filename.charAt(15)));
            }
            if (gcLogFileNumber > previousGCLogFileNumber){
                gcLogFile = file;
                previousGCLogFileNumber = gcLogFileNumber;
            }
        }
        return gcLogFile;
    }

    public static void getGCStatistics(File input, File output) throws IOException {
        String youngGen = "PSYoungGen";
        String oldGen = "ParOldGen";
        int youngGenCollectionsDone = 0;
        int oldGenCollectionsDone = 0;
        String youngGenTimeSecs = "0.0000000";
        String oldGenTimeSecs = "0.0000000";
        String currentTimeSecs;
        String line;
        try (FileInputStream fis = new FileInputStream(input);
             InputStreamReader isr = new InputStreamReader(fis, Charset.forName("ASCII"));
             BufferedReader br = new BufferedReader(isr);
             FileWriter fw = new FileWriter(output, true);
             BufferedWriter bw = new BufferedWriter(fw)){

            while ((line = br.readLine()) != null){
                boolean containsYoungGen = line.contains(youngGen);
                boolean containsOldGen = line.contains(oldGen);
                if (containsYoungGen || containsOldGen){
                    currentTimeSecs = getTimeSecs(line);
                    if (containsYoungGen) {
                        youngGenTimeSecs = sumStringsAsNumbers(youngGenTimeSecs, currentTimeSecs);
                        youngGenCollectionsDone++;
                    }
                    if (containsOldGen){
                        oldGenTimeSecs = sumStringsAsNumbers(oldGenTimeSecs, currentTimeSecs);
                        oldGenCollectionsDone++;
                    }
                }
            }

            bw.write(new SimpleDateFormat("yyyyMMdd_HHmmss.mmm").format(Calendar.getInstance().getTime()) +"\n");
            bw.write(youngGen + " collections: " + youngGenCollectionsDone +
                    ", total time: " + youngGenTimeSecs + " secs.\n");
            bw.write(oldGen + " collections: " + oldGenCollectionsDone +
                    ", total time: " + oldGenTimeSecs + " secs.\n");
            bw.write("=================================================\n");
        }
    }

    public static String getTimeSecs (String str){
        String[] words = str.split(" ");
        for (int i = 0; i != words.length; i++){
            if (words[i].equals("secs]") && i > 0){
                return words[i - 1];
            }
        }
        return null;
    }

    public static String sumStringsAsNumbers (String num1, String num2) throws NumberFormatException {
        String[] n1 = num1.split("\\.");
        String[] n2 = num2.split("\\.");
        int f = Integer.parseInt(n1[1]) + Integer.parseInt(n2[1]);
        int d = Integer.parseInt(n1[0]) + Integer.parseInt(n2[0]) + f / 10_000_000;
        return d + "." + f % 10_000_000;
    }
}
