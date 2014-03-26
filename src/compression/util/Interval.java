package compression.util;

public class Interval
{
    private long start;
    private long length;

    public Interval ( long start, long length )
    {
        this.start = start;
        this.length = length;
    }

    public long start()
    {
        return start;
    }
    public long end()
    {
        return start + length;
    }
    public long length()
    {
        return length;
    }

    @Override
    public String toString()
    {
        return "|" + start + ", " + length + ">";
    }
}
