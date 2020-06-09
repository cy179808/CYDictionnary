package 英汉词典.Tools;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonDivide {

    private static final int lineNumber = 50000;

    public static void MakeJson() {
        try {
            FileReader fileReader = new FileReader("中文释义.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            List<String> FiftyThousandLines = new ArrayList<String>();

            int CurrentLine = 0;
            int FileNumber = 0;

            //跳过第一行
            line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                if (CurrentLine < lineNumber) {
                    FiftyThousandLines.add(line);
                    CurrentLine++;
                } else {
                    WriteFile(FileNumber, FiftyThousandLines, true);
                    FileNumber++;
                    FiftyThousandLines.clear();
                    CurrentLine = 0;
                }
            }

            //删除最后一行:}
            FiftyThousandLines.remove(FiftyThousandLines.size() - 1);
            WriteFile(FileNumber, FiftyThousandLines, false);
            fileReader.close();
            System.out.println("finish");

        } catch (FileNotFoundException e) {
            getChineseToJson.MakeJson();
            JsonDivide.MakeJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void WriteFile(int FileNumber, List<String> FiftyThousandLines, boolean deleteComma) {
        try {
            FileWriter fileWriter = new FileWriter("词典" + FileNumber + ".json", false);
            fileWriter.write("{\n");

            for (int i = 0; i < FiftyThousandLines.size(); i++) {
                String line = FiftyThousandLines.get(i);
                if (deleteComma && i == FiftyThousandLines.size() - 1) {
                    fileWriter.write(line.substring(0, line.length() - 1) + "\n");
                } else {
                    fileWriter.write(line + "\n");
                }
            }
            fileWriter.write("}");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
