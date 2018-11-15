package cn.bonuli.values;

import java.util.List;

/**
 * ExceptionValue
 *
 * @author D.jin
 * @date 2018/7/3
 */
public class ExceptionValue {
    final boolean succeed;
    final int total;
    final List<ExceptionDTO> exceptionLogs;

    public ExceptionValue(boolean succeed, int total, List<ExceptionDTO> exceptionLogs) {
        this.succeed = succeed;
        this.total = total;
        this.exceptionLogs = exceptionLogs;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public int getTotal() {
        return total;
    }

    public List<ExceptionDTO> getExceptionLogs() {
        return exceptionLogs;
    }
}
