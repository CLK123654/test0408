package test;

public class Interview2Test {

    public static final class Stack<T> {
        private static final int DEFAULT_CAPACITY = 8;

        private Object[] dataArr;

        private int top;

        public Stack() {
            initStack();
        }

        private void initStack() {
            top = -1;
            ensureCapacity(DEFAULT_CAPACITY);
        }

        private void ensureCapacity(int newCapacity) {
            if (top + 1 >= newCapacity) {
                return;
            }
            Object[] old = dataArr;
            this.dataArr = new Object[newCapacity];
            for (int i = 0; i <= top; i++) {
                dataArr[i] = old[i];
            }
        }

        public void push(T element) {
            if (top + 1 == dataArr.length) {
                ensureCapacity((dataArr.length << 1) + 1);
            }
            dataArr[++top] = element;
        }

        public T pop() {
            if (empty()) {
                return null;
            }
            return (T) dataArr[top--];
        }

        public T peak() {
            if (empty()) {
                return null;
            }
            return (T) dataArr[top];
        }

        public boolean empty() {
            return top == -1;
        }

    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack.empty());
        stack.pop();
        stack.pop();
        System.out.println(stack.peak());
        System.out.println(stack.pop());
        System.out.println(stack.empty());
        System.out.println(stack.pop());
    }
}