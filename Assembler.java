import java.io.FileNotFoundException;
public class Assembler
{
    // TODO: check page 124
    public static void main(String[] args) { 
        // usage: java Assembler [filename]
        Parser parser = null;
        try{
            parser = new Parser(args[0]);
        } catch(FileNotFoundException e){
            System.out.println(e);
            return;
        }
        // populate symboltable
        SymbolTable symbolTable = new SymbolTable();

        while(parser.hasMoreCommands()){
            String type = parser.commandType();
            System.out.println(type + " | " + parser.getCommand());
            if(type.equals("L_COMMAND") ||
            type.equals("A_COMMAND")){
                System.out.println(parser.symbol());
            }
            parser.advance();
        }
    }
}