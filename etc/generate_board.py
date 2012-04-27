#!/usr/bin/env python

import json
import os
from pprint import pprint


rents = []

p = {
    "title": '',
    "type": "PROPERTY",
    "position": 0,
    "price": 0,
    "property_color": "",
    "house_cost": 0,
    "hotel_cost": 0,
    "mortgage": 0,
    "rents": rents
}

for i in range(1, 41):
    print 'ON SPACE #: %d' % i
    title = raw_input("Title: ")
    property_color = raw_input("Color: ")
    price = i * 50
    house_cost = price - 10
    hotel_cost = house_cost * 2
    mortgage = price * 0.5
    rents = [x * (house_cost) for x in range(1, 7)]

    p['title'] = title
    p['position'] = i
    p['property_color'] = property_color
    p['price'] = price
    p['house_cost'] = house_cost
    p['hotel_cost'] = hotel_cost
    p['mortgage'] = mortgage
    p['rents'] = rents

    s = json.dumps(p, sort_keys=True, indent=4)
    s2 = '\n'.join([l.rstrip() for l in s.splitlines()])
    s2 = s2 + ','
    print s2

    cmd = 'echo "%s" | tr -d "\n" | pbcopy' % s2
    os.system(cmd)
