import java.util.*;

interface Command {
    void runCommand();
}

public class Test {

    public static void main(String[] args) throws Exception {
        Map<Character, Command> methodMap = new HashMap<Character, Command>();

        methodMap.put('h', new Command() {
            public void runCommand() { System.out.println("help"); };
        });

        methodMap.put('t', new Command() {
            public void runCommand() { System.out.println("teleport"); };
        });

        char cmd = 'h';
        methodMap.get(cmd).runCommand();  // prints "Help"

        cmd = 't';
        methodMap.get(cmd).runCommand();  // prints "teleport"

        ////////////////////////////////////////////////////////////////////////
        Map<Integer, Command> methodMapInt = new HashMap<Integer, Command>();

        methodMapInt.put(1, new Command() {
            public void runCommand() { System.out.println("help"); };
        });

        methodMapInt.put(2, new Command() {
            public void runCommand() { System.out.println("teleport"); };
        });

        int cmdInt = 1;
        methodMapInt.get(cmdInt).runCommand();  // prints "Help"

        cmdInt= 2;
        methodMapInt.get(cmdInt).runCommand();  // prints "teleport"
        
        
    }
}