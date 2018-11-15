package cn.bonuli.values.result;

/**
 * ResultData
 *
 * @author D.jin
 * @date 2018/10/19
 */
public class ResultData<T> {
    final boolean isSucced;
    final String message;
    final int total;
    final Object data;

    public ResultData(boolean isSucced, String message, int total, Object data) {
        this.isSucced = isSucced;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public static ResultData succed(int total, Object data, String message) {
        return new ResultData<>(true, message, total, data);
    }

    public static ResultData failed(String message) {
        return new ResultData<>(false, message, 0, null);
    }


    public boolean isSucced() {
        return isSucced;
    }

    public String getMessage() {
        return message;
    }

    public int getTotal() {
        return total;
    }

    public Object getData() {
        return data;
    }
}
