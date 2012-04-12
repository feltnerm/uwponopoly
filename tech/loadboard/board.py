from pprint import pprint
import json

space = {'name':'', 'color':'', 'price':'', 'rent':'', 
         '1houseprice':'', '2houseprice':'', '3houseprice':'',
         '4houseprice':'', 'hotelprice':''}

board = dict()

for x in range(1,41):
    s = space
    s['name'] = raw_input('Name: ')
    s['color'] = raw_input('Color: ')
    s['price'] = (x * 100) + 50
    s['rent'] = (x * 50)
    s['1houseprice'] = s['price'] - 50
    s['2houseprice'] = s['price'] - 25
    s['3houseprice'] = s['price'] - 20
    s['4houseprice'] = s['price'] - 15
    s['hotelprice'] = s['price'] - 10
    pprint(space)
    board[x] = space

with open('board.json', 'w') as f:
    json.dump(board, f)
