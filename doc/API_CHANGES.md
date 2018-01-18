The main API Change was instead of returning a map of strings to values for the user defined methods and variables, we're returning an observable list of variables and UD methods from back end to front end. This change is very minor and is just repackaging data without changing the design.

We also changed the parameters of the moving turtle methods just because it turned out it was easier to implement that way using javafx properties. This change was for the better, because it makes the implementations less complicated.

WindowView, which was originally designed to manage the overall GUI structure, became redesigned as IDE class. A Window class was instead created to implement each component of GUI with specific tasks.

The internal API of turtle was changed because the task of managing the state of turtle became re-delegated to a turtle manager class.

All of the commands now have public constructor so that they can be instantiated during parsing.

