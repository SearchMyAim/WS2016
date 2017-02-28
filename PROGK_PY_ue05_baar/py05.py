#!/usr/bin/env python3
# Baar Alexander

class MovableObject:
    def __init__(self,x,y):
        self.posX=x
        self.posY=y
    def move(self,toX,toY):
        self.posX=toX
        self.posY=toY
    def __str__(self):
        return "{} {} at {}{}".format(self.color, self.name,self.posX,self.posY)

class Shape:
    def __init__(self,material):        
        self.material=material
    def __str__(self):
        return "{} {} {}".format(self.color,self.material,self.name)
        
class ChessPiece(MovableObject, Shape):
    abbrev = { 'k': "King",   'q': "Queen",  'r': "Rook",
               'b': "Bishop", 'n': "Knight", 'p': "Pawn"}

    def __init__(self, name, x, y, material = "wooden"):
        MovableObject.__init__(self, x, y)
        Shape.__init__(self, material)
        self.color = "Black" if name.islower() else "White"
        self.name = ChessPiece.abbrev[ name.lower() ]

class Board:
    def __init__(self,chessmen):
        self.chessmen=chessmen
    def __str__(self):
        return " and ".join( map(str,self.chessmen) )+"."

    # Overloading "+"
    def __add__(self, other):
        l = list(self.chessmen)
        l.append(other)
        return Board(l)

    # Overloading "+="
    def __iadd__(self, other):
        l = list(self.chessmen)
        l.append(other)
        self.chessmen = l
        return self

    # Overloading "=="
    def __eq__(self, other):
        if (self.__str__() == other.__str__()) :
            return "True"
        else :
            return "False"

# helpers:
def chessmen(configString): # "Kc4,rd2,Qb2" lowercase for BLACK
   result=[]
   for elem in configString.split(","):
       name=elem[0] # Black: kqrbnp and White:  KQRBNP 
       x=elem[1]
       y=elem[2]
       result += [ ChessPiece(elem[0],x,y) ]
   return result # list of objects


# Just for testing: 

# (the moodle-"evaluate" might  use different test data set!)

print('\nTEST "multiple inheritance" for a Chessman/Chesswoman:')
newBishop = ChessPiece("b","a","1") 
print(newBishop)    # Black Bishop at a1

print('\nTEST creation of a Chess board:')
mychessboard = Board( chessmen("Qb2,Rh3") )
print( mychessboard )# White Queen at b2 and White Rook at h3.

print('\nTest "+" operator overloading')
print( mychessboard  + ChessPiece("b","c","5") ) # White Queen at b2 and White Rook at h3 and Black Bishop at c5.
print( mychessboard )# White Queen at b2 and White Rook at h3.

print('\nTest "+=" operator overloading')
mychessboard += newBishop
print( mychessboard ) # White Queen at b2 and White Rook at h3 and Black Bishop at a1.

print('\nTest "==" operator overloading')
yourchessboard = Board( chessmen("Qb2,Rh3,ba1") )
print( yourchessboard )# White Queen at b2 and White Rook at h3 and Black Bishop at a1.
print( "My chess board is equal your board: {}.".format(mychessboard == yourchessboard) ) # My chess board is equal your board: True.
