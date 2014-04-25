package compression.util.arithmetic;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Stores the probability of each character of the message.
 * @author Eric Dilmore (geppettodivacin)
 */
public class CharMap extends HashMap<Character, Integer> implements java.io.Serializable
{
    public CharMap()
    {
        super();
    }

    /**
     * Adds one to the probability of seeing the character.
     * @param c     the character to add to the probability
     * @return      the current probability of the incremented character
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
     * Get the character's cumulative probability shifted for the accumulated
     * probability.
     * @param c     the character to get information for
     * @return      the cumulative probability prior to this character
     */
    @Override
    public Integer get ( Object c )
    {
        return getPosition ( c );
    }

    /**
     * Get the character's cumulative probability shifted for the accumulated
     * probability.
     * @param c     the character to get information for
     * @return      the cumulative probability prior to this character
     */
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

    /**
     * Get the character's probability shifted for the accumulated probability.
     * @param c     the character to get information for
     * @return      the simple probability prior to this character
     */
    public Integer getProbability ( Object c )
    {
        if ( !containsKey ( c ) )
        {
            return null;
        }
        return super.get ( c );
    }

    /**
     * Retrieve the set of Entries from a CharMap.
     * @return  the set of Entries
     */
    @Override
    public Set<Map.Entry<Character, Integer>> entrySet()
    {
        return entriesSortedByValue();
    }

    /**
     * Sorts the entries by value as opposed to key.
     * @return  a set of Entries sorted by value
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
                        int compared;
                        compared = e1.getValue().compareTo ( e2.getValue() );
                        if ( compared == 0 )
                        {
                            return 1;
                        }
                        return compared;
                    }
                }
            );
        sortedEntries.addAll ( super.entrySet() );
        return sortedEntries;
    }
}
