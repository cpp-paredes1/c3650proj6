import java.util.HashMap;
public class SymbolTable
{
    private HashMap<String, Integer> myMap;
    int nextEmpty;
    public SymbolTable(){
        myMap = new HashMap<>();
        // initialize existing symbols
        myMap.put("SP", 0);
        myMap.put("LCL", 1);
        myMap.put("ARG", 2);
        myMap.put("THIS", 3);
        myMap.put("THAT", 4);
        myMap.put("SCREEN", 16384);
        myMap.put("KBD", 24576);
        // R0 - R15
        for(int i = 0; i < 16; i++){
            myMap.put("R" + i, i);
        }
        nextEmpty = 16;
    }

    public void addEntry(String symbol, int index){
        myMap.put(symbol, index);
    }

    public boolean contains(String symbol){
        return myMap.containsKey(symbol);
    }

    public int GetAddress(String symbol){
        // fix label exists but not defined
        if(!contains(symbol)){
            myMap.put(symbol, nextEmpty);
            nextEmpty++;
        }
        return myMap.get(symbol);
    }
}