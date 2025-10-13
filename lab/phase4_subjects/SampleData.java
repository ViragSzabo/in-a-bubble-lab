package phase4_subjects;

public class SampleData implements Comparable<SampleData> {
    private final String value;

    public SampleData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(SampleData o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return value;
    }
}