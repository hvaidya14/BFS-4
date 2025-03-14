class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
        Queue<int[]> q = new LinkedList<>();
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add( new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()) {
            int[] a = q.poll();
            if (board[a[0]][a[1]] == 'M') {
                board[a[0]][a[1]] = 'X';
                return board;
            }
            int count = countAdjacentMines(a, dirs, board, m , n);
            if (count >= 1) {
                board[a[0]][a[1]] = (char)(count + '0');
            } else if ( count == 0) {
                for (int[] dir :dirs) {
                    int nr = dir[0] + a[0];
                    int nc = dir[1] + a[1];
                    if (nr >=0 && nr < m && nc >=0 && nc < n && (board[nr][nc] != 'B' || Character.isDigit(board[nr][nc]))) {
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }
        return board;
    }
    private int countAdjacentMines(int[] a, int[][] dirs, char[][] board, int m , int n) {
        int count=0;
        for (int[] dir:dirs) {
            int nr = a[0] + dir[0];
            int nc = a[1] + dir[1];
            if(nr >=0 && nr <m && nc >=0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        System.out.println("here end");
        return count;
    }
}
