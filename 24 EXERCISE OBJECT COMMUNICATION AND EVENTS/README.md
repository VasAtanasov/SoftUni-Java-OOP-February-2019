# Exercises: Communication and Events

## Problem 1. Event Implementation

Create a class Dispatcher with a property name and a class Handler. Create an Event (a class that extends EventObject) NameChange - which holds a string changed name that it receives from the constructor and has a getter for it. Create also an interface NameChangeListener that defines a method handleChangedName(NameChange event).

Implement the NameChangeListener in the Handler class, the implementation should write on the console “Dispatcher’s name changed to \<newName>”.  Create in the Dispatcher a list of NameChangeListeners and 3 methods - addNameChangeListener, removeNameChangeListener and fireNameChangeEvent. As the names imply the add method should add a new NameChangeListener to the list the remove method should remove an existing NameChangeListener and the fire method should fire the event to all listeners in the list (call their handleChangedName method).

At the start of your program create a new Dispatcher and Handler, then add the Handler to the list of NameChangeListeners in the Dispatcher. 

### Input

From the console you will receive lines containing a name until the “End” command is received. For each name change the dispatcher’s name to it. Everytime the Dispatcher’s name is changed, you should fire an event to all observers.

### Output

For each name change of the dispatcher the handler should print “Dispatcher's name changed to \<newName>.” on the console.

### Constraints

- Names will contain only alphanumerical characters.
- The number of commands will be a positive integer between [1…100].
- The last command will always be the only “End” command.

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td>Pesho<br>Gosho<br>Stefan<br>End</td>
<td>Dispatcher's name changed to Pesho.<br>Dispatcher's name changed to Gosho.<br>Dispatcher's name changed to Stefan.</td>
</tr>
<tr>
<td>Prakash<br>Stamat<br>MuadDib<br>Ivan<br>Joro<br>End</td>
<td><br>Dispatcher's name changed to Prakash.<br>Dispatcher's name changed to Stamat.<br>Dispatcher's name changed to MuadDib.<br>Dispatcher's name changed to Ivan.Dispatcher's name changed to Joro.</td>
</tr>
</tbody>
</table>

<p><b>Solution: <a href="./excercises/src/Ex01EventImplementation">Event Implementation</a></b></p>

## blem 2. King’s Gambit

Implement 3 classes - King, Footman and Royal Guard. All of them have a name (names are unique there will never be two units with the same name), Footmen and Royal Guards can also be killed (killed units are removed from the program), while the king is attackable - should have a method to respond to attacks. Whenever the king is attacked, he should print to the console “King <kingName> is under attack!”and all alive Footmen and Royal guards should respond to the attack:

- Footman respond by writing to the console “Footman \<footmanName> is panicking!”.
- Royal Guards instead write “Royal Guard \<guardName> is defending!”.

### Input

On the first line of the console you will receive a single string - the name of the king. On the second line you will receive the names of his Royal Guards separated by spaces. On the third the names of his Footmen separated by spaces. On the next lines until the command “End” is received, you will receive commands in one of the following format:

- “Attack King” - calls the king’s respond to attack method.
- “Kill \<name>” - the Footman or Royal Guard with the given name is killed.

### Output

Whenever the king is attacked you should print on the console “King \<kingName> is under attack!” and each living Footman and Royal Guard should print their response message - first all Royal Guards should respond (in the order that they were received from the input) and then all Footmen should respond (in the order that they were received from the input). Every message should be printed on a new line.

### Constraints

- Names will contain only alphanumerical characters.
- There will always be a king and at least one Footman and one Royal Guard.
- The king cannot be killed - there will never be a kill command for the king.
- Kill commands will be received only for living soldiers.
- All commands received will be valid commands in the formats described.
- The number of commands will be a positive integer between [1…100].
- The last command will always be the only “End” command.

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td>Pesho<br>Krivogled Ruboglav<br>Gosho Pencho Stamat<br>Attack King<br>End</td>
<td>King Pesho is under attack!<br>Royal Guard Krivogled is defending!<br>Royal Guard Ruboglav is defending!<br>Footman Gosho is panicking!<br>Footman Pencho is panicking!<br>Footman Stamat is panicking!</td>
</tr>
<tr>
<td>HenryVIII<br>Thomas Oliver<br>Mark<br>Kill Oliver<br>Attack King<br>Kill Thomas<br>Kill Mark<br>Attack King<br>End</td>
<td>King HenryVIII is under attack!<br>Royal Guard Thomas is defending!<br>Footman Mark is panicking!<br>King HenryVIII is under attack!</td>
</tr>
</tbody>
</table>

