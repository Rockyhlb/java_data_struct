import java.util.PriorityQueue;

/**
 * @author: code_hlb
 * @date :  2023/11/11 12:28
 * @desc :  测试类
 */
public class Test {

    public static void main(String[] args) {

        TestHeap testHeap = new TestHeap();
        int[] arrays = {43, 21, 22, 55, 2, 7, 56, 45, 33, 88};
        testHeap.initHeap(arrays);
        testHeap.createHeap();
        testHeap.heapSort();
    }

    public static void main2(String[] args) {
/*       1、PriorityQueue中放置的元素必须要能够比较大小，不能插入无法比较大小的对象
         2、不能插入null对象，不然会报空指针异常
         3、没有“容量限制”（扩容），可以插入任意多个元素，其内部可以自动扩容
         4、插入和删除元素的时间复杂度都是O(log2(N))
         5、PriorityQueue底层使用了堆数据结构
         6、PriorityQueue默认情况是小根堆，因此每次获取到的元素都是最小的元素*/
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(10);
        priorityQueue.offer(5);
        priorityQueue.offer(9);
        priorityQueue.offer(22);
        priorityQueue.offer(77);

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }

    public static void main1(String[] args) {
        Demo demo = new Demo();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        Demo.TreeNode root = demo.buildTree(preorder, inorder);

        System.out.println("===================");
        System.out.println(demo.tree2str(root));
        System.out.println("===================");
        TestHeap testHeap = new TestHeap();
        int[] arrays = {43, 21, 22, 55, 2, 7, 56, 45, 33, 88};
        testHeap.initHeap(arrays);
        testHeap.createHeap();
        System.out.println("===================");
        testHeap.offer(60);
        System.out.println("===================");
        System.out.println(testHeap.poll());
    }
}
