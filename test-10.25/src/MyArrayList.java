
import java.util.Arrays;

/**
 * @author: code_hlb
 * @date :  2023/10/25 12:50
 * @desc :
 */
public class MyArrayList {

    private int[] elem;
    private int usedSize;

    // 定义一个常量为数组的初始值
    private static final int DEFAULT_SIZE = 10;

    // 默认容量
    public MyArrayList() {
        this.elem =  new int[DEFAULT_SIZE];
    }

    // 指定容量
    public MyArrayList(int size) {
        this.elem = new int[size];
    }

    // 遍历数组
    public void display(){
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] + " ");
        }
        System.out.println();
    }

    // 增加元素 -->  默认在数组最后位置新增
    // 顺序表的中间元素必定有一个前驱和后继，因此不能跳着增加元素
    public void add(int data){
        if (isFull()){
            // 扩容
            this.elem = reSize();
            this.elem[this.usedSize] = data;
            this.usedSize++;
        }else {
            this.elem[this.usedSize] = data;
            this.usedSize++;
        }
    }

    // 增加元素 -->  在pos位置增加元素 --> 从后往前挪
    public void add(int pos,int data){
        // 令pos从0下标开始计数
        checkPos(pos);

        if (isFull()){
            // 扩容
            this.elem = reSize();
        }

        for (int i = this.usedSize; i >= pos; i--) {
            this.elem[i] = this.elem[i-1];
        }
        this.elem[pos] = data;
        this.usedSize++;
    }

    // 判定是否包含某个元素
    public boolean contains(int wantFind){
        for (int i = 0; i < this.usedSize; i++) {
            if (wantFind == this.elem[i]){
                return true;
            }
        }
        return false;
    }

    // 查找某个元素对应的位置
    public int indexOf(int wantFind){
        for (int i = 0; i < this.usedSize; i++) {
            if (wantFind == this.elem[i]){
                return i;
            }
        }
        return -1;
    }

    // 获取pos位置的元素
    public int get(int pos){
        checkPos(pos);
        return this.elem[pos];
    }

    // 将pos位置的元素设置为value
    public void set(int pos,int value){
        checkPos(pos);
        this.elem[pos] = value;
    }

    // 判断顺序表的长度
    public int size(){
        return this.usedSize;
    }

    // 删除第一次出现的关键字key
    public void remove(int key){

        int index = indexOf(key);
        if (-1 == index){
            System.out.println("顺序表当中无该元素！");
        }else {
            for (int i = index; i < this.usedSize - 1; i++) {
                this.elem[i] = this.elem[i + 1];
            }

            // 若该顺序表存储的是引用类型
            // 还应加上 this.elem[usedSize -1] = null 清理内存
            this.usedSize--;
        }
    }

    // 清空顺序表
    public void clear(){
        this.usedSize = 0;
        // 存储引用类型的顺序表清空应该将每一个引用都置为null
/*        for (int i = 0; i < this.usedSize; i++) {
            this.elem[i] = null;
        }*/
    }

    // 判断顺序表是否为空
    public boolean isFull(){
        if (this.usedSize == this.elem.length){
            return true;
        }
        return false;
    }

    // 检查pos位置是否合法
    private void checkPos(int pos){
        if (pos < 0 || pos >= this.usedSize){
            throw new PosOutOfBoundsException(pos + "下标位置不合法！");
        }
    }

    // 扩容数组
    public int[] reSize(){
        return Arrays.copyOf(this.elem,2 * this.elem.length);
    }
}
