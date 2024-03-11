import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private Deck deck;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    private Scanner kb;
    private int balance;

    public Blackjack() {
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);
        balance = 1500;
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.run();
    }

    private void run() {
        while (balance > 0) {
            System.out.println("Your balance is: $" + balance);
            System.out.println("Place your bets! (1-" + balance + "):");
            int bet = kb.nextInt();

            // validate the betting amount
            while (bet <= 0 || bet > balance) {
                System.out.println("Sorry, that's an invalid bet :(. Please place a bet between 1 and " + balance + ":");
                bet = kb.nextInt();
            }


            balance -= bet;


            deck.shuffle();

            //deals the first set of cards
            dealInitialCards();
            //prints both hands
            System.out.println("Dealer hand:\t" + dealer.get(0) + " [?]");
            System.out.println("Player hand:\t" + player.get(0) + " " + player.get(1));

            //player's turn
            playerTurn();

            //dealer's turn
            dealerTurn();

            //determine the winner + take/give money back to player
            determineWinner(bet);

            //prints the hands one more time

            System.out.println("Dealer's full hand:");
            printHand(dealer);

            System.out.println("Player's full hand:");
            printHand(player);

            player.clear();
            dealer.clear();
        }

        //ends when the player's balance is 0

        System.out.println("Game over! You have run out of balance.");
    }

    private void dealInitialCards() {
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());
    }

    private void playerTurn() {
        while (true) {

            System.out.println("\nDo you want to hit or stand: h/s");
            String response = kb.next();
            if (response.equalsIgnoreCase("h")) {
                player.add(deck.getCard());
                System.out.println("Player hand:");
                printHand(player);
                int handValue = calculateHandValue(player);
                System.out.println("Hand value: " + handValue);
                if (handValue > 21) { //if the player exceeds a hand value of 21 the round ends
                    System.out.println("Bust! Player hand value is over 21.");
                    break;
                }
            } else if (response.equalsIgnoreCase("s")) {
                break;
            }
        }
    }

    private void dealerTurn() {
        System.out.println("\nDealer's turn:");

        if (calculateHandValue(player) > 21) {
            System.out.println("Player has busted. The Dealer will not draw.");
            return;
        }

        while (calculateHandValue(dealer) < 17) { //if the dealer's hand is lower than 17 the draw again
            dealer.add(deck.getCard());
            System.out.println("Dealer draws: " + dealer.get(dealer.size() - 1));
            System.out.println("Dealer's hand:");
            printHand(dealer);
        }
    }

    private void determineWinner(int bet) {
        int playerTotal = calculateHandValue(player);
        int dealerTotal = calculateHandValue(dealer);

        System.out.println("\nPlayer's hand value: " + playerTotal);
        System.out.println("Dealer's hand value: " + dealerTotal);

        if (playerTotal > 21) {
            System.out.println("Bust! Dealer wins.");
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("Congratulations! You win! :D");
            balance += 2 * bet;
        } else if (playerTotal == dealerTotal) {
            System.out.println("It's a tie!");
            balance += bet;
        } else {
            System.out.println("Dealer wins! Good luck next time!");
        }
    }

    //calculates the hand values of both the dealer and the player
    private int calculateHandValue(ArrayList<Card> hand) {
        int totalValue = 0;
        int numAces = 0;

        for (Card card : hand) {
            int cardValue = card.getValue();
            if (cardValue == 11) {
                numAces++;
            }
            totalValue += cardValue;
        }

        while (totalValue > 21 && numAces > 0) {
            totalValue -= 10;
            numAces--;
        }

        return totalValue;
    }

    private void printHand(ArrayList<Card> hand) {
        for (Card card : hand) {
            System.out.println(card);
        }
    }
}
