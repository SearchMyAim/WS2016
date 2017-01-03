#!/usr/bin/env python3
# Baar Alexander

# Base class for chess figures
# ToDo: move doesn't have any impact or judgement yet.
class ChessFigure:
    def __init__(self, description, position, color, move, points):
        # Save description
        self.desc = description

        # Save initial position
        if len(position) is not 2:
            raise ValueError("Passed length of position is incorrect!")

        if (ord(position[:1].lower()) not in range(ord("a"), ord("i"))) or \
                ((ord(position[1:]) - 48) not in range(1, 9)):
            raise ValueError("Passed position is incorrect!")

        self.position = position

        # Check the passed color
        if color.lower() not in ["black", "white"]:
            raise ValueError("Given color is wrong. It must be either \"Black\" or \"White\"")
        self.color = color

        # Save moving range
        self.move = move

        # Save point value
        self.points = points
        return

    # Override standard function to return customized string
    def __str__(self):
        return self.color + " " + self.desc + " on position " + self.position


# King
# ToDo: add move constraints for this figure
class King(ChessFigure):
    def __init__(self, position, color):
        ChessFigure.__init__(self, "King", position, color, 1, 10)

# Queen
# ToDo: add move constraints for this figure
class Queen(ChessFigure):
    def __init__(self, position, color):
        ChessFigure.__init__(self, "Queen", position, color, 1, 9)

# Rook
# ToDo: add move constraints for this figure
class Rook(ChessFigure):
    def __init__(self, position, color):
        ChessFigure.__init__(self, "Rook", position, color, 1, 5)

# Bishop
# ToDo: add move constraints for this figure
class Bishop(ChessFigure):
    def __init__(self, position, color):
        ChessFigure.__init__(self, "Bishop", position, color, 1, 3)

# Knight
# ToDo: add move constraints for this figure
class Knight(ChessFigure):
    def __init__(self, position, color):
        ChessFigure.__init__(self, "Knight", position, color, 1, 3)

# Pawn
# ToDo: add move constraints for this figure
class Pawn(ChessFigure):
    def __init__(self, position, color):
        ChessFigure.__init__(self, "Pawn", position, color, 1, 1)


# Chess Board Class
# ToDo: add board related functionality like figure collision
class Board:
    def __init__(self, chessfigures):
        self.chessmen = chessfigures


# Method to create the chess figures out of the given string
def chessmen(configString):
    # Function array to ease up the figure creation
    figures = {
        'K': King,
        'Q': Queen,
        'R': Rook,
        'B': Bishop,
        'N': Knight,
        'P': Pawn
    }

    # Parse the given string and create the figures
    # ToDo: add string validation
    result = []
    chessmens = configString.split(",")
    for piece in chessmens:
        if (piece[:1].islower()):
            color = "Black"
        else:
            color = "White"

        result.append(figures[piece[:1].upper()](piece[1:], color))

    return result


# Helper to make grading (comparing strings) easier
def chessmenToString(configString):
    men = chessmen(configString)
    chessmenstrings = map(str, men)
    return " and ".join(chessmenstrings)

# Test positioning string
# ToDo: add error tests
currentPositions = "Ra1,ra8,Pa2,Pb2,Ph2"

# Create figures, place them on board and print them
pieces = chessmen(currentPositions)
mychessboard = Board(pieces)
for piece in mychessboard.chessmen:
    print(piece)

# Print summary of all placed figures.
print(chessmenToString(currentPositions))
