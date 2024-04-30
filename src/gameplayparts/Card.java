package gameplayparts;

public class Card {
	private String name = "";
	private String description = "";
	private String command = "";

	public String getCommand() {
		return command;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Card(String new_name, String new_description) {
		name = new_name;
		description = new_description;
	}

	public Card(String new_name, String new_description, String in_command) {
		this(new_name, new_description);
		command = in_command;
	}

}
