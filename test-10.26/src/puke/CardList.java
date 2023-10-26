package puke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: code_hlb
 * @date :  2023/10/26 14:21
 * @desc :  定义整副卡牌的 ArrayList
 */
public class CardList {

    // 声明列表
    public List<Card> list = new ArrayList<>();
    // 声明卡牌的花色
    public String[] colors = {"♦","♣","♠","♥"};

    public CardList() {

    }

    // 初始化卡牌
    public List<Card> buyCards(){
        // 花色只有四种
        for (int i = 0; i < this.colors.length; i++) {
            // 每种花色有13张牌
            for (int j = 1; j <= 13; j++) {
                // 将单张卡牌插入到列表当中
                this.list.add(new Card(this.colors[i],j));
            }
        }
        return this.list;
    }

    // 洗牌
    public List<Card> shuffle(){
        // 通过生成随机数下标交换数组元素完成洗牌
        Random random = new Random();
        for (int i = this.list.size() - 1; i > 0; i--) {
            // 随机生成的不大于 i 的下标
            int index = random.nextInt(i);
            // 交换随即和i位置的卡片
            swap(index,i);
        }
        return this.list;
    }

    public List<Card> swap(int i, int j){
        Card tmp = this.list.get(j);
        this.list.set(j,list.get(i));
        this.list.set(i,tmp);

        return this.list;
    }

    // 摸牌 --> 每人一次，每次摸五张
    public void giveCard(List<Card> lists){

        // 采用二维数组来存储每个人摸的牌
        List<List<Card>> hand = new ArrayList<>();
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        List<Card> hand3 = new ArrayList<>();

        hand.add(hand1);
        hand.add(hand2);
        hand.add(hand3);

        // 控制当前摸牌的人
        for (int i = 0; i < 3; i++) {
            // 控制摸牌数量
            for (int j = 0; j < 5; j++) {
                // 每次发牌都是从面上发，因此需要remove
                Card card = lists.remove(0);
                hand.get(i).add(card);
            }
        }

        for (int i = 1; i < hand.size() + 1; i++) {
            System.out.println("第" + i + "个人的牌：" + hand.get(i-1));
        }

        System.out.println("剩余的牌" + lists);
    }
}
