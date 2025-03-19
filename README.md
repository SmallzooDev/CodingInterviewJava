# 핵심 문법 및 테크닉

## 목차
1. [입출력 최적화](#1-입출력-최적화)
2. [자료구조 활용](#2-자료구조-활용)
3. [정렬과 검색](#3-정렬과-검색)
4. [문자열 처리](#4-문자열-처리)
5. [숫자와 수학 연산](#5-숫자와-수학-연산)
6. [비트 연산](#6-비트-연산)
7. [시간 복잡도 최적화 테크닉](#7-시간-복잡도-최적화-테크닉)
8. [그래프 알고리즘 구현](#8-그래프-알고리즘-구현)
9. [자주 출제되는 알고리즘 유형별 템플릿](#9-자주-출제되는-알고리즘-유형별-템플릿)
10. [디버깅 전략](#10-디버깅-전략)

---

## 1. 입출력 최적화

### 1.1 빠른 입력 처리

```java
// 가장 빠른 입력 처리 방식
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 한 줄 읽기
        String line = br.readLine();
        
        // 공백으로 분리된 정수 읽기
        String[] tokens = br.readLine().split(" ");
        int a = Integer.parseInt(tokens[0]);
        int b = Integer.parseInt(tokens[1]);
        
        // StringTokenizer 사용 (split보다 빠름)
        import java.util.StringTokenizer;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
    }
}
```

### 1.2 빠른 출력 처리

```java
// StringBuilder 사용 (System.out.println보다 훨씬 빠름)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 100000; i++) {
    sb.append(i).append("\n");
}
System.out.println(sb);

// BufferedWriter 사용 (매우 큰 출력에 효과적)
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
for (int i = 0; i < 100000; i++) {
    bw.write(Integer.toString(i));
    bw.newLine();
}
bw.flush();
bw.close();
```

### 1.3 입력 패턴

```java
// 테스트 케이스 수가 주어지는 경우
int T = Integer.parseInt(br.readLine());
for (int tc = 0; tc < T; tc++) {
    // 각 테스트 케이스 처리
}

// 입력 끝까지 읽는 경우
String input;
while ((input = br.readLine()) != null) {
    // 처리
}

// 또는
while ((input = br.readLine()) != null && !input.isEmpty()) {
    // 처리
}
```

## 2. 자료구조 활용

### 2.1 배열과 리스트

```java
// 2차원 배열 초기화
int[][] grid = new int[rows][cols];
for (int i = 0; i < rows; i++) {
    Arrays.fill(grid[i], -1); // 모든 값을 -1로 초기화
}

// ArrayList와 배열 변환
ArrayList<Integer> list = new ArrayList<>();
// ArrayList -> 배열
Integer[] arr = list.toArray(new Integer[0]);
// 배열 -> ArrayList
Integer[] array = {1, 2, 3};
ArrayList<Integer> newList = new ArrayList<>(Arrays.asList(array));

// 정수 배열 -> Integer 리스트 (Java 8+)
int[] primitiveArray = {1, 2, 3};
List<Integer> listFromArray = Arrays.stream(primitiveArray)
                                   .boxed()
                                   .collect(Collectors.toList());
```

### 2.2 해시 기반 자료구조

```java
// HashMap 응용
HashMap<String, Integer> map = new HashMap<>();
map.put("key", 1);
map.getOrDefault("key", 0); // key가 없으면 0 반환
map.merge("key", 1, Integer::sum); // 값 증가 (빈도수 계산 시 유용)
map.computeIfAbsent("key", k -> new ArrayList<>()); // 없으면 새 리스트 생성

// HashSet 응용
HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
set.retainAll(Arrays.asList(2, 3, 4)); // 교집합 - set은 [2, 3]이 됨
set.addAll(Arrays.asList(5, 6)); // 합집합
set.removeAll(Arrays.asList(2, 5)); // 차집합

// LinkedHashMap/LinkedHashSet: 삽입 순서 유지
LinkedHashMap<String, Integer> orderedMap = new LinkedHashMap<>();
LinkedHashSet<String> orderedSet = new LinkedHashSet<>();

// TreeMap/TreeSet: 정렬 상태 유지
TreeMap<Integer, String> sortedMap = new TreeMap<>(); // 키 기준 오름차순
TreeSet<Integer> sortedSet = new TreeSet<>(); // 오름차순
TreeSet<Integer> reverseSortedSet = new TreeSet<>(Collections.reverseOrder()); // 내림차순
```

### 2.3 큐와 스택

```java
// 큐 (Queue)
Queue<Integer> queue = new LinkedList<>();
queue.offer(1); // 추가
int first = queue.poll(); // 제거 및 반환
int peek = queue.peek(); // 조회만

// 스택 (Stack) - Deque 사용 권장
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1); // 추가
int top = stack.pop(); // 제거 및 반환
int peek = stack.peek(); // 조회만

// 우선순위 큐
PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 최소 힙
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙

// 커스텀 비교 기준 우선순위 큐
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
    if (a[0] == b[0]) return a[1] - b[1]; // 첫 번째 원소가 같으면 두 번째 원소로 비교
    return a[0] - b[0]; // 첫 번째 원소로 비교
});
```

### 2.4 고급 자료구조

```java
// 이중 연결 리스트
LinkedList<Integer> linkedList = new LinkedList<>();
linkedList.addFirst(1); // 앞에 추가
linkedList.addLast(2);  // 뒤에 추가
linkedList.removeFirst(); // 앞에서 제거
linkedList.removeLast();  // 뒤에서 제거

// BitSet (효율적인 비트 배열)
BitSet bitSet = new BitSet(100);
bitSet.set(10); // 10번 비트 설정
bitSet.set(20, 30); // 20~29번 비트 설정
bitSet.clear(15); // 15번 비트 해제
boolean isSet = bitSet.get(10); // 비트 상태 확인
int size = bitSet.cardinality(); // 설정된 비트 수

// 구간 트리 (세그먼트 트리) 기본 구조
class SegmentTree {
    int[] tree;
    int n;
    
    SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 1, 0, n - 1);
    }
    
    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) / 2;
        build(arr, 2 * node, start, mid);
        build(arr, 2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] + tree[2 * node + 1]; // 합을 저장하는 경우
    }
    
    int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int leftSum = query(2 * node, start, mid, left, right);
        int rightSum = query(2 * node + 1, mid + 1, end, left, right);
        return leftSum + rightSum;
    }
    
    void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) / 2;
        if (idx <= mid) update(2 * node, start, mid, idx, val);
        else update(2 * node + 1, mid + 1, end, idx, val);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}
```

## 3. 정렬과 검색

### 3.1 정렬 기법

```java
// 기본 정렬
int[] arr = {3, 1, 4, 1, 5, 9};
Arrays.sort(arr); // 오름차순 정렬

// Integer 배열 내림차순 정렬
Integer[] boxedArr = {3, 1, 4, 1, 5, 9};
Arrays.sort(boxedArr, Collections.reverseOrder());

// 일부분만 정렬
Arrays.sort(arr, 0, 3); // 인덱스 0부터 2까지만 정렬

// 2차원 배열 정렬
int[][] points = {{3, 4}, {1, 2}, {5, 6}};
// 첫 번째 원소 기준 오름차순
Arrays.sort(points, (a, b) -> a[0] - b[0]);
// 첫 번째 원소 기준 내림차순
Arrays.sort(points, (a, b) -> b[0] - a[0]);
// 첫 번째 원소 같으면 두 번째 원소로 정렬
Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

// 객체 정렬
class Point implements Comparable<Point> {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Point o) {
        return this.x - o.x; // x 좌표 기준 오름차순
    }
}

// 리스트 정렬
ArrayList<Integer> list = new ArrayList<>();
Collections.sort(list); // 오름차순
Collections.sort(list, Collections.reverseOrder()); // 내림차순
```

### 3.2 검색 알고리즘

```java
// 이진 검색 (배열이 정렬되어 있어야 함)
int index = Arrays.binarySearch(arr, key);
// 없으면 음수 반환: -(insertion point) - 1

// 커스텀 비교기를 이용한 이진 검색
Arrays.binarySearch(objects, key, comparator);

// 직접 구현한 이진 검색 (lower bound - key 이상인 첫 위치)
int lowerBound(int[] arr, int key) {
    int lo = 0, hi = arr.length;
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] < key) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }
    return lo;
}

// 직접 구현한 이진 검색 (upper bound - key 초과인 첫 위치)
int upperBound(int[] arr, int key) {
    int lo = 0, hi = arr.length;
    while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] <= key) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }
    return lo;
}
```

## 4. 문자열 처리

### 4.1 기본 문자열 연산

```java
// 문자열 비교
"abc".equals("abc"); // true
"abc".equalsIgnoreCase("ABC"); // true

// 문자열 검색
"abcdef".contains("cd"); // true
"abcdef".indexOf("cd"); // 2
"abcdef".lastIndexOf("a"); // 0
"abcdef".startsWith("abc"); // true
"abcdef".endsWith("def"); // true

// 문자열 변환
"abc".toUpperCase(); // "ABC"
"ABC".toLowerCase(); // "abc"
"  abc  ".trim(); // "abc"
"  abc  ".strip(); // "abc" (Java 11+)

// 문자열 대체
"abcabc".replace('a', 'z'); // "zbczbc"
"abcabc".replace("ab", "XY"); // "XYcXYc"
"a,b,c".replaceAll(",", ""); // "abc"
"a1b2c3".replaceAll("\\d", "X"); // "aXbXcX" (정규식 사용)

// 문자열 분할 및 결합
String[] parts = "a,b,c".split(","); // ["a", "b", "c"]
String joined = String.join("-", parts); // "a-b-c"
```

### 4.2 고급 문자열 처리

```java
// StringBuilder 고급 활용
StringBuilder sb = new StringBuilder("abcdef");
sb.insert(2, "XYZ"); // "abXYZcdef"
sb.delete(1, 4); // "aZcdef"
sb.reverse(); // "fedcZa"
sb.setCharAt(0, 'X'); // "XedcZa"
sb.substring(1, 3); // "ed" (주의: 원본 변경 안 함)

// 문자 배열 <-> 문자열
char[] chars = "abcdef".toCharArray();
String str = new String(chars);
String substr = new String(chars, 1, 3); // "bcd"

// 문자 처리
char c = 'A';
boolean isDigit = Character.isDigit(c);
boolean isLetter = Character.isLetter(c);
boolean isUpperCase = Character.isUpperCase(c);
char lower = Character.toLowerCase(c);

// 정규식
String text = "My phone number is 123-456-7890 and email is user@example.com";
Pattern phonePattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
Matcher phoneMatcher = phonePattern.matcher(text);
while (phoneMatcher.find()) {
    System.out.println(phoneMatcher.group()); // "123-456-7890"
}

// 문자열을 정수 리스트로 변환
String numStr = "1 2 3 4 5";
List<Integer> nums = Arrays.stream(numStr.split(" "))
                           .map(Integer::parseInt)
                           .collect(Collectors.toList());
```

### 4.3 문자열 알고리즘

```java
// KMP 알고리즘 (문자열 검색)
int[] computeLPS(String pattern) {
    int m = pattern.length();
    int[] lps = new int[m];
    int len = 0;
    int i = 1;
    
    while (i < m) {
        if (pattern.charAt(i) == pattern.charAt(len)) {
            len++;
            lps[i] = len;
            i++;
        } else {
            if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
    }
    return lps;
}

List<Integer> KMP(String text, String pattern) {
    List<Integer> result = new ArrayList<>();
    int n = text.length();
    int m = pattern.length();
    if (m == 0) return result;
    
    int[] lps = computeLPS(pattern);
    int i = 0, j = 0;
    
    while (i < n) {
        if (pattern.charAt(j) == text.charAt(i)) {
            i++;
            j++;
        }
        if (j == m) {
            result.add(i - j);
            j = lps[j - 1];
        } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
            if (j != 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
    }
    return result;
}

// 트라이(Trie) 구현
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // 알파벳 소문자만 가정
    boolean isEndOfWord;
}

class Trie {
    TrieNode root = new TrieNode();
    
    void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }
    
    boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }
    
    boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }
    
    private TrieNode searchPrefix(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }
}
```

## 5. 숫자와 수학 연산

### 5.1 기본 수학 연산

```java
// Math 클래스 활용
double pow = Math.pow(2, 3); // 2^3 = 8.0
double sqrt = Math.sqrt(16); // 4.0
int abs = Math.abs(-5); // 5
int max = Math.max(10, 20); // 20
int min = Math.min(10, 20); // 10
double ceil = Math.ceil(3.1); // 4.0
double floor = Math.floor(3.9); // 3.0
long round = Math.round(3.5); // 4

// 삼각함수
double sin = Math.sin(Math.PI / 2); // 1.0
double cos = Math.cos(0); // 1.0
double tan = Math.tan(Math.PI / 4); // 약 1.0

// 로그
double log = Math.log(Math.E); // 1.0
double log10 = Math.log10(100); // 2.0
```

### 5.2 정수론 연산

```java
// 최대공약수 (GCD)
int gcd(int a, int b) {
    while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}

// 최소공배수 (LCM)
int lcm(int a, int b) {
    return a / gcd(a, b) * b; // 오버플로 방지를 위한 순서
}

// 소수 판별
boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 == 0 || n % 3 == 0) return false;
    for (int i = 5; i * i <= n; i += 6) {
        if (n % i == 0 || n % (i + 2) == 0) return false;
    }
    return true;
}

// 에라토스테네스의 체 (소수 찾기)
boolean[] sieveOfEratosthenes(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    
    for (int i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }
    return isPrime;
}

// 모듈러 연산
int mod = 1_000_000_007;
long result = ((a % mod) * (b % mod)) % mod; // 곱셈
long sum = ((a % mod) + (b % mod)) % mod; // 덧셈

// 모듈러 거듭제곱 (a^b % mod)
long modPow(long a, long b, long mod) {
    if (b == 0) return 1;
    long half = modPow(a, b / 2, mod);
    long result = (half * half) % mod;
    if (b % 2 == 1) result = (result * a) % mod;
    return result;
}

// 모듈러 역원 (페르마의 소정리 이용, mod가 소수일 때)
long modInverse(long a, long mod) {
    return modPow(a, mod - 2, mod);
}

// 팩토리얼
long factorial(int n) {
    long result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

// 조합 (nCr)
long combination(int n, int r) {
    if (r > n - r) r = n - r; // 더 작은 쪽으로 계산 (효율성)
    long result = 1;
    for (int i = 0; i < r; i++) {
        result *= (n - i);
        result /= (i + 1);
    }
    return result;
}
```

### 5.3 BigInteger 및 BigDecimal

```java
// BigInteger 사용법
import java.math.BigInteger;

BigInteger a = new BigInteger("12345678901234567890");
BigInteger b = BigInteger.valueOf(123); // long -> BigInteger
int c = a.intValue(); // BigInteger -> int (오버플로 주의)

// 연산
BigInteger sum = a.add(b);
BigInteger diff = a.subtract(b);
BigInteger product = a.multiply(b);
BigInteger quotient = a.divide(b);
BigInteger remainder = a.remainder(b);
BigInteger gcd = a.gcd(b);

// 비교
int compare = a.compareTo(b); // a<b: -1, a==b: 0, a>b: 1
boolean isEqual = a.equals(b);

// 비트 연산
BigInteger and = a.and(b);
BigInteger or = a.or(b);
BigInteger xor = a.xor(b);
BigInteger not = a.not();
BigInteger leftShift = a.shiftLeft(2); // a << 2
BigInteger rightShift = a.shiftRight(2); // a >> 2

// 멱승과 모듈러
BigInteger pow = a.pow(10); // a^10
BigInteger modPow = a.modPow(b, BigInteger.valueOf(mod)); // a^b % mod
BigInteger modInverse = a.modInverse(BigInteger.valueOf(mod)); // a^(-1) % mod

// BigDecimal 사용법
import java.math.BigDecimal;
import java.math.RoundingMode;

BigDecimal x = new BigDecimal("123.456789");
BigDecimal y = BigDecimal.valueOf(0.1); // double -> BigDecimal (정확하지 않을 수 있음)

// 연산
BigDecimal sum = x.add(y);
BigDecimal diff = x.subtract(y);
BigDecimal product = x.multiply(y);
BigDecimal quotient = x.divide(y, 10, RoundingMode.HALF_UP); // 10자리까지 반올림
```

## 6. 비트 연산

```java
// 기본 비트 연산
int a = 5;  // 0101
int b = 3;  // 0011

int and = a & b;      // 0001 (1)
int or = a | b;       // 0111 (7)
int xor = a ^ b;      // 0110 (6)
int not = ~a;         // 1...1010 (비트 반전, 부호 주의)
int leftShift = a << 1;  // 1010 (10)
int rightShift = a >> 1; // 0010 (2)
int unsignedRightShift = a >>> 1; // 0010 (양수에선 >> 와 동일)

// 유용한 비트 연산 기법
boolean isPowerOfTwo = (n & (n - 1)) == 0; // 2의 거듭제곱 확인
int setBit = n | (1 << pos); // pos 위치 비트 1로 설정
int clearBit = n & ~(1 << pos); // pos 위치 비트 0으로 설정
int toggleBit = n ^ (1 << pos); // pos 위치 비트 토글
boolean checkBit = (n & (1 << pos)) != 0; // pos 위치 비트 확인

// 비트 조작 유틸리티
int countOnes = Integer.bitCount(n); // 1 비트 개수
int leadingZeros = Integer.numberOfLeadingZeros(n); // 앞쪽 0 개수
int trailingZeros = Integer.numberOfTrailingZeros(n); // 뒤쪽 0 개수
int highestOneBit = Integer.highestOneBit(n); // 가장 높은 1 비트 위치
int lowestOneBit = Integer.lowestOneBit(n); // 가장 낮은 1 비트 위치

// 비트 마스크 활용 (부분집합 표현)
int fullSet = (1 << n) - 1; // n개 원소 모두 포함한 집합
int emptySet = 0; // 공집합

// 모든 부분집합 순회
for (int subset = 0; subset < (1 << n); subset++) {
    // subset은 원소 0~n-1의 부분집합
    // i번째 원소가 포함되어 있는지 확인
    for (int i = 0; i < n; i++) {
        if ((subset & (1 << i)) != 0) {
            // i번째 원소가 포함됨
        }
    }
}
```

## 7. 시간 복잡도 최적화 테크닉

### 7.1 메모이제이션 (DP)

```java
// 피보나치 수열 - 메모이제이션 적용
long[] memo = new long[100]; // 충분한 크기의 메모 배열
Arrays.fill(memo, -1); // 초기값 설정
memo[0] = 0;
memo[1] = 1;

long fibonacci(int n) {
    if (memo[n] != -1) return memo[n]; // 이미 계산된 값 재활용
    memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
    return memo[n];
}

// 2차원 DP 예시 - 최장 공통 부분 수열(LCS)
int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length();
    int n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    return dp[m][n];
}

// 상태 압축 DP - 비트마스크 사용
int tsp(int mask, int pos, int[][] dist) {
    int n = dist.length;
    if (mask == (1 << n) - 1) {
        return dist[pos][0]; // 모든 도시 방문 후 시작점으로 돌아감
    }
    
    if (dp[mask][pos] != -1) return dp[mask][pos];
    
    int ans = Integer.MAX_VALUE;
    for (int city = 0; city < n; city++) {
        if ((mask & (1 << city)) == 0) { // city가 아직 방문되지 않았다면
            ans = Math.min(ans, dist[pos][city] + tsp(mask | (1 << city), city, dist));
        }
    }
    return dp[mask][pos] = ans;
}
```

### 7.2 투 포인터 기법

```java
// 정렬된 배열에서 합이 target인 두 수 찾기
int[] twoSum(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) {
            return new int[] {left, right};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[] {-1, -1}; // 찾지 못함
}

// 부분 배열의 합이 target 이하인 최대 길이 찾기
int maxSubArrayLen(int[] nums, int target) {
    int n = nums.length;
    int left = 0, right = 0;
    int sum = 0, maxLen = 0;
    
    while (right < n) {
        sum += nums[right++]; // 오른쪽 확장
        
        while (sum > target && left < right) {
            sum -= nums[left++]; // 왼쪽 축소
        }
        
        if (sum <= target) {
            maxLen = Math.max(maxLen, right - left);
        }
    }
    return maxLen;
}

// 세 수의 합이 0에 가장 가까운 값 찾기
int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int n = nums.length;
    int closestSum = nums[0] + nums[1] + nums[2];
    
    for (int i = 0; i < n - 2; i++) {
        int left = i + 1, right = n - 1;
        
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            
            if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                closestSum = sum;
            }
            
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return target; // 정확히 target을 찾았으면 바로 반환
            }
        }
    }
    return closestSum;
}
```

### 7.3 슬라이딩 윈도우

```java
// 고정 크기 윈도우 - 크기가 k인 부분 배열의 최대 합
int maxSumFixedWindow(int[] nums, int k) {
    int n = nums.length;
    int maxSum = 0;
    int windowSum = 0;
    
    // 첫 번째 윈도우의 합 계산
    for (int i = 0; i < k; i++) {
        windowSum += nums[i];
    }
    maxSum = windowSum;
    
    // 윈도우 슬라이딩
    for (int i = k; i < n; i++) {
        windowSum = windowSum - nums[i - k] + nums[i];
        maxSum = Math.max(maxSum, windowSum);
    }
    return maxSum;
}

// 가변 크기 윈도우 - 합이 target 이상인 최소 길이 부분 배열
int minSubArrayLen(int target, int[] nums) {
    int n = nums.length;
    int left = 0;
    int sum = 0;
    int minLen = Integer.MAX_VALUE;
    
    for (int right = 0; right < n; right++) {
        sum += nums[right];
        
        while (sum >= target) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left++];
        }
    }
    
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}

// 문자열에서 모든 애너그램 찾기
List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    if (s.length() < p.length()) return result;
    
    int[] pCount = new int[26]; // 패턴 p의 문자 빈도수
    int[] sCount = new int[26]; // 현재 윈도우의 문자 빈도수
    
    // 패턴 p의 문자 빈도수 계산
    for (char c : p.toCharArray()) {
        pCount[c - 'a']++;
    }
    
    // 첫 윈도우 설정
    for (int i = 0; i < p.length(); i++) {
        sCount[s.charAt(i) - 'a']++;
    }
    
    // 빈도수가 일치하면 결과에 추가
    if (Arrays.equals(pCount, sCount)) {
        result.add(0);
    }
    
    // 윈도우 슬라이딩
    for (int i = p.length(); i < s.length(); i++) {
        // 윈도우 오른쪽에 문자 추가
        sCount[s.charAt(i) - 'a']++;
        // 윈도우 왼쪽에서 문자 제거
        sCount[s.charAt(i - p.length()) - 'a']--;
        
        // 빈도수 비교
        if (Arrays.equals(pCount, sCount)) {
            result.add(i - p.length() + 1);
        }
    }
    
    return result;
}
```

### 7.4 분할 정복

```java
// 병합 정렬 구현
void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}

void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    
    int[] L = new int[n1];
    int[] R = new int[n2];
    
    for (int i = 0; i < n1; i++) {
        L[i] = arr[left + i];
    }
    for (int i = 0; i < n2; i++) {
        R[i] = arr[mid + 1 + i];
    }
    
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k++] = L[i++];
        } else {
            arr[k++] = R[j++];
        }
    }
    
    while (i < n1) {
        arr[k++] = L[i++];
    }
    
    while (j < n2) {
        arr[k++] = R[j++];
    }
}

// 퀵 정렬 구현
void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            // swap arr[i] and arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    // swap arr[i+1] and arr[high]
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    
    return i + 1;
}

// 거듭제곱 빠르게 계산
long power(long x, long n, long mod) {
    if (n == 0) return 1;
    long half = power(x, n / 2, mod);
    if (n % 2 == 0) {
        return (half * half) % mod;
    } else {
        return (((half * half) % mod) * x) % mod;
    }
}
```

## 8. 그래프 알고리즘 구현

### 8.1 기본 그래프 표현

```java
// 인접 리스트 (가장 일반적)
ArrayList<Integer>[] adjList = new ArrayList[n + 1];
for (int i = 0; i <= n; i++) {
    adjList[i] = new ArrayList<>();
}
// 간선 추가 (양방향)
adjList[u].add(v);
adjList[v].add(u);

// 가중치 그래프
class Edge {
    int to, weight;
    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
ArrayList<Edge>[] weightedAdjList = new ArrayList[n + 1];
for (int i = 0; i <= n; i++) {
    weightedAdjList[i] = new ArrayList<>();
}
// 간선 추가 (양방향)
weightedAdjList[u].add(new Edge(v, w));
weightedAdjList[v].add(new Edge(u, w));

// 인접 행렬
int[][] adjMatrix = new int[n + 1][n + 1];
// 간선 추가 (양방향)
adjMatrix[u][v] = 1;
adjMatrix[v][u] = 1;
// 가중치 간선
adjMatrix[u][v] = weight;
adjMatrix[v][u] = weight;
```

### 8.2 그래프 탐색

```java
// DFS (깊이 우선 탐색) - 재귀 버전
boolean[] visited = new boolean[n + 1];

void dfs(int node) {
    visited[node] = true;
    System.out.println(node); // 방문 처리
    
    for (int next : adjList[node]) {
        if (!visited[next]) {
            dfs(next);
        }
    }
}

// DFS - 스택 버전
void dfsWithStack(int start) {
    boolean[] visited = new boolean[n + 1];
    Stack<Integer> stack = new Stack<>();
    stack.push(start);
    
    while (!stack.isEmpty()) {
        int node = stack.pop();
        
        if (visited[node]) continue;
        visited[node] = true;
        System.out.println(node); // 방문 처리
        
        // 인접 노드를 스택에 추가 (역순으로 탐색하려면 역순으로 추가)
        for (int i = adjList[node].size() - 1; i >= 0; i--) {
            int next = adjList[node].get(i);
            if (!visited[next]) {
                stack.push(next);
            }
        }
    }
}

// BFS (너비 우선 탐색)
void bfs(int start) {
    boolean[] visited = new boolean[n + 1];
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited[start] = true;
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.println(node); // 방문 처리
        
        for (int next : adjList[node]) {
            if (!visited[next]) {
                visited[next] = true;
                queue.offer(next);
            }
        }
    }
}

// 최단 거리를 구하는 BFS
int[] bfsDistance(int start) {
    int[] distance = new int[n + 1];
    Arrays.fill(distance, -1); // -1은 방문하지 않은 상태
    
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    distance[start] = 0;
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        
        for (int next : adjList[node]) {
            if (distance[next] == -1) {
                distance[next] = distance[node] + 1;
                queue.offer(next);
            }
        }
    }
    
    return distance;
}
```

### 8.3 최단 경로 알고리즘

```java
// 다익스트라 알고리즘 (단일 출발점 최단 경로)
int[] dijkstra(int start) {
    int[] distance = new int[n + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;
    
    // 우선순위 큐 (거리, 노드)
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    pq.offer(new int[]{0, start});
    
    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int dist = curr[0];
        int node = curr[1];
        
        // 이미 처리된 노드라면 스킵
        if (dist > distance[node]) continue;
        
        for (Edge next : weightedAdjList[node]) {
            int cost = dist + next.weight;
            
            if (cost < distance[next.to]) {
                distance[next.to] = cost;
                pq.offer(new int[]{cost, next.to});
            }
        }
    }
    
    return distance;
}

// 플로이드-워셜 알고리즘 (모든 쌍 최단 경로)
int[][] floydWarshall() {
    int[][] dist = new int[n + 1][n + 1];
    
    // 초기화
    for (int i = 1; i <= n; i++) {
        Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[i][i] = 0;
    }
    
    // 직접 연결된 간선 정보 입력
    for (int u = 1; u <= n; u++) {
        for (Edge e : weightedAdjList[u]) {
            dist[u][e.to] = e.weight;
        }
    }
    
    // 플로이드-워셜 알고리즘
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
    
    return dist;
}

// 벨만-포드 알고리즘 (음수 간선이 있는 경우)
class Edge {
    int from, to, weight;
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

boolean bellmanFord(int start, ArrayList<Edge> edges) {
    int[] distance = new int[n + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;
    
    // 정점 수만큼 반복
    for (int i = 0; i < n; i++) {
        for (Edge e : edges) {
            if (distance[e.from] != Integer.MAX_VALUE && 
                distance[e.to] > distance[e.from] + e.weight) {
                distance[e.to] = distance[e.from] + e.weight;
            }
        }
    }
    
    // 음수 사이클 확인
    for (Edge e : edges) {
        if (distance[e.from] != Integer.MAX_VALUE && 
            distance[e.to] > distance[e.from] + e.weight) {
            return true; // 음수 사이클 존재
        }
    }
    
    return false; // 음수 사이클 없음
}
```

### 8.4 최소 신장 트리

```java
// 크루스칼 알고리즘 (Union-Find 활용)
class Edge implements Comparable<Edge> {
    int from, to, weight;
    
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

class UnionFind {
    int[] parent;
    
    UnionFind(int n) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }
    
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}

int kruskal(ArrayList<Edge> edges, int n) {
    Collections.sort(edges); // 가중치 기준 오름차순 정렬
    
    UnionFind uf = new UnionFind(n);
    int mstWeight = 0;
    int edgeCount = 0;
    
    for (Edge e : edges) {
        if (uf.find(e.from) != uf.find(e.to)) {
            uf.union(e.from, e.to);
            mstWeight += e.weight;
            edgeCount++;
            
            if (edgeCount == n - 1) break; // MST 완성
        }
    }
    
    return mstWeight;
}

// 프림 알고리즘
int prim(int start) {
    boolean[] visited = new boolean[n + 1];
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, weight]
    pq.offer(new int[]{start, 0});
    
    int mstWeight = 0;
    
    while (!pq.isEmpty()) {
        int[] curr = pq.poll();
        int node = curr[0];
        int weight = curr[1];
        
        if (visited[node]) continue;
        
        visited[node] = true;
        mstWeight += weight;
        
        for (Edge next : weightedAdjList[node]) {
            if (!visited[next.to]) {
                pq.offer(new int[]{next.to, next.weight});
            }
        }
    }
    
    return mstWeight;
}
```

### 8.5 위상 정렬

```java
// 위상 정렬 (DAG에서 순서 있는 정렬)
ArrayList<Integer> topologicalSort() {
    ArrayList<Integer> result = new ArrayList<>();
    int[] inDegree = new int[n + 1]; // 진입 차수
    
    // 진입 차수 계산
    for (int i = 1; i <= n; i++) {
        for (int next : adjList[i]) {
            inDegree[next]++;
        }
    }
    
    // 진입 차수가 0인 노드를 큐에 삽입
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i <= n; i++) {
        if (inDegree[i] == 0) {
            queue.offer(i);
        }
    }
    
    // 위상 정렬
    while (!queue.isEmpty()) {
        int node = queue.poll();
        result.add(node);
        
        for (int next : adjList[node]) {
            inDegree[next]--;
            if (inDegree[next] == 0) {
                queue.offer(next);
            }
        }
    }
    
    // 사이클이 있는 경우 결과 크기가 n보다 작음
    if (result.size() != n) {
        return new ArrayList<>(); // 빈 리스트 반환 (사이클 존재)
    }
    
    return result;
}
```

## 9. 자주 출제되는 알고리즘 유형별 템플릿

### 9.1 다이나믹 프로그래밍 템플릿

```java
// 배낭 문제 (Knapsack Problem)
int knapsack(int W, int[] wt, int[] val, int n) {
    int[][] dp = new int[n + 1][W + 1];
    
    for (int i = 0; i <= n; i++) {
        for (int w = 0; w <= W; w++) {
            if (i == 0 || w == 0) {
                dp[i][w] = 0;
            } else if (wt[i - 1] <= w) {
                dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
            } else {
                dp[i][w] = dp[i - 1][w];
            }
        }
    }
    
    return dp[n][W];
}

// 최장 증가 부분 수열 (LIS)
int longestIncreasingSubsequence(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
    }
    
    int max = 0;
    for (int len : dp) {
        max = Math.max(max, len);
    }
    
    return max;
}

// 최장 증가 부분 수열 - O(nlogn) 버전
int longestIncreasingSubsequenceOptimized(int[] nums) {
    int n = nums.length;
    if (n == 0) return 0;
    
    // tails[i]는 길이가 i+1인 증가 부분 수열의 마지막 원소 중 최소값
    int[] tails = new int[n];
    int len = 0;
    
    for (int num : nums) {
        int idx = Arrays.binarySearch(tails, 0, len, num);
        if (idx < 0) idx = -idx - 1;
        
        tails[idx] = num;
        if (idx == len) len++;
    }
    
    return len;
}

// 편집 거리 (Edit Distance)
int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    // 초기화
    for (int i = 0; i <= m; i++) {
        dp[i][0] = i;
    }
    for (int j = 0; j <= n; j++) {
        dp[0][j] = j;
    }
    
    // 점화식
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // 대체
                                 Math.min(dp[i - 1][j],   // 삭제
                                          dp[i][j - 1]));  // 삽입
            }
        }
    }
    
    return dp[m][n];
}
```

### 9.2 백트래킹 템플릿

```java
// 조합 (Combination) - nCr
void combination(int[] arr, boolean[] visited, int start, int n, int r) {
    if (r == 0) {
        // 조합이 완성됨, 원하는 처리 수행
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
        return;
    }
    
    for (int i = start; i < n; i++) {
        visited[i] = true;
        combination(arr, visited, i + 1, n, r - 1);
        visited[i] = false;
    }
}

// 순열 (Permutation) - nPr
void permutation(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
    if (depth == r) {
        // 순열이 완성됨, 원하는 처리 수행
        for (int i = 0; i < r; i++) {
            System.out.print(output[i] + " ");
        }
        System.out.println();
        return;
    }
    
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            visited[i] = true;
            output[depth] = arr[i];
            permutation(arr, output, visited, depth + 1, n, r);
            visited[i] = false;
        }
    }
}

// N-Queens 문제
int[] queens; // queens[i]는 i번째 행의 퀸 위치(열)
int count = 0;

void nQueens(int row, int n) {
    if (row == n) {
        count++;
        return;
    }
    
    for (int col = 0; col < n; col++) {
        boolean canPlace = true;
        
        for (int prevRow = 0; prevRow < row; prevRow++) {
            // 같은 열 또는 대각선 체크
            if (queens[prevRow] == col || 
                queens[prevRow] - prevRow == col - row || 
                queens[prevRow] + prevRow == col + row) {
                canPlace = false;
                break;
            }
        }
        
        if (canPlace) {
            queens[row] = col;
            nQueens(row + 1, n);
        }
    }
}
```

### 9.3 그리디 알고리즘 템플릿

```java
// 활동 선택 문제 (Activity Selection)
int maxActivities(int[] start, int[] finish, int n) {
    // 끝나는 시간 기준 정렬
    int[][] activities = new int[n][2];
    for (int i = 0; i < n; i++) {
        activities[i][0] = start[i];
        activities[i][1] = finish[i];
    }
    
    Arrays.sort(activities, (a, b) -> a[1] - b[1]);
    
    int count = 1; // 첫 번째 활동 선택
    int lastFinish = activities[0][1];
    
    for (int i = 1; i < n; i++) {
        if (activities[i][0] >= lastFinish) {
            count++;
            lastFinish = activities[i][1];
        }
    }
    
    return count;
}

// 허프만 코딩
class HuffmanNode implements Comparable<HuffmanNode> {
    int freq;
    char data;
    HuffmanNode left, right;
    
    public HuffmanNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        left = right = null;
    }
    
    @Override
    public int compareTo(HuffmanNode node) {
        return this.freq - node.freq;
    }
}

void printCodes(HuffmanNode root, String code) {
    if (root == null) return;
    
    if (root.data != '\0') {
        System.out.println(root.data + ": " + code);
    }
    
    printCodes(root.left, code + "0");
    printCodes(root.right, code + "1");
}

void huffmanCoding(char[] data, int[] freq) {
    PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
    
    for (int i = 0; i < data.length; i++) {
        pq.add(new HuffmanNode(data[i], freq[i]));
    }
    
    while (pq.size() > 1) {
        HuffmanNode left = pq.poll();
        HuffmanNode right = pq.poll();
        
        HuffmanNode merged = new HuffmanNode('\0', left.freq + right.freq);
        merged.left = left;
        merged.right = right;
        
        pq.add(merged);
    }
    
    printCodes(pq.poll(), "");
}

// 분수 배낭 문제 (Fractional Knapsack)
double fractionalKnapsack(int W, int[] wt, int[] val, int n) {
    // 가치/무게 비율 기준 정렬
    Item[] items = new Item[n];
    for (int i = 0; i < n; i++) {
        items[i] = new Item(wt[i], val[i], (double)val[i] / wt[i]);
    }
    
    Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
    
    double totalValue = 0;
    int currentWeight = 0;
    
    for (int i = 0; i < n; i++) {
        if (currentWeight + items[i].weight <= W) {
            currentWeight += items[i].weight;
            totalValue += items[i].value;
        } else {
            int remainingWeight = W - currentWeight;
            totalValue += items[i].value * ((double)remainingWeight / items[i].weight);
            break;
        }
    }
    
    return totalValue;
}

class Item {
    int weight, value;
    double ratio;
    
    Item(int weight, int value, double ratio) {
        this.weight = weight;
        this.value = value;
        this.ratio = ratio;
    }
}
```

## 10. 디버깅 전략

### 10.1 입출력 디버깅

```java
// 디버깅을 위한 입출력 테스트 방법
public static void main(String[] args) throws IOException {
    // 콘솔 입력 - 테스트 실행 시
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    // 파일 입력 - 로컬 테스트 시
    // BufferedReader br = new BufferedReader(new FileReader("input.txt"));
    
    // 출력 확인용 로그
    System.err.println("Debug: " + variable); // 디버그 출력
    
    // 로깅 파일 생성
    // PrintWriter debugLog = new PrintWriter("debug.log");
    // debugLog.println("Debug: " + variable);
    // debugLog.flush();
}
```

### 10.2 코드 검증 및 최적화

```java
// 정확성 검증
// 1. 반례 케이스 체크
//   - 엣지 케이스: 최소값, 최대값, 빈 입력
//   - 특수 케이스: 모두 같은 값, 정렬된 값

// 2. 중간 값 확인
// Arrays.toString(array) 활용
System.err.println("Intermediate array: " + Arrays.toString(array));
System.err.println("DP table: " + Arrays.deepToString(dp));

// 3. 시간 복잡도 체크
// 주요 시간 복잡도
// - O(1): 상수 시간
// - O(log n): 이진 탐색, 힙 연산
// - O(n): 선형 탐색, 한 번의 배열 순회
// - O(n log n): 효율적인 정렬 (퀵소트, 머지소트)
// - O(n^2): 이중 반복문, 비효율적 정렬 (버블 소트)
// - O(n^3): 삼중 반복문
// - O(2^n): 부분집합 생성

// 4. 공간 복잡도 최적화
// - 1차원 DP 배열로 2차원 문제 해결
int[] dp = new int[n + 1]; // 1차원 배열로 충분한 경우가 많음

// - 필요 없는 객체 제거
// - 기본 타입 사용 (Integer 대신 int)
```

### 10.3 알고리즘 컨테스트 팁

```java
// 1. 빠른 코드 작성을 위한 팁
// - 공통 기능 미리 정의 (템플릿 활용)
// - 복잡한 알고리즘은 라이브러리 활용 (Collections.sort 등)
// - 문제의 제약 조건을 파악하고 적절한 자료구조 선택

// 2. 시간 관리
// - 쉬운 문제부터 해결
// - 문제 이해와 알고리즘 설계에 시간 투자
// - 난이도 높은 문제는 부분 점수 획득 전략

// 3. 디버깅 전략
// - 작은 예제로 먼저 테스트
// - 반례 케이스 고려 (최소값, 최대값, 엣지 케이스)
// - 중간 결과 출력으로 오류 파악 (이진 탐색)

// 4. 구현 팁
// - 무한대 값 표현: Integer.MAX_VALUE / 2 (덧셈 고려)
// - 모듈러 연산 주의: (a + b) % MOD = ((a % MOD) + (b % MOD)) % MOD
// - 정확한 타입 사용 (int vs long)
// - 오버플로우 주의 (큰 숫자 계산)
```

---