<p><b>Solution: <a href="./excercises/src/Ex02KingGambit">King’s Gambit</a></b></p>

## Problem 3. Dependency Inversion

The project must provide class Primitive Calculator which supports two methods - changeStrategy(char operator) and performCalculation(int firstOperand, int secondOperand). The performCalculation method should perform a mathematical operation on the two operands based on the Primitive Calculator’s current Strategy and the changeStrategy should change the calculator’s current Strategy. Make Strategies which supports adding, subtracting, division and multiplication strategies. Think how the project should be structured in orded to support any strategy. Add functionality of your choise NOTE YOU NEED TO TEST THOSE WITH YOUR OWN TESTS . The provided test support only the base four operations.

The calculator should start by default in addition mode. 

### Input

From the console you will receive lines in one of the following formats until the command “End” is received:

- “\<number> \<number>” - perform calculation on the current numbers based on the current mode of the calculator.
- “mode \<operator>” - changes the mode of the calculator to the specified one. 

### Output

Print the results of the calculation of all number lines - each result on a new line.

### Constraints

- The operators received from the console will always be valid ones specified in the calculator modes section.
- The result of the calculations should also be an integer.
- There will never be a 0 divisor.
- The last command will always be the “End” command.

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td>10 15<br>mode /<br>20 5<br>17 7<br>mode -<br>30 31<br>End</td>
<td>25<br>4<br>2<br>-1</td>
</tr>
<tr>
<td>mode *<br>1 1<br>3 21<br>-5 -6<br>mode -<br>-30 -50<br>mode /<br>-28 4<br>mode +<br>1 10<br>End</td>
<td>1<br>63<br>30<br>20<br>-7<br>11</td>
</tr>
</tbody>
</table>

<p><b>Solution: <a href="./excercises/src/Ex03DependencyInversion">Dependency Inversion</a></b></p>

## Problem 4. \*\*Work Force

Create two classes - StandartEmployee and PartTimeEmployee, both of which have a name and work hours per week. The StandartEmployee’s work hours per week are always 40 and the PartTimeEmployee’s work hours per week are always 20. Create a class Job which should receive an employee through its constructor, have fields - name and hours of work required and a method update which should subtract from its hours of work required the employee’s work hours per week. Whenever a job’s hours of work required reaches 0 or less it should print “Job \<jobName> done!” and find a way to notify the collection you hold all jobs in, that it is done and should be deleted from the collection.

### Input

From the console you will receive lines in one of the following formats until the command “End” is received:

- “Job \<nameOfJob> \<hoursOfWorkRequired> \<employeeName>”- should create a Job with the specified name, hours of work required and employee.
- “StandartEmployee \<name>” - should create a Standart Employee with the specified name.
- “PartTimeEmployee \<name>” - should create a Part Time Employee with the specified name.
- “Pass Week” - should call each job’s update method.
- “Status” - should print the status of all jobs in the following format “Job: \<jobName> Hours Remaining: \<hoursOfWorkRequired>”.

### Output

Every time a job ends the message “Job \<jobName> done!” should be printed on the console. Every time a Status command is received all jobs currently active (not completed) should be printed on the console in the format specified on the Status, in order of being receiving them from the input - each message on a new line.

###Constraints

