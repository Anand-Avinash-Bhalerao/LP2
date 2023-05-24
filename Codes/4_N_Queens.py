class NQueen:
	def __init__(self, N):
		self.N = N
		self.board = []
		self.slashCode = []
		self.backslashCode = []
		self.rowLookup = []
		self.slashCodeLookup = []
		self.backslashCodeLookup = []

		self.solveNQueens()
	
	def print_board(self):
		for i in range(self.N):
			for j in range(self.N):
				print(self.board[i][j], end = " ")
			print()
	
	def isSafe(self, row, col):
		if (self.slashCodeLookup[self.slashCode[row][col]] or
			self.backslashCodeLookup[self.backslashCode[row][col]] or
			self.rowLookup[row]):
			return False
		return True

	def solveNQueensUtil(self, col):
							
		""" base case: If all queens are
		placed then return True """
		if(col >= self.N):
			return True
		for i in range(self.N):
			if(self.isSafe(i, col)):
						
				""" Place this queen in board[i][col] """
				self.board[i][col] = 1
				self.rowLookup[i] = True
				self.slashCodeLookup[self.slashCode[i][col]] = True
				self.backslashCodeLookup[self.backslashCode[i][col]] = True
				
				""" recur to place rest of the queens """
				if(self.solveNQueensUtil(col + 1)):
					return True
				
				""" If placing queen in board[i][col]
				doesn't lead to a solution,then backtrack """
				
				""" Remove queen from board[i][col] """
				self.board[i][col] = 0
				self.rowLookup[i] = False
				self.slashCodeLookup[self.slashCode[i][col]] = False
				self.backslashCodeLookup[self.backslashCode[i][col]] = False
				
		""" If queen can not be place in any row in
		this column col then return False """
		return False

	def solveNQueens(self):
		self.board = [[0 for i in range(self.N)]
					for j in range(self.N)]
		self.slashCode = [[0 for i in range(self.N)]
						for j in range(self.N)]
		self.backslashCode = [[0 for i in range(self.N)]
							for j in range(self.N)]
		
		self.rowLookup = [False] * self.N
		
		# keep two arrays to tell us
		# which diagonals are occupied
		x = 2 * self.N - 1
		self.slashCodeLookup = [False] * x
		self.backslashCodeLookup = [False] * x
		
		for rr in range(self.N):
			for cc in range(self.N):
				self.slashCode[rr][cc] = rr + cc
				self.backslashCode[rr][cc] = rr - cc + self.N - 1
		
		if(self.solveNQueensUtil(0) == False):
			print("Solution does not exist")
			return False
			
		self.print_board()
		return True

if __name__ == "__main__":
    N = int(input("Enter N: "))
    Q = NQueen(N)