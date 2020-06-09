package 英汉词典;

public class WordChange {

    public WordChangeType type;//词形变化类型
    public String morphology;  //词形

    public WordChange(WordChangeType type, String morphology) {
        this.type = type;
        this.morphology = morphology;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof WordChange && this.type.equals(((WordChange) obj).type) && this.morphology.equals
                (((WordChange) obj).morphology);
    }

    @Override
    public String toString() {
        return type.toString() + ":" + morphology;
    }
}
