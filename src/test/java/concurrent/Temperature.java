package concurrent;

public enum Temperature {
    OVER_100("test","备注",12),
    AAA("AAA","一级棒",11111);

    Temperature(String name, String memo, int value) {
        this.name=name;
        this.value=value;
        this.memo=memo;
    }

    private String name;

    private String memo;

    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
