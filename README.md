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
- has a position  
- has a state (i.e. active/dead)  
- has a alliance (white/black)  
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

## (Class) Board
**Package: Board**
This class represents the actual chess board. The board class has the following:  
* list of tiles
* collection of white pieces
* collection of black pieces
* list of active pieces  
`<createBoard()>`  
`<setPieces()>`

## (Abstract Class) Player
**Package: Player**  
This class represents a player (either human or computer player).  
- has alliance (white/black)  
- has type (computer/player)  
- has boardSide (top/bottom of board)  
`<getAlliance()>`  
`<getActivePieces()>`  
`<isMoveLegal()>`  
`<isInCheck()>`  
`<isInCheckMate()>`  
`<isInStaleMate()>`  
`<isCastled()>`  
`<makeMove()>`

### (Concrete Class) WhitePlayer
`<getAlliance() : return "white";>`  
`<getActivePieces() : return list of active pieces (not dead)>`  
`<isMoveLegal() : return true if legal>` This is the player's move, not necessarily the piece's move.  
`<isInCheck() : returns true if the opposite alliance can attack the player's King piece with any of their pieces>`  
`<isInCheckMate() : returns true if player's King piece is in check and cannot make any legal moves to break out of its check state>`  
`<isInStaleMate() : returns true if player's King piece is not in check and cannot make any legal moves>`  
`<isCastled() : returns true if player chooses to castle, and perform the swap when castled>` look into this for the rules of castling..  
`<makeMove() : performs the White Player's move>`

### (Concrete Class) BlackPlayer
`<getAlliance() : return "Black";>`  
`<getActivePieces() : return list of active pieces (not dead)>`  
`<isMoveLegal() : return true if legal>` This is the player's move, not necessarily the piece's move.  
`<isInCheck() : returns true if the opposite alliance can attack the player's King piece with any of their pieces>`  
`<isInCheckMate() : returns true if player's King piece is in check and cannot make any legal moves to break out of its check state>`  
`<isInStaleMate() : returns true if player's King piece is not in check and cannot make any legal moves>`  
`<isCastled() : returns true if player chooses to castle, and perform the swap when castled>` look into this for the rules of castling..  
`<makeMove() : performs the Black Player's move>`
