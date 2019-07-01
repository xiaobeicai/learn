import datastructure.ListNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 146.LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 capacity )
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.put(4,4);    // evicts key 1
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
*/
class doubleListNode{
    doubleListNode pre;
    doubleListNode pos;
    int key;
    int val;
    public doubleListNode(int key, int val){
        this.key = key;
        this.val = val;
    }
}
public class LRUCache {
    public Map<Integer,doubleListNode> map;
    public int size;
    public doubleListNode head;
    public doubleListNode tail;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        size = capacity;
        head = new doubleListNode(Integer.MIN_VALUE,Integer.MIN_VALUE);
        tail = new doubleListNode(Integer.MAX_VALUE, Integer.MAX_VALUE);

        head.pre = null;
        tail.pos = null;

        head.pos = tail;
        tail.pre = head;
    }

    public void add(doubleListNode node){
            node.pos = head.pos;
            node.pre = head;

            head.pos = node;
            node.pos.pre = node;
    }

    public void remove(doubleListNode node){
        doubleListNode pre = node.pre;
        doubleListNode pos = node.pos;

        pre.pos = pos;
        pos.pre = pre;
    }

    public void moveToHead(doubleListNode node){
        this.remove(node);
        this.add(node);
    }

    public int removeTail(){
        doubleListNode node = tail.pre;
        int key = node.key;
        remove(node);
        return key;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            doubleListNode node = map.get(key);
            this.moveToHead(node);
            return node.val;
        }
        else return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            doubleListNode node = map.get(key);
            node.val = value;
            map.put(key,node);
            this.remove(node);
            this.moveToHead(node);
        }
        else{
            doubleListNode node = new doubleListNode(key,value);
            if (size > 0){
                this.add(node);
                size--;
            }
            else {
                int temp = this.removeTail();
                map.remove(temp);
                this.add(node);
            }
            map.put(key, node);
        }
    }
}
