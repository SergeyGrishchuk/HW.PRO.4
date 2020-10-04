public class ABC {
    static Object mon = new Object();
    static volatile char currentChar = ' ';
    static final int num = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentChar != ' ') {
                            mon.wait();
                        }
                        System.out.print("A");
                        currentChar = '\\';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentChar != '\\') {
                            mon.wait();
                        }
                        System.out.print("B");
                        currentChar = '_';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currentChar != '_') {
                            mon.wait();
                        }
                        System.out.print("C ");
                        currentChar = ' ';
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
