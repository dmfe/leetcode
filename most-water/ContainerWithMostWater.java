
/**
 * ContainerWithMostWater
 */
public class ContainerWithMostWater {

    private static class ContainerData {
        private int left = 0;
        private int right = 0;
        private int capacity = 0;

        ContainerData(int left, int right, int capacity) {
            this.left = left;
            this.right = right;
            this.capacity = capacity;
        }

        int getLeft() {
            return left;
        }

        int getRigth() {
            return right;
        }

        int getCapacity() {
            return capacity;
        }
    }

    public static void main (String[] args) {
        ContainerData data = findMostWaterContainer(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println("Most water capacity: " + data.getCapacity());
        System.out.println("Left: " + data.getLeft());
        System.out.println("Right: " + data.getRigth());
    }

    static ContainerData findMostWaterContainer(int[] numbers) {
        int maxArea = 0;
        int leftResult = 0;
        int rightResult = numbers.length - 1;

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int area = Math.min(numbers[left], numbers[right]) * (right - left);
            if (area > maxArea) {
                maxArea = area;
                leftResult = left;
                rightResult = right;
            }

            if (numbers[left] < numbers[right])
                left++;
            else
                right--;
        }


        return new ContainerData(leftResult, rightResult, maxArea);
    }
}
