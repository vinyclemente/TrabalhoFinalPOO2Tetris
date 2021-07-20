import java.util.ArrayList;

public class Ranking {

    private ReadSequentialFile reader;
    private ArrayList<Object> log;

    public Ranking() {
        reader = new ReadSequentialFile();
        reader.openFile();
        reader.readRecords();
        log = reader.getList();
        reader.closeFile();
    }

    public String getHighest() {

        String highestName = "";
        long highestScore = 0;
        
        for (int i = 1; i < log.size(); i = i + 2) {

            if ( ((long) log.get(i)) > highestScore) {
                highestScore = (long) log.get(i);
                highestName = (String) log.get(i-1);
            }
        }
        String result = highestName + "     " + highestScore;
        
        return result;
    }

    public String getAll() {
        
        String allScores = "";
        String format = String.format("\n", null);

        for (int i = 1; i < log.size(); i = i + 2) {
            allScores = allScores + ( log.get(i-1) + "   " + log.get(i) + format );
        }

        return allScores;
    }
}