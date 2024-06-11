package com.mycompany.wordgame;


class BSTNode {

    String word;
    MyLinkedList<FileCount> fileCounts;
    BSTNode left, right;

    public BSTNode(String word, MyLinkedList<FileCount> fileCounts) {
        this.word = word;
        this.fileCounts = fileCounts;
        this.left = null;
        this.right = null;
    }

      public void addOrUpdateFile(String fileName) {
        boolean found = false;
        for (FileCount fc : fileCounts) {
            if (fc.fileName.equals(fileName)) {
                fc.count++;
                found = true;
                break;
            }
        }
        if (!found) {
            fileCounts.addLast(new FileCount(fileName, 1));
        }
    }
    
}
