import java.util.HashMap;
public class SymbolTable
{
    private HashMap<String, Integer> myMap;
    public SymbolTable(){
        myMap = new HashMap<>();
    }

    public void addEntry(String symbol, int address){
        myMap.put(symbol, address);
    }

    public boolean contains(String symbol){
        return myMap.containsKey(symbol);
    }

    public int GetAddress(String symbol){
        return myMap.get(symbol);
    }
}