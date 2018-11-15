package cn.bonuli.values;

/**
 * ExceptionLog
 *
 * @author D.jin
 * @date 2018/6/29
 */
public class ExceptionDTO {
    final String cartonNumber;
    final String exception;
    final String sortingAt;

    public ExceptionDTO(String cartonNumber, String exception, String sortingAt) {
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

    public String getSortingAt() {
        return sortingAt;
    }
}
