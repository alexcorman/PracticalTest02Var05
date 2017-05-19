package ro.pub.cs.systems.eim.practicaltest02var05;


public class TimeInformation
{
    private String value;
    private int minute;

    public TimeInformation()
    {
        this.value = null;
        this.minute = 0;

    }

    public TimeInformation(String value, int minute)
    {
        this.value = value;
        this.minute = minute;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value= value;
    }

    public int getMinute()
    {
        return minute;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }


    @Override
    public String toString() {
        return minute + "";
    }
}
