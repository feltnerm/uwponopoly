from pprint import pprint
import json

space = {'name':'', 'color':'', 'price':'', 'rent':'', 
         '1houseprice':'', '2houseprice':'', '3houseprice':'',
         '4houseprice':'', 'hotelprice':''}

board = dict()

for x in range(1,41):
    space['name'] = raw_input('Name: ')
    space['color'] = raw_input('Color: ')
    space['price'] = (x * 100) + 50
    space['rent'] = (x *50)
    space['1houseprice'] = space['price'] - 50
    space['2houseprice'] = space['price'] - 25
    space['3houseprice'] = space['price'] - 20
    space['4houseprice'] = space['price'] - 15
    space['hotelprice'] = space['price'] - 10
    pprint(space)
    board[x] = space

with open('board.json', 'w') as f:
    json.dump(board, f)
