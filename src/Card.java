public class Card {

    private int suit;
    private int value;

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        if (value >= 11 && value <= 13) { // jack, queen, king
            return 10;
        } else if (value == 1) { // ace
            return 11;
        } else { // numbers
            return value;
        }
    }

    public String toString() {
        String[] suits = {"♥", "♣", "♦", "♠"};
        String[] values = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return values[this.value] + " " + suits[this.suit];
    }
}