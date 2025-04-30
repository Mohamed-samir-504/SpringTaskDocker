# SpringTaskDocker

The course adding is done through an html form which is fetched when "/form" is called

![image](https://github.com/user-attachments/assets/a25b5d17-68a2-4c7c-aa8a-2852f93f4972)
Therefore, the controller uses @ModelAttribuite not @ResponseBody

Extra bonus task:
Added XSD to generate a course class with extra attribuite
Created custom DTO for that class
Created a mock server using SOAP UI which returns a list of CourseXSD Dtos when the external api is called

Uses docker container of mysql server

![image](https://github.com/user-attachments/assets/226cf566-712c-45f4-add3-07eb28814c9c)
