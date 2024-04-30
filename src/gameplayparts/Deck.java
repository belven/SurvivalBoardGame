package gameplayparts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Stack;

public class Deck {
	private String name;

	public Deck(String in_name, ArrayList<Card> newCards) {
		name = in_name;
		cards = newCards;
		currentCards.addAll(cards);
		Shuffle();
	}

	ArrayList<Card> cards = new ArrayList<>();
	Stack<Card> currentCards = new Stack<>();
	ArrayList<Card> discardedCards = new ArrayList<>();

	public void Shuffle() {
		Stack<Card> tempCards = new Stack<>();
		tempCards.addAll(currentCards);

		currentCards.clear();
		Shuffle(tempCards);
	}

	@Override
	public String toString() {
		return name;
	}

	public void Shuffle(Collection<? extends Card> cardsToShuffle) {
		// System.out.println("Shuffle");

		ArrayList<Integer> cardIndexes = new ArrayList<>();
		Card[] tempCards = cardsToShuffle.toArray(new Card[cardsToShuffle.size()]);

		for (int i = 0; i < cardsToShuffle.size(); i++) {
			cardIndexes.add(i);
		}

		Random rand = new Random();

		while (!cardIndexes.isEmpty()) {
			int cardIndex = rand.nextInt(cardIndexes.size());
			int index = cardIndexes.get(cardIndex);
			Card cardFound = tempCards[index];

			currentCards.add(cardFound);
			// System.out.println(cardFound.GetName());

			cardIndexes.remove(cardIndex);
		}
	}

	public void AddCard(Card card) {
		cards.add(card);
		currentCards.add(card);
	}

	public void Reset() {
		// System.out.println("Reset");
		currentCards.clear();
		discardedCards.clear();
		Shuffle(cards);
	}

	public Card Draw() {
		// System.out.println("Draw");
		Card card = currentCards.pop();

		if (currentCards.isEmpty()) {
			Reset();
		} else {
			discardedCards.add(card);
		}

		return card;
	}

}
