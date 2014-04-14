package compression.util.arithmetic;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Stores the probability of each character of the message.
 */
public class CharMap extends HashMap<Character, Long>
{
    public CharMap()
    {
        super();
    }

    /**
     * Adds one to the probability of seeing the character.
     */
    public Long addTo ( Character c )
    {
        if ( containsKey ( c ) )
        {
            return put ( c, super.get ( c ) + 1 );
        } else
        {
            return put ( c, 1L );
        }
    }

    /**
     * Get the character's probability shifted for the accumulated probability.
     */
    @Override
    public Long get ( Object c )
    {
        return getPosition ( c );
    }

    public Long getPosition ( Object c )
    {
        long sum = 0;
        if ( !containsKey ( c ) )
        {
            return null;
        }

        for ( Map.Entry<Character, Long> e : entrySet() )
        {
            if ( c == e.getKey() )
            {
                break;
            }
            sum += e.getValue();
        }
        return Long.valueOf ( sum );
    }

    public Long getProbability ( Object c )
    {
        if ( !containsKey ( c ) )
        {
            return null;
        }
        return super.get ( c );
    }

    @Override
    public Set<Map.Entry<Character, Long>> entrySet()
    {
        return entriesSortedByValue();
    }

    /**
     * Sorts the entries by value as opposed to key.
     */
    private SortedSet<Map.Entry<Character, Long>> entriesSortedByValue()
    {
        SortedSet<Map.Entry<Character, Long>> sortedEntries =
            new TreeSet<Map.Entry<Character, Long>>
            (
                new Comparator<Map.Entry<Character, Long>>()
                {
                    @Override
                    public int compare ( Map.Entry<Character, Long> e1,
                                         Map.Entry<Character, Long> e2 )
                    {
                        return e2.getValue().compareTo ( e1.getValue() );
                    }
                }
            );
        sortedEntries.addAll ( super.entrySet() );
        return sortedEntries;
    }
}
