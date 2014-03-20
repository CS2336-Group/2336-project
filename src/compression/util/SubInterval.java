package compression.util;

class SubInterval
{
    private double start;
    private double length;

    public SubInterval ( int start, int length )
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
        return start + length();
    }
    public int length()
    {
        return length;
    }
}
