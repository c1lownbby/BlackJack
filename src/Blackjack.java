import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    private Deck deck;
    private ArrayList<Card> player;
    private ArrayList<Card> dealer;
    Scanner kb;

    public Blackjack() {
        deck = new Deck();
        player = new ArrayList<>();
        dealer = new ArrayList<>();
        kb = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.run();
    }

    private void run() {
        //dealCards()
        deck.shuffle();

        dealFirstSetOfCards();
        System.out.println("dealer hand: \t" + dealer.get(0)+ " [?]");
        System.out.println("player hand: \t" + player.get(0)+ " " + player.get(1));

        playerTurn();
        dealerTurn();

        //while(response.equals("hit)
        //do{...}while(logic)

        for(int i = 0; i < player.size(); i++) {
            //not complete
            //print what is below
            player.get(i);
        }





    }

    public void dealFirstSetOfCards(){
        System.out.println("Dealing cards!");
        player.add(deck.getCard());
        dealer.add(deck.getCard());
        player.add(deck.getCard());
        dealer.add(deck.getCard());
    }

    public void playerTurn() {
        while (true) {
            System.out.println();
            System.out.println("hit or stay? [h/s]");
            String response = kb.nextLine();
            int playerTotal = calculateHandValue(player);
            //toLowerCase or toUpperCase
            if(response.equals("h")) {
                player.add(deck.getCard());
                playerHand();
                if (calculateHandValue(player) > 21) {
                    System.out.println("You busted D:! You lose.");
                    break;
                }
            }
            else if (response.equals("s")){
                break;
            }
            else {
                System.out.println("Invalid! Please enter either h for hit or s for stay.");
            }
        }

    }

    public void dealerTurn() {
        System.out.println("Dealer's hand:");
        for (Card card : dealer) {
            System.out.println(card + " ");
        }
        System.out.println();
        while (calculateHandValue(dealer) < 17) {
            dealer.add(deck.getCard());
            System.out.println("Dealer hits.");
            System.out.println("Dealer's hand: ");
            for (Card card : dealer) {
                System.out.print(card + " ");
            }
            System.out.println();
        }
        if (calculateHandValue(dealer) > 21) {
            System.out.println("Dealer busted :D. You win!");
        }
    }

    public void playerHand() {
        System.out.println("Player's hand:");
        for (Card card : player) {
            System.out.println(card + " ");
        }
        System.out.println();
    }

    private int calculateHandValue(ArrayList<Card> hand) {
        int totalValue = 0;
        int numAces = 0;
        for (Card card : hand) {
            int cardValue = card.getValue();
            if (cardValue == 11){
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


    private void determineWinner() {
        int playerTotal = calculateHandValue(player);
        int dealerTotal = calculateHandValue(dealer);

        System.out.println("Players hand: ");

    }

    //write push




    //playerTurn()
    //dealerTurn()
    //calcHandValue()

}
