import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;      //�s�񦹰ƵP���Ҧ����P
	private ArrayList<Card> usedCard;  //�s�񦹰ƵP���Ҧ��o�X�h���P
	private ArrayList<Card> openCard;  //�s�񦹰ƵP���Ҧ����}���P
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
		//�o��n�إߤ@�ӵP�ո̭��n��52�i�P
        for(int deck=0;deck<nDeck;deck++) {//�X�ƵP
        	 for (Card.Suit s : Card.Suit.values()) {//�|���
        		for(int rank=1;rank<=13;rank++) {//�@���13�i 
        			Card card=new Card(s,rank);//�إߤ@��Card���O������
        			cards.add(card);//�N�إߪ�card����[�JArrayList<Card> cards��
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
			card=cards.get(n);//���ocards�� ���ޭȬ�n���ȡA�ñN���ǵ�card
			card.printCard();//�ϥ�printCard() Method
		}
	}
	public void shuffle() {
		cards.addAll(usedCard); //�N�o�L���P�����^��
		usedCard.removeAll(usedCard); //�M�ťιL���P��
		openCard.clear(); //�M�ť��}�L���P�� 
		nUsed=0; //�ιL���P���ƶq�k�s
        Random rnd = new Random();
		
		for(int i = 0; i < cards.size(); i++)//��C�@�i�P���~�P���ʧ@
		{
			int j = rnd.nextInt(cards.size());
			Card tempCard = cards.get(j); //�N�üƨ��쪺�P����J�Ȧs
			cards.set(j, cards.get(i)); //�N��mi���P�令��mj���P
			cards.set(i, tempCard); //�A�N�Ȧs���P���i��m
		}
	}
	public Card getOneCard(boolean isOpened) {
	if(cards.isEmpty()) //�p�G�P���o���F�h�A���s�~�P
	{
		shuffle();
	}
	Card c=cards.get(0); //���O���Ĥ@�i�P�X��
	usedCard.add(c); //�⮳�X�Ӫ��P���ιL���P��
	nUsed++; //�ιL���P���ƶq�W�[
	cards.remove(0); //��Ĥ@�i�P�R��
	if(isOpened == true) {
		openCard.add(c);
	}
	return c;
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
}
