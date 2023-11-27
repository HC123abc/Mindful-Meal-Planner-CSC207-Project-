package use_case.unfavouriteThis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class UnfavouriteThisInputData {

    private Map<String, Map<String, String>> recipes;

    private Map<String, Map<String, String>> entry;


    public UnfavouriteThisInputData(Map<String, Map<String, String>> recipes, Map<String, Map<String, String>> entry) {
        this.recipes = recipes;
        this.entry = entry;

    }


    public Map<String, Map<String, String>> getRecipes() {
        return recipes;
    }

    public Map<String, Map<String, String>> getEntry() {
        return entry;
    }

    public Map<String, String> getEntryValue() {
        Collection<Map<String, String>> values = entry.values();
        Iterator<Map<String, String>> iter = values.iterator();
        Map<String, String> firstValue = iter.next();
        return firstValue;
    }

    public String getEntryKey() {
        Set<String> keys = entry.keySet();
        Iterator<String> keyIterator = keys.iterator();
        return keyIterator.hasNext() ? keyIterator.next() : null;
    }

}
