package command;


public interface Command {
	public abstract void execute();
	public abstract void unexecute();
	public abstract String getCommand();
}
