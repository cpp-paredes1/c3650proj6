import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Parser
{
    Scanner s;
    String currentCommand;
    public Parser(String filename) throws FileNotFoundException{
        s = new Scanner(new File(filename));
        currentCommand = "";
    }

    public boolean hasMoreCommands(){
        return s.hasNextLine();
    }

    public void advance(){
        currentCommand = s.nextLine();
        if(currentCommand.equals("") || currentCommand.startsWith("//")){
            advance();
        }
        if(currentCommand.contains("//")){
            currentCommand = currentCommand.substring(0, currentCommand.indexOf("//"));
        }
    }

    public String commandType(){
        if(currentCommand.contains("@")){
            return "A_COMMAND";
        } else if(currentCommand.contains("(") && currentCommand.contains(")")){
            return "L_COMMAND";
        }
        return "C_COMMAND";
    }

    public String symbol(){
        if(currentCommand.contains("@")){
            return currentCommand.substring(currentCommand.indexOf("@") + 1);
        } else if(currentCommand.contains("(") && currentCommand.contains(")")){
            return currentCommand.substring(
                currentCommand.indexOf("(") + 1,
                currentCommand.indexOf(")"));
        }
        return "";
    }

    public String getCommand(){
        return currentCommand;
    }
}