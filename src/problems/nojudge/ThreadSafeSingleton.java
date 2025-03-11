package problems.nojudge;

public class ThreadSafeSingleton {

    public static class EagerSingleton {
        private static final EagerSingleton INSTANCE = new EagerSingleton();

        private EagerSingleton() {
        }

        public static EagerSingleton getInstance() {
            return INSTANCE;
        }

        public void doSomething() {
            System.out.println("EagerSingleton is doing something");
        }
    }

    public static class DCLSingleton {
        private static volatile DCLSingleton instance;

        private DCLSingleton() {
            if (instance != null) {
                throw new IllegalStateException("Already initialized");
            }
        }

        public static DCLSingleton getInstance() {
            DCLSingleton result = instance;

            if (result == null) {
                synchronized (DCLSingleton.class) {
                    result = instance;
                    if (result == null) {
                        instance = result = new DCLSingleton();
                    }
                }
            }

            return result;
        }

        public void doSomething() {
            System.out.println("DCLSingleton is doing something");
        }
    }

    public static class BillPughSingleton {
        private BillPughSingleton() {
        }

        private static class SingletonHolder {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }

        public static BillPughSingleton getInstance() {
            return SingletonHolder.INSTANCE;
        }

        public void doSomething() {
            System.out.println("BillPughSingleton is doing something");
        }
    }

    public enum EnumSingleton {
        INSTANCE;

        public void doSomething() {
            System.out.println("EnumSingleton is doing something");
        }
    }

    public static void main(String[] args) {
        EagerSingleton eager = EagerSingleton.getInstance();
        eager.doSomething();

        DCLSingleton dcl = DCLSingleton.getInstance();
        dcl.doSomething();

        BillPughSingleton billPugh = BillPughSingleton.getInstance();
        billPugh.doSomething();

        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        enumSingleton.doSomething();

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + DCLSingleton.getInstance().hashCode());
        };

        for (int i = 0; i < 5; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}
