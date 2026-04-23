# Task - 2

## Q1. What is the exact cause of ConcurrentModificationException in Java?
ConcurrentModificationException occurs when a collection in our case ArrayList is structurally modified while it is being iterated.

## Q2. What code pattern at line 142 most likely triggered this error?
Based on the stack trace there would be some transaction being filtered in a loop wherein instead of removing via the iterator its being removed from the arraylist itself
```java
Iterator<Transaction> it = transactions.iterator();
while (it.hasNext()) {
    Transaction t = it.next();
    transactions.remove(t); // modifying list directly instead of iterator
}
```

## Q3 Minimal code change to resolve safely
As we are going with minimal code change, the safe method would be
```java
Iterator<Transaction> it = transactions.iterator(); 
while (it.hasNext()) { 
    if (someCondition(it.next())) { 
        it.remove(); // FIX: safe removal via iterator 
    } 
}
```