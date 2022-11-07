public class Table {
    /**
     * Total number of player. Use this variable whenever possible
     */
    private static final int NUM_OF_PLAYERS = 4;
    /**
     * Total number of cards used in this game. Use this variable whenever possible
     */
    private static final int TOTAL_NUMBER_OF_CARD = 104;
    /**
     * The four stacks of cards on the table.
     */
    private Card[][] stacks = new Card[4][6];
    /**
     * This number of cards of each stack on the table. For example, if the variable
     * stacks stores
     * -------------------------
     * | 0 | 10 13 14 -- -- -- |
     * | 1 | 12 45 -- -- -- -- |
     * | 2 | 51 55 67 77 88 90 |
     * | 3 | 42 -- -- -- -- -- |
     * -------------------------
     * <p>
     * stacksCount should be {3, 2, 6, 1}.
     * <p>
     * You are responsible to maintain the data consistency.
     */
    private int[] stacksCount = new int[4];
    /**
     * The array of players
     */
    private Player[] players = new Player[NUM_OF_PLAYERS];

    /**
     * Default constructor
     * <p>
     * In the constructor, you should perform the following tasks:
     * <p>
     * 1. Initialize cards for play. You should construct enough number of cards
     * to play. These cards should be unique (i.e., no two cards share the same
     * number). The value of card must be between 1 to 104. The number of bullHead
     * printed on each card can be referred to the rule.
     * <p>
     * 2. Initialize four player. The first player should be a human player, call
     * "Kevin". The other player should be a computer player. These computer player
     * should have the name "Computer #1", "Computer #2", "Computer #3".
     * <p>
     * 3. Deal randomly 10 cards to each player. A card can only be dealt to one
     * player. That is, no two players can have the same card.
     * <p>
     * 4. Deal a card on each stack. The card dealt on the stack should not be dealt
     * to any player. Card dealt on each stack should also be unique (no two stack
     * have the same card).
     */
    public Table() {
        // random unique number between 1 to 104 in an array
        int[] cards = new int[TOTAL_NUMBER_OF_CARD];
        for (int i = 0; i < TOTAL_NUMBER_OF_CARD; i++) {
            cards[i] = i + 1;
        }
        // shuffle the array
        for (int i = 0; i < TOTAL_NUMBER_OF_CARD; i++) {
            // access the index
            int random = (int) (Math.random() * (TOTAL_NUMBER_OF_CARD - 1));
            // get the value of card[i]
            int temp = cards[i];
            // swap the value
            cards[i] = cards[random];
            cards[random] = temp;
        }
        // create 4 players
        players[0] = new Player("Kevin");
        players[1] = new Player("Computer #1");
        players[2] = new Player("Computer #2");
        players[3] = new Player("Computer #3");
        // deal 10 cards to each player
        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            for (int j = 0; j < 10; j++) {
                Card deal_card = new Card(cards[i * 10 + j]);
                players[i].dealCard(deal_card);
            }
        }
        // deal 1 card to each stack
        for (int i = 0; i < 4; i++) {
            stacks[i][0] = new Card(cards[NUM_OF_PLAYERS * 10 + i]); // put a card from index 40-44
            stacksCount[i] = 1;
        }
    }

    /**
     * This method is to find the correct stack that a card should be added to
     * according to the rule. It should return the stack among which top-card of
     * that stack is the largest of those smaller than the card to be placed. (If
     * the rule sounds complicate to you, please refer to the game video.)
     * <p>
     * In case the card to be place is smaller than the top cards of all stacks,
     * return -1.
     *
     * @param card - the card to be placed
     * @return the index of stack (0,1,2,3) that the card should be place or -1 if
     * the card is smaller than all top cards
     */
    public int findStackToAdd(Card card) {
        int max = -1;
        int index = -1;
        for (int i = 0; i < 4; i++) {
            if (stacks[i][stacksCount[i] - 1].getNumber() < card.getNumber() && stacks[i][stacksCount[i] - 1].getNumber() > max) {
                max = stacks[i][stacksCount[i] - 1].getNumber();
                index = i;
            }
        }
        return index;
    }

    /**
     * To print the stacks on the table. Please refer to the demo program for the
     * format. Within each stack, the card should be printed in ascending order,
     * left to right. However, there is no requirement on the order of stack to
     * print.
     */
    public void print() {
        // TODO
        for (int i = 0; i < stacks.length; i++) {
            System.out.print("Stack " + i + ": ");
            for (int j = 0; j < stacks[i].length; j++) {
                System.out.print(stacks[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * This method is the main logic of the game. You should create a loop for 10
     * times (running 10 rounds). In each round all players will need to play a
     * card. These cards will be placed to the stacks from small to large according
     * to the rule of the game.
     * <p>
     * In case a player plays a card smaller than all top cards, he will be
     * selecting one of the stack of cards and take them to his/her own score pile.
     * If the player is a human player, he will be promoted for selection. If the
     * player is a computer player, the computer player will select the "cheapest"
     * stack, i.e. the stack that has fewest bull heads. If there are more than
     * one stack having fewest bull heads, selecting any one of them.
     */
    public void runApp() {
        for (int turn = 0; turn < 10; turn++) {
            // print Table
            print();

            // TODO
        }
        for (Player p : players) {
            System.out.println(p.getName() + " has a score of " + p.getScore());
            p.printPile();
        }
    }

    /**
     * Programme main. You should not change this.
     *
     * @param args - no use.
     */
    public static void main(String[] args) {
        new Table().runApp();
    }

}
