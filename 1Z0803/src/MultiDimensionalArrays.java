
public class MultiDimensionalArrays {

	int [] array1D = { 0, 1, 2, 4};

	
	//int [] [] array 2D = { { 0, 1, 2, 4} {5, 6}};
	public static void main(String[] args) {
		int [] array1D = { 0, 1, 2, 4};
		array1D[0]=array1D[1];

	int [] [] array2D = new int [2] [2];
	array2D[0] [0] = 1;
	array2D[0] [1] = 2;
	array2D[1] [0] = 3;
	array2D[1] [1] = 4;
	
	array2D[0] = array2D[1]; // Assignment has to be a array or else will fail on compilation
	
	
	int [] [] [] array3D1 = {{{0, 1}, {2, 3}, {4, 5}},{}};
	
	int [] [] [] array3D = new int [2] [2] [2];
	array3D [0] [0] [1]= 1;
	array3D [0] [1] = array3D[1][1];
//	array3D [1] [0] = array;
//	array3D [0] [1] = array;
	
	//int [] [] array2D = {0, 1};
	
}
}
