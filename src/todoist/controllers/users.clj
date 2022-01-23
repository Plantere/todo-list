(ns todoist.controllers.users
  (:require [todoist.repository.users :as users]))

(defn get-all-users [] (users/get-all))
(defn get-user [id] (users/get-specific (str id)))
(defn create-user [params] (users/create (params :name)))
(defn update-user [id params] (users/update-users id (params :name)))
(defn delete-user [id] (users/delete id))