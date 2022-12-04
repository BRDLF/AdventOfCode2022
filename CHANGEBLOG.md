### Dec 4, 2022
Whew, did day 4. The collection functions included in Kotlin make quick work of this stuff.
Not that it's hard to just, write an extension function yourself. That's what I ended up doing for part Two here.
It saves lines!!! (like 5... and maybe makes the code a bit more unclear as a result. But I kinda like it. I enjoy passing functions as parameters :> )

I kinda wanna go back and clean `Main` up, I intended to be able to pass in args to select a day/part without the prompts. 
I should do that.
Later.

### Dec 3, 2022
Been a while since I journaled or blogged or reflected on code I wrote anywhere but my own head.
But I think it's a good habit to keep, so I'll stick with it for the duration of Advent of Code.

Kotlin has been really nice to work with, & the fact I've actually done any amount of coding recently helps me remember "Oh right, I can iterate over lists like this/like that."
These notes seem a bit unorganized. It's kinda early still. I should eat breakfast!

I remember Day2 Part Two gave me a tiny bit of trouble because I was being stubborn about not writing an enum. and in trying to keep things concise I made them unclear.
Like, it's not clear right away why we're using `when(it.second)` in part Two. Or why the numbers we're adding are 1,4,7
Going back and declaring some values would no doubt make this more bearable to look at.