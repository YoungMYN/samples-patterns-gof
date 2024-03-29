package behavioral.command.concreteCommands;

import behavioral.command.Command;
import behavioral.command.Computer;

public class PrintHelloWorld implements Command {
    private Computer computer;
    public PrintHelloWorld(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.printHelloWorld();
    }

    @Override
    public void undo() {
        if(!computer.getDisplayLine().contains(computer.helloWorld)){
            throw new RuntimeException("can't run undo, because behavioral.command wasn't executed");
        }
        String newLine = computer.getDisplayLine().replace(computer.helloWorld, "");
        computer.setDisplayLine(newLine);
    }
}
