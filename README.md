# To-do-list :pushpin:

Simple To-Do-List using clojure as primary language, and json as a way to store data of the API.

## Usage

To use this API:

    ;;First you need install all dependencies that we required to run this API, 
    lein deps

    ;;In second you need run lein
    lein run

when you run the command "lein run", you can use localhost:3000 to access the API

## Routes
###### Users
    GET /users/
    - This route return all stored users in users.json file
    GET /users/{UUID}
    - This route return all informations of user with uuid established
    PUT /users/{UUID}
    - This route can change name of user if exist, you have to send a body, like
      {
        "name":""
      }
    POST /users/
    - This route can create a user, you have to send a body with name, like
      {
        "name":""
      }
    DELETE /users/{UUID}
    - This route can delete the user specified if exist


###### Tasks
    GET /tasks/
    - This route return all stored tasks in tasks.json file
    GET /tasks/{UUID}
    - This route return all informations of task with uuid established
    PUT /tasks/{UUID}
    - This route can change name of task if exist, you have to send a body, like
      {
        "title":""
        "description":""
        "user_id":""
        "status":""
      }
    POST /tasks/
    - This route can create a task, you have to send a body, like
      {
        "title":""
        "description":""
        "user_id":""
        "status":""
      }
    DELETE /tasks/{UUID}
    - This route can delete the task specified if exist