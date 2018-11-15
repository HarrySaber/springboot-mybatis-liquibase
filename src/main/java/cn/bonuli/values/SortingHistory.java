package cn.bonuli.values;

import java.util.Date;

/**
 * SortingHistory
 *
 * @author D.jin
 * @date 2018/6/26
 */
public class SortingHistory {
    final int sortingPort;
    final String cartonNumber;
    final Date sortingAt;

    public SortingHistory(int sortingPort, String cartonNumber, Date sortingAt) {
        this.sortingPort = sortingPort;
        this.cartonNumber = cartonNumber;
        this.sortingAt = sortingAt;
    }

    public int getSortingPort() {
        return sortingPort;
    }

    public String getCartonNumber() {
        return cartonNumber;
    }

    public Date getSortingAt() {
        return sortingAt;
    }
}
