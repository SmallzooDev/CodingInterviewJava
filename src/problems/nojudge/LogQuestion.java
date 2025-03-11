package problems.nojudge;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 여러분은 대규모 웹 서비스의 백엔드 개발자입니다. 서비스 로그 파일에서 특정 패턴의 에러를 찾고 분석하는 기능을 구현해야 합니다.
 * <p>
 * 주어진 로그 파일 형식:
 * [timestamp] [log-level] [service-name] [message]
 * <p>
 * 예시:
 * [2023-03-15 14:23:45] [ERROR] [user-service] Failed to authenticate user: invalid credentials
 * [2023-03-15 14:25:12] [INFO] [payment-service] Payment processed successfully
 * [2023-03-15 14:26:18] [ERROR] [database-service] Connection timeout after 30s
 * <p>
 * 요구사항:
 * 1. 모든 ERROR 레벨 로그를 추출하여 서비스별로 그룹화
 * 2. 각 서비스별 에러 발생 빈도를 계산
 * 3. 가장 많은 에러가 발생한 서비스와 해당 에러 메시지들을 반환
 * 4. 시간대별 에러 발생 추이를 분석할 수 있는 함수 구현
 */
public class LogQuestion {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList(
                "[2023-03-15 14:23:45] [ERROR] [user-service] Failed to authenticate user: invalid credentials",
                "[2023-03-15 14:25:12] [INFO] [payment-service] Payment processed successfully",
                "[2023-03-15 14:26:18] [ERROR] [database-service] Connection timeout after 30s",
                "[2023-03-15 15:10:45] [ERROR] [user-service] User profile update failed: database lock",
                "[2023-03-15 15:15:22] [ERROR] [user-service] Failed to send verification email",
                "[2023-03-15 15:20:11] [WARN] [payment-service] Payment processing delay detected",
                "[2023-03-15 16:30:45] [ERROR] [database-service] Query execution exceeded timeout limit",
                "[2023-03-15 16:45:12] [ERROR] [user-service] Invalid session token"
        );

        List<LogData> logDataList = new ArrayList<>();
        for (String log : logs) {
            try {
                LogData parsedLog = LogData.parse(log);
                logDataList.add(parsedLog);
            } catch (Exception e) {
                System.err.println("로그 파싱 오류: " + e.getMessage() + " - 로그: " + log);
            }
        }

        Map<String, List<LogData>> errorsByService = extractErrorsByService(logDataList);
        Map<String, Integer> errorFrequencyByService = calculateErrorFrequency(errorsByService);

        System.out.println("서비스별 에러 발생 빈도:");
        errorFrequencyByService.forEach((service, count) ->
                System.out.println(service + ": " + count + "회"));

        Map.Entry<String, List<LogData>> serviceWithMostErrors = findServiceWithMostErrors(errorsByService);

        System.out.println("\n가장 많은 에러가 발생한 서비스: " + serviceWithMostErrors.getKey() +
                " (" + serviceWithMostErrors.getValue().size() + "회)");
        System.out.println("에러 메시지:");
        serviceWithMostErrors.getValue().forEach(log ->
                System.out.println("- " + log.getMessage()));

        Map<Integer, Long> errorTrendByHour = analyzeErrorTrendByHour(logDataList);

        System.out.println("\n시간대별 에러 발생 추이:");
        errorTrendByHour.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.println(entry.getKey() + "시: " + entry.getValue() + "회"));
    }

    /**
     * 1. 모든 ERROR 레벨 로그를 추출하여 서비스별로 그룹화
     */
    public static Map<String, List<LogData>> extractErrorsByService(List<LogData> logs) {
        return logs.stream()
                .filter(log -> "ERROR".equals(log.getLevel()))
                .collect(Collectors.groupingBy(LogData::getService));
    }

    /**
     * 2. 각 서비스별 에러 발생 빈도를 계산
     */
    public static Map<String, Integer> calculateErrorFrequency(Map<String, List<LogData>> errorsByService) {
        Map<String, Integer> result = new HashMap<>();
        errorsByService.forEach((service, errors) -> result.put(service, errors.size()));
        return result;
    }

    /**
     * 3. 가장 많은 에러가 발생한 서비스와 해당 에러 메시지들을 반환
     */
    public static Map.Entry<String, List<LogData>> findServiceWithMostErrors(Map<String, List<LogData>> errorsByService) {
        if (errorsByService.isEmpty()) {
            throw new NoSuchElementException("에러 로그가 없습니다");
        }
        return errorsByService.entrySet().stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .orElseThrow(() -> new NoSuchElementException("에러 로그가 없습니다"));
    }

    /**
     * 4. 시간대별 에러 발생 추이를 분석할 수 있는 함수 구현
     */
    public static Map<Integer, Long> analyzeErrorTrendByHour(List<LogData> logs) {
        return logs.stream()
                .filter(log -> "ERROR".equals(log.getLevel()))
                .collect(Collectors.groupingBy(
                        log -> log.getTime().getHour(),
                        Collectors.counting()
                ));
    }

    /**
     * 다양한 시간 단위로 에러 추이를 분석할 수 있는 일반화된 함수
     * @param logs 로그 데이터 목록
     * @param timeUnit 시간 단위 ("hour", "day", "month" 중 하나)
     * @return 시간 단위별 에러 발생 횟수
     */
    public static Map<Integer, Long> analyzeErrorTrendByTimeUnit(List<LogData> logs, String timeUnit) {
        return logs.stream()
                .filter(log -> "ERROR".equals(log.getLevel()))
                .collect(Collectors.groupingBy(
                        log -> switch (timeUnit.toLowerCase()) {
                            case "hour" -> log.getTime().getHour();
                            case "day" -> log.getTime().getDayOfMonth();
                            case "month" -> log.getTime().getMonthValue();
                            default -> throw new IllegalArgumentException("지원하지 않는 시간 단위: " + timeUnit);
                        },
                        Collectors.counting()
                ));
    }

    public static class LogData {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        private LocalDateTime time;
        private String level;
        private String service;
        private String message;

        public LocalDateTime getTime() { return time; }
        public String getLevel() { return level; }
        public String getService() { return service; }
        public String getMessage() { return message; }

        public static LogData parse(String logLine) {
            LogData log = new LogData();

            Pattern bracketPattern = Pattern.compile("\\[(.*?)]");
            Matcher bracketMatcher = bracketPattern.matcher(logLine);

            List<String> bracketContents = new ArrayList<>();
            while (bracketMatcher.find()) {
                bracketContents.add(bracketMatcher.group(1));
            }

            if (bracketContents.size() < 3) {
                throw new IllegalArgumentException("로그 형식이 올바르지 않습니다. 최소 3개의 [] 내용이 필요합니다.");
            }

            log.time = LocalDateTime.parse(bracketContents.get(0), formatter);

            log.level = bracketContents.get(1);

            log.service = bracketContents.get(2);

            int lastBracketEnd = logLine.indexOf("]", logLine.indexOf(log.service)) + 1;
            if (lastBracketEnd < logLine.length()) {
                log.message = logLine.substring(lastBracketEnd).trim();
            } else {
                log.message = ""; // 메시지 없음
            }

            return log;
        }
    }
}
