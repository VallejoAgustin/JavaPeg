# Cracker Barrel peg puzzle: 
# see https://shop.crackerbarrel.com/toys-games/games/travel-games/peg-game/606154

#IS GOOD
# from,over,to : describes moves
moves = (
  (0,1,3),
  (0,2,5),
  (1,3,6),
  (1,4,8),
  (2,4,7),
  (2,5,9),
  (3,6,10),
  (3,7,12),
  (4,7,11),
  (4,8,13),
  (5,8,12),
  (5,9,14),
  (3,4,5),
  (6,7,8),
  (7,8,9),
  (10,11,12),
  (11,12,13),
  (12,13,14)
)

#IS GOOD
# generator for moves and their oposites
def step() :
  myL = []
  for m in moves :
    f,o,t = m
    myL.append(m)
    myL.append((t,o,f))
  return myL

#IS GOOD, need to change all functions that use init()
# builds cells, 1 if full 0 if empty  
# returns as a pair a count k of the full ones and the cells
def init(i) :
  cells = []
  for j in range(16):
    #print(j)
    cells.append(1)
  cells[i] = 0
  #cells = [1]*15
  #cells[i] = 0
  cells[15] = 14

  #for j in cells:
  #  print(j)

  return cells
  #return (14,cells)

#IS GOOD
# performs, if possible, a move
# given the current occupancy of the cells
def move(kd,fot) :
   d = []#is basically k, d = kd
   for i in range(16):
    d.append(kd[i])
   k = kd[15]

   f,o,t=fot
   if d[f]==1 and d[o]==1 and d[t]==0 :
     c=d.copy()#copies array of board
     c[f]=0 # moved away
     c[o]=0 # remove jumped over
     c[t]=1 # landing here after jump

     c[15] = k - 1#modify the index that stores move count/if wrong

     #return (k-1,c)#returns 'kd'
     return c
   else :
     kd[15] = - 1#tells return that it is not valid
     return kd

# generator that yields all possible solutions
# given a cell configuration
def solve(kd) :
   solutions = []
   timesSolved = 0
   d = []
   for i in range(16):
    d.append(kd[i])
    k = kd[15]
   #k,d=kd
   if k<2 :
     #puzzle has been solved
     timesSolved += 1
     solutions.append(kd)
     #yield (None,kd)
     return 
   else :
     for m in step() :#goes through list returned by step 
       kc = move(kd,m)
       if kc[15] != -1 :#if valid move
         for r in solve(kc) :
           ms,newkd=r
           #yield (m,ms),newkd
           solutions.append(((m, ms), newkd))
   return solutions


# sets initial position with empty at i
# picks first solution
# collects path made of moves to a list
def puzzle(i) :
  kd = init(i)
  #ms,newkd = next(solve(kd))
  print(next(solve(kd))
  #ms, newkd = solve(kd)
  #mlist = []
  #while ms :
  #  m,ms = ms
  #  mlist.append(m)
  #return kd,mlist,newkd  

# shows the result by printing out successive states
def show(kd):
  k,d=kd
  lines = [(4,0,0),(3,1,2),(2,3,5),(1,6,9),(0,10,14)]
  for l in lines :
    t,a,b=l
    tab=' '*t
    print(tab,end='')
    for i in range(a,b+1) :
      if d[i]==0 : c='. '
      else : c = 'x '
      print(c,end='')
    print('')
  print('') 

# replay a sequence of moves, showing the state of cells  
def replay(ms,kd) :  
   for m in ms :
     show(kd)
     #print(kd)
     k,d=kd
     d=d.copy()
     f,o,t=m
     d[f]=0
     d[o]=0
     d[t]=1
     kd=k-1,d
   show(kd)

# visualizes a solution for each first 5 positions
# others look the same after 120 degrees rotations
def go() :
  for i in range(2) :
    print('='*3,i,'='*3)
    kd1,ms,kd2 = puzzle(i)  
    replay(ms,kd1)
    print('')
  
#go()
init(1)
puzzle(1)
'''
>>> go()
=== 0 ===
    . 
   x x 
  x x x 
 x x x x 
x x x x x 

    x 
   . x 
  . x x 
 x x x x 
x x x x x 

    x 
   x x 
  . . x 
 x x . x 
x x x x x 

    x 
   x x 
  x . x 
 . x . x 
. x x x x 

    x 
   . x 
  . . x 
 x x . x 
. x x x x 

    x 
   . x 
  . x x 
 x . . x 
. . x x x 

    x 
   . . 
  . . x 
 x x . x 
. . x x x 

    x 
   . x 
  . . . 
 x x . . 
. . x x x 

    . 
   . . 
  . . x 
 x x . . 
. . x x x 

    . 
   . . 
  . . x 
 . . x . 
. . x x x 

    . 
   . . 
  . . x 
 . . x . 
. x . . x 

    . 
   . . 
  . . . 
 . . . . 
. x x . x 

    . 
   . . 
  . . . 
 . . . . 
. . . x x 

    . 
   . . 
  . . . 
 . . . . 
. . x . . 


=== 1 ===
...
etc.

'''
  