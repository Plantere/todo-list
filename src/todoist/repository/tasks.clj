(ns todoist.repository.tasks
  (:require [todoist.helpers.database :refer [check-exist generate-uuid append-json edit-by delete-by read-json-file get-by]]))

(def table-tasks "database/tasks.json")
(def table-users "database/users.json")

(defn create
  [title description user_id status]
  (if (check-exist user_id "id" table-users)
    (let [params {:id (generate-uuid) :title title :description description :status status :user_id user_id}]
      (append-json params table-tasks))
    (throw (AssertionError. "User not exist in the system"))))
  
(defn edit
  [task_id title description user_id status]
  (if (and (check-exist user_id "id" table-users) (check-exist task_id "id" table-tasks))
    (let [params {:id task_id :title title :description description :status status}]
      (edit-by task_id "id" table-tasks params))
    (throw (AssertionError. "user or task not found"))))
    
(defn delete
  [task_id]
  (if (check-exist task_id "id" table-tasks) 
    (delete-by task_id "id" table-tasks)
    (throw (AssertionError. "task not found"))))


(defn get-specific
  [task_id]
  (if (check-exist task_id "id" table-tasks)
    (get-by task_id "id" table-tasks)
    (throw (AssertionError. "task not found")))
  )


(defn get-all
  []
  (read-json-file table-tasks))