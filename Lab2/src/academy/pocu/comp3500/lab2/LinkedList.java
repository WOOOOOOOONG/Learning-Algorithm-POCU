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

    /* insertAt
    * 1. index 위치까지 이동
    * 2. 연결관계 유지
    * */
    public static Node insertAt(final Node rootOrNull, final int index, final int data) {
        Node newNode = new Node(data);
        Node dummy   = rootOrNull;
        Node prev    = null;

        if (index < 0) {
            return rootOrNull;
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

    /* removeAt
    * 1. removeNode를 통해 지울 내용 저장, prev로 이전 노드 내용 저장
    * 2. index 위치까지 이동(삭제할 수 없는 위치라면 return)
    * 3. 위치관계 재수정
    * */
    public static Node removeAt(final Node rootOrNull, final int index) {
        Node removeNode = null;
        Node dummy   = rootOrNull;
        Node prev    = null;

        if (index < 0) {
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
            removeNode = dummy;
            prev.setNext(removeNode.getNextOrNull());

            return rootOrNull;
        }
    }

    public static int getIndexOf(final Node rootOrNull, final int data) {
        Node node = rootOrNull;
        int index = 0;
        boolean bIsFind = false;

        while (rootOrNull.getNextOrNull() != null) {
            if (data == node.getData()) {
                bIsFind = true;
                break;
            }
            index++;
            node = node.getNextOrNull();
        }

        if (bIsFind) {
            return index;
        } else {
            return -1;
        }
    }

    public static Node getOrNull(final Node rootOrNull, final int index) {
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
        Node reverseNode = rootOrNull;
        Node newRoot = null;
        int[] data;
        int index = 0;
        int i = 0;

        index++;
        while (reverseNode.getNextOrNull() != null) {
            reverseNode = reverseNode.getNextOrNull();
            index++;
        }
        reverseNode = rootOrNull;
        data = new int[index];

        data[i++] = reverseNode.getData();
        while (reverseNode.getNextOrNull() != null) {
            reverseNode = reverseNode.getNextOrNull();
            data[i++] = reverseNode.getData();
        }

        for (int j = index-1; j >= 0 ; j--) {
            if (j == index-1) {
                newRoot = new Node(data[j]);
            } else {
                append(newRoot, data[j]);
            }
        }

        return newRoot;
    }

    /* interleaveOrNull
    * 1. root00rNull, root10rNull 내에 데이터를 각각 배열로 보관한다
    * 2. 새 노드에 추출된 데이터들을 기반으로 첫 번째 노드부터 시작하여 두 번째 노드를 번갈아서 데이터를 넣는다.
    * 3. 반환한다
    * */
    public static Node interleaveOrNull(final Node root0OrNull, final Node root1OrNull) {
        Node dummy;
        Node newNode;
        Node newRootNode;
        int[] arr1;
        int[] arr2;
        int[] arr3;
        int arr1Index = 0;
        int arr2Index = 0;
        int arr3Index = 0;

        // 1. 두 리스트의 총 개수
        dummy = root0OrNull;
        arr1Index++;
        while(dummy.getNextOrNull() != null) {
            dummy = dummy.getNextOrNull();
            arr1Index++;
        }

        dummy = root1OrNull;
        arr2Index++;
        while(dummy.getNextOrNull() != null) {
            dummy = dummy.getNextOrNull();
            arr2Index++;
        }

        arr1 = new int[arr1Index];
        arr2 = new int[arr2Index];

        // 2. 각 노드의 데이터들을 배열에 저장
        dummy = root0OrNull;
        for (int i = 0; i < arr1Index; i++) {
            arr1[i] = dummy.getData();
            dummy = dummy.getNextOrNull();
        }

        dummy = root1OrNull;
        for (int i = 0; i < arr2Index; i++) {
            arr2[i] = dummy.getData();
            dummy = dummy.getNextOrNull();
        }

        // 3. 각 노드의 데이터들 합치기
        arr3Index = arr1Index + arr2Index;
        arr3 = new int[arr3Index];
        int i = 0;
        int j = 0;
        int k = 0;
        while (true) {
            if (i < arr1Index && j < arr2Index) {
                arr3[k++] = arr1[i++];
                arr3[k++] = arr2[j++];
            } else if (i < arr1Index) {
                arr3[k++] = arr1[i++];
            } else if (j < arr2Index) {
                arr3[k++] = arr2[j++];
            } else {
                break;
            }
        }

        // 4. 합친 데이터를 새로운 리스트로 만들기
        newNode = new Node(arr3[0]);
        newRootNode = newNode;
        for (int l = 1; l < arr3Index; l++) {
            Node n = new Node(arr3[l]);
            newNode.setNext(n);
            newNode = newNode.getNextOrNull();
        }

        // 결과 리스트의 첫 번째 노드를 반환
        return newRootNode;
    }
}