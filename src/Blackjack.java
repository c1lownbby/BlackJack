public class Blackjack {
    public static void main(String[] args) {
        Blackjack game = new Blackjack();
        game.run();
    }

    private void run() {
        Card card1 = new Card(2,0);
        Card card2 = new Card(3,0);
        Card card3 = new Card(4,0);
        Card card4 = new Card(5,0);
        Card card5 = new Card(6,0);


        System.out.println(card1);
        System.out.println(card3);
        System.out.println(card2);
        System.out.println(card4);
        System.out.println(card5);

    }

}
