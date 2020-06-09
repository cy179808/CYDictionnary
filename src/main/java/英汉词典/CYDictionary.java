package 英汉词典;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class CYDictionary {

    public static final InputStream in = CYDictionary.class.getClassLoader().getResourceAsStream("ecdict.csv");

    private static final List<String[]> AllLines = new ArrayList<String[]>();

    protected static Map<String, Word> SearchTable = new HashMap<String, Word>();

    private static CSVReader reader = null;

    static {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();

        try {
            CSVReaderBuilder builder = new CSVReaderBuilder(new InputStreamReader(in)).withCSVParser(rfc4180Parser);
            reader = builder.build();
            //跳过第一行
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                AllLines.add(line);
                Word word = new Word();
                word.English = line[0];
                word.phoneticSymbol = line[1];
                word.EnglishMeaning = DivideMeanings(line[2]);
                word.ChineseMeaning = DivideMeanings(line[3]);
                word.WordPosition = line[4];
                word.KeSiLinStar = ChangeToInt(line[5]);
                word.isOxford = ChangeToBool(line[6]);
                word.tag = line[7];
                word.Changes = ChangeToWordChange(line[10]);
                word.detail = line[11];

                SearchTable.put(word.English, word);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Word SearchWord(String English) {
        return SearchTable.get(English);
    }

    public static List<String[]> AllOriginalLines() {
        return AllLines;
    }

    private static List<String> DivideMeanings(String origin) {
        if (origin.isEmpty()) {
            return new ArrayList<String>();
        }
        return Arrays.asList(origin.split("\\\\n"));
    }

    private static List<WordChange> ChangeToWordChange(String origin) {
        List<WordChange> changes = new ArrayList<WordChange>();
        if (origin.isEmpty()) {
            return changes;
        }
        String[] morphologySegment = origin.split("/");
        for (String s : morphologySegment) {
            String[] ss = s.split(":");
            changes.add(new WordChange(WordChangeType.getType(ss[0]), ss.length == 1 ? "" : ss[1]));
        }
        return changes;
    }

    private static boolean ChangeToBool(String num) {
        return num.equals("1");
    }

    private static int ChangeToInt(String numString) {
        return numString.isEmpty() ? 0 : Integer.parseInt(numString);
    }
}
