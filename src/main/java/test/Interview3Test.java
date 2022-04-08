package test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Interview3Test {

    /**
     * 通过hash表+双向链表的方式实现(LinkedHashMap),查找和插入的时间复杂度都是O(1).
     */
    private static class LRUCache {

        private LRUMap map;

        public LRUCache(int capacity) {
            map = new LRUMap(capacity, 0.75f, true);
        }

        public int get(int key) {
            return Integer.valueOf(map.get(key).toString());
        }

        public void put(int key, int value) {
            map.put(key, value);
        }

        private static class LRUMap extends LinkedHashMap {
            private int capacity;

            public LRUMap(int initialCapacity, float loadFactor, boolean accessOrder) {
                super(initialCapacity, loadFactor, accessOrder);
                this.capacity = initialCapacity;
            }

            public LRUMap(int initialCapacity) {
                super(initialCapacity);
                this.capacity = initialCapacity;
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry entry) {
                return this.size() > this.capacity;
            }

            public Object get(int key) {
                return super.getOrDefault(key, -1);
            }

            public Object put(int key, int value) {
                return super.put(key, value);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2 /* 缓存容量 */);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));  // 返回 1
        cache.put(3, 3);// 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));//-1
        cache.put(4, 4); // 该操作会使得关键字 1 作废
        System.out.println(cache.get(-1));//-1
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}