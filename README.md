# Chess Project: Classes
2020-11-26 (15:04) : These classes will build the chess board and the functionality of its pieces. Once implemented, we should be able to play a pvp chess game on a console (not on the GUI just yet).

## Packages
* **board**: has classes Tile, EmptyTile, OccupiedTile, Move, activeMove, attackMove, and Board
* **pieces**: has classes Piece, and each concrete class of Piece (x16)
* **player**: has classes Player, WhitePlayer, and BlackPlayer

## (Abstract Class) Tile
**Package: Board**  
This class represents a tile on the chess board. There are 64 tiles on each board.  
- has coordinates (i.e. coord A3 = coord[3][1])  
`<checkIfOccupied()>`  
`<getPiece()>`

### (Concrete Class) EmptyTile
This class represents an empty, unoccupied tile.  
`<checkIfOccuied() : returns false;>`  
`<getPiece() : returns null;>`

### (Concrete Class) OccupiedTile
This class represents an occupied tile.  
`<checkIfOccuied() : returns false;>`  
`<getPiece() : returns piece on tile;>`

## (Abstract Class) Piece
**Package: Pieces**  
This class represents a chess piece entity. There are 6 different types of pieces.  
- has a position, state (i.e. active/dead), and alliance (white/black)  
`<legalMoves()>`

### (Concrete Classes) King, Rook, Bishop, Queen, Knight, and Pawn
`<legalMoves() : determine candidate move coordinantes.  
 For each candidate move, if tile to move to is unoccupied or is occupied by the opposite alliance:  
 add move to legal moves.>`

## (Abstract Class) Move
**Package: Board**  
This class allows for a piece to perform a legal move.  
- has its board, piece to move, and destination coordinates (selected by the user/computer).  
`<move()>`  
Note: after a move has been made, the move should get displayed to the user.

### (Concrete Class) ActiveMove
This class allows for a piece to make a legal move that does not involve attacking another piece.  
`<move() : make a new instance of a board with that move being made (along with the rest of the pieces on the board)>`

### (Concrete Class) AttackMove
This class allows for a piece to make a legal move that does not involve attacking another piece.  
`<move() : make a new instance of a board with that attack move being made (removing the attacked piece from the board and changing its state to dead/inactive).>`

