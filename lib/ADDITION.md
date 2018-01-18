Estimation:

Back-End extension

I believe that the following changes will take approx. 30 minutes to complete. It will involve adding two new files to the project: Stamp.java and ClearStamp.java. 
I will also edit the various resource files to enable use of these commands in all languages.

Review:

It took 21 minutes to complete the changes. I had one small bug with the naming of my clearstamps class. I didn't realize that the stamp and clearstamp commands had already been added to the 
resource files for the project, and added them again, but with the name slightly changed to clearstamp instead of clearstamps. This caused a reflection error. A quick refarctor->rename solved the issue though.

I needed to update 4 files, the two new commands I added, as well as adding a helper method to the turtle class so that I could get a copy of the image. Finally, I needed to add two methods to the turtle display to show/hide the stamps on the display.

The design for the project was solid. I was able to go in and make the changes for this analysis in just a couple minutes, after not touching the code in a while. Because each command is so similar, I was able to copy-paste the active turtles loop from another command and then add a single line to implement the change. Fortunately I remembered that the turtle display held the screen objects, and so was able to quickly incorporate my changes there. Perhaps the turtles and turtle display should have been in their own package. If I were not familiar with the code at all, the helper methods for the turtles would have been harder to find, but the commands are incredibly straight forward since you only need to update a single method.