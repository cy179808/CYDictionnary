package 英汉词典.Tools;

import com.opencsv.*;
import 英汉词典.Word;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getChinese {

    private static final String outFileName = "中文释义.csv";
    private static final InputStream in = getChinese.class.getClassLoader().getResourceAsStream("ecdict.csv");
    private static final List<String[]> allLines = new ArrayList<String[]>();

    protected static Map<String, Word> SearchTable = new HashMap<String, Word>();

    private static CSVReader reader = null;

    public static void MakeCsv() {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        try {
            CSVReaderBuilder builder = new CSVReaderBuilder(new InputStreamReader(in)).withCSVParser(rfc4180Parser);
            CSVWriter writer = new CSVWriter(new FileWriter(Paths.get(outFileName).toString()));
            reader = builder.build();

            //跳过第一行
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                allLines.add(line);
                String English = line[0];
                String ChineseMeaning = line[3];
                writer.writeNext(new String[]{English, ChineseMeaning});
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
