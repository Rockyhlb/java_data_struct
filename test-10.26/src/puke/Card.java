package puke;

/**
 * @author: code_hlb
 * @date :  2023/10/26 14:19
 * @desc :  定义卡牌的基本属性
 */
public class Card {

    private String color;
    private int num;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Card(String color, int num) {
        this.color = color;
        this.num = num;
    }

    @Override
    public String toString() {
/*        return "Card{" +
                "color='" + color + '\'' +
                ", num=" + num +
                '}';*/

        return this.color + " " + this.num;
    }
}
