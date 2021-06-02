OOSE Criteria response by Heetesh Doorbiz

If you are seeing html tags, do ignore it and go by the criteria numbers or go on 

https://gist.github.com/be3593b31bf36998e7a86fd6eee3e21d



<ol>
<li> Criteria 1


<br>I believe I did a reasonable job of separating the program into various packages. I first of all made a package called edu.curtin.comp2003 which contained two sub packages, rover and roverapi. I did this so that if you as the tester/marker are marking my work, you can easily substitute your own code into rover API and it should all work, hopefully. I also did this so becuase as per the requirements we assumed another team is developing the APIs, so I made them seperate and independent of the main rover package.

The main rover package contains 5 seperate sub-packages which each have a distinct purpose. I do admit that the MarsRover class could have been broken down into sub class that handles a specific task to satisfy single responsibility principle but I was short on time when designing and programming the software. Most of the packages and its containing code however is reasonably cohesive from my knowledge. I also had multiple interfaces classes and I designed my software such that one or more interface inherited directly another interface. This design had to do with event listeners. This practice I think is uncommon and I did it so that I can really emphasise what event is being used in the code.
</li>

<li> Criteria 2


<br>I had a single exception class extending RuntimeException which I believe I used a few times only. The reason why I didn't explicitly catch errors was because I contactact Dr David Cooper and he implied that catching errors was probably a bad practice when it came to catching API errors. Thus, I designed my code such that it would use less explicit Exception handling. I do admit that if I had more time as of writing this a day before the due date, I'd probably have found many places where to use explicit exception handling.
</li>

<li> Criteria 3


<br>I believe I implemented dependency injection. I used it in Mars rover class, in the strategy pattern (commandStrategy package), state classes and other places in the software.
</li>

<li> Criteria 4


<br>I used various patterns but if they are being marked only based on the two used out of all used pattern, I want to make it known that I want my state pattern implementation and the listener/observer pattern marked and not the strategy pattern.


I used the state pattern to represent states in the software. The software had 3 main states, Stopped, Driving and Analysis which all extended the RoverState abstract class. States was used handle specific functions of the rover accordinly and minimising the total code to write.
</li>


<li> Criteria 5


<br>I also used the observer pattern. My version of observer pattern is just listeners that are all contained in the MarsRover class. I used a concurrent safe list so I could at the same time access and modify the list whenever needed to prevent ConcurrentModificationException. I realise that isn't the way observer pattern were taught in this unit but I believe it should be fine it decoupled the code. I also added listeners inside the observer itself as it was needed for some event checking.
</li>

<li> Criteria 6


<br>I included both a UML and state chart diagram. The state chart is mostly just to make sense of how I design the state if you are interested. I mentioned this since it doesn't look like it will be marked as per the requirements. The UML represents the software written. It is clear and shows which package classes are contained in. It starts by showing the main package edu.curtin.comp2003 and its following sub packages. Relationships have been shown using the proper arrows.

<li> Criteria 7



<br>I did try to add generic for code reusuability but due to the lack of time and practice of using generics extension, I chose to omit it completely.</li>

</ol>