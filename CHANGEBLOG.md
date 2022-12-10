### Dec 10, 2022
Easier one today. Or maybe I'm just more comfortable with this kind of problem? \
I had a bit of a tricky issue debugging part two, trying to visualize where the problem was (I wasn't subtracting 1 from the cycle before comparing it to the sprite position) \
Once I figured that out, though. No problems at all. I had fun with this one :)

### Dec 9, 2022
That was fun! But i maybe took too long to do it. I got tripped up in part 2 until I re-read the prompt. 
Seems I misunderstood the rules for knots moving at first. \
I seem to do that a lot! I need to be better about it.

### Dec 8, 2022
I'm worried that it's too early for me to get lazy like this. There's the idiom "work smarter not harder" and I feel I'm ignoring that.
With that in mind, this didn't take long, so that's nice at least. I'm satisfied but not happy, if that makes any sense.

##### Minor update
I actually went back and cleaned up(barely) the earlier week to better use the `inputList` val. \
In some cases, they just access it directly. In others, like day 5, there's some more abstraction that stays in the `init` \
Day 5 actually had to be changed a bit to allow the new `-Part Both` parameter that I added to make testing easier.
Maybe some other time I'll add a `-Day All` parameter in case I make big sweeping changes...

### Dec 7, 2022
It's starting to get properly messy, now. By not thinking ahead in some of these problems i've gotten really sloppy & inefficient.
But, it's complete. I'm thankful for that.

### Dec 6, 2022
Didn't start as early as i'd wanted to, lots of errands about the house.
But holy heck does Kotlin's `.distinct()` come in handy. Made this thing a breeze.
Also I still haven't implemented `args` properly... need to fix that.

Ok done.

### Dec 5, 2022
Easy enough. Had a bit of an issue with IntelliJ's text editor removing trailing whitespace causing an outofbounds error when trying to fill the array.
Fixed this by editing in notepad++. I probably could have just clarified that it's a txt file. It's fine though, otherwise everything went well.

I also added test arguments, and changed `Day` from an interface to an abstract class.

### Dec 4, 2022
Whew, did day 4. The collection functions included in Kotlin make quick work of this stuff.
Not that it's hard to just, write an extension function yourself. That's what I ended up doing for part Two here.
It saves lines!!! (like 5... and maybe makes the code a bit more unclear as a result. But I kinda like it. I enjoy passing functions as parameters :> )

I kinda wanna go back and clean `Main` up, I intended to be able to pass in args to select a day/part without the prompts. 
I should do that.
Later.
EDIT: ok it takes args now, but it's kinda sloppy

### Dec 3, 2022
Been a while since I journaled or blogged or reflected on code I wrote anywhere but my own head.
But I think it's a good habit to keep, so I'll stick with it for the duration of Advent of Code.

Kotlin has been really nice to work with, & the fact I've actually done any amount of coding recently helps me remember "Oh right, I can iterate over lists like this/like that."
These notes seem a bit unorganized. It's kinda early still. I should eat breakfast!

I remember Day2 Part Two gave me a tiny bit of trouble because I was being stubborn about not writing an enum. and in trying to keep things concise I made them unclear.
Like, it's not clear right away why we're using `when(it.second)` in part Two. Or why the numbers we're adding are 1,4,7
Going back and declaring some values would no doubt make this more bearable to look at.