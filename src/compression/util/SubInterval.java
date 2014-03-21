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

    public Interval subOf ( Interval interval )
    {
        return new Interval ( ( int ) Math.ceil ( ( interval.end() - interval.start() ) * start ) + interval.start(),
                ( int ) Math.ceil ( interval.length() * length ) );
    }
}
