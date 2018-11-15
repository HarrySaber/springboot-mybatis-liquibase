package cn.bonuli.values;

import java.util.Date;

/**
 * ExceptionLog
 *
 * @author D.jin
 * @date 2018/6/29
 */
public class ExceptionLog {
    final String cartonNumber;
    final String exception;
    final Date sortingAt;

    public ExceptionLog(String cartonNumber, String exception, Date sortingAt) {
        this.cartonNumber = cartonNumber;
        this.exception = exception;
        this.sortingAt = sortingAt;
    }

    public String getCartonNumber() {
        return cartonNumber;
    }

    public String getException() {
        return exception;
    }

    public Date getSortingAt() {
        return sortingAt;
    }
}
