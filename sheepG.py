#Game
from pygame import *
size=width,height=800,600
screen=display.set_mode(size)
RED=(255,0,0)   
GREEN=(0,255,0)
BLUE=(0,0,255)
BLACK=(0,0,0)
WHITE=(255,255,255)


running=True
while running:
    for evt in event.get():
        if evt.type==QUIT:
            running=False
    

    display.flip() 

quit()
