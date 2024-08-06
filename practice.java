import java.util.Scanner;

class practice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int a[] = new int[n];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        
        System.out.println("Array before sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        
        radixsort(a, n);
        
        System.out.println("\nArray after sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        
        scanner.close();
    }

    static void radixsort(int a[], int n)
    {
        int buckets[][]=new int[10][n];
        int count[]=new int[10];
        int passes, large, div, bucketno, i, j, k;
        //find largest number in a[]
        large=a[0];
        for(i=1;i<n;i++)
        {
            if(a[i]>large)
            {
                large=a[i];
            }
        }

        //find number of digit in large number
        passes=0;
        while(large>0)
        {
            passes++;
            large=large/10;
        }
        //perform radix sort
        div=1;
        for(i=1;i<=9;i++)
        {
            count[i]=0;
        }
        //insert element in buckets
        for(j=0;j<n;j++)
        {
            bucketno=(a[j]/div)%10;
            buckets[bucketno][count[bucketno]]=a[j];
            count[bucketno]++;
        }
        //merge element and store in the list
        j=0;
        for(bucketno=0;bucketno<=9;bucketno++)
        {
            for(k=0;k<count[bucketno];k++)
            {
                a[j++]=buckets[bucketno][k];
                div=div*10;
            }
        }
    }
}




/*
********* Bubble Sort *********
import java.util.Scanner;

public class practice
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int a[] = new int[n];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        
        System.out.println("Array before sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        
        bubblesort(a, n);
        
        System.out.println("\nArray after sort:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        
        scanner.close();
    }

    public static void bubblesort(int a[], int n)
    {
        int i, j, temp;
        for (i = 1; i < n; i++)
        {
            for (j = 0; j < n - i; j++)
            {
                if (a[j] > a[j + 1])
                {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
}


********* Selection Sort *********
import java.util.Scanner;

public class practice
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int a[] = new int[n];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++)
        {
            a[i] = scanner.nextInt();
        }
        
        System.out.println("Array before sort:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(a[i] + " ");
        }
        
        selectionsort(a, n);
        
        System.out.println("\nArray after sort:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(a[i] + " ");
        }
        
        scanner.close();
    }

        public static void selectionsort(int a[], int n)
        {
        int i, j, temp;
        for (i = 0; i < n - 1; i++)
        {
            int minIndex = i;
            for (j = i + 1; j < n; j++)
            {
                if (a[j] < a[minIndex])
                {
                    minIndex = j;
                }
            }
            if (minIndex != i)
            {
                temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }
}


********* Insertion Sort *********
import java.util.Scanner;

public class practice
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        
        int a[] = new int[n];
        
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++)
        {
            a[i] = scanner.nextInt();
        }
        
        System.out.println("Array before sort:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(a[i] + " ");
        }
        
        insertionsort(a, n);
        
        System.out.println("\nArray after sort:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(a[i] + " ");
        }
        
        scanner.close();
    }

        public static void insertionsort(int a[], int n)
        {
        int i, j, temp;
        for (i = 1; i < n; i++)
        {
            temp=a[i];
            for(j=i-1;j>=0&&a[j]>temp;j--)
            {
                a[j+1]=a[j];
            }
            a[j+1]=temp;
        }
    }
}
*/