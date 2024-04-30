package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gameplayparts.Card;
import gameplayparts.Deck;

public class main {
	static HashMap<String, Deck> decks = new HashMap<>();

	static Random rand = new Random();

	static Deck event_deck;
	static Deck search_deck;
	static Deck resource_deck;
	static Deck item_deck;
	static Deck hostile_deck;

	static String event_deck_name = "events";
	static String item_deck_name = "items";
	static String resouce_deck_name = "resources";
	static String hostile_deck_name = "hostile";
	static String search_deck_name = "search";

	static {
		ArrayList<Card> event_cards = new ArrayList<>();
		event_cards.add(new Card("Bandits Attack", "The player that triggered the card takes one damamge", "damage 1"));
		event_cards.add(new Card("Survivours Need Aid", "Player can give medkit for 3 random resources"));
		event_cards.add(new Card("Wolves Attack", ""));
		event_cards.add(new Card("Food Supply", "Gain a random amount of food", "roll 3"));
		event_cards.add(new Card("Water Supply", "Gain a random amount of Water", "roll 3"));
		event_cards.add(new Card("Metal Supply", "Gain a random amount of Metal", "roll 3"));
		event_cards.add(new Card("Item Found", "Gain a random amount item"));
		event_deck = new Deck(event_deck_name, event_cards);
	}

	static {
		ArrayList<Card> item_cards = new ArrayList<>();
		item_cards.add(new Card("Hammer", "Reduces cost of buildings by 1 wood and stone"));
		item_cards.add(new Card("Gun", "Can be used to kill an additional hostile or to counter hostile events at a location"));
		item_cards.add(new Card("Axe", "Grants extra wood from gathering wood"));

		item_deck = new Deck(item_deck_name, item_cards);
	}

	static {
		ArrayList<Card> search_cards = new ArrayList<>();
		search_cards.add(new Card("Resource", "Draw from the resource deck", "draw resources"));
		search_cards.add(new Card("Special", "Draw from the locations unquie deck", "draw items"));
		search_cards.add(new Card("Event", "Draw from the event deck", "draw events"));
		search_cards.add(new Card("Item", "Draw from the items deck", "draw items"));

		search_deck = new Deck(search_deck_name, search_cards);
	}

	static {
		ArrayList<Card> resource_cards = new ArrayList<>();
		resource_cards.add(new Card("Wood", ""));
		resource_cards.add(new Card("Stone", ""));
		resource_cards.add(new Card("Metal", ""));

		resource_deck = new Deck(resouce_deck_name, resource_cards);
	}

	public static void main(String[] args) {
		decks.put(event_deck_name, event_deck);
		decks.put(item_deck_name, item_deck);
		decks.put(resouce_deck_name, resource_deck);
		decks.put(search_deck_name, search_deck);

		try (Scanner scanner = new Scanner(System.in)) {

			while (true) {
				command(scanner.nextLine());
			}
		}
	}

	private static void command(String command) {
		command = command.toLowerCase();

		List<String> commands = Arrays.asList(command.split(" "));

		String primary_command = commands.get(0);

		if (primary_command.equals("x")) {
			System.exit(0);
		} else if (primary_command.startsWith("draw")) {
			Draw(commands);
		} else if (primary_command.startsWith("roll")) {
			Roll(commands);
		} else if (primary_command.startsWith("damage")) {
			Damage(commands);
		}
	}

	private static int Roll(int max) {
		return rand.nextInt(max - 1) + 1;
	}

	private static void Damage(List<String> commands) {
		Integer damage = 1;
		int damage_max_index = 1;

		if (commands.size() == 2) {
			damage = Integer.valueOf(commands.get(damage_max_index));
		}
		// Deal damage to player
		System.out.println("Player took " + damage + " damage");
	}

	private static void Roll(List<String> commands) {
		Integer max = 6;
		int roll_max_index = 1;

		if (commands.size() == 2) {
			max = Integer.valueOf(commands.get(roll_max_index));
		}

		System.out.println("Rolled " + Roll(max));
	}

	private static void Draw(List<String> commands) {
		int deck_index = 1;
		int amount_index = 2;

		if (commands.size() >= 2) {
			String deck_name = commands.get(deck_index);

			if (decks.containsKey(deck_name)) {
				Integer amount = 1;
				Deck deck_to_draw_from = decks.get(deck_name);

				if (commands.size() == 3) {
					amount = Integer.valueOf(commands.get(amount_index));
				}

				for (int i = 0; i < amount; i++) {
					Card drawn_card = deck_to_draw_from.Draw();
					System.out.println(drawn_card.getName());
					System.out.println(drawn_card.getDescription());

					if (!drawn_card.getCommand().isEmpty()) {
						command(drawn_card.getCommand());
					}
				}
			} else {
				System.err.println("Deck of name " + deck_name + " not found");
			}
		} else {
			System.err.println("No deck name provided");
			String deck_list = decks.keySet().toString().replace("[", "").replace("]", "");

			System.out.println("Pick from " + deck_list);
		}

	}
}