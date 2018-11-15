package cn.bonuli.values.result;

/**
 * LayuiTableResult
 *
 * @author D.jin
 * @date 2018/10/29
 */
public class LayuiTableResult {
    final String code;
    final String msg;
    final int count;
    final Object data;

    public LayuiTableResult(String code, String msg, int count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static LayuiTableResult failed(String msg) {
        return new LayuiTableResult("0", msg, 0, null);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public Object getData() {
        return data;
    }
}
