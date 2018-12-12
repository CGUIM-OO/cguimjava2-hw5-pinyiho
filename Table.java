import java.util.ArrayList;

public class Table {

	  static final int MAXPLAYER = 4;
	  private Deck AllCard; //�s��Ҧ����P
	  private Player player[]; //�s��Ҧ������a
	  private Dealer banker; //�s��@�Ӳ��a
	  private int[] pos_betArray = new int[MAXPLAYER]; //�s��C�Ӫ��a�b�@���U���`

	public Table(int nDeck){ 
		AllCard = new Deck(nDeck); //�NDeck class�����
		player = new Player[MAXPLAYER]; //��l�ƤW�z�s�W���O��Player[]���ܼ�,�B�ŧi�@�Ӫ��׬OMAXPLAYER ��Player array
	}
	
	public void set_player(int pos, Player p) {
		//�NPlayer���P��W (�N�Y��쫬�O��Player[]���ܼ�(instance field)���A��setter)
		//pos���P���m�A�̦hMAXPLAYER�H�Ap�h��Player instance�C
		if(pos >= MAXPLAYER)
			   System.out.print("Over maxplayer");
		else
			   player[pos] = p;
	}
	public Player[] get_player() {
		//�^�ǩҦ��b�P��W��player�A�N�Y�^�ǫ��O��Player[]���ܼ�(instance field)�ܼơA��getter
		return player;
	}
	public void set_dealer(Dealer dealer) {
		//�NDealer���P��W (�N�Y�NDealer��� ���O��Dealer ���ܼ�(instance field) ���A���ܼƤ�setter)
		banker=dealer;
	}
	public Card get_face_up_card_of_dealer() {
		//�^��dealer���}�����i�P�A�]�N�O�ĤG�i�P
		return banker.getOneRoundCard().get(1);
	}
	private void ask_each_player_about_bets() {
		//�ШC�Ӫ��a���۩I
		//�ШC�Ӫ��a�U�`
		//�C�Ӫ��a�U���`�n�s�bpos_betArray�Ѥ���ϥ�
		for(int i=0; i<player.length; i++) {
		player[i].sayHello();
		pos_betArray[i]=player[i].makeBet();
		}
	}
	private void distribute_cards_to_dealer_and_players() {
		//�o�P�����a����a�A���o��i���}���P�����a
		//�A�@�i�\�۪��P�A�H�Τ@�i���}���P�����a�C(����: setOneRoundCard())
		//�o�P�����a��A�b�e���W�L�X���a���}���P"Dealer's face up card is "
		for(int i=0; i<player.length; i++) {
			ArrayList<Card> playerCard = new ArrayList<Card>();
			playerCard.add(AllCard.getOneCard(true));
			playerCard.add(AllCard.getOneCard(true));
			player[i].setOneRoundCard(playerCard);
			}
		ArrayList<Card> bankerCard = new ArrayList<Card>();
		bankerCard.add(AllCard.getOneCard(false));
		bankerCard.add(AllCard.getOneCard(true));
		banker.setOneRoundCard(bankerCard);
		System.out.println("Dealer's face up card is " );
		banker.getOneRoundCard().get(1).printCard();
	}
	private void ask_each_player_about_hits() {
		//�ݨC�Ӫ��a�n���n�P 
		//�߰ݶ���: ���ӥ[�J�P�������Ǧөw (��m)
		//�p�G���a�n�P�A�Цb�e���W�L�X"Hit! "+ ���a���W�r+ "��s cards now: "�A�ç⪱�a�n�P�᪺����P�L�X�C
		//�̫�N�s���P��setOneRoundCard()�]�w�^���a����C
		//�p�G�z�F�A�Ф��n�A�ݪ��a�O�_�n�P
		//�p�G���a���n�P�F�A�Цb�e���W�L�X ���a���W�r+"Pass hit!"
		boolean hit=false;
		for(int j=0; j<player.length; j++) {
		do{
			hit = player[j].hit_me(this); 
			ArrayList<Card> objectCard = player[j].getOneRoundCard();
			if(hit){
				objectCard.add(AllCard.getOneCard(true));
				System.out.print("Hit! ");
				System.out.println(player[j].getName()+"'s Cards now:");
				for(Card card : objectCard){
					card.printCard();
				}
			}
			else{
				System.out.println(player[j].getName()+", Pass hit!");
				System.out.println(player[j].getName()+", Final Card:");
				for(Card card : objectCard){
					card.printCard();
				}
			}
		}while(hit);
		}
	}
	private void ask_dealer_about_hits() {
		//�߰ݲ��a�O�_�n�P�A������A�L�X"Dealer's hit is over!"
		ArrayList<Card> bCard=banker.getOneRoundCard();
		if(banker.hit_me(this)) {
			bCard.add(AllCard.getOneCard(true));
			banker.setOneRoundCard(bCard);
		}
		else {
        System.out.println("Dealer's hit is over!");
	}
	}
	private void calculate_chips() {
		//�L�X���a���I�ƩM�P"Dealer's card value is "+�`�I��+" ,Cards:"+�P�� 
		//���a��C�@�Ӫ��a���P��
		//�w��C�Ӫ��a�A���L�X ���a�m�W+" card value is "+���a�`�I��
		//�ݽ�Ĺ�A�n�O���aĹ�A�⪱�a�w�X�S���A�L�X", Loss "+�U�`�w�X��+" Chips, the Chips now is: "+���a�̷s�w�X��
		//�n�O���a��A�h�ߪ��a�P�U�`�w�X�۲Ť��w�X�A�L�X",Get "+�U�`�w�X��+" Chips, the Chips now is: "+���a�̷s�w�X��
		//���餣Ĺ�A�L�X",chips have no change! The Chips now is: "+���a�̷s�w�X��
		System.out.println("Dealer's card value is "+banker.getTotalValue()+" ,Cards:");
		banker.printAllCard();
		for(int i=0; i<player.length; i++) {
		System.out.println(player[i].getName()+" card value is "+player[i].getTotalValue());
		if(banker.getTotalValue()>player[i].getTotalValue()) {
			System.out.println(", Loss "+player[i].makeBet()+" Chips, the Chips now is: "+player[i].getCurrentChips());
		}
		if(banker.getTotalValue()<player[i].getTotalValue()) {
			System.out.println(", Get "+player[i].makeBet()+" Chips, the Chips now is: "+player[i].getCurrentChips());
		}
		else {
			System.out.println(",chips have no change! The Chips now is: " +player[i].getCurrentChips());
		}
		}
	}
	public int[] get_players_bet() {
		return pos_betArray;
		
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	}
	
