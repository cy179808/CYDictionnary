package 英汉词典;

import java.util.HashMap;

public enum WordChangeType {

    过去式("p"), // past tense
    过去分词("d"),
    现在分词("i"), // -ing
    第三人称单数("3"),
    形容词比较级("r"), // -er
    形容词最高级("t"), // -est
    名词复数形式("s"),
    原型("0"),
    原型变换形式("1");

    private String abbreviation;//缩写

    WordChangeType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    private static final HashMap<String, WordChangeType> FieldValueConversionTable = new HashMap<String, WordChangeType>();

    static {
        for (WordChangeType type : WordChangeType.values()) {
            FieldValueConversionTable.put(type.abbreviation, type);
        }
    }

    public static WordChangeType getType(String abbreviation) {
        return FieldValueConversionTable.get(abbreviation);
    }
}
