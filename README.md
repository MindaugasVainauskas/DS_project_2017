# DS_project_2017
RMI Dictionary Service Project for Distributed Systems module. GMIT 2017

Author: Mindaugas Vainauskas, Year 4, Software Development module, GMIT 2017-2018

### Project Description
This project is a Dictionary service application running on localhost Tomcat server and utilizing RMI server. Users can submit requests for definitions of words. If definition is found on a dictionary file provided in the package ("dictionary.csv") it is returned on next page with task number of that thread.

### Project Architecture

Project is utilizing Java RMI architecture with Servitor interface as a base. DictionaryService is an implementation of Servitor interface. DictionaryMap file maps words and values from dictionary csv file and stores it in a hash map which is then utilised by DictionaryService to see if word submitted by user exists on the dictionary hash map key list. DictionaryServer rebinds implementation of DiscionaryService for client to use via RMI. Client side is a jsp page with simple form input for words. Once word is submitted the task number for current job and its definition are returned to the customer with a button to go back to submission page. Servlet file behind the jsp pages takes in word submitted by user, adds it to inQueue, after that word is processed via RMI method call to DictionaryService and taken out of the inQueue and added into outQueue where it is assigned to the definition variable and displayed to the user.

### Running project

To run the project, users should navigate to project folder in terminal and start DictionaryServer file in ie.gmit.sw.server package first. After that users should start tomcat server and navigate to *http://localhost:8080/DS_Project/Index.jsp* on their prefered browser. 
Requests are submitted via input form where word is written in and once submit button is clicked the definition of the word is shown(if not found in dictionary a message notifying that no definitions found is shown instead). Users can then click on button under the definition to look for definition of another word.
