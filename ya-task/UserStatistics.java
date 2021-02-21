import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * UserStatistics
 */
public class UserStatistics {

    private final long period;
    private final int requestsLimit;
    private final Map<Long, Map<Long, Integer>> statistics;

    public UserStatistics(int period, int eventLimit) {
        this.period = period * 1000L;
        this.requestsLimit = eventLimit;
        statistics = new HashMap<>();
    }

    public int getRequestLimit() {
        return requestsLimit;
    }

    public void event(long timestamp, long userId) {
        Long periodIndex = timestamp / period;
        Map<Long, Integer> periodStats = statistics
            .computeIfAbsent(periodIndex, k -> new HashMap<>());

        periodStats.merge(userId, 1, (v1, v2) -> v1 + v2);
    }

    public int robotCount(long timestamp) {
        Long periodIndex = timestamp / period;
        Map<Long, Integer> periodStats = statistics.get(periodIndex);
        Long count = periodStats.entrySet().stream()
            .filter(entry -> entry.getValue() >= requestsLimit)
            .count();

        return count.intValue();
    }

    public static void main (String[] args) throws InterruptedException {
        UserStatistics userStatistics = new UserStatistics(1, 5);
        Set<Long> userIds = new HashSet<>();
        userIds.add(111L);
        userIds.add(222L);
        userIds.add(333L);

        Long checkTime = System.currentTimeMillis();
        for (int i = 0; i <= 10; i++) {
            if (i == 5) checkTime = System.currentTimeMillis();
            for (Long userId : userIds) {
                userStatistics.event(System.currentTimeMillis(), userId);
            }
            Thread.sleep(100L);
        }

        System.out.println("Users with requests more that " +
                userStatistics.getRequestLimit() + " : " +
                userStatistics.robotCount(checkTime));
    }
}
