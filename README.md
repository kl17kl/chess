# Chess Project: Classes
2020-11-26 (15:04) : These classes will build the chess board and the functionality of its pieces. Once implemented, we should be able to play a pvp chess game.

## Packages
* board: has classes Tile, EmptyTile, OccupiedTile, Move, activeMove, attackMove, and Board
* pieces: has classes Piece, and each concrete class of Piece (x16)
* player: has classes Player, WhitePlayer, and BlackPlayer

## (Abstract) Tile
**Package: Board**
This class represents a tile on the chess board. There are 64 tiles on each board.
> has coordinates (i.e. coord A3 = coord[3][1])
> checkIfOccupied()
> getPiece()

### (Concrete) EmptyTile
This class represents an empty, unoccupied tile.
> checkIfOccuied() : returns false;
> getPiece() : returns null;

### (Concrete) OccupiedTile
This class represents an occupied tile.
> checkIfOccuied() : returns true;
> getPiece() : returns piece on tile;

