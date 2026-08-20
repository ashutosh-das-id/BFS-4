/**
 * Time Complexity:  O(m*n)  where m is the number of rows, n is the number of columns
 * Space Complexity: O(m*n) where m is the number of rows, n is the number of columns
 */
class Solution {
    int[][] dirs = { { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<Pair<Integer, Integer>> queueOfIndices = new LinkedList<>();

        Pair<Integer, Integer> startIndex = new Pair<>(click[0], click[1]);

        if (board[startIndex.getKey()][startIndex.getValue()] == 'M') {
            board[startIndex.getKey()][startIndex.getValue()] = 'X';
            return board;
        }

        queueOfIndices.offer(startIndex);
        board[startIndex.getKey()][startIndex.getValue()] = 'B';
        while (!queueOfIndices.isEmpty()) {
            Pair<Integer, Integer> square = queueOfIndices.poll();
            int numOfMinesAtNeighbor = 0;

            for (Pair<Integer, Integer> neighbor : getNeighbors(square, board)) {
                if (board[neighbor.getKey()][neighbor.getValue()] == 'M') {
                    numOfMinesAtNeighbor++;
                    board[square.getKey()][square.getValue()] = (char) (numOfMinesAtNeighbor + '0');
                }
            }

            if (numOfMinesAtNeighbor == 0) {
                for (Pair<Integer, Integer> neighbor : getNeighbors(square, board)) {
                    if (board[neighbor.getKey()][neighbor.getValue()] == 'E') {
                        queueOfIndices.offer(neighbor);
                        board[neighbor.getKey()][neighbor.getValue()] = 'B';
                    }
                }
            }

        }
        return board;
    }

    List<Pair<Integer, Integer>> getNeighbors(Pair<Integer, Integer> square, char[][] board) {
        int m = board.length;
        int n = board[0].length;

        List<Pair<Integer, Integer>> neighbors = new ArrayList<>();
        int row = square.getKey();
        int col = square.getValue();

        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow < m && newRow >= 0 && newCol < n && newCol >= 0) {
                Pair<Integer, Integer> pair = new Pair<>(newRow, newCol);
                neighbors.add(pair);
            }
        }
        return neighbors;

    }

}