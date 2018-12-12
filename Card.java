
public class Card {
	private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	enum Suit {Club, Diamond, Heart, Spade}//¦CÁ|ªá¦â
	public Card(Suit s, int value){
		suit=s;
		rank=value;
	}	
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		if(rank==1) {
			System.out.println(suit+","+"Ace");
		}
		else if(rank==11) {
			System.out.println(suit+","+"Jack");
		}
		else if(rank==12) {
			System.out.println(suit+","+"Queen");
		}
		else if(rank==13) {
			System.out.println(suit+","+"King");
		}
		else
		{
			System.out.println(suit+","+rank);
		}
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
