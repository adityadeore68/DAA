#Name : Aditya Deore
#PRN : 123B1F019
public class BubbleSortExample {

    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        int temp;
        for (int i = 0; i < n - 1; i++) { 
            for (int j = 0; j < (n - i - 1); j++) { 
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); 
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {50, 10, 40, 30, 20};
        int n = arr.length;

        System.out.println("Array size is = " + n);

        System.out.println("Original array:");
        printArray(arr);

        bubbleSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}

