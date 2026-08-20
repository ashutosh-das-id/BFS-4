/**
 * Time Complexity:  O(n^2)  where n is the number of rows
 * Space Complexity: O(n^2) size of the array
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        int[] newBoard = createOneDimensionalArray(board);
        if (newBoard.length <= 6)
            return 1;
        return bfs(newBoard);

    }

    int bfs(int[] newBoard) {
        int totalLength = newBoard.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int count = 0;

        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poppedIndex = queue.poll();

                for (int j = 1; j <= 6; j++) {
                    int value = 0;
                    int index = poppedIndex + j;
                    if (index < totalLength) {
                        value = newBoard[index];
                        if (value == totalLength - 1) {
                            return count;
                        }
                        if (value != -2) {
                            queue.add(value);
                        }
                    }
                    newBoard[index] = -2;
                }

            }
        }
        return -1;

    }

    int[] createOneDimensionalArray(int[][] board) {

        int n = board.length;
        int[] newBoard = new int[n * n];

        int boardRow = n - 1;
        int boardCol = 0;
        boolean leftToReft = true;
        for (int i = 0; i < n * n; i++) {
            newBoard[i] = i;
            if (board[boardRow][boardCol] != -1) {
                newBoard[i] = board[boardRow][boardCol] - 1;
            }
            if (leftToReft) {
                boardCol++;
                if (boardCol == n) {
                    leftToReft = !leftToReft;
                    boardRow--;
                    boardCol = n - 1;
                }
            } else {
                boardCol--;
                if (boardCol < 0) {
                    leftToReft = !leftToReft;
                    boardRow--;
                    boardCol = 0;
                }
            }
        }

        return newBoard;

    }

}