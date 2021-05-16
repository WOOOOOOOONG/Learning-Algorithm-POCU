package academy.pocu.comp3500.lab2;

import academy.pocu.comp3500.lab2.datastructure.Node;

public final class LinkedList {
    private LinkedList() {
    }

    public static Node append(final Node rootOrNull, final int data) {
        Node newNode = new Node(data);
        Node dummy = rootOrNull;

        if (dummy == null) {
            dummy = newNode;
            return dummy;
        } else {
            while (dummy.getNextOrNull() != null) {
                //System.out.print(dummy.getData() + " ");
                dummy = dummy.getNextOrNull();
                //System.out.println(dummy.getData());
            }
        }

        dummy.setNext(newNode);
        return rootOrNull;
    }

    public static Node prepend(final Node rootOrNull, final int data) {
        Node newNode = new Node(data);
        newNode.setNext(rootOrNull);

        return newNode;
    }

    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        Node newNode = new Node(data);
        Node dummy = rootOrNull;
        Node prev = null;

        if (index < 0 || index > getSize(rootOrNull)) {
            return rootOrNull;
        }

        if (rootOrNull == null) {
            return newNode;
        }

        for (int i = 0; i < index; i++) {
            /*if (dummy.getData() ==) {
                return rootOrNull;
            }*/

            prev = dummy;
            dummy = dummy.getNextOrNull();
        }
        if (prev == null) {
            newNode.setNext(dummy);
            return newNode;
        } else {
            prev.setNext(newNode);
            newNode.setNext(dummy);
            return rootOrNull;
        }
    }

    public static Node removeAt(final Node rootOrNull, final int index) {
        Node removeNode = null;
        Node dummy = rootOrNull;
        Node prev = null;

        if (index < 0 || index > getSize(rootOrNull) || rootOrNull == null) {
            return rootOrNull;
        }

        for (int i = 0; i < index; i++) {
            prev = dummy;
            dummy = dummy.getNextOrNull();
        }

        if (prev == null) {
            removeNode = rootOrNull;
            dummy = rootOrNull.getNextOrNull();
            removeNode = null;

            return dummy;
        } else {
            if (dummy == null) {
                return rootOrNull;
            }
            removeNode = dummy;
            prev.setNext(removeNode.getNextOrNull());

            return rootOrNull;
        }
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        if (rootOrNull == null) {
            return -1;
        }

        Node node = rootOrNull;
        int index = 0;
        boolean bIsFind = false;

        if (node.getData() == data) {
            bIsFind = true;
        }
        while (bIsFind != true && node.getNextOrNull() != null) {
            node = node.getNextOrNull();
            index++;
            if (data == node.getData()) {
                bIsFind = true;
                break;
            }
        }

        if (bIsFind) {
            return index;
        } else {
            return -1;
        }
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
        if (index < 0 || index >= getSize(rootOrNull)) {
            return null;
        }

        Node node = rootOrNull;
        for (int i = 0; i < index; i++) {
            if (node.getNextOrNull() == null) {
                return null;
            }
            node = node.getNextOrNull();
        }

        return node;
    }

    public static Node reverse(final Node rootOrNull) {
        if (rootOrNull == null) {
            return null;
        }

        Node reverseNode = null;
        Node newRootNode = null;
        Node dummy = rootOrNull;
        Node dummy2 = rootOrNull;
        int index = 0;

        index++;
        while (dummy.getNextOrNull() != null) {
            dummy = dummy.getNextOrNull();
            index++;
        }

        for (int i = index - 1; i >= (int)(index / 2); i--) {
            int j = index - 1 - i;
            dummy = getOrNull(rootOrNull, i); // 뒷값
            dummy2 = getOrNull(rootOrNull, j); // 앞값
            Node tempNode = null;
            if (newRootNode != null) {
                tempNode = getOrNull(rootOrNull, i + 1);
            }

            if (dummy == dummy2) {
                break;
            }

            dummy = new Node(dummy.getData()); // 13
            dummy.setNext(dummy2.getNextOrNull());// 13
            if (j != 0) {
                getOrNull(newRootNode, j - 1).setNext(dummy);
            }

            dummy2 = new Node(dummy2.getData()); // 11
            if (j != 0) {
                getOrNull(newRootNode, i - 1).setNext(dummy2);
                dummy2.setNext(tempNode);
            } else {
                getOrNull(rootOrNull, i - 1).setNext(dummy2);
            }

            if (i == index - 1) {
                newRootNode = dummy;
            }
            /*
            Node n = newRootNode;
            if (n != null) {
                System.out.print(n.getData() + ", ");
                while (n.getNextOrNull() != null) {
                    n = n.getNextOrNull();
                    System.out.print(n.getData() + ", ");
                }
                System.out.println();
            }
             */
        }

        /*
        for (int i = index - 1; i >= 0; i--) {
            reverseNode = append(reverseNode, getOrNull(rootOrNull, i).getData());
            Node tempNode = getOrNull(rootOrNull, i);
            tempNode = null;
        }*/



        return newRootNode;
    }

    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        if (root0OrNull == null && root1OrNull == null) {
            return null;
        }

        Node dummy1 = root0OrNull;
        Node dummy2 = root1OrNull;
        Node newNode = null;
        Node newRootNode = null;
        Node memoryNode = null;
        boolean bFirst = false;

        // 1. 두 값을 순서대로 넣기
        while (true) {
            if (!bFirst && (dummy1 == null || dummy2 == null)) {
                bFirst = true;
                continue;
            } else if (!bFirst) {
                newNode = new Node(dummy1.getData());
                memoryNode = newNode;
                newRootNode = memoryNode;

                newNode = new Node(dummy2.getData());
                memoryNode.setNext(newNode);
                memoryNode = newNode;

                bFirst = true;
            } else if ((dummy1 != null && dummy2 != null) &&
                    dummy1.getNextOrNull() != null && dummy2.getNextOrNull() != null) {
                dummy1 = dummy1.getNextOrNull();
                newNode = new Node(dummy1.getData());
                memoryNode.setNext(newNode);
                memoryNode = newNode;

                dummy2 = dummy2.getNextOrNull();
                newNode = new Node(dummy2.getData());
                memoryNode.setNext(newNode);
                memoryNode = newNode;
            } else if (dummy1 != null && dummy1.getNextOrNull() != null) {
                dummy1 = dummy1.getNextOrNull();
                newNode = new Node(dummy1.getData());
                memoryNode.setNext(newNode);
                memoryNode = newNode;
            } else if (dummy2 != null && dummy2.getNextOrNull() != null) {
                dummy2 = dummy2.getNextOrNull();
                newNode = new Node(dummy2.getData());
                memoryNode.setNext(newNode);
                memoryNode = newNode;
            } else {
                break;
            }
        }

        // 결과 리스트의 첫 번째 노드를 반환
        return newRootNode;
    }

    public static int getSize(Node rootOrNull) {
        if (rootOrNull == null) {
            return 0;
        }

        Node dummy = rootOrNull;
        int index = 1;
        while (dummy.getNextOrNull() != null) {
            dummy = dummy.getNextOrNull();
            index++;
        }

        return index;
    }
}