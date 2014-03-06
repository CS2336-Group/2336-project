public class Interval
{
    private int start;
    private int length;

    public Interval ( int start, int length )
    {
        this.start = start;
        this.length = length;
    }

    public int start()
    {
        return start;
    }
    public int end()
    {
        return start + length;
    }
    public int length()
    {
        return length;
    }
}
