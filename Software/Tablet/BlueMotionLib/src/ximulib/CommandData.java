package ximulib;

public class CommandData extends xIMUdata {
	private CommandCodes commandCode;

	public CommandCodes getCommandCode() {
		return commandCode;
	}

	public void setCommandCode(CommandCodes commandCode) {
		this.commandCode = commandCode;
	}

	public CommandData(short commandCode) throws Exception
    {
		try{
			this.commandCode = CommandCodes.values()[commandCode];
		}catch(Exception e){
			throw new Exception("Invalid command code.");
		}
    }
}
