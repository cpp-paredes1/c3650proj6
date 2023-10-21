import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Parser
{
    Scanner s;
    String currentCommand;
    boolean reachedEnd;
    int lineNumber;
    public Parser(File file) throws FileNotFoundException{
        s = new Scanner(file);
        currentCommand = "";
        reachedEnd = false;
        lineNumber = 0;
    }

    public boolean hasMoreCommands(){
        if(s.hasNextLine()){
            return true;
        } else{
            if(reachedEnd){
                return false;
            }
            reachedEnd = true;
        }
        return true;
    }

    public void advance(){
        if(!s.hasNextLine()){
            reachedEnd = true;
            return;
        }
        currentCommand = s.nextLine();
        // skip lines with comments
        if(currentCommand.equals("") || currentCommand.startsWith("//")){
            advance();
            return;
        }
        // remove comments
        if(currentCommand.contains("//")){
            currentCommand = currentCommand.substring(0, currentCommand.indexOf("//"));
        }
        // remove whitespace
        currentCommand = currentCommand.replaceAll("\\s", "");
        currentCommand = currentCommand.replaceAll("\t", "");
        if(!commandType().equals("L_COMMAND")){
            lineNumber++;
        }
    }

    public String commandType(){
        if(currentCommand.contains("@")){
            return "A_COMMAND";
        } else if(currentCommand.contains("(") && currentCommand.contains(")")){
            return "L_COMMAND";
        } else if(currentCommand.contains("=") || currentCommand.contains(";")){
            return "C_COMMAND";
        }
        return "INVALID";
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

    public int getLineNumber(){
        return lineNumber;
    }
}