# Chess Project: Classes
2020-11-26 (15:04) : These classes will build the chess board and the functionality of its pieces. Once implemented, we should be able to play a pvp chess game on a console (not on the GUI just yet).  
2020-11-29 (14:17) : Updating and correcting class descriptions.

## Packages
* **board**: has classes Tile, EmptyTile, OccupiedTile, Move, activeMove, attackMove, and Board
* **pieces**: has classes Piece, and each concrete class of Piece (x16)
* **player**: has classes Player, WhitePlayer, and BlackPlayer

## (Abstract Class) Tile
**Package: Board**  
This class represents a tile on the chess board. There are 64 tiles on each board.  
- has coordinates (i.e. coord A3 = coord[3][1])  
- has piece
`<checkIfOccupied()>`  
`<setPiece()>`  
`<getPiece()>`  
`<setCoords()>`

### (Concrete Class) EmptyTile
This class represents an empty, unoccupied tile.  
`<checkIfOccuied() : returns false;>`  
`<getPiece() : returns null;>`

### (Concrete Class) OccupiedTile
This class represents an occupied tile.  
`<checkIfOccuied() : returns true;>`  
`<getPiece() : returns piece on tile;>`

## (Abstract Class) Piece
**Package: Pieces**  
This class represents a chess piece entity. There are 6 different types of pieces.  
- has a position  
- has a state (i.e. active/dead)  
- has a alliance (white/black)  
- has a board  
`<legalMoves() : unique for each piece>`  
`<getAlliance()>`  
`<setPosition()>`  
`<getPosition()>`  
`<setState()>`

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
This class represents the actual chess board.  
- has list of board tiles  
- has list of dead pieces  
- has two players  
- has White side (top/bot)  
`<createBoard() : generate a new board with an initial state>`  
`<setWhiteSide()> : determine which side is the white side`  
`<setPieces()> : set the current layout of the board`  
`<setTile()> : of type EmptyTile or OccupiedTile`  
`<getTile()> : get a tile at a given position`  
`<getBoardTiles()> : get the list of all board tiles`  
`<addDeadPiece()> : add an attacked piece to the list of dead pieces`  

## (Abstract Class) Player
**Package: Player**  
This class represents a player (either human or computer).  
- has alliance (white/black)  
- has boardSide (top/bottom of board)  
- has type (computer/player)  
- has list of active pieces  
`<getAlliance()>`  
`<getBoardSide()>`  
`<getType()>`  
`<getActivePieces()> : return list of active pieces`  
`<getActivePiece()>`  
`<isMoveLegal()>`  
`<makeMove()>`  
`<isInCheck()> : returns true if the opposite alliance can attack the player's King piece with any of their pieces>`  
`<isInCheckMate()> : returns true if player's King piece is in check and cannot make any legal moves to break out of its check state>`  
`<isInStaleMate()> : returns true if player's King piece is not in check and cannot make any legal moves`  
`<isCastled()> : returns true if player chooses to castle, and perform the swap when castled>` look into rules of castling..

### (Concrete Class) WhitePlayer
`<getAlliance()> : return "white"`  
`<getActivePieces()>`  
`<isMoveLegal()>`  
`<isInCheck()>`  
`<isInCheckMate()>`  
`<isInStaleMate()>`  
`<isCastled()>`  
`<makeMove()>`

### (Concrete Class) BlackPlayer
`<getAlliance() : return "Black";>`  
`<getActivePieces()>`  
`<isMoveLegal()>`  
`<isInCheck()>`  
`<isInCheckMate()>`  
`<isInStaleMate()>`  
`<isCastled()>`  
`<makeMove()>`
