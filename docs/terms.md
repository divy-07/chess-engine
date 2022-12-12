
### Engine notation

Represented using 4 characters, the notation is used for intermediate representation of moves between the GUI and engine.

The representation is different for normal moves, promotions, castling and en passant captures.
Each move is of type "abcd", as specified below:

#### Normal moves

- a = origin rank
- b = origin file
- c = destination rank
- d = destination file

#### Promotions

- a = origin file
- b = destination file
- c = promotion type (Q, R, B, N for white and q, r, b, n for black)
- d = side promoted ("P" for white and "p" for black)

#### Castling

TBA

#### En passant captures

- a = origin file
- b = destination file
- c = capturing side ("W" for white and "B" for black)
- d = "E" for en passant


### Algebraic notation

- TODO: add explanation for what the algebraic notation is
