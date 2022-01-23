(ns todoist.routes
  (:require [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [todoist.controllers.users :as users]
            [todoist.controllers.tasks :as tasks]
            [ring.util.response :refer [response]]))

(defroutes users
  (GET "/users" []
    (response
     (users/get-all-users)))
  (GET "/users/:userId" [userId]
    (response
     (users/get-user userId)))
  (POST "/users" request
    (response
     (users/create-user (request :body))))
  (PUT "/users/:userId" [userId :as request]
    (response
     (users/update-user userId (request :body))))
  (DELETE "/users/:userId" [userId]
    (response
     (users/delete-user userId))))

(defroutes tasks
  (GET "/tasks" []
    (response
     (tasks/get-all-tasks)))
  (GET "/tasks/:taskId" [taskId]
    (response
     (tasks/get-task taskId)))
  (POST "/tasks" request
    (response
     (tasks/create-task (request :body))))
  (PUT "/tasks/:taskId" [taskId :as request]
    (response
     (tasks/update-task taskId (request :body))))
  (DELETE "/tasks/:taskId" [taskId]
    (response
     (tasks/delete-task taskId))))