package 英汉词典;

import java.util.List;

public class Word {

    public String English;  //英文
    public String phoneticSymbol;  //音标
    public List<String> EnglishMeaning;  //英文释义
    public List<String> ChineseMeaning;  //中文释义
    public String WordPosition;          //词语位置
    public int KeSiLinStar;              //科斯林星级
    public boolean isOxford;             //是否为牛津三千核心词

    //zk/中考  gk/高考  cet4/四级等
    public String tag;

    public List<WordChange> Changes;
    public String detail;


    @Override
    public String toString() {
        return "{\n" +
                "英文: " + English + "\n" +
                "音标: " + phoneticSymbol + "\n" +
                "英文释义: " + ChangeToText(EnglishMeaning, "\n") + "\n" +
                "中文释义: " + ChangeToText(ChineseMeaning, "\n") + "\n" +
                "词语位置: " + WordPosition + "\n" +
                "柯林斯星级: " + KeSiLinStar + "\n" +
                "为牛津三千核心词: " + isOxford + "\n" +
                "标签: " + tag + "\n" +
                "变形: " + ChangeToText(Changes, "; ") + "\n" +
                "详细: " + detail + "\n" +
                "}";
    }


    private String ChangeToText(List<?> Changes, String divide) {
        String out = "";
        for (Object change : Changes) {
            out += change.toString() + divide;
        }
        return out;
    }
}
