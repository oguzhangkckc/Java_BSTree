/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.wordgame;

import java.util.NoSuchElementException;

/**
 *
 * @author murat
 */
class BinarySearchTree {

    public BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean insertOrUpdate(String word, String fileName) {
        boolean isNewInsert = false;
        root = insertOrUpdate(root, word.toLowerCase(), fileName, isNewInsert);
        return isNewInsert;
    }

    private BSTNode insertOrUpdate(BSTNode node, String word, String fileName, boolean isNewInsert) {
        if (node == null) {
            MyLinkedList<FileCount> list = new MyLinkedList<>();
            list.addLast(new FileCount(fileName, 1));
            isNewInsert = true;
            return new BSTNode(word, list);
        } else if (node.word.equals(word)) {
            node.addOrUpdateFile(fileName);
            isNewInsert = false;
            return node;
        } else if (word.compareTo(node.word) < 0) {
            node.left = insertOrUpdate(node.left, word, fileName, isNewInsert);
        } else {
            node.right = insertOrUpdate(node.right, word, fileName, isNewInsert);
        }
        return node;
    }

    public MyLinkedList<String> collectWordsInOrder() {
        MyLinkedList<String> words = new MyLinkedList<>();
        inorderRec(root, words);
        return words;
    }

    private void inorderRec(BSTNode root, MyLinkedList<String> words) {
        if (root != null) {
            inorderRec(root.left, words);
            for (FileCount fc : root.fileCounts) {
                words.addLast(root.word + " in " + fc.fileName + ", frequency=" + fc.count);
            }
            inorderRec(root.right, words);
        }
    }

    public MyLinkedList<String> collectWordsPreOrder() {
        MyLinkedList<String> words = new MyLinkedList<>();
        preorderRec(root, words);
        return words;
    }

    private void preorderRec(BSTNode root, MyLinkedList<String> words) {
        if (root != null) {
            for (FileCount fc : root.fileCounts) {
                words.addLast(root.word + " in " + fc.fileName + ", frequency=" + fc.count);
            }
            preorderRec(root.left, words);
            preorderRec(root.right, words);
        }
    }

    public MyLinkedList<String> collectWordsPostOrder() {
        MyLinkedList<String> words = new MyLinkedList<>();
        postorderRec(root, words);
        return words;
    }

    private void postorderRec(BSTNode root, MyLinkedList<String> words) {
        if (root != null) {
            postorderRec(root.left, words);
            postorderRec(root.right, words);
            for (FileCount fc : root.fileCounts) {
                words.addLast(root.word + " in " + fc.fileName + ", frequency=" + fc.count);
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public MyLinkedList<String> searchFilesContainingWord(String searchWord) {
        return searchRec(root, searchWord.toLowerCase());
    }

    private MyLinkedList<String> searchRec(BSTNode root, String searchWord) {
        if (root == null) {

            throw new NoSuchElementException("Word not found in any files.");
        }
        if (searchWord.equals(root.word)) {

            MyLinkedList<String> fileDetails = new MyLinkedList<>();
            for (FileCount fc : root.fileCounts) {
                fileDetails.addLast(fc.fileName + " (frequency=" + fc.count + ")");
            }
            return fileDetails;
        } else if (searchWord.compareTo(root.word) < 0) {

            return searchRec(root.left, searchWord);
        } else {

            return searchRec(root.right, searchWord);
        }
    }
}
