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
public class CharMap extends HashMap<Character, Integer> implements java.io.Serializable
{
    public CharMap()
    {
        super();
    }

    /**
     * Adds one to the probability of seeing the character.
     */
    public Integer addTo ( Character c )
    {
        if ( containsKey ( c ) )
        {
            return put ( c, super.get ( c ) + 1 );
        } else
        {
            return put ( c, 1 );
        }
    }

    /**
     * Get the character's probability shifted for the accumulated probability.
     */
    @Override
    public Integer get ( Object c )
    {
        return getPosition ( c );
    }

    public Integer getPosition ( Object c )
    {
        int sum = 0;
        if ( !containsKey ( c ) )
        {
            return null;
        }

        for ( Map.Entry<Character, Integer> e : entrySet() )
        {
            if ( c == e.getKey() )
            {
                break;
            }
            sum += e.getValue();
        }
        return Integer.valueOf ( sum );
    }

    public Integer getProbability ( Object c )
    {
        if ( !containsKey ( c ) )
        {
            return null;
        }
        return super.get ( c );
    }

    @Override
    public Set<Map.Entry<Character, Integer>> entrySet()
    {
        return entriesSortedByValue();
    }

    /**
     * Sorts the entries by value as opposed to key.
     */
    private SortedSet<Map.Entry<Character, Integer>> entriesSortedByValue()
    {
        SortedSet<Map.Entry<Character, Integer>> sortedEntries =
            new TreeSet<Map.Entry<Character, Integer>>
            (
                new Comparator<Map.Entry<Character, Integer>>()
                {
                    @Override
                    public int compare ( Map.Entry<Character, Integer> e1,
                                         Map.Entry<Character, Integer> e2 )
                    {
                        return e1.getValue().compareTo ( e2.getValue() );
                    }
                }
            );
        sortedEntries.addAll ( super.entrySet() );
        return sortedEntries;
    }
}
