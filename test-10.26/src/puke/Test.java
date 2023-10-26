package puke;

import java.util.List;

/**
 * @author: code_hlb
 * @date :  2023/10/26 14:28
 * @desc :  测试类
 */
public class Test {

    public static void main(String[] args) {

        CardList cardList = new CardList();
        System.out.println(cardList.buyCards());

        List<Card> myList = cardList.shuffle();
        System.out.println(myList);

        cardList.giveCard(myList);

    }
}
