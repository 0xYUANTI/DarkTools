# DarkTools

A collection of command-line tools which help you plan your Dark Souls
character.

The data set is taken from

    http://www.raymondhill.net/darksouls/darksouls-armor-calc.php

many thanks to him!

## Usage
The syntax is

    $ lein run TOOL TOOL-ARGS

Currently, there's only one tool, SUITUP

    $ lein run suitup MIN-POISE MAX-WEIGHT BODY-PART ...

MIN-POISE and MAX-WEIGHT are floats; known body parts include *head*,
*torso*, *legs*, and *arms*.
A typical query might look like so:

    $ lein run suitup 36 24 torso legs arms >SUITS36

which will print all helmet-less suits which provide at least 36 poise
while weighing at most 24 pounds, sorted by DEF.

## License
Copyright 2012 jakob@primat.es

Distributed under the new BSD license.
