package compression.util;

public class SubInterval
{
    private double start;
    private double length;

    public SubInterval ( double start, double length )
    {
        this.start = start;
        this.length = length;
    }

    public double start()
    {
        return start;
    }
    public double end()
    {
        return start + length();
    }
    public double length()
    {
        return length;
    }
}
