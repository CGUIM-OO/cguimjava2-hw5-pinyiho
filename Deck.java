import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;      //存放此副牌中所有的牌
	private ArrayList<Card> usedCard;  //存放此副牌中所有發出去的牌
	private ArrayList<Card> openCard;  //存放此副牌中所有打開的牌
	public int nUsed;  
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		usedCard=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		//這邊要建立一個牌組裡面要有52張牌
        for(int deck=0;deck<nDeck;deck++) {//幾副牌
        	 for (Card.Suit s : Card.Suit.values()) {//四花色
        		for(int rank=1;rank<=13;rank++) {//一花色13張 
        			Card card=new Card(s,rank);//建立一個Card類別的物件
        			cards.add(card);//將建立的card物件加入ArrayList<Card> cards中
        		}
        	}
        }
        shuffle();
	}
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		Card card;
		for(int n=0;n<cards.size();n++) {
			card=cards.get(n);//取得cards中 索引值為n之值，並將之傳給card
			card.printCard();//使用printCard() Method
		}
	}
	public void shuffle() {
		cards.addAll(usedCard); //將發過的牌都拿回來
		usedCard.removeAll(usedCard); //清空用過的牌組
		openCard.clear(); //清空打開過的牌組 
		nUsed=0; //用過的牌的數量歸零
        Random rnd = new Random();
		
		for(int i = 0; i < cards.size(); i++)//對每一張牌做洗牌的動作
		{
			int j = rnd.nextInt(cards.size());
			Card tempCard = cards.get(j); //將亂數取到的牌先放入暫存
			cards.set(j, cards.get(i)); //將位置i的牌改成位置j的牌
			cards.set(i, tempCard); //再將暫存的牌放到i位置
		}
	}
	public Card getOneCard(boolean isOpened) {
	if(cards.isEmpty()) //如果牌都發完了則再重新洗牌
	{
		shuffle();
	}
	Card c=cards.get(0); //都是拿第一張牌出來
	usedCard.add(c); //把拿出來的牌放到用過的牌區
	nUsed++; //用過的牌的數量增加
	cards.remove(0); //把第一張牌刪掉
	if(isOpened == true) {
		openCard.add(c);
	}
	return c;
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
}
