/**
 * @author: code_hlb
 * @date :  2023/10/25 14:27
 * @desc :  自定义给定下标越界异常
 */
public class PosOutOfBoundsException extends RuntimeException {

    public PosOutOfBoundsException() {
        super();
    }

    public PosOutOfBoundsException(String message) {
        super(message);
    }
}