- All names will consist of alphanumerical characters.
- All hours of work required will be valid positive integers between [1…1000].
- The employee specified in the Job input line will always be a valid existing employee.
- Employee and Job names are unique - there will never be two Employee/Jobs with the same name.
- The last command will always be “End”.

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td>StandartEmployee Pesho<br>PartTimeEmployee Penka<br>Job FeedTheFishes 45 Pesho<br>Pass Week<br>Status<br>Pass Week<br>End</td>
<td>Job: FeedTheFishes Hours Remaining: 5<br>Job FeedTheFishes done!</td>
</tr>
<tr>
<td>PartTimeEmployee Penka<br>PartTimeEmployee Vanka<br>PartTimeEmployee Stanka<br>Job Something 177 Stanka<br>Pass Week<br>Job AnotherThing 33 Vanka<br>Status<br>Pass Week<br>Pass Week<br>Pass Week<br>Status<br>End</td>
<td>Job: Something Hours Remaining: 157<br>Job: AnotherThing Hours Remaining: 33<br>Job AnotherThing done!<br>Job: Something Hours Remaining: 97</td>
</tr>
</tbody>
</table>

#### Hint

Find a way to have your collection respond to events. Create your own class extending the ArrayList and implementing an EventListener to a custom event which is triggered when a job is done. Use abstraction in the Job class to allow for different type of employees to be accepted - i.e. extract an interface for employees and have the Job class accept an object from that implements the interface instead of a concrete class.

<p><b>Solution: <a href="./excercises/src/Ex04WorkForce">Work Force</a></b></p>

## Problem 5. \*\*King’s Gambit Extended

Extend your code from Problem 2 King’s Gambit - normal Footmen should now die in 2 hits (you would have to receive 2 Kill commands with their name from the input to kill them), while Royal Guards should die from 3 hits. Dead Footmen and Royal Guards should still not respond to the king being attacked and be deleted from the collection of units. Find a way for the dying soldiers to communicate their deaths to the king and the collection holding them without you manually checking their state at each Kill command (i.e. use Events).

### Input

On the first line of the console you will receive a single string - the name of the king. On the second line you will receive the names of his royal guards separated by spaces. On the third the names of his Footmen separated by spaces. On the next lines until the command “End” is received, you will receive commands in one of the following format:

- “Attack King” - calls the king’s respond to attack method.
- “Kill \<name>” - the Footman or Royal Guard with the given name is attacked, if this is the second Kill command for Footmen or the third for Royal Guards - they are killed.

### Output

Whenever the king is attacked you should print on the console “King \<kingName> is under attack!” and each living footman and royal guard should print their response message - first all royal guards should respond (in the order that they were received from the input) and then all footmen should respond (in the order that they were received from the input). Every message should be printed on a new line.

### Constraints

- Names will contain only alphanumerical characters.
- There will always be a king and at least one Footman and one Royal Guard.
- The king cannot be killed - there will never be a kill command for the king.
- All commands received will be valid commands in the formats described.
- Kill commands will be received only for living soldiers.
- The number of commands will be a positive integer between [1…100].
- The last command will always be the only “End” command.

### Examples

<table>
<thead>
<tr>
<th>Input</th>
<th>Output</th>
</tr>
</thead>
<tbody>
<tr>
<td>Pesho<br>Ruboglav<br>Gosho Stamat<br>Kill Gosho<br>Kill Stamat<br>Attack King<br>Kill Gosho<br>Attack King<br>End</td>
<td>King Pesho is under attack!<br>Royal Guard Ruboglav is defending!<br>Footman Gosho is panicking!<br>Footman Stamat is panicking!<br>King Pesho is under attack!<br>Royal Guard Ruboglav is defending!<br>Footman Stamat is panicking!</td>
</tr>
<tr>
<td>HenryVIII<br>Thomas<br>Mark<br>Kill Thomas<br>Kill Mark<br>Attack King<br>Kill Thomas<br>Kill Thomas<br>Kill Mark<br>Attack King<br>End</td>
<td>King HenryVIII is under attack!<br>Royal Guard Thomas is defending!<br>Footman Mark is panicking!<br>King HenryVIII is under attack!</td>
</tr>
</tbody>
</table>

<p><b>Solution: <a href="./excercises/src/Ex05KingsGambitExtended">King’s Gambit Extended</a></b></p>

<p><b>Document with tasks description: <a href="./resources/11. Java-OOP-Advanced-Communication-Exercises.docx">11. Java-OOP-Advanced-Communication-Exercises.docx</a></b></p>