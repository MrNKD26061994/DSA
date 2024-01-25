import java.util.ArrayList;
import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {

        // Tìm vị trí lớn thứ 2 theo thứ tự của nó trong mảng
        int[] array = {2,4,5,6,3,4,2,6,2,6,5,124};
        searchTop2(array);

        int[] a = {50,2,4,2,3,52,3,52};
//        int[] a = {2,2,2,2,2,2,2,2};
        // In ra số lần xuất hiện của các phần tử trong mảng?
        filterNumber(a);

        // Mảng chưa sắp xếp
        printArray(a);
//        selectionSort(a);
//        bubbleSort(a);
//        insertionSort(a);
//        mergeSort(a, 0, a.length - 1);
        quickSort(a, 0, a.length - 1);
        printArray(a);
        System.out.println("So " + 2 + ": nam o chi muc " + linearSearch(a, 2) + " theo tim kiem tuyen tinh");
        System.out.println("So " + 2 + ": nam o chi muc " + binarySearch(a, 2) + " theo tim kiem nhi phan");
        System.out.println("So " + 2 + ": nam o chi muc " + ternarySearch(a, 2) + " theo tim kiem tam phan");
        System.out.println("So " + 2 + ": nam o chi muc " + jumpSearch(a, 2) + " theo tim kiem jumpSearch");
        System.out.println("So " + 2 + ": nam o chi muc " + exponentialSearch(a, 2) + " theo tim kiem exponentialSearch");

    }

    // Tìm vị trí lớn thứ 2 theo thứ tự của nó trong mảng
    public static void searchTop2(int[] a) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap STT cua so max2 can tim bat dau tu 1 (vi tri cuoi cung thi nhap bang 'end'): ");
        String n = scanner.nextLine();
        if(a.length < 2) {
            System.out.println("Mang cho it hon 2 phan tu");
        } else {
            ArrayList<Integer> list = searchListMax2(a);
            if(n.equals("end")) {
                System.out.println("So max2 cuoi cung la :" + (list.get(list.size() - 2) + 1));
            } else {
                try {
                    int index = Integer.parseInt(n);
                    if(index >= list.size()) {
                        System.out.println("Vi tri cua ban vuot qua so luong so max2");
                    } else {
                        System.out.println("Vi tri so max2 thu " + index + " la: " + (list.get(index - 1) + 1) );
                    }
                } catch (Exception e) {
                    System.out.println("Ban chi duoc nhap so hoac 'end'");
                }
            }
        }
    }

    private static ArrayList<Integer> searchListMax2(int[] a) {
        ArrayList<Integer> ar = new ArrayList<>();
        int sizeArray = a.length;
        if(a[0] > a[1]) {
            ar.add(1);
            ar.add(0);
        } else {
            ar.add(0);
            ar.add(1);
        }
        for (int i = 3; i < sizeArray; i++) {
            if(a[i] == a[ar.get(ar.size() - 1)]) {
                if(a[i] == a[ar.get(ar.size() - 2)]) {
                    ar.add(i);
                } else {
                    int temp = ar.get(ar.size() - 1);
                    ar.clear();
                    ar.add(temp);
                    ar.add(i);
                }
            } else if(a[i] > a[ar.get(ar.size() - 1)]) {
                if(a[ar.get(ar.size() - 1)] == a[ar.get(ar.size() - 2)]) {
                    ar.add(i);
                } else {
                    int temp = ar.get(ar.size() - 1);
                    ar.clear();
                    ar.add(temp);
                    ar.add(i);
                }
            }
        }
        return ar;
    }

    private static void filterNumber(int[] a) {
        bubbleSort(a);
        int arraySize = a.length;
        int temp = a[0];
        int count = 0;
        for (int i = 0; i < arraySize; i++) {
            if(a[i] == temp) {
                count++;
            } else {
                System.out.println("So: " + temp + " duoc lap lai: " + count + " lan");
                count = 1;
                temp = a[i];
            }
        }
        System.out.println("So: " + temp + " duoc lap lai: " + count + " lan");
    }

    // Thuật toán sắp xếp nổi bọt (Bubble Sort)
    public static void bubbleSort(int[] arrays) {
        int arraySize = arrays.length;
        for (int i = 0; i < arraySize; i++) {
            for (int j = i + 1; j < arraySize; j++) {
                if(arrays[i] > arrays[j]) {
                    int temp = arrays[i];
                    arrays[i] = arrays[j];
                    arrays[j] = temp;
                }
            }
        }
    }

    // Thuật toán sắp xếp chọn (Selection Sort)
    public static void selectionSort(int[] arrays) {
        int sizeArrays = arrays.length;
        for (int i = 0; i < sizeArrays; i++) {
            int indexMin = i;
            for (int j = i + 1; j < sizeArrays; j++) {
                if(arrays[j] < arrays[indexMin]) {
                    indexMin = j;
                }
            }
            int temp = arrays[i];
            arrays[i] = arrays[indexMin];
            arrays[indexMin] = temp;
        }
    }

    // Thuật toán sắp xếp chèn (Insertion sort)
    public static void insertionSort(int[] arrays) {
        int sizeArrays = arrays.length;
        for (int i = 1; i < sizeArrays - 1; i++) {
            int key = arrays[i];
            int j = i - 1;
            for (;j >= 0 && key < arrays[j]; j--) {
                arrays[j + 1] = arrays[j];
            }
            arrays[j + 1] = key;
        }
    }

    // Thuật toán sắp xếp trộn (Merge Sort)
    public static void mergeSort(int[] arrays, int leftIndex, int rightIndex) {
        if(leftIndex >= rightIndex) {
            return;
        }
        int middle = leftIndex + (rightIndex - leftIndex) / 2;
        mergeSort(arrays, leftIndex, middle);
        mergeSort(arrays, middle + 1, rightIndex);
        merge(arrays, leftIndex, middle, rightIndex);
    }
    private static void merge(int[] arrays, int left, int middle, int right) {
        // Khởi tạo mảng left và right có độ dài ứng với left, middle, right Index
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;
        int[] arrayLeft = new int[sizeLeft];
        int[] arrayRight = new int[sizeRight];

        // Sao chép dữ liệu cho 2 mảng left và right
        for (int i = 0; i < sizeLeft; i++) {
            arrayLeft[i] = arrays[left + i];
        }
        for (int i = 0; i < sizeRight; i++) {
            arrayRight[i] = arrays[middle + 1 + i];
        }

        // Sắp xếp 2 mảng left, right theo đúng thứ tự

        int leftIndex = 0;
        int rightIndex = 0;
        for (int i = left; i < right + 1; i++) {
            if(leftIndex < sizeLeft && rightIndex < sizeRight) {
                if(arrayLeft[leftIndex] <= arrayRight[rightIndex]) {
                    arrays[i] = arrayLeft[leftIndex];
                    leftIndex++;
                } else {
                    arrays[i] = arrayRight[rightIndex];
                    rightIndex++;
                }
            } else if(leftIndex < sizeLeft) {
                arrays[i] = arrayLeft[leftIndex];
                leftIndex++;
            } else if (rightIndex < sizeRight){
                arrays[i] = arrayRight[rightIndex];
                rightIndex++;
            }
        }
    }

    // Thuật toán sắp xếp nhanh (Quick Sort)
    public static void quickSort(int[] array, int low, int high) {
        if(low < high) {
            int indexPi = quick(array, low, high);
            quickSort(array, low, indexPi - 1);
            quickSort(array, indexPi + 1, high);
        }
    }
    private static int quick(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if(array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }


    // Thuật toán tìm kiếm tuyến tính (Linear Search)
    public static int linearSearch(int[] arrays, int x) {
        int sizeArray = arrays.length;
        for (int i = 0; i < sizeArray - 1; i++) {
            if (arrays[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // Thuật toán tìm kiếm nhị phân (Binary Search)
    public static int binarySearch(int[] array, int x) {
        int left = 0;
        int right = array.length - 1;
        for (int i = left; i <= right; i++) {
            int mid = (left + right) / 2;
            if(array[mid] == x) {
                return mid;
            } else if(array[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // Thuật toán tìm kiếm bậc ba (Ternary Search)
    public static int ternarySearch(int[] array, int x) {
        int sizeArray = array.length;
        int left = 0;
        int right = sizeArray - 1;
        while (right - left >= 0) {
            int midIndex = (right - left) / 3;
            int midLeft = left + midIndex;
            int midRight = right - midIndex;
            if(x == array[midLeft]) {
                return midLeft;
            } else if(x == array[midRight]) {
                return midRight;
            } else if(x < array[midLeft]) {
                right = midLeft - 1;
            } else if(x > array[midRight]) {
                left = midRight;
            } else {
                left = midLeft + 1;
                right = midRight - 1;
            }
        }
        return -1;
    }

    // Thuật toán Jump Search
    public static int jumpSearch(int[] array, int x) {
        int sizeArray = array.length;
        int start = 0;
        int blockSize = (int) Math.sqrt(sizeArray);
        int next = blockSize;

        // start là vị trí trước khi nhảy
        // Nếu start vượt quá size hoặc x lớn hơn giá trị cuối cùng của bước nhảy thì thoát vòng lặp
        while (start < sizeArray && x > array[next - 1]) {
            start = next;
            next = next + blockSize;
            if(next >= sizeArray) {
                next = sizeArray;
            }
        }
        for (int i = start; i < next; i++) {
            if(x == array[i]) {
                return i;
            }
        }
        return -1;
    }

    // Thuật toán Depth First Search
//    public static void dfs()
    public static int exponentialSearch(int[] array, int x) {
        int size = array.length;
        if(array[0] == x) {
            return 0;
        }
        int i = 1;
        while (i < size - 1 && array[i] <= x) {
            i = i * 2;
        }
        return binarySearch(array, x);
    }
    // Thuật toán Exponential Search

    public static void printArray(int[] arrays) {
        int sizeArrays = arrays.length;
        for (int i = 0; i < sizeArrays; i++) {
            if(i != (sizeArrays - 1)) {
                System.out.print(arrays[i] + ", ");
            } else {
                System.out.println(arrays[i] + ".");
            }
        }
    }
}
