package day0917;


import java.util.Arrays;
import java.util.Collections;

/**
 * 0917 지뢰찾기 코딩테스트(프로그래머스)
 * 해당 위치 클릭시 열리는 칸의 갯수 구하기
 * * : 지뢰
 * - : 열리는 칸
 * 0~9 : 주변 지뢰 갯수
 */

class Solution {
    //지뢰
    private static final String BOUND = "*";
    //열리는 칸
    private static final String OPEN = "-";


    private int countMine(int rowIndex, int columIndex ,String[][] array) {
        int count = 0;
        int rowLength = rowIndex + 2; //검사할 행 범위
        int columLength = columIndex + 2;//검사할 열 범위

        for(int i=rowIndex-1 ; i<rowLength ; i++){
            for(int j=columIndex-1 ; j<columLength ; j++){
                //카운드 상승 제외
                if(i<0 || j<0 || i>array.length-1 ||j>array[i].length-1  || (i == rowIndex && j ==columIndex)){
                    continue;
                }
                if(array[i][j] == BOUND){
                    count++;
                }
            }
        }
        return count;
    }
    //열리는 부분 OPEN로 표시
    private void findEmptySquare(int rowIndex, int columIndex,String[][] array) {
        array[rowIndex][columIndex] = OPEN;

        int rowLength = rowIndex + 2; //검사할 행 범위
        int columLength = columIndex + 2;//검사할 열 범위
        boolean noMine = true;

        for(int i=rowIndex-1 ; i<rowLength ; i++){
            for(int j=columIndex-1 ; j<columLength ; j++){
                //카운드 상승 제외
                if(i<0 || j<0 || i>array.length-1 ||j>array[i].length-1  || (i == rowIndex && j ==columIndex || array[i][j]== OPEN)){
                    continue;
                }
                if(array[i][j] == BOUND){
                    noMine = false;
                }
            }
        }
        if(noMine){
            for(int i=rowIndex-1 ; i<rowLength ; i++){
                for(int j=columIndex-1 ; j<columLength ; j++){
                    //카운드 상승 제외
                    if(i<0 || j<0 || i>array.length-1 ||j>array[i].length-1  || (i == rowIndex && j ==columIndex || array[i][j]== OPEN)){
                        continue;
                    }

                    findEmptySquare(i, j ,array);
                }
            }
        }
    }

    //실행 메소드
    public int solution(int N,int[][] mine, int[] P){
        int answer = 0;

        //2차원 배열 보드판 생성

        String [][] array = new String [N][N];

        //지뢰 찍기(지뢰 BOUND)
        for(int i=0 ; i<mine.length ; i++) {
            int mineX = mine[i][0]-1;
            int mineY = mine[i][1]-1;
            array[mineX][mineY] = BOUND; //지뢰 표시

        }

        //지뢰 유무 숫자 표시
        for(int i=0 ; i<array.length ; i++) {
            for(int j=0 ; j<array[i].length ; j++){
                if(array[i][j] != BOUND){
                    array[i][j] = Integer.toString(countMine(i, j, array));

                }
            }
        }

        //열리는 부분 표시 - 제귀함수
        findEmptySquare(P[0]-1,P[1]-1,array);


        //지뢰 출력
        for (int i = 0; i < array.length; i++) {
            String[] inArr = array[i];
            for (int j = 0; j < inArr.length; j++) {
                System.out.print(inArr[j] + " ");
            }
            System.out.println();
        }

        //열린 수
        for(int i=0 ; i<array.length ; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] == OPEN){
                    answer++;
                }
            }
        }

        return answer;
    }
}


public class CodeTest {
    public static void main(String[] args) {
        Solution solu = new Solution();
        int [][] array =  {{1,2},{2,6},{3,4},{3,8},{4,9},{5,4},{5,8},{6,7},{7,2},{9,1}};
        int [] P = {8,5};
        solu.solution(9,array,P);

    }

}
