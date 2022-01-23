(ns todoist.controllers.tasks
  (:require [todoist.repository.tasks :as tasks]))

(defn get-all-tasks [] (tasks/get-all))
(defn get-task [id] (tasks/get-specific (str id)))
(defn create-task [params] (tasks/create (params :title) (params :description) (params :user_id) (params :status)))
(defn update-task [id params] (tasks/update-tasks (str id) (params :title) (params :description) (params :user_id) (params :status)))
(defn delete-task [id] (tasks/delete (str id)))