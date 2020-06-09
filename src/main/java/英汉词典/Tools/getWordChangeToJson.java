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

public class getWordChangeToJson {

    private static final String outFileName = "词形变化.json";
    private static final InputStream in =
            getWordChangeToJson.class.getClassLoader().getResourceAsStream("ecdict.csv");
    private static final List<String[]> allLines = new ArrayList<>();

    protected static Map<String, Word> 查词表 = new HashMap<>();

    private static CSVReader reader = null;

    public static void MakeJson() {
        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();

        try {
            CSVReaderBuilder csvReaderBuilder =
                    new CSVReaderBuilder(new InputStreamReader(in)).withCSVParser(rfc4180Parser);

            CSVWriter writer = new CSVWriter(new FileWriter(Paths.get(outFileName).toString()),
                    ':',
                    '"',
                    '\\',
                    ",\n");

            reader = csvReaderBuilder.build();

            // 跳过第一行(头)
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                allLines.add(line);
                String English = line[0];
                String WordChange = line[10];
                if (WordChange.isEmpty()) {
                    continue;
                }

                writer.writeNext(new String[]{English, WordChange});
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
